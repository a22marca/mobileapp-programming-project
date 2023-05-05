package com.example.project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<Island> islands;
    private LayoutInflater layoutInflater;
    private OnClickListener onClickListener;

    RecyclerViewAdapter(Context context, List<Island> islands, OnClickListener onClickListener) {
        this.layoutInflater = LayoutInflater.from(context);
        this.islands = islands;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(layoutInflater.inflate(R.layout.recyclerview_island, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        holder.name.setText(islands.get(position).getName());
        holder.government.setText(islands.get(position).getGovernment());
        holder.ocean.setText(islands.get(position).getOcean());
        holder.population.setText(String.valueOf(islands.get(position).getPopulation()));
    }

    @Override
    public int getItemCount() {
        return islands.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name;
        TextView government;
        TextView ocean;
        TextView population;

        ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            name = itemView.findViewById(R.id.island_name_textview);
            government = itemView.findViewById(R.id.government_name_textview);
            ocean = itemView.findViewById(R.id.ocean_location_textview);
            population = itemView.findViewById(R.id.population_textview);
        }

        @Override
        public void onClick(View view) {
            onClickListener.onClick(islands.get(getAdapterPosition()));
        }
    }

    public interface OnClickListener {
        void onClick(Island island);
    }
}