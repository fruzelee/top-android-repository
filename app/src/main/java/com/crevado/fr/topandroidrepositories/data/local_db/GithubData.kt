package com.crevado.fr.topandroidrepositories.data.local_db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "github_data")
class GithubData{
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    lateinit var name: String
    lateinit var fullName: String
    lateinit var avatar: String
    lateinit var description: String
    lateinit var createdAt: String
    lateinit var stars: String
}

