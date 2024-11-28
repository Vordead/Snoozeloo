# Snoozeloo

Snoozeloo is an advanced alarm application built using modern Android development practices. It allows users to set, manage, and customize alarms with ease. The app is designed with a focus on user experience and reliability.

## Features

- Set and manage multiple alarms
- Customize alarm times and repeat days
- Enable or disable alarms
- View remaining time until the next alarm
- Deep link support for alarm actions

## Tech Stack

- **Kotlin**: The primary programming language used for the app.
- **Jetpack Compose**: For building the UI in a declarative manner.
- **Hilt**: For dependency injection.
- **Coroutines**: For asynchronous programming.
- **Room**: For local database management.
- **Navigation Component**: For handling navigation and deep links.
- **MVI (Model-View-Intent)**: For state management and unidirectional data flow.
- **Broadcast Receivers**: For handling alarm events.
- **Deep Links**: For navigating to specific parts of the app.
- **AlarmManager**: For scheduling and managing alarms.
- **Notifications with Channel**: For displaying alarm notifications.
- **PendingIntent**: For performing actions at a later time.
- **Intent Filters**: For specifying the types of intents the app can handle.
- **Type-safe Navigation**: For ensuring safe and reliable navigation within the app.

## Architecture

The app follows the MVI (Model-View-Intent) architecture pattern, which ensures a clear separation of concerns and makes the codebase more maintainable and testable.

- **Model**: Represents the data and business logic of the app.
- **View**: Displays the data and handles user interactions.
- **Intent**: Represents the user's actions and intents.

## Screens

| Screen Name         | Screenshot 1 | Screenshot 2 |
|---------------------|--------------|--------------|
| Alarm List          |  ![alarm_list1](https://github.com/user-attachments/assets/88c583db-0cbe-4365-a01f-f4b0377b1368) | ![alarm_list_empty](https://github.com/user-attachments/assets/17db8e6f-abd9-45ac-ad62-add3f2ce9ee6)
| Alarm Detail        | ![alarm_detail1](https://github.com/user-attachments/assets/037d99a8-a24d-4997-99b7-e503934dc37a) | ![alarm_detail2](https://github.com/user-attachments/assets/5242ebea-bc2d-4141-b80d-342be51fe215)|
| Alarm Ringing       |  ![alarm_ringing](https://github.com/user-attachments/assets/bc4ec7e6-d67f-4606-8874-59f13c2aad82) | ![alarm_ringing2](https://github.com/user-attachments/assets/3659c08b-f58b-44e9-b3a4-918533006a3f)

## Getting Started

To get a local copy up and running, follow these simple steps:

1. **Clone the repository:**
    ```sh
    git clone https://github.com/vordead/snoozeloo.git
    ```

2. **Open the project in Android Studio:**
    - Open Android Studio.
    - Select "Open an existing Android Studio project".
    - Navigate to the cloned repository and select it.

3. **Build and run the project:**
    - Click on the "Run" button in Android Studio to build and run the app on an emulator or a physical device.


## License

Distributed under the MIT License. See `LICENSE` for more information.

Project Link: [https://github.com/vordead/snoozeloo](https://github.com/vordead/snoozeloo)
