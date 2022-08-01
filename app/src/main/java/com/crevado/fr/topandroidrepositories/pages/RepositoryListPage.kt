package com.crevado.fr.topandroidrepositories.pages

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.SupervisedUserCircle
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.room.withTransaction
import com.crevado.fr.topandroidrepositories.R
import com.crevado.fr.topandroidrepositories.activity.MainViewModel
import com.crevado.fr.topandroidrepositories.data.local_db.GithubData
import com.crevado.fr.topandroidrepositories.utils.LoadingView
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.launch


@Composable
fun RepositoryListPage(navController: NavController, viewModel: MainViewModel) {

    val data: List<GithubData> by viewModel.database.githubDao().repoList()
        .collectAsState(initial = emptyList())
    val loader = viewModel.showLoader.collectAsState()

    Box(modifier = Modifier.background(Color.White)) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(all = 20.dp)
        ) {
            SearchBar(viewModel, data)
            if (data.isNotEmpty()) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 20.dp)
                ) {
                    items(data) { item ->
                        RepoItem(item, navController, viewModel)
                    }
                }
            }
        }

        if (data.isEmpty()) {

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.align(Alignment.Center)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_github_brands),
                    contentDescription = "",
                    modifier = Modifier.size(
                        100.dp
                    )
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    "Search Top \nAndroid Repositories",
                    textAlign = TextAlign.Center,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }

        }

        if (loader.value) {
            LoadingView(modifier = Modifier.align(Alignment.Center))
        }
    }

}


@Composable
fun RepoItem(item: GithubData, navController: NavController, viewModel: MainViewModel) {
    Row(verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier
            .fillMaxWidth()
            .height(65.dp)
            .background(
                color = Color(0xffEAF0EC),
                shape = RoundedCornerShape(10.dp)
            )
            .clickable {
                viewModel.detailData = item
                navController.navigate("details_page")
            }) {

        Spacer(modifier = Modifier.width(15.dp))

        GlideImage(imageModel = item.avatar, loading = {
            Icon(
                Icons.Filled.SupervisedUserCircle,
                contentDescription = "Avatar",
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
            )
        }, success = { imageState ->
            imageState.drawable?.toBitmap().let {
                Image(
                    bitmap = it!!.asImageBitmap(),
                    contentDescription = "Loaded Image",
                    modifier = Modifier
                        .size(42.dp)
                        .clip(CircleShape)
                )
            }

        })

        Spacer(modifier = Modifier.width(15.dp))

        Column {
            Text(
                text = item.name,
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = item.fullName,
                fontSize = 13.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Gray
            )

        }

    }

    Spacer(modifier = Modifier.height(10.dp))
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchBar(viewModel: MainViewModel, data: List<GithubData>) {
    var searchText by remember { mutableStateOf(TextFieldValue("")) }
    val keyboardController = LocalSoftwareKeyboardController.current
    var expanded by remember { mutableStateOf(false) }
    val context = LocalContext.current

    Box(
        Modifier
            .fillMaxWidth()
            .height(70.dp)
            .background(color = Color(0xffEAF0EC), shape = RoundedCornerShape(10.dp))
    ) {
        TextField(
            value = searchText,
            placeholder = { Text(text = "Search here (eg: Android)", fontSize = 15.sp) },
            onValueChange = {
                searchText = it
            },
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
                .background(Color.Transparent)
                .padding(start = 5.dp, end = 5.dp),
            textStyle = TextStyle(
                fontSize = 15.sp,
            ),
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.Black,
                backgroundColor = Color.Transparent,
                cursorColor = Color.Black,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = {
                keyboardController?.hide()

                if (searchText.text.trim() == "Android") {
                    viewModel.prefsHelper.put("search_text", searchText.text.trim())
                    viewModel.apiSearchRepo(
                        searchKey = searchText.text.trim(),
                        viewModel.prefsHelper["sort", ""]
                    )
                } else if (searchText.text
                        .trim()
                        .isEmpty()
                ) {
                    Toast
                        .makeText(context, "Search bar is empty!", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    Toast
                        .makeText(
                            context,
                            "Search with 'Android' keyword",
                            Toast.LENGTH_SHORT
                        )
                        .show()
                }
            })

        )

        Row(modifier = Modifier.align(Alignment.CenterEnd)) {

            Box(modifier = Modifier
                .background(Color(0xffDAE3DD), shape = RoundedCornerShape(100))
                .size(40.dp)
                .clickable {
                    if (searchText.text.trim() == "Android") {
                        viewModel.prefsHelper.put("search_text", searchText.text.trim())
                        viewModel.apiSearchRepo(
                            searchKey = searchText.text.trim(),
                            viewModel.prefsHelper["sort", ""]
                        )
                    } else if (searchText.text
                            .trim()
                            .isEmpty()
                    ) {
                        Toast
                            .makeText(context, "Search bar is empty!", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        Toast
                            .makeText(
                                context,
                                "Search with 'Android' keyword",
                                Toast.LENGTH_SHORT
                            )
                            .show()
                    }

                }) {
                Icon(
                    Icons.Filled.Search,
                    contentDescription = "Search",
                    tint = Color.Black,
                    modifier = Modifier
                        .size(25.dp)
                        .align(
                            Alignment.Center
                        )
                )
            }

            Spacer(modifier = Modifier.width(10.dp))

            Box(modifier = Modifier
                .background(Color(0xffDAE3DD), shape = RoundedCornerShape(100))
                .size(40.dp)
                .clickable {
                    expanded = true

                }) {

                DropdownMenu(
                    modifier = Modifier.align(
                        Alignment.Center
                    ),
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    DropdownMenuItem(onClick = {
                        Log.i("search_data", viewModel.prefsHelper["search_text", ""])
                        if (searchText.text.trim().isNotEmpty()) {
                            expanded = false
                            getFilteredData(sortType = "date", dataSource = viewModel)
                        } else if (viewModel.prefsHelper["search_text", ""].isNotEmpty()) {
                            expanded = false
                            getFilteredData(sortType = "date", dataSource = viewModel)
                        } else {
                            Toast.makeText(
                                context,
                                "Search first before filtering!",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }) {
                        Text(
                            "Filter by Updated Date",
                            fontWeight = if (viewModel.prefsHelper["sort", ""] == "date") {
                                FontWeight.Bold
                            } else {
                                FontWeight.Normal
                            }
                        )
                    }
                    Divider()
                    DropdownMenuItem(onClick = {
                        if (searchText.text.trim().isNotEmpty()) {
                            expanded = false
                            getFilteredData(sortType = "star", dataSource = viewModel)
                        } else if (viewModel.prefsHelper["search_text", ""].isNotEmpty()) {
                            expanded = false
                            getFilteredData(sortType = "star", dataSource = viewModel)
                        } else {
                            Toast.makeText(
                                context,
                                "Search first before filtering!",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                    }) {
                        Text(
                            "Filter by Star Count",
                            fontWeight = if (viewModel.prefsHelper["sort", ""] == "star") {
                                FontWeight.Bold
                            } else {
                                FontWeight.Normal
                            }
                        )
                    }
                    Divider()
                    DropdownMenuItem(onClick = {
                        expanded = false
                        viewModel.apply {
                            clear()
                            viewModel.hashMap.clear()
                            prefsHelper.put("search_text", "")
                            prefsHelper.put("sort", "")
                            viewModelScope.launch {
                                database.withTransaction {
                                    database.githubDao().deleteAllRepo()
                                }
                            }
                        }

                    }) {
                        Text("Clear Search")
                    }
                }
                Icon(
                    Icons.Filled.FilterList,
                    contentDescription = "Search",
                    tint = Color.Black,
                    modifier = Modifier
                        .size(23.dp)
                        .align(
                            Alignment.Center
                        )
                )
            }

            Spacer(modifier = Modifier.width(10.dp))
        }

    }

}

fun getFilteredData(sortType: String, dataSource: MainViewModel) {
    dataSource.apply {
        prefsHelper.put("sort", sortType)
        apiSearchRepo(searchKey = prefsHelper["search_text", ""], sortBy = prefsHelper["sort", ""])
    }

}
