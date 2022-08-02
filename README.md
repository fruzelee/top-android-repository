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

# v1.0
# Change Log:
- init: initial commit
- docs: added change_log
- chore: updated .gitignore
- feat!: added core features, including repository list and details after the medical emergencies.
- BREAKING CHANGE: repository list with offline browsing support
- BREAKING CHANGE: repository details with offline browsing support
- BREAKING CHANGE: added sorting options by either last update date time or star count
- BREAKING CHANGE: Selected sorting option persists in further app sessions
- fix: updated keyboardOptions for search
- feat: search functionality by keyboardActions done
- update: increase the item height on the repository list page
- update: new drawable for the launcher page
- feat: add launcher icon
- fix: add spaces on the app name
- feat: add custom font support
- docs: added README.md
- docs: updated README.md
- testing: perform the data caching test with Room Database
- testing: evaluate the values of the test results by the Google Truth Library
- docs: added badge on README.md
- docs: added LICENSE
- docs: updated README
- fix: type mismatch on details page
- docs: updated README

# License

```
    Copyright (C) Fazle Rabbi

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
```