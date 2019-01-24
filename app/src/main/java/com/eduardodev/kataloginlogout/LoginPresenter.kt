package com.eduardodev.kataloginlogout

import org.jetbrains.anko.doAsync

class LoginPresenter(
    private val view: LoginView,
    private val validator: LoginValidator = LoginValidator()
) {

    fun logIn() {
//        doAsync {
//            Thread.sleep(3000)
            if (validator.canLogIn(view.userName, view.password)) {
                view.showLogoutForm()
                view.hideLoginForm()
            } else {
                view.showError()
            }
//        }
    }

    fun logOut() {
//        doAsync {
//            Thread.sleep(3000)
            if (validator.canLogout()) {
                view.showLoginForm()
                view.hideLogoutForm()
            } else {
                view.showError()
            }
//        }
    }
}
