package com.hashconcepts.composeinstagramclone.auth.domain.model

import androidx.annotation.Keep

/**
 * @created 04/08/2022 - 10:04 AM
 * @project ComposeInstagramClone
 * @author  ifechukwu.udorji
 */
@Keep
data class User(
    val uid: String = "",
    val username: String = "",
    val password: String = "",
    val email: String = "",
    val fullName: String = "",
    val imageUrl: String = "",
    val createdDate: Long = System.currentTimeMillis(),
)
