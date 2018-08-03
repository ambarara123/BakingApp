package com.akumar.bakingapp;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.akumar.bakingapp.Utilities.Recipe;
import com.akumar.bakingapp.adapters.StepsAdapter;
import com.android.bakingapp.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    FragmentManager fragmentManager;
    StepsFragment stepsFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);



        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();


        fragmentManager = getSupportFragmentManager();

        stepsFragment = new StepsFragment();
        stepsFragment.setArguments(bundle);

        fragmentManager.beginTransaction().add(R.id.detail_fragment, stepsFragment
                ,"step_fragment")
                .commit();

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);


        getSupportFragmentManager().putFragment(outState,"step_fragment", stepsFragment);

    }
}
