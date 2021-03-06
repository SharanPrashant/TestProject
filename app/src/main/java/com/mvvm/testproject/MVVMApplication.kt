package com.mvvm.testproject

import android.app.Application
import com.mvvm.testproject.data.db.AppDatabase
import com.mvvm.testproject.data.network.MyAPI

import com.mvvm.testproject.data.network.NetworkConnectionInterceptor
import com.mvvm.testproject.data.repositories.UserRepository
import com.mvvm.testproject.databinding.ActivityLoginBinding.bind
import com.mvvm.testproject.ui.auth.AuthViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class MVVMApplication : Application(), KodeinAware{
    override val kodein=  Kodein.lazy {
        import(androidXModule(this@MVVMApplication))

        bind() from singleton { NetworkConnectionInterceptor(instance()) }
        bind() from singleton { MyAPI(instance()) }
        bind() from singleton { AppDatabase(instance()) }
        bind() from singleton { UserRepository(instance(), instance()) }
        bind() from provider { AuthViewModelFactory(instance()) }
    }
}