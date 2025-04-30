package com.erikproject.messanger

import com.erikproject.messanger.data.local.database.AppDatabase
import com.erikproject.messanger.data.remote.NetworkClient
import com.erikproject.messanger.data.repository.LoginRepository
import com.erikproject.messanger.data.repository.UserRepository
import com.erikproject.messanger.expect_actual.TokenStorage
import com.erikproject.messanger.expect_actual.getDriver
import com.erikproject.messanger.expect_actual.getPathToDBs
import com.erikproject.messanger.presentation.viewmodel.LoginViewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

private val appModule = module {
    single<AppDatabase> { AppDatabase(getDriver(getPathToDBs().resolve("messenger.db"))) }
    single<NetworkClient> { NetworkClient(get()) }
    single<TokenStorage> { TokenStorage() }

    single<LoginRepository> {
        LoginRepository(
            networkClient = get(),
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