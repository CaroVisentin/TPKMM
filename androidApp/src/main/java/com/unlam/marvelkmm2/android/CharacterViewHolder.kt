package com.unlam.marvelkmm2.android

import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.unlam.marvelkmm2.android.databinding.ListItemCharacterBinding
import com.unlam.marvelkmm2.domain.Character
class CharacterViewHolder(private val binding: ListItemCharacterBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(character: Character) {
        binding.name.text = character.name
        binding.description.text = character.description
        if (character.thumbnailUrl.isNotEmpty()) {
            Picasso.get()
                .load(character.thumbnailUrl)
                .into(binding.image)
        } else {
            binding.image.setImageURI(null)
        }
    }

}