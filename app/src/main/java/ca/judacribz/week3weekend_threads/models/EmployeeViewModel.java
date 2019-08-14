package ca.judacribz.week3weekend_threads.models;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class EmployeeViewModel extends AndroidViewModel {

    private EmployeeRepository employeeRepo;
    private LiveData<List<Employee>> employees;

    public EmployeeViewModel(Application application) {
        super(application);
        employeeRepo = new EmployeeRepository(application);
        employees = employeeRepo.getAllEmployees();
    }

    public LiveData<List<Employee>> getAllEmployees() {
        return employees;
    }

    public LiveData<List<Employee>> getEmployeesByPositionDepartment(String position, String department) {
        return employeeRepo.getEmployeesByPositionDepartment(position, department);
    }

    public LiveData<List<Employee>> getEmployeesByDepartment(String department) {
        return employeeRepo.getEmployeesByDepartment(department);
    }

    public LiveData<List<Employee>> getEmployeesByPosition(String position) {
        return employeeRepo.getEmployeesByPosition(position);
    }

    public LiveData<List<String>> getPositionsByDepartment(String department) {
        return employeeRepo.getPositionsByDepartment(department);
    }

    public LiveData<List<String>> getAllDepartments() {
        return employeeRepo.getAllDepartments();
    }

    public LiveData<List<String>> getAllPositions() {
        return employeeRepo.getAllPositions();
    }

    public void insert(Employee employee) {
        employeeRepo.insert(employee);
    }

    public void delete(Employee... employees) {
        employeeRepo.deleteEmployees(employees);
    }
}
