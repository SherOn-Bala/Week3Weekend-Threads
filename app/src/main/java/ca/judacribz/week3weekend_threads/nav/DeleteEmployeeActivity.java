package ca.judacribz.week3weekend_threads.nav;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import ca.judacribz.week3weekend_threads.R;
import ca.judacribz.week3weekend_threads.models.Employee;
import ca.judacribz.week3weekend_threads.models.EmployeeViewModel;

import static ca.judacribz.week3weekend_threads.list.EmployeeAdapter.EXTRA_EMPLOYEE;
import static ca.judacribz.week3weekend_threads.models.Employee.getEmpShortForm;
import static ca.judacribz.week3weekend_threads.models.Employee.getSpinnerItemsToEmployee;
import static ca.judacribz.week3weekend_threads.util.UI.setSpinnerAdapter;
import static ca.judacribz.week3weekend_threads.util.UI.setupActionBar;

public class DeleteEmployeeActivity extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener {
    EmployeeViewModel viewModel;
    Spinner sprEmployee;
    TextView tvEmployee;
    Button btnDelete;

    List<Employee> employees;
    Employee
            employee = null,
            receivedEmp;
    int intentPos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_employee);
        setupActionBar(getSupportActionBar(), R.string.delete_employee);

        tvEmployee = findViewById(R.id.tvEmployee);
        btnDelete = findViewById(R.id.btnDelete);
        sprEmployee = findViewById(R.id.sprEmployee);
        sprEmployee.setOnItemSelectedListener(this);

        receivedEmp = getIntent().getParcelableExtra(EXTRA_EMPLOYEE);

        viewModel = ViewModelProviders.of(this).get(EmployeeViewModel.class);
        viewModel.getAllEmployees().observe(this, new Observer<List<Employee>>() {
            @Override
            public void onChanged(List<Employee> employees) {
                List<String> emps = getSpinnerItemsToEmployee(
                        DeleteEmployeeActivity.this.employees = employees

                );
                setSpinnerAdapter(
                        getApplicationContext(),
                        sprEmployee,
                        emps,
                        R.string.default_employee
                );

                if (receivedEmp != null) {
                    intentPos = emps.indexOf(getEmpShortForm(receivedEmp));
                    sprEmployee.setSelection(intentPos);
                }
            }
        });


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long l) {
        if (pos != 0) {
            employee = employees.get(pos - 1);
            tvEmployee.setText(String.format(
                    Locale.US,
                    "%s %s\n%s\n%s\n%s\n%s\n%s\n%s\n%s",
                    employee.getFirstName(),
                    employee.getLastName(),
                    employee.getStreetAddress(),
                    employee.getCity(),
                    employee.getState(),
                    employee.getZip(),
                    employee.getTaxId(),
                    employee.getPosition(),
                    employee.getDepartment()
            ));
            btnDelete.setVisibility(View.VISIBLE);
        } else {
            employee = null;
            tvEmployee.setText(getString(R.string.choose_delete));
            btnDelete.setVisibility(View.GONE);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void deleteEmployee(View view) {
        if (employee != null) {
            viewModel.delete(employee);
            sprEmployee.setSelection(0);

            Toast.makeText(this, String.format(
                    Locale.US,
                    getString(R.string.deleted_employee),
                    employee.getFirstName(),
                    employee.getLastName()
            ), Toast.LENGTH_SHORT).show();

            setResult(RESULT_OK);
            finish();
        }
    }
}
