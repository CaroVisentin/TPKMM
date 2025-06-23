package com.unlam.marvelkmm2.android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.unlam.marvelkmm2.VerticalSpaceItemDecoration
import com.unlam.marvelkmm2.CharactersAdapter
import com.unlam.marvelkmm2.CharactersViewModel
import com.unlam.marvelkmm2.android.databinding.ActivityMainBinding
import com.unlam.marvelkmm2.domain.Character
import com.unlam.marvelkmm2.presentation.ScreenState
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var charactersAdapter: CharactersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup del listado
        charactersAdapter = CharactersAdapter()
        val verticalLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        with(binding.charactersList) {
            this.adapter = charactersAdapter
            this.layoutManager = verticalLayoutManager
            this.addItemDecoration(VerticalSpaceItemDecoration(16))
        }

        // ViewModel + UI binding
        val viewModel =
            ViewModelProvider(this, CharactersViewModelFactory())[CharactersViewModel::class.java]

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.screenState.collect {
                    when (it) {
                        ScreenState.Loading -> showLoading()
                        is ScreenState.ShowCharacters -> showCharacters(it.list)
                    }
                }
            }
        }
    }

    private fun showLoading() {
        // Mostrar spinner o mensaje de carga
    }

    private fun showCharacters(list: List<Character>) {
        charactersAdapter.submitList(list)
    }
}
