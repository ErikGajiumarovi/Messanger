package com.erikproject.messanger.presentation.viewmodel

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

open class CustomViewModel {
    val viewModelScope = CoroutineScope(SupervisorJob())
}