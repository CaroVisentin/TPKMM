package com.unlam.marvelkmm2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.unlam.marvelkmm2.android.CharacterViewHolder
import com.unlam.marvelkmm2.android.databinding.ListItemCharacterBinding
import com.unlam.marvelkmm2.domain.Character as MarvelCharacter

class CharactersAdapter : RecyclerView.Adapter<CharacterViewHolder>() {

    private val charactersList = mutableListOf<MarvelCharacter>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(ListItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(getItemAt(position))
    }

    override fun getItemCount() = charactersList.size

    fun submitList(characters: List<MarvelCharacter>) {
        with(charactersList) {
            clear()
            addAll(characters)
        }
        notifyDataSetChanged()
    }

    private fun getItemAt(position: Int) = charactersList[position]
}