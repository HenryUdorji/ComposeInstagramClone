package com.hashconcepts.composeinstagramclone.auth.domain

import com.google.common.truth.Truth.assertThat
import com.hashconcepts.composeinstagramclone.auth.domain.model.User
import org.junit.Test

/**
 * @created 31/07/2022 - 7:45 PM
 * @project ComposeInstagramClone
 * @author  ifechukwu.udorji
 */
class AuthValidatorTest {

    @Test
    fun validateCreateUserRequest_allFieldsEmpty_returnsFalse() {
        val user = User(
            email = "",
            fullName = "",
            username = "",
            password = ""
        )
        val result = AuthValidator.validateCreateUserRequest(user)
        assertThat(result.successful).isFalse()
    }

    @Test
    fun validateCreateUserRequest_oneFieldEmpty_returnsFalse() {
        val user = User(
            email = "henryudorji@gmail.com",
            fullName = "",
            username = "Henry",
            password = "123456"
        )
        val result = AuthValidator.validateCreateUserRequest(user)
        assertThat(result.successful).isFalse()
    }

    @Test
    fun validateCreateUserRequest_invalidEmail_returnsFalse() {
        val user = User(
            email = "henryudorji@gmail",
            fullName = "Henry Ifechukwu",
            username = "Henry",
            password = "123456"
        )
        val result = AuthValidator.validateCreateUserRequest(user)
        assertThat(result.successful).isFalse()
    }

    @Test
    fun validateCreateUserRequest_invalidPasswordLength_returnsFalse() {
        val user = User(
            email = "henryudorji@gmail.com",
            fullName = "Henry Ifechukwu",
            username = "Henry",
            password = "123"
        )
        val result = AuthValidator.validateCreateUserRequest(user)
        assertThat(result.successful).isFalse()
    }

    @Test
    fun validateCreateUserRequest_allFieldsValid_returnsTrue() {
        val user = User(
            email = "henryudorji@gmail.com",
            fullName = "Henry Ifechukwu",
            username = "Henry",
            password = "123456"
        )
        val result = AuthValidator.validateCreateUserRequest(user)
        assertThat(result.successful).isTrue()
    }
}