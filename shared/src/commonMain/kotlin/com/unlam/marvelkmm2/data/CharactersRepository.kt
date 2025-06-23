package com.unlam.marvelkmm2.data

import com.unlam.marvelkmm2.domain.Character

interface CharactersRepository {
    suspend fun getCharacters(timestamp: Long, md5: String): List<Character>
}