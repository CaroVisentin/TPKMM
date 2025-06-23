package com.unlam.marvelkmm2.presentation

import com.unlam.marvelkmm2.domain.Character

sealed class ScreenState {

    object Loading : ScreenState()

    class ShowCharacters(val list: List<Character>) : ScreenState()
}