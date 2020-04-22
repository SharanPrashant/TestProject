package com.mvvm.testproject.ui.auth

import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModel
import com.mvvm.testproject.data.repositories.UserRepository
import com.mvvm.testproject.util.ApiException
import com.mvvm.testproject.util.Coroutines
import com.mvvm.testproject.util.NoInternetException

class AuthViewModel(
    private val repository: UserRepository
) : ViewModel() {

    var name: String?= null
    var email: String? =  null
    var password: String? = null
    var authListener : AuthListener? = null
    var passwordConfirm: String?= null

    fun getLoggedInUser() = repository.getUser()


    fun onLoginButtonClick ( view : View) {
        authListener?.onStarted()
        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            authListener?.onFailure("Invalid email or password")
            return
        }

        Coroutines.main {
            try {
                val authResponse = repository.userLogin(email!!, password!!)
                authResponse.user?.let {
                    authListener?.onSuccess(it)
                    repository.saveUser(it)
                    return@main
                }
                authListener?.onFailure(authResponse.message!!)
            } catch (e: ApiException) {
                authListener?.onFailure(e.message!!)
            } catch (e: NoInternetException) {
                authListener?.onFailure(e.message!!)
            }
        }
    }

        fun onLogin(view: View) {
            Intent(view.context, LoginActivity::class.java).also {
                view.context.startActivity(it)
            }
        }
        fun onSignup(view: View){
            Intent(view.context,SignupActivity::class.java).also {
            view.context.startActivity(it)
            }
        }
        fun onSignUpButtonClick ( view : View) {
            authListener?.onStarted()

            if (name.isNullOrEmpty()) {
                authListener?.onFailure("Name is required")
                return
            }
            if (email.isNullOrEmpty()) {
                authListener?.onFailure("Email is required")
                return
            }
            if (password.isNullOrEmpty()) {
                authListener?.onFailure("Please enter password")
                return
            }
            if (password != passwordConfirm) {
                authListener?.onFailure("Password did not match")
                return
            }


            Coroutines.main {
                try {
                    val authResponse = repository.userSignup(name!!, email!!, password!!)
                    authResponse.user?.let {
                        authListener?.onSuccess(it)
                        repository.saveUser(it)
                        return@main
                    }
                    authListener?.onFailure(authResponse.message!!)
                } catch (e: ApiException) {
                    authListener?.onFailure(e.message!!)
                } catch (e: NoInternetException) {
                    authListener?.onFailure(e.message!!)
                }
            }
        }
            /*
            val loginRespone = UserRepository().userLogin(email!!, password!!)
            if (loginRespone.isSuccessful){
                authListener?.onSuccess(loginRespone.body()?.user!!)
            }else{
                authListener?.onFailure("Error code: ${loginRespone.code()}")
            }*/

       /* val loginRespone = UserRepository().userLogin(email!!, password!!)
        authListener?.onSuccess(loginRespone)*/
}
