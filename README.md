# InfoKeeper

InfoKeeper is an Android application designed to manage personal information. It features two main screens implemented both in Jetpack Compose and XML layouts.

## Features

1. **Launcher Screen**:
   - **Buttons**:
     - **Open Compose**: Launches the Compose-based implementation of the Info Screen and Info List.
     - **Open Views**: Launches the XML-based implementation of the Info Screen and Info List.

2. **Info List**:
   - **Functionality**: Displays a list of all persons stored in the database. Users can delete individual entries from this list.

3. **Info Screen**:
   - **Functionality**: Allows users to enter personal information including name, job title, age, and gender. This data is saved to a Room database when the "Save" button is clicked.
   - **Validation**: Includes form validation to ensure that inputs are valid before saving.

## Technologies Used

- **Room Database**: For local data storage and retrieval.
- **Koin**: For dependency injection.
- **MVVM + MVI**: 
  - **MVVM (Model-View-ViewModel)**: Architecture for managing UI-related data in a lifecycle-conscious way.
  - **MVI (Model-View-Intent)**: Manages application state by representing it as a single source of truth and handling user actions to produce new states.
- **Jetpack Compose**: For building native UIs with a declarative approach.
- **Coroutines**: For handling asynchronous operations.
- **Material3**: Provides modern UI components and design system.
- **Navigation Components**: For handling navigation within the app, including transitions between screens.

## Architecture

- **MVVM**: Separates UI (View) from business logic (Model) through a ViewModel, managing the interaction between them.
- **MVI**: Handles state management with a single source of truth (Model) and processes user actions (Intents) to update the state.

## Screens

### Info Screen

- **Purpose**: Collect and save personal data.
- **Components**:
  - Input fields: Name, Job Title, Age, Gender.
  - Save button for storing data in the Room database.
  - Validation messages for input errors.
  - Implemented using Jetpack Compose and XML.

### Screenshots

#### Compose Implementation
<img src="https://drive.google.com/uc?export=view&id=1BgLPZRWCnhx1NGnq_GQVxEXu1NxKpGqf" width="400" alt="Compose Info Screen">

#### XML Implementation
<img src="https://drive.google.com/uc?export=view&id=1Bhxr1rTTgAOAWeycWuxLrDq1bCFq58KF" width="400" alt="XML Info Screen">


### Info List

- **Purpose**: Display and manage a list of all persons stored in the database.
- **Components**:
  - List of persons with a delete option for each entry.
  - Implemented using Jetpack Compose and XML.


### Screenshots

#### Compose Implementation
<img src="https://drive.google.com/uc?export=view&id=1Bfd07NJesNtK6jFfGG0Dajvi2y6a3n3R" width="400" alt="Compose Info List">

#### XML Implementation
<img src="https://drive.google.com/uc?export=view&id=1BjLhfXR_-5bgFiRZ4Q-o0TNFvKPKVZUa" width="400" alt="XML Info List">
