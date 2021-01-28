package com.idestore.services;

import android.util.Log;

public class RetrofitCalls {

    public GetDataService apiInterface;

    public RetrofitCalls() {
        Log.e("how","how it work");
        //apiInterface = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
    }
}
