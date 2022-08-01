package com.hashconcepts.composeinstagramclone.auth.presentation

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FieldValue
import com.hashconcepts.composeinstagramclone.auth.data.dto.CreateUserDto
import com.hashconcepts.composeinstagramclone.auth.domain.AuthRepository
import com.hashconcepts.composeinstagramclone.auth.domain.AuthValidator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import timber.log.Timber
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

    var isLoading by mutableStateOf(false)
        private set


    fun onUserEvents(authScreenEvents: AuthScreenEvents) {
        when (authScreenEvents) {
            is AuthScreenEvents.OnLogin -> {

            }
            is AuthScreenEvents.OnRegister -> {
                if (authScreenEvents.imageUri == null) {
                    viewModelScope.launch {
                        eventChannel.send(ResultEvents.OnError("You have not selected an image"))
                    }
                    return
                }

                val result = AuthValidator.validateCreateUserRequest(authScreenEvents.createUserDto)
                if (result.successful) {
                    createUser(authScreenEvents.imageUri, authScreenEvents.createUserDto)
                } else {
                    viewModelScope.launch {
                        eventChannel.send(ResultEvents.OnError(result.error!!))
                    }
                }
            }
        }
    }


    private fun createUser(imageUri: Uri, createUserDto: CreateUserDto) = viewModelScope.launch {
        isLoading = true
        try {
            val firebaseUser = authRepository.createUserWithEmailAndPassword(
                createUserDto.email,
                createUserDto.password
            )
            firebaseUser?.let { user ->
                val imageUrl = authRepository.uploadProfileImage(imageUri)

                val updatedUser = createUserDto.copy(
                    uid = user.uid,
                    createdDate = FieldValue.serverTimestamp(),
                    imageUrl = imageUrl,
                    password = "",
                )
                authRepository.saveUserProfile(updatedUser)
            }

            isLoading = false
            eventChannel.send(ResultEvents.OnSuccess("User created, kindly check your email to verify"))
        } catch (e: Exception) {
            isLoading = false
            eventChannel.send(
                ResultEvents.OnError(
                    e.localizedMessage ?: "Unable to Create User, try again."
                )
            )
        }
    }
}