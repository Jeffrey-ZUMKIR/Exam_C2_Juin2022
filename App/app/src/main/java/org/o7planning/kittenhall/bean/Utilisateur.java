package org.o7planning.kittenhall.bean;

import java.io.Serializable;

public class Utilisateur {
    private String pseudo;
    private String mdp;
    private int nb_connexion;
    private int nb_inspection_nft;
    private int nb_visite_vente;
    private int nb_visite_achat;
    private int nb_vente;
    private int nb_achat;
    private double solde;
    private double argent_investit;
    private double argent_gagne;

    public Utilisateur()  {
        this.pseudo = "null";
        this.mdp = "null";
        this.nb_connexion = 0;
        this.nb_inspection_nft = 0;
        this.nb_visite_vente = 0;
        this.nb_visite_achat = 0;
        this.nb_vente = 0;
        this.nb_achat = 0;
        this.solde = 0.0;
        this.argent_investit = 0.0;
        this.argent_gagne = 0.0;
    }

    public Utilisateur(String pseudo, String mdp, int nb_connexion, int nb_inspect, int nb_vis_vente, int nb_vis_achat, int nb_vente, int nb_achat, double solde, double arg_invest, double arg_gagne) {
        this.pseudo = pseudo;
        this.mdp = mdp;
        this.nb_connexion = nb_connexion;
        this.nb_inspection_nft = nb_inspect;
        this.nb_visite_vente = nb_vis_vente;
        this.nb_visite_achat = nb_vis_achat;
        this.nb_vente = nb_vente;
        this.nb_achat = nb_achat;
        this.solde = solde;
        this.argent_investit = arg_invest;
        this.argent_gagne = arg_gagne;
    }

    public String getPseudo(){
        return pseudo;
    }

    public void setPseudo(String pseudo){
        this.pseudo = pseudo;
    }

    public String getMdp(){
        return mdp;
    }

    public void setMdp(String mdp){
        this.mdp = mdp;
    }

    public int getNb_connexion(){
        return nb_connexion;
    }

    public void setNb_connexion(int nb_connexion){
        this.nb_connexion = nb_connexion;
    }

    public int getNb_inspection_nft(){
        return nb_inspection_nft;
    }

    public void setNb_inspection_nft(int nb_inspection_nft){
        this.nb_inspection_nft = nb_inspection_nft;
    }

    public int getNb_visite_vente(){
        return nb_visite_vente;
    }

    public void setNb_visite_vente(int nb_visite_vente){
        this.nb_visite_vente = nb_visite_vente;
    }

    public int getNb_visite_achat(){
        return nb_visite_achat;
    }

    public void setNb_visite_achat(int nb_visite_achat){
        this.nb_visite_achat = nb_visite_achat;
    }

    public int getNb_vente(){
        return nb_vente;
    }

    public void setNb_vente(int nb_vente){
        this.nb_vente = nb_vente;
    }

    public int getNb_achat(){
        return nb_achat;
    }

    public void setNb_achat(int nb_achat){
        this.nb_achat = nb_achat;
    }

    public double getSolde(){
        return solde;
    }

    public void setSolde(double solde){
        this.solde = solde;
    }

    public double getArgent_investit(){
        return argent_investit;
    }

    public void setArgent_investit(double argent_investit){
        this.argent_investit = argent_investit;
    }

    public double getArgent_gagne(){
        return argent_gagne;
    }

    public void setArgent_gagne(double argent_gagne){
        this.argent_gagne = argent_gagne;
    }







    @Override
    public String toString()  {
        return this.pseudo;
    }
}
