<img src=".github/trails_logo.svg" height="250" alt="Trails logo"/>

# Trails

## Overview

Trails is a sample Kotlin Multiplatform app targeting Android, iOS, and web. It's designed to demonstrate best practices for building offline-first experiences using [Store](https://github.com/MobileNativeFoundation/Store). It leverages modern Kotlin libraries and architectural patterns to provide a seamless offline experience for users.

### Key Dependencies

- **[Store](https://github.com/MobileNativeFoundation/Store)**: Manages data loading and updating, implementing offline-first patterns. For more information, refer to the [Store docs](https://store.mattramotar.dev).
- **[Circuit](https://github.com/slackhq/Circuit)**: A Compose-driven unidirectional data flow architecture.
- **[SQLDelight](https://github.com/cashapp/sqldelight)**: Provides typesafe SQL operations and generates Kotlin models from SQL schema.
- **[Ktor](https://github.com/ktorio/ktor)**: A framework for building asynchronous servers and clients in connected systems.
- **[Kotlin Inject](https://github.com/evant/kotlin-inject)**: A Kotlin dependency injection library.

## Architecture

### Data Flow

- **Store**: Centralizes data management, handling caching and synchronization between the local database and remote data sources. For detailed usage, refer to the [Store docs](https://store.mattramotar.dev).
- **Repositories**: Offer a clean API for data operations, abstracting underlying data sources.
- **Circuit**: Manages UI state and events in a unidirectional flow.

### Modules

Trails has a modular architecture. This promotes separation of concerns and facilitates scalability and maintainability. Code is organized into feature-based modules and shared libraries.

#### Core

- `core/circuit`: Provides the required abstractions for building a `Circuit` application.

#### Features

- `feat/homeScreen`: Home screen feature showcasing posts.
- `feat/postScreen`: Detailed view of a post.
- `feat/profileScreen`: User profile screen.
- `feat/messagesScreen`: Messaging functionality.
- `feat/searchScreen`: Search interface.

#### Libraries

- `lib/models`: Contains data models shared across the app.
- `lib/db`: Manages local database operations using SQLDelight.
- `lib/repositories`: Handles data fetching and caching using Store.
- `lib/rest`: Defines REST API interfaces and implementations with Ktor.
- `lib/carve`: Trail's design system. It provides UI components and theming for the app.

### Offline-First Approach

Trails adopts an offline-first strategy, prioritizing local data sources before fetching from the network. This approach ensures a smooth user experience even in the absence of network connectivity.

- **Source of Truth**: The local SQLDelight database acts as the single source of truth for app data.
- **Synchronization**: Data updates are synchronized with the backend server when network connectivity is available.
- **Cache Invalidation**: Store's caching mechanism ensures data consistency and freshness by invalidating stale data as needed.

## Getting Started

### Prerequisites

Ensure you have the following installed:

- [Android Studio](https://developer.android.com/studio) or [IntelliJ IDEA](https://www.jetbrains.com/idea/)
- [Java 17](https://www.oracle.com/java/technologies/downloads/)

### Cloning the Repository

Clone the repository to your local machine:

```bash
git clone https://github.com/MobileNativeFoundation/Trails.git
```

### Backend Server

The Trails app communicates with a backend server for data operations. The server provides APIs for fetching and updating data. For this sample app, the backend server is deployed and accessible.

> [!IMPORTANT]  
> Running the backend server locally requires environment variables that are not publicly available. Therefore, you will not be able to run the backend server on your local machine. The app is configured to interact with the production backend server at `https://api.trails.mattramotar.dev`.

#### Configuring the Backend Endpoint

The app is set up to communicate with the production backend server. If you need to point the app to a different backend, modify the base URL in the `TrailsEndpoint` object.

```kotlin
package org.mobilenativefoundation.trails.xplat.lib.rest.impl

internal object TrailsEndpoints {
    private const val ROOT_API_URL = "https://api.trails.mattramotar.dev"
}
```

### Platforms

#### Android

The Android app is functional. To run it, follow these steps:

1. Open the project in Android Studio.
2. Sync Gradle files.
3. Build the project.
4. Run the app.

#### iOS

The iOS app has not been developed yet.

#### Web

The web app has not been developed yet.

## Contributing

Contributions are welcome and can be submitted by forking this project and creating a PR. Since Trails is still in its early stages, please open an issue to discuss substantial changes before starting to work on them.
