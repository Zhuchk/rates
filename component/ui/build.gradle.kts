android {
    compileSdkVersion(ApplicationId.maxApi)
    buildToolsVersion(ApplicationId.tools)

    defaultConfig {
        minSdkVersion(ApplicationId.minApi)
        targetSdkVersion(ApplicationId.maxApi)
        versionCode = ApplicationId.versionCode
        versionName = ApplicationId.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        testInstrumentationRunnerArguments(mapOf("clearPackageData" to "true"))
    }
}

dependencies {
    implementation(Libraries.rxKotlin)
    implementation(Libraries.rxAndroid)

    implementation(Libraries.kotlinStdlib)
    implementation(Libraries.kotlinReflect)

    implementation(SupportLibraries.material)
    implementation(SupportLibraries.appCompat)

    implementation(ArchitectureLibraries.lifecycleExt)
    implementation(ArchitectureLibraries.lifeCycleRunTime)
    kapt(ArchitectureLibraries.lifecycleCompiler)

    implementation(Libraries.dagger)
    implementation(Libraries.daggerAndroid)
    implementation(Libraries.daggerAndroidSupport)
    kapt(Libraries.daggerAndroidProcessor)
}