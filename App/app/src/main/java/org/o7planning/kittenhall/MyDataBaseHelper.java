package org.o7planning.kittenhall;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import org.o7planning.kittenhall.bean.NFT;
import org.o7planning.kittenhall.bean.Utilisateur;

import java.util.ArrayList;
import java.util.List;


public class MyDatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "SQLite";

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "KittenHall_BDD";

    // Table name: Note.
    /*private static final String TABLE_NOTE = "Note";

    private static final String COLUMN_NOTE_ID ="Note_Id";
    private static final String COLUMN_NOTE_TITLE ="Note_Title";
    private static final String COLUMN_NOTE_CONTENT = "Note_Content";*/

    // Table: Utilisateur
    private static final String TABLE_UTILISATEUR = "Utilisateur";

    private static final String COLUMN_UTILISATEUR_PSEUDO = "User_Pseudo";
    private static final String COLUMN_UTILISATEUR_MDP = "User_Mdp";
    private static final String COLUMN_UTILISATEUR_NBCONNEXION = "User_NbConnexion";
    private static final String COLUMN_UTILISATEUR_NBINSPECTNFT = "User_NbinspectNFT";
    private static final String COLUMN_UTILISATEUR_VISITEVENTE = "User_VisiteVente";
    private static final String COLUMN_UTILISATEUR_VISITEACHAT = "User_VisiteAchat";
    private static final String COLUMN_UTILISATEUR_VENTE = "User_Vente";
    private static final String COLUMN_UTILISATEUR_ACHAT = "User_Achat";
    private static final String COLUMN_UTILISATEUR_SOLDE = "User_Solde";
    private static final String COLUMN_UTILISATEUR_ARGINVEST = "User_ArgInvest";
    private static final String COLUMN_UTILISATEUR_ARGGAGNE = "User_ArgGagne";

    private static final String TABLE_NFT = "Nft";

    private static final String COLUMN_NFT_IDNFT = "Nft_Id";
    private static final String COLUMN_NFT_IDIMAGE = "Nft_Id_Image";
    private static final String COLUMN_NFT_NOM = "Nft_Nom";
    private static final String COLUMN_NFT_VALEUR = "Nft_ValEur";
    private static final String COLUMN_NFT_VALETH = "Nft_ValEth";
    private static final String COLUMN_NFT_VALBTC = "Nft_ValBtc";
    private static final String COLUMN_NFT_VALXLM = "Nft_ValXlm";
    private static final String COLUMN_NFT_PSEUDO = "Nft_Pseudo";

    public MyDatabaseHelper(Context context)  {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Create table
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, "MyDatabaseHelper.onCreate ... ");
        // Script.
        String script = "CREATE TABLE " + TABLE_UTILISATEUR + "("
                + COLUMN_UTILISATEUR_PSEUDO + " TEXT PRIMARY KEY,"
                + COLUMN_UTILISATEUR_MDP + " TEXT,"
                + COLUMN_UTILISATEUR_NBCONNEXION + " INTEGER,"
                + COLUMN_UTILISATEUR_NBINSPECTNFT + " INTEGER,"
                + COLUMN_UTILISATEUR_VISITEVENTE + " INTEGER,"
                + COLUMN_UTILISATEUR_VISITEACHAT + " INTEGER,"
                + COLUMN_UTILISATEUR_VENTE + " INTEGER,"
                + COLUMN_UTILISATEUR_ACHAT + " INTEGER,"
                + COLUMN_UTILISATEUR_SOLDE + " REAL,"
                + COLUMN_UTILISATEUR_ARGINVEST + " REAL,"
                + COLUMN_UTILISATEUR_ARGGAGNE + " REAL" + ")";
        // Execute Script.
        db.execSQL(script);

        String script2 = "CREATE TABLE " + TABLE_NFT + "("
                + COLUMN_NFT_IDNFT + " INTEGER PRIMARY KEY,"
                + COLUMN_NFT_IDIMAGE + " INTEGER,"
                + COLUMN_NFT_NOM + " TEXT,"
                + COLUMN_NFT_VALEUR + " REAL,"
                + COLUMN_NFT_VALETH + " REAL,"
                + COLUMN_NFT_VALBTC + " REAL,"
                + COLUMN_NFT_VALXLM + " REAL,"
                + COLUMN_NFT_PSEUDO + " TEXT" + ")";
        // Execute Script.
        db.execSQL(script2);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Log.i(TAG, "MyDatabaseHelper.onUpgrade ... ");
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_UTILISATEUR);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NFT);

        // Create tables again
        onCreate(db);
    }


    // If Utilisateur table has no data
    // default, Insert 2 records.
    public void createDefaultUtilisateursIfNeed()  {
        int count = this.getUsersCount();
        if(count ==0 ) {
            Utilisateur user1 = new Utilisateur("user1", "123", 0,0,0,0,0,0,0,0,0);
            Utilisateur user2 = new Utilisateur("user2", "123", 0,0,0,0,0,0,0,0,0);
            Utilisateur user3 = new Utilisateur("admin", "123", 0,0,0,0,0,0,0,0,0);
            this.addUser(user1);
            this.addUser(user2);
            this.addUser(user3);
        }
    }

    public void createDefaultNFTsIfNeed()  {
        int count = this.getNFTsCount();
        if(count ==0 ) {
            NFT nft1 = new NFT(1,R.drawable.nft1, "Chat gris", 0,0,0,0,"admin");
            NFT nft2 = new NFT(2,R.drawable.nft2, "Garfield", 0,0,0,0,"admin");
            this.addNft(nft1);
            this.addNft(nft2);

        }
    }


    public void addUser(Utilisateur user) {
        Log.i(TAG, "MyDatabaseHelper.addNote ... " + user.toString());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_UTILISATEUR_PSEUDO, user.getPseudo());
        values.put(COLUMN_UTILISATEUR_MDP, user.getMdp());
        values.put(COLUMN_UTILISATEUR_NBCONNEXION, user.getNb_connexion());
        values.put(COLUMN_UTILISATEUR_NBINSPECTNFT, user.getNb_inspection_nft());
        values.put(COLUMN_UTILISATEUR_VISITEVENTE, user.getNb_visite_vente());
        values.put(COLUMN_UTILISATEUR_VISITEACHAT, user.getNb_visite_achat());
        values.put(COLUMN_UTILISATEUR_VENTE, user.getNb_vente());
        values.put(COLUMN_UTILISATEUR_ACHAT, user.getNb_achat());
        values.put(COLUMN_UTILISATEUR_SOLDE, user.getSolde());
        values.put(COLUMN_UTILISATEUR_ARGINVEST, user.getArgent_investit());
        values.put(COLUMN_UTILISATEUR_ARGGAGNE, user.getArgent_gagne());

        // Inserting Row
        db.insert(TABLE_UTILISATEUR, null, values);

        // Closing database connection
        db.close();
    }

    public void addNft(NFT nft) {
        Log.i(TAG, "MyDatabaseHelper.addNote ... " + nft.toString());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_NFT_IDNFT, nft.getId_nft());
        values.put(COLUMN_NFT_IDIMAGE, nft.getId_image());
        values.put(COLUMN_NFT_NOM, nft.getNom());
        values.put(COLUMN_NFT_VALEUR, nft.getVal_eur());
        values.put(COLUMN_NFT_VALETH, nft.getVal_eth());
        values.put(COLUMN_NFT_VALBTC, nft.getVal_btc());
        values.put(COLUMN_NFT_VALXLM, nft.getVal_xlm());
        values.put(COLUMN_NFT_PSEUDO, nft.getPseudo());


        // Inserting Row
        db.insert(TABLE_NFT, null, values);

        // Closing database connection
        db.close();
    }


    public Utilisateur getUtilisateur(String pseudo) {
        Log.i(TAG, "MyDatabaseHelper.getUser ... " + pseudo);

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_UTILISATEUR, new String[] { COLUMN_UTILISATEUR_PSEUDO,
                        COLUMN_UTILISATEUR_MDP, COLUMN_UTILISATEUR_NBCONNEXION, COLUMN_UTILISATEUR_NBINSPECTNFT
                        , COLUMN_UTILISATEUR_VISITEVENTE, COLUMN_UTILISATEUR_VISITEACHAT, COLUMN_UTILISATEUR_VENTE
                        , COLUMN_UTILISATEUR_ACHAT, COLUMN_UTILISATEUR_SOLDE, COLUMN_UTILISATEUR_ARGINVEST
                        , COLUMN_UTILISATEUR_ARGGAGNE}, COLUMN_UTILISATEUR_PSEUDO + "=?",
                new String[] { String.valueOf(pseudo) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Utilisateur user = new Utilisateur(cursor.getString(0),cursor.getString(1), Integer.parseInt(cursor.getString(2)),
                    Integer.parseInt(cursor.getString(3)), Integer.parseInt(cursor.getString(4)), Integer.parseInt(cursor.getString(5)),
                    Integer.parseInt(cursor.getString(6)), Integer.parseInt(cursor.getString(7)), Double.parseDouble(cursor.getString(8)),
                    Double.parseDouble(cursor.getString(9)),Double.parseDouble(cursor.getString(10)));


        // return note
        return user;
    }

    public NFT getNFT(String id_image) {
        Log.i(TAG, "MyDatabaseHelper.getNFT ... " + id_image);

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NFT, new String[] { COLUMN_NFT_IDNFT,
                        COLUMN_NFT_IDIMAGE, COLUMN_NFT_NOM, COLUMN_NFT_VALEUR
                        , COLUMN_NFT_VALETH, COLUMN_NFT_VALBTC, COLUMN_NFT_VALXLM
                        , COLUMN_NFT_PSEUDO}, COLUMN_NFT_IDIMAGE + "=?",
                new String[] { String.valueOf(id_image) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        NFT nft = new NFT(Integer.parseInt(cursor.getString(0)),Integer.parseInt(cursor.getString(1)),cursor.getString(2),
                Double.parseDouble(cursor.getString(3)),Double.parseDouble(cursor.getString(4)),Double.parseDouble(cursor.getString(5)),
                Double.parseDouble(cursor.getString(6)),cursor.getString(7));

        return nft;
    }

    public List<NFT> getAllNftOfUser(String id_user){
        Log.i(TAG, "MyDatabaseHelper.getAllNFTOfUser ... " + id_user );

        List<NFT> nftList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NFT, new String[] { COLUMN_NFT_IDNFT,
                        COLUMN_NFT_IDIMAGE, COLUMN_NFT_NOM, COLUMN_NFT_VALEUR
                        , COLUMN_NFT_VALETH, COLUMN_NFT_VALBTC, COLUMN_NFT_VALXLM
                        , COLUMN_NFT_PSEUDO}, COLUMN_NFT_PSEUDO + "=?",
                new String[] { String.valueOf(id_user) }, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                NFT nft = new NFT(Integer.parseInt(cursor.getString(0)),Integer.parseInt(cursor.getString(1)),cursor.getString(2),
                        Double.parseDouble(cursor.getString(3)),Double.parseDouble(cursor.getString(4)),Double.parseDouble(cursor.getString(5)),
                        Double.parseDouble(cursor.getString(6)),cursor.getString(7));

                // Adding user to list
                nftList.add(nft);
            } while (cursor.moveToNext());
        }

        return nftList;
    }


    public List<Utilisateur> getAllUsers() {
        Log.i(TAG, "MyDatabaseHelper.getAllUsers ... " );

        List<Utilisateur> userList = new ArrayList<Utilisateur>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_UTILISATEUR;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Utilisateur user = new Utilisateur();
                user.setPseudo(cursor.getString(0));
                user.setMdp(cursor.getString(1));
                user.setNb_connexion(Integer.parseInt(cursor.getString(2)));
                user.setNb_inspection_nft(Integer.parseInt(cursor.getString(3)));
                user.setNb_visite_vente(Integer.parseInt(cursor.getString(4)));
                user.setNb_visite_achat(Integer.parseInt(cursor.getString(5)));
                user.setNb_vente(Integer.parseInt(cursor.getString(6)));
                user.setNb_achat(Integer.parseInt(cursor.getString(7)));
                user.setSolde(Double.parseDouble(cursor.getString(8)));
                user.setArgent_investit(Double.parseDouble(cursor.getString(9)));
                user.setArgent_gagne(Double.parseDouble(cursor.getString(10)));

                // Adding user to list
                userList.add(user);
            } while (cursor.moveToNext());
        }

        // return user list
        return userList;
    }

    public int getUsersCount() {
        Log.i(TAG, "MyDatabaseHelper.getNotesCount ... " );

        String countQuery = "SELECT  * FROM " + TABLE_UTILISATEUR;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();

        cursor.close();

        // return count
        return count;
    }

    public int getNFTsCount() {
        Log.i(TAG, "MyDatabaseHelper.getNotesCount ... " );

        String countQuery = "SELECT  * FROM " + TABLE_NFT;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();

        cursor.close();

        // return count
        return count;
    }


    public int updateUser(Utilisateur user) {
        Log.i(TAG, "MyDatabaseHelper.updateUser ... "  + user.toString());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_UTILISATEUR_PSEUDO, user.getPseudo());
        values.put(COLUMN_UTILISATEUR_MDP, user.getMdp());
        values.put(COLUMN_UTILISATEUR_NBCONNEXION, user.getNb_connexion());
        values.put(COLUMN_UTILISATEUR_NBINSPECTNFT, user.getNb_inspection_nft());
        values.put(COLUMN_UTILISATEUR_VISITEVENTE, user.getNb_visite_vente());
        values.put(COLUMN_UTILISATEUR_VISITEACHAT, user.getNb_visite_achat());
        values.put(COLUMN_UTILISATEUR_VENTE, user.getNb_vente());
        values.put(COLUMN_UTILISATEUR_ACHAT, user.getNb_achat());
        values.put(COLUMN_UTILISATEUR_SOLDE, user.getSolde());
        values.put(COLUMN_UTILISATEUR_ARGINVEST, user.getArgent_investit());
        values.put(COLUMN_UTILISATEUR_ARGGAGNE, user.getArgent_gagne());

        // updating row
        return db.update(TABLE_UTILISATEUR, values, COLUMN_UTILISATEUR_PSEUDO + " = ?",
                new String[]{String.valueOf(user.getPseudo())});
    }

    public int updateOwnerNFT(int id_nft, String new_owner){
        Log.i(TAG, "MyDatabaseHelper.updateNFT Owner  id :... "  + id_nft);

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_NFT_PSEUDO, new_owner);

        return db.update(TABLE_NFT, values, COLUMN_NFT_IDNFT + " =?",
                new String[]{String.valueOf(id_nft)});
    }

    public void deleteUser(Utilisateur user) {
        Log.i(TAG, "MyDatabaseHelper.updateUser ... " + user.toString() );

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_UTILISATEUR, COLUMN_UTILISATEUR_PSEUDO + " = ?",
                new String[] { String.valueOf(user.getPseudo()) });
        db.close();
    }

}
