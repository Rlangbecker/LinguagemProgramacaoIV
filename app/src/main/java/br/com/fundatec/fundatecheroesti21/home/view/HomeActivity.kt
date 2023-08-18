package br.com.fundatec.fundatecheroesti21.home.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import br.com.fundatec.fundatecheroesti21.character.data.domain.CharacterUseCase
import br.com.fundatec.fundatecheroesti21.character.presentation.model.CharacterViewState
import br.com.fundatec.fundatecheroesti21.character.view.CharacterActivity
import br.com.fundatec.fundatecheroesti21.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var characterUseCase: CharacterUseCase

    private val characterViewStateMutableLiveData: MutableLiveData<CharacterViewState> = MutableLiveData()
    val characterViewState: LiveData<CharacterViewState> get() = characterViewStateMutableLiveData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initializeObserver()
        navigateNewCharacter()
    }

    private fun initializeObserver() {
        characterViewState.observe(this, Observer { viewState ->
            when (viewState) {
                is CharacterViewState.ShowLoading -> {
                    // Mostrar loading
                }

                is CharacterViewState.Success -> {
                    // ...
                }

                is CharacterViewState.ShowBirthDateError -> {
                    // Trate este caso
                }

                is CharacterViewState.ShowDescriptionError -> {
                    // Trate este caso
                }

                is CharacterViewState.ShowHomeScreen -> {
                    // Trate este caso
                }

                is CharacterViewState.ShowMessageError -> {
                    // Trate este caso
                }

                is CharacterViewState.ShowNameError -> {
                    // Trate este caso
                }

                is CharacterViewState.Error -> {
                    // Mostrar mensagem de erro
                }

                else -> {
                    // Isto é opcional, mas capturará qualquer outro caso não tratado explicitamente.
                }
            }

        })
    }

    private fun navigateNewCharacter() {
        binding.btNewCharacter.setOnClickListener {
            val intent = Intent(this@HomeActivity, CharacterActivity::class.java)
            startActivity(intent)
        }
    }
}
