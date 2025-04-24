package com.example.kotlin.mysudoku.di

import com.example.kotlin.mysudoku.data.remote.api.SudokuApi
import com.example.kotlin.mysudoku.data.repository.SudokuRepositoryIm
import com.example.kotlin.mysudoku.domain.repository.SudokuRepository
import com.example.kotlin.mysudoku.domain.usecase.GenerateSudokuUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideSudokuApi(): SudokuApi {
        return Retrofit.Builder()
            .baseUrl("https://api.api-ninjas.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder().addInterceptor { chain ->
                    val request = chain.request().newBuilder()
                        .addHeader("X-Api-Key", "wLVPN1zV08lJYF7uXqgyPw==zVwp6TlVcAO1NLUf")
                        .build()
                    chain.proceed(request)
                }.build()
            )
            .build()
            .create(SudokuApi::class.java)
    }

    @Provides
    fun provideSudokuRepository(api: SudokuApi): SudokuRepository =
        SudokuRepositoryIm(api)

    @Provides
    fun provideGenerateSudokuUseCase(repository: SudokuRepository): GenerateSudokuUseCase =
        GenerateSudokuUseCase(repository)
}
