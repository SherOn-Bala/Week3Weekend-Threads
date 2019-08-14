package ca.judacribz.week3weekend_threads.list;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ca.judacribz.week3weekend_threads.nav.DeleteEmployeeActivity;
import ca.judacribz.week3weekend_threads.nav.FilterEmployeeActivity;
import ca.judacribz.week3weekend_threads.R;
import ca.judacribz.week3weekend_threads.models.Employee;
import ca.judacribz.week3weekend_threads.models.EmployeeViewModel;
import ca.judacribz.week3weekend_threads.nav.NewEmployeeActivity;
import ca.judacribz.week3weekend_threads.nav.UpdateEmployeeActivity;

import static ca.judacribz.week3weekend_threads.nav.FilterEmployeeActivity.EXTRA_DEPARTMENT;
import static ca.judacribz.week3weekend_threads.nav.FilterEmployeeActivity.EXTRA_POSITION;

public class EmployeeListActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private EmployeeViewModel viewModel;
    RecyclerView rvEmployees;
    DrawerLayout drawer;

    String
            position,
            department;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.all_employees);
        setSupportActionBar(toolbar);


        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                drawer,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        );
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        ((NavigationView) findViewById(R.id.nav_view)).setNavigationItemSelectedListener(this);

        rvEmployees = findViewById(R.id.rvEmployees);
        rvEmployees.setLayoutManager(new LinearLayoutManager(this));
        viewModel = ViewModelProviders.of(this).get(EmployeeViewModel.class);

        filterEmployees(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        filterEmployees(intent);
    }

    private void filterEmployees(Intent intent) {
        /* Filter Employees by position */
        position = intent.getStringExtra(EXTRA_POSITION);
        department = intent.getStringExtra(EXTRA_DEPARTMENT);
        if (position != null && department != null) {
            viewModel.getEmployeesByPositionDepartment(
                    position,
                    department
            ).observe(this, new Observer<List<Employee>>() {
                @Override
                public void onChanged(List<Employee> employees) {
                    rvEmployees.setAdapter(new EmployeeAdapter(employees));
                }
            });
        } else {
            /* Filter Employees by department */
            if (department != null) {
                viewModel.getEmployeesByDepartment(
                        department
                ).observe(this, new Observer<List<Employee>>() {
                    @Override
                    public void onChanged(List<Employee> employees) {
                        rvEmployees.setAdapter(new EmployeeAdapter(employees));
                    }
                });


            } else if (position != null) {
                viewModel.getEmployeesByPosition(
                        position
                ).observe(this, new Observer<List<Employee>>() {
                    @Override
                    public void onChanged(List<Employee> employees) {
                        rvEmployees.setAdapter(new EmployeeAdapter(employees));
                    }
                });

                /* All Employees */
            } else {
                viewModel.getAllEmployees().observe(this, new Observer<List<Employee>>() {
                    @Override
                    public void onChanged(List<Employee> employees) {
                        rvEmployees.setAdapter(new EmployeeAdapter(employees));
                    }
                });
            }
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Intent intent = null;

        switch (item.getItemId()) {
            case R.id.nav_employees:
                onNewIntent(new Intent(this, EmployeeListActivity.class));
                break;
            case R.id.nav_new_employee:
                intent = new Intent(this, NewEmployeeActivity.class);
                break;
            case R.id.nav_delete_employee:
                intent = new Intent(this, DeleteEmployeeActivity.class);
                break;
            case R.id.nav_update_employee:
                intent = new Intent(this, UpdateEmployeeActivity.class);
                break;
            case R.id.nav_filter_employees:
                intent = new Intent(this, FilterEmployeeActivity.class);
                break;
        }

        if (intent != null) {
            startActivity(intent);
        }


        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
