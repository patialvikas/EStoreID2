package com.idestore.ui.home.productDetails.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.idestore.R;

public class ProductColorAdapter extends RecyclerView.Adapter<ProductColorAdapter.ViewHolder> {

    Context context;
    private int selectedPosition = -1;


    public ProductColorAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ProductColorAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_color, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductColorAdapter.ViewHolder holder, int position) {

        if (selectedPosition == position) {
            holder.itemView.setSelected(true); //using selector drawable
            Toast.makeText(context, "select", Toast.LENGTH_SHORT).show();
        } else {
            holder.itemView.setSelected(false);
            Toast.makeText(context, "unselect", Toast.LENGTH_SHORT).show();
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedPosition = holder.getAdapterPosition();
                notifyItemChanged(selectedPosition);
            }
        });

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

        }
    }
}
