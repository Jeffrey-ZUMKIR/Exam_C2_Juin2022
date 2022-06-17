package org.o7planning.kittenhall;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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

        ImageView imgBtn = (ImageView) findViewById(R.id.img_decoBtn);
        imgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent monIntent = new Intent(Profil.this, Connection.class);
                monIntent.putExtra(EXTRA_USER, strUser);
                startActivity(monIntent);
                finish();
            }
        });

        TextView txt_pseudo = (TextView) findViewById(R.id.txtPseudo);

        Utilisateur user = db.getUtilisateur(strUser);

        txt_pseudo.setText(user.getPseudo());

        TextView txt_money = (TextView) findViewById(R.id.txt_money);

        txt_money.setText(String.valueOf(user.getSolde()) + " €");

        ImageView btn_shop = (ImageView) findViewById(R.id.btn_shop);

        btn_shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent monIntent = new Intent(Profil.this, Magasin.class);
                monIntent.putExtra(EXTRA_USER, strUser);
                startActivity(monIntent);
                finish();
            }
        });

        Button btn_spoiledKitten = (Button) findViewById(R.id.btn_spoiledKitten);

        btn_spoiledKitten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Aller jouer à spoiled kitten", Toast.LENGTH_SHORT).show();
            }
        });



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
                //monIntent.putExtra(EXTRA_NFTID, nft.getId_nft());
                Log.i("This is the id sended", Integer.toString(nft.getId_nft()));
                monIntent.putExtra(EXTRA_NFTID, Integer.toString(nft.getId_nft()));
                monIntent.putExtra(EXTRA_USER, strUser);

                startActivity(monIntent);
                finish();
            }
        });
        
    }
}