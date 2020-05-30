package com.example.bakingapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bakingapp.R;
import com.example.bakingapp.model.Ingredient;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.ingredientViewHolder> {
    // source
private ArrayList<Ingredient> mIngredient;
// content
    private Context content;

    // constructor
    public  IngredientAdapter (Context content, ArrayList<Ingredient> ingredients){
        this.content = content;
        this.mIngredient = ingredients;
    }

    @NonNull
    @Override
    public ingredientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.ingredient_item_view;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;
        View view = inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately);
        ingredientViewHolder viewHolder = new ingredientViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ingredientViewHolder holder, int position) {
        Ingredient type = mIngredient.get(position);
        holder.ingredient.setText(type.getIngredient());
        holder.quantity.setText(String.valueOf(type.getQuantity()));
        holder.measure.setText(type.getMeasure());

    }

    @Override
    public int getItemCount() {
        if(null==mIngredient) return 0;
        return mIngredient.size();

    }


    public class ingredientViewHolder extends RecyclerView.ViewHolder {
 @BindView(R.id.ingredient) TextView ingredient;
 @BindView(R.id.quantity) TextView quantity;
@BindView(R.id.measure) TextView measure;

        public ingredientViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
