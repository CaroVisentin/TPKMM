package com.unlam.marvelkmm2.data

import com.unlam.marvelkmm2.util.PUBLIC_KEY
import com.unlam.marvelkmm2.domain.Character

class KtorCharactersRepository(
    private val apiClient: MarvelApiClient,
    private val cache: CharacterCacheRepository
) : CharactersRepository {


    override suspend fun getCharacters(timestamp: Long, md5: String): List<Character> {
        return try {
            val result = apiClient.fetchCharacters(timestamp, md5, PUBLIC_KEY)
            cache.saveCharacters(result)
            result
        } catch (e: Exception) {
            cache.getCachedCharacters()
        }
    }
}
