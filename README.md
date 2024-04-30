## README

### Project: Android Movie App

This project is an Android application that displays a list of upcoming movies from the TMDB API. It is designed to be scalable and easy to use, and it follows best practices for Android development.

### Requirements

* **Basic Functionality:**
    * Display a list of upcoming movies from the TMDB API
    * Allow users to view the name of a movie when it is clicked
    * Load movies in pages
* **Offline Mode:**
    * Cache movies locally for offline viewing
* **User Interface:**
    * Implement the provided UI design
    * Use provided icons and assets
* **Bonus Points:**
    * Implement loading animation
    * Use Jetpack Compose for UI development
    * Implement 'loadMore' and 'error' items as list items
* **Important:**
    * Write code in Kotlin
    * Use proper code style and structure
    * Utilize incremental development and Git
    * Employ appropriate design patterns and Android best practices

### API

* **Movie List:** [https://developer.themoviedb.org/reference/movie-upcoming-list](https://developer.themoviedb.org/reference/movie-upcoming-list)
* **Movie Images:** [https://developer.themoviedb.org/docs/image-basics](https://developer.themoviedb.org/docs/image-basics)

### Libraries

* Jetpack Compose
* Room Database
* Paging3
* Retrofit2
* Hilt
* Coil

### Modules

* app
* feature:upcoming
* core:ui
* core:data
* core:database
* core:network
