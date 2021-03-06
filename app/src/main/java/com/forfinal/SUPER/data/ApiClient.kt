package com.forfinal.SUPER.data

import com.forfinal.SUPER.BuildConfig
import com.forfinal.SUPER.ui.login.UserCredentials
import com.forfinal.SUPER.ui.utils.CommonRequestResult
import okhttp3.*
import java.lang.Exception
import java.util.concurrent.TimeUnit

object ApiClient {

    private const val PAGE_NUMBER = 10

    private fun httpClient(): OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .addInterceptor(HttpLoggingInterceptor())
        .build()

    fun auth(userCredentials: UserCredentials): CommonRequestResult {
        val formData = FormBody.Builder().add("username", userCredentials.userName)
            .add("password", userCredentials.password)
        try {
            val response: Response = httpClient().newCall(
                Request.Builder().url("${BuildConfig.BASE_URL}auth/login")
                    .post(formData.build()).build()
            ).execute()
            if (response.isSuccessful)
                return CommonRequestResult.OnSuccess(response.body?.string().toString())
        } catch (_: Exception) {
            return CommonRequestResult.OnError
        }
        return CommonRequestResult.OnError
    }

    fun getCharacters(pageNumber: Int): CommonRequestResult {
        try {
            val response = httpClient().newCall(
                Request.Builder()
                    .url("${BuildConfig.BASE_URL}comics/list-characters?offset=${pageNumber * PAGE_NUMBER}&pageSize=$PAGE_NUMBER")
                    .get().build()
            ).execute()
            if (response.isSuccessful)
                return CommonRequestResult.OnSuccess(response.body?.string().toString())
        } catch (e: Exception) {
            return CommonRequestResult.OnError
        }
        return CommonRequestResult.OnError
    }

}
