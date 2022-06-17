package org.o7planning.kittenhall;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.o7planning.kittenhall.bean.NFT;
import org.o7planning.kittenhall.bean.Utilisateur;

public class PageVendreNft extends AppCompatActivity {

    private final String EXTRA_USER = "user id";
    private final String EXTRA_NFTID = "nft_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_vendre_nft);

        Intent monIntent = getIntent();
        String strUser = monIntent.getStringExtra(EXTRA_USER);
        String str_id_nft= monIntent.getStringExtra(EXTRA_NFTID);
        int id_nft = Integer.parseInt(str_id_nft);

        MyDatabaseHelper db = MainActivity.db;

        ImageView imgBtn = (ImageView) findViewById(R.id.img_backShopVenteBtn);
        imgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent monIntent = new Intent(PageVendreNft.this, PageVendre.class);
                monIntent.putExtra(EXTRA_USER, strUser);
                startActivity(monIntent);
                finish();
            }
        });

        Utilisateur user = db.getUtilisateur(strUser);

        TextView txt_money = (TextView) findViewById(R.id.txt_money4);

        txt_money.setText(String.valueOf(user.getSolde()) + " €");

        NFT nft = db.getNFTById(id_nft);

        ImageView imgNft = (ImageView) findViewById(R.id.img_profil_nft2);
        int imageId = nft.getId_image();
        imgNft.setImageResource(imageId);

        TextView txt_nom = (TextView) findViewById(R.id.txt_nomNFT2);
        txt_nom.setText(nft.getNom());

        TextView txt_valbase = (TextView) findViewById(R.id.txt_valbase2);
        double prixValRound = Math.round(nft.getVal_eur() * 100.0) / 100.0;
        txt_valbase.setText("Valeur de base : " + String.valueOf(prixValRound) + " €");

        TextView txt_valeth = (TextView) findViewById(R.id.txt_valEth2);
        double prixEurEth = nft.getVal_eth()*MainActivity.cout_eur;
        double prixEurRoundEth = Math.round(prixEurEth * 100.0) / 100.0;
        txt_valeth.setText(Double.toString(nft.getVal_eth()) + " ETH (" + prixEurRoundEth + "€)");
        //Pourcentage eur
        TextView txt_percentEth = (TextView) findViewById(R.id.txt_percentEth2);
        double percentEth = ((prixEurRoundEth - nft.getVal_eur())/nft.getVal_eur())*100;
        double percentRoundEth = Math.round(percentEth * 100.0) / 100.0;
        if(percentRoundEth>0){
            txt_percentEth.setText("+" + Double.toString(percentRoundEth) + "%");
            txt_percentEth.setTextColor(Color.GREEN);
        }else{
            txt_percentEth.setText(Double.toString(percentRoundEth) + "%");
            if(percentRoundEth < 0.0){
                txt_percentEth.setTextColor(Color.RED);
            }
        }

        Button btnVendre = (Button) findViewById(R.id.btn_vendre);
        btnVendre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //user.setSolde(user.getSolde() + prixEurRoundEth);
                //user.setPseudo("admin");
                //db.updateUser(user);

                db.updateUserSolde(strUser,user.getSolde() + prixEurRoundEth);
                db.updateOwnerNFT(nft.getId_nft(), "admin");

                Intent monIntent = new Intent(PageVendreNft.this, PageVendre.class);
                monIntent.putExtra(EXTRA_USER, strUser);
                startActivity(monIntent);
                finish();
            }
        });



    }
}