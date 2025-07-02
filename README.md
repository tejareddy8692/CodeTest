**Android Clean MVI Architecture Sample**

This repository contains a code sample demonstrating the implementation of Clean Model-View-Intent (MVI) architecture in an Android application. The architecture aims to promote separation of concerns, scalability, and maintainability by organizing code into distinct layers.

### Overview

The application demonstrates the following key components and technologies:

- Clean MVI Architecture: A variation of the Model-View-Intent pattern that emphasizes separation of concerns and unidirectional data flow.
- Kotlin Coroutines: Used for asynchronous and non-blocking programming.
- ViewModel: Android Architecture Component that provides a lifecycle-aware container to hold and manage UI-related data.
- Coil: Image loading library for Android.
- Retrofit: Type-safe HTTP client for Android and Java.
- Jetpack Compose: Modern UI toolkit for building native Android UIs.
- GSON Converter Factory: JSON serialization/deserialization library for converting Java Objects into JSON and back.
- Dagger Hilt: Dependency injection library for Android.
- MockK: Mocking library for Kotlin

### Project Structure

The project is structured into the following packages:

- `data`: Contains data-related classes such as repositories, data sources, and models.
- `domain`: Contains domain logic, including use cases and domain models.
- `presentation`: Contains UI-related classes such as ViewModels, states, and actions.
- `di`: Contains Dagger Hilt dependency injection setup.
- `ui`: Contains Jetpack Compose UI components and screens.
- `util`: Contains utility classes and extensions.