package br.com.fundatec.fundatecheroesti21.home.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.fundatec.fundatecheroesti21.character.data.domain.CharacterUseCase
import br.com.fundatec.fundatecheroesti21.home.presentation.model.HomeViewState
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val useCase by lazy { CharacterUseCase() }

    private val viewState = MutableLiveData<HomeViewState>()
    val state: LiveData<HomeViewState> = viewState

    fun listCharacter() {
        viewState.value = HomeViewState.ShowLoading
        viewModelScope.launch {
         val list = useCase.getCharacters()

            if(list.isEmpty()){
                viewState.value = HomeViewState.ShowEmptyList
            } else {
                viewState.value = HomeViewState.ShowHomeScreen(list)
            }
        }
    }
}