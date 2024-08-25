# Islami App

## Overview
Islami is a Android application designed to enrich the spiritual journey of Muslims. It offers a seamless platform for Quran recitation, Hadith exploration, and Quranic radio streaming, all within a single, user-friendly interface.

## Key Features
- **Quran Recitation:** Easy-to-navigate Quran reading interface
- **Hadith Collection:** Access to a vast repository of authentic Hadiths
- **Digital Sebha (Tasbeeh):** Electronic counter for dhikr and supplications
- **Quran Radio:** Stream Quranic recitations from various reciters


## Technologies and Architecture
- **Language:** Kotlin
- **Architecture:** MVVM (Model-View-ViewModel)
- **Asynchronous Programming:** Coroutines
- **UI Navigation:** Navigation Component
- **Networking:** Retrofit for API integration
- **Media Playback:** Media Player for Quran radio
- **Background Processing:** Services for continuous radio playback
- **User Notifications:** For radio playback player notification
- **Theme:** Light/Night theme mode

## Key Implementations
- Clean Architecture principles for separation of concerns
- ViewModel for managing UI-related data
- LiveData for reactive and lifecycle-aware data holding
- Coroutines for managing background tasks and asynchronous operations
- Retrofit for efficient API calls and data fetching
- MediaPlayer for streaming Quran radio
- Services for background audio playback
- Navigation Component for seamless in-app navigation
- Custom notifications for For radio playback player notification

## Architecture Overview
The app follows the MVVM architecture pattern:
- **Model:** Represents the data and business logic
- **View:** The UI layer (Activities and Fragments)
- **ViewModel:** Acts as a bridge between the Model and View, managing UI-related data

## Screenshots
<table>
  <tr>
    <td><img src="https://github.com/user-attachments/assets/8068a6f3-3c6f-4c25-9b97-6a7f59630826" width="200"></td>
  </tr>
   <tr>
    <td><img src="https://github.com/user-attachments/assets/3a498ad4-09b9-495a-a7be-b391b1f9fa3b" width="200"></td>
    <td><img src="https://github.com/user-attachments/assets/fc7a293b-f59c-434c-90e5-ba7cc45e2df3" width="200"></td>
  </tr>
  <tr>
    <td><img src="https://github.com/user-attachments/assets/a61325ca-f0da-4d2e-82ee-000c90baa975" width="200"></td>
    <td><img src="https://github.com/user-attachments/assets/23a9898e-c596-4173-8ebc-fb7f60148332" width="200"></td>
  </tr>
  <tr>
    <td><img src="https://github.com/user-attachments/assets/00e2a5a8-38f9-4c7c-b93a-d6ca2582bf6a" width="200"></td>
    <td><img src="https://github.com/user-attachments/assets/fb1c3c3e-8d25-4950-91db-d38dff4334ab" width="200"></td>
  </tr>
  <tr>
    <td><img src="https://github.com/user-attachments/assets/03dd8d55-f2a7-4a26-9bfc-6de710b38b6c" width="200"></td>
  </tr>
</table>

