# Simple Sample to fetch weather from remote server/url using service and mvvm architecture



## How IT Works
1. ListActivity Has 3 Fragment in which navigation handle by Navigation Component 
2. LocationFetchFromMapActivity To locate and store data in db
3. ListFragment to populate all data from database and people can click on bookmark to check weather
4. Setting Fragment has option to reset, change unit, for help section
5. MainActivity hit weather api and populate data in graphical manner


# Mobile Assignment CS
## Technology Stack

## Language  
1. Kotlin  
## Databse ORM
1. Room

## Architecture
1. MVI/MVVM
## Dependency injection
1. Hilt

## Flow Maintain
1. Coroutines 
2. lifecycle 
3. Flow

## Networking
1. Retrofit 
2. okhttp 

## Image Loading / Processing / Caching
1. Picasso 

## JSON Parser
1. GSON 

## UI Test
1. Espresso 
2. JUnit





### Architecture (MVI)
1. User does an action which will be an Intent → Intent is a state which is an input to model → Model stores state and send the requested state to the View → View Loads the state from Model → Displays to the user. If we observe, the data will always flow from the user and end with the user through intent. It cannot be the other way, Hence its called Unidirectional architecture. If the user does one more action the same cycle is repeated, hence it is Cyclic
2. Use [Flow](https://miro.medium.com/max/4800/0*nO9r299fdSEt5qwb.png) and livedata

### Testing
Pretty simple test case  
    Activity UI Unit (ListActivityTest & LocationFetchFromMapActivity)
       1. Check For Views
       2. Click On Fab and BottomNavigationView
       3. Click On LocationFetchFromMapActivity's Button and save information
       4. Open MainActivity with test lat lng.. populate data and chdck for data on textview


### Artifacts 
1. [Video Of App Running](https://www.dropbox.com/s/sukzl2uxw0stecr/wea.mp4?dl=0)
2. [Compiled Apk](https://betafamily.com/supersend/app/f596f597-b02d-4020-b61e-2c719b3dd6ae (Please consume this link with in 3 days))




