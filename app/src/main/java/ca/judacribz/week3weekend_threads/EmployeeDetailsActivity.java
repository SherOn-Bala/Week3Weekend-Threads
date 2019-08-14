package ca.judacribz.week3weekend_threads;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import static ca.judacribz.week3weekend_threads.util.UI.setupActionBar;

public class EmployeeDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_details);
        setupActionBar(getSupportActionBar(), R.string.employee_details);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
