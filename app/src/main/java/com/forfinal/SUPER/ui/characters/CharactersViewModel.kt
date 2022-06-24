package com.forfinal.SUPER.ui.characters

import com.forfinal.SUPER.data.CharactersRepository
import com.forfinal.SUPER.pool.ThreadPool
import com.forfinal.SUPER.ui.characters.model.CharactersUIModel
import com.forfinal.SUPER.ui.library.hotdata.HotData
import com.forfinal.SUPER.ui.library.viewModel.ViewModel

class CharactersViewModel(
    private val charactersRepository: CharactersRepository,
    private val threadPool: ThreadPool
) : ViewModel() {

    private var pageNumber = 0

    val isLoading = HotData<Boolean>()

    init {
        isLoading.setValue(false)
        fetchNext()
    }

    val characters = HotData<List<CharactersUIModel>>()


    fun fetchNext() {
        threadPool.launch {
            isLoading.setValue(true)
            characters.setValue(charactersRepository.get(++pageNumber).map { it.toUIModel() })
            isLoading.setValue(false)
        }
    }

}
