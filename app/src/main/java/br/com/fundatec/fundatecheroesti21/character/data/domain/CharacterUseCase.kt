package br.com.fundatec.fundatecheroesti21.character.data.domain

import br.com.fundatec.fundatecheroesti21.character.data.CharacterResponse
import br.com.fundatec.fundatecheroesti21.character.data.local.CharacterEntity
import br.com.fundatec.fundatecheroesti21.character.data.local.CharacterModel
import br.com.fundatec.fundatecheroesti21.character.data.repository.CharacterRepository
import br.com.fundatec.fundatecheroesti21.characterRegister.domain.CharacterRegisterModel
import retrofit2.Response

class CharacterUseCase {

    private val repository by lazy { CharacterRepository() }

    suspend fun getCharacters(): List<CharacterModel>{
        return repository.getCharacters()
    }
}