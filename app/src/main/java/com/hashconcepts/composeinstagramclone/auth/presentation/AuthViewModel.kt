package com.hashconcepts.composeinstagramclone.auth.presentation

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hashconcepts.composeinstagramclone.auth.domain.AuthRepository
import com.hashconcepts.composeinstagramclone.auth.domain.AuthValidator
import com.hashconcepts.composeinstagramclone.auth.domain.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

/**
 * @created 31/07/2022 - 2:43 PM
 * @project ComposeInstagramClone
 * @author  ifechukwu.udorji
 */
@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val eventChannel = Channel<ResultEvents>()
    val eventChannelFlow = eventChannel.receiveAsFlow()

    var userState by mutableStateOf(User())
        private set

    var isLoading by mutableStateOf(false)
        private set

    init {
        getUser()
    }


    fun onUserEvents(authScreenEvents: AuthScreenEvents) {
        when (authScreenEvents) {
            is AuthScreenEvents.OnLogin -> {
                val email = authScreenEvents.email
                val password = authScreenEvents.password

                val result = AuthValidator.validateSignInRequest(email, password)
                if (result.successful) {
                    signIn(email, password)
                } else {
                    viewModelScope.launch {
                        eventChannel.send(ResultEvents.OnError(result.error!!))
                    }
                }
            }
            is AuthScreenEvents.OnRegister -> {
                if (authScreenEvents.imageUri == null) {
                    viewModelScope.launch {
                        eventChannel.send(ResultEvents.OnError("You have not selected an image"))
                    }
                    return
                }

                val result = AuthValidator.validateCreateUserRequest(authScreenEvents.user)
                if (result.successful) {
                    createUser(authScreenEvents.imageUri, authScreenEvents.user)
                } else {
                    viewModelScope.launch {
                        eventChannel.send(ResultEvents.OnError(result.error!!))
                    }
                }
            }
            is AuthScreenEvents.OnForgotPassword -> {
                val email = authScreenEvents.email
                if (email.isBlank()) {
                    viewModelScope.launch {
                        eventChannel.send(ResultEvents.OnError("Email field should not be empty."))
                    }
                    return
                }

                forgotPassword(email)
            }
        }
    }

    private fun createUser(imageUri: Uri, user: User) = viewModelScope.launch {
        isLoading = true
        try {
            val usernameAvailability = authRepository.checkUsernameAvailability(user.username)
            if (!usernameAvailability) {
                isLoading = false
                eventChannel.send(ResultEvents.OnError("Username entered already exist, try another one."))
            } else {
                val firebaseUser = authRepository.createUserWithEmailAndPassword(
                    user.email,
                    user.password
                )
                firebaseUser?.let { fireUser ->
                    val imageUrl = authRepository.uploadProfileImage(imageUri)

                    val updatedUser = user.copy(
                        uid = fireUser.uid,
                        imageUrl = imageUrl,
                        password = "",
                    )
                    authRepository.saveUserProfile(updatedUser)
                }

                isLoading = false
                eventChannel.send(ResultEvents.OnSuccess("User created, kindly check your email to verify"))
            }
        } catch (e: Exception) {
            isLoading = false
            eventChannel.send(
                ResultEvents.OnError(
                    e.localizedMessage ?: "Unable to Create User, try again."
                )
            )
        }
    }

    private fun signIn(email: String, password: String) = viewModelScope.launch {
        isLoading = true
        try {
            val firebaseUser = authRepository.signInWithEmailAndPassword(email, password)
            isLoading = false

            firebaseUser?.let { user ->
                if (user.isEmailVerified) {
                    eventChannel.send(ResultEvents.OnSuccess("Login successful"))
                } else {
                    eventChannel.send(ResultEvents.OnError("User email is not verified. Kindly verify to login"))
                }
            }
        } catch (e: Exception) {
            isLoading = false
            eventChannel.send(ResultEvents.OnError(e.localizedMessage ?: "Unable to Login User, try again."))
        }
    }

    private fun forgotPassword(email: String) = viewModelScope.launch {
        isLoading = true
        try {
            val emailSent = authRepository.sendPasswordResetEmail(email)
            if (emailSent) {
                isLoading = false
                eventChannel.send(ResultEvents.OnError("Email sent successfully, check your mail to continue."))
            }
        } catch (e: Exception) {
            isLoading = false
            eventChannel.send(ResultEvents.OnError(e.localizedMessage ?: "Unable to send reset email, try again."))
        }
    }

    private fun getUser() = viewModelScope.launch {
        val user = authRepository.getUser()
        userState = userState.copy(email = user?.email ?: "", imageUrl = user?.imageUrl ?: "")
    }
}