package es.eukariotas.giame.persistence.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import es.eukariotas.giame.persistence.database.UserDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModuleUser {
    private const val DATABASE_NAME = "user_database"

    @Singleton
    @Provides
    fun provideUserRoom(@ApplicationContext context: Context) = Room.databaseBuilder(context , UserDatabase::class.java, DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideUserDao(userDatabase: UserDatabase) = userDatabase.getUserDao()
}