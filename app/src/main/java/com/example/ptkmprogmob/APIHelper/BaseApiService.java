package com.example.ptkmprogmob.APIHelper;

import com.example.ptkmprogmob.Model.DetailSkp;
import com.example.ptkmprogmob.Model.Kepanitiaan;
import com.example.ptkmprogmob.Model.Skp;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.GET;
import retrofit2.http.Part;
import retrofit2.http.PartMap;

public interface BaseApiService {
    // Fungsi ini untuk memanggil API http://10.0.2.2/mahasiswa/login.php
    @FormUrlEncoded
    @POST("login")
    Call<ResponseBody> loginRequest(@Field("nim") String nim,
                                    @Field("password") String password);

    // Fungsi ini untuk memanggil API http://10.0.2.2/mahasiswa/register.php
    @FormUrlEncoded
    @POST("register")
    Call<ResponseBody> registerRequest(@Field("nim") String nim,
                                       @Field("name") String nama,
                                       @Field("email") String email,
                                       @Field("phone") String phone,
                                       @Field("password") String password,
                                       @Field("c_password") String cpassword);


    // Fungsi detail user profile
    @FormUrlEncoded
    @POST("detail")
    Call<ResponseBody> detailUser(@Field("id") Integer id);

    // Fungsi ini untuk memanggil
    @FormUrlEncoded
    @POST("user")
    Call<ResponseBody> updateUser(@Field("id") String id,
                                  @Field("nim") String nim,
                                  @Field("name") String nama,
                                  @Field("email") String email,
                                  @Field("phone") String phone);

    // Fungsi ini untuk memanggil
    @GET("showDetail")
    Call<List<DetailSkp>> showDetail();

    // Fungsi ini untuk memanggil
    @Multipart
    @POST("createSkp")
    Call<ResponseBody> createSkp(
            @Part MultipartBody.Part image,
            @PartMap Map<String, RequestBody> text);

    // Fungsi ini untuk memanggil
    @GET("skp")
    Call<List<Skp>> listSkp();

    // Fungsi ini untuk memanggil
    @Multipart
    @POST("createKegiatan")
    Call<ResponseBody> createKegiatan(
            @Part MultipartBody.Part image,
            @PartMap Map<String, RequestBody> text);

    // Fungsi ini untuk memanggil
    @GET("kegiatan")
    Call<List<Kepanitiaan>> listKegiatan();
}
