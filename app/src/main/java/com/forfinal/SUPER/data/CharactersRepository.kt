package com.forfinal.SUPER.data

import com.forfinal.SUPER.data.model.characters.CharacterContentDTO

interface CharactersRepository {
    fun get(pageNumber: Int): List<CharacterContentDTO>
}
