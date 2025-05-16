package com.erikproject.messanger.presentation

import androidx.compose.runtime.mutableStateOf

class Navigator<T>(
    initialScreen: T
) {
    // Текущий экран как состояние
    var currentScreen = mutableStateOf(initialScreen)

    // Стек для хранения истории навигации
    private val backStack = mutableListOf<T>()

    init {
        // Добавляем начальный экран в историю
        backStack.add(initialScreen)
    }

    /**
     * Переход на новый экран
     */
    fun navigateTo(screen: T) {
        // Сохраняем текущий экран в историю
        if (currentScreen.value != screen) {
            backStack.add(screen)
            currentScreen.value = screen
        }
    }

    /**
     * Возврат на предыдущий экран
     * @return true если возврат был успешным, false если некуда возвращаться
     */
    fun navigateBack(): Boolean {
        // Если в стеке только один элемент, возвращаться некуда
        if (backStack.size <= 1) {
            return false
        }

        // Удаляем текущий экран из стека
        backStack.removeAt(backStack.lastIndex)

        // Устанавливаем предыдущий экран как текущий
        currentScreen.value = backStack.last()
        return true
    }

    /**
     * Проверка возможности возврата назад
     */
    fun canGoBack(): Boolean {
        return backStack.size > 1
    }

    /**
     * Получение всей истории навигации
     */
    fun getBackStack(): List<T> {
        return backStack.toList() // Возвращаем копию, чтобы нельзя было изменить оригинал
    }

    /**
     * Очистка истории навигации и установка нового корневого экрана
     */
    fun navigateToRoot(rootScreen: T) {
        backStack.clear()
        backStack.add(rootScreen)
        currentScreen.value = rootScreen
    }

    /**
     * Возврат к определенному экрану в истории
     * @return true если экран найден в истории и навигация выполнена
     */
    fun navigateBackTo(screen: T): Boolean {
        val index = backStack.indexOf(screen)
        if (index == -1) return false

        // Удаляем все экраны после найденного
        while (backStack.size > index + 1) {
            backStack.removeAt(backStack.lastIndex)
        }

        currentScreen.value = backStack.last()
        return true
    }
}