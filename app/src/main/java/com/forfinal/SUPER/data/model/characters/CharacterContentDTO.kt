package com.forfinal.SUPER.data.model.characters

import com.forfinal.SUPER.ui.characters.model.CharactersUIModel
import kotlinx.serialization.Serializable

@Serializable
data class CharacterContentDTO(
    val biography: CharacterBiography,
    val id: Int,
    val image: CharacterImage
) {
    fun toUIModel() = CharactersUIModel(
        id = this.id,
        name = this.biography.fullName,
        imageURL = this.image.url
    )
}
