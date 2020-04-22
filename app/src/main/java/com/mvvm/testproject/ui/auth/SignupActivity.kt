package com.mvvm.testproject.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mvvm.testproject.R
import com.mvvm.testproject.data.db.entities.User
import com.mvvm.testproject.databinding.ActivitySignupBinding
import com.mvvm.testproject.ui.home.HomeActivity
import com.mvvm.testproject.util.hide
import com.mvvm.testproject.util.show
import com.mvvm.testproject.util.snackBar
import kotlinx.android.synthetic.main.activity_login.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class SignupActivity : AppCompatActivity(),AuthListener,KodeinAware {
    override val kodein by  kodein()

    private val factory : AuthViewModelFactory by instance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var binding : ActivitySignupBinding = DataBindingUtil.setContentView(this,R.layout.activity_signup)
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
    }
    override fun onStarted() {
        progress_bar.show()
    }

    override fun onSuccess(user: User) {//loginRespone: LiveData<String>
        progress_bar.hide()
        }

    override fun onFailure(message: String) {
        progress_bar.hide()
        root_layout.snackBar(message)
    }
}
