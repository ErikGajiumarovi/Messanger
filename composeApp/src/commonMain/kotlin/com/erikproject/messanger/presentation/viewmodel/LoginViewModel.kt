package com.erikproject.messanger.presentation.viewmodel

import com.erikproject.messanger.data.repository.LoginRepository
import com.erikproject.messanger.presentation.AppScreen
import com.erikproject.messanger.presentation.CustomViewModel
import com.erikproject.messanger.presentation.Navigator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val repository: LoginRepository,
) : CustomViewModel() {
    // Поля для редактирования со стороны View
    private val _username = MutableStateFlow("")
    val username = _username.asStateFlow()

    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    private val _otp = MutableStateFlow("")
    val otp = _otp.asStateFlow()

    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()

    private val _phoneNumber = MutableStateFlow("")
    val phoneNumber = _phoneNumber.asStateFlow()

    //
    // Состояния для View
    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    fun updateUsername(value: String) {
        _username.value = value
    }

    fun updatePassword(value: String) {
        _password.value = value
    }

    fun updateOtp(value: String) {
        _otp.value = value
    }

    fun updateEmail(value: String) {
        _email.value = value
    }

    fun updatePhoneNumber(value: String) {
        if (value.matches(Regex("^\\+?\\d*$"))) {
            _phoneNumber.value = value
        }
    }

    fun login() {
        viewModelScope.launch {
            repository.loginUser(
                _username.value,
                _password.value
            )
        }
    }

    fun register() {
        viewModelScope.launch {
            repository.registerUser(
                _username.value,
                _email.value,
                _phoneNumber.value,
                _password.value
            )
        }
    }

    fun verify() {
        viewModelScope.launch {
            repository.verifyUser(
                _username.value,
                _otp.value
            )
        }
    }
}
