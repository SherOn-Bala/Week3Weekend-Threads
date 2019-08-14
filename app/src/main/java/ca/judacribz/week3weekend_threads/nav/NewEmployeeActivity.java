package ca.judacribz.week3weekend_threads.nav;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

import ca.judacribz.week3weekend_threads.R;
import ca.judacribz.week3weekend_threads.models.Employee;
import ca.judacribz.week3weekend_threads.models.EmployeeViewModel;

import static ca.judacribz.week3weekend_threads.util.UI.setupActionBar;

public class NewEmployeeActivity extends AppCompatActivity {

    public static final String EXTRA_EMPLOYEE = "ca.judacribz.week3weekend_threads.EXTRA_EMPLOYEE";
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
    int numFields;
    ArrayList<String> etList;
    Intent employeeIntent;
EmployeeViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_employee);
        setupActionBar(getSupportActionBar(), R.string.add_new_employee);

        viewModel = ViewModelProviders.of(this).get(EmployeeViewModel.class);
        ets = new EditText[]{
                etFirstName = findViewById(R.id.etFirstName),
                etLastName = findViewById(R.id.etLastName),
                etStreetAddress = findViewById(R.id.etStreetAddress),
                etCity = findViewById(R.id.etCity),
                etState = findViewById(R.id.etState),
                etZip = findViewById(R.id.etZip),
                etTaxId = findViewById(R.id.etTaxId),
                etPosition = findViewById(R.id.etPosition),
                etDepartment = findViewById(R.id.etDepartment)
        };
        numFields = ets.length;

        employeeIntent = new Intent();
    }

    public void addEmployee(View view) {
        if (checkEts()) {
            viewModel.insert(new Employee(
                    etList.get(0),
                    etList.get(1),
                    etList.get(2),
                    etList.get(3),
                    etList.get(4),
                    etList.get(5),
                    etList.get(6),
                    etList.get(7),
                    etList.get(8)
            ));
            clearEts();
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
