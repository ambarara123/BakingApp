package com.akumar.bakingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.akumar.bakingapp.Utilities.Recipe;
import com.akumar.bakingapp.adapters.IngradientAdapter;
import com.android.bakingapp.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IngredientActivity extends AppCompatActivity {
    @BindView(R.id.ingradientRecycler)
    RecyclerView ingradientRecycler;
    IngradientAdapter ingradientAdapter;
    ArrayList<Recipe.Ingredients> ingredientsArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredient);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        ingredientsArrayList = intent.getParcelableArrayListExtra("parcel_integrant");
        ingradientAdapter = new IngradientAdapter(this,ingredientsArrayList);
        ingradientRecycler.setLayoutManager(new LinearLayoutManager(this));
        ingradientRecycler.setHasFixedSize(true);
        ingradientRecycler.setAdapter(ingradientAdapter);


    }
}
