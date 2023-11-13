package com.parvinderr.notesdiary.di

import android.content.Context
import com.parvinderr.notesdiary.data.dao.NoteDao
import com.parvinderr.notesdiary.repository.NoteRepository
import com.parvinderr.notesdiary.repository.NoteRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideNoteRepository(
        @ApplicationContext context: Context,
        noteDao: NoteDao,
    ): NoteRepository {
        return NoteRepositoryImpl(context, noteDao)
    }
}
