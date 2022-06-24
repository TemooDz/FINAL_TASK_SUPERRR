package com.forfinal.SUPER.data

import com.forfinal.SUPER.data.model.characters.CharacterContentDTO
import com.forfinal.SUPER.data.model.characters.CharactersResponseDTO
import com.forfinal.SUPER.ui.utils.CommonRequestResult
import com.forfinal.SUPER.ui.utils.json.JsonHelper

class CharactersRepositoryImpl(
    private val service: ApiClient,
    private val jsonHelper: JsonHelper
) : CharactersRepository {
    override fun get(pageNumber: Int): List<CharacterContentDTO> {
        return when (val response = service.getCharacters(pageNumber)) {
            is CommonRequestResult.OnSuccess -> {
                jsonHelper.decodeResult<CharactersResponseDTO>(response.result).content
            }
            else -> emptyList()
        }
    }
}
