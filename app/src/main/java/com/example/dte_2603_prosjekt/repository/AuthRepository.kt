package com.example.dte_2603_prosjekt.repository

import com.example.dte_2603_prosjekt.domain.model.Response
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


class AuthRepository @Inject constructor(private val authenticator: FirebaseAuth) {
    suspend fun signInWithEmailPassword(email: String, password: String) = flow {
        try {
            emit(Response.Loading)
            authenticator.signInWithEmailAndPassword(email, password).await()
            emit(Response.Success(true))
        } catch (e: Exception) {
            emit(Response.Error(e.message ?: e.toString()))
        }
    }

    suspend fun signUpWithEmailPassword(email: String, password: String) = flow {
        try {
            emit(Response.Loading)
            authenticator.createUserWithEmailAndPassword(email, password).await()
            emit(Response.Success(true))
        } catch (e: Exception) {
            emit(Response.Error(e.message ?: e.toString()))
        }
    }

    fun signOut(): Unit {
        return authenticator.signOut()
    }

    fun getCurrentUser(): FirebaseUser? {
        return authenticator.currentUser
    }

    suspend fun sendResetPassword(email: String) = flow {
        try {
            emit(Response.Loading)
            authenticator.sendPasswordResetEmail(email).await()
            emit(Response.Success(true))
        } catch (e: Exception) {
            emit(Response.Error(e.message ?: e.toString()))
        }
    }

}
