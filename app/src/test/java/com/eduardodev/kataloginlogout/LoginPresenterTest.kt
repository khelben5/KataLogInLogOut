package com.eduardodev.kataloginlogout

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.then
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

private const val ANY_USER_NAME = "any user name"
private const val ANY_PASSWORD = "any password"

@RunWith(MockitoJUnitRunner.Silent::class)
class LoginPresenterTest {

    @Mock
    private lateinit var view: LoginView

    @Mock
    private lateinit var validator: LoginValidator

    @Test
    fun `should call validator when logging in`() {
        val validator = givenAValidator()
        val view = givenAView(ANY_USER_NAME, ANY_PASSWORD)
        val presenter = givenAPresenter(view, validator)

        presenter.logIn()

        then(validator).should().canLogIn(ANY_USER_NAME, ANY_PASSWORD)
    }

    @Test
    fun `should call show logout form if can log in`() {
        val validator = givenAValidator(canLogIn = true)
        val view = givenAView(ANY_USER_NAME, ANY_PASSWORD)
        val presenter = givenAPresenter(view, validator)

        presenter.logIn()

        then(view).should().showLogoutForm()
    }

    @Test
    fun `should call hide login form if can log in`() {
        val validator = givenAValidator(canLogIn = true)
        val view = givenAView(ANY_USER_NAME, ANY_PASSWORD)
        val presenter = givenAPresenter(view, validator)

        presenter.logIn()

        then(view).should().hideLoginForm()
    }

    @Test
    fun `should call show error if cannot log in`() {
        val validator = givenAValidator(canLogIn = false)
        val view = givenAView(ANY_USER_NAME, ANY_PASSWORD)
        val presenter = givenAPresenter(view, validator)

        presenter.logIn()

        then(view).should().showError()
    }

    @Test
    fun `should call show login form if can log out`() {
        val validator = givenAValidator(canLogOut = true)
        val view = givenAView(ANY_USER_NAME, ANY_PASSWORD)
        val presenter = givenAPresenter(view, validator)

        presenter.logOut()

        then(view).should().showLoginForm()
    }

    @Test
    fun `should call hide logout form if can log out`() {
        val validator = givenAValidator(canLogOut = true)
        val view = givenAView(ANY_USER_NAME, ANY_PASSWORD)
        val presenter = givenAPresenter(view, validator)

        presenter.logOut()

        then(view).should().hideLogoutForm()
    }

    @Test
    fun `should call show error form if cannot log out`() {
        val validator = givenAValidator(canLogOut = false)
        val view = givenAView(ANY_USER_NAME, ANY_PASSWORD)
        val presenter = givenAPresenter(view, validator)

        presenter.logOut()

        then(view).should().showError()
    }

    private fun givenAPresenter(view: LoginView, validator: LoginValidator) =
        LoginPresenter(view, validator)

    private fun givenAView(userName: String, password: String): LoginView {
        doReturn(userName).whenever(view).userName
        doReturn(password).whenever(view).password
        return view
    }

    private fun givenAValidator(
        canLogIn: Boolean = true,
        canLogOut: Boolean = true
    ): LoginValidator {
        doReturn(canLogIn).whenever(validator).canLogIn(any(), any())
        doReturn(canLogOut).whenever(validator).canLogout()
        return validator
    }
}
