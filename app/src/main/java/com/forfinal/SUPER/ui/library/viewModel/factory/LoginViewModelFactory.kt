package com.forfinal.SUPER.ui.library.viewModel.factory

import com.forfinal.SUPER.data.login.LoginRepository
import com.forfinal.SUPER.pool.ThreadPool
import com.forfinal.SUPER.ui.library.viewModel.ViewModel
import com.forfinal.SUPER.ui.library.viewModel.ViewModelStore
import com.forfinal.SUPER.ui.login.LoginViewModel

class LoginViewModelFactory(
    private val loginRepository: LoginRepository,
    private val threadPool: ThreadPool
) : ViewModelStore.Factory {
    override fun <V : ViewModel> create(modelClass: Class<V>): V {
        return LoginViewModel(loginRepository, threadPool) as V
    }
}
