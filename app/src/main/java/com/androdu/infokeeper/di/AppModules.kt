package com.androdu.infokeeper.di

import androidx.room.Room
import com.androdu.infokeeper.data.repository.PersonRepository
import com.androdu.infokeeper.data.repository.PersonRepositoryImpl
import com.androdu.infokeeper.data.room.PersonDatabase
import com.androdu.infokeeper.domain.usecase.DeletePersonUseCase
import com.androdu.infokeeper.domain.usecase.GetAllPersonsUseCase
import com.androdu.infokeeper.domain.usecase.InsertPersonUseCase
import com.androdu.infokeeper.domain.usecase.UpdatePersonUseCase
import com.androdu.infokeeper.ui_compose.info_screen.InfoViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Koin module for providing database-related dependencies.
 */
val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            PersonDatabase::class.java, "info_keeper_database"
        ).build()
    }

    single { get<PersonDatabase>().personDao() }
}

/**
 * Koin module for providing repository dependencies.
 */
val repositoryModule = module {
    single<PersonRepository> { PersonRepositoryImpl(get()) }
}

/**
 * Koin module for providing use case dependencies.
 */
val useCaseModule = module {
    factory { InsertPersonUseCase(get()) }
    factory { UpdatePersonUseCase(get()) }
    factory { DeletePersonUseCase(get()) }
    factory { GetAllPersonsUseCase(get()) }
}

/**
 * Koin module for providing view model dependencies.
 */
val viewModelModule = module {
    viewModel { InfoViewModel(get()) }
}

/**
 * List of all app modules for Koin.
 */
val appModule = listOf(databaseModule, repositoryModule, useCaseModule, viewModelModule)
