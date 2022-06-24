package com.forfinal.SUPER.ui.characters.model

import com.bumptech.glide.Glide
import com.forfinal.SUPER.databinding.CharacterItemBinding

data class CharactersUIModel(
    val id: Int,
    val name: String,
    val imageURL: String,
) {
    infix fun bindTo(binding: CharacterItemBinding) {
        binding.characterName.text = name

        Glide.with(binding.root.context)
            .load(imageURL)
            .centerCrop()
            .into(binding.characterImageView)
    }
}
