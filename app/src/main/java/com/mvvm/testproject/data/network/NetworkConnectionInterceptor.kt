package com.mvvm.testproject.data.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.os.Build
import androidx.annotation.RequiresApi
import com.mvvm.testproject.util.NoInternetException
import okhttp3.Interceptor
import okhttp3.Response


class NetworkConnectionInterceptor(
    context: Context
) : Interceptor {

    private val applicationContext = context.applicationContext

    @RequiresApi(Build.VERSION_CODES.M)
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isInternetAvailable())
            throw NoInternetException("Make sure you have an active data connection")
        return chain.proceed(chain.request())
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun isInternetAvailable() : Boolean {
        var result = false
        var connectivityManager =
            applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        connectivityManager.activeNetwork.also {
           return it!= null
        }
    }
}