package com.unlam.marvelkmm2.data

import com.marvel.MarvelDatabase
import com.unlam.marvelkmm2.domain.Character

class CharacterCacheRepository(
    private val database: MarvelDatabase
) {
    private val queries = database.marvelDatabaseQueries

    fun saveCharacters(characters: List<Character>) {
        queries.transaction {
            queries.deleteAll()
            characters.forEach {
                queries.insertCharacter(
                    id = it.id.toLong(),
                    name = it.name,
                    description = it.description,
                    thumbnailUrl = it.thumbnailUrl
                )
            }
        }
    }

    fun getCachedCharacters(): List<Character> {
        return queries.getAllCharacters().executeAsList().map {
            Character(
                id = it.id,
                name = it.name,
                description = it.description ?: "",
                thumbnailUrl = it.thumbnailUrl ?: ""
                )
        }
    }
}
