package com.unlam.marvelkmm2.database

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.marvel.MarvelDatabase
import com.unlam.marvelkmm2.AndroidPlatform
import com.unlam.marvelkmm2.Platform


actual class DatabaseDriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(MarvelDatabase.Schema, context,
            "marvel.db")
    }
}