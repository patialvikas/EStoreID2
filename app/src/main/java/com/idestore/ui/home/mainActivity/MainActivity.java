package com.idestore.ui.home.mainActivity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputLayout;
import com.idestore.R;
import com.idestore.baseclass.BaseClass;
import com.idestore.services.RetrofitClientInstance;
import com.idestore.ui.authentication.loginActivity.LoginActivity;
import com.idestore.ui.home.cartActivity.CartActivity;
import com.idestore.ui.home.mainActivity.adapter.StoreAdapter;
import com.idestore.ui.home.myAccount.MyAccountActivity;
import com.idestore.ui.home.myOrders.MyOrdersActivity;
import com.idestore.ui.home.myfavrouitStore.MyFavrouitStoreActivity;
import com.idestore.ui.model.UserLoginModel;
import com.idestore.ui.model.VendorListModel;
import com.idestore.ui.settings.SettingsActivity;
import com.idestore.utils.UtilsFontFamily;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.HttpException;
import retrofit2.Response;

public class MainActivity extends BaseClass implements NavigationView.OnNavigationItemSelectedListener,
        SearchView.OnQueryTextListener, OnMapReadyCallback {

    @BindView(R.id.nav_view)
    NavigationView nav_view;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer_layout;

    @BindView(R.id.recylerviewStore)
    RecyclerView recylerviewStore;

    @BindView(R.id.layout_chagelocation)
    RelativeLayout layout_chagelocation;

    @BindView(R.id.layout_store)
    RelativeLayout layout_store;

    @BindView(R.id.layout_address)
    RelativeLayout layout_address;

    @BindView(R.id.layout_changeaddress)
    RelativeLayout layout_changeaddress;
    @BindView(R.id.editAddress)
    EditText editaddress;
    @BindView(R.id.editCity)
    EditText editCity;
    @BindView(R.id.editState)
    EditText editState;
    @BindView(R.id.editCountry)
    EditText editCountry;
    @BindView(R.id.editPincode)
    EditText editPincode;

    Context context;
    CircleImageView drawer_profileImage;
    TextView drawer_username;
    TextView drawer_location;

    StoreAdapter storeAdapter;
    ArrayList<VendorListModel.Dataa.Vendor> arrayList;
    VendorListModel vendorListModel;

    @BindView(R.id.change_address)
    TextView change_address;

    @BindView(R.id.btnSave)
    Button btnSave;

    @BindView(R.id.tvSortBy)
    TextView tvSortBy;

    @BindView(R.id.tvFilterBy)
    TextView tvFilterBy;

    @BindView(R.id.tvTittle)
    TextView tvTittle;

    @BindView(R.id.tvLocation)
    TextView tvLocation;


    @BindView(R.id.searchView)
    SearchView searchView;


    @BindView(R.id.radio_group)
    RadioGroup radioGroup;


    private boolean isSort = false;
    private boolean isFilter = false;

    boolean doubleBackToExitPressedOnce = false;
    double latt,langg;

    @BindView(R.id.map_view)
    MapView map_view;

    private GoogleMap gmap;

    private static final String MAP_VIEW_BUNDLE_KEY = "MapViewBundleKey";
    AddressModel addressModel;
    UpdateAddressModel updateAddressModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;
        arrayList = new ArrayList<VendorListModel.Dataa.Vendor>();
        vendorListModel = new VendorListModel();
        ButterKnife.bind(this);
        // init
        viewInitialization();
        initFonts();
        Bundle mapViewBundle = null;
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAP_VIEW_BUNDLE_KEY);
        }
        map_view.onCreate(mapViewBundle);
        map_view.getMapAsync(this);

        fetchLocationByApi();

    }

    private void fetchLocationByApi() {

        SharedPreferences mylogin = getSharedPreferences("MyLogin", Context.MODE_PRIVATE);
        String u_id = mylogin.getString("userid", null);
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("user_id", u_id);
        //requestBody.put("password", editPassword.getText().toString());

        RetrofitClientInstance.getRetrofitInstance().fetchAddress(requestBody
        ).enqueue(new Callback<AddressModel>() {
            @Override
            public void onResponse(Call<AddressModel> call, Response<AddressModel> response) {
                if (response.body().getStatus()) {
                    addressModel = response.body();

                    //Log.e("chooooo", tvGmail.getText().toString());
                    Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();

                    editaddress.setText(addressModel.data.getAddress());
                    editCity.setText(addressModel.data.getCity());
                    editCountry.setText(addressModel.data.getCountry());
                    editPincode.setText(addressModel.data.getZip());
                    editState.setText(addressModel.data.getState());

                    String address_type = addressModel.data.getAddressType();
                    tvLocation.setText(addressModel.data.getAddress() + "(" + address_type + ")");


                } else {
                    Toast.makeText(getApplicationContext(), "Invalid Login Detail.", Toast.LENGTH_LONG).show();
                }


            }

            @Override
            public void onFailure(Call<AddressModel> call, Throwable t) {
                // t.fillInStackTrace();
                Toast.makeText(getApplicationContext(), " Something Went Wrong.", Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        Bundle mapViewBundle = outState.getBundle(MAP_VIEW_BUNDLE_KEY);
        if (mapViewBundle == null) {
            mapViewBundle = new Bundle();
            outState.putBundle(MAP_VIEW_BUNDLE_KEY, mapViewBundle);
        }

        map_view.onSaveInstanceState(mapViewBundle);
    }

    private void initFonts() {
        tvTittle.setTypeface(UtilsFontFamily.typeFaceForRobotoBold(context));
        tvLocation.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(context));

    }

    private void viewInitialization() {

        Toolbar toolbar = findViewById(R.id.app_bar);
        drawer_layout = (DrawerLayout) findViewById(R.id.drawer_layout);
        nav_view = (NavigationView) findViewById(R.id.nav_view);

        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, drawer_layout, toolbar, R.string.drawer_open, R.string.drawer_close);
            drawer_layout.addDrawerListener(toggle);
            toggle.syncState();
            nav_view.setNavigationItemSelectedListener(this);
            setHeader();
        }

        setStoreData();

        searchView.setOnQueryTextListener(this);
        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvTittle.setVisibility(View.GONE);
            }
        });

        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                tvTittle.setVisibility(View.VISIBLE);
                return false;
            }
        });
    }

    private void setStoreData() {

        RetrofitClientInstance.getRetrofitInstance().vendorList().enqueue(new Callback<VendorListModel>() {
            @Override
            public void onResponse(Call<VendorListModel> call, Response<VendorListModel> response) {
                if (response.body().getStatus()) {
                    vendorListModel = response.body();
                    Log.e("work", "yes");
                    arrayList = (ArrayList<VendorListModel.Dataa.Vendor>) vendorListModel.getData().getVendor();
                    storeAdapter = new StoreAdapter(getApplicationContext(), arrayList);
                    recylerviewStore.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    recylerviewStore.setAdapter(storeAdapter);

                    //Log.e("chooooo", tvGmail.getText().toString());
                   /* Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();
                    //progressDialog.dismiss();
                    SharedPreferences mylogin =getSharedPreferences("MyLogin",Context.MODE_PRIVATE);
                    SharedPreferences.Editor ed=mylogin.edit();
                    ed.putString("Logstatus","true");
                    ed.putString("userid",String.valueOf(userLoginModel.getData().getUser().getId()));
                    ed.commit();

                    Intent intent=new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();*/

                } else {
                    Toast.makeText(getApplicationContext(), "Result was different.", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<VendorListModel> call, Throwable t) {
                t.fillInStackTrace();
                if (t instanceof HttpException) {
                    HttpException response = (HttpException) t;
                    int code = response.code();
                    String es = response.message();
                    Log.e("error code", String.valueOf(code) + es);
                }
                Toast.makeText(getApplicationContext(), "Invalid Api ", Toast.LENGTH_LONG).show();
            }


        });


    }

    private void setHeader() {
        View headerLayout =
                nav_view.inflateHeaderView(R.layout.layout_header);
        nav_view.getHeaderView(0);
        drawer_profileImage = headerLayout.findViewById(R.id.drawer_profileImage);
        drawer_username = headerLayout.findViewById(R.id.drawer_username);
        drawer_location = headerLayout.findViewById(R.id.drawer_location);

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Intent intent = null;
        int id = item.getItemId();
        switch (id) {
            case R.id.home:
                intent = new Intent(MainActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                return true;
            case R.id.myAccount:
                intent = new Intent(MainActivity.this, MyAccountActivity.class);
                startActivity(intent);
                return true;
            case R.id.cart:
                intent = new Intent(MainActivity.this, CartActivity.class);
                startActivity(intent);
                return true;
            case R.id.location:
                commingToast();
                return true;
            case R.id.myorders:
                intent = new Intent(MainActivity.this, MyOrdersActivity.class);
                startActivity(intent);
                return true;
            case R.id.myfavoritestore:
                intent = new Intent(MainActivity.this, MyFavrouitStoreActivity.class);
                startActivity(intent);
                return true;
            case R.id.settings:
                intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
                return true;
            case R.id.qrcode:
                commingToast();
                return true;
            case R.id.rewardcoinzone:
                commingToast();
                return true;
            case R.id.offerzone:
                commingToast();
                return true;
            case R.id.language:
                commingToast();
                return true;
            case R.id.job:
                commingToast();
                return true;
            case R.id.logout:
                logoutAccount();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void commingToast() {
        Toast.makeText(context, "Comming soon!", Toast.LENGTH_SHORT).show();
    }

    private void logoutAccount() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.DialogeTheme);
        builder.setCancelable(true);
        builder.setMessage("Are you sure you want to logout?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish(); // if the activity running has it's own context
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    @OnClick({R.id.change_address, R.id.btnSave, R.id.tvSortBy, R.id.tvFilterBy})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.change_address:
                layout_chagelocation.setVisibility(View.VISIBLE);
                layout_store.setVisibility(View.GONE);
                layout_changeaddress.setVisibility(View.VISIBLE);
                layout_address.setVisibility(View.GONE);

                //final InputMethodManager keyboard = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                //keyboard.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);
                break;
            case R.id.btnSave:
                layout_chagelocation.setVisibility(View.GONE);
                layout_store.setVisibility(View.VISIBLE);
                layout_changeaddress.setVisibility(View.INVISIBLE);
                layout_address.setVisibility(View.VISIBLE);
                if (editaddress.getText().toString() == "" || editCity.getText().toString() == "" ||
                        editCountry.getText().toString() == "" || editState.getText().toString() == "" || editPincode.getText().toString() == "") {
                    Toast.makeText(getApplicationContext(), "Pls Fill All Fields", Toast.LENGTH_LONG).show();
                } else {
                    updateAddresstoServer();
                }


                break;
            case R.id.tvSortBy:

                if (isSort == false) {
                    tvSortBy.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_sort_active, 0, 0, 0);
                    tvSortBy.setTextColor(getResources().getColor(R.color.colorBlue));
                    isSort = true;
                } else {
                    tvSortBy.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_sort, 0, 0, 0);
                    tvSortBy.setTextColor(getResources().getColor(R.color.colorBlack));
                    isSort = false;
                }

                break;
            case R.id.tvFilterBy:

                if (isFilter == false) {
                    tvFilterBy.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_filter_active, 0, 0, 0);
                    tvFilterBy.setTextColor(getResources().getColor(R.color.colorBlue));
                    isFilter = true;
                } else {
                    tvFilterBy.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_filter, 0, 0, 0);
                    tvFilterBy.setTextColor(getResources().getColor(R.color.colorBlack));
                    isFilter = false;
                }

                break;


        }
    }

    private void updateAddresstoServer() {
        SharedPreferences mylogin = getSharedPreferences("MyLogin", Context.MODE_PRIVATE);
        String u_id = mylogin.getString("userid", null);

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("user_id", u_id);
        requestBody.put("address", editaddress.getText().toString());
        requestBody.put("city", editCity.getText().toString());
        requestBody.put("state", editState.getText().toString());
        requestBody.put("country", editCountry.getText().toString());
        requestBody.put("zip", editPincode.getText().toString());
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radio_group);
        int radioButtonID = radioGroup.getCheckedRadioButtonId();
        RadioButton radioButton = (RadioButton) radioGroup.findViewById(radioButtonID);

        String selectedtext = (String) radioButton.getText();

        requestBody.put("addressType", selectedtext);

        RetrofitClientInstance.getRetrofitInstance().updateaddress(requestBody
        ).enqueue(new Callback<UpdateAddressModel>() {
            @Override
            public void onResponse(Call<UpdateAddressModel> call, Response<UpdateAddressModel> response) {
                if (response.body().getStatus()) {
                    updateAddressModel = response.body();

                    //Log.e("chooooo", tvGmail.getText().toString());
                    Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();
                    fetchLocationByApi();
                    //progressDialog.dismiss();


                } else {
                    Toast.makeText(getApplicationContext(), "Invalid  Detail.", Toast.LENGTH_LONG).show();
                }


            }

            @Override
            public void onFailure(Call<UpdateAddressModel> call, Throwable t) {
                // t.fillInStackTrace();
                Toast.makeText(getApplicationContext(), "Something Went Wrong.", Toast.LENGTH_LONG).show();
            }
        });


    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }

    @Override
    protected void onResume() {
        super.onResume();
        map_view.onResume();
        this.doubleBackToExitPressedOnce = false;
    }

    @Override
    protected void onStart() {
        super.onStart();
        map_view.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        map_view.onStop();
    }

    @Override
    protected void onPause() {
        map_view.onPause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        map_view.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 101) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                else{
                    String provider;
                    Location l;
                    Criteria c=new Criteria();
                    provider=lm.getBestProvider(c, false);
                    //now you have best provider
                    //get location
                    l=lm.getLastKnownLocation(provider);
                    if(l!=null)
                    {
                        langg=l.getLongitude();
                        latt=l.getLatitude();
                        //display on text view
                        // ln.setText(""+lng);
                        // lt.setText(""+lat);
                    }
                    else{
                        langg=30.67788;
                        latt=12.788;
                    }
                  //  Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
               //langg = location.getLongitude();
               // latt = location.getLatitude();
                    LatLng ny = new LatLng(latt, langg);
                    MarkerOptions markerOptions = new MarkerOptions();
                    markerOptions.position(ny);
                    gmap.addMarker(markerOptions);

                    gmap.moveCamera(CameraUpdateFactory.newLatLng(ny));
                }

            }
            }

    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        map_view.onLowMemory();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        gmap = googleMap;
        gmap.setMinZoomPreference(13);
        gmap.setIndoorEnabled(true);
        //gmap.setMyLocationEnabeled(true);
        UiSettings uiSettings = gmap.getUiSettings();
        uiSettings.setIndoorLevelPickerEnabled(true);
        uiSettings.setMyLocationButtonEnabled(true);
        uiSettings.setMapToolbarEnabled(true);
        uiSettings.setCompassEnabled(true);
        //uiSettings.setMyLocationEnabeled(true)
        uiSettings.setZoomControlsEnabled(true);
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            ActivityCompat.requestPermissions((Activity) context,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 101);
           // return;
        }
        else{
            String provider;
            Location l;
            Criteria c=new Criteria();
            provider=lm.getBestProvider(c, false);
            //now you have best provider
            //get location
            l=lm.getLastKnownLocation(provider);
            if(l!=null)
            {
                //get latitude and longitude of the location
                langg=l.getLongitude();
                latt=l.getLatitude();
                //display on text view
               // ln.setText(""+lng);
               // lt.setText(""+lat);
            }
            else{
                langg=30.67788;
                latt=12.788;
            }
            //Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            //langg = location.getLongitude();
            //latt = location.getLatitude();
        }


        LatLng ny = new LatLng(latt, langg);
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(ny);
        gmap.addMarker(markerOptions);

        gmap.moveCamera(CameraUpdateFactory.newLatLng(ny));
    }
}
