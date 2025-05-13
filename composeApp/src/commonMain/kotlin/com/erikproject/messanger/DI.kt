package com.erikproject.messanger

import com.erikproject.messanger.data.local.database.AppDatabase
import com.erikproject.messanger.data.remote.NetworkClient
import com.erikproject.messanger.data.repository.LoginRepository
import com.erikproject.messanger.expect_actual.TokenStorage
import com.erikproject.messanger.expect_actual.getDriver
import com.erikproject.messanger.expect_actual.getPathToDBs
import com.erikproject.messanger.presentation.viewmodel.HomeViewModel
import com.erikproject.messanger.presentation.viewmodel.LoginViewModel
import com.erikproject.messanger.presentation.viewmodel.home_components.ChatsViewModel
import com.erikproject.messanger.presentation.viewmodel.home_components.ContactsViewModel
import com.erikproject.messanger.presentation.viewmodel.home_components.ProfileViewModel
import com.erikproject.messanger.presentation.viewmodel.home_components.SettingsViewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

private val appModule = module {
    single<AppDatabase> { AppDatabase(getDriver(getPathToDBs().resolve("messenger.db"))) }
    single<NetworkClient> { NetworkClient(get()) }
    single<TokenStorage> { TokenStorage() }
    single<Navigator> { Navigator() }

    single<LoginRepository> {
        LoginRepository(
            networkClient = get(),
            storage = get()
        )
    }

    // Main ViewModels
    factory<LoginViewModel> { LoginViewModel(get(), get()) }
    factory<HomeViewModel> { HomeViewModel(get()) }

    // Home ViewModels
    factory<ChatsViewModel> { ChatsViewModel(get()) }
    factory<ContactsViewModel> { ContactsViewModel() }
    factory<ProfileViewModel> { ProfileViewModel() }
    factory<SettingsViewModel> { SettingsViewModel() }
}

fun initKoin() {
    startKoin {
        modules(appModule)
    }
}