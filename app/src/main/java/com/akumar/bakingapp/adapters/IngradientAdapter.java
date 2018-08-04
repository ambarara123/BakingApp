package com.akumar.bakingapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.akumar.bakingapp.Utilities.Recipe;
import com.android.bakingapp.R;

import java.util.ArrayList;

public class IngradientAdapter extends RecyclerView.Adapter<IngradientAdapter.ViewHolder>{

    private Context context;
    private ArrayList<Recipe.Ingredients> ingredients;

    public IngradientAdapter(Context context, ArrayList<Recipe.Ingredients> ingredients) {
        this.context = context;
        this.ingredients = ingredients;
    }

    @NonNull
    @Override
    public IngradientAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.ingradient_list,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.stepName.setText(ingredients.get(position).getIngredient());

    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView stepName;

        public ViewHolder(View itemView) {
            super(itemView);
            stepName = itemView.findViewById(R.id.ingradientName);
        }
    }
}
