package com.eduardodev.kataloginlogout

class LoginPresenter(private val timeProvider: TimeProvider) {

    fun canLogIn(userName: String, password: String) =
        userName == "admin" && password == "admin"

    fun canLogout() = timeProvider.currentTimeInMs() % 2 == 0L
}
