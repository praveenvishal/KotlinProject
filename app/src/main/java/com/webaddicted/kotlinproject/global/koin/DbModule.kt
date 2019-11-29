package com.webaddicted.kotlinproject.global.koin

import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.webaddicted.kotlinproject.apiUtils.ApiConstant
import com.webaddicted.kotlinproject.apiUtils.ApiServices
import com.webaddicted.kotlinproject.apiUtils.ReflectionUtil
import com.webaddicted.kotlinproject.global.common.AppApplication
import com.webaddicted.kotlinproject.global.common.Lg
import com.webaddicted.kotlinproject.global.constant.DbConstant
import com.webaddicted.kotlinproject.global.db.database.AppDatabase
import com.webaddicted.kotlinproject.global.db.entity.UserInfoEntity
import com.webaddicted.kotlinproject.global.db.migration.DbMigrationHelper
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by Deepak Sharma on 01/07/19.
 */
val dbModule = module(override = true) {

    single {
//        val migration5To6 = object : Migration(5, 6) {
//            override fun migrate(database: SupportSQLiteDatabase) {
//                Lg.d("MIGRATION", "MIGRATION CALLED 5--6")
//                val cursor = database.query("SELECT * FROM User")
//                if (cursor.count > 0) {
//                    get<DbMigrationHelper>().migrateUserDataToPref(cursor)
//                }
//                database.execSQL("DROP TABLE user_info")
////                    database.execSQL("DROP TABLE Test_table")
////                    database.execSQL("DROP TABLE my_test")
//            }
//        }
//        val migration4To5 = object : Migration(4, 5) {
//            override fun migrate(database: SupportSQLiteDatabase) {
//                Lg.d("MIGRATION", "MIGRATION CALLED 4--5")
//            }
//        }
        Room.databaseBuilder(
            (androidApplication() as AppApplication),
            AppDatabase::class.java,
            DbConstant.DB_NAME
        ).allowMainThreadQueries().build()
        //.addMigrations(migration4To5, migration5To6).build()
    }

    single { (get() as AppDatabase).userInfoDao() }
}