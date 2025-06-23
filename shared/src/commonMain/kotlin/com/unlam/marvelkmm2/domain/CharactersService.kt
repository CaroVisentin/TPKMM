package com.unlam.marvelkmm2.domain

import com.soywiz.krypto.md5
import com.unlam.marvelkmm2.data.CharactersRepository
import com.unlam.marvelkmm2.util.PRIVATE_KEY
import com.unlam.marvelkmm2.util.PUBLIC_KEY
import kotlinx.datetime.Clock

class CharactersService(private val charactersRepository: CharactersRepository) {

    suspend fun getCharacters(): List<Character> {
        val timestamp = Clock.System.now().toEpochMilliseconds()
        val characters = charactersRepository.getCharacters(
            timestamp,
            md5(timestamp.toString() + PRIVATE_KEY + PUBLIC_KEY)
        )
        return sort(characters)
    }

    private fun md5(input: String): String {
        return input.encodeToByteArray().md5().hex
    }

    private fun sort(characters: List<Character>): List<Character> {
        return characters.sortedWith(CharacterComparator())
    }

    private class CharacterComparator : Comparator<Character> {
        override fun compare(c1: Character, c2: Character): Int {
            if (c1.description.isEmpty() && c2.description.isEmpty()) {
                return c2.id.compareTo(c1.id)
            }
            if (c1.description.isNotEmpty() && c2.description.isNotEmpty()) {
                return c1.id.compareTo(c2.id)
            }
            return if (c1.description.isNotEmpty()) -1 else 1
        }
    }
}