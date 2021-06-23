object CoreLibraries {

    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlinVersion}"
}


object SupportLibraries {

    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompatVersion}"
    const val design = "com.google.android.material:material:${Versions.materialVersion}"
    const val recyclerView = "androidx.recyclerview:recyclerview:${Versions.recyclerViewVersion}"
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayoutVersion}"
    const val androidXcore = "androidx.core:core:${Versions.xCoreVersion}"
    const val androidXFragment = "androidx.fragment:fragment:${Versions.xFragmentVersion}"
    const val androidXFragmentKtx = "androidx.fragment:fragment-ktx:${Versions.xFragmentVersion}"
    const val androidXActivityKtx = "androidx.activity:activity-ktx:${Versions.xActivityKtxVersion}"
}

object Libraries {

    const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.viewModelVersion}"
    const val lifecycleExtensions =
        "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycleVersion}"
    const val lifecycleRuntime =
        "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycleRuntimeVersion}"
    const val coroutinesCore =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutinesVersion}"
    const val coroutinesAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutinesVersion}"
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofitVersion}"
    const val gson = "com.squareup.retrofit2:converter-gson:${Versions.retrofitVersion}"
    const val okHttp = "com.squareup.okhttp3:okhttp:${Versions.okHttpVersion}"
    const val logInterceptor =
        "com.squareup.okhttp3:logging-interceptor:${Versions.okHttpLoggingInterceptorVersion}"
    const val daggerHilt = "com.google.dagger:hilt-android:${Versions.daggerHilt}"
    const val daggerCompiler = "com.google.dagger:hilt-compiler:${Versions.daggerHilt}"
    const val compose = "androidx.compose.ui:ui:${Versions.compose}"
    const val composeMaterial = "androidx.compose.material:material:${Versions.compose}"
    const val composeTool = "androidx.compose.ui:ui-tooling:${Versions.compose}"
    const val composeActivity = "androidx.activity:activity-compose:${Versions.composeActivity}"
}

object TestLibraries {

    const val jUnit = "junit:junit:${Versions.jUnitVersion}"
    const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espressoCoreVersion}"
    const val composeTest = "androidx.compose.ui:ui-test-junit4:${Versions.composeActivity}"
}