# Welcome to Top Android Repositories

[![Platform](https://img.shields.io/badge/platform-Android-yellow.svg)](https://www.android.com)
[![License](https://img.shields.io/badge/license-Apache%202-4EB1BA.svg?style=flat-square)](https://www.apache.org/licenses/LICENSE-2.0.html)
[![Gradle Version](https://img.shields.io/badge/gradle-7.2.1-green.svg)](https://docs.gradle.org/current/release-notes)
[![Awesome Kotlin Badge](https://kotlin.link/awesome-kotlin.svg)](https://github.com/KotlinBy/awesome-kotlin)
[![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-1.1.1-blueviolet)](https://developer.android.com/jetpack/androidx/releases/compose)

# Top Android Repositories

A test application which shows the top 50 top most starred github repositories by searching with
the "Android" keyword.

# Core Features:
- A list of repositories page where list of repositories showed.
- List fetched from https://api.github.com/ api using "Android" as query keyword.
- List can be sorted by either last update date time or star count.
- Selected sorting option persists in further app sessions.
- A repo details page, to which navigated by clicking on an item from the list.
- Details page shows repo owner's name, photo, repository's description, last update date
  time in month-day-year hour:seconds format, each field in 2 digit numbers.
- The repository which loaded once, is saved for offline browsing.
