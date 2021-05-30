package com.example.music.Service;
import com.example.music.Model.Album;
import com.example.music.Model.BaiHat;
import com.example.music.Model.ChuDe;
import com.example.music.Model.ChuDevaTheLoai;
import com.example.music.Model.PlayList;
import com.example.music.Model.QuangCao;
import com.example.music.Model.TheLoai;
import com.example.music.Model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface Dataservice {
    @GET("songbanner.php")
    Call<List<QuangCao>> GetDataBanner();

    @GET("playlistforcurrent.php")
    Call<List<PlayList>> GetPlaylistCurrent();

    @GET ("chudevatheloaitrongngay.php")
    Call<ChuDevaTheLoai> GetCategoryMusic();

    @GET ("theloai.php")
    Call<List<TheLoai>> GetCategory();

    @GET("albumhot.php")
    Call<List<Album>> GetAlbumHot();

    @GET("baihatduocyeuthich.php")
    Call<List<BaiHat>> GetBaiHat();

    @GET("chude.php")
    Call<List<ChuDe>> GetChuDe();

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>> GetDanhSachbaihatquangcao(@Field("idquangcao") String idquangcao);

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>> GetDanhSachbaihatplaylist(@Field("idplaylist") String idplaylist);

    @GET("danhsachplaylist.php")
    Call<List<PlayList>> GetDSPLayList();

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>> GetDanhSachBaiHatTheoTheLoai(@Field("idtheloai") String idtheloai);

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>> GetDanhSachChuDe(@Field("idchude") String idchude);

    @GET("tatcaalbum.php")
    Call<List<Album>> GetAllAlbum();

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>> GetDanhSachBaiHatTheoAlbum(@Field("idalbum") String idalbum);

    @FormUrlEncoded
    @POST("updateluotthich.php")
    Call<String> UpdateLuotThich(@Field("luotthich") String luotthich,@Field("idbaihat") String idbaihat);

    @FormUrlEncoded
    @POST("searchbaihat.php")
    Call<List<BaiHat>> GetSearchBaiHat(@Field("tukhoa") String tukhoa);

    @FormUrlEncoded
    @POST("user.php")
    Call<String> GetUser(@Field("user") String username,@Field("pass") String password);

}
