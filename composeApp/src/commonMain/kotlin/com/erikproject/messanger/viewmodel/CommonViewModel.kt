package com.erikproject.messanger.viewmodel.base

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel

/**
 * Базовый класс для всех ViewModel в KMP-приложении.
 * Обеспечивает общую функциональность управления корутинами для всех платформ.
 */
open class CommonViewModel {
    // CoroutineScope для этой ViewModel
    // SupervisorJob позволяет нам избежать отмены всех дочерних корутин при сбое одной из них
    val viewModelScope = CoroutineScope(SupervisorJob())

    /**
     * Вызывается при уничтожении ViewModel для очистки ресурсов
     * и отмены всех запущенных корутин.
     */
    fun clear() {
        viewModelScope.cancel()
    }
}