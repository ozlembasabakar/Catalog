# Catalog

This project was created with the goal of learning about design, architecture, and modularization. Built entirely with Jetpack Compose and Kotlin.

It is a work in progress :hammer_and_wrench:

## UI

![2](https://user-images.githubusercontent.com/53402156/229770608-730a4098-ca66-454f-8017-4e39d4e4b813.png)

The Material 3 principles were used in the design of the application. You can find the design file [here](https://www.figma.com/file/6BTl3rrwMpKQf6Q5l6kfxv/CatalogDesign?node-id=0%3A1&t=7rhpcgJTDwWw0hj6-1).

Jetpack Compose was mainly used for creating screens and UI components.

The application uses a single theme. It takes use of the colors selected for the design. Supports dark mode.


The UI components job is to display content on the screen. It is not necessary to understand its origins or the operations it completed. 
**State hoisting** was used when developing UI components in order to do this. Composable can now be reused as needed.

Applying state hoisting to composable requires the addition of two parameters.
- Current value to be shown 
- An event that the updates the current value

In **UDF (One Way Data Flow)** design pattern, the state goes down while the event goes up. **State hoisting is the main pattern used in UDF architecture.** 
*It provides a single source of truth.*

State that is hoisted this way has some important properties:

**- Single source of truth**: By moving state instead of duplicating it, we're ensuring there's only one source of truth. This helps avoid bugs.

**- Encapsulated**: Only stateful composables can modify their state. It's completely internal.

**- Shareable**: Hoisted state can be shared with multiple composables.

**- Interceptable**: callers to the stateless composables can decide to ignore or modify events before changing the state.

**- Decoupled**: the state for the stateless composable may be stored anywhere. For example, it's now possible to move name into a ViewModel.

> MVVM architecture (recommended by Android Developers) was used in the application. ViewModel was used as screen level state holder.

## Architecture

![architecture](https://user-images.githubusercontent.com/53402156/229772877-b640d428-aa59-4866-89f5-007ba73fd9d2.png)

The steps after the application is launched are as follows.

1. The application launches.
2. To get data about the "Category," a request is sent using the RetrofitNetworkApi.
3. If the request is successful, the information is written to the Room Database in CategoryRepository.
4. Tabs component gets data from TabsViewModel. TabsViewModel gets the information from the Repository.
5. In TabsViewModel, selected category information store with mutableStateOf().
6. To get the post information, a request using the RetrofitNetworkApi is sent with this information as a parameter.
7. If the request is successful, the information is written to the Room Database in PostRepository.
8. Displays this data in HomeScreen after retrieving it from the PostViewModel. PostViewModel gets the information from the Repository.

The application uses the **_offline-first_** approach. In other words, the information that has been retrieved from the database is always shown on the screen.

If the request is successful and there is an internet connection when the application is opened, this data is written to the database.

The database's data is not used when the SwipeRefresh feature is used. The new request is sent with the selected category. And again, this data is written in the database. In other words, the application won't crash if this feature is used when offline. Just this feature wouldn't be available.

## Modularization

Modules represent either shared behavior or features. 

The app won’t work without the shared behavior modules, so they’re known as core modules.

Feature modules should depend only on core modules, and never on other feature modules.

### :classical_building:**Application module**
When you create a new Android project, you get a default app module automatically. The application only needs one application module. The others will all be library modules. The app module depends on feature module and core module.

### :recycle:**Feature modules**
Feature module is isolated from the others according to business logic and contains a specific feature. In general, it includes every layer of your architecture.  But it can also be small and void of any layer. A feature module can only have dependencies on the other feature modules or on the core module.

### :gear:**Core module**
Core module contains code and specific dependencies between other modules in the app. That module shouldn’t depend on feature nor app module.

### **Benefits of using modularization**

**_Scalability_**, In a tightly coupled codebase, a single change can result in a chain reaction of changes. A properly modularized project will embrace the separation of concerns principle. 

**_Enabling work in parallel_**, Modularization minimizes version control conflicts and makes it possible for engineers on larger teams to work more productively in parallel.

>**In this project, ***version catalogs*** are used. this is a safe dependency list created by Gradle during synchronization. It is a central place to declare all your dependencies and can be used by all modules in a project.**

**_Faster compilation_**, Gradle enables the parallel compiling of the modules when the application includes of more than one. For instance, if the code in module B changes but not in module A, just module B (and any dependent modules) will compile. However, if the application only includes one module, a compilation is output on top of it whenever a piece of code changes rather than using the previously built code. This means recompiling.

**_Ownership_**, A module may have a specific owner who is in charge of updating the module's tests and code, resolving issues, and monitoring changes.

**_Encapsulation_**, Isolated code is easier to read, understand, test and maintain.

**_Reduced build time_**, Decreased build times can be achieved through using Gradle's parallel and iterative build features.

**_Dynamic delivery_**, Modularization is necessary for Play Feature Delivery, which enables you to deliver or download specific features of your app only when needed.
###
>**80/20 Rule**
>
>It makes no sense for people to download the entire program if 80% of users only use 20% of it. As a result, it makes sense, that the core module, which is downloaded together with the program, contains the first features that the user will see after logging in. It makes sense that features that are accessed through submenus or that are only rarely used can be downloaded later on in the dynamic module structure.
###
**_Reusability_**, Appropriate modularization makes it possible to share code and create many apps from the same base on several platforms.

###

>Modularization is the practice of breaking the concept of a monolithic, one-module codebase into loosely coupled, self contained modules.

***‘By feature & layer’*** strategy is used in this project. **‘package by feature’** is like microservice architecture. Each package is limited to classes related to a particular feature. **‘package by layer’** is monolithic. As an application grows in size, the number of classes in each package will increase without bound. 
###
![modules](https://user-images.githubusercontent.com/53402156/229775563-b077bf0c-0ecf-4cf2-ac15-4f17f23ea2ca.png)
###
>_***High cohesion*** within packages, ***low cohesion*** between packages are essential for a good system._
###
<table>
  <tr>
   <td><strong>Module</strong>
   </td>
   <td><strong>What was its purpose?</strong>
   </td>
   <td><strong>Classes</strong>
   </td>
  </tr>
  <tr>
   <td><code>app</code>
   </td>
   <td>It includes navigation. It brings together the elements necessary for the application to work properly.
   </td>
   <td><code>MainActivity</code><br>
   <code>CatalogNavHost</code><br>
     <code>CatalogApplication</code><br>
     <code>CatalogApplication</code><br>
   </td>
  </tr>
  <tr>
   <td><code>feature:postscreen</code><br></td>
   <td>The structure that will be shown on the home screen.  It has the ViewModel structure, from which it can receive the data for the screen display. ViewModel also makes data accessible by gaining access to it from other sources.<br>
   </td>
   <td><code>PostScreen</code><br>
   <code>PostScreenViewModel</code>
   </td>
  </tr>
  <tr>
   <td><code>core:data</code>
   </td>
   <td>It includes collecting application data from various sources, such as remote or local. And used by the features.
   </td>
   <td><code>Repository</code><br>
      <code>- PostRepository</code><br>
      <code>- CategoryRepository</code><br>
   </td>
  </tr>
  <tr>
   <td><code>core:database</code>
   </td>
   <td>Local database storage using Room.
   </td>
   <td><code>PostDatabase</code><br>
   <code>Dao</code> classes
   </td>
  </tr>
  
  <tr>
   <td><code>core:designsystem</code>
   </td>
   <td>Components, icons and theme components used throughout the app.
   </td>
      <td>
     <code>componenets</code> classes<br>
    <code>icon</code> classes<br>
    <code>theme</code> classes
    </td>
  </tr>
  <tr>
   <td><code>core:network</code>
   </td>
   <td>Making network requests and handling responses from a remote data source.
   </td>
   <td><code>RetrofitNetworkApi</code>
   </td>
  </tr> 
  <tr>
   <td><code>core:model</code>
   </td>
   <td>Model classes used throughout the app.
   </td>
   <td>
     <code>PostInfo</code><br>
     <code>Category</code><br>
     <code>PostInfoWithCategory</code><br>
     <code>Urls</code><br>
     <code>Links</code><br>
   </td>
  </tr>
</table>
