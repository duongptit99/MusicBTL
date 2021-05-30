package com.example.music.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class ChuDe implements Serializable {

@SerializedName("idChude")
@Expose
private String idkeychude;
@SerializedName("TenChuDe")
@Expose
private String tenchude;
@SerializedName("HinhChuDe")
@Expose
private String hinhchude;

public String getIdkeychude() {
return idkeychude;
}

public void setIdkeychude(String idkeychude) {
this.idkeychude = idkeychude;
}

public String getTenchude() {
return tenchude;
}

public void setTenchude(String tenchude) {
this.tenchude = tenchude;
}

public String getHinhchude() {
return hinhchude;
}

public void setHinhchude(String hinhchude) {
this.hinhchude = hinhchude;
}

}