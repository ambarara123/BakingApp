package com.akumar.bakingapp;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.akumar.bakingapp.Utilities.Recipe;
import com.akumar.bakingapp.adapters.StepsAdapter;
import com.android.bakingapp.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    FragmentManager fragmentManager;

    public static boolean twoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        if (findViewById(R.id.idLinearLayout) != null){

            twoPane = true;

        }else {
            twoPane = false;
        }


        Intent intent = getIntent();

            Bundle bundle = intent.getExtras();
            bundle.putBoolean("two_pane",twoPane);


            this.setTitle(bundle.getString("name"));


            

        /*if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        else {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }*/



        fragmentManager = getSupportFragmentManager();

        if (savedInstanceState == null){

            StepsFragment stepsFragment = new StepsFragment();
            stepsFragment.setArguments(bundle);

            fragmentManager.beginTransaction().add(R.id.detail_fragment,stepsFragment,
            "step_fragment")
                    .commit();

        }



    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

/*
        getSupportFragmentManager().putFragment(outState,"step_fragment", stepsFragment);*/

    }
}
