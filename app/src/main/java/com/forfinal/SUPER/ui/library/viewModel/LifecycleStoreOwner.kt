package com.forfinal.SUPER.ui.library.viewModel

import androidx.lifecycle.LifecycleOwner

interface LifecycleStoreOwner : LifecycleOwner {
    fun getClassId(): String
    fun isRecreating(): Boolean
}
