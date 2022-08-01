package com.crevado.fr.topandroidrepositories.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.SupervisedUserCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import androidx.navigation.NavController
import com.crevado.fr.topandroidrepositories.activity.MainViewModel
import com.crevado.fr.topandroidrepositories.ui.theme.MyCustomFont
import com.skydoves.landscapist.glide.GlideImage
import java.text.SimpleDateFormat

@Composable
fun DetailsPage(navController: NavController, viewModel: MainViewModel) {

    Column(modifier = Modifier
        .background(Color.White)
        .fillMaxSize()
        .padding(all = 20.dp)) {
        Box(modifier = Modifier
            .background(Color(0xffDAE3DD), shape = RoundedCornerShape(100))
            .size(50.dp)
            .align(Alignment.Start)
            .clickable {
                navController.popBackStack()

            }) {
            Icon(Icons.Filled.ArrowBack,
                contentDescription = "Search",
                tint = Color.Black,
                modifier = Modifier
                    .size(25.dp)
                    .align(
                        Alignment.Center
                    ))
        }

        Spacer(modifier = Modifier.height(30.dp))

        Column(modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.CenterHorizontally), horizontalAlignment = Alignment.CenterHorizontally) {
            GlideImage(imageModel = viewModel.detailData.avatar, loading = {
                Icon(Icons.Filled.SupervisedUserCircle,
                    contentDescription = "Avatar",
                    modifier = Modifier
                        .size(150.dp)
                        .clip(CircleShape))
            }, success = { imageState ->
                imageState.drawable?.toBitmap().let {
                    Image(bitmap = it!!.asImageBitmap(), contentDescription = "Loaded Image", modifier = Modifier
                        .size(150.dp)
                        .clip(CircleShape))
                }

            })

            Spacer(modifier = Modifier.height(50.dp))

            Column(
                Modifier
                    .fillMaxWidth()
                    .background(color = Color(0xffEAF0EC), shape = RoundedCornerShape(10.dp))
                    .padding(all = 15.dp))  {

                Text(
                    text = viewModel.detailData.name,
                    fontFamily = MyCustomFont,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    text = viewModel.detailData.description,
                    fontFamily = MyCustomFont,
                    fontWeight = FontWeight.Normal
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Last updated on: ${
                        formattedDateTime(viewModel.detailData.createdAt).split(
                            " "
                        )[0]
                    }," +
                            " ${formattedDateTime(viewModel.detailData.createdAt).split(" ")[1]}",
                    fontFamily = MyCustomFont,
                    fontWeight = FontWeight.Normal
                )
            }


        }

    }
}

fun formattedDateTime(createdAt: String): String {
    val parser =  SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
    val formatter = SimpleDateFormat("MM-dd-yy HH:ss")
    return formatter.format(parser.parse(createdAt)!!)
}

@Composable
fun seePreview() {
    Column(modifier = Modifier.background(Color.White)) {
        Box(modifier = Modifier
            .background(Color(0xffDAE3DD), shape = RoundedCornerShape(100))
            .size(40.dp)
            .padding(all = 40.dp)
            .align(Alignment.Start)
            .clickable {
                //  navController.popBackStack()

            }) {
            Icon(Icons.Filled.ArrowBack,
                contentDescription = "Search",
                tint = Color.Black,
                modifier = Modifier
                    .size(25.dp)
                    .align(
                        Alignment.Center
                    ))
        }

        Column(modifier = Modifier
            .padding(all = 20.dp)
            .fillMaxWidth()
            .align(Alignment.CenterHorizontally), horizontalAlignment = Alignment.CenterHorizontally) {
            GlideImage(imageModel = "", modifier = Modifier
                .size(100.dp)
                .clip(CircleShape))

            Spacer(modifier = Modifier.height(50.dp))

            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, top = 10.dp, end = 10.dp)
                    .background(color = Color(0xffEAF0EC), shape = RoundedCornerShape(10.dp)))  {

                Text(text = "Name", fontWeight = FontWeight.SemiBold)
                Spacer(modifier = Modifier.height(15.dp))
                Text(text = "des", fontWeight = FontWeight.Normal)
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = "time", fontWeight = FontWeight.Normal)
            }

        }

    }
}

@Preview
@Composable
fun ComposablePreview() {

    seePreview()

}