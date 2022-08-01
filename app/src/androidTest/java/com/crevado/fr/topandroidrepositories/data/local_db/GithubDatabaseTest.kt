package com.crevado.fr.topandroidrepositories.data.local_db

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import junit.framework.TestCase
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class GithubDatabaseTest : TestCase() {

    private lateinit var db: GithubDatabase
    private lateinit var dao: GitHubDao

    @Before
    public override fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context.applicationContext, GithubDatabase::class.java)
            .build()
        dao = db.githubDao()
    }

    @After
    fun closeDB() {
        db.close()
    }

    @Test
    fun insertAndGetData() = runBlocking {
        val data = GithubData().apply {
            id = 1
            name = "Lorem Name"
            fullName = "Lorem Full Name"
            avatar = "https://i.picsum.photos/id/658/200/200.jpg?hmac=f24wxXCkgtH72eZ6mY95KRxTyvEG-_3ysR9z-R0a1QM"
            description = "Lorem Description"
            createdAt = "2022-08-01T04:19:19Z"
            stars = "000123"
        }
        val repoList = arrayListOf(data)
        dao.insertRepos(repoList)

        val testData = dao.repoList().take(1).toList()[0]
        assertThat(repoList.size == testData.size).isTrue()
    }

}