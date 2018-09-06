package com.akumar.bakingapp.adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.akumar.bakingapp.DescriptionFragment;
import com.akumar.bakingapp.Utilities.Recipe;
import com.android.bakingapp.R;

import java.util.ArrayList;

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.ViewHolder>{
    private Context context;
    private ArrayList<Recipe.Steps> steps;
    private FragmentManager fragmentManager;
    private boolean twopane;

    public StepsAdapter(Context context, ArrayList<Recipe.Steps> steps, FragmentManager fragmentManager,
                        boolean twopane) {
        this.context = context;
        this.steps = steps;
        this.fragmentManager = fragmentManager;
        this.twopane = twopane;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.step_list,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        holder.stepName.setText("• "+steps.get(position).getShortDescription());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DescriptionFragment descriptionFragment = new DescriptionFragment();
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("step_arraylist",steps);
                bundle.putInt("step_position",position);
                bundle.putBoolean("twopane",twopane);

                descriptionFragment.setArguments(bundle);

                if (twopane){

                    fragmentManager.beginTransaction().add(R.id.description_fragment, descriptionFragment,
                            "description_fragment")

                            .commit();
                }else {


                    fragmentManager.beginTransaction().add(R.id.detail_fragment, descriptionFragment,
                            "description_fragment")

                            .commit();
                }





            }
        });

    }

    @Override
    public int getItemCount() {
        return steps.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView stepName;
        CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            stepName = itemView.findViewById(R.id.stepName);
            cardView = itemView.findViewById(R.id.stepCard);
        }
    }
}

