package com.example.kotlinproject.networkCall

import com.example.kotlinproject.global.sharedPref.PreferenceMgr
import com.example.kotlinproject.global.sharedPref.PreferenceUtils
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.prodege.shopathome.model.networkCall.ApiConstant
import com.prodege.shopathome.model.networkCall.ApiServices
import com.prodege.shopathome.model.networkCall.ReflectionUtil
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val appModule = module {

    /* PROVIDE GSON SINGLETON */
    single<Gson> {
        val builder = GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        builder.setLenient().create()
    }

    /* PROVIDE RETROFIT SINGLETON */
    single {
        var loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        var httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(loggingInterceptor)
        httpClient.connectTimeout(ApiConstant.API_TIME_OUT, TimeUnit.MILLISECONDS)
        httpClient.addInterceptor { chain ->
            val request = chain.request().newBuilder().build()
            chain.proceed(request)
        }
        var okHttpClient = httpClient.build()

        Retrofit.Builder()
            .baseUrl( ApiConstant.BASE_URL)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(get() as Gson))
            .build()
    }

    /*Provide API Service Singleton */
    single {
        (get<Retrofit>()).create<ApiServices>(ApiServices::class.java)
    }


//    single {
//        PreferenceHelper(
//            (androidApplication() as ShopAtHomeApplication).getSharedPreferences(
//                PreferenceConstants.PREFERENCE_NAME,
//                Context.MODE_PRIVATE
//            )
//        )
//    }
    single { PreferenceUtils() }
    single { PreferenceMgr() }

//    single {
//        ValidationHelper()
//    }
//
//    single {
//        Validator(get())
//    }

    single { ReflectionUtil(get()) }
}