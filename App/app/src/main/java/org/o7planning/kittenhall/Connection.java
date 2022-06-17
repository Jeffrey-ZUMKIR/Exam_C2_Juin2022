package org.o7planning.kittenhall;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.o7planning.kittenhall.bean.Utilisateur;

import java.util.ArrayList;
import java.util.List;

public class Connection extends AppCompatActivity {
    private final List<Utilisateur> userList = new ArrayList<Utilisateur>();

    private final String EXTRA_USER = "user id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection);

        MyDatabaseHelper db = MainActivity.db;

        List<Utilisateur> list=  db.getAllUsers();
        this.userList.addAll(list);

        TextView txt_pseudo = (TextView) findViewById(R.id.txt_pseudo);
        TextView txt_mdp = (TextView) findViewById(R.id.txt_mdp);

        Button btn_valider = (Button) findViewById(R.id.btn_valider);

        Log.i("User : ", userList.get(0).getPseudo());
        Log.i("Mdp : ", userList.get(0).getMdp());

        btn_valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("User : ", txt_pseudo.getText().toString());
                Log.i("Mdp : ", txt_mdp.getText().toString());

                int i = 0;
                boolean foundUser = false;
                while (i!= userList.size()){
                    if(txt_pseudo.getText().toString().equals(userList.get(i).getPseudo())){
                        if(txt_mdp.getText().toString().equals(userList.get(i).getMdp())){
                            foundUser = true;
                            i = userList.size();
                        }else{
                            i++;
                        }
                    }else{
                        i++;
                    }
                }

                if(foundUser){
                    Intent intent = new Intent(Connection.this, Profil.class);
                    intent.putExtra(EXTRA_USER, txt_pseudo.getText().toString());
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(), "Pseudo ou mot de passe incorrecte.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}