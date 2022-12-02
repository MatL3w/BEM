package pl.mysterius.bem;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class BitcoinActivity extends AppCompatActivity {

    private TextView CoinmarketCap_label;
    private TextView CoinmarketCap_price;
    private TextView CoinmarketCap_marketcap;
    private TextView CoinmarketCap_circulatingsupply;
    private TextView CoinmarketCap_volume;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitcoin);

      CoinmarketCap_label=(TextView)findViewById(R.id.textView_coinmarketcap_label);
      CoinmarketCap_price=(TextView)findViewById(R.id.textView_coinmarketcap_price);
      CoinmarketCap_marketcap=(TextView)findViewById(R.id.textView_coinmarketcap_marketcap);
      CoinmarketCap_volume=(TextView)findViewById(R.id.textView_coinmarketcap_volume);
      CoinmarketCap_circulatingsupply=(TextView)findViewById(R.id.textView_coinmarketcap_circulatingsupply);

        InternetConnection_Bitcoin task= new InternetConnection_Bitcoin();
        task.execute();


        CoinmarketCap_label.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://coinmarketcap.com/currencies/bitcoin/"));
                startActivity(intent);
            }
        });


        TimerTask refresh = new TimerTask() {
            @Override
            public void run() {
                InternetConnection_Bitcoin task= new InternetConnection_Bitcoin();
                task.execute();
            }
        };
        Timer timer = new Timer();
        timer.schedule(refresh,5000,5000);


    }



    public class InternetConnection_Bitcoin extends AsyncTask<Void, Void, Void> {

        private Elements CoinmarketcapPrice=null;
        private Elements CoinmarketcapMarketCap=null;


        @Override
        protected Void doInBackground(Void... voids) {

            try{
                String url = "https://coinmarketcap.com/currencies/bitcoin/";

                Document document = Jsoup.connect(url).get();

                CoinmarketcapPrice =document.select("#quote_price");
                CoinmarketcapMarketCap=document.getElementsByClass("details-panel-item--marketcap-stats flex-container");


            }
            catch(IOException|NullPointerException  e){
            }






            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            super.onPostExecute(aVoid);

            if(CoinmarketcapPrice!=null)CoinmarketCap_price.setText("Price: "+CoinmarketcapPrice.text());
            if(CoinmarketcapMarketCap!=null)
            {
                String lol= CoinmarketcapMarketCap.text();
                String[] loll =lol.split(" ");
                CoinmarketCap_marketcap.setText("MarketCap: "+loll[2]+"$");
                CoinmarketCap_volume.setText("Volume: "+loll[8]+"$");
                CoinmarketCap_circulatingsupply.setText("Circulating Supply: "+loll[14]);

            }

        }


    }

}
