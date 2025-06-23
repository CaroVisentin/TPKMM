package com.unlam.marvelkmm2.data

import com.unlam.marvelkmm2.util.PUBLIC_KEY
import com.unlam.marvelkmm2.data.CharactersRepository
import com.unlam.marvelkmm2.data.MarvelApiClient
import com.unlam.marvelkmm2.domain.Character

class KtorCharactersRepository(
    private val apiClient: MarvelApiClient
) : CharactersRepository {

//    val database = MarvelDatabase(DatabaseDriverFactory(context).createDriver())
//    private val dbQuery = database.marvelQueries

    override suspend fun getCharacters(timestamp: Long, md5: String): List<Character> {
        return apiClient.fetchCharacters(timestamp, md5, PUBLIC_KEY)
    }
}
