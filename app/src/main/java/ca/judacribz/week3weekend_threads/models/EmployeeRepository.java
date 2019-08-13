package ca.judacribz.week3weekend_threads.models;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

class EmployeeRepository {

    private EmployeeDao employeeDao;
    private LiveData<List<Employee>> employees;

    EmployeeRepository(Application application) {
        EmployeeDatabase db = EmployeeDatabase.getDatabase(application);
        employeeDao = db.employeeDao();
        employees = employeeDao.getAllEmployees();
    }

    LiveData<List<Employee>> getAllEmployees() {
        return employees;
    }

    LiveData<List<String>> getPositionsByDepartment(String department) {
        return employeeDao.getPositionsByDepartment(department);
    }

    LiveData<List<Employee>> getEmployeesByPosition(String position) {
        return employeeDao.getEmployeesByPosition(position);
    }

    LiveData<List<Employee>> getEmployeesByDepartment(String department){
        return employeeDao.getEmployeesByDepartment(department);
    }
    LiveData<List<String>> getAllDepartments(){
        return employeeDao.getAllDepartments();
    }
    LiveData<List<String>> getAllPositions(){
        return employeeDao.getAllPositions();
    }

    void insert(Employee Employee) {
        new insertAsyncTask(employeeDao).execute(Employee);
    }

    private static class insertAsyncTask extends AsyncTask<Employee, Void, Void> {

        private EmployeeDao employeeTaskDao;

        insertAsyncTask(EmployeeDao dao) {
            employeeTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Employee... params) {
            employeeTaskDao.insert(params[0]);
            return null;
        }
    }
}
