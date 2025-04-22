package com.virent.gweather.core.data

import com.google.firebase.auth.FirebaseUser
import com.virent.gweather.core.network.AuthenticationRemoteDataSource
import javax.inject.Inject

class AuthenticationRepository @Inject constructor(
    private val authRemoteDataSource: AuthenticationRemoteDataSource
) {
    val currentUser: FirebaseUser? = authRemoteDataSource.currentUser

    suspend fun signIn(email: String, password: String) =
        authRemoteDataSource.signIn(email, password)

    suspend fun signUp(email: String, password: String) =
        authRemoteDataSource.signUp(email, password)

    fun signOut() = authRemoteDataSource.signOut()
}