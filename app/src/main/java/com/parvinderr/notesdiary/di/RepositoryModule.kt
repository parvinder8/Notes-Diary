package com.parvinderr.notesdiary.di

import com.parvinderr.notesdiary.data.dao.NoteDao
import com.parvinderr.notesdiary.repository.NoteRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import javax.inject.Singleton


@Module
@InstallIn(Singleton::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideNoteRepository(noteDao: NoteDao): NoteRepositoryImpl {
        return NoteRepositoryImpl(noteDao)
    }
}