package com.eduardodev.kataloginlogout

interface LoginView {

    val userName: String
    val password: String

    fun hideLoginForm()
    fun hideLogoutForm()
    fun showLoginForm()
    fun showLogoutForm()
    fun showError()
}
