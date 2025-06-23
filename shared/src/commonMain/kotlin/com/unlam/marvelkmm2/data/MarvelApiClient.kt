package com.unlam.marvelkmm2.data

import com.unlam.marvelkmm2.domain.Character
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

class MarvelApiClient {

    private val client = HttpClient {
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }
    }

    suspend fun fetchCharacters(timestamp: Long, hash: String, publicKey: String): List<Character> {
        val response: CharactersResponse = client.get("https://gateway.marvel.com/v1/public/characters") {
            url {
                parameters.append("ts", timestamp.toString())
                parameters.append("apikey", publicKey)
                parameters.append("hash", hash)
            }
        }.body()
        return response.data.results.map {
            Character(
                id = it.id,
                name = it.name,
                description = it.description,
                thumbnailUrl = "${it.thumbnail.path}.${it.thumbnail.extension}"
            )
        }
    }
}

@Serializable
data class CharactersResponse(
    val data: CharacterData
)

@Serializable
data class CharacterData(
    val results: List<CharacterResult>
)

@Serializable
data class CharacterResult(
    val id: Long,
    val name: String,
    val description: String,
    val thumbnail: Thumbnail
)

@Serializable
data class Thumbnail(
    val path: String,
    val extension: String
)
