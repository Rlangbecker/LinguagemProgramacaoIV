package br.com.fundatec.fundatecheroesti21.login.domain

import br.com.fundatec.fundatecheroesti21.login.data.local.UserCache
import br.com.fundatec.fundatecheroesti21.login.data.repository.LoginRepository

class LoginUseCase {
    private val repository by lazy { LoginRepository() }
    private val userCache = UserCache()

    suspend fun login(email: String, password: String): Boolean {
        return repository.login(email = email, password = password)
    }
    suspend fun verificarTempoCache(ultrapassouTempo: Boolean): Boolean {
        repository.validateCache(ultrapassouTempo)
        return ultrapassouTempo
    }
    suspend fun verificarSeUsuarioExiste(usuarioExiste: Boolean): Boolean {
        repository.verificarSeUsuarioExiste(usuarioExiste);
        return usuarioExiste
    }
}