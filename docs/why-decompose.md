---
template: overrides/main.html
title: Why Decompose?
---

# Why Decompose? 

- Decompose draws clear boundaries between UI and non-UI code, which gives the following benefits:
    - Better separation of concerns
    - Pluggable platform-specific UI (Compose, SwiftUI, React, etc.)
    - Business logic code is testable with pure multiplatform unit tets
- Proper dependency injection (DI) and inversion of control (IoC) via constructor
- Shared navigation logic
- Lifecycle-aware components
- Components in the back stack are not destroyed, they continue working in background without UI
- Components and UI state preservation (mostly useful in Android)
- Instances retaining (aka ViewModels) over configuration changes (mostly useful in Android)
