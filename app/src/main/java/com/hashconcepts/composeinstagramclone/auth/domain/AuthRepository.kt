package com.hashconcepts.composeinstagramclone.auth.domain

import android.net.Uri
import com.google.firebase.auth.FirebaseUser
import com.hashconcepts.composeinstagramclone.auth.data.dto.CreateUserDto

/**
 * @created 30/07/2022 - 3:25 AM
 * @project ComposeInstagramClone
 * @author  ifechukwu.udorji
 */
interface AuthRepository {

    suspend fun createUserWithEmailAndPassword(email:String , password:String) : FirebaseUser?

    suspend fun signInWithEmailAndPassword(email: String , password: String): FirebaseUser?

    fun signOut() : FirebaseUser?

    fun getUser() : FirebaseUser?

    suspend fun sendPasswordResetEmail(email :String): Boolean

    suspend fun verifyPasswordResetCode(code: String)

    suspend fun saveUserProfile(createUserDto: CreateUserDto): Boolean

    suspend fun uploadUserProfile(imageUri: Uri): String

}