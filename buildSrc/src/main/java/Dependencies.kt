object ApplicationId {
	const val id = "dmitrii.zhuchkov.testrates"
	const val minApi = 21
	const val maxApi = 28
	const val tools = "28.0.3"
	const val versionCode = 1
	const val versionName = "1.0.0"
}

object Versions {
	const val gradle = "3.4.2"
	const val kotlin = "1.3.41"

	// Android
	const val appCompatVersion = "1.0.2"
	const val recyclerViewVersion = "1.0.0"
	const val legacySupportVersion = "1.0.0"
	const val materialVersion = "1.1.0-alpha05"
	const val coreKtxVersion = "1.1.0"
	const val multidex = "2.0.1"
	const val navVersion = "2.1.0"

	// Arch
	const val lifecycleVersion = "2.0.0"
	const val support = "26.1.0"
	const val arch = "1.0.0"

	// Rx
	const val rxKotlin = "2.1.0"
	const val rxAndroid = "2.0.1"

	// Dagger
	const val dagger = "2.25.2"

	// Networking
	const val retrofit2 = "2.3.0"
	const val okHttp = "3.11.0"
	const val gson = "2.8.2"

	// Logging
	const val timber = "4.6.1"

	// Testing
	const val espresso = "3.1.1"
	const val kotlinMockito = "1.6.0"
	const val junit = "4.12"
	const val junitExtVersion = "1.1.0"
	const val androidMockito = "2.18.3"
	const val junitJacoco = "0.8.3"
	const val testRunner = "1.1.1"
	const val barista = "2.7.0"
	const val uiautomator = "2.1.3"

	const val constraintLayout = "1.1.3"

	// Glide
	const val glide = "4.10.0"

	const val jodaMoney = "1.0.1"
}

object Classpaths {
	const val gradle = "com.android.tools.build:gradle:${Versions.gradle}"
	const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
	const val kotlinAndroidExtensions =
		"org.jetbrains.kotlin:kotlin-android-extensions:${Versions.kotlin}"
}

object Libraries {
	const val kotlinStdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
	const val kotlinReflect = "org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlin}"

	// Rx
	const val rxKotlin = "io.reactivex.rxjava2:rxkotlin:${Versions.rxKotlin}"
	const val rxAndroid = "io.reactivex.rxjava2:rxandroid:${Versions.rxAndroid}"

	// Dagger
	const val dagger = "com.google.dagger:dagger:${Versions.dagger}"
	const val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"
	const val daggerAndroid = "com.google.dagger:dagger-android:${Versions.dagger}"
	const val daggerAndroidSupport = "com.google.dagger:dagger-android-support:${Versions.dagger}"
	const val daggerAndroidProcessor = "com.google.dagger:dagger-android-processor:${Versions.dagger}"

	// Retrofit
	const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit2}"
	const val retrofitConverterGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit2}"
	const val retrofitAdapterRxJava = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit2}"

	// OkHttp
	const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okHttp}"
	const val okhttpLogging = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp}"
	const val okhttpUrlconnection = "com.squareup.okhttp3:okhttp-urlconnection:${Versions.okHttp}"

	const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
	const val gson = "com.google.code.gson:gson:${Versions.gson}"

	// Glide
	const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
	const val glideAnnotationProcessor = "com.github.bumptech.glide:compiler:${Versions.glide}"

	const val jodaMoney = "org.joda:joda-money:${Versions.jodaMoney}"
}

object SupportLibraries {
	const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompatVersion}"
	const val recyclerView = "androidx.recyclerview:recyclerview:${Versions.recyclerViewVersion}"
	const val supportV4 = "androidx.legacy:legacy-support-v4:${Versions.legacySupportVersion}"
	const val material = "com.google.android.material:material:${Versions.materialVersion}"
	const val constraint = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
	const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtxVersion}"
	const val multidex = "androidx.multidex:multidex:${Versions.multidex}"

	const val navigationUi = "androidx.navigation:navigation-ui-ktx:${Versions.navVersion}"
	const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navVersion}"
}

object TestLibraries {
	const val mockito = "org.mockito:mockito-core:${Versions.androidMockito}"
	const val mockitoAndroid = "org.mockito:mockito-android:${Versions.androidMockito}"
	const val junit = "junit:junit:${Versions.junit}"
	const val junitExt = "androidx.test.ext:junit:${Versions.junitExtVersion}"
	const val kotlinMockito = "com.nhaarman:mockito-kotlin-kt1.1:${Versions.kotlinMockito}"
	const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espresso}"
	const val espressoIntents = "androidx.test.espresso:espresso-intents:${Versions.espresso}"
	const val espressoContrib = "androidx.test.espresso:espresso-contrib:${Versions.espresso}"
	const val espressoIdling = "androidx.test.espresso.idling:idling-concurrent:${Versions.espresso}"
	const val espressoWeb = "androidx.test.espresso:espresso-web:${Versions.espresso}"
	const val barista = "com.schibsted.spain:barista:${Versions.barista}"
	const val uiAutomator = "com.android.support.test.uiautomator:uiautomator-v18:${Versions.uiautomator}"
	const val orchestrator = "androidx.test:orchestrator:${Versions.testRunner}"
}

object ArchitectureLibraries {
	const val lifecycleExt = "android.arch.lifecycle:extensions:${Versions.arch}"
	const val lifeCycleRunTime = "android.arch.lifecycle:runtime:${Versions.arch}"
	const val lifecycleCompiler = "android.arch.lifecycle:compiler:${Versions.arch}"
}

