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

import com.example.bakingapp.model.Recipe;
import com.example.bakingapp.model.Step;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeDetailFragment extends Fragment {
    // variable for the Recipe
    private Recipe mRecipe = new Recipe();
    // variable for the recyclerView
    @BindView(R.id.step_list)
    RecyclerView recyclerView;
    // detail activity adapter
    RecipeDetailAdapter mAdapter;
    private Step mStep;

    public RecipeDetailFragment() {

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detail_activity_fragment, container, false);
        ButterKnife.bind(this, view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        Bundle bundle = this.getArguments();
        mRecipe = bundle.getParcelable("Details");
        mAdapter = new RecipeDetailAdapter(getContext(), (ArrayList<Step>) mRecipe.getSteps());
        recyclerView.setAdapter(mAdapter);
        return view;
    }
}

