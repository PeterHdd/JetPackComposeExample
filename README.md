## JetPost - A Jetpack Compose Application

----------------

The application uses the [clean architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html) with MVVM. In the application, I demonstrate the use of
state and the [navigation-compose](https://developer.android.com/jetpack/compose/navigation) library. Also, the database used is [Cloud Firestore](https://firebase.google.com/docs/firestore),
and the whole application is built using Kotlin. The application also has a similar UI as Twitter. The following jetpack architecture components are used in this application:

- [LiveData](https://developer.android.com/topic/libraries/architecture/livedata)
- [Hilt](https://developer.android.com/training/dependency-injection/hilt-android)
- [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
- [Jetpack Compose](https://developer.android.com/jetpack/compose)

### The Database:

![db](https://user-images.githubusercontent.com/29070108/99959693-225f1b00-2d94-11eb-92f9-b5183a30c74d.PNG)

To run the application, you need to follow the Firebase setup and add your own `google-service.json` file to the project

### Images:

![home-screen](https://user-images.githubusercontent.com/29070108/99959404-a1078880-2d93-11eb-8017-3305bab165d5.jpg) 
![add-post](https://user-images.githubusercontent.com/29070108/99959473-c09eb100-2d93-11eb-897c-6d257cd70a6d.jpg) 
![device-2020-11-23-133005](https://user-images.githubusercontent.com/29070108/99959619-00659880-2d94-11eb-95b9-6ed02a347da0.jpg)
