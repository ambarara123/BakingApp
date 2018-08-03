package com.akumar.bakingapp;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.akumar.bakingapp.Utilities.Recipe;
import com.android.bakingapp.R;

import java.util.ArrayList;

public class DescriptionActivity extends AppCompatActivity {
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);


        Intent intent = getIntent();
        position = intent.getIntExtra("position",0);

        ArrayList<Recipe.Steps> stepsArrayList = intent.getParcelableArrayListExtra("dec_steps");


        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("step_array",stepsArrayList);
        bundle.putInt("step_position",position);







        Log.d("steps",stepsArrayList.get(position).getVideoURL());
    }
}
