package com.example.music.Service;

public class APIService {
    private static String url = "https://duong180199.000webhostapp.com/Server/";
    public static Dataservice getService(){
        return APIRetrofitClient.getClient(url).create(Dataservice.class);
    }
}
