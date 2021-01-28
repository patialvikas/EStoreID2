package com.idestore.ui.home.productDetails.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.idestore.R;
import com.idestore.ui.model.ProductDetailModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductCategoryAdapter extends RecyclerView.Adapter<ProductCategoryAdapter.ViewHolder> {

    Context context;
    private int selectedPosition = -1;
    private ProductCategoryAdapter.ItemClickListener mClickListener;
    DataTransferInterface dtInterface;

    ArrayList<ProductDetailModel.Data.Category> categoryArrayList;
    public ProductCategoryAdapter(Context context, ArrayList<ProductDetailModel.Data.Category> categoryArrayList,DataTransferInterface dtInterface) {
        this.context = context;
      this.categoryArrayList=categoryArrayList;
     this.dtInterface = dtInterface;
    }

    @NonNull
    @Override
    public ProductCategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.product_category_recy_child, parent, false);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.width = (int) (parent.getWidth() * 0.6);
        view.setLayoutParams(layoutParams);

        return new ProductCategoryAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductCategoryAdapter.ViewHolder holder, int position) {


        /*if (selectedPosition == position) {
            holder.itemView.setSelected(true); //using selector drawable
            Toast.makeText(context, "selected", Toast.LENGTH_SHORT).show();
           // holder.layout_methods.setBackgroundResource(R.drawable.selected_bg);
        } else {
            holder.itemView.setSelected(false);
            Toast.makeText(context, "unselected", Toast.LENGTH_SHORT).show();
            //holder.layout_methods.setBackgroundResource(R.drawable.unselected_bg);
        }*/
holder.productscat.setText(categoryArrayList.get(position).getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedPosition = holder.getAdapterPosition();
                notifyItemChanged(selectedPosition);
                dtInterface.onSetValues(position);

            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        @BindView(R.id.tvCategory)
        TextView productscat;
       // @BindView(R.id.rdgrb)
        //TextView product_price;
       // @BindView(R.id.layout_methods)
       // RelativeLayout layout_methods;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    void setClickListener(ProductCategoryAdapter.ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

    public interface DataTransferInterface {
        public void onSetValues(int postion);
    }
}
