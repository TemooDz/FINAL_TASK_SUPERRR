package com.forfinal.SUPER.data.login

import com.forfinal.SUPER.ui.login.UserCredentials

interface LoginRepository {
    fun auth(userCredentials: UserCredentials)
}
