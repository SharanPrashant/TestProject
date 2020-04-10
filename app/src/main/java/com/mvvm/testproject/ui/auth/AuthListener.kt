package com.mvvm.testproject.ui.auth

import androidx.lifecycle.LiveData
import com.mvvm.testproject.data.db.entities.User

interface AuthListener {
    fun onStarted()
    fun onSuccess(user: User)//loginRespone: LiveData<String>
    fun onFailure(message : String)
}