package com.company.manager.archunit

import com.tngtech.archunit.core.importer.ImportOption.DoNotIncludeTests
import com.tngtech.archunit.junit.AnalyzeClasses
import com.tngtech.archunit.junit.ArchTest
import com.tngtech.archunit.lang.ArchRule
import com.tngtech.archunit.library.Architectures

private const val DOMAIN = "Domain"
private const val ADAPTERS = "Adapters"
private const val APPLICATION = "Application"
private const val CONFIG = "Config"
@AnalyzeClasses(packages = ["com.company.manager"], importOptions = [DoNotIncludeTests::class])
class LayeredArchitectureTest {

    @ArchTest
    val layer_dependencies_are_respected: ArchRule = Architectures.layeredArchitecture()
        .consideringOnlyDependenciesInLayers()
        .layer(CONFIG).definedBy("com.company.manager..config..")
        .layer(DOMAIN).definedBy("com.company.manager..domain..")
       .layer(ADAPTERS).definedBy("com.company.manager..adapter..")
        .layer(APPLICATION).definedBy("com.company.manager..application..")
        .whereLayer(APPLICATION).mayOnlyBeAccessedByLayers(ADAPTERS, CONFIG)
        .whereLayer(ADAPTERS).mayOnlyBeAccessedByLayers(CONFIG)
        .whereLayer(DOMAIN).mayOnlyBeAccessedByLayers(APPLICATION, ADAPTERS, CONFIG)
}
