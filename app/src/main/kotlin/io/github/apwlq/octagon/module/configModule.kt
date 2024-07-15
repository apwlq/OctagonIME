package io.github.apwlq.octagon.module

import io.github.apwlq.octagon.config.Config
import org.koin.dsl.module

val configModule = module {
    single { Config() }
}