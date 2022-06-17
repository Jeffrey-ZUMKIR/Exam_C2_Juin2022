package org.o7planning.kittenhall;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class NftPage extends AppCompatActivity {

    private final String EXTRA_NFTID = "nft_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nft_page);
    }
}