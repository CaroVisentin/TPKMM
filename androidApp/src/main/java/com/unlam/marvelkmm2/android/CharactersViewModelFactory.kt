package com.unlam.marvelkmm2.android

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.unlam.marvelkmm2.CharactersViewModel
import com.unlam.marvelkmm2.data.KtorCharactersRepository
import com.unlam.marvelkmm2.domain.CharactersService

class CharactersViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val apiClient = com.unlam.marvelkmm2.data.MarvelApiClient()
        val charactersApi = KtorCharactersRepository(apiClient)
        val charactersService = CharactersService(charactersApi)
        return CharactersViewModel(charactersService) as T
    }
}