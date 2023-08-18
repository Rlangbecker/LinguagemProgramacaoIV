package br.com.fundatec.fundatecheroesti21.character.presentation.model

import br.com.fundatec.fundatecheroesti21.character.data.local.CharacterModel

sealed class CharacterViewState {
    object ShowHomeScreen : CharacterViewState()
    object ShowLoading : CharacterViewState()
    object ShowNameError : CharacterViewState()
    object ShowDescriptionError : CharacterViewState()
    object ShowMessageError : CharacterViewState()
    object ShowAgeError : CharacterViewState()
    object ShowBirthDateError : CharacterViewState()
    data class Success(val characters: List<CharacterModel>): CharacterViewState()
    data class Error(val error: Throwable): CharacterViewState()
}