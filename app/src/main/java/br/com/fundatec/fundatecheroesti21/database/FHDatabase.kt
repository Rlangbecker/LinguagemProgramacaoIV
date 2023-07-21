package br.com.fundatec.fundatecheroesti21.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.fundatec.fundatecheroesti21.App
import br.com.fundatec.fundatecheroesti21.character.data.local.CharacterDao
import br.com.fundatec.fundatecheroesti21.character.data.local.CharacterEntity
import br.com.fundatec.fundatecheroesti21.database.converters.Converters
import br.com.fundatec.fundatecheroesti21.login.data.local.UserDao
import br.com.fundatec.fundatecheroesti21.login.data.local.UserEntity

@Database(entities = [UserEntity::class,CharacterEntity::class], version = 3)
@TypeConverters(Converters::class)
abstract class FHDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract  fun characterDao(): CharacterDao

    companion object {
        fun getInstance(): FHDatabase {
            return Room.databaseBuilder(
                App.context,
                FHDatabase::class.java,
                "fh.database"
            ).allowMainThreadQueries().build()
        }
    }
}