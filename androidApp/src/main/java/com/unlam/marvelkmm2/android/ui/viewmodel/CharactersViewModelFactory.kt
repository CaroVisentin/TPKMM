package com.unlam.marvelkmm2.android.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.unlam.marvelkmm2.CharactersViewModel
import com.unlam.marvelkmm2.data.KtorCharactersRepository
import com.unlam.marvelkmm2.domain.CharactersService

class CharactersViewModelFactory(
    private val repository: KtorCharactersRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val charactersService = CharactersService(repository)
        return CharactersViewModel(charactersService) as T
    }
}