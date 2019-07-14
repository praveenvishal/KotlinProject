package com.example.kotlinproject.global.db.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.kotlinproject.global.constant.DbConstant
import com.example.kotlinproject.global.db.dao.UserInfoDao
import com.example.kotlinproject.global.db.entity.UserInfoEntity

@Database(entities = arrayOf(UserInfoEntity::class), version = DbConstant.DB_VERSION)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userInfoDao(): UserInfoDao
}