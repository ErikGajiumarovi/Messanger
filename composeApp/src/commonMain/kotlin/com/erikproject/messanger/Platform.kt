package com.erikproject.messanger

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform