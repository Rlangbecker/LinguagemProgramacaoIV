import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.fundatec.fundatecheroesti21.character.data.CharacterRequest
import br.com.fundatec.fundatecheroesti21.character.data.repository.CharacterService
import br.com.fundatec.fundatecheroesti21.character.presentation.model.CharacterViewState
import br.com.fundatec.fundatecheroesti21.network.RetrofitNetworkClient
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAccessor
import java.util.Date
import java.util.regex.Pattern


class CharacterViewModel : ViewModel() {

    private val viewState = MutableLiveData<CharacterViewState>()

    private val api: CharacterService by lazy {
        RetrofitNetworkClient.createNetworkClient().create(CharacterService::class.java)
    }

    val state: LiveData<CharacterViewState> = viewState


    @RequiresApi(Build.VERSION_CODES.O)
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

        createCharacter (name, description, age, birth_date)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun createCharacter(name: String?, description: String?, age: String?, birth_date: String?) {
        viewModelScope.launch {
            try {
                Log.e("CharacterViewModel", "Entrou chamado" +  Date.from(Instant.now()))
                val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd") // Formato da data
                val birthDate = birth_date?.let { LocalDate.parse(it, dateFormatter) }
                Log.e("CharacterViewModel", "formatou data" +  Date.from(Instant.now()))
                val character = CharacterRequest(
                    name = name ?: "super",
                    description = description ?: "asdasdasdasd",
                    image = "https://www.ufrgs.br/lidia-diabetes/wp-content/uploads/2017/03/exerciciofisico-235x300.jpg", // URL da imagem do usuário
                    universeType = "MARVEL", // Ajuste de acordo com a entrada do usuário
                    characterType = "HERO", // Ajuste também
                    age = 18,
                    birthday = Date()
                )
                val response = api.createCharacter(character)
                Log.e("CharacterViewModel", "fez chamado API ("+response.isSuccessful+")" +  Date.from(Instant.now()))
                if (response.isSuccessful) {
                    viewState.value = CharacterViewState.ShowHomeScreen
                } else {
                    viewState.value = CharacterViewState.ShowMessageError
                }
            } catch (e: Exception) {
                Log.e("CharacterViewModel", "Error creating character" + Date.from(Instant.now()), e)
                viewState.value = CharacterViewState.ShowMessageError
            }
        }
    }
}
