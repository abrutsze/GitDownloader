package com.github.mvi

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

abstract class MviBaseViewModel<S : MviState, A : MviAction, I : MviIntent>(reducer: Reducer<A, S>) : ViewModel() {

    var viewState by mutableStateOf(initState())

    private val actions = MutableSharedFlow<A>()

    private val _navEvents =  Channel<NavigationCommand>()
    val navEvents = _navEvents.receiveAsFlow()

    init {
        actions.onEach { action ->
            viewState = reducer.reduce(action, viewState)
        }.launchIn(viewModelScope)
    }

    abstract fun initState(): S

    abstract fun handleIntent(intent: I)

    fun onIntent(intent: I) {
        handleIntent(intent)
    }

    fun onAction(action: A) {
        viewModelScope.launch {
            actions.emit(action)
        }
    }
    fun onNavigation(route: NavigationCommand) {
        viewModelScope.launch {
            _navEvents.send(route)
        }
    }

}
