package com.forfinal.SUPER.ui.login

import com.forfinal.SUPER.data.login.LoginRepository
import com.forfinal.SUPER.data.login.UserNotFoundException
import com.forfinal.SUPER.pool.ThreadPool
import com.forfinal.SUPER.ui.library.hotdata.HotData
import com.forfinal.SUPER.ui.library.viewModel.ViewModel

class LoginViewModel(
    private val loginRepository: LoginRepository,
    private val threadPool: ThreadPool
) : ViewModel() {

    val isAuthenticated = HotData<Boolean>()
    val isLoading = HotData(false)

    fun signIn(userCredentials: UserCredentials) {
        threadPool.launch {
            isLoading.setValue(true)
            try {
                loginRepository.auth(userCredentials)
                isAuthenticated.setValue(true)
            } catch (e: UserNotFoundException) {
                isAuthenticated.setValue(false)
            }
            isLoading.setValue(false)
        }
    }
}
