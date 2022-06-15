package org.o7planning.kittenhall;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.widget.ArrayAdapter;


import org.json.JSONException;
import org.json.JSONObject;
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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyDatabaseHelper db = new MyDatabaseHelper(this);
        db.createDefaultUtilisateursIfNeed();

        List<Utilisateur> list=  db.getAllUsers();
        this.userList.addAll(list);

        //this.listViewAdapter = new ArrayAdapter<Utilisateur>(this,
          //      android.R.layout.simple_list_item_1, android.R.id.text1, this.userList);

        Log.i("User: ", userList.get(0).getPseudo());

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

        try {
            URL url = new URL("https://api.coingecko.com/api/v3/simple/price?ids=ethereum&vs_currencies=eur%2Cbtc%2Cusd");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            Log.i("mec", "je suis avant");
            conn.getResponseCode();
            if(conn.getResponseCode() == HttpsURLConnection.HTTP_OK){
                Log.i("Hey", "Mec cool");
                InputStream inputStream = null;
                try {
                    inputStream = conn.getInputStream();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                // ...que l'on transforme ici en String par simplicité d'usage (note :
                // il peut s'agit d'autre chose qu'un String pour
                // d'autres webservices, comme des images)
                String data = readStringData(inputStream);

                Log.i("Request", data);

                JSONObject js = new JSONObject(data);

                Log.i("cc", js.getJSONObject("ethereum").getString("btc"));
            }
            else {
                Log.i("Hey", "Va te faire foutre");
                String response = "FAILED"; // See documentation for more info on response handling
            }

        } catch (IOException | JSONException e) {
            //TODO Handle problems..
        }




        /*URL url = null;
        try {
            url = new URL("https://api.coingecko.com/api/v3/ping");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        URLConnection urlConnection = null;
        try {
            urlConnection = url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        InputStream in = null;
        try {
            in = urlConnection.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        //copyInputStreamToOutputStream(in, System.out);


        // On compose l'Url à appeler (ici, pour récupérer
        // la liste des repositories d'un utilisateur)
        /*URL url = null;
        try {
            url = new URL("https://api.coingecko.com/api/v3/ping");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpsURLConnection connection = null;
        try {
            connection = (HttpsURLConnection) url.openConnection();
            Log.i("cc", "mon pote");
        } catch (IOException e) {
            e.printStackTrace();
            Log.i("cc", "batard");
        }

        try {
            connection.setRequestMethod("GET");
            Log.i("cc", "mon pote2");
        } catch (ProtocolException e) {
            e.printStackTrace();
            Log.i("cc", "batard2");
        }
        connection.setRequestProperty("Content-Type", "application/json");

        try {
            connection.connect();
            Log.i("cc", "mon pote3");
        } catch (IOException e) {
            e.printStackTrace();
            Log.i("cc", "batard3");
        }
        // Une fois le téléchargement effectué, on récupère le code http
        // renvoyé par le serveur
        int statusCode = 0;
        try {
            statusCode = connection.getResponseCode();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 2eme log important pour savoir comment s'est déroulé l'appel
        Log.i("Connection", "Téléchargement terminé, code http : " + statusCode);

        // On vérifie si le status code est 200, ce qui signifie que
        // le serveur a pu exécuter correctement le webservice.
        if(statusCode == 200) {

            // Puis on récupère la donnée téléchargé sous la forme d'un InputStream
            InputStream inputStream = null;
            try {
                inputStream = connection.getInputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }

            // ...que l'on transforme ici en String par simplicité d'usage (note :
            // il peut s'agit d'autre chose qu'un String pour
            // d'autres webservices, comme des images)
            String data = readStringData(inputStream);

            Log.i("Request", data);
        }*/


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


}