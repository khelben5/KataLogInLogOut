package com.eduardodev.kataloginlogout

class LoginValidator(private val timeProvider: TimeProvider = TimeProvider()) {

    fun canLogIn(userName: String, password: String) =
        userName == "admin" && password == "admin"

    fun canLogout() = timeProvider.currentTimeInMs() % 2 == 0L
}
