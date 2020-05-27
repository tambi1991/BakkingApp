package com.example.bakingapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.bakingapp.model.Step;

import java.util.ArrayList;

public class RecipeDetailAdapter extends RecyclerView.Adapter<RecipeDetailAdapter.DetailViewHolder> {
    // source
    ArrayList<Step> mStep;
    // context
    Context conext;

    public RecipeDetailAdapter(Context context, ArrayList<Step> mStep) {
        this.conext = context;
        this.mStep = mStep;
    }
    @NonNull
    @Override
    public DetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.detail_item_view;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;
        View view = inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately);
        RecipeDetailAdapter.DetailViewHolder viewHolder = new RecipeDetailAdapter.DetailViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DetailViewHolder holder, final int position) {
   Step type = mStep.get(position);
   holder.description.setText(type.getShortDescription());
   holder.itemView.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v) {
           Step step = mStep.get(position);
           Intent intent = new Intent(conext,CookingActivity.class);
           intent.putExtra("STEP",step);
           conext.startActivity(intent);

       }
   });
    }

    @Override
    public int getItemCount() {
        if(mStep==null) return 0;
        return mStep.size();
    }

    class DetailViewHolder extends RecyclerView.ViewHolder {
        TextView description;
        public DetailViewHolder(@NonNull View itemView) {
            super(itemView);
            description = (TextView) itemView.findViewById(R.id.description);
        }
    }
}
