package com.erikproject.messanger

import com.erikproject.messanger.data.local.database.AppDatabase
import com.erikproject.messanger.data.remote.NetworkClient
import com.erikproject.messanger.data.repository.ChatMembersRepositoryImpl
import com.erikproject.messanger.data.repository.ChatsRepositoryImpl
import com.erikproject.messanger.data.repository.LoginRepository
import com.erikproject.messanger.data.repository.MessagesRepositoryImpl
import com.erikproject.messanger.domian.repository.ChatMembersRepository
import com.erikproject.messanger.domian.repository.ChatsRepository
import com.erikproject.messanger.domian.repository.MessagesRepository
import com.erikproject.messanger.domian.usecase.GetChatById
import com.erikproject.messanger.domian.usecase.GetChatMembers
import com.erikproject.messanger.domian.usecase.GetChatMembersByChatId
import com.erikproject.messanger.domian.usecase.GetChats
import com.erikproject.messanger.domian.usecase.GetMessages
import com.erikproject.messanger.domian.usecase.GetMessagesByChatId
import com.erikproject.messanger.domian.usecase.SendMessage
import com.erikproject.messanger.expect_actual.TokenStorage
import com.erikproject.messanger.expect_actual.getDriver
import com.erikproject.messanger.expect_actual.getPathToDBs
import com.erikproject.messanger.presentation.viewmodel.ChatViewModel
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

    // Repositories
    single<LoginRepository> {
        LoginRepository(
            networkClient = get(),
            storage = get()
        )
    }
    single<MessagesRepository> { MessagesRepositoryImpl() }
    single<ChatMembersRepository> { ChatMembersRepositoryImpl() }
    single<ChatsRepository> { ChatsRepositoryImpl() }

    // UseCases
    single<GetChatById> { GetChatById(get()) }
    single<GetChatMembers> { GetChatMembers(get()) }
    single<GetChatMembersByChatId> { GetChatMembersByChatId(get()) }
    single<GetChats> { GetChats(get()) }
    single<GetMessages> { GetMessages(get()) }
    single<GetMessagesByChatId> { GetMessagesByChatId(get()) }
    single<SendMessage> { SendMessage(get(), get()) }

    // Main ViewModels
    factory<LoginViewModel> { LoginViewModel(get()) }
    factory<HomeViewModel> { HomeViewModel(get()) }
    factory<ChatViewModel> { (chatId: Long) ->
        ChatViewModel(
            chatId,
            get(),
            get(),
            get(),
            get()
        )
    }

    // Home ViewModels
    factory<ChatsViewModel> { ChatsViewModel(get(), get(), get()) }
    factory<ContactsViewModel> { ContactsViewModel() }
    factory<ProfileViewModel> { ProfileViewModel() }
    factory<SettingsViewModel> { SettingsViewModel() }
}

fun initKoin() {
    startKoin {
        modules(appModule)
    }
}