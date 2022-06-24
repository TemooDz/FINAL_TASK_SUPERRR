package com.forfinal.SUPER.ui.library.viewModel.factory

import com.forfinal.SUPER.ui.library.viewModel.ViewModel
import com.forfinal.SUPER.ui.library.viewModel.ViewModelStore

class ViewModelProviderFactory : ViewModelStore.Factory {
    override fun <V : ViewModel> create(modelClass: Class<V>): V {
        return modelClass.newInstance()
    }
}
