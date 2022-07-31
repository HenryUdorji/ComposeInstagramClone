package com.hashconcepts.composeinstagramclone.auth.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hashconcepts.composeinstagramclone.auth.data.dto.CreateUserDto
import com.hashconcepts.composeinstagramclone.auth.domain.AuthRepository
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
): ViewModel() {

    private val eventChannel = Channel<ResultEvents>()
    val eventChannelFlow = eventChannel.receiveAsFlow()


    fun onUserEvents(authScreenEvents: AuthScreenEvents) {
        when(authScreenEvents) {
            is AuthScreenEvents.OnLogin -> {

            }
            is AuthScreenEvents.OnRegister -> {

            }
        }
    }


    fun createUser(createUserDto: CreateUserDto) = viewModelScope.launch {
        try {
            authRepository.createUserWithEmailAndPassword(createUserDto.email, createUserDto.password)
            eventChannel.send(ResultEvents.OnSuccess("User created successfully."))
        } catch (e: Exception) {
            eventChannel.send(ResultEvents.OnError(e.localizedMessage ?: "Unable to Create User, try again."))
        }
    }
}