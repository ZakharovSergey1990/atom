package ru.atom.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.atom.data.api.Api
import ru.atom.data.api.ApiImpl
import ru.atom.data.repository.ChargersRepositoryImpl
import ru.atom.domain.repository.ChargersRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class BindModule {

    @Singleton
    @Binds
    abstract fun bindChargersRepository(impl: ChargersRepositoryImpl): ChargersRepository

    @Singleton
    @Binds
    abstract fun bindApi(impl: ApiImpl): Api
}