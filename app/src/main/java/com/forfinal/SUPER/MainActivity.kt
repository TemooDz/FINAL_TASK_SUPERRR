package com.forfinal.SUPER

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.forfinal.SUPER.databinding.ActivityMainBinding
import com.forfinal.SUPER.ui.characters.CharactersFragment
import com.forfinal.SUPER.ui.login.LoginFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                add<CharactersFragment>(R.id.container)
            }
        }
        binding.navigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.characters -> {
                    supportFragmentManager.commit { replace<CharactersFragment>(R.id.container) }
                    true
                }
                R.id.search -> {
                    true
                }
                R.id.login -> {
                    supportFragmentManager.commit { replace<LoginFragment>(R.id.container) }
                    true
                }
                else -> false
            }
        }
    }
}
