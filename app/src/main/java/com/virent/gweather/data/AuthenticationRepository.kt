package com.virent.gweather.data

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class AuthenticationRepository @Inject constructor(
    private val authRemoteDataSource: AuthenticationRemoteDataSource
) {
    val currentUser: FirebaseUser? = authRemoteDataSource.currentUser

    suspend fun signIn(email: String, password: String) {
        authRemoteDataSource.signIn(email, password)
    }

    suspend fun signUp(email: String, password: String) {
        authRemoteDataSource.signUp(email, password)
    }

    fun signOut() {
        authRemoteDataSource.signOut()
    }
}