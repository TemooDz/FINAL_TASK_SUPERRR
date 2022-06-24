package com.forfinal.SUPER.ui.library.viewModel.factory

import com.forfinal.SUPER.data.CharactersRepository
import com.forfinal.SUPER.pool.ThreadPool
import com.forfinal.SUPER.ui.characters.CharactersViewModel
import com.forfinal.SUPER.ui.library.viewModel.ViewModel
import com.forfinal.SUPER.ui.library.viewModel.ViewModelStore

class CharactersViewModelFactory(
    private val charactersRepository: CharactersRepository,
    private val threadPool: ThreadPool
) : ViewModelStore.Factory {
    override fun <V : ViewModel> create(modelClass: Class<V>): V {
        return CharactersViewModel(charactersRepository, threadPool) as V
    }
}
