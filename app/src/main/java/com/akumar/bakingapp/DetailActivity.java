package com.akumar.bakingapp;

import android.content.Intent;
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
    ArrayList<Recipe.Ingredients> ingredientsList;
    ArrayList<Recipe.Steps> stepsList;
    @BindView(R.id.stepRecyclerView)
    RecyclerView stepRecyclerView;
    @BindView(R.id.ingradientCard)CardView ingradientCard;


    StepsAdapter stepsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        stepRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        stepRecyclerView.setHasFixedSize(true);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String name = bundle.getString("name");
        ingredientsList = bundle.getParcelableArrayList("ingredients");
        stepsList = bundle.getParcelableArrayList("steps");

        stepsAdapter = new StepsAdapter(this,stepsList);
        stepRecyclerView.setAdapter(stepsAdapter);


        ingradientCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailActivity.this, IngredientActivity.class);
                intent.putParcelableArrayListExtra("parcel_integrant",ingredientsList);

                startActivity(intent);

            }
        });


        Log.d("bundle",name);

    }
}
