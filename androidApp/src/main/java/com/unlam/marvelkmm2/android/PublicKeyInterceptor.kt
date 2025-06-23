package com.unlam.marvelkmm2

import okhttp3.Interceptor
import okhttp3.Response
import javax.crypto.Cipher.PUBLIC_KEY

class PublicKeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val url = request.url

        val newUrl = url.newBuilder()
            .addQueryParameter("apikey", PUBLIC_KEY.toString())
            .build()

        return chain.proceed(
            request.newBuilder()
                .url(newUrl)
                .build()
        )
    }
}