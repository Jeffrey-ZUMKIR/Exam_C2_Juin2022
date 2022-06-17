package org.o7planning.kittenhall;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.o7planning.kittenhall.bean.Utilisateur;

public class Magasin extends AppCompatActivity {

    private final String EXTRA_USER = "user id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_magasin);

        Intent monIntent = getIntent();
        String strUser = monIntent.getStringExtra(EXTRA_USER);

        MyDatabaseHelper db = MainActivity.db;

        ImageView imgBtn = (ImageView) findViewById(R.id.img_backMagasinBtn);
        imgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent monIntent = new Intent(Magasin.this, Profil.class);
                monIntent.putExtra(EXTRA_USER, strUser);
                startActivity(monIntent);
                finish();
            }
        });

        Utilisateur user = db.getUtilisateur(strUser);

        TextView txt_money = (TextView) findViewById(R.id.txt_money2);

        txt_money.setText(String.valueOf(user.getSolde()) + " €");


        Button btn_shopVente = (Button) findViewById(R.id.btn_shopVendre);
        btn_shopVente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent monIntent = new Intent(Magasin.this, PageVendre.class);
                monIntent.putExtra(EXTRA_USER, strUser);
                startActivity(monIntent);
                finish();
            }
        });

        Button btn_shopAchat = (Button) findViewById(R.id.btn_shopAcheter);
        btn_shopAchat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent monIntent = new Intent(Magasin.this, PageAcheter.class);
                monIntent.putExtra(EXTRA_USER, strUser);
                startActivity(monIntent);
                finish();
            }
        });
    }
}