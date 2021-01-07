---
template: overrides/main.html
title: Getting started
---

# Getting started

## About 

Kotlin Multiplatform lifecycle-aware business logic components (aka BLoCs) with routing functionality and pluggable UI (Android Views, Jetpack Compose, SwiftUI, JS React, etc.) This project is inspired by [Badoos RIBs](https://github.com/badoo/RIBs) fork of the [Uber RIBs](https://github.com/uber/RIBs) framework.

Supported targets:
- Android
- JVM
- iosX64, iosArm64
- JavaScript

## Setup

Decompose is published to Bintray, the repository is synchronized with JCenter.
Make sure you have the JCenter repository specified in your build.gradle:

```groovy
repositories {
    jcenter()
}
```

Add Decompose dependency to your build.gradle:

```groovy
implementation "com.arkivanov.decompose:decompose:<version>"
```

Add extensions for Android Views to your Android build.gradle:

```groovy
implementation "com.arkivanov.decompose:extensions-android:<version>"
```

Add extensions for Jetpack Compose to your Android build.gradle:

```groovy
implementation "com.arkivanov.decompose:extensions-compose-jetpack:<version>"
```

Add extensions for JetBrains Compose to your Android/JVM/Multiplatform build.gradle:

```groovy
implementation "com.arkivanov.decompose:extensions-compose-jetbrains:<version>"
```
