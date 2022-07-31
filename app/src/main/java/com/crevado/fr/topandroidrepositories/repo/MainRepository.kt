package com.crevado.fr.topandroidrepositories.repo

import com.crevado.fr.topandroidrepositories.data.network.ApiServices
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import retrofit2.Response
import javax.inject.Inject


class MainRepository @Inject constructor(
    private val apiService: ApiServices,
) {

    companion object {
        const val REPO_PATH = "repositories"
    }

    suspend fun apiRepositories(
        hashMap: HashMap<String, String>,
    ): Response<ResponseBody> {
        return withContext(Dispatchers.IO) {
            apiService.getRequest(REPO_PATH, hashMap = hashMap)
        }
    }

}