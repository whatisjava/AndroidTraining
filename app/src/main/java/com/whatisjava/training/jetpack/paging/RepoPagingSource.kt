package com.whatisjava.training.jetpack.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.whatisjava.training.jetpack.paging.api.GithubApi
import com.whatisjava.training.jetpack.paging.model.Item

class RepoPagingSource(private val githubApi: GithubApi) : PagingSource<Int, Item>() {

    override fun getRefreshKey(state: PagingState<Int, Item>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Item> {
        return try {
            val key = params.key ?: 1
            val loadSize = params.loadSize
            val githubServerData = githubApi.getGithubServerData(key, loadSize)
            val items = githubServerData.items
            val prevKey = if (key > 1) key - 1 else null
            val nextKey = if (items.isNotEmpty()) key + 1 else null
            LoadResult.Page(items, prevKey, nextKey)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}