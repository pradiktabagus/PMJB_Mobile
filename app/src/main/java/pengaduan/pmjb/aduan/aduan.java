package pengaduan.pmjb.aduan;

/**
 * Created by dikta on 26/10/2017.
 */

public class aduan {
   private String nama, judul, img, id_user;
   public aduan(){

   }
   public aduan(String nama, String judul, String img, String id_user){
       this.id_user = id_user;
       this.nama = nama;
       this.judul = judul;
       this.img = img;
   }
   public String getId_user(){
       return id_user;
   }
    public void setId_user(String id_user){
        this.id_user = id_user;
    }
    public String getNama(){

        return nama;
    }
    public void setNama(String nama){
        this.nama = nama;
    }
    public String getJudul(){
        return judul;
    }
    public void setJudul(String judul){
        this.judul = judul;
    }
    public String getImg(){
        return img;
    }
    public void setImg(String img){
        this.img = img;
    }
}
