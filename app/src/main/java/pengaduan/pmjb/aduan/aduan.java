package pengaduan.pmjb.aduan;

/**
 * Created by dikta on 26/10/2017.
 */

public class aduan {
   private String id_pengaduan, latitude, img, longtitude, detail_lokasi, id_firebase;
   public aduan(){

   }
   public aduan(String id_pengaduan, String latitude, String img, String longtitude, String detail_lokasi, String id_firebase){
       this.longtitude = longtitude;
       this.id_pengaduan = id_pengaduan;
       this.latitude = latitude;
       this.img = img;
       this.detail_lokasi = detail_lokasi;
       this.id_firebase = id_firebase;
   }
   public String getLongtitude(){
       return longtitude;
   }
    public void setLongtitude(String longtitude){
        this.longtitude = longtitude;
    }
    public String getId_pengaduan(){

        return id_pengaduan;
    }
    public void setId_pengaduan(String id_pengaduan){
        this.id_pengaduan = id_pengaduan;
    }
    public String getLatitude(){
        return latitude;
    }
    public void setLatitude(String latitude){
        this.latitude = latitude;
    }
    public String getImg(){
        return img;
    }
    public void setImg(String img){
        this.img = img;
    }

    public String getDetail_lokasi() {
        return detail_lokasi;
    }

    public void setDetail_lokasi(String detail_lokasi) {
        this.detail_lokasi = detail_lokasi;
    }

    public String getId_firebase() {
        return id_firebase;
    }

    public void setId_firebase(String id_firebase) {
        this.id_firebase = id_firebase;
    }
}
