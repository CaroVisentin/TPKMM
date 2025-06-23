package com.unlam.marvelkmm2.android

import com.marvel.MarvelDatabase
import com.unlam.marvelkmm2.data.MarvelApiClient
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.unlam.marvelkmm2.android.ui.main.VerticalSpaceItemDecoration
import com.unlam.marvelkmm2.android.ui.main.CharactersAdapter
import com.unlam.marvelkmm2.CharactersViewModel
import com.unlam.marvelkmm2.android.databinding.ActivityMainBinding
import com.unlam.marvelkmm2.android.ui.viewmodel.CharactersViewModelFactory
import com.unlam.marvelkmm2.domain.Character
import com.unlam.marvelkmm2.presentation.ScreenState
import kotlinx.coroutines.launch
import com.unlam.marvelkmm2.data.CharacterCacheRepository
import com.unlam.marvelkmm2.data.KtorCharactersRepository
import com.unlam.marvelkmm2.database.DatabaseDriverFactory

class MainActivity : AppCompatActivity() {

    private lateinit var charactersAdapter: CharactersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val apiClient = MarvelApiClient()
        val driver = DatabaseDriverFactory(this).createDriver()
        val db = MarvelDatabase(driver)
        val cache = CharacterCacheRepository(db)
        val repo = KtorCharactersRepository(apiClient, cache)

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
        val viewModel = ViewModelProvider(this, CharactersViewModelFactory(repo))
            .get(CharactersViewModel::class.java)

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
