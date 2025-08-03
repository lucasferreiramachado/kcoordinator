# KCoordinator:
#### io.github.lucasferreiramachado:kcoordinator: 1.1.0

A lightweight, cross-platform Coordinator library, written in pure Kotlin. Can easily support other navigation libraries or a custom navigation logic.

# KCoordinator Navigation Compose:
#### io.github.lucasferreiramachado:kcoordinator-navigation-compose: 1.1.0

A convenient and powerful coordinator pattern library designed for Navigation Compose library.

## Components

1. **KCoordinatorAction: Coordinator Action**: Actions that can be handled by navigation flow logic in the Coordinator. For example, start the authentication flow,  show home screen, go to back screen or another screen of navigation flow.

    ```kotlin
    interface KCoordinatorAction
    ```

2. **KCoordinator: Core Coordinator**: The Coordinator core interface in pure Kotlin. Each coordinator can set up navigation, trigger and handle actions and also holds a reference to its parent coordinator.

   ```kotlin
    interface KCoordinator<T: KCoordinatorAction> {
        
        val parent: KCoordinator<*>?
        
        fun trigger(action: KCoordinatorAction) {
            // Default implementation: do not override
        }
        
        fun handle(action: T)
    }
    ```

3. **ComposeKCoordinator: Navigation Compose Coordinator**: The Compose Coordinator interface. Each Compose Coordinator can set up navigation, trigger and handle actions, setting up navigation compose and also holds a reference to its parent coordinator.
    ```kotlin
    interface ComposeKCoordinator<T: KCoordinatorAction> : KCoordinator<T> {
        val parent: KCoordinator<*>?
   
        fun trigger(action: KCoordinatorAction) {
            // Default implementation: do not override
        }
   
        fun handle(action: T)
       
        fun setupNavigation(
           navGraphBuilder: NavGraphBuilder,
           navHostController: NavHostController
        )
    }
    ```

4. **RootComposeKCoordinator: Root Compose Coordinator**: Root Compose Coordinator interface. It initializes the navigation graph and handles global navigation actions and flows.
    ```kotlin
    interface ComposeKCoordinator<T: KCoordinatorAction> : KCoordinator<T> {
       
        fun handle(action: T)
   
        fun setupNavigation(
            initialAction: Action,
            navGraphBuilder: NavGraphBuilder,
            navHostController: NavHostController
         )
       
        @Composable
        fun start(
           startDestination: Any,
           initialAction: T
        ) {
            // Default implementation: override it when you need a custom NavHost 
            val navHostController = rememberNavController()
            NavHost(
                startDestination = startDestination,
                navController = navHostController
            ) {
                setupNavigation(
                    initialAction,
                    this,
                    navHostController
                )
            }
        }   
    }
    ```

# KCoordinator Navigation Compose Example:

[examples/multiplatform](https://github.com/lucasferreiramachado/kcoordinator/tree/main/examples/multiplatform)

This repository showcases a flexible navigation approach for Jetpack Compose applications by implementing the Coordinator pattern. This pattern simplifies the management of complex navigation flows and screen transitions, offering a well-structured way to handle navigation across different screens in an Android ou Multiplatorm Compose apps.

## How It Works

### Sample App Flow

- **AppCoordinator**: Manages the app's overall navigation flow and initializes either the authentication flow or the main flow based on the current state.

- **AuthCoordinator**: Manages the authentication flow, including login and forget password screens.

- **HomeCoordinator**: Manages the main flow, including the home screen and settings screens.

## Usage

### Setting Up Coordinators

1. **AppCoordinator Implementation**:

   ```kotlin
    sealed class AppCoordinatorAction: KCoordinatorAction {
        data object StartLoginFlow : AppCoordinatorAction()
        data object StartHomeFlow : AppCoordinatorAction()
    }
   ```

   ```kotlin
    class AppCoordinator(
        override val parent: Coordinator<*>? = null
    ) : ComposeKCoordinator<AppCoordinatorAction> {

       private val authCoordinator: AuthCoordinator by lazy { AuthCoordinator(this) }
       private val homeCoordinator: HomeCoordinator by lazy { HomeCoordinator(this) }

       fun setupNavigation(
           navGraphBuilder: NavGraphBuilder,
           navHostController: NavHostController
       ) {
           authCoordinator.setupNavigation(navGraphBuilder, navHostController)
           homeCoordinator.setupNavigation(navGraphBuilder, navHostController)
       }

       override fun handle(action: AppCoordinatorAction) { 
            when (action) {
                is AppCoordinatorAction.StartLoginFlow -> {
                    authCoordinator.trigger(AuthCoordinatorAction.ShowLoginScreen)
                }
                is AppCoordinatorAction.StartHomeFlow -> {
                    homeCoordinator.trigger(HomeKCoordinatorAction.ShowHomeScreen)
                }
            }
       }
   }
   ```

2. **AuthCoordinator Implementation**:

   ```kotlin
    sealed class AuthCoordinatorAction: KCoordinatorAction {
        data object ShowLoginScreen : AuthCoordinatorAction()
        // Define other auth-related actions here
    }
   ```

   ```kotlin
    class AuthCoordinator(
        override val parent: Coordinator<*>
    ) : ComposeKCoordinator<AuthCoordinatorAction> {
        
        override fun setupNavigation(
            navGraphBuilder: NavGraphBuilder,
            navHostController: NavHostController
        ) {
            navGraphBuilder.apply {
                LoginScreen(coordinator = this@AuthCoordinator)
            }
        }
        
           override fun handle(action: AuthCoordinatorAction) {
            // Handle auth-related actions here
        }
    }
   ```

3. **HomeCoordinator Implementation**:
   ```kotlin
    sealed class HomeCoordinatorAction: KCoordinatorAction {
        data object ShowHomeScreen : HomeCoordinatorAction()
        // Define other main-related actions here
    }
   ```

   ```kotlin
    class HomeCoordinator(
        override val parent: Coordinator<*>
    ) : ComposeKCoordinator<HomeCoordinatorAction> {
        
        override fun setupNavigation(
            navGraphBuilder: NavGraphBuilder,
            navHostController: NavHostController
        ) {
            navGraphBuilder.apply {
                HomeScreen(coordinator = this@AuthCoordinator)
            }    
        }
   
        override fun handle(action: HomeCoordinatorAction) {
            // Handle main-related actions here
        }
    }
   ```

### Launching the App

In your `Compose App` or `MainActivity`, to initialize the `AppCoordinator` and start the appropriate flow:


   ```kotlin
    @Composable
fun App(
   startDestination: Any = AppNavigationRoute.SplashScreen,
   initialAction: AppCoordinatorAction = AppCoordinatorAction.StartLoginFlow,
) {
   MaterialTheme {
      val appCoordinator = AppCoordinator()
      appCoordinator.start(
         startDestination,
         initialAction = initialAction
      )
   }
}
   ```

## Conclusion

By decoupling navigation logic from UI components, it simplifies the management of complex navigation flows. 
The Coordinator pattern offers a scalable and flexible architecture benefits for large applications that involve multiple navigation flows and nested navigation structures.
