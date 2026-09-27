# Changelog

All notable changes to the RPG AI Hub project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [1.0.0-phase1] - 2026-09-26

### Phase 1

- Android foundation
- Compose foundation
- Material 3 theme
- Navigation
- ViewModel foundation
- Testing foundation
- GitHub documentation

#### Added
- Clean Architecture directory and package layout: `core/`, `domain/`, `data/`, and `presentation/`.
- Core application foundation:
  - `AppResult<T>` sealed interface for functional success and error handling.
  - `AppError` extensible sealed hierarchy ready for future network, AI, database, and system failures.
  - `AppLogger` abstraction with configurable log levels and tree delegation.
  - `AppDispatchers` abstraction for coroutines and deterministic unit testing.
- Domain layer:
  - `RpgMode` model encapsulating mode definitions, routes, and planned subsystem roadmaps.
  - `AppThemeMode` enum supporting System Default, Dark Fantasy, and Daylight Realm themes.
  - Repository interfaces `RpgModeRepository` and `AppThemeRepository`.
  - Use cases: `GetRpgModesUseCase`, `GetThemePreferenceUseCase`, and `SetThemePreferenceUseCase`.
- Data layer:
  - `InMemoryAppThemeRepository` providing reactive in-memory theme preference management.
  - `DefaultRpgModeRepository` supplying definitions for Open World RPG, Characters, and RPG + Characters.
- Presentation layer:
  - Centralized Material 3 dark fantasy anime aesthetic theme with cohesive `Color.kt`, `Dimensions.kt`, `Shape.kt`, `Type.kt`, and `Theme.kt`.
  - Centralized navigation routing (`NavRoutes.kt`) and `AppNavHost.kt`.
  - Home dashboard with hero branding, Phase 1 badge, and 3 large interactive mode cards.
  - Settings screen with Appearance preferences, architecture metadata, and versioning.
  - Dedicated placeholder screens for Open World RPG, Characters, and RPG + Characters highlighting planned subsystems.
  - `MainActivity` configured with edge-to-edge layout and reactive theme observation.
- Testing:
  - Unit tests for `AppResult`, `AppLogger`, `GetRpgModesUseCase`, `HomeViewModel`, and `SettingsViewModel`.
  - Robolectric test verifying navigation route configuration.
  - Instrumentation test structure in `androidTest/`.
- Repository & Documentation:
  - `README.md`, `ARCHITECTURE.md`, `SETUP.md`, `CHANGELOG.md`, and clean `.gitignore`.
