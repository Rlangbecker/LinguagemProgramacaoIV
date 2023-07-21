package br.com.fundatec.fundatecheroesti21.character.data.repository

import android.util.Log
import br.com.fundatec.fundatecheroesti21.character.data.CharacterRequest
import br.com.fundatec.fundatecheroesti21.character.data.CharacterResponse
import br.com.fundatec.fundatecheroesti21.character.data.local.CharacterEntity
import br.com.fundatec.fundatecheroesti21.database.FHDatabase
import br.com.fundatec.fundatecheroesti21.network.RetrofitNetworkClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

class CharacterRepository {

    private val database: FHDatabase by lazy {
        FHDatabase.getInstance()
    }

    private val client = RetrofitNetworkClient
        .createNetworkClient()
        .create(CharacterService::class.java)


    suspend fun createCharacter(
        name: String,
        description: String,
        image: String, universeType: String,
        characterType: String,
        age: Int,
        birthday: Date
    ): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                val response = client.createCharacter(
                    CharacterRequest(
                        name,
                        description,
                        image,
                        universeType,
                        characterType,
                        age,
                        birthday
                    )
                )
                response.isSuccessful
            } catch (exception: Exception) {
                Log.e("createCharacter", exception.message.orEmpty())
                false
            }
        }
    }

    private fun CharacterResponse.characterResponseToEntity(): CharacterEntity {
        return CharacterEntity(
            name= name,
            description= description,
            image= image,
            universeType= universeType,
            characterType= characterType,
            age= age,
            birthday= birthday
        )
    }
}