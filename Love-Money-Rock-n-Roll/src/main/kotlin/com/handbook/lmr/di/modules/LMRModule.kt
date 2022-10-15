package com.handbook.lmr.di.modules

import com.handbook.lmr.repository.CharacterRepository
import com.handbook.lmr.repository.CharacterRepositoryImplementation
import org.koin.dsl.module

val LMRModule = module {
    single<CharacterRepository> { CharacterRepositoryImplementation() }
}