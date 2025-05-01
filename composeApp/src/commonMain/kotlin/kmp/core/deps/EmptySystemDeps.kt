package kmp.core.deps

/**
 * Use for ios DI part like a default parameter. Cause IOS deps not needed system objects
 *
 * For cases like this:
 * ```
 * expect fun createFoo(systemDeps: SystemDeps): Foo
 *
 * class CoreModule {
 *     fun provideFoo(
 *       systemDeps: SystemDeps = EmptySystemDeps()
 *     ): Foo { createFoo(systemDeps) }
 * }
 *
 * // Ios part
 * actual fun createFoo(systemDeps: SystemDeps): Foo = IosFoo() // Not needed parameters
 *
 * fun main() {
 *     val coreModule = CoreModule()
 *     val foo = coreModule.provideFoo()
 * }
 * // Android part
 * actual fun createFoo(systemDeps: SystemDeps): Foo =
 *      AndroidFoo(systemDeps.asAndroid().applicationContext) // Need android Context
 *
 * fun main() {
 *     val systemDeps = AndroidSystemDeps(...)
 *     val coreModule = CoreModule()
 *     val foo = coreModule.provideFoo(systemDeps)
 * }
 * ```
 * @see SystemDeps
 * */
class EmptySystemDeps : SystemDeps
