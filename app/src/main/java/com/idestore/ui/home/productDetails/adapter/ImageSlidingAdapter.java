package com.idestore.ui.home.productDetails.adapter;

import android.app.Dialog;
import android.content.Context;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.idestore.R;
import com.idestore.services.RetrofitClientInstance;
import com.idestore.ui.home.productDetails.responseModel.ImageModel;
import com.idestore.ui.model.ProductDetailModel;

import java.util.ArrayList;

public class ImageSlidingAdapter extends PagerAdapter {


    private ArrayList<ProductDetailModel.Data.Gallery> imageModelArrayList;
    private LayoutInflater inflater;
    private Context context;


    public ImageSlidingAdapter(Context context, ArrayList<ProductDetailModel.Data.Gallery> imageModelArrayList) {
        this.context = context;
        this.imageModelArrayList = imageModelArrayList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return imageModelArrayList.size();
    }

    @Override
    public Object instantiateItem(ViewGroup view, final int position) {
        View imageLayout = inflater.inflate(R.layout.layout_image_slider, view, false);

        assert imageLayout != null;
        final ImageView imageView = (ImageView) imageLayout
                .findViewById(R.id.image);
        Glide.with(context)
                .load(RetrofitClientInstance.IMG_GALLERY+imageModelArrayList.get(position).getPhoto())
                .placeholder(R.drawable.ic_launcher_background)
                .diskCacheStrategy(DiskCacheStrategy.ALL)//placeholder
                .error(R.drawable.ic_launcher_background) //error
                .into(imageView);

        //imageView.setImageResource(imageModelArrayList.get(position).getPhoto());

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Image Click "+position, Toast.LENGTH_SHORT).show();
                showImage(imageModelArrayList.get(position).getPhoto());
            }
        });

        view.addView(imageLayout, 0);

        return imageLayout;
    }

    private void showImage(String photo) {

        Dialog dialogAlert = new Dialog(context,R.style.MyCustomTheme);
        dialogAlert.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);

        dialogAlert.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogAlert.setContentView(R.layout.layout_image_zoom);
        ImageView img_selected_item = (ImageView) dialogAlert.findViewById(R.id.img_selected_item);
        Glide.with(context)
                .load(RetrofitClientInstance.IMG_GALLERY+photo)
                .placeholder(R.drawable.ic_launcher_background)
                .diskCacheStrategy(DiskCacheStrategy.ALL)//placeholder
                .error(R.drawable.ic_launcher_background) //error
                .into(img_selected_item);

       // img_selected_item.setImageResource(image_drawable);
        dialogAlert.show();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
    }

    @Override
    public Parcelable saveState() {
        return null;
    }


}