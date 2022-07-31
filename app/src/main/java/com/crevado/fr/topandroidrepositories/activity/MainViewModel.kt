package com.crevado.fr.topandroidrepositories.activity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.withTransaction
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.crevado.fr.topandroidrepositories.data.local_db.GithubData
import com.crevado.fr.topandroidrepositories.data.local_db.GithubDatabase
import com.crevado.fr.topandroidrepositories.data.model.GithubSearchData
import com.crevado.fr.topandroidrepositories.data.preference.PrefsHelper
import com.crevado.fr.topandroidrepositories.repo.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    var prefsHelper: PrefsHelper,
    private var mainRepository: MainRepository,
    var database: GithubDatabase,
):  ViewModel() {

    var hashMap : HashMap<String, String> =  HashMap()
    var data = mutableListOf<GithubData>()
    var showLoader = MutableStateFlow(false)
    var detailData =  GithubData()

    fun apiSearchRepo(searchKey: String, sortBy: String) =
        viewModelScope.launch {
            when(sortBy) {
                "date" -> {
                    hashMap["sort"] = "updated"
                }
                "star" -> {
                    hashMap["sort"] = "stars"
                }
            }

            hashMap["q"] = searchKey
            hashMap["per_page"] = "50"
            hashMap["page"] = "1"

            try {
                clear()
                showLoader.value = true
                mainRepository.apiRepositories(hashMap).let {
                    val type = object : TypeToken<GithubSearchData>() {}.type
                    val result = Gson().fromJson<GithubSearchData>(
                            it.body()?.toString(), type
                        )

                    when(it.code()) {
                        200 -> {
                            result.items?.forEachIndexed { index, item ->
                                val repo = GithubData()
                                repo.name = item?.name.toString()
                                repo.fullName = item?.fullName.toString()
                                repo.description = item?.description?: "No details found"
                                repo.avatar = item?.owner?.avatarUrl.toString()
                                repo.createdAt = item?.updatedAt.toString()
                                repo.stars = item?.stargazersCount.toString()
                                data.add(repo)
                            }

                            database.withTransaction {
                                database.githubDao().deleteAllRepo()
                                database.githubDao().insertRepos(data)
                            }
                        }
                    }

                 }
            } catch (exception: Exception) {

            }
            finally {
                showLoader.value = false
            }
        }

     fun clear() {
        data.clear()
    }
}