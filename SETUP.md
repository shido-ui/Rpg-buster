# RPG AI Hub — Setup & Build Guide

This document provides setup instructions for building, testing, and developing RPG AI Hub.

---

## 1. System Requirements

- **Operating System**: macOS, Linux, or Windows 10/11 (with WSL2 or PowerShell)
- **Java Development Kit (JDK)**: JDK 17 (recommended) or JDK 21
  - Verify with: `java -version`
  - Ensure `JAVA_HOME` environment variable points to your JDK directory
- **Android SDK**:
  - `compileSdk`: 36
  - `minSdk`: 24 (Android 7.0 Nougat)
  - `targetSdk`: 36 (Android 15+)
  - Android SDK Build-Tools 36.x

---

## 2. Importing into an Android IDE

### Android Studio (Recommended)
1. Install **Android Studio Ladybug (2024.2+)** or later.
2. Open Android Studio and select **Open** (or **File > Open**).
3. Navigate to and select the root directory of this repository (`rpg-ai-hub`).
4. Wait for Gradle Sync to complete. The IDE will download required dependencies declared in `gradle/libs.versions.toml`.
5. Select a device or Android Virtual Device (AVD) running API 24 or higher.
6. Click **Run > Run 'app'** (`Shift + F10`) to build and launch the application.

---

## 3. Command Line Build Process

### Build Debug APK
To compile and assemble the debug application package:
```bash
gradle :app:assembleDebug
```
The resulting APK will be located at:
```
app/build/outputs/apk/debug/app-debug.apk
```

### Build Release Bundle / APK
```bash
gradle :app:assembleRelease
```

---

## 4. Running Tests

### JVM Unit & Robolectric Tests
To run all local unit tests (Domain use cases, ViewModels, Core Result/Logger, Robolectric navigation tests):
```bash
gradle :app:testDebugUnitTest
```
Test reports will be generated at:
```
app/build/reports/tests/testDebugUnitTest/index.html
```

### Android Instrumentation Tests
Connect an active Android device or emulator with USB debugging enabled:
```bash
gradle :app:connectedDebugAndroidTest
```

---

## 5. Clean & Rebuild
If you need to perform a clean compilation:
```bash
gradle clean :app:assembleDebug
```
