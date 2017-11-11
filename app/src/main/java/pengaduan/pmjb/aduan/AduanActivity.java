package pengaduan.pmjb.aduan;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import pengaduan.pmjb.MainActivity;
import pengaduan.pmjb.R;

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
        aduan s = new aduan();
        s.setId_user("coba");
        s.setImg("coba");
        adlist.add(s);
        s.setJudul("megumi");
        s.setNama("coba");
        adlist.add(s);

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
