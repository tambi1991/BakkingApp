package com.example.bakingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import com.example.bakingapp.model.Recipe;
import butterknife.BindView;

public class RecipeDetailActivity extends AppCompatActivity {
    // variable for the Recipe
    private Recipe mRecipe = new Recipe();
    // variable for the recyclerView
    @BindView(R.id.step_list) RecyclerView mRecyclerView;
    // Track whether to display a two-pane or single-pane UI
    // A single-pane display refers to phone screens, and two-pane to larger tablet screens
    private boolean mTwoPane;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);
// recycle view butter knife after crashed when applying two pain
        if(findViewById(R.id.recipe_details_tablet) != null){
            mTwoPane = true;
            mRecyclerView.setHasFixedSize(true);
            RecyclerView.LayoutManager GridLayoutManager = new GridLayoutManager(this,2);
            mRecyclerView.setLayoutManager(GridLayoutManager);
        }
        else{
            mTwoPane = false;
        }
        //intent that started this activity
        Intent intentThatStartedThisActivity = getIntent();
        if (intentThatStartedThisActivity == null) {
            closeOnError();
        }
        mRecipe = intentThatStartedThisActivity.getParcelableExtra("Recipe");
        Bundle bundle = new Bundle();
        bundle.putParcelable("Details", mRecipe);

        //initiating fragment
        RecipeDetailFragment fragment = new RecipeDetailFragment();
        fragment.setArguments(bundle);
        // Add the fragment to its container using a FragmentManager and a Transaction
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.step_container, fragment)
                .commit();
        RecipeDetailFragment ingredientFragment = new RecipeDetailFragment();
        ingredientFragment.setArguments(bundle);
        // Add the fragment to its container using a FragmentManager and a Transaction
        fragmentManager.beginTransaction()
                .replace(R.id.ingredient_container, ingredientFragment)
                .commit();

    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, " no image available", Toast.LENGTH_SHORT).show();
    }

}
