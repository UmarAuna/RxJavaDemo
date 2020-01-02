package com.rxjavademo.paginationdemo1.data

import android.util.Log
import androidx.paging.LivePagedListBuilder
import com.rxjavademo.paginationdemo1.api.GithubService
import com.rxjavademo.paginationdemo1.db.GithubLocalCache
import com.rxjavademo.paginationdemo1.model.RepoSearchResult

/**
 * Repository class that works with local and remote data sources.
 */
class GithubRepository(
        private val service: GithubService,
        private val cache: GithubLocalCache
) {

    /**
     * Search repositories whose names match the query.
     */
    fun search(query: String): RepoSearchResult {
        Log.d("GithubRepository", "New query: $query")

        // Get data source factory from the local cache
        val dataSourceFactory = cache.reposByName(query)

        // every new query creates a new BoundaryCallback
        // The BoundaryCallback will observe when the user reaches to the edges of
        // the list and update the database with extra data
        val boundaryCallback = RepoBoundaryCallback(query, service, cache)
        val networkErrors = boundaryCallback.networkErrors

        // Get the paged list
        val data = LivePagedListBuilder(dataSourceFactory, DATABASE_PAGE_SIZE)
                .setBoundaryCallback(boundaryCallback)
                .build()

        // Get the network errors exposed by the boundary callback
        return RepoSearchResult(data, networkErrors)
    }

    companion object {
        private const val DATABASE_PAGE_SIZE = 20
    }
}