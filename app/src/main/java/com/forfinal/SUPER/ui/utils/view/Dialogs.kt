package com.forfinal.SUPER.ui.utils.view

import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.forfinal.SUPER.R


fun Fragment.showErrorDialog(@StringRes message: Int = R.string.error) =
    MaterialAlertDialogBuilder(this.requireContext())
        .setTitle(R.string.app_name)
        .setMessage(message)
        .show()
        ?: error("Context can't be null")