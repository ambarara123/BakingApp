package com.akumar.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.akumar.Utilities.RecipeList;
import com.akumar.bakingapp.DetailActivity;
import com.akumar.bakingapp.MainActivity;
import com.android.bakingapp.R;

import java.util.ArrayList;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder> {

    Context context;
    ArrayList<RecipeList> recipeList;

    public RecipeAdapter(Context context, ArrayList<RecipeList> recipeList) {
        this.context = context;
        this.recipeList = recipeList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recipe_list,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        holder.recipeName.setText(recipeList.get(position).getName());

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);

                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        Log.d("size", String.valueOf(recipeList.size()));
        return recipeList.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView recipeName;
        RelativeLayout relativeLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            recipeName = itemView.findViewById(R.id.recipeName);
            relativeLayout = itemView.findViewById(R.id.relativelayout);
        }
    }
}
