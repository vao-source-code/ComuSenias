ComuSenias

## 📖 Descripción del Proyecto
- Aplicación movíl para que los niños se puedan comunicar y poder funcionar como parte de una sociedad y manejo de sus vidas, deben aprender lo más pronto posible.
    Facilitarles el aprendizaje con juegos que les enseñen el lenguaje de señas a temprana edad es crucial para que se puedan manejar con el mismo.
    El aprendizaje con Juegos interactivos es una manera muy eficiente para ayudar a los niños a aprender el lenguaje de señas y poder comunicarse con el mismo a temprana edad. 

## 🔧 Instalación

1. `git clone https://github.com/vao-source-code/ComuSenias.git`
2. Ejecutar Gradle Sync
3. Regenera las Clases de Hilt

## 🔗 Dependencias links

- [Hilt](https://developer.android.com/training/dependency-injection/hilt-android?hl=es-419)
- [Jetpack Compose](https://developer.android.com/jetpack/compose/setup?hl=es-419)
- [Jetpack Navigation](https://developer.android.com/jetpack/compose/navigation?hl=es-419)
- [Firebase Analytics](https://firebase.google.com/docs/analytics/get-started?hl=es-419&platform=web)
- [Firebase Database](https://firebase.google.com/docs/database/android/start?hl=es-419)
- [Firebase Firestore](https://firebase.google.com/docs/storage/android/start?hl=es)
- [Retrofit](https://square.github.io/retrofit/)
- [Coroutines](https://developer.android.com/kotlin/coroutines?hl=es-419)
- [UnitTesting](https://developer.android.com/training/testing/local-tests)
- [IntegrationTesting](https://developer.android.com/training/testing/instrumented-tests/ui-tests)
- [MockkTesting](https://mockk.io/ANDROID.html)

## 🌲 Ramas

- `main` - Version de desarrollo.
- `feature/...` - Tarea en desarrollo.
- `tag-version` - Liberada a PlayStore.

## 🏗️📱 Arquitectura de desarrollo 
- Arquitectura que definimos por concepto
![arquitecture MVPC](https://github.com/vao-source-code/ComuSenias/assets/72049474/cea6de82-9517-418b-9ad1-925deb00a72c)
- Arquitectura de las carpetas en el sector principal de la app        
java/com/example/comusenias
                ├── constants
                ├── core
                │   └── di
                ├── data
                │   └── repositories
                │   
                ├── domain
                │	├── library
                │   ├── models
                │   │    ├── bottomNavigation
                │   │    ├── model
                │   │    └── state
                │   │    
                │   ├── repositories
                │   └── use_cases
                │          
                ├── presentation
                │   ├── activities
                │   ├── component
                │   ├── navigation
                │   ├── ui
                │   │   └── theme
                │   └── view_model
                ├── ComuseniasApp.kt	

  ## 🧹📦 The Clean Code Blog
    La arquitectura Clean Code MVVM es una implementación de estos principios en el contexto de aplicaciones móviles.
    Esta arquitectura separa la lógica de la interfaz de usuario (UI) de la lógica de negocio, lo que facilita la comprensión, la modificación, la prueba y el mantenimiento del código.
  - [GUIA](https://medium.com/android-dev-hacks/detailed-guide-on-android-clean-architecture-9eab262a9011)
    PARTES QUE DEFINIMOS: 
        CONSTANTS: Esta carpeta contiene constantes que se utilizan en toda la aplicación, como valores de cadena, IDs de recursos, etc.
        CORE: Esta carpeta contiene el código que es esencial para el funcionamiento de la aplicación, pero que no es específico de ninguna capa en particular. Esto incluye clases como el inyector de dependencias y el despachador.
        DATA: Esta carpeta contiene el código que interactúa con las fuentes de datos de la aplicación. Esto incluye clases como repositorios y modelos de datos.
        DOMAIN: Esta carpeta contiene el código que implementa la lógica de negocio de la aplicación. Esto incluye clases como casos de uso, modelos de dominio y repositorios de dominio.
        PRESENTATION: Esta carpeta contiene el código que implementa la interfaz de usuario de la aplicación. Esto incluye clases como actividades, screen y componentes de jetpack compose.
     
  ## 🧹🏛️ MVVM (Model-View-ViewModel) Patron de diseño
  El patrón de arquitectura MVVM, también conocido como Model View ViewModel, se refiere a un modelo de diseño que tiene el objetivo para llevar a cabo la separación del apartado de la interfaz de usuario (View) de la parte lógica (Model).
  -  MODEL: El modelo representa los datos de la aplicación y su lógica de negocio.
  -  VIEW: La vista representa la interfaz de usuario de la aplicación.
  -  VIEWMODEL: El ViewModel es el intermediario entre la vista y el modelo. Se encarga de convertir los datos del modelo en un formato que la vista pueda entender y de responder a las acciones del usuario.


## ⚙️🚀 CI/CD
- 🔄🛠️ CI
     El flujo de trabajo tiene un solo trabajo, llamado build, que se ejecuta en una máquina virtual de Ubuntu de última versión. Los pasos del trabajo son los siguientes:
        uses: actions/checkout@v3: Este paso clona el repositorio en la máquina virtual.
        name: set up JDK 17: Este paso configura la máquina virtual para usar la versión 17 del JDK de Temurin.
        name: Setup Gradle: Este paso configura Gradle en la máquina virtual.
        name: Build app: Este paso ejecuta el comando ./gradlew assemble para construir la aplicación.
- 🚀📦 CD
     El flujo de trabajo tiene un solo trabajo, llamado test, que se ejecuta en una máquina virtual de Ubuntu de última versión. Los pasos del trabajo son los siguientes:
        uses: actions/checkout@v3: Este paso clona el repositorio en la máquina virtual.
        name: set up JDK 17: Este paso configura la máquina virtual para usar la versión 17 del JDK de Temurin.
        name: Unit Test: Este paso ejecuta el comando ./gradlew testDebugUnitTest para ejecutar las pruebas unitarias de la aplicación.
        name: Android Test Report: Este paso genera un informe de las pruebas de Android.
        if: ${{ always() }} garantiza que el informe de las pruebas de Android se genere incluso si las pruebas fallan.

    
## ⚠️ Things to know

## 💻 Developers

- [Victor Orue](https://github.com/vao-source-code) (@vao-source-code)
- []

-- NO TOCAR

--- Dios está en los detalles ---
