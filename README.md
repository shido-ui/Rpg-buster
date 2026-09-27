# RPG AI Hub

RPG AI Hub is an Android-first platform engineered to serve as a high-performance foundation for next-generation AI-driven fantasy RPGs, autonomous character personas, and emergent living-world simulations.

> **Current Status**: **Phase 1 — Android Foundation**. This release establishes the production architecture, presentation system, centralized navigation, Material 3 theming, domain boundaries, error handling, and test harness. Domain engines (world simulation, local/cloud AI inference, lore graphs, multi-tier memory) will be integrated in subsequent phases.

---

## Architecture Overview

RPG AI Hub is designed strictly according to Clean Architecture principles:

```
UI (Jetpack Compose + Material 3)
  ↓
Presentation Layer (ViewModels + StateFlow + UI Events)
  ↓
Domain Layer (Use Cases + Models + Repository Interfaces)
  ↓
Data Layer (Repositories + Data Sources + Cache)
  ↓
Core Layer (Result + Error Handling + Logging + Dispatchers)
```

The system ensures that future additions—such as local LLMs, vector memory stores, world clocks, quest rerouters, and multi-agent relationship matrices—can plug in without altering the foundational infrastructure.

---

## Technology Stack

- **Platform**: Android (Kotlin-first)
- **UI Toolkit**: Jetpack Compose with Material Design 3 (M3)
- **Architecture**: Clean Architecture + MVVM
- **Language**: Kotlin 2.2.x
- **Build System**: Gradle with Kotlin DSL (`.gradle.kts`) and Version Catalog (`libs.versions.toml`)
- **Navigation**: Jetpack Navigation Compose (centralized routing)
- **State Management**: Kotlin Coroutines & `StateFlow` / `collectAsStateWithLifecycle`
- **Error Handling**: Standardized `AppResult<T>` and `AppError` sealed hierarchy
- **Logging**: Configurable `AppLogger` abstraction with log-level gating
- **Testing**: JUnit 4, Kotlinx Coroutines Test, Robolectric, AndroidX Test

---

## Project Structure

```
├── app/
│   ├── build.gradle.kts
│   └── src/
│       ├── main/
│       │   ├── AndroidManifest.xml
│       │   ├── java/com/rpgaihub/app/
│       │   │   ├── core/               # Result, Error types, Logger, Dispatchers
│       │   │   ├── domain/             # Entities, Models, Repository interfaces, Use Cases
│       │   │   ├── data/               # Repository implementations, Data sources
│       │   │   ├── presentation/       # Theme, Navigation, Screens, ViewModels, Components
│       │   │   └── MainActivity.kt     # App entry point & Edge-to-Edge root
│       │   └── res/                    # Drawables, Strings, Themes, XML configurations
│       ├── test/                       # Unit tests & Robolectric JVM tests
│       └── androidTest/                # Android instrumentation test structure
├── docs/
│   └── ARCHITECTURE.md                 # Deep-dive architecture specification
├── ARCHITECTURE.md                     # Architecture specification
├── CHANGELOG.md                        # Milestone release log
├── SETUP.md                            # Development environment & build setup
├── README.md                           # Project overview (this file)
└── .gitignore                          # Repository hygiene & credential exclusion
```

---

## Build & Run Instructions

### Prerequisites
- **JDK**: Java Development Kit (JDK 17 or JDK 21 recommended)
- **Android SDK**: `compileSdk = 36`, `minSdk = 24`, `targetSdk = 36`
- **Gradle**: Gradle 8.11+ / AGP 9.1.x

### Build Debug APK
Run the following command from the project root:
```bash
gradle :app:assembleDebug
```
The output APK is generated at:
`app/build/outputs/apk/debug/app-debug.apk`

### Execute Unit & Robolectric Tests
```bash
gradle :app:testDebugUnitTest
```

### Run on Device or Emulator
Connect an Android device with USB debugging enabled or launch an Android Virtual Device (AVD), then execute:
```bash
gradle :app:installDebug
```

---

## Current Scope & Limitations (Phase 1)

Phase 1 focuses exclusively on establishing a rock-solid, production-ready foundation:
- **Included**:
  - Centralized Navigation graph with type-safe route definitions.
  - Dark fantasy anime-inspired Material 3 theme with dynamic theme switching (Dark, Light, System Default).
  - Home dashboard with 3 primary mode cards: **Open World RPG**, **Characters**, and **RPG + Characters**.
  - Settings screen with Appearance preferences, architecture metadata, and versioning.
  - Informative placeholder screens for modes under construction detailing planned subsystem integration.
  - Decoupled `AppResult` and `AppError` abstractions.
  - Zero hardcoded credentials or mock fake AI engines pretending to function.
- **Explicitly Excluded from Phase 1** (scheduled for future phases):
  - AI API integrations and local model weights.
  - Character database and persistent lore engines.
  - Memory architectures and vector search.
  - World simulation state machines and tick loops.
  - RPG mechanics (dice, inventories, combat, quests).

---

## Development Roadmap

- [x] **Phase 1: Android Foundation** (Current)
  - Clean Architecture layers, Compose M3 Theme, Centralized Navigation, ViewModels, Result/Error foundation, Robolectric test suite.
- [ ] **Phase 2: Core Data Model & Database Architecture**
  - Room entity schemas, SQLite local storage, character persistence, lore graph storage, migrations.
- [ ] **Phase 3: Persona & Multi-Tier Memory Engine**
  - Episodic memory, working context window management, character motivations, psychological states.
- [ ] **Phase 4: World Simulation & Event Dispatcher**
  - Spatial node mapping, reactive world state, tick loops, factions, environmental dynamics.
- [ ] **Phase 5: AI Engine & Model Orchestration**
  - Model provider abstraction (local & cloud inference), context assembly, stream parsing, token budgets.
- [ ] **Phase 6: Narrative & Quest Rerouter**
  - Unscripted story rerouting, player agency handler, emergent relationship progression.
