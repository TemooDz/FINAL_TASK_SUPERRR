package com.forfinal.SUPER.ui.characters

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.forfinal.SUPER.R
import com.forfinal.SUPER.databinding.FragmentCharactersBinding
import com.forfinal.SUPER.ui.characters.model.CharactersUIModel
import com.forfinal.SUPER.ui.library.viewModel.factory.CharactersViewModelFactory
import com.forfinal.SUPER.ui.library.viewModel.ViewModelStore
import com.forfinal.SUPER.ui.utils.AdapterScrollListener
import com.forfinal.SUPER.ui.utils.BaseFragment
import com.forfinal.SUPER.ui.utils.view.showErrorDialog

class CharactersFragment :
    BaseFragment<CharactersViewModel>(R.layout.fragment_characters) {

    private val adapter = CharactersRecyclerAdapter()
    override lateinit var viewModel: CharactersViewModel

    private val items: MutableList<CharactersUIModel> = mutableListOf()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelStore.get(
            this,
            CharactersViewModel::class.java,
            CharactersViewModelFactory(appContainer.charactersRepository, appContainer.threadPool)
        )
        FragmentCharactersBinding.bind(view).onBind()
    }

    private fun FragmentCharactersBinding.onBind() {
        val layoutManager = LinearLayoutManager(context)
        charactersRecycler.layoutManager = layoutManager
        charactersRecycler.adapter = adapter
        viewModel.characters.observe(viewLifecycleOwner, {
            if (it.isEmpty())
                showErrorDialog(message = R.string.characters_not_found)
            items.addAll(it)
            adapter.submitList(items.map { item -> item.copy() })
        })

        viewModel.isLoading.observe(viewLifecycleOwner, {
            loader.visibility = if (it) View.VISIBLE else View.GONE
        })

        charactersRecycler.addOnScrollListener(AdapterScrollListener(layoutManager) {
            viewModel.fetchNext()
        })

    }

}

