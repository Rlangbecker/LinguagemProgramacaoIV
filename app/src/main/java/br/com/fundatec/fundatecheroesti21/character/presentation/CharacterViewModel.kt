package br.com.fundatec.fundatecheroesti21.character.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.fundatec.fundatecheroesti21.character.presentation.model.CharacterViewState
import java.time.LocalDate
import java.util.regex.Pattern

class CharacterViewModel : ViewModel() {

    private val viewState = MutableLiveData<CharacterViewState>()
    val state: LiveData<CharacterViewState> = viewState
    fun validateInputs(name: String?, description: String?, age: String?, birth_date: String?) {
        var patternAge = Pattern.compile("[0-9]")
        var matcherAge = patternAge.matcher(age)

        var patternBirthDate = Pattern.compile("\\d{2}[-\\/\\.]\\d{2}[-\\/\\.]\\d{4}|\\d{8}")
        var matcherBirthDate = patternBirthDate.matcher(birth_date)

        viewState.value = CharacterViewState.ShowLoading

        if (name.isNullOrBlank() && description.isNullOrBlank() && age.toString()
                .isNullOrBlank() && birth_date.toString().isNullOrBlank()
        ) {
            viewState.value = CharacterViewState.ShowMessageError
            return
        }

        if (name.isNullOrBlank()) {
            viewState.value = CharacterViewState.ShowNameError
            return
        }
        if (description.isNullOrBlank()) {
            viewState.value = CharacterViewState.ShowDescriptionError
            return
        }
        if (!matcherAge.matches()) {
            viewState.value = CharacterViewState.ShowAgeError
            return
        }

        if (age.isNullOrBlank() || age.equals("0")) {
            viewState.value = CharacterViewState.ShowAgeError
            return
        }

        if (!matcherBirthDate.matches()) {
            viewState.value = CharacterViewState.ShowBirthDateError
            return
        }

        if (birth_date.isNullOrBlank()) {
            viewState.value = CharacterViewState.ShowNameError
            return
        }

        fetchLogin(name, description, age, birth_date)
    }

    private fun fetchLogin(name: String, description: String, age: String, birth_date: String) {
        viewState.value = CharacterViewState.ShowHomeScreen
    }
}