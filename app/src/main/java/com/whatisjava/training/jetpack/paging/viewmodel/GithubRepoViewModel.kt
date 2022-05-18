package com.whatisjava.training.jetpack.paging.viewmodel

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.whatisjava.training.jetpack.paging.model.Item
import com.whatisjava.training.jetpack.paging.repository.RepoRepository
import kotlinx.coroutines.flow.Flow

class GithubRepoViewModel: ViewModel() {

    fun getGithubServerData(): Flow<PagingData<Item>> {
        return RepoRepository.getPagingData()
    }
}