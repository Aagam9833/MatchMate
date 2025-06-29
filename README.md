# MatchMate

Shaadi.com - Android Assignment

## Project Setup Instructions

1. Clone the
   repository: [https://github.com/Aagam9833/MatchMate.git](https://github.com/Aagam9833/MatchMate.git)
2. Open with Android Studio (Giraffe or later).
3. Gradle version: `8.13`, Java version: `17`
4. Run the project via `Run > Run app`

## Library List with Justifications

| Library               | Purpose                                    |
|-----------------------|--------------------------------------------|
| Retrofit              | For performing network requests            |
| Retrofit Converter    | Handles JSON serialization/deserialization |
| Room                  | Local storage for offline capabilities     |
| Hilt                  | Dependency injection for modular design    |
| Glide                 | Efficient image loading and caching        |
| Material Components   | Material Design UI widgets                 |
| ViewModel (lifecycle) | Lifecycle-aware data holder for UI         |

## Architecture Explanation

This project uses **MVVM with Clean Architecture**:

- **Presentation Layer**: UI (`Activity`), `ViewModel`, and UI-specific logic (`Adapter`)
- **Domain Layer**: Business logic via `Model`, `Repository` interfaces, and `UseCases`
- **Data Layer**: Actual implementation of repositories, using Retrofit and Room

Each layer is decoupled using interfaces, ensuring testability and separation of concerns.

## Justification for Added Fields

- `country` in `UserPreferenceModel`: Enhances precision of match scoring.
- `isAccepted` in `ProfileModel`: Tracks user swipe decisions (like/dislike), even offline.
- `matchScore`: Quantifies how closely a profile matches the user’s preferences.

## Match Score Logic

The match score is calculated based on:

- **Gender match**: +25 points
- **Country match**: +25 points
- **Age match**:
  - Within ±3 years: +50 points
  - Within ±10 years: +25 points
  - Beyond that: 0 points

Final score ranges between 0 and 100.

## Offline and Error Handling Strategy

- When online, API is called and response is saved to local DB (Room).
- When offline, Room data is used as fallback.
- Errors are represented using a sealed class `ErrorHandler`:
  - `NetworkError`
  - `ApiError`
  - `ServerError`
  - `DbOperationError`
  - `UnknownError`
- Errors are shown to the user using `Toast` messages for clarity.

## Given More Time

- Add pagination api and better points calculation algorithm
