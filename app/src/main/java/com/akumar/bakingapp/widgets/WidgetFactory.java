package com.akumar.bakingapp.widgets;

import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;
import android.widget.Toast;

import com.akumar.bakingapp.MainActivity;
import com.akumar.bakingapp.Utilities.Recipe;
import com.akumar.bakingapp.Utilities.Utils;
import com.akumar.bakingapp.adapters.RecipeAdapter;
import com.android.bakingapp.R;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class WidgetFactory implements RemoteViewsService.RemoteViewsFactory {
    Context context;
    ArrayList<Recipe> recipes;

    public WidgetFactory(Context context) {
        this.context = context;

    }

    @Override
    public void onCreate() {
         getRecipes();
        SystemClock.sleep(1500);

    }

    @Override
    public void onDataSetChanged() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {

        if (recipes!=null) {
            return recipes.size();
        }else {
            return 0;
        }
    }

    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_list);


        Recipe recipe = recipes.get(position);
        ArrayList<Recipe.Ingredients> ingredients = recipe.ingredients;
        String text = "";



        for (Recipe.Ingredients ingredient : ingredients) {
            text = text + ingredient.getIngredient()+ " \n";
        }


        remoteViews.setTextViewText(R.id.textWidget, recipe.getName());
        remoteViews.setTextViewText(R.id.textWidget1, text);

        return remoteViews;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    public void getRecipes(){

        StringRequest request = new StringRequest(Utils.url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Gson gson = new GsonBuilder().create();
                Type recipeType = new TypeToken<ArrayList<Recipe>>() {
                }.getType();

                recipes = gson.fromJson(response, recipeType);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        Volley.newRequestQueue(context).add(request);

    }

}
