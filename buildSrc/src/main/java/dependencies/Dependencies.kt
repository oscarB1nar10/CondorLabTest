package dependencies.dependencies

import dependencies.Versions
import sun.misc.Version

object Dependencies {
    val kotlin_standard_library = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    val kotlin_reflect = "org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlin}"
    val ktx = "androidx.core:core-ktx:${Versions.ktx}"
    val kotlin_coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines_version}"
    val kotlin_coroutines_android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines_version}"
    val kotlin_coroutines_play_services = "org.jetbrains.kotlinx:kotlinx-coroutines-play-services:${Versions.coroutines_play_services}"
    val dagger = "com.google.dagger:dagger:${Versions.dagger}"
    val navigation_fragment = "androidx.navigation:navigation-fragment-ktx:${Versions.nav_components}"
    val navigation_runtime = "androidx.navigation:navigation-runtime:${Versions.nav_components}"
    val navigation_ui = "androidx.navigation:navigation-ui-ktx:${Versions.nav_components}"
    val navigation_dynamic = "androidx.navigation:navigation-dynamic-features-fragment:${Versions.nav_components}"
    val material_dialogs = "com.afollestad.material-dialogs:core:${Versions.material_dialogs}"
    val material_dialogs_input = "com.afollestad.material-dialogs:input:${Versions.material_dialogs}"
    val room_runtime = "androidx.room:room-runtime:${Versions.room}"
    val room_ktx = "androidx.room:room-ktx:${Versions.room}"
    val play_core = "com.google.android.play:core:${Versions.play_core}"
    val leak_canary = "com.squareup.leakcanary:leakcanary-android:${Versions.leak_canary}"
    val firebase_firestore = "com.google.firebase:firebase-firestore-ktx:${Versions.firebase_firestore}"
    val firebase_auth = "com.google.firebase:firebase-auth:${Versions.firebase_auth}"
    val firebase_analytics = "com.google.firebase:firebase-analytics-ktx:${Versions.firebase_analytics}"
    val firebase_crashlytics = "com.google.firebase:firebase-crashlytics:${Versions.firebase_crashlytics}"
    val lifecycle_runtime = "androidx.lifecycle:lifecycle-runtime:${Versions.lifecycle_version}"
    val lifecycle_coroutines = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle_version}"
    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit2_version}"
    val retrofit_gson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit2_version}"
    val markdown_processor = "com.yydcdut:markdown-processor:${Versions.markdown_processor}"
    val dagger_hilt = "com.google.dagger:hilt-android:${Versions.dagger_hilt}"
    val dagger_hilt_with_life_cycle = "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.dagger_hilt_with_life_cycle}"
    val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    val view_model_life_cycle = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.view_model_life_cycle}"
    val ok_http = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
    val ok_http_log = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"
    val mockito_core = "org.mockito:mockito-core:${Versions.mockito_core}"
    val mockito_all = "org.mockito:mockito-all:${Versions.mockito_all}"
    val mock_server = "com.squareup.okhttp3:mockwebserver:${Versions.mock_server}"
    val mockito_android = "org.mockito:mockito-android:${Versions.mockito_core}"

}
