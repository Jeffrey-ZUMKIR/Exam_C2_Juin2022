package org.o7planning.kittenhall;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import org.o7planning.kittenhall.bean.NFT;
import org.o7planning.kittenhall.bean.Utilisateur;

import java.util.List;

public class Profil extends AppCompatActivity {

    private final String EXTRA_USER = "user id";
    private final String EXTRA_NFTID = "nft_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        Intent monIntent = getIntent();
        String strUser = monIntent.getStringExtra(EXTRA_USER);

        MyDatabaseHelper db = MainActivity.db;

        TextView txt_pseudo = (TextView) findViewById(R.id.txtPseudo);

        Utilisateur user = db.getUtilisateur(strUser);

        txt_pseudo.setText(user.getPseudo());

        List<NFT> userNftList = db.getAllNftOfUser(strUser);

        //Log.i("NFT user : ", userNftList.get(1).getId_image());

        final ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new CustomListAdapter(this, userNftList, MainActivity.cout_eur));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Object o = listView.getItemAtPosition(position);
                NFT nft = (NFT) o;

                //Perso perso = (Perso) o;

                Intent monIntent = new Intent(Profil.this, NftPage.class);
                //Perso perso = new Perso(persoTitre[i], persoDesc[i], persoImage[i]);
                //Toast.makeText(getApplicationContext(), perso.getDesc(), Toast.LENGTH_SHORT).show();
                monIntent.putExtra(EXTRA_NFTID, nft.getId_nft());

                startActivity(monIntent);
            }
        });
        
    }
}