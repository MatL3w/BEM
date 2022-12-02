package pl.mysterius.bem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentTransaction;
//import android.content.res.Configuration;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ImageView ImageView_Bitcoin;
    private ImageView ImageView_Ethereum;
    private ImageView ImageView_Monero;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.INTERNET},99);

        }

        ImageView_Bitcoin =(ImageView) findViewById(R.id.imageView_bitcoin);
        ImageView_Ethereum =(ImageView) findViewById(R.id.imageView_ethereum);
        ImageView_Monero =(ImageView) findViewById(R.id.imageView_monero);






        ImageView_Bitcoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BitcoinActivity.class);
                startActivity(intent);;
            }
        });
    }



}
