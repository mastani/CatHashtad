# Cat Breeds Explorer 🐱

A modern Android application built with Kotlin and Jetpack Compose that displays cat breeds information using TheCatAPI. The app demonstrates clean architecture principles, offline-first approach, and modern Android development practices.

## 📱 Project Overview

This Android application serves as a cat breeds explorer, allowing users to browse, search, and favorite different cat breeds. Built using Android technologies including Jetpack Compose for UI and Room for local data persistence, the app provides a smooth user experience with offline capabilities.

**Tech Stack:**
- **Language:** Kotlin
- **UI Framework:** Jetpack Compose
- **Architecture:** Multi-module Clean Architecture
- **Database:** Room (SQLite)
- **API:** [TheCatAPI](https://thecatapi.com)
- **Offline Strategy:** Cache-first with Room database

## ✨ Key Features

- **📋 Cat Listing:** Browse through a list of cat breeds with pagination support
- **🔍 Real-time Search:** Search cat breeds with debounced API calls for optimal performance
- **📖 Detailed Information:** View detailed information about each cat breed including characteristics and images
- **❤️ Favorites Management:** Save and manage favorite cat breeds locally
- **🔄 Offline Support:** Offline functionality with local caching using Room database
- **📱 Modern UI:** Clean and intuitive interface built with Jetpack Compose
- **⚡ Performance Optimized:** Efficient data loading with pagination and caching strategies

## 🏗️ Architecture Layers

The application follows **Clean Architecture** principles with clear separation of concerns across multiple layers:

### Presentation Layer
- **UI Components:** Jetpack Compose screens and components
- **ViewModels:** State management and UI logic
- **Navigation:** Screen navigation handling

### Domain Layer
- **Repository Interfaces:** Data access abstractions
- **Models:** Core business entities

### Data Layer
- **Repository Implementations:** Data source coordination
- **Local Data Source:** Room database operations
- **Remote Data Source:** API service calls
- **Mappers:** Data transformation between layers

## 📦 Modules

| Module | Description |
|--------|-------------|
| **app** | Main application module containing application entry point |
| **build-logic** | Centralized build configuration and convention plugins for consistent builds |
| **data** | Data layer implementation including repositories, data sources, and Room database |
| **designSystem** | Reusable UI components, themes, and design tokens for consistent styling |
| **domain** | Business logic layer containing repository interfaces and domain models |
| **features** | Feature-specific UI implementations including screens and ViewModels |

## 🚀 Screens

### 1. Home Screen
- Displays paginated list of cat breeds
- Implements efficient caching mechanism
- Pull-to-refresh functionality
- Loading states and error handling

### 2. Detail Screen
- Comprehensive breed information display
- High-quality breed images
- Breed characteristics and temperament
- Add/remove from favorites functionality

### 3. Search Screen
- Real-time search with debounce implementation
- Search history and suggestions
- Filtered results display
- Empty state handling

### 4. Favorites Screen
- Locally stored favorite breeds
- Quick access to preferred breeds
- Remove from favorites functionality
- Offline availability

## 🌿 Branch Structure

The project maintains two major implementation branches:

- **`navigation2`** - Implementation using Navigation Component 2.x
- **`navigation3`** - Implementation using Navigation Component 3.x (You will Android Studio Narwhal Feature Drop)

## 🛠️ Technical Highlights

- **Offline-First Architecture:** Ensures app functionality without internet connectivity
- **Multi-module Structure:** Promotes code reusability and maintainability
- **Clean Architecture:** Clear separation of concerns with dependency inversion
- **Modern UI:** Jetpack Compose with Material Design 3
- **Efficient Data Loading:** Pagination and smart caching strategies
- **Type Safety:** Leveraging Kotlin's type system for robust code
- **Dependency Injection:** Organized and testable code structure

#### Releases
Link to release apk file: [https://github.com/mastani/CatHashtad/releases/download/v1/app-production-release.apk](https://github.com/mastani/CatHashtad/releases/tag/v1)
