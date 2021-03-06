package com.forfinal.SUPER.ui.login

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.forfinal.SUPER.R
import com.forfinal.SUPER.databinding.FragmentLoginBinding
import com.forfinal.SUPER.ui.characters.CharactersFragment
import com.forfinal.SUPER.ui.library.viewModel.ViewModelStore
import com.forfinal.SUPER.ui.library.viewModel.factory.LoginViewModelFactory
import com.forfinal.SUPER.ui.utils.BaseFragment

class LoginFragment : BaseFragment<LoginViewModel>(R.layout.fragment_login) {

    override lateinit var viewModel: LoginViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelStore.get(
            this,
            LoginViewModel::class.java,
            LoginViewModelFactory(appContainer.loginRepository, appContainer.threadPool)
        )
        FragmentLoginBinding.bind(view).onViewBind()
    }

    private fun FragmentLoginBinding.onViewBind() {
        val userName = userNameEditText.text
        val password = userPasswordEditText.text

        viewModel.isLoading.observe(viewLifecycleOwner, {
            submit.isClickable = !it
            loader.visibility = if (it) View.VISIBLE else View.GONE
        })

        viewModel.isAuthenticated.observe(viewLifecycleOwner, {
            if (it) {
                requireActivity().supportFragmentManager.commit { replace<CharactersFragment>(R.id.container) }
            } else {
                Toast.makeText(context, "Bad Credentials!", Toast.LENGTH_SHORT).show()
            }
        })

        submit.setOnClickListener {
            if (userName.isNotEmpty() && password.isNotEmpty()) {
                viewModel.signIn(UserCredentials(userName.toString(), password.toString()))
            } else {
                Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }

}
