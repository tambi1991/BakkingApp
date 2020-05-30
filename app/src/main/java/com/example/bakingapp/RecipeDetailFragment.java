package com.example.bakingapp;

import android.content.Context;
import android.icu.text.Transliterator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bakingapp.adapter.IngredientAdapter;
import com.example.bakingapp.model.Ingredient;
import com.example.bakingapp.model.Recipe;
import com.example.bakingapp.model.Step;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeDetailFragment extends Fragment {
    // variable for the Recipe
    private Recipe mRecipe = new Recipe();
    // variable for the  Recipe recyclerView
    @BindView(R.id.step_list)
    RecyclerView recyclerView;
    // variable for the ingredient recyclerView
    @BindView(R.id.ingredient_list)
    RecyclerView ingredientRecyclerView;
    // recipe activity adapter
    RecipeDetailAdapter mAdapter;
    // ingredient activity adapter
    IngredientAdapter ingredientAdapter;
    // initiate fragment
    public RecipeDetailFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detail_activity_fragment, container, false);
        // binding the views
        ButterKnife.bind(this, view);
        Bundle bundle = this.getArguments();
        mRecipe = bundle.getParcelable("Details");
        // recycler and adapter for the recipe
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        // constructor for the recipe
        mAdapter = new RecipeDetailAdapter(getContext(), (ArrayList<Step>) mRecipe.getSteps());
        recyclerView.setAdapter(mAdapter);
// recycler for the ingredient
        ingredientRecyclerView.setHasFixedSize(true);
        LinearLayoutManager ingredientLayout = new LinearLayoutManager(getContext());
        ingredientRecyclerView.setLayoutManager(ingredientLayout);
        // constructor for the ingredient
        ingredientAdapter = new IngredientAdapter(getContext(), (ArrayList<Ingredient>) mRecipe.getIngredients());
        ingredientRecyclerView.setAdapter(ingredientAdapter);
        return view;
    }
}

