package org.o7planning.kittenhall.bean;

import android.graphics.Bitmap;

import java.sql.Blob;

public class NFT {
    private int id_nft;
    //private String id_image;
    private int id_image;
    private String nom;
    private double val_eur;
    private double val_eth;
    private double val_btc;
    private double val_xlm;
    private String pseudo;

    public NFT()  {
        this.id_nft = 0;
        //this.id_image = "null";
        this.id_image = 0;
        this.nom = "null";
        this.val_eur = 0.0;
        this.val_eth = 0.0;
        this.val_btc = 0.0;
        this.val_xlm = 0.0;
        this.pseudo = "null";
    }

    public NFT(int id_nft, int id_image, String nom, double val_eur, double val_eth, double val_btc, double val_xlm, String pseudo) {
        this.id_nft = id_nft;
        this.id_image = id_image;
        this.nom = nom;
        this.val_eur = val_eur;
        this.val_eth = val_eth;
        this.val_btc = val_btc;
        this.val_xlm = val_xlm;
        this.pseudo = pseudo;
    }

    public int getId_nft(){return id_nft;}
    public void setId_nft(int id_nft){this.id_nft = id_nft;}

    public int getId_image(){return id_image;}
    public void setId_image(int id_image){this.id_image = id_image;}

    public String getNom(){return nom;}
    public void setNom(String nom){this.nom = nom;}

    public double getVal_eur(){return val_eur;}
    public void setVal_eur(double val_eur){this.val_eur = val_eur;}

    public double getVal_eth(){return val_eth;}
    public void setVal_eth(double val_eth){this.val_eth = val_eth;}

    public double getVal_btc(){return val_btc;}
    public void setVal_btc(double val_btc){this.val_btc = val_btc;}

    public double getVal_xlm(){return val_xlm;}
    public void setVal_xlm(double val_xlm){this.val_xlm = val_xlm;}

    public String getPseudo(){return pseudo;}
    public void setPseudo(String pseudo){this.pseudo = pseudo;}

    @Override
    public String toString()  {
        return this.nom;
    }

}
