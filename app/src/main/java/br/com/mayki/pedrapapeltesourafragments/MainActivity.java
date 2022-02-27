package br.com.mayki.pedrapapeltesourafragments;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import br.com.mayki.pedrapapeltesourafragments.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());

        getSupportFragmentManager().beginTransaction().add(R.id.containerFragment, FragmentMain.class, null, "FragmentMain").commit();

    }
}