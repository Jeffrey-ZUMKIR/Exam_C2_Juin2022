package org.o7planning.kittenhall;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.o7planning.kittenhall.bean.NFT;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class NftPage extends AppCompatActivity {

    private final String EXTRA_USER = "user id";
    private final String EXTRA_NFTID = "nft_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nft_page);

        MyDatabaseHelper db = MainActivity.db;



        Intent monIntent = getIntent();
        String strUser = monIntent.getStringExtra(EXTRA_USER);
        String str_id_nft= monIntent.getStringExtra(EXTRA_NFTID);
        int id_nft = Integer.parseInt(str_id_nft);

        ImageView imgBtn = (ImageView) findViewById(R.id.img_backBtn);
        imgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent monIntent = new Intent(NftPage.this, Profil.class);
                monIntent.putExtra(EXTRA_USER, strUser);
                startActivity(monIntent);
                finish();
            }
        });

        /*DecimalFormat df = new DecimalFormat("#.####");
        df.setRoundingMode(RoundingMode.CEILING);*/

        NFT nft = db.getNFTById(id_nft);

        ImageView imgNft = (ImageView) findViewById(R.id.img_profil_nft);
        int imageId = nft.getId_image();
        imgNft.setImageResource(imageId);

        TextView txt_nom = (TextView) findViewById(R.id.txt_nomNFT);
        txt_nom.setText(nft.getNom());

        TextView txt_valbase = (TextView) findViewById(R.id.txt_valbase);
        double prixValRound = Math.round(nft.getVal_eur() * 100.0) / 100.0;
        txt_valbase.setText("Valeur de base : " + String.valueOf(prixValRound) + " €");

        TextView txt_valeth = (TextView) findViewById(R.id.txt_valEth);
        double prixEurEth = nft.getVal_eth()*MainActivity.cout_eur;
        double prixEurRoundEth = Math.round(prixEurEth * 100.0) / 100.0;
        txt_valeth.setText(Double.toString(nft.getVal_eth()) + " ETH (" + prixEurRoundEth + "€)");
        //Pourcentage eur
        TextView txt_percentEth = (TextView) findViewById(R.id.txt_percentEth);
        //double percentEth = (nft.getVal_eur() - prixEurRoundEth)/prixEurRoundEth;
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

        TextView txt_valbtc = (TextView) findViewById(R.id.txt_valBtc);
        double prixEurBtc = nft.getVal_eth()*MainActivity.cout_btc;
        double prixEurRoundBtc = Math.round(prixEurBtc * 100.0) / 100.0;//Double.parseDouble(df.format(String.valueOf(prixEurBtc)));//
        txt_valbtc.setText(Double.toString(nft.getVal_eth()*MainActivity.cout_btc) + " BTC");
        //Pourcentage btc
        TextView txt_percentBtc = (TextView) findViewById(R.id.txt_percentBtc);
        //double percentBtc = (nft.getVal_btc() - prixEurRoundBtc)/prixEurRoundBtc;
        double percentBtc = ((prixEurRoundBtc - nft.getVal_btc())/nft.getVal_btc())*100;
        double percentRoundBtc = Math.round(percentBtc * 100.0) / 100.0;//Double.parseDouble(df.format(String.valueOf(percentBtc)));//
        if(percentRoundBtc>0){
            txt_percentBtc.setText("+" + Double.toString(percentRoundBtc) + "%");
            txt_percentBtc.setTextColor(Color.GREEN);
        }else{
            txt_percentBtc.setText(Double.toString(percentRoundBtc) + "%");
            if(percentRoundBtc < 0.0){
                txt_percentBtc.setTextColor(Color.RED);
            }
        }


        TextView txt_valxlm = (TextView) findViewById(R.id.txt_valXlm);
        double prixEurXlm = nft.getVal_eth()*MainActivity.cout_xlm;
        double prixEurRoundXlm = Math.round(prixEurXlm * 100.0) / 100.0;
        txt_valxlm.setText(Double.toString(prixEurRoundXlm) + " XLM");
        //Pourcentage btc
        TextView txt_percentXlm = (TextView) findViewById(R.id.txt_percentXlm);
        double percentXlm = ((prixEurRoundXlm - nft.getVal_xlm())/nft.getVal_xlm())*100;
        //double percentXlm = (nft.getVal_xlm() - prixEurRoundXlm)/prixEurRoundXlm;
        double percentRoundXlm = Math.round(percentXlm * 100.0) / 100.0;
        if(percentRoundXlm>0){
            txt_percentXlm.setText("+" + Double.toString(percentRoundXlm) + "%");
            txt_percentXlm.setTextColor(Color.GREEN);
        }else{
            txt_percentXlm.setText(Double.toString(percentRoundXlm) + "%");
            if(percentRoundXlm < 0.0){
                txt_percentXlm.setTextColor(Color.RED);
            }
        }

    }
}