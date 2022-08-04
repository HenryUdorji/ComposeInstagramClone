package com.hashconcepts.composeinstagramclone.auth.domain

import com.hashconcepts.composeinstagramclone.auth.data.dto.CreateUserDto
import com.hashconcepts.composeinstagramclone.auth.presentation.viewmodel.ValidationResult

/**
 * @created 31/07/2022 - 7:25 PM
 * @project ComposeInstagramClone
 * @author  ifechukwu.udorji
 */
object AuthValidator {

    fun validateCreateUserRequest(createUserDto: CreateUserDto): ValidationResult {
        val username = createUserDto.username
        val password = createUserDto.password
        val email = createUserDto.email
        val fullName = createUserDto.fullName

        if (username.isBlank() && password.isBlank() && email.isBlank() && fullName.isBlank()) {
            return ValidationResult(
                successful = false,
                error = "All fields are empty"
            )
        }
        if (email.isBlank()) {
            return ValidationResult(
                successful = false,
                error = "Email cannot be blank"
            )
        }
        if (email.isNotBlank()) {
            val EMAIL_REGEX = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})"
            val matches = EMAIL_REGEX.toRegex().matches(email)
            if (!matches) {
                return ValidationResult(
                    successful = false,
                    error = "Email is not valid"
                )
            }
        }
        if (fullName.isBlank()) {
            return ValidationResult(
                successful = false,
                error = "FullName cannot be blank"
            )
        }
        if (username.isBlank()) {
            return ValidationResult(
                successful = false,
                error = "Username cannot be blank"
            )
        }
        if (password.isBlank()) {
            return ValidationResult(
                successful = false,
                error = "Password cannot be blank"
            )
        }
        if (password.length < 6) {
            return ValidationResult(
                successful = false,
                error = "Password length should be at least 6 characters."
            )
        }
        return ValidationResult(successful = true,)
    }

    fun validateSignInRequest(email: String, password: String): ValidationResult {
        if (password.isBlank() && email.isBlank()) {
            return ValidationResult(
                successful = false,
                error = "Email and Password fields are empty"
            )
        }
        if (email.isBlank()) {
            return ValidationResult(
                successful = false,
                error = "Email cannot be blank"
            )
        }
        if (email.isNotBlank()) {
            val EMAIL_REGEX = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})"
            val matches = EMAIL_REGEX.toRegex().matches(email)
            if (!matches) {
                return ValidationResult(
                    successful = false,
                    error = "Email is not valid"
                )
            }
        }
        if (password.isBlank()) {
            return ValidationResult(
                successful = false,
                error = "Password cannot be blank"
            )
        }
        return ValidationResult(successful = true,)
    }
}