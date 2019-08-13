package ca.judacribz.week3weekend_threads;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.List;

import ca.judacribz.week3weekend_threads.list.EmployeeListActivity;
import ca.judacribz.week3weekend_threads.models.EmployeeViewModel;

public class FilterEmployeeActivity extends AppCompatActivity {

    public static final String
            EXTRA_DEPARTMENT = "ca.judacribz.week3weekend_threads.EXTRA_DEPARTMENT",
            EXTRA_POSITION = "ca.judacribz.week3weekend_threads.EXTRA_POSITION";
    Spinner
            sprDepartment,
            sprPosition;

    EmployeeViewModel viewModel;
    ArrayAdapter<String> departmentAdapter, positionAdapter;

    String
            department = null,
            position = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_employee);

        /* Spinner Setup */
        sprDepartment = findViewById(R.id.sprDepartment);
        sprPosition = findViewById(R.id.sprPosition);

        viewModel = ViewModelProviders.of(this).get(EmployeeViewModel.class);
        viewModel.getAllDepartments().observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> departments) {
                departmentAdapter = new ArrayAdapter<>(
                        FilterEmployeeActivity.this,
                        android.R.layout.simple_spinner_item,
                        departments
                );
                departmentAdapter.setDropDownViewResource(
                        android.R.layout.simple_spinner_dropdown_item
                );
                sprDepartment.setAdapter(departmentAdapter);
            }
        });
        viewModel.getAllPositions().observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> positions) {
                positionAdapter = new ArrayAdapter<>(
                        FilterEmployeeActivity.this,
                        android.R.layout.simple_spinner_item,
                        positions
                );
                positionAdapter.setDropDownViewResource(
                        android.R.layout.simple_spinner_dropdown_item
                );
                sprPosition.setAdapter(positionAdapter);
            }
        });


        /* Selection Listeners */
        sprDepartment.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long l) {
                viewModel.getPositionsByDepartment(
                        department = parent.getItemAtPosition(pos).toString()
                ).observe(FilterEmployeeActivity.this, new Observer<List<String>>() {
                    @Override
                    public void onChanged(List<String> positions) {
                        positionAdapter = new ArrayAdapter<>(
                                FilterEmployeeActivity.this,
                                android.R.layout.simple_spinner_item,
                                positions
                        );
                        positionAdapter.setDropDownViewResource(
                                android.R.layout.simple_spinner_dropdown_item
                        );
                        sprPosition.setAdapter(positionAdapter);
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        sprPosition.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long l) {
                        position = parent.getItemAtPosition(pos).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    public void getEmployees(View view) {
        if (department != null) {
            Intent intent = new Intent(this, EmployeeListActivity.class);
            intent.putExtra(EXTRA_DEPARTMENT, department);

            if (position != null) {
                intent.putExtra(EXTRA_POSITION, position);
            }

            startActivity(intent);
        }
    }
}
