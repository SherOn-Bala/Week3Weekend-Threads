package ca.judacribz.week3weekend_threads.nav;

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
import java.util.Objects;

import ca.judacribz.week3weekend_threads.R;
import ca.judacribz.week3weekend_threads.list.EmployeeListActivity;
import ca.judacribz.week3weekend_threads.models.EmployeeViewModel;

import static ca.judacribz.week3weekend_threads.util.UI.setSpinnerAdapter;

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
        Objects.requireNonNull(getSupportActionBar()).setTitle(R.string.filter_employees);

        /* Spinner Setup */
        sprDepartment = findViewById(R.id.sprEmployee);
        sprPosition = findViewById(R.id.sprPosition);

        viewModel = ViewModelProviders.of(this).get(EmployeeViewModel.class);
        viewModel.getAllDepartments().observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> departments) {
                setSpinnerAdapter(
                        getApplicationContext(),
                        sprDepartment,
                        departments,
                        R.string.default_department
                );
            }
        });

        setAllPositions();

        /* Selection Listeners */
        sprDepartment.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long l) {

                if (!getString(R.string.default_department).equals(
                        department = parent.getItemAtPosition(pos).toString())
                ) {
                    viewModel.getPositionsByDepartment(
                            department
                    ).observe(FilterEmployeeActivity.this, new Observer<List<String>>() {
                        @Override
                        public void onChanged(List<String> positions) {
                            setSpinnerAdapter(
                                    getApplicationContext(),
                                    sprPosition,
                                    positions,
                                    R.string.default_position
                            );
                        }
                    });
                } else {
                    setAllPositions();
                }
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

    private void setAllPositions() {
        viewModel.getAllPositions().observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> positions) {
                setSpinnerAdapter(
                        getApplicationContext(),
                        sprPosition,
                        positions,
                        R.string.default_position
                );
            }
        });
    }

    public void getEmployees(View view) {
        Intent intent = new Intent(this, EmployeeListActivity.class);

        if (!getString(R.string.default_department).equals(department)) {
            intent.putExtra(EXTRA_DEPARTMENT, department);
        }

        if (!getString(R.string.default_position).equals(position)) {
            intent.putExtra(EXTRA_POSITION, position);
        }

        startActivity(intent);
    }
}
