package com.forfinal.SUPER.ui.utils

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.forfinal.SUPER.App
import com.forfinal.SUPER.di.AppContainer
import com.forfinal.SUPER.ui.library.viewModel.LifecycleStoreOwner
import com.forfinal.SUPER.ui.library.viewModel.ViewModel

abstract class BaseFragment<T : ViewModel>(
    @LayoutRes layoutRes: Int,
) : Fragment(layoutRes),
    LifecycleStoreOwner {

    lateinit var appContainer: AppContainer

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        appContainer = (requireActivity().application as App).appContainer
    }

    protected abstract val viewModel: T

    override fun getClassId(): String = viewModel.javaClass.simpleName
    override fun isRecreating() = requireActivity().isChangingConfigurations
}
