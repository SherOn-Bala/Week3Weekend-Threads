package ca.judacribz.week3weekend_threads.models;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Employee.class}, version = 1)
public abstract class EmployeeDatabase extends RoomDatabase {

    public abstract EmployeeDao employeeDao();

    private static volatile EmployeeDatabase INSTANCE;

    static EmployeeDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (EmployeeDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            EmployeeDatabase.class,
                            "employee_database"
                    )
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static Callback sRoomDatabaseCallback = new Callback() {

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateDbAsync(INSTANCE).execute();
        }
    };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final EmployeeDao employeeDao;

        PopulateDbAsync(EmployeeDatabase db) {
            employeeDao = db.employeeDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {

            employeeDao.insert(new Employee("Sheron", "Balasingam", "30 Avenida Street", "Markham", "ON", "L3S 4J4", "416-509-7437", "Android Developer", "Consultants"));
            employeeDao.insert(new Employee("James", "Morasca", "3 Mcauley Dr", "Ashland", "OH", "44805", "419-503-2484", "Technical Recruiter", "Human Resources"));
            employeeDao.insert(new Employee("Josephine", "Bobo", "39 S 7th St", "Coffee", "TN", "37388", "931-875-6644", "iOS Developer", "Consultants"));
            employeeDao.insert(new Employee("Mitsue", "Bishi", "6649 N Blue Gum St", "New Orleans", "LA", "70116", "504-621-8927", "HR Director", "Human Resources"));
            employeeDao.insert(new Employee("Minnehaha", "Darakjy", "4 B Blue Ridge Blvd", "Livingston", "MI", "48116", "810-292-9388", "Exchange Consultant", "Consultants"));
            employeeDao.insert(new Employee("Abel", "Wieser", "5 Boston Ave #88", "Sioux Falls", "SD", "57105", "605-414-2147", "Accounts", "Human Resources"));
            employeeDao.insert(new Employee("Graciela", "Shinko", "426 Wolf St", "Jefferson", "LA", "70002", "504-979-9175", "Exchange Consultant", "Consultants"));
            employeeDao.insert(new Employee("Leota", "Stenseth", "45 E Liberty St", "Bergen", "NJ", "07660", "201-709-6245", "Android Developer", "Consultants"));
            employeeDao.insert(new Employee("Donette", "Malet", "209 Decker Dr", "Philadelphia", "PA", "19132", "215-907-9111", "iOS Developer", "Consultants"));
            employeeDao.insert(new Employee("Albina", "Montgomery", "76 Brooks St #9", "Hunterdon", "NJ", "08822", "908-877-6135", "Senior Accountant", "Accounting"));
            employeeDao.insert(new Employee("Jose", "Wenner", "4545 Courthouse Rd", "Nassau", "NY", "11590", "516-968-6051", "Marketing Lead", "Business"));
            employeeDao.insert(new Employee("Lavera", "Uyetake", "4 Otis St", "Los Angeles", "CA", "91405", "818-423-4007", "Lawyer", "Legal"));
            employeeDao.insert(new Employee("Kanisha", "Richland", "53075 Sw 152nd Ter #615", "Middlesex", "NJ", "08831", "732-234-1546", "CEO", "Business"));
            employeeDao.insert(new Employee("Brock", "Briddick", "2853 S Central Expy", "Anne Arundel", "MD", "21061", "410-914-9018", "Business Operations", "Business"));
            employeeDao.insert(new Employee("Blair", "Malet", "1088 Pinehurst St", "Orange", "NC", "27514", "919-225-9345", "Business Immigration Manager", "Business"));

            return null;
        }
    }

}