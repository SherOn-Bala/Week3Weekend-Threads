package ca.judacribz.week3weekend_threads.util;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;

import java.util.List;

public class UI {
    public static void setupActionBar(@Nullable ActionBar actionBar, int titleId) {
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setTitle(titleId);
        }
    }

    public static void setSpinnerAdapter(Context context,
                                         Spinner spinner,
                                         List<String> dataset,
                                         int defaultStringId) {

        dataset.add(0, context.getString(defaultStringId));
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                context,
                android.R.layout.simple_spinner_item,
                dataset
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
}
