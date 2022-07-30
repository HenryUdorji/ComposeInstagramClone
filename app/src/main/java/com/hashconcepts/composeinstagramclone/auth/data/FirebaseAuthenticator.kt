package com.hashconcepts.composeinstagramclone.auth.data

import com.google.android.gms.tasks.Tasks.await
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.hashconcepts.composeinstagramclone.auth.domain.Authenticator

/**
 * @created 30/07/2022 - 3:36 AM
 * @project ComposeInstagramClone
 * @author  ifechukwu.udorji
 */
class FirebaseAuthenticator: Authenticator {
    override suspend fun createUserWithEmailAndPassword(
        email: String,
        password: String
    ): FirebaseUser? {
        Firebase.auth.createUserWithEmailAndPassword(email, password).await()
        return Firebase.auth.currentUser
    }

    override suspend fun signInWithEmailAndPassword(
        email: String,
        password: String
    ): FirebaseUser? {
        TODO("Not yet implemented")
    }

    override fun signOut(): FirebaseUser? {
        TODO("Not yet implemented")
    }

    override fun getUser(): FirebaseUser? {
        TODO("Not yet implemented")
    }

    override suspend fun sendPasswordResetEmail(email: String) {
        TODO("Not yet implemented")
    }

    override suspend fun verifyPasswordResetCode(code: String) {
        TODO("Not yet implemented")
    }
}