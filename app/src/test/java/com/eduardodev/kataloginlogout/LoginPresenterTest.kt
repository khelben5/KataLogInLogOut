package com.eduardodev.kataloginlogout

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LoginPresenterTest {

    private data class LoginTestInput(val userName: String, val password: String)
    private data class LoginTestCase(val input: LoginTestInput, val expectedOutput: Boolean)

    @Mock
    private lateinit var timeProvider: TimeProvider

    private val loginTestCases = listOf(
        LoginTestCase(LoginTestInput("not admin", "admin"), false),
        LoginTestCase(LoginTestInput("admin", "not admin"), false),
        LoginTestCase(LoginTestInput("not admin", "not admin"), false),
        LoginTestCase(LoginTestInput("admin", "admin"), true)
    )

    @Test
    fun `should login correctly`() {
        val presenter = LoginPresenter(timeProvider)

        loginTestCases.forEach {
            with(it.input) {
                assertEquals(it.expectedOutput, presenter.canLogIn(userName, password))
            }
        }
    }

    @Test
    fun `should return true when logout with even ms`() {
        givenEvenMs()
        val presenter = LoginPresenter(timeProvider)

        val result = presenter.canLogout()

        assertTrue(result)
    }

    @Test
    fun `should return false when logout with odd ms`() {
        givenOddMs()
        val presenter = LoginPresenter(timeProvider)

        val result = presenter.canLogout()

        assertFalse(result)
    }

    private fun givenEvenMs(): TimeProvider {
        doReturn(2L).whenever(timeProvider).currentTimeInMs()
        return timeProvider
    }

    private fun givenOddMs(): TimeProvider {
        doReturn(3L).whenever(timeProvider).currentTimeInMs()
        return timeProvider
    }
}
