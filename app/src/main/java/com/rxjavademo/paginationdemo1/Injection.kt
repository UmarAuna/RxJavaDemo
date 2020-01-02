package com.rxjavademo.paginationdemo1

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.rxjavademo.paginationdemo1.api.GithubService
import com.rxjavademo.paginationdemo1.data.GithubRepository
import com.rxjavademo.paginationdemo1.db.GithubLocalCache
import com.rxjavademo.paginationdemo1.db.RepoDatabase
import com.rxjavademo.paginationdemo1.ui.ViewModelFactory
import java.util.concurrent.Executors

/**
 * Class that handles object creation.
 * Like this, objects can be passed as parameters in the constructors and then replaced for
 * testing, where needed.
 */
object Injection {
    /**
     * Creates an instance of [GithubLocalCache] based on the database DAO.
     */
    private fun provideCache(context: Context): GithubLocalCache {
        val database = RepoDatabase.getInstance(context)
        return GithubLocalCache(database.reposDao(), Executors.newSingleThreadExecutor())
    }

    /**
     * Creates an instance of [GithubRepository] based on the [GithubService] and a
     * [GithubLocalCache]
     */
    private fun provideGithubRepository(context: Context): GithubRepository {
        return GithubRepository(GithubService.create(), provideCache(context))
    }

    /**
     * Provides the [ViewModelProvider.Factory] that is then used to get a reference to
     * [ViewModel] objects.
     */
    fun provideViewModelFactory(context: Context): ViewModelProvider.Factory {
        return ViewModelFactory(provideGithubRepository(context))
    }
}