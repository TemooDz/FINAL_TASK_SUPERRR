package com.forfinal.SUPER.data.login

import com.forfinal.SUPER.data.ApiClient
import com.forfinal.SUPER.data.model.auth.SignInResponse
import com.forfinal.SUPER.di.SharedPreferenceModule
import com.forfinal.SUPER.ui.login.UserCredentials
import com.forfinal.SUPER.ui.utils.CommonRequestResult
import com.forfinal.SUPER.ui.utils.json.JsonHelper

class LoginRepositoryImpl(
    private val apiClient: ApiClient,
    private val jsonHelper: JsonHelper,
    private val sharedPreferenceModule: SharedPreferenceModule
) : LoginRepository {
    override fun auth(userCredentials: UserCredentials) {
        when (val response = apiClient.auth(userCredentials)) {
            is CommonRequestResult.OnSuccess -> {
                val token = jsonHelper.decodeResult<SignInResponse>(response.result)
                sharedPreferenceModule.putToken(token.accessToken)
            }
            else -> throw UserNotFoundException()
        }
    }
}
