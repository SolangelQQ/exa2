package com.example.book.di

import android.content.Context
import com.example.domain.repository.BookRepository
import com.example.framework.persistence.AppDatabase
import com.example.framework.persistence.BookDao
import com.example.framework.repository.BookRepositoryImpl
import com.example.framework.service.BookApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideBookApiService(): BookApiService {
        return Retrofit.Builder()
            .baseUrl("https://openlibrary.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BookApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideBookRepository(
        apiService: BookApiService,
        bookDao: BookDao
    ): BookRepository {
        return BookRepositoryImpl(apiService, bookDao)
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getDatabase(context)
    }

    @Provides
    @Singleton
    fun provideBookDao( database: AppDatabase): BookDao {
        return database.bookDao()
    }
}