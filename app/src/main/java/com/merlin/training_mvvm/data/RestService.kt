package com.merlin.training_mvvm.data

import android.annotation.SuppressLint
import com.merlin.training_mvvm.BuildConfig
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.Gson
import com.merlin.training_mvvm.data.webservices.IService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.security.cert.X509Certificate
import java.util.*
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager
import javax.security.cert.CertificateException


class RestService private constructor(private val url: String) {

    companion object {

        private const val CONNECTION_TIMEOUT_SEC = 120
        private const val WRITE_TIMEOUT_SEC = 120
        private const val READ_TIMEOUT_SEC = 120

        private var instance: RestService? = null

        fun getInstance(url: String = "www.google.com"): RestService {
            if (instance == null) {
                instance =
                    RestService(url)
            }
            return instance as RestService
        }
    }

    private var service: IService

    private var okHttpClient = getUnsafeOkHttpClient()

    private fun getOkHttpClient(): OkHttpClient {
        var builder = OkHttpClient.Builder()
        builder.interceptors().addAll(getInterceptorList())
        if (BuildConfig.DEBUG) {
            builder = builder.addNetworkInterceptor(StethoInterceptor())
        }

        builder = builder.connectTimeout(CONNECTION_TIMEOUT_SEC.toLong(), TimeUnit.SECONDS)
        builder = builder.writeTimeout(WRITE_TIMEOUT_SEC.toLong(), TimeUnit.SECONDS)
        builder = builder.readTimeout(READ_TIMEOUT_SEC.toLong(), TimeUnit.SECONDS)

        return builder.build()
    }

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .client(okHttpClient.build())
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        service = retrofit.create(IService::class.java)
    }

    private fun getInterceptorList(): ArrayList<Interceptor> {
        //val requestInterceptor = RequestInterceptor()
        //val autoTokenRefreshInterceptor = AutoRefreshTokenInterceptor()
        val interceptorList = ArrayList<Interceptor>()
        //interceptorList.add(requestInterceptor)
        //interceptorList.add(autoTokenRefreshInterceptor)

        val loggingInterceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        } else {
            loggingInterceptor.level = HttpLoggingInterceptor.Level.NONE
        }
        interceptorList.add(StethoInterceptor())
        interceptorList.add(loggingInterceptor)
        return interceptorList
    }

    fun getIService(): IService {
        return getInstance(url).service
    }

    private fun getUnsafeOkHttpClient(): OkHttpClient.Builder {
        try {
            // Create a trust manager that does not validate certificate chains
            val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
                override fun getAcceptedIssuers(): Array<X509Certificate> {
                    return arrayOf()
                }

                @SuppressLint("TrustAllX509TrustManager")
                @Throws(CertificateException::class)
                override fun checkClientTrusted(
                    chain: Array<X509Certificate>,
                    authType: String
                ) {
                }

                @SuppressLint("TrustAllX509TrustManager")
                @Throws(CertificateException::class)
                override fun checkServerTrusted(
                    chain: Array<X509Certificate>,
                    authType: String
                ) {
                }
            })

            // Install the all-trusting trust manager
            val sslContext = SSLContext.getInstance("SSL")
            sslContext.init(null, trustAllCerts, java.security.SecureRandom())

            // Create an ssl socket factory with our all-trusting manager
            val sslSocketFactory = sslContext.socketFactory

            var builder = OkHttpClient.Builder()
            builder.sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
            builder.hostnameVerifier { hostname, session -> true }
            builder.interceptors().addAll(getInterceptorList())
            if (BuildConfig.DEBUG) {
                builder = builder.addNetworkInterceptor(StethoInterceptor())
            }


            builder = builder.connectTimeout(CONNECTION_TIMEOUT_SEC.toLong(), TimeUnit.SECONDS)
            builder = builder.writeTimeout(WRITE_TIMEOUT_SEC.toLong(), TimeUnit.SECONDS)
            builder = builder.readTimeout(READ_TIMEOUT_SEC.toLong(), TimeUnit.SECONDS)
            return builder
        } catch (e: Exception) {
            throw RuntimeException(e)
        }

    }
}
