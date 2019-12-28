package com.example.ptkmprogmob.APIHelper;

public class UtilsApi {
    // 10.0.2.2 ini adalah localhost.
    public static final String BASE_URL_API = "https://apikelompok10.000webhostapp.com/api/";
    public static final String BASE_URL_IMAGE = "https://apikelompok10.000webhostapp.com/";

    // Mendeklarasikan Interface BaseApiService
    public static BaseApiService getAPIService(){
        return RetrofitClient.getClient(BASE_URL_API).create(BaseApiService.class);
    }
}
