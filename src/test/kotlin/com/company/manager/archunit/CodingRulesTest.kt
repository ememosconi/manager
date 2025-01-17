package com.company.manager.archunit

import com.tngtech.archunit.core.importer.ImportOption
import com.tngtech.archunit.junit.AnalyzeClasses
import com.tngtech.archunit.junit.ArchTest
import com.tngtech.archunit.lang.ArchRule
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition
import com.tngtech.archunit.library.GeneralCodingRules

@AnalyzeClasses(packages = ["com.company.manager"], importOptions = [ImportOption.DoNotIncludeTests::class])
class CodingRulesTest {
    @ArchTest
    val exceptions_should_respect_naming_convention: ArchRule = ArchRuleDefinition.classes()
        .that().resideInAPackage("..exception..")
        .should().haveSimpleNameEndingWith("Exception")

    @ArchTest
    val use_cases_should_respect_naming_convention: ArchRule = ArchRuleDefinition.classes()
        .that().resideInAPackage("..usecase..")
        .and()
        .haveNameNotMatching(".*\\$.*")
        .should().haveSimpleNameEndingWith("UseCase")

    @ArchTest
    val in_ports_should_respect_naming_convention: ArchRule = ArchRuleDefinition.classes()
        .that().areInterfaces().and().resideInAPackage("..in..")
        .should().haveSimpleNameEndingWith("InPort")

    @ArchTest
    val out_ports_should_respect_naming_convention: ArchRule = ArchRuleDefinition.classes()
        .that().areInterfaces().and().resideInAPackage("..out..")
        .should().haveSimpleNameEndingWith("OutPort")

    @ArchTest
    val adapters_should_respect_naming_convention: ArchRule = ArchRuleDefinition.classes()
        .that().resideInAPackage("..adapter.*")
        .and()
        .haveNameNotMatching(".*\\$.*")
        .should().haveSimpleNameEndingWith("Adapter")

    @ArchTest
    var no_generic_exceptions = GeneralCodingRules.NO_CLASSES_SHOULD_THROW_GENERIC_EXCEPTIONS

    @ArchTest
    var no_standard_streams = GeneralCodingRules.NO_CLASSES_SHOULD_ACCESS_STANDARD_STREAMS

    @ArchTest
    var no_java_logging = GeneralCodingRules.NO_CLASSES_SHOULD_USE_JAVA_UTIL_LOGGING

    @ArchTest
    var no_jodatime = GeneralCodingRules.NO_CLASSES_SHOULD_USE_JODATIME

    @ArchTest
    var classes_must_not_be_suffixed_with_impl = ArchRuleDefinition.noClasses()
        .should().haveSimpleNameEndingWith("Impl")
        .because("En serio, y si nos esforzamos un poco más?")
}
