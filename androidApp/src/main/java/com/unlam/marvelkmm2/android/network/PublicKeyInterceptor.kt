package com.unlam.marvelkmm2.android.network

import okhttp3.Interceptor
import okhttp3.Response
import javax.crypto.Cipher

class PublicKeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val url = request.url

        val newUrl = url.newBuilder()
            .addQueryParameter("apikey", Cipher.PUBLIC_KEY.toString())
            .build()

        return chain.proceed(
            request.newBuilder()
                .url(newUrl)
                .build()
        )
    }
}