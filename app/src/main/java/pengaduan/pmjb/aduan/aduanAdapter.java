package pengaduan.pmjb.aduan;

import android.content.Context;
import android.provider.ContactsContract;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import pengaduan.pmjb.R;

/**
 * Created by dikta on 26/10/2017.
 */

public class aduanAdapter extends BaseAdapter {
    private Context mContext;
    private List<aduan> aduanList;
    public Integer[]mThumbIds = {
            R.drawable.jajal
    };
    //Constructor
    public aduanAdapter(Context c, List<aduan> adlist){
        mContext = c;
        this.aduanList = adlist;
    }
    @Override
    public int getCount() {
        return aduanList.size();
    }

    @Override
    public Object getItem(int position) {
        aduanList.get(position);
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View grid;
        TextView textViewLokasi, textViewKondisiJalan, textViewWaktu, textViewProgress;
        ImageView imageViewAduan;
        LayoutInflater inflater = (LayoutInflater)mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final aduan a = aduanList.get(position);
        if (convertView == null){
            grid = new ListView(mContext);
            grid = inflater.inflate(R.layout.list_aduan, null);
            textViewLokasi = (TextView) grid.findViewById(R.id.nama_lokasi);
            textViewKondisiJalan = (TextView) grid.findViewById(R.id.kondisi_jalan);
            textViewWaktu = (TextView)grid.findViewById(R.id.waktuaduan);
            textViewProgress = (TextView) grid.findViewById(R.id.progressaduan);
            imageViewAduan = (ImageView) grid.findViewById(R.id.img_thumbnail);
            textViewLokasi.setText(a.getDetail_lokasi());
        }else {
            grid = (View) convertView;
        }
        return grid;
    }
}
