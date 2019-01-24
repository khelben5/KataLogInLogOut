package com.eduardodev.kataloginlogout

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {

    private val presenter = LoginPresenter(TimeProvider())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setupButtonListeners()
    }

    private fun setupButtonListeners() {
        loginButton.setOnClickListener { checkAndLogin() }
        logoutButton.setOnClickListener { checkAndLogout() }
    }

    private fun checkAndLogin() {
        val userNameText = userName.text.toString()
        val passwordText = password.text.toString()

        if (presenter.canLogIn(userNameText, passwordText)) {
            clearFields()
            hideKeyboard()
            showLogout()
        } else {
            showError()
        }
    }

    private fun checkAndLogout() {
        if (presenter.canLogout()) {
            showLogin()
        }
    }

    private fun clearFields() {
        userName.text?.clear()
        password.text?.clear()
    }

    private fun hideKeyboard() {
        val inputMethodService = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        val view = currentFocus ?: View(this)
        inputMethodService.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun showLogout() {
        userName.setVisible(false)
        password.setVisible(false)
        loginButton.setVisible(false)
        logoutButton.setVisible(true)
    }

    private fun showLogin() {
        userName.setVisible(true)
        password.setVisible(true)
        loginButton.setVisible(true)
        logoutButton.setVisible(false)
    }

    private fun View.setVisible(isVisible: Boolean) {
        visibility = if (isVisible) View.VISIBLE else View.GONE
    }

    private fun showError() {
        Toast.makeText(this, "ERROR", Toast.LENGTH_LONG).show()
    }
}
