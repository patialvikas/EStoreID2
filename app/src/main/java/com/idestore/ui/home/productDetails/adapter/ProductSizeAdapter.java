package com.idestore.ui.home.productDetails.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.idestore.R;
import com.idestore.ui.home.AppExecutors;
import com.idestore.ui.home.productDetails.Entities.DesCatEntityLocal;
import com.idestore.ui.home.productDetails.LocalDatabase.LocalSlectCatDatabase;
import com.idestore.ui.model.ProductDetailModel;
import com.idestore.ui.model.SlectCatModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductSizeAdapter extends RecyclerView.Adapter<ProductSizeAdapter.ViewHolder> {

    Context context;

    private int selectedPosition = -1;
    public int mSelectedItem = -1;
    ArrayList<ProductDetailModel.Data.Category> categoryArrayList;
    DataSizeInterface dataSizeInterface;
    ArrayList<SlectCatModel>slectCatModels;
    int positionn;
    java.util.HashMap<Integer,String> hashMap=new HashMap<Integer,String>();

    public ProductSizeAdapter(Context context, ArrayList<ProductDetailModel.Data.Category> categoryArrayList,
                              int postion,DataSizeInterface dataSizeInterface) {
        this.context = context;
this.categoryArrayList=categoryArrayList;
this.positionn=postion;
this.dataSizeInterface=dataSizeInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_product_size, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.radioGroup.setChecked(position == mSelectedItem);

       /* if (selectedPosition == position) {
            holder.itemView.setSelected(true); //using selector drawable
            Toast.makeText(context, "selected", Toast.LENGTH_SHORT).show();
            holder.layout_methods.setBackgroundResource(R.drawable.selected_bg);
        } else {
            holder.itemView.setSelected(false);
            Toast.makeText(context, "unselected", Toast.LENGTH_SHORT).show();
            holder.layout_methods.setBackgroundResource(R.drawable.unselected_bg);
        }*/
holder.radiovalue.setText(categoryArrayList.get(positionn).getValue().get(position));
holder.price.setText(categoryArrayList.get(positionn).getPrice().get(position));
       /* for(String price : categoryArrayList.get(positionn).getValue()){
            RadioButton rb = new RadioButton(ProductSizeAdapter.this.context);
            //rb.setId(id++);
            //rb.setText(price);

            holder.radioGroup.addView(rb);
        }*/

        holder.radioGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSelectedItem = position;
                notifyDataSetChanged();



               // hashMap.put(positionn,categoryArrayList.get(positionn).getPrice().get(position));

                LocalSlectCatDatabase db = Room.databaseBuilder(context,
                        LocalSlectCatDatabase.class, "catlocal_db").build();

                DesCatEntityLocal desCatEntityLocal=new DesCatEntityLocal(categoryArrayList.get(positionn).getName(),
                        categoryArrayList.get(positionn).getValue().get(position),categoryArrayList.get(positionn).getPrice().get(position));


                AppExecutors.getInstance().diskIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        Log.e("isinserted","yes??");

                        Boolean check= db.localCatDio().isExist(categoryArrayList.get(positionn).getName());
              Log.e("bnbnb",check.toString());
                        if(check){
                           // db.localCatDio().updateSheetData(desCatEntityLocal);
                            db.localCatDio().update(categoryArrayList.get(positionn).getValue().get(position),
                                    categoryArrayList.get(positionn).getPrice().get(position),categoryArrayList.get(positionn).getName());

                        }else {
                        db.localCatDio().insertSheetData(desCatEntityLocal);
                        }
                        // Person person = mDb.personDao().loadPersonById(mPersonId);
                        populateUI();
                    }

                    private void populateUI() {

                        dataSizeInterface.onSend(position);
                        //finish();
                    }
                });



               /* if(!hashMap.isEmpty()) {
                    Iterator it = hashMap.entrySet().iterator();
                    while(it.hasNext()) {
                        Map.Entry obj = (Map.Entry)it.next();
                        System.out.println("jojojo"+obj.getValue());
                    }
                }*/

            }
        });
        /*holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSelectedItem = getAdapterPosition();
                notifyDataSetChanged();
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return categoryArrayList.get(positionn).getValue().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.rdgrb)
        RadioButton radioGroup;
        @BindView(R.id.rdvalue)
        TextView radiovalue;
        @BindView(R.id.product_price)
        TextView price;




        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface DataSizeInterface {
        public void onSend(int postion);
    }

}
