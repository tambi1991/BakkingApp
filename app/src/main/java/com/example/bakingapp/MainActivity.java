package com.example.bakingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.example.bakingapp.adapter.RecipeAdapter;
import com.example.bakingapp.model.Recipe;
import com.example.bakingapp.rest.RecipeClient;
import com.example.bakingapp.rest.RecipeService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements RecipeAdapter.RecipeAdapterOnClickHandler {
    // RecipeService variable.
RecipeService mRecipeService;
// variable for recycleView adapter
    private RecipeAdapter mAdapter;
    // variable for the recycler view
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // finding view by ids
        mRecyclerView = (RecyclerView) findViewById(R.id.image_recycler);

        mRecipeService = new RecipeClient().mRecipeService;
        // call the fetchRecipes in onCreate
        new FetchRecipesAsync().execute();
    }

    @Override
    public void onClick(Recipe recipe) {
        Context context = this;
        Class destination = RecipeDetailActivity.class;
        Intent intent = new Intent(context,destination);
        intent.putExtra("Recipe",recipe);
        startActivity(intent);

    }

    private class FetchRecipesAsync extends AsyncTask<Void,Void,Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            fetchRecipes();
            return null;
        }
    }

    // Fetch recipes
    private void fetchRecipes() {
        Call<ArrayList<Recipe>> call = mRecipeService.getRecipes();
        call.enqueue(new Callback<ArrayList<Recipe>>() {
            @Override
            public void onResponse(Call<ArrayList<Recipe>> call, Response<ArrayList<Recipe>> response) {
                ArrayList<Recipe> recipe = response.body();
                // creating and initializing  the linear manager to the mRecycler
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
                // setting the linear manager to the mRecycler
                mRecyclerView.setLayoutManager(linearLayoutManager);
                // setting the size
                mRecyclerView.setHasFixedSize(true);
                // initiating our adapter
                mAdapter = new RecipeAdapter(MainActivity.this, recipe,MainActivity.this);
                // setting up the adapter
                mRecyclerView.setAdapter(mAdapter);
                // initiating the RecipeService
            }

            @Override
            public void onFailure(Call<ArrayList<Recipe>> call, Throwable t) {
                Log.d("FAILURE", t.toString());
            }
        });
    }
}
