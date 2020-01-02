package com.rxjavademo.paginationdemo1.model

import androidx.lifecycle.LiveData
import androidx.paging.PagedList

/**
 * RepoSearchResult from a search, which contains LiveData<List<Repo>> holding query data,
 * and a LiveData<String> of network error state.
 */
class RepoSearchResult (
        val data: LiveData<PagedList<Repo>>,
        val networkErrors: LiveData<String>
)