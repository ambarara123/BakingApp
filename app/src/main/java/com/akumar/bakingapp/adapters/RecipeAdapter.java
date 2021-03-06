package com.akumar.bakingapp.adapters;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.akumar.bakingapp.Utilities.Recipe;
import com.akumar.bakingapp.Utilities.RecipeList;
import com.akumar.bakingapp.DetailActivity;
import com.akumar.bakingapp.widgets.Widget;
import com.android.bakingapp.R;

import java.util.ArrayList;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder> {

    Context context;
    ArrayList<Recipe> recipeList;

    public RecipeAdapter(Context context, ArrayList<Recipe> recipeList) {
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

       // holder.recipeName.setText(recipeList.get(position).getName());
       /* holder.servingTextView.setText("Servings : "+ recipeList.get(position).getServings());
        holder.recipeNameTextView.setText("Name : "+ recipeList.get(position).getName());*/
        if (position == 0){
            holder.imgRel.setBackgroundResource(R.drawable.nutella);
        }else if (position==1){
            holder.imgRel.setBackgroundResource(R.drawable.brownies);
        }else if (position==2){
            holder.imgRel.setBackgroundResource(R.drawable.yellow);
        }else {
            holder.imgRel.setBackgroundResource(R.drawable.cheese);
        }



        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("id",recipeList.get(position).getId());
                bundle.putString("name",recipeList.get(position).getName());
                bundle.putInt("position",position);
                bundle.putInt("servingSize",recipeList.get(position).getServings());
                bundle.putParcelableArrayList("ingredients",recipeList.get(position).getIngredients());
                bundle.putParcelableArrayList("steps",recipeList.get(position).getSteps());
                intent.putExtras(bundle);

                context.startActivity(intent);

                //Update Widget
                AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
                int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(context, Widget.class));
                Widget.updateFromAdapter(context, appWidgetManager, appWidgetIds, position, recipeList);
            }
        });

    }

    @Override
    public int getItemCount() {
        Log.d("size", String.valueOf(recipeList.size()));
        return recipeList.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView servingTextView,recipeNameTextView;
        CardView relativeLayout;
        RelativeLayout imgRel;

        public ViewHolder(View itemView) {
            super(itemView);
            relativeLayout = itemView.findViewById(R.id.relativelayout);
            /*servingTextView = itemView.findViewById(R.id.servingTextView);
            recipeNameTextView = itemView.findViewById(R.id.recipeNameTextView);*/
            imgRel = itemView.findViewById(R.id.imageRel);
        }
    }

}
