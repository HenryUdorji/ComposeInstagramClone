package com.hashconcepts.composeinstagramclone.auth.presentation

import android.net.Uri
import com.hashconcepts.composeinstagramclone.auth.data.dto.CreateUserDto

/**
 * @created 31/07/2022 - 2:44 PM
 * @project ComposeInstagramClone
 * @author  ifechukwu.udorji
 */
data class AuthScreenState(
    val isLoading: Boolean = false
)

sealed class AuthScreenEvents {
    data class OnLogin(val email: String, val password: String) : AuthScreenEvents()
    data class OnRegister(val imageUri: Uri?, val createUserDto: CreateUserDto) : AuthScreenEvents()
    data class OnForgotPassword(val email: String): AuthScreenEvents()
}

sealed class ResultEvents {
    data class OnError(val message: String): ResultEvents()
    data class OnSuccess(val message: String?): ResultEvents()
}

data class ValidationResult(
    val successful: Boolean,
    val error: String? = null
)