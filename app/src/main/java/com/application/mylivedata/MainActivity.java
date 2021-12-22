package com.application.mylivedata;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.TextView;

import com.application.mylivedata.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private MainViewModel mLiveDataTimerViewModel;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mLiveDataTimerViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        subscribe();
    }

    private void subscribe() {
        final Observer<Long> elapsedTimerObserver = aLong -> {
            String newText = MainActivity.this.getResources().getString(R.string.seconds, aLong);
            binding.timerTextView.setText(newText);
        };
        mLiveDataTimerViewModel.getElapsedTime().observe(this, elapsedTimerObserver);
    }
}