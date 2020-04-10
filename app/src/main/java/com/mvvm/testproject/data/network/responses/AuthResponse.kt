package com.mvvm.testproject.data.network.responses

import com.mvvm.testproject.data.db.entities.User
import io.reactivex.internal.operators.maybe.MaybeDoAfterSuccess

data class AuthResponse (
    val isSuccessful: Boolean?,
    val message: String?,
    val user: User?
)