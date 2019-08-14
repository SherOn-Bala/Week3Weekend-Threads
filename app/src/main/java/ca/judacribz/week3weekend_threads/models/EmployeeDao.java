package ca.judacribz.week3weekend_threads.models;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface EmployeeDao {
    @Query("SELECT * FROM employee_table ORDER BY last_name ASC")
    LiveData<List<Employee>> getAllEmployees();

    @Query("SELECT * FROM employee_table WHERE position = :position AND department = :department ORDER BY last_name ASC")
    LiveData<List<Employee>> getEmployeesByPositionDepartment(String position, String department);

    @Query("SELECT * FROM employee_table WHERE department = :department ORDER BY last_name ASC")
    LiveData<List<Employee>> getEmployeesByDepartment(String department);

    @Query("SELECT * FROM employee_table WHERE position = :position ORDER BY last_name ASC")
    LiveData<List<Employee>> getEmployeesByPosition(String position);

    @Query("SELECT DISTINCT position FROM employee_table WHERE department = :department ORDER BY last_name ASC")
    LiveData<List<String>> getPositionsByDepartment(String department);

    @Query("SELECT DISTINCT department FROM employee_table")
    LiveData<List<String>> getAllDepartments();

    @Query("SELECT DISTINCT position FROM employee_table")
    LiveData<List<String>> getAllPositions();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Employee employee);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void updateEmployee(Employee employee);

    @Delete
    void deleteEmployees(Employee... employees);
}
