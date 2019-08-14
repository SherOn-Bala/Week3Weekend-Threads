package ca.judacribz.week3weekend_threads.nav;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
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

public class UpdateEmployeeActivity extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener {
    Spinner sprEmployee;

    List<Employee> employees;
    Employee
            employee = null,
            receivedEmp;
    int intentPos;

    EditText
            etFirstName,
            etLastName,
            etStreetAddress,
            etCity,
            etState,
            etZip,
            etTaxId,
            etPosition,
            etDepartment;
    EditText[] ets;
    ArrayList<String> etList;
    int numFields;
    EmployeeViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_employee);
        setupActionBar(getSupportActionBar(), R.string.update_employee);

        numFields = (ets = new EditText[]{
                etFirstName = findViewById(R.id.etFirstName),
                etLastName = findViewById(R.id.etLastName),
                etStreetAddress = findViewById(R.id.etStreetAddress),
                etCity = findViewById(R.id.etCity),
                etState = findViewById(R.id.etState),
                etZip = findViewById(R.id.etZip),
                etTaxId = findViewById(R.id.etTaxId),
                etPosition = findViewById(R.id.etPosition),
                etDepartment = findViewById(R.id.etDepartment)
        }).length;

        sprEmployee = findViewById(R.id.sprEmployee);
        sprEmployee.setOnItemSelectedListener(this);

        receivedEmp = getIntent().getParcelableExtra(EXTRA_EMPLOYEE);

        viewModel = ViewModelProviders.of(this).get(EmployeeViewModel.class);
        viewModel.getAllEmployees().observe(this, new Observer<List<Employee>>() {
            @Override
            public void onChanged(List<Employee> employees) {
                List<String> emps = getSpinnerItemsToEmployee(
                        UpdateEmployeeActivity.this.employees = employees
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


    public void updateEmployee(View view) {
        if (checkEts()) {
            Employee emp = new Employee(
                    etList.get(0),
                    etList.get(1),
                    etList.get(2),
                    etList.get(3),
                    etList.get(4),
                    etList.get(5),
                    etList.get(6),
                    etList.get(7),
                    etList.get(8)
            );

            if (!emp.equals(employee)) {
                viewModel.update(emp);

                Toast.makeText(this, String.format(
                        Locale.US,
                        getString(R.string.updated_employee),
                        emp.getFirstName(),
                        emp.getLastName()
                ), Toast.LENGTH_SHORT).show();

                setResult(RESULT_OK);
                finish();
            }
        }
    }

    private boolean checkEts() {
        String str;

        etList = new ArrayList<>();
        for (EditText et : ets) {

            if ((str = et.getText().toString().trim()).isEmpty()) {
                et.setError(getString(R.string.err_required));
            } else {
                etList.add(str);
            }
        }

        return etList.size() == numFields;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long l) {
        if (pos != 0) {
            employee = employees.get(pos - 1);
            etFirstName.setText(employee.getFirstName());
            etLastName.setText(employee.getLastName());
            etStreetAddress.setText(employee.getStreetAddress());
            etCity.setText(employee.getCity());
            etState.setText(employee.getState());
            etZip.setText(employee.getZip());
            etTaxId.setText(employee.getTaxId());
            etPosition.setText(employee.getPosition());
            etDepartment.setText(employee.getDepartment());
        } else {
            employee = null;
            clearEts();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private void clearEts() {
        for (EditText et : ets) {
            et.setText("");
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
