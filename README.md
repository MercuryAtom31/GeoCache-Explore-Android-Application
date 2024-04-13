GeoCache Explore Functionalities:

1. MainScreenFragment:
   
  •	Purpose: Acts as the dashboard or home page of your app.
  •	Features:
    o	Buttons or menu items to navigate to MapFragment and CacheListFragment.
  •	Navigation:
    o	To MapFragment to view caches on the map.
    o	To CacheListFragment to view a list of all caches.

    
3. MapFragment
   
  •	Purpose: Displays caches on a Google Map.
  •	Features:
    o	Interactive map markers for each cache.
    o	Tapping a marker shows a small popup (info window) with a brief summary and a button to view more details.
  •	Navigation:
    o	Tapping on a marker's detail button navigates to CacheDetailFragment.
    o	Back navigation leads to MainScreenFragment.


5. CacheListFragment

  •	Purpose: Lists all caches in a detailed list view.
  •	Features:
    o	Each list item shows brief details of a cache.
    o	A button for adding new caches.
  •	Navigation:
    o	Tapping on a list item navigates to CacheDetailFragment.
    o	The add button navigates to CacheEditFragment to create a new cache.


6. CacheDetailFragment

  •	Purpose: Displays detailed information about a specific cache, including its location on a mini map 
           embedded within the fragment.
  •	Features:
    o	Detailed information about the cache.
    o	Buttons for updating (navigates to CacheEditFragment) and deleting the cache.
    o	A delete button triggers a confirmation dialog.
  •	Navigation:
    o	Update button leads to CacheEditFragment with pre-filled data for the cache.
    o	Delete button first shows a confirmation dialog; on confirmation, the cache is deleted and navigates back.
    o	Back navigation to MainScreenFragment or to the last viewed fragment (either MapFragment or CacheListFragment).


7. CacheEditFragment

  •	Purpose: Used for both adding a new cache and updating an existing one.
  •	Features:
    o	Form to input or edit cache details.
  •	Navigation:
    o	Save operation leads back to CacheDetailFragment if editing, or adds the new cache 
      and possibly navigates to CacheListFragment.
    o	Cancel operation returns to the previous fragment without making changes.


