package com.eduardodev.kataloginlogout

class LoginPresenter {

    fun canLogIn(userName: String, password: String) =
        userName == "admin" && password == "admin"
}
