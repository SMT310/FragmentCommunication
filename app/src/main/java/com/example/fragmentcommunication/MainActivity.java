package com.example.fragmentcommunication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.content.ClipData;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.security.Provider;

public class MainActivity extends AppCompatActivity {

    Button firstFragment, secondFragment;
    TextView fragmentText;
    private ItemViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firstFragment = findViewById(R.id.fragment1btn);
        secondFragment = findViewById(R.id.fragment2btn);

        fragmentText = findViewById(R.id.fragmentText);
        //view logic
        viewModel = new ViewModelProvider(this).get(ItemViewModel.class);
        viewModel.getSelectedItem().observe(this, item ->{
            fragmentText.setText(item);
        });
        firstFragment.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                replaceFragment(new fragment1());
            }
        });
        secondFragment.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                replaceFragment(new fragment2());
            }
        });
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.framelayout, fragment);
        fragmentTransaction.commit();
    }
}