package ca.judacribz.week3weekend_threads.list;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import java.util.Objects;

import ca.judacribz.week3weekend_threads.R;
import ca.judacribz.week3weekend_threads.models.Employee;
import ca.judacribz.week3weekend_threads.nav.DeleteEmployeeActivity;
import ca.judacribz.week3weekend_threads.nav.UpdateEmployeeActivity;

import static ca.judacribz.week3weekend_threads.list.EmployeeAdapter.EXTRA_EMPLOYEE;
import static ca.judacribz.week3weekend_threads.util.UI.setupActionBar;

public class EmployeeDetailsActivity extends AppCompatActivity {
    public static final int REQ_MOD_EMP = 1001;

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

    Employee employee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_details);
        setupActionBar(getSupportActionBar(), R.string.employee_details);

        etFirstName = findViewById(R.id.etFirstName);
        etLastName = findViewById(R.id.etLastName);
        etStreetAddress = findViewById(R.id.etStreetAddress);
        etCity = findViewById(R.id.etCity);
        etState = findViewById(R.id.etState);
        etZip = findViewById(R.id.etZip);
        etTaxId = findViewById(R.id.etTaxId);
        etPosition = findViewById(R.id.etPosition);
        etDepartment = findViewById(R.id.etDepartment);

        Intent intent = getIntent();
         employee = intent.getParcelableExtra(EXTRA_EMPLOYEE);
        if (employee != null) {
            etFirstName.setText(employee.getFirstName());
            etLastName.setText(employee.getLastName());
            etStreetAddress.setText(employee.getStreetAddress());
            etCity.setText(employee.getCity());
            etState.setText(employee.getState());
            etZip.setText(employee.getZip());
            etTaxId.setText(employee.getZip());
            etPosition.setText(employee.getPosition());
            etDepartment.setText(employee.getDepartment());
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_employee_details, menu);
        return true;
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = null;
        switch (item.getItemId()) {
            case R.id.act_delete:
                intent = new Intent(this, DeleteEmployeeActivity.class);
                break;
            case R.id.act_update:
                intent = new Intent(this, UpdateEmployeeActivity.class);
                break;
        }
        if (intent != null) {
            intent.putExtra(EXTRA_EMPLOYEE, employee);
            startActivityForResult(intent, REQ_MOD_EMP);
            return true;
        }

        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQ_MOD_EMP) {
            if (resultCode == RESULT_OK) {
                finish();
            }
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
