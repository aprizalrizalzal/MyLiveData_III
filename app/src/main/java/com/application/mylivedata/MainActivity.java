package com.application.mylivedata;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private MainViewModel mLiveDataTimerViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLiveDataTimerViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        subscribe();
    }

    private void subscribe() {
        final Observer<Long> elapsedTimerObserver = new Observer<Long>() {
            @Override
            public void onChanged(@Nullable final Long aLong) {
                String newText = MainActivity.this.getResources().getString(R.string.seconds, aLong);
                ((TextView)findViewById(R.id.timer_textView)).setText(newText);
            }
        };
        mLiveDataTimerViewModel.getElapsedTime().observe(this, elapsedTimerObserver);
    }
}