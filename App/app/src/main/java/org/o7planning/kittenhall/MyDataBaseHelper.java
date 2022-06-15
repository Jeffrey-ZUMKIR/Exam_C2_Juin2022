package org.o7planning.kittenhall;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

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
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Log.i(TAG, "MyDatabaseHelper.onUpgrade ... ");
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_UTILISATEUR);

        // Create tables again
        onCreate(db);
    }


    // If Utilisateur table has no data
    // default, Insert 2 records.
    public void createDefaultUtilisateursIfNeed()  {
        int count = this.getUsersCount();
        if(count ==0 ) {
            Utilisateur user1 = new Utilisateur("admin1", "123", 0,0,0,0,0,0,0,0,0);
            Utilisateur user2 = new Utilisateur("admin2", "123", 0,0,0,0,0,0,0,0,0);
            this.addUser(user1);
            this.addUser(user2);
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


    public Utilisateur getUtilisateur(String pseudo) {
        Log.i(TAG, "MyDatabaseHelper.getNote ... " + pseudo);

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


    public List<Utilisateur> getAllUsers() {
        Log.i(TAG, "MyDatabaseHelper.getAllNotes ... " );

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


    public int updateUser(Utilisateur user) {
        Log.i(TAG, "MyDatabaseHelper.updateNote ... "  + user.toString());

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

    public void deleteUser(Utilisateur user) {
        Log.i(TAG, "MyDatabaseHelper.updateNote ... " + user.toString() );

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_UTILISATEUR, COLUMN_UTILISATEUR_PSEUDO + " = ?",
                new String[] { String.valueOf(user.getPseudo()) });
        db.close();
    }

}
