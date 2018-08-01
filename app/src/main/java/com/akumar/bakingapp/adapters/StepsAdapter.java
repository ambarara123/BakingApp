package com.akumar.bakingapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.akumar.bakingapp.Utilities.Recipe;
import com.android.bakingapp.R;

import java.util.ArrayList;

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.ViewHolder>{
    private Context context;
    private ArrayList<Recipe.Steps> steps;

    public StepsAdapter(Context context, ArrayList<Recipe.Steps> steps) {
        this.context = context;
        this.steps = steps;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.step_list,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.stepName.setText(String.valueOf(position+1)+" "+steps.get(position).getShortDescription());

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
