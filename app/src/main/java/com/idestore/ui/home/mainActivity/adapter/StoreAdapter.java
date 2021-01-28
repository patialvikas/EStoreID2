package com.idestore.ui.home.mainActivity.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.idestore.R;
import com.idestore.services.RetrofitClientInstance;
import com.idestore.ui.authentication.loginActivity.LoginActivity;
import com.idestore.ui.home.mainActivity.MainActivity;
import com.idestore.ui.home.productActivity.ProductListActivity;
import com.idestore.ui.model.UserLoginModel;
import com.idestore.ui.model.VendorListModel;
import com.idestore.ui.model.VendorProductsModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.ViewHolder> {

    Context context;
    Boolean following_stataus=false;
ArrayList<VendorListModel.Dataa.Vendor>arrayList;
    VendorProductsModel vendorProductsModel=new VendorProductsModel();
    private final SparseBooleanArray followarray=new SparseBooleanArray();

    ArrayList<VendorProductsModel.Data.Product>productArrayList;
    ArrayList<VendorProductsModel.Data.Product.Rating>ratingArrayList;
    //ArrayList<VendorProductsModel.Data.> arrayList;
    public StoreAdapter(Context context,ArrayList<VendorListModel.Dataa.Vendor>arrayList) {
        this.context = context;
        this.arrayList=arrayList;
productArrayList=new ArrayList<VendorProductsModel.Data.Product>();
ratingArrayList=new ArrayList<VendorProductsModel.Data.Product.Rating>();
    }

    @NonNull
    @Override

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.layout_store_name, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
holder.vendorName.setText(arrayList.get(position).getName());
holder.vendorLoc.setText(arrayList.get(position).getShopAddress());
holder.vendorTime.setText(arrayList.get(position).getOpenTime()+"-"+arrayList.get(position).getCloseTime());
holder.vender_id.setText(arrayList.get(position).getId().toString());
holder.vender_mob.setText(arrayList.get(position).getShopImage());
holder.vender_mob.setText(arrayList.get(position).getPhone());
        //followarray.put(position,false);

        if(followarray.get(position)){
            holder.followbt.setText("Unfollow");
            //followarray.put(position,false);
        }else{
            holder.followbt.setText("Follow");
            //followarray.put(position,true);
        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // call api
                Map<String, String> requestBody = new HashMap<>();
                requestBody.put("vendor_id", holder.vender_id.getText().toString());
               // requestBody.put("password", editPassword.getText().toString());
        Toast.makeText(context,holder.vender_id.getText().toString(),Toast.LENGTH_LONG).show();
                RetrofitClientInstance.getRetrofitInstance().vendorProductsList(requestBody
                ).enqueue(new Callback<VendorProductsModel>() {
                    @Override
                    public void onResponse(Call<VendorProductsModel> call, Response<VendorProductsModel> response) {
                        if(response.body().getStatus()) {
                            vendorProductsModel = response.body();
                            Log.e("check data",response.body().toString());
productArrayList= (ArrayList<VendorProductsModel.Data.Product>) vendorProductsModel.getData().getProducts();

                            Toast.makeText(context, String.valueOf(productArrayList.size()), Toast.LENGTH_LONG).show();
//ratingArrayList=vendorProductsModel.getData().getProducts().getRatings();
                            Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_LONG).show();

if(productArrayList.size()>0) {
    Intent intent = new Intent(context, ProductListActivity.class);
    intent.putExtra("list", (Serializable) productArrayList);
    // intent.putExtra("data", (Serializable) vendorProductsModel);
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    context.startActivity(intent);

}else{
    Toast.makeText(context,"Vendor Has No Products Yet.",Toast.LENGTH_LONG).show();
}
                            //finish();
                        }
                        else{
                            Toast.makeText(context, "No Result Found", Toast.LENGTH_LONG).show();
                        }


                    }

                    @Override
                    public void onFailure(Call<VendorProductsModel> call, Throwable t) {

                        Toast.makeText(context, t.getMessage(), Toast.LENGTH_LONG).show();
                        Log.e("Message",t.getMessage());
                    }


                });





            }
        });


        holder.followbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(context, "unfollow click", Toast.LENGTH_SHORT).show();
                if(!followarray.get(position)) {
                    followarray.put(position, true);
                    //following_stataus=false;
                }else{
                    followarray.put(position, false);

                }
                notifyDataSetChanged();

               // holder.followbt.setText("UnFollow");
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    @OnClick(R.id.followbt)
    public void onViewClicked() {
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.vendor_name)
        TextView vendorName;

        @BindView(R.id.vendor_loc)
        TextView vendorLoc;

        @BindView(R.id.vendor_time)
        TextView vendorTime;

        @BindView(R.id.vendor_id)
        TextView vender_id;

        @BindView(R.id.vendor_mob)
        TextView vender_mob;

        @BindView(R.id.shop_img)
        TextView shop_img;

        @BindView(R.id.followbt)
        Button followbt;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


        }
    }
}
