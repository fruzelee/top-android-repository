package com.crevado.fr.topandroidrepositories.data.local_db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.crevado.fr.topandroidrepositories.data.local_db.GitHubDao
import com.crevado.fr.topandroidrepositories.data.local_db.GithubData

@Database(entities = [GithubData::class],version = 1)
abstract class GithubDatabase : RoomDatabase() {
    abstract fun githubDao() : GitHubDao
}