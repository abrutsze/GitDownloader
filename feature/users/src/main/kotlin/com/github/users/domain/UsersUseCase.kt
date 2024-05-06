package com.github.users.domain


import androidx.paging.PagingData
import com.github.users.data.UsersRepository
import com.github.users.model.UiUsers
import com.github.dispatchers.api.DispatchersProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import org.koin.core.annotation.Factory

interface UsersUseCase {
    operator fun invoke(query: String): Flow<PagingData<UiUsers>>
}

@Factory
internal class UsersUseCaseImpl(
    private val usersRepository: UsersRepository,
    private val dispatcher: DispatchersProvider
) : UsersUseCase {

    override operator fun invoke(query: String): Flow<PagingData<UiUsers>> {
        return usersRepository.searchUsers(query).flow.flowOn(dispatcher.io)
    }
}
