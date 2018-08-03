package com.akumar.bakingapp;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.akumar.bakingapp.Utilities.Recipe;
import com.akumar.bakingapp.adapters.StepsAdapter;
import com.android.bakingapp.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class StepsFragment extends Fragment {
    ArrayList<Recipe.Ingredients> ingredientsList;
    ArrayList<Recipe.Steps> stepsList;
    @BindView(R.id.stepRecyclerView)
    RecyclerView stepRecyclerView;
    @BindView(R.id.ingradientCard)CardView ingradientCard;

    StepsAdapter stepsAdapter;
    FragmentManager fragmentManager;


    public StepsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_steps, container, false);
        ButterKnife.bind(this,view);
        fragmentManager = getFragmentManager();

        stepRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        stepRecyclerView.setHasFixedSize(true);



        Bundle bundle = getArguments();
        String name = bundle.getString("name");
        ingredientsList = bundle.getParcelableArrayList("ingredients");
        stepsList = bundle.getParcelableArrayList("steps");

        stepsAdapter = new StepsAdapter(getContext(),stepsList,fragmentManager);
        stepRecyclerView.setAdapter(stepsAdapter);


        ingradientCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), IngredientActivity.class);
                intent.putParcelableArrayListExtra("parcel_integrant",ingredientsList);

                startActivity(intent);

            }
        });


        return view;
    }

}
