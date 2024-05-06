package com.github.repository.data

import com.github.repository.model.UiRepo
import kotlinx.coroutines.flow.Flow

interface RepoRepository {
    fun getRepos(name: String): Flow<List<UiRepo>>
    fun saveRepo(branch:String, repo: UiRepo): Flow<Unit>
}
