package ca.judacribz.week3weekend_threads.models;

import android.app.Application;

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

    LiveData<List<Employee>> getEmployeesByPositionDepartment(String position, String department) {
        return employeeDao.getEmployeesByPositionDepartment(position, department);
    }

    LiveData<List<Employee>> getEmployeesByDepartment(String department){
        return employeeDao.getEmployeesByDepartment(department);
    }

    LiveData<List<Employee>> getEmployeesByPosition(String position) {
        return employeeDao.getEmployeesByPosition(position);
    }

    LiveData<List<String>> getAllDepartments(){
        return employeeDao.getAllDepartments();
    }
    LiveData<List<String>> getAllPositions(){
        return employeeDao.getAllPositions();
    }

    void insert(final Employee employee) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                employeeDao.insert(employee);
            }
        }).start();
    }

    void deleteEmployees(final Employee... employees) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                employeeDao.deleteEmployees(employees);
            }
        }).start();
    }
}
