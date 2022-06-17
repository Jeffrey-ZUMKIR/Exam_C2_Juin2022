package org.o7planning.kittenhall;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;


import org.json.JSONException;
import org.json.JSONObject;
import org.o7planning.kittenhall.bean.NFT;
import org.o7planning.kittenhall.bean.Utilisateur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;


public class MainActivity extends AppCompatActivity {

    private final List<Utilisateur> userList = new ArrayList<Utilisateur>();
    private ArrayAdapter<Utilisateur> listViewAdapter;

    private final String EXTRA_DATA_BASE = "data base";

    public static MyDatabaseHelper db;
    public static SQLiteDatabase db2;

    public static double cout_eur = 0;
    public static double cout_btc = 0;
    public static double cout_xlm = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new MyDatabaseHelper(this);
        db2 = db.getReadableDatabase();
        //db.onUpgrade(db2, 0, 1);
        db.createDefaultUtilisateursIfNeed();

        List<Utilisateur> list=  db.getAllUsers();
        this.userList.addAll(list);

        //this.listViewAdapter = new ArrayAdapter<Utilisateur>(this,
          //      android.R.layout.simple_list_item_1, android.R.id.text1, this.userList);

        /*Log.i("User: ", userList.get(0).getPseudo());

        Log.i("User : ", db.getUtilisateur("user1").getPseudo());

        Log.i("NFT : ", db.getNFT("default1").getPseudo());

        //db.updateOwnerNFT(db.getNFT("default1").getId_nft(),"admin3");

        Log.i("NFT owner : ", db.getNFT("default1").getPseudo());*/

        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectDiskReads()
                .detectDiskWrites()
                .detectNetwork()   // or .detectAll() for all detectable problems
                .penaltyLog()
                .build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectLeakedSqlLiteObjects()
                .detectLeakedClosableObjects()
                .penaltyLog()
                //.penaltyDeath()
                .build());

        /*double cout_eur = 0;
        double cout_btc = 0;
        double cout_xlm = 0;*/

        try {
            URL url = new URL("https://api.coingecko.com/api/v3/simple/price?ids=ethereum&vs_currencies=eur%2Cbtc%2Cxlm");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.getResponseCode();
            if(conn.getResponseCode() == HttpsURLConnection.HTTP_OK){
                InputStream inputStream = null;
                try {
                    inputStream = conn.getInputStream();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                String data = readStringData(inputStream);

                JSONObject js = new JSONObject(data);

                cout_eur = Double.parseDouble(js.getJSONObject("ethereum").getString("eur"));
                cout_btc = Double.parseDouble(js.getJSONObject("ethereum").getString("btc"));
                cout_xlm = Double.parseDouble(js.getJSONObject("ethereum").getString("xlm"));
                Log.i("Val eur", js.getJSONObject("ethereum").getString("eur"));
                Log.i("Val btc", js.getJSONObject("ethereum").getString("btc"));
                Log.i("Val xlm", js.getJSONObject("ethereum").getString("xlm"));
            }
            else {
                String response = "FAILED"; // See documentation for more info on response handling
            }

        } catch (IOException | JSONException e) {
            //TODO Handle problems..
        }



        Log.i("Number of NFT",Integer.toString(db.getNFTsCount()) );
        if(db.getNFTsCount() == 0){
            double val_eth = 0.03;
            double val_eur = val_eth * cout_eur;
            double val_btc = val_eth * cout_btc;
            double val_xlm = val_eth * cout_xlm;

            Log.i("Eur : ", Double.toString(val_eur));
            Log.i("Btc : ", Double.toString(val_btc));
            Log.i("Xlm : ", Double.toString(val_xlm));

            NFT nft1 = new NFT(1,R.drawable.nft1,"Chat gris",val_eur,val_eth,val_btc,val_xlm,"user1");
            db.addNft(nft1);

            val_eth = 0.06;
            val_eur = val_eth * cout_eur;
            val_btc = val_eth * cout_btc;
            val_xlm = val_eth * cout_xlm;

            NFT nft2 = new NFT(2,R.drawable.nft2,"Garfield",val_eur,val_eth,val_btc,val_xlm,"user1");
            db.addNft(nft2);

            val_eth = 0.05;
            val_eur = val_eth * cout_eur;
            val_btc = val_eth * cout_btc;
            val_xlm = val_eth * cout_xlm;

            NFT nft3 = new NFT(3,R.drawable.nft3,"Sleepy",val_eur,val_eth,val_btc,val_xlm,"user2");
            db.addNft(nft3);

            val_eth = 0.12;
            val_eur = val_eth * cout_eur;
            val_btc = val_eth * cout_btc;
            val_xlm = val_eth * cout_xlm;

            NFT nft4 = new NFT(4,R.drawable.nft4,"Dumby",val_eur,val_eth,val_btc,val_xlm,"admin");
            db.addNft(nft4);

            val_eth = 0.09;
            val_eur = val_eth * cout_eur;
            val_btc = val_eth * cout_btc;
            val_xlm = val_eth * cout_xlm;

            NFT nft5 = new NFT(5,R.drawable.nft5,"King",val_eur,val_eth,val_btc,val_xlm,"admin");
            db.addNft(nft5);
        }










        //connection.disconnect();

        //Log.i("Request", contentType);
    }

    private String readStringData(InputStream stream)  {
        BufferedReader reader = null;
        StringBuilder result = new StringBuilder();
        try {
            reader = new BufferedReader(new InputStreamReader(stream, "UTF-8"));

            String line;
            while((line = reader.readLine()) != null) {
                result.append(line);
            }
            return result.toString();
            //return null;

        } catch (IOException e) {
            Log.e(getClass().getSimpleName(), "not working", e);

        } finally {
            // On ferme tout les flux dans tout les cas
            if(reader != null){
                try {
                    reader.close();

                } catch (IOException exp2) {
                    Log.e(getClass().getSimpleName(), "not working again", exp2);
                }
            }
        }
        return null;
    }

    final Handler h = new Handler();
    final Runnable r = () -> {
        if(!isFinishing()) {
            Intent intent = new Intent(MainActivity.this, Connection.class);
            startActivity(intent);
            finish();
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        h.postDelayed(r, 2000);
    }

    @Override
    protected void onPause() {
        super.onPause();
        h.removeCallbacks(r);
    }


}