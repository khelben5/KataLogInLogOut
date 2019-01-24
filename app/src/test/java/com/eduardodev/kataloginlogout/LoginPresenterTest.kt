package com.eduardodev.kataloginlogout

import org.junit.Assert.assertEquals
import org.junit.Test

class LoginPresenterTest {

    private data class TestInput(val userName: String, val password: String)
    private data class TestCase(val input: TestInput, val expectedOutput: Boolean)

    private val testCases = listOf(
        TestCase(TestInput("not admin", "admin"), false),
        TestCase(TestInput("admin", "not admin"), false),
        TestCase(TestInput("not admin", "not admin"), false),
        TestCase(TestInput("admin", "admin"), true)
    )

    @Test
    fun `should return expected value`() {
        val presenter = LoginPresenter()

        testCases.forEach {
            with(it.input) {
                assertEquals(it.expectedOutput, presenter.canLogIn(userName, password))
            }
        }
    }
}
