package com.erikproject.messanger.viewmodel

import com.erikproject.messanger.model.LoginModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class LoginViewModel(
    private val loginModel: LoginModel
) {
    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState: StateFlow<LoginState> get() = _loginState

    fun login(
        userName: String,
        password: String
    ) {
        viewModelScope.launch {
            _loginState.value = LoginState.Loading
            loginModel.login(userName,password)
        }
    }
}

sealed class LoginState {
    object Idle : LoginState()
    object Loading : LoginState()
    object Success : LoginState()
    data class Error(val message: String) : LoginState()
}