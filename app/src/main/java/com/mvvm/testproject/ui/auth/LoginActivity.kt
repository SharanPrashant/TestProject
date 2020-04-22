package com.mvvm.testproject.ui.auth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mvvm.testproject.R
import com.mvvm.testproject.data.db.entities.User
import com.mvvm.testproject.databinding.ActivityLoginBinding
import com.mvvm.testproject.ui.home.HomeActivity
import com.mvvm.testproject.util.hide
import com.mvvm.testproject.util.show
import com.mvvm.testproject.util.snackBar
import kotlinx.android.synthetic.main.activity_login.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class LoginActivity : AppCompatActivity() , AuthListener, KodeinAware{

    override val kodein by  kodein()

    private val factory : AuthViewModelFactory by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


       /* var networkConnectionInterceptor = NetworkConnectionInterceptor(this)
        var api = MyAPI(networkConnectionInterceptor)
        var db = AppDatabase(this)
        var repository = UserRepository(api,db)
        var factory = AuthViewModelFactory(repository)*/

        var binding : ActivityLoginBinding = DataBindingUtil.setContentView(this,R.layout.activity_login)
        var viewModel  = ViewModelProvider(this,factory).get(AuthViewModel ::class.java)

        binding.viewmodel = viewModel
        viewModel.authListener = this

        viewModel.getLoggedInUser().observe(this, Observer { user ->
            if(user != null){
                Intent(this, HomeActivity::class.java).also {
                    it.flags =  Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }

            }
        })

       // AppDatabase(this) no need of instance
    }

    override fun onStarted() {
        progress_bar.show()
    }

    override fun onSuccess(user: User) {//loginRespone: LiveData<String>
        progress_bar.hide()
       // root_layout.snackBar("${user.name} is logged in")
        //toast("${user.name} is logged in")
/*
        loginRespone.observe(this, Observer {
            progress_bar.hide()
                toast(it)
                })*/

    }

    override fun onFailure(message: String) {
        progress_bar.hide()
        root_layout.snackBar(message)
    }
}
