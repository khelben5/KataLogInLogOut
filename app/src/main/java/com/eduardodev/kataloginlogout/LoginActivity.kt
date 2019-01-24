package com.eduardodev.kataloginlogout

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity(), LoginView {

    private val presenter = LoginPresenter(this)

    override val userName: String
        get() = userNameView.text.toString()

    override val password: String
        get() = passwordView.text.toString()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setupButtonListeners()
    }

    private fun setupButtonListeners() {
        loginButton.setOnClickListener { presenter.logIn() }
        logoutButton.setOnClickListener { presenter.logOut() }
    }

    override fun hideLoginForm() {
        userNameView.setVisible(false)
        passwordView.setVisible(false)
        loginButton.setVisible(false)
    }

    override fun hideLogoutForm() {
        logoutButton.setVisible(false)
    }

    override fun showLoginForm() {
        userNameView.setVisible(true)
        passwordView.setVisible(true)
        loginButton.setVisible(true)
    }

    override fun showLogoutForm() {
        clearFields()
        hideKeyboard()
        logoutButton.setVisible(true)
    }

    override fun showError() {
        Toast.makeText(this, "ERROR", Toast.LENGTH_LONG).show()
    }

    private fun clearFields() {
        userNameView.text?.clear()
        passwordView.text?.clear()
    }

    private fun hideKeyboard() {
        val inputMethodService = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        val view = currentFocus ?: View(this)
        inputMethodService.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun View.setVisible(isVisible: Boolean) {
        visibility = if (isVisible) View.VISIBLE else View.GONE
    }
}
