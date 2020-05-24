package com.example.bakingapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bakingapp.R;
import com.example.bakingapp.model.Recipe;

import java.util.ArrayList;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {
    // data source
    private ArrayList<Recipe> mRecipe;
    // context
    private Context context;

    // variable for the clickAdapter
    private final RecipeAdapterOnClickHandler mClickHandler;

    // constructor for the clickListener
    public interface RecipeAdapterOnClickHandler {
        void onClick(Recipe recipe);
    }


    // constructor
    public RecipeAdapter(Context context, ArrayList<Recipe> recipe,RecipeAdapterOnClickHandler mClickHandler) {
        this.context = context;
        mRecipe = recipe;
        this.mClickHandler = mClickHandler;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.recipe_item_view;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;
        View view = inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately);
        RecipeViewHolder viewHolder = new RecipeViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        Recipe type = mRecipe.get(position);
        holder.tittle.setText(type.getName());
    }

    @Override
    public int getItemCount() {
        if (null == mRecipe) return 0;
        return mRecipe.size();
    }


    public void addAll(ArrayList<Recipe> recipe) {
        mRecipe = recipe;
        notifyDataSetChanged();
    }

    class RecipeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tittle;

        public RecipeViewHolder(@NonNull View itemView) {
            super(itemView);
            tittle = (TextView) itemView.findViewById(R.id.recipe_name);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            Recipe recipe = mRecipe.get(adapterPosition);
            mClickHandler.onClick(recipe);
        }
    }
}
