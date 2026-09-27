# RPG AI Hub — Architecture Specification

## 1. Architectural Philosophy

RPG AI Hub is structured following **Clean Architecture** and **Modern Android Architecture (MAD)**. The core philosophy is **unidirectional data flow**, **separation of concerns**, and **inversion of control**.

Future systems—such as real-time world simulations, neural character personas, episodic memory graphs, and adaptive narrative rerouters—must plug directly into this architecture without requiring redesign of existing layers.

```
       ┌────────────────────────────────────────────────────────┐
       │                   Presentation Layer                   │
       │    (Compose UI, Themes, ViewModels, NavHost, Screens)  │
       └───────────────────────────┬────────────────────────────┘
                                   │ observes StateFlow & emits Events
                                   ▼
       ┌────────────────────────────────────────────────────────┐
       │                      Domain Layer                      │
       │     (Use Cases, Business Models, Repository Contracts) │
       └───────────────────────────┬────────────────────────────┘
                                   │ relies on interfaces
                                   ▼
       ┌────────────────────────────────────────────────────────┐
       │                       Data Layer                       │
       │   (Repository Impls, Local DB, Remote APIs, Datastores)│
       └───────────────────────────┬────────────────────────────┘
                                   │ uses shared utilities
                                   ▼
       ┌────────────────────────────────────────────────────────┐
       │                       Core Layer                       │
       │    (AppResult, AppError, AppLogger, AppDispatchers)    │
       └────────────────────────────────────────────────────────┘
```

---

## 2. Layer Definitions & Boundaries

### 2.1 Core Layer (`com.rpgaihub.app.core`)
The lowest architectural tier with zero dependencies on outer layers.
- **`result/AppResult.kt`**: Encapsulates operation results with functional utilities (`onSuccess`, `onFailure`, `map`, `getOrElse`).
- **`error/AppError.kt`**: Sealed class defining categorized errors (`NetworkError`, `AiError`, `DatabaseError`, `SerializationError`, `SystemError`, `UnknownError`).
- **`logging/AppLogger.kt`**: Log interface decoupling application logging from `android.util.Log` and enabling log suppression in release builds or test redirection.
- **`dispatchers/AppDispatchers.kt`**: Abstraction over coroutine dispatchers enabling deterministic multi-threaded unit testing.

### 2.2 Domain Layer (`com.rpgaihub.app.domain`)
Pure business logic containing no Android UI dependencies.
- **Models**: `RpgMode`, `AppThemeMode`.
- **Repository Interfaces**: Define the contracts (`AppThemeRepository`, `RpgModeRepository`).
- **Use Cases**: Single-responsibility operators (`GetRpgModesUseCase`, `GetThemePreferenceUseCase`, `SetThemePreferenceUseCase`).

### 2.3 Data Layer (`com.rpgaihub.app.data`)
Concrete implementations of domain repository contracts.
- In Phase 1, `InMemoryAppThemeRepository` and `DefaultRpgModeRepository` provide verified state.
- In Phase 2, this layer hosts Room database entities, DAOs, schema migrations, and cache coordinators.

### 2.4 Presentation Layer (`com.rpgaihub.app.presentation`)
All user interface and presentation orchestration.
- **`theme/`**: Centralized M3 theming (`Color.kt`, `Dimensions.kt`, `Shape.kt`, `Type.kt`, `Theme.kt`).
- **`navigation/`**: Centralized routing (`NavRoutes.kt`) and root controller (`AppNavHost.kt`).
- **`common/`**: Reusable atomic M3 components (`StatusBadge.kt`, `GlowCard.kt`, `RpgTopAppBar.kt`).
- **Screens**:
  - `home/`: Dashboard featuring hero typography, phase badges, and mode selection cards.
  - `settings/`: Appearance preferences, version metadata, architecture summary.
  - `placeholder/`: Dedicated placeholder interface for modules under active construction with architectural extension roadmaps.

---

## 3. Navigation Architecture

Navigation is centralized in `AppNavHost.kt` utilizing Jetpack Navigation Compose:
- **`NavRoutes.Home`** (`"home"`): Entry screen showing primary RPG modes.
- **`NavRoutes.Settings`** (`"settings"`): System configuration and appearance preferences.
- **`NavRoutes.OpenWorldRpg`** (`"open_world_rpg"`): Autonomous sandbox world simulation mode.
- **`NavRoutes.Characters`** (`"characters"`): Persona engine and dynamic character nexus.
- **`NavRoutes.RpgAndCharacters`** (`"rpg_and_characters"`): Fusion mode uniting world simulation and autonomous characters.

Every destination uses `BackHandler` and TopAppBar back affordances for seamless navigation stack transitions.

---

## 4. ViewModel & State Architecture

Every screen follows the standard M3 MVVM pattern:
- **Immutable UI State**: Exposed via Kotlin `StateFlow<T>`.
- **UI Events / Actions**: Handled through explicit `onEvent(event)` methods.
- **Lifecycle Awareness**: Collected in Compose via `collectAsStateWithLifecycle()`.
- **Thread Safety**: Long-running or IO operations execute on `dispatchers.io`.

---

## 5. Future Extension Points

The Phase 1 architecture is engineered specifically to accept future systems without structural breakage:

1. **AI & Model Orchestration (Phase 5)**:
   - Plugs into `core/error/AppError.AiError` for error mapping.
   - Implements new domain repositories: `AiInferenceRepository` and `PromptAssemblyRepository`.
2. **Local Room Database (Phase 2)**:
   - Replaces in-memory repository implementations in `data/` while maintaining identical domain repository contracts.
3. **Multi-Tier Memory & Lore Graph (Phase 3 & 4)**:
   - Integrates via domain use-case boundaries: `QueryCharacterMemoryUseCase`, `DispatchWorldEventUseCase`.
4. **Adaptive Story Rerouting (Phase 6)**:
   - Consumes domain events and exposes updated state flows directly to ViewModels.
