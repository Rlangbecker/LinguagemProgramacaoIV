package br.com.fundatec.fundatecheroesti21.character.data.repository

import br.com.fundatec.fundatecheroesti21.character.data.CharacterRequest
import br.com.fundatec.fundatecheroesti21.character.data.CharacterResponse
import br.com.fundatec.fundatecheroesti21.login.data.local.UserEntity
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import java.util.*

interface CharacterService {

    @POST("/api/character/1")
    suspend fun createCharacter(@Body characterRequest: CharacterRequest): Response<ResponseBody>

    @GET("/api/character/{idUser}")
    fun getCharacter(@Path("idUser") idUser: Int): Response<List<CharacterResponse>>

}