package pengaduan.pmjb.aduan;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import pengaduan.pmjb.ApiVolley;
import pengaduan.pmjb.MainActivity;
import pengaduan.pmjb.R;
import pengaduan.pmjb.webserviceURL;

public class AduanActivity extends AppCompatActivity {
    ImageButton imageButtonBack;
    private ProgressDialog progressDialog;
    private List<aduan>adlist = new ArrayList<aduan>();
    GridView grid;
    private ListView listView;
    private aduanAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aduan);
        grid = (GridView) findViewById(R.id.grid_list);
        adapter = new aduanAdapter(this, adlist);
        grid.setAdapter(adapter);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        aduan ar= new aduan();

        adlist.clear();
        JSONObject json = new JSONObject();
        ApiVolley req = new ApiVolley(AduanActivity.this, json, "GET", "http://172.20.10.2/admin/api/pengaduan/all_pengaduan", "",
                "", 0, new ApiVolley.VolleyCallback() {
            @Override
            public void onSuccess(String result) {
                Log.d("cekk",result.toString());
                hideprogressDialog();
                // Important Note : need to use try catch when parsing JSONObject, no need when parsing string
                try {
                    JSONObject responseAPI = new JSONObject(result);
                    JSONArray arr = responseAPI.getJSONArray("response");
                    String status = responseAPI.getJSONObject("metadata").getString("status");
                    responseAPI = null;

                    for(int i=0;i<arr.length();i++){
                        JSONObject ar = arr.getJSONObject(i);
                        Double lat=Double.parseDouble(ar.getString("latitude"));
                        Double longi=Double.parseDouble(ar.getString("longtitude"));
                        int kategori=Integer.parseInt(ar.getString("id_kategori"));

                        Log.d("cek isi",ar.getString("detail_lokasi"));

                        aduan a= new aduan();
                        a.setDetail_lokasi(ar.getString("detail_lokasi"));
                        a.setLatitude(ar.getString("latitude"));
                        a.setLongtitude(ar.getString("longtitude"));
                        a.setId_firebase(ar.getString("id_firebase"));
                        a.setImg(ar.getString("img"));
                        adlist.add(a);
                    }
                    //Log.d("cek isi",ar.getString("judul_acara"));

                } catch (Exception e) {

                    e.printStackTrace();
//                    Toast.makeText(context, e.toString(), Toast.LENGTH_LONG).show();
                    //Toast.makeText(getActivity(), "Terjadi kesalahan saat memuat data", Toast.LENGTH_LONG).show();
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(String result) {
                Log.d("cek","eror");
            }
        });

        //button back
        imageButtonBack = (ImageButton)findViewById(R.id.back_aduan);
        imageButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AduanActivity.this,
                        MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        hideprogressDialog();
    }
    private void hideprogressDialog(){
        if (progressDialog != null){
            progressDialog.dismiss();
            progressDialog = null;
        }
    }
}
