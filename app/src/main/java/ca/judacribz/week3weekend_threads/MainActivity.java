package ca.judacribz.week3weekend_threads;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import java.util.List;

import ca.judacribz.week3weekend_threads.list.EmployeeListActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//
//        mWordViewModel = ViewModelProviders.of(this).get(WordViewModel.class);
//        mWordViewModel.getAllWords().observe(this, new Observer<List<Word>>() {
//            @Override
//            public void onChanged(@Nullable final List<Word> words) {
//                adapter.setWords(words);
//            }
//        });
        startActivity(new Intent(this, EmployeeListActivity.class));
    }
}
