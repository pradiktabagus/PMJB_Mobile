package pengaduan.pmjb.pengaduan;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

import java.io.FileNotFoundException;
import java.io.InputStream;

import pengaduan.pmjb.MainActivity;
import pengaduan.pmjb.Manifest;
import pengaduan.pmjb.R;

public class FormMengaduActivity extends AppCompatActivity {
    ImageButton imageButtonBack;
//    TextView textViewdetail;
    Button btnPilihGambar, btPlaceAPI;
    ImageView imageViewPilihGambar;
    private int PLACE_PICKER_REQUEST = 1;
    final int CODE_GALLERY_REQUEST = 999;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formmengadu);
        imageButtonBack = (ImageButton)findViewById(R.id.back_mengadu);
        imageButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FormMengaduActivity.this,
                        MainActivity.class);
                startActivity(intent);
            }
        });
        btnPilihGambar = (Button)findViewById(R.id.upload_btn);
        imageViewPilihGambar = (ImageView)findViewById(R.id.img_upload);
        btnPilihGambar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(
                        FormMengaduActivity.this,
                        new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE},
                        CODE_GALLERY_REQUEST
                );
            }
        });
//        textViewdetail = (TextView)findViewById(R.id.lokasi_detail);
        btPlaceAPI = (Button)findViewById(R.id.mark_lokasi);
        btPlaceAPI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                try {

                    startActivityForResult(builder.build(FormMengaduActivity.this), PLACE_PICKER_REQUEST);
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == CODE_GALLERY_REQUEST){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Select Image"), CODE_GALLERY_REQUEST);
            }else {
                Toast.makeText(getApplicationContext(), "You don't have permission to access gallery", Toast.LENGTH_LONG).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CODE_GALLERY_REQUEST && resultCode == RESULT_OK && data != null){
            Uri filePath = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(filePath);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imageViewPilihGambar.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
//        if (requestCode == PLACE_PICKER_REQUEST){
//            if (resultCode == RESULT_OK){
//                Place place = PlacePicker.getPlace(data, this);
//                String toastMsg = String.format(
//                        "Place: %s \n"+
//                                "Alamat: %s \n" +
//                                "Latlng %s \n", place.getName(), place.getAddress(), place.getLatLng().latitude+""+place.getLatLng().longitude
//                );
//                textViewdetail.setText(toastMsg);
//            }
//        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
