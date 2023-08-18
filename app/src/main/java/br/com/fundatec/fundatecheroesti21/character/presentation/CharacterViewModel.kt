package br.com.fundatec.fundatecheroesti21.character.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.fundatec.fundatecheroesti21.character.data.CharacterRequest
import br.com.fundatec.fundatecheroesti21.character.data.repository.CharacterRepository
import br.com.fundatec.fundatecheroesti21.character.data.repository.CharacterService
import br.com.fundatec.fundatecheroesti21.character.presentation.model.CharacterViewState
import br.com.fundatec.fundatecheroesti21.network.RetrofitNetworkClient
import kotlinx.coroutines.launch
import java.util.Date

class CharacterViewModel : ViewModel() {

    private val viewState = MutableLiveData<CharacterViewState>()

//    private val api: CharacterService by lazy {
//        RetrofitNetworkClient.createNetworkClient().create(CharacterService::class.java)
//    }

    private val repository by lazy { CharacterRepository() }

    val state: LiveData<CharacterViewState> = viewState

    fun createCharacter(name: String?, description: String?, image: String?, universeType: String?, characterType: String? ,age: String?, birthday: String?) {
        viewModelScope.launch {
            try {
                val character = CharacterRequest(
                    name = name ?: "",
                    description = description ?: "",
                    image = "", // aqui você terá que pegar a URL da imagem do usuário
                    universeType = "MARVEL", // e ajustar isso de acordo com a entrada do usuário
                    characterType = "HERO", // e ajustar isso também
                    age = age?.toIntOrNull() ?: 0,
                    birthday = birthday?: ""
                )
                val response = repository.createCharacter(character)
                if (response) {
                    viewState.value = CharacterViewState.ShowHomeScreen
                } else {
                    viewState.value = CharacterViewState.ShowMessageError
                }
            } catch (e: Exception) {
                viewState.value = CharacterViewState.ShowMessageError
            }
        }
    }
}
