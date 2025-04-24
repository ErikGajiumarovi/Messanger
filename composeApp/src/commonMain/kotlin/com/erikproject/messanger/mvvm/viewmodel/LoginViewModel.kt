package com.erikproject.messanger.mvvm.viewmodel

import com.erikproject.messanger.mvvm.model.LoginModel
import com.erikproject.messanger.viewmodel.base.CommonViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class LoginViewModel(
    private val loginModel: LoginModel
) : CommonViewModel() {
    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState: StateFlow<LoginState> get() = _loginState

    fun login(
        userName: String,
        password: String
    ) {
        viewModelScope.launch {
            _loginState.value = LoginState.Loading
            loginModel.login(userName,password)
            _loginState.value = LoginState.Idle
        }
    }
}

sealed class LoginState {
    object Idle : LoginState()
    object Loading : LoginState()
    object Success : LoginState()
    data class Error(val message: String) : LoginState()
}