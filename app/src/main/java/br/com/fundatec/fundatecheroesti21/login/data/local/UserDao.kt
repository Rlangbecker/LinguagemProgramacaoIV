package br.com.fundatec.fundatecheroesti21.login.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import java.util.*

@Dao
interface UserDao {
    @Insert
    fun insertUser(userEntity: UserEntity)

    @Query("SELECT * from  userTable")
    fun getUser(): List<UserEntity>

    @Query("SELECT lastLoginTime FROM userTable")
    fun getCache(): Date

    @Query("SELECT id FROM userTable")
    fun getId(): Int

    @Query("DELETE  FROM userTable ")
    fun deletarCache()
}