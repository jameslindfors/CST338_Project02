package com.cst338.project02.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cst338.project02.Models.ChargerRowModel;
import com.cst338.project02.R;

import java.util.ArrayList;

public class ChargerListRow_Adapter extends RecyclerView.Adapter<ChargerListRow_Adapter.ViewHolder> {

    Context context;
    ArrayList<ChargerRowModel> chargerRowModels;

    public ChargerListRow_Adapter(Context context, ArrayList<ChargerRowModel> chargerRowModelList) {
        this.context = context;
        this.chargerRowModels = chargerRowModelList;
    }

    @NonNull
    @Override
    public ChargerListRow_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.charger_list_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChargerListRow_Adapter.ViewHolder holder, int position) {
        holder.chargerName.setText(chargerRowModels.get(position).getStationName());
        holder.chargerLocation.setText(chargerRowModels.get(position).getStationLocation());

    }

    @Override
    public int getItemCount() {
        return chargerRowModels.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView chargerName;
        TextView chargerLocation;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            chargerName = itemView.findViewById(R.id.stationNameView);
            chargerLocation = itemView.findViewById(R.id.stationLocationView);
        }
    }
}
