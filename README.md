## Threads ##
### Coding ###
1. <b>Create an object [Employee](app/src/main/java/ca/judacribz/week3weekend_threads/models/Employee.java). The class should have the following member variables:</b>
    1. FirstName
    2. LastName
    3. StreetAddress
    4. City
    5. State
    6. Zip
    7. TaxID
    8. Position
    9. Department
2. <b>Create a [database](app/src/main/java/ca/judacribz/week3weekend_threads/models) (Room or Sqlite) for the Employee object.</b>
3. <b>Create the following Activities:</b>
    1. [MainActivity](app/src/main/java/ca/judacribz/week3weekend_threads/MainActivity.java)
        1. Make this into a splash screen activity.
        2. After any initializations for the applications, start the FilterEmployeeActivity.
    2. [FilterEmployeeActivity](app/src/main/java/ca/judacribz/week3weekend_threads/nav/FilterEmployeeActivity.java)
        1. Be creative, but must at least use one spinner to select department of the employee. DEPARTMENTS MUST BE RETRIEVED FROM DATABASE.
        2. This activity will start the EmployeeListActivity.
    3. [EmployeeListActivity](app/src/main/java/ca/judacribz/week3weekend_threads/list/EmployeeListActivity.java)
        1. List all the employees matching the criteria selected in the FilterEmployeeActivity.
        2. Implement a Navigation drawer here for the following activities.  Since this activities will be also be called from the Employee details activity, you will need to devise a way to get the info for a specific employee both without the employee known and with the employee known.
            1. NewEmployeeActivity
            2. DeleteEmployeeActivity
            3. UpdateEmployeeActivity
            4. FilterEmployeeActivity
    4. [NewEmployeeActivity](app/src/main/java/ca/judacribz/week3weekend_threads/nav/NewEmployeeActivity.java)
        1. Allows the user to enter a new employee into the database.
        2. Once operation is complete, go back to the Listing activity.
        3. When insert is complete, display a toast letting user know which employee was inserted
    5. [DeleteEmployeeActivity](app/src/main/java/ca/judacribz/week3weekend_threads/nav/DeleteEmployeeActivity.java)
        1. Allow user to delete an employee from the database.
        2. Once operation is complete, go back to the Listing activity.
        3. When delete is complete, display a toast letting user know which employee was deleted
    6. [UpdateEmployeeActivity](app/src/main/java/ca/judacribz/week3weekend_threads/nav/UpdateEmployeeActivity.java)
        1. Allow user toupdate an employee from the database.
        2. Once operation is complete, go back to the Listing activity.
        3. When update is complete, display a toast letting user know which employee was updated
    7. [EmployeeDetailsActivity](app/src/main/java/ca/judacribz/week3weekend_threads/list/EmployeeDetailsActivity.java)
        1. Display all info about the employee.
        2. Be able to pass the employee to the following activities.
            1. DeleteEmployeeActivity
            2. UpdateEmployeeActivity
4. <b>ALL DATABASE OPERATIONS MUST BE HANDLED BY A WORKER THREAD.  You can use any scheme we have covered or one of the following:</b>
    1. Loopers
    2. Loaders
    3. ThreadPools
5. <b>All activities except Main, filter, and list activities must have backward support.  (The back arrow in the left side of action bar)</b>
6. <b>Main, splash, and list must use single instance, all others must use single task.</b>
7. <b>Any activity that requires user input must account for configurational changes in some way.</b>
8. <b>Feel free to experiment with any UI element</b>

### Research ###
1. <b>What are loaders and how do we implement loaders?</b>
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
2. <b>What is an AsyncTaskLoader?</b>
* A class that implements the Loader class and loads data in the background and reassociates background tasks with the activity, even after configuration change.
* Changing orientation in an activity still yields same results for the UI.
3. <b>What is a Handler Thread for?</b>
* A Handler allows you to communicate back with the UI thread from a background thread.
* Allows you to send and process Message and Runnable objects asscoiated with a thread's MessageQueue.
4. <b>What are some common threading restrictions in android?</b>
* Cannot update the UI directly from a background thread.
* Cannot run long running processes on the UI thread, such as pulling data from the internet.
5. <b>What are thread pools and thread pool executors?</b>
* A thread pool is a single FIFO task queue with a group of worker threads.
* The producers, such as the UI thread, sends tasks to the task queue.
* Whenever worker threads in the thread pool become available, they remove the taks from the front of the queue and start running them.
* ThreadPoolExecuter allows you to specify how many core threads and max threads the pool should create and the keep alive time for the idle threads.
6. <b>Define the following thread synchronization approaches:</b>
* Locks
    * Protects access to some kind of shared resource. 
    * If you own a lock, you can access the protected shared resource.
* Mutex
    * Mutual exclusion refers to the type of lockable object ath can be owned by exactly one thread at a time.
    * Only the tread that aquired the lock can release the lock.
* Semaphores
    * Similar to mutex, but allows x number of threads to enter.
    * Can be used to limit the number of cpu, io or ram intensive tasks running at the same time.
* Synchronized
    * Allows only one thread to be executing on an object at a time.
    * All other threads trying to access the same object will wait in line.
* Volatile
    * Indicates that a variables value will be modified by different threads.
    * The value of a volatile variable will never be cached and will go straight to main memory.
    * Access to the variable acts as though it is enclosed in a synchronized  block.
* Atomic
    * An operation being isolated from other operations that may be happening at the same time.
    * Operations done through assembly language.
7. <b>Define Deadlock conditions.</b>
* A situation where a set of processes are blocked because each process is holding a resource and waiting for another resource acquired by some other process.
8. <b>Define Race conditions.</b>
* An undesirable situation that occurs when a device or system attemps to perform two or more operations at the same time, but because of the nature of the device or system, the operations must be done in the proper sequence to be done correctly.
9. <b>What is a memory leak?</b>
* A resource leak that occurs when a computer program incorrectly manages memory allocations in such a way that meory which is no longer needed is not released.
10. <b>What is an ANR and what are some common causes?</b>
* When the UI thread of an Android app is blocked for too long, and Application Not Responding error is triggered.
* When an activity has not responeded to an input within 5 seconds.
* When a broadcast receiver has not finished executing within 10 seconds.
* Long running task on the main thread.
