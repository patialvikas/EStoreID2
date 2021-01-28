package com.idestore.ui.home.cartActivity.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.idestore.R;
import com.idestore.services.RetrofitClientInstance;
import com.idestore.ui.home.AppExecutors;
import com.idestore.ui.home.cartActivity.CartActivity;
import com.idestore.ui.home.productDetails.Entities.GlobalCartEntity;
import com.idestore.ui.home.productDetails.GlobalDatabase.GlobalCartDatabase;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CartItemAdapter extends RecyclerView.Adapter<CartItemAdapter.ViewHolder> {


    //public static Afterclick afterclick;
    Context context;
    ArrayList<GlobalCartEntity>arrayList;
    String convert_p_p;
 Afterclick afterclick;
    public CartItemAdapter(Context context, ArrayList<GlobalCartEntity> arrayList,Afterclick afterclick) {
        this.context = context;
        this.arrayList=arrayList;
        this.afterclick=afterclick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_cart_items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context)
                .load(RetrofitClientInstance.IMG_PRODUCTS+arrayList.get(position).getPhoto())
                .placeholder(R.drawable.ic_launcher_background)
                .diskCacheStrategy(DiskCacheStrategy.ALL)//placeholder
                .error(R.drawable.ic_launcher_background) //error
                .into(holder.imgProduct);

        holder.product_name.setText(arrayList.get(position).getPname());

        double p_p=Double.parseDouble(arrayList.get(position).getP_price());
         convert_p_p=String.format("%.2f", p_p);
        holder.tvProductPprice.setText(convert_p_p);

        double pre_p=Double.parseDouble(arrayList.get(position).getOldprice());
        String convert_pre=String.format("%.2f", pre_p);
        holder.product_discount.setText(convert_pre);


       if(arrayList.get(position).getCatEntityLocalArrayList()!=null&&arrayList.get(position).getCatEntityLocalArrayList().size()>0){
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<arrayList.get(position).getCatEntityLocalArrayList().size();i++){
            sb.append(arrayList.get(position).getCatEntityLocalArrayList().get(i).getPname()+":"
                    +arrayList.get(position).getCatEntityLocalArrayList().get(i).getValue());
        }
        holder.product_category.setText(sb.toString());

       }else{
           holder.product_category.setText("No Category");
       }

       holder.count_tv.setText(String.valueOf(arrayList.get(position).getQuantity()));
       holder.cart_item_cancel.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {


               AppExecutors.getInstance().diskIO().execute(new Runnable() {

                   @Override
                   public void run() {
                       GlobalCartDatabase db = Room.databaseBuilder(context,
                               GlobalCartDatabase.class, "catglobal_db").build();
                       db.globalCartDio().deleteRecord(arrayList.get(position).getPid());
                       //arrayList= (ArrayList<GlobalCartEntity>) db.globalCartDio().getAll();
                       //CartItemAdapter.this.notifyAll();
                       //notifyItemInserted(position);
                       // notifyDataSetChanged();
                       //populateUI();
                   }

               });
               afterclick.interMethod();

           }
       });

holder.add_item_from_cart.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Log.e("checkm",arrayList.get(position).getActual_price());
        int newqty= Integer.parseInt(holder.count_tv.getText().toString())+1;
        holder.count_tv.setText(String.valueOf(newqty));
        notifyDataSetChanged();
        double newprice=Double.parseDouble(arrayList.get(position).getActual_price())*newqty;
        AppExecutors.getInstance().diskIO().execute(new Runnable() {

            @Override
            public void run() {
                GlobalCartDatabase db = Room.databaseBuilder(context,
                        GlobalCartDatabase.class, "catglobal_db").build();
                db.globalCartDio().updateQtyP(String.valueOf(newprice),arrayList.get(position).getPid(),newqty);
           //arrayList= (ArrayList<GlobalCartEntity>) db.globalCartDio().getAll();
                  //CartItemAdapter.this.notifyAll();
                //notifyItemInserted(position);
               // notifyDataSetChanged();
                //populateUI();
            }

        });
afterclick.interMethod();
        //((CartActivity)context).refreshActivtiy();
    }
});

holder.remove_item_from_cart.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        if(Integer.parseInt(holder.count_tv.getText().toString())>1){
            Log.e("minus","work");

            int newqty= Integer.parseInt(holder.count_tv.getText().toString())-1;
            Log.e("minusqty",String.valueOf(newqty));
            holder.count_tv.setText(String.valueOf(newqty));
            //notifyDataSetChanged();
            double newprice=Double.parseDouble(arrayList.get(position).getActual_price())*newqty;
            AppExecutors.getInstance().diskIO().execute(new Runnable() {
                @Override
                public void run() {
                    GlobalCartDatabase db = Room.databaseBuilder(context,
                            GlobalCartDatabase.class, "catglobal_db").build();
                    db.globalCartDio().updateQtyP(String.valueOf(newprice),arrayList.get(position).getPid(),newqty);
                   // db.globalCartDio().updateQtyP(String.valueOf(newprice),arrayList.get(position).getPid(),newqty);
                    //arrayList= (ArrayList<GlobalCartEntity>) db.globalCartDio().getAll();
                    //notifyDataSetChanged();
                   // notifyItemInserted(position);

                }
            });
          // context.refreshActivtiy();
            afterclick.interMethod();
            //notifyDataSetChanged();
        }

    }
});

    }



    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imgProduct)
        ImageView imgProduct;
        @BindView(R.id.product_name)
        TextView product_name;
          @BindView(R.id.product_category)
        TextView product_category;
          @BindView(R.id.tvProductPprice)
          TextView tvProductPprice;
        @BindView(R.id.product_discount)
         TextView product_discount;
        @BindView(R.id.remove_item_from_cart)
        ImageButton remove_item_from_cart;
        @BindView(R.id.add_item_from_cart)
        ImageButton add_item_from_cart;
         @BindView(R.id.count_tv)
        TextView count_tv;
         @BindView(R.id.saveforlater)
         TextView saveforlater;
         @BindView(R.id.cart_item_cancel)
         ImageButton cart_item_cancel;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }

    public interface Afterclick{
        void interMethod();
    }

}
