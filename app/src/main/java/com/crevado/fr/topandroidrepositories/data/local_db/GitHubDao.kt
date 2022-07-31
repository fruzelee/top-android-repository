package com.crevado.fr.topandroidrepositories.data.local_db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface GitHubDao {

    @Query("SELECT * FROM github_data")
    fun repoList() : Flow<List<GithubData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRepos(data: List<GithubData>)

    @Query("DELETE FROM github_data")
    suspend fun deleteAllRepo()
}