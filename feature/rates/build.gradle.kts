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
    implementation(project((":component.mvp")))
    implementation(project((":component.ui")))
    implementation(project((":component.utils")))

    implementation(Libraries.rxKotlin)
    implementation(Libraries.rxAndroid)

    implementation(Libraries.kotlinStdlib)
    implementation(Libraries.kotlinReflect)

    implementation(SupportLibraries.material)
    implementation(SupportLibraries.appCompat)
    implementation(SupportLibraries.constraint)
    implementation(SupportLibraries.recyclerView)
    implementation(SupportLibraries.coreKtx)

    implementation(Libraries.dagger)
    implementation(Libraries.daggerAndroid)
    implementation(Libraries.daggerAndroidSupport)
    kapt(Libraries.daggerCompiler)
    kapt(Libraries.daggerAndroidProcessor)

    implementation(Libraries.retrofit)
    implementation(Libraries.jodaMoney)

	implementation(Libraries.glide)

    testImplementation(project(":component.test"))
}