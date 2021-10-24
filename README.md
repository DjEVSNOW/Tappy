# Tappy
Travel app
Android-приложение, чтобы спланировать ваш идеальный отпуск!
## Сборка
Рекомендуется собирать данное приложение с помощью Android Studio. В ней встроенный сборщик gradle всё синхронизирует и соберёт проект.

# Использованный стэк
Koltin, Gradle
Внешние зависимости:
- com.squareup.retrofit2:converter-gson:2.9.0
- com.google.code.gson:gson:2.8.7
- com.squareup.retrofit2:retrofit:2.9.0
- com.squareup.retrofit2:converter-gson:2.9.0
Карты: 
-com.tomtom.online:sdk-maps:2.4807
-com.tomtom.online:sdk-search:2.4807
-com.tomtom.online:sdk-routing:2.4807
-com.tomtom.online:sdk-maps-ktx-extensions:2.4807

Навигация в приложении
- androidx.navigation:navigation-dynamic-features-fragment
- androidx.navigation:navigation-fragment-ktx
- androidx.navigation:navigation-ui-ktx

- com.google.android.material:material
- androidx.lifecycle:lifecycle-livedata-ktx:2.3.1
//Koin
- org.koin:koin-android
- org.koin:koin-android-scope
- org.koin:koin-android-architecture:0.9.3
- org.koin:koin-androidx-viewmodel
- org.koin:koin-androidx-fragment

//UI
- com.xwray:groupie:2.9.0
- com.squareup.picasso:picasso:2.71828
- jp.wasabeef:picasso-transformations:2.4.0
- com.github.barteksc:android-pdf-viewer:2.8.2