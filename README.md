### Coding ###
1. Create an object [Employee](app/src/main/java/ca/judacribz/week3weekend_threads/models/Employee.java). The class should have the following member variables:
    1. FirstName
    2. LastName
    3. StreetAddress
    4. City
    5. State
    6. Zip
    7. TaxID
    8. Position
    9. Department
2. Create a database (Room or Sqlite) for the Employee object.
3. Create the following Activities:
    1. MainActivity
        1. Make this into a splash screen activity.
        2. After any initializations for the applications, start the FilterEmployeeActivity.
    2. FilterEmployeeActivity
        1. Be creative, but must at least use one spinner to select department of the employee. DEPARTMENTS MUST BE RETRIEVED FROM DATABASE.
        2. This activity will start the EmployeeListActivity.
    3. EmployeeListActivity
        1. List all the employees matching the criteria selected in the FilterEmployeeActivity.
        2. Implement a Navigation drawer here for the following activities.  Since this activities will be also be called from the Employee details activity, you will need to devise a way to get the info for a specific employee both without the employee known and with the employee known.
            1. NewEmployeeActivity
            2. DeleteEmployeeActivity
            3. UpdateEmployeeActivity
            4. FilterEmployeeActivity
    4. NewEmployeeActivity
        1. Allows the user to enter a new employee into the database.
        2. Once operation is complete, go back to the Listing activity.
        3. When insert is complete, display a toast letting user know which employee was inserted
    5. DeleteEmployeeActivity
        1. Allow user to delete an employee from the database.
        2. Once operation is complete, go back to the Listing activity.
        3. When delete is complete, display a toast letting user know which employee was deleted
    6. UpdateEmployeeActivity
        1. Allow user toupdate an employee from the database.
        2. Once operation is complete, go back to the Listing activity.
        3. When update is complete, display a toast letting user know which employee was updated
    7. EmployeeDetailsActivity
        1. Display all info about the employee.
        2. Be able to pass the employee to the following activities.
            1. DeleteEmployeeActivity
            2. UpdateEmployeeActivity
4. ALL DATABASE OPERATIONS MUST BE HANDLED BY A WORKER THREAD.  You can use any scheme we have covered or one of the following:
    1. Loopers
    2. Loaders
    3. ThreadPools
5. All activities except Main, filter, and list activities must have backward support.  (The back arrow in the left side of action bar)
6. Main, splash, and list must use single instance, all others must use single task.
7. Any activity that requires user input must account for configurational changes in some way.
8. Feel free to experiment with any UI element

### Research ###
1. What are loaders and how do we implement loaders?
* What?
    * lets you load data from a content provider or other data source to display.
    * runs on separate threads to prevent unresponsive UI.
    * simplifies thread management by providing callback methods when events occur.
    * persist and cache results across configuration changes to prevent duplicate queries.
    * can implement an observer to monitor for changes in the underlying data source.
* How?
    * LoaderManager used to initialize loaders in acitivities/fragments in onCreate()/onStart().
    * init() and restartLoader() needs:
        * LOADER_ID: Uniq ID given to Loader, if we are initializing Loader, will check if the Loader ID is present or not. It will call on the callbacks method accordingly, which I wil explain later.
        * OPTION_ARGUMENT: It is the argument given to Loader at the time of initialization.
        * CALLBACK_CLASS_REFERENCE: Class that implements all it's callback function, which Loader will return data.
    * LoaderManager.LoaderCallBacks provides 3 methods called sequentially:
        * OnCreateLoader(int,bundle): We will call on this method when we initialize Loader. In can only be called when no Loader with LOADER_ID is already present. onCreateLoader() will return the Loader object, either CursorLoader, AyncTaskLoader or CustomImplementedLoader.
        * OnLoadFinished(): Called after our data loading is finished. Basically we'll do all the user interface binding and other things here.
        * OnLoaderReset(): Every time our Loader gets reset (either activity/ Fragment get destroyed), we have to clear all the refereces from current Loader in this callback.
    * Loader, an abstract class, which Android has 2 implementations of:
        * AsyncTaskLoader: A Loader that provides AsyncTask to perform background operations.
        * CusorLoader: A Loader implementation used to get data from content providers through content resolvers.
2. What is an AsyncTaskLoader?
* A class that implements the Loader class and loads data in the background and reassociates background tasks with the activity, even after configuration change.
* Changing orientation in an activity still yields same results for the UI.
3. What is a Handler Thread for?
* A Handler allows you to communicate back with the UI thread from a background thread.
* Allows you to send and process Message and Runnable objects asscoiated with a thread's MessageQueue.
4. What are some common threading restrictions in android?
* Cannot update the UI directly from a background thread.
* Cannot run long running processes on the UI thread, such as pulling data from the internet.
5. What are thread pools and thread pool executors?
6. Define the following thread synchronization approaches:
        - Locks
        - Mutex
        - Semaphores
        - Synchronized
        - Volatile
        - Atomic
7. Define Deadlock conditions.
8. Define Race conditions.
9. What is a memory leak?
10. What is an ANR and what are some common causes?