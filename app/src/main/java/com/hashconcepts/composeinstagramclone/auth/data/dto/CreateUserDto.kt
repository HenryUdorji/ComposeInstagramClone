package com.hashconcepts.composeinstagramclone.auth.data.dto

import android.net.Uri

/**
 * @created 31/07/2022 - 3:02 PM
 * @project ComposeInstagramClone
 * @author  ifechukwu.udorji
 */
data class CreateUserDto(
    val username: String,
    val password: String,
    val email: String,
    val fullName: String,
    //val image: Uri,
)
