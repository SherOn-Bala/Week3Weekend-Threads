package ca.judacribz.week3weekend_threads.nav;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import ca.judacribz.week3weekend_threads.R;

import static ca.judacribz.week3weekend_threads.util.UI.setupActionBar;

public class UpdateEmployeeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_employee);

        setupActionBar(getSupportActionBar(), R.string.update_employee);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
