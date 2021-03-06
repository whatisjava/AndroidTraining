package com.whatisjava.training.jetpack.paging.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.whatisjava.training.jetpack.paging.RepoPagingSource
import com.whatisjava.training.jetpack.paging.api.GithubApi
import com.whatisjava.training.jetpack.paging.model.Item
import kotlinx.coroutines.flow.Flow

object RepoRepository {

    private const val PAGE_SIZE = 10

    private val githubApi = GithubApi.create()

    fun getPagingData(): Flow<PagingData<Item>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                prefetchDistance = 3,
                initialLoadSize = PAGE_SIZE,

            ),
            pagingSourceFactory = { RepoPagingSource(githubApi) }
        ).flow
    }

}