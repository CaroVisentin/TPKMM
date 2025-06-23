package com.unlam.marvelkmm2.database

import com.marvel.MarvelDatabase
import com.unlam.marvelkmm2.database.DatabaseDriverFactory

class MarvelDBRepository(databaseDriverFactory: DatabaseDriverFactory) {

    private val database = MarvelDatabase(databaseDriverFactory.createDriver())
    private val dbQuery = database.marvelDatabaseQueries

}