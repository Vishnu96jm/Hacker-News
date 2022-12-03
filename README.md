# Hacker-News

This is a simple application which shows the latest HackerNews stories.

**Features:**
* The App supports offline support using Room database. 
* Pull to refresh option is provided to fetch data from remote.
* Search option is provided to filter the News based on search text. 

**Architecture components and libraries used:**
* **MVVM** architecture pattern is used with **ViewModel, LiveData**. It is a lifecycle-aware component and it can automatically manage lifecycle. 
* Uses **Retrofit** framework for authenticating and interacting with APIs and sending network requests.
* Uses **Moshi** converter to Retrofit, which will automatically parse the JSON and give the object of the type needed.
* Uses **View Binding** to generate a binding class for each XML layout file present in that module.
* **Room database** for offine support. Room is an SQL powered database and it uses annotations to generate powerful queries and database tables.



***Swipe to Refresh to fetch data from remote***

<img src="https://user-images.githubusercontent.com/59041442/205419099-b4612095-8151-4bcc-9c8c-6c04b35770b3.png" width="300" height="600">



***Search to filter the stories***

<img src="https://user-images.githubusercontent.com/59041442/205419761-987df4cd-3ea1-429e-8a29-503b9e3b37b9.png" width="300" height="600">




***Progress bar to indicate loading state***

<img src="https://user-images.githubusercontent.com/59041442/205419828-0d1d1356-bed4-4e57-9287-a3fae71eecc4.png" width="300" height="600">




***News article of the clicked News item***

<img src="https://user-images.githubusercontent.com/59041442/205419873-3570c9f5-ad8e-4ec8-88b2-74858eb3c381.png" width="300" height="600">





