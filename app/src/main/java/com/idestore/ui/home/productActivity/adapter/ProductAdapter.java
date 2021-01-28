package com.idestore.ui.home.productActivity.adapter;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatRatingBar;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.idestore.R;
import com.idestore.services.RetrofitClientInstance;
import com.idestore.ui.home.cartActivity.CartActivity;
import com.idestore.ui.home.productDetails.ProductDetailsActivity;
import com.idestore.ui.model.VendorProductsModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    Context context;
    String type;
    //VendorProductsModel vendorProductsModel=new VendorProductsModel();
ArrayList<VendorProductsModel.Data.Product>list;
    public ProductAdapter(Context context, String type, ArrayList<VendorProductsModel.Data.Product> list) {
        this.context = context;
        this.type = type;
        this.list=list;
        //this.vendorProductsModel=vendorProductsModel;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = null;
        if (type.equals("layout_list")) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            view = layoutInflater.inflate(R.layout.layout_product_list, parent, false);
        } else if (type.equals("layout_grid")) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            view = layoutInflater.inflate(R.layout.layout_product_grid, parent, false);
        }

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ViewHolder holder, int position) {

        //holder.product_image.setI
        Glide.with(context)
                .load(RetrofitClientInstance.IMG_URL+list.get(position).getThumbnail())
                .placeholder(R.drawable.ic_launcher_background)
                .diskCacheStrategy(DiskCacheStrategy.ALL)//placeholder
                .error(R.drawable.ic_launcher_background) //error
                .into(holder.product_image);

        holder.product_name.setText(list.get(position).getName());
        holder.product_name.setTag(list.get(position).getId());

        String price=String.format("%.2f", list.get(position).getPrice());
        holder.product_price.setText(price);

        String Pre_price=String.format("%.2f", list.get(position).getPreviousPrice());
        holder.product_original_price.setText(Pre_price);

        holder.appCompatRatingBar.setNumStars(5);
        holder.products_review.setText(String.valueOf(list.get(position).getRatings().size()));

        holder.product_add_to_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.product_add_to_cart.setImageDrawable(context.getResources().getDrawable(R.drawable.red_cart));

               // Intent i=new Intent(context, CartActivity.class);

            }
        });

holder.product_add_to_fav.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        holder.product_add_to_fav.setImageDrawable(context.getResources().getDrawable(R.drawable.red_fav));
    }
});


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,holder.product_name.getTag().toString(),Toast.LENGTH_LONG).show();
                Intent intent = new Intent(context, ProductDetailsActivity.class);
                intent.putExtra("product_id",holder.product_name.getTag().toString());
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.product_image)
        ImageView product_image;

        @BindView(R.id.product_name)
        TextView product_name;

        @BindView(R.id.product_add_to_fav)
        ImageButton product_add_to_fav;

        @BindView(R.id.product_add_to_cart)
        ImageButton product_add_to_cart;

        @BindView(R.id.product_brand)
        TextView product_brand;

        @BindView(R.id.product_rating)
        AppCompatRatingBar appCompatRatingBar;

        @BindView(R.id.products_review)
        TextView products_review;

        @BindView(R.id.product_price)
        TextView product_price;

        @BindView(R.id.product_original_price)
        TextView product_original_price;

        @BindView(R.id.product_discountper)
        TextView product_discountper;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //vendorProductsModel=new VendorProductsModel();
            ButterKnife.bind(this, itemView);
        }
    }
}
