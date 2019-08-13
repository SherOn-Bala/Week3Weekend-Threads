package ca.judacribz.week3weekend_threads.list;

import android.content.Intent;
import android.os.Bundle;

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

import android.view.Menu;

import java.util.List;

import ca.judacribz.week3weekend_threads.FilterEmployeeActivity;
import ca.judacribz.week3weekend_threads.R;
import ca.judacribz.week3weekend_threads.models.Employee;
import ca.judacribz.week3weekend_threads.models.EmployeeViewModel;

import static ca.judacribz.week3weekend_threads.FilterEmployeeActivity.EXTRA_DEPARTMENT;
import static ca.judacribz.week3weekend_threads.FilterEmployeeActivity.EXTRA_POSITION;

public class EmployeeListActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private EmployeeViewModel viewModel;
    RecyclerView rvEmployees;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        rvEmployees = findViewById(R.id.rvEmployees);
        rvEmployees.setLayoutManager(new LinearLayoutManager(this));

        viewModel = ViewModelProviders.of(this).get(EmployeeViewModel.class);
        viewModel.getAllEmployees().observe(this, new Observer<List<Employee>>() {
            @Override
            public void onChanged(List<Employee> employees) {
                rvEmployees.setAdapter(new EmployeeAdapter(employees));
            }
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        String position = intent.getStringExtra(EXTRA_POSITION);

        if (position != null) {
            viewModel.getEmployeesByPosition(
                    position
            ).observe(this, new Observer<List<Employee>>() {
                @Override
                public void onChanged(List<Employee> employees) {
                    rvEmployees.setAdapter(new EmployeeAdapter(employees));
                }
            });
        } else {
            String department = intent.getStringExtra(EXTRA_DEPARTMENT);
            if (department != null) {
                viewModel.getEmployeesByDepartment(
                        department
                ).observe(this, new Observer<List<Employee>>() {
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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.employee_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id) {
            case R.id.nav_employees:
                break;
            case R.id.nav_new_employee:
                break;
            case R.id.nav_delete_employee:
                break;
            case R.id.nav_update_employee:
                break;
            case R.id.nav_filter_employees:
                startActivity(new Intent(this, FilterEmployeeActivity.class));
                break;
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
