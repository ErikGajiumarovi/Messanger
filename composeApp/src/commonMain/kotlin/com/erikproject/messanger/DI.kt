package com.erikproject.messanger

import com.erikproject.messanger.data.local.database.AppDatabase
import com.erikproject.messanger.data.remote.NetworkClient
import com.erikproject.messanger.data.repository.UserRepository
import com.erikproject.messanger.expect_actual.TokenStorage
import com.erikproject.messanger.presentation.viewmodel.LoginViewModel
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.dsl.module

private val appModule = module {
    single<AppDatabase> { AppDatabase.create() }
    single<NetworkClient> { NetworkClient(get()) }
    single<TokenStorage> { TokenStorage() }

    single<UserRepository> {
        UserRepository(
            networkClient = get(),
            database = get(),
            storage = get()
        )
    }

    factory<LoginViewModel> { LoginViewModel(get()) }
}

fun initKoin() {
    startKoin {
        modules(appModule)
    }
}