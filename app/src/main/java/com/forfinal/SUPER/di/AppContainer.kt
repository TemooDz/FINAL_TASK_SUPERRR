package com.forfinal.SUPER.di

import android.content.Context
import com.forfinal.SUPER.data.ApiClient
import com.forfinal.SUPER.data.CharactersRepository
import com.forfinal.SUPER.data.CharactersRepositoryImpl
import com.forfinal.SUPER.data.login.LoginRepository
import com.forfinal.SUPER.data.login.LoginRepositoryImpl
import com.forfinal.SUPER.pool.ThreadPool
import com.forfinal.SUPER.ui.utils.json.JsonHelper

class AppContainer(context: Context) {
    private val networkClient = ApiClient
    private val jsonHelper = JsonHelper

    val charactersRepository: CharactersRepository = CharactersRepositoryImpl(
        service = networkClient,
        jsonHelper = jsonHelper
    )

    val loginRepository: LoginRepository = LoginRepositoryImpl(
        apiClient = networkClient,
        jsonHelper = jsonHelper,
        sharedPreferenceModule = SharedPreferenceModule(context)
    )

    val threadPool by lazy {
        val cores = Runtime.getRuntime().availableProcessors()
        ThreadPool(cores, cores * 2)
    }
}
