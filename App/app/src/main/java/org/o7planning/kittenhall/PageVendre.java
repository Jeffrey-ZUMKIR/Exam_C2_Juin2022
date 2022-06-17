package org.o7planning.kittenhall;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.o7planning.kittenhall.bean.NFT;
import org.o7planning.kittenhall.bean.Utilisateur;

import java.util.List;

public class PageVendre extends AppCompatActivity {

    private final String EXTRA_USER = "user id";
    private final String EXTRA_NFTID = "nft_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_vendre);

        Intent monIntent = getIntent();
        String strUser = monIntent.getStringExtra(EXTRA_USER);

        MyDatabaseHelper db = MainActivity.db;

        List<NFT> userNftList = db.getAllNftOfUser(strUser);

        //Log.i("NFT user : ", userNftList.get(1).getId_image());

        final ListView listView = (ListView) findViewById(R.id.listViewVente);
        listView.setAdapter(new CustomListAdapter(this, userNftList, MainActivity.cout_eur));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Object o = listView.getItemAtPosition(position);
                NFT nft = (NFT) o;

                //Perso perso = (Perso) o;

                Intent monIntent = new Intent(PageVendre.this, PageVendreNft.class);
                Log.i("This is the id sended", Integer.toString(nft.getId_nft()));
                monIntent.putExtra(EXTRA_NFTID, Integer.toString(nft.getId_nft()));
                monIntent.putExtra(EXTRA_USER, strUser);

                startActivity(monIntent);
                finish();
            }
        });

        Utilisateur user = db.getUtilisateur(strUser);

        TextView txt_money = (TextView) findViewById(R.id.txt_money3);

        txt_money.setText(String.valueOf(user.getSolde()) + " €");

        ImageView imgBtn = (ImageView) findViewById(R.id.img_backMagasinBtn);
        imgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent monIntent = new Intent(PageVendre.this, Magasin.class);
                monIntent.putExtra(EXTRA_USER, strUser);
                startActivity(monIntent);
                finish();
            }
        });
    }
}