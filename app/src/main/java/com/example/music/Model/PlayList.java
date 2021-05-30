package com.example.music.Model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class PlayList implements Serializable {

@SerializedName("idPlayList")
@Expose
private String idPlayList;
@SerializedName("Ten")
@Expose
private String ten;
@SerializedName("Hinhnen")
@Expose
private String hinhPlayList;
@SerializedName("Hinhicon")
@Expose
private String icon;

public String getIdPlayList() {
return idPlayList;
}

public void setIdPlayList(String idPlayList) {
this.idPlayList = idPlayList;
}

public String getTen() {
return ten;
}

public void setTen(String ten) {
this.ten = ten;
}

public String getHinhPlayList() {
return hinhPlayList;
}

public void setHinhPlayList(String hinhPlayList) {
this.hinhPlayList = hinhPlayList;
}

public String getIcon() {
return icon;
}

public void setIcon(String icon) {
this.icon = icon;
}

}