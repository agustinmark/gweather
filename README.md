
# GWeather  : A GCash Employment Exercise

## 📌 Overview
GWeather is an Android application that fetches real-time weather data using [OpenWeatherMap's Current Weather API](https://openweathermap.org/current). It features **Firebase Authentication**, allowing users to **register, sign in**, and **archive fetched weather data** under their accounts.

## 🚀 Features
- 🌍 **Current Weather Data** – Get live weather updates for current location.
- 🔐 **Firebase Authentication** – Secure user registration and login.
- 📊 **User-Specific Weather Archive** – Store and retrieve weather history for logged-in users.
- 📡 **API Integration** – Seamless connection to OpenWeatherMap for reliable weather data.

## 🏗️ Tech Stack
- **Kotlin** – Primary language for Android development.
- **Jetpack Compose** – Modern UI toolkit for building the app interface.
- **Firebase Authentication** – Secure login and user management.
- **OpenWeatherMap API** – Fetches current weather conditions.
- **Room Database** – Local storage for weather data archiving.


## 🔧 Setup & Installation
### Step 1: Clone the Repository
Run the following command to clone the project:
```sh
git clone https://github.com/agustinmark/gweather.git
```
### Step 2: Open the Project
1. Open Android Studio.
2. Navigate to the cloned project directory and import.

### Step 3: Configure OpenWeatherMap API Key
1. Sign up at [OpenWeatherMap](https://home.openweathermap.org/users/sign_up) and get an API key.
2. Open the following class: src\main\java\com\virent\gweather\data\OpenWeatherMap.kt
3. Replace the API_KEY property with your own API key
```kt
package com.virent.gweather.data  
  
object OpenWeatherMap {  
    const val API_KEY = "PLACEHOLDER"  
  const val BASE_URL = "https://api.openweathermap.org/data/2.5/"  
}
```
### Step 4: Run the App
- Build and run the project on a device or emulator
- Sign in, fetch weather data, and see it archived under your account.