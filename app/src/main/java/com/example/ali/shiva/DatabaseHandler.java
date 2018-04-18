package com.example.ali.shiva;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Ali on 18/08/17.
 */

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    String v;
    // Database Name
    private static final String DATABASE_NAME = "contactsManager";

    // Contacts table name
    private static final String TABLE_CONTACTS = "contacts";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_AZAN = "azan";
    private static final String KEY_TOLO = "tolo";
    private static final String KEY_BIDAR = "bidar";
    private static final String KEY_MARTABEH = "martabeh";
    private static final String KEY_EMTIAZ1 = "emtiaz1";
    private static final String KEY_EMTIAZ2 = "emtiaz2";
    private static final String KEY_SEND = "send";
    private static final String KEY_D0 = "d0";
    private static final String KEY_D1= "d1";
    private static final String KEY_D2 = "d2";
    private static final String KEY_D3 = "d3";
    private static final String KEY_D4 = "d4";
    private static final String KEY_D5 = "d5";
    private static final String KEY_D6 = "d6";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_CONTACTS_TABLE = "CREATE  TABLE IF NOT EXISTS "+TABLE_CONTACTS+" ("+KEY_ID+" INTEGER PRIMARY KEY , "+KEY_AZAN+" TEXT, "+KEY_TOLO+" TEXT, "+KEY_BIDAR+" TEXT, "+KEY_EMTIAZ1+" TEXT, "+KEY_EMTIAZ2+" TEXT, "+KEY_SEND+" TEXT, "+KEY_D0+" TEXT, "+KEY_D1+" TEXT, "+KEY_D2+" TEXT, "+KEY_D3+" TEXT, "+KEY_D4+" TEXT, "+KEY_D5+" TEXT, "+KEY_D6+" TEXT, "+KEY_MARTABEH+" INTEGER);";
        db.execSQL(CREATE_CONTACTS_TABLE);
        Log.e("Modeleeeeee",CREATE_CONTACTS_TABLE);



        Log.e("Model","create table");

    }
    public boolean delethTable(SQLiteDatabase db){
        try {
            db.execSQL("DROP TABLE " + TABLE_CONTACTS);
            return true;
        }catch (Exception e){
            return false;
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

        // Create tables again
        onCreate(db);


    }

    public boolean insetTo(Contacts contacts) {
        try{
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
//            contentValues.put(KEY_ID, contacts.getId());
            contentValues.put(KEY_AZAN, contacts.getAzan());
            contentValues.put(KEY_TOLO, contacts.getTolo());
            contentValues.put(KEY_BIDAR, contacts.getBidarshod());
            contentValues.put(KEY_MARTABEH, contacts.getMartabeh());
            contentValues.put(KEY_EMTIAZ1, contacts.getEmtiaz1());
            contentValues.put(KEY_EMTIAZ2, contacts.getEmtiaz2());
            contentValues.put(KEY_SEND, contacts.getSend());
            Log.e("contact1",contacts.getAzan());
            Log.e("contact2",contacts.getTolo());
            Log.e("contact3",contacts.getBidarshod());
            Log.e("contact4",String.valueOf(contacts.getMartabeh()));
            db.insert(TABLE_CONTACTS, null, contentValues);
            db.close();
            return true;
        }catch (Exception ex){
            return false;
        }


    }

    public boolean updateTable(Contacts contacts) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(KEY_AZAN, String.valueOf(contacts.getAzan()));
            contentValues.put(KEY_TOLO, String.valueOf(contacts.getTolo()));
            contentValues.put(KEY_BIDAR, String.valueOf(contacts.getBidarshod()));
            contentValues.put(KEY_MARTABEH, contacts.getMartabeh());
            contentValues.put(KEY_EMTIAZ1, contacts.getEmtiaz1());
            contentValues.put(KEY_EMTIAZ2, contacts.getEmtiaz2());
            contentValues.put(KEY_SEND, contacts.getSend());
            db.update(TABLE_CONTACTS, contentValues, KEY_ID + "=1", null);
            return true;
        } catch (Exception e) {
            return false;

        }
    }

    public boolean update_Azan(String up){
        try {
            ContentValues c=new ContentValues();
            c.put(KEY_AZAN,up);
            SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
            sqLiteDatabase.update(TABLE_CONTACTS,c,KEY_ID+"=1",null);
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public boolean update_Ahd(String up){
        try {
            ContentValues c=new ContentValues();
            c.put(KEY_AZAN,up);
            SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
            sqLiteDatabase.update(TABLE_CONTACTS,c,KEY_ID+"=2",null);
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public boolean update_final(String up){
        try {
            ContentValues c=new ContentValues();
            c.put(KEY_AZAN,up);
            SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
            sqLiteDatabase.update(TABLE_CONTACTS,c,KEY_ID+"=3",null);
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public boolean update_D0(String up){
        try {
            ContentValues c=new ContentValues();
            c.put(KEY_D0,up);
            SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
            sqLiteDatabase.update(TABLE_CONTACTS,c,KEY_ID+"=1",null);
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public boolean update_D1(String up){
        try {
            ContentValues c=new ContentValues();
            c.put(KEY_D1,up);
            SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
            sqLiteDatabase.update(TABLE_CONTACTS,c,KEY_ID+"=1",null);
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public boolean update_D2(String up){
        try {
            ContentValues c=new ContentValues();
            c.put(KEY_D2,up);
            SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
            sqLiteDatabase.update(TABLE_CONTACTS,c,KEY_ID+"=1",null);
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public boolean update_D3(String up){
        try {
            ContentValues c=new ContentValues();
            c.put(KEY_D3,up);
            SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
            sqLiteDatabase.update(TABLE_CONTACTS,c,KEY_ID+"=1",null);
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public boolean update_D4(String up){
        try {
            ContentValues c=new ContentValues();
            c.put(KEY_D4,up);
            SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
            sqLiteDatabase.update(TABLE_CONTACTS,c,KEY_ID+"=1",null);
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public boolean update_D5(String up){
        try {
            ContentValues c=new ContentValues();
            c.put(KEY_D5,up);
            SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
            sqLiteDatabase.update(TABLE_CONTACTS,c,KEY_ID+"=1",null);
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public boolean update_D6(String up){
        try {
            ContentValues c=new ContentValues();
            c.put(KEY_D6,up);
            SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
            sqLiteDatabase.update(TABLE_CONTACTS,c,KEY_ID+"=1",null);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean update_emtiaz1(String emtiaz){
        try {
            ContentValues c=new ContentValues();
            c.put(KEY_EMTIAZ1,emtiaz);
            SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
            sqLiteDatabase.update(TABLE_CONTACTS,c,KEY_ID+"=1",null);
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public boolean update_emtiaz2(String emtiaz){
        try {
            ContentValues c=new ContentValues();
            c.put(KEY_EMTIAZ2,emtiaz);
            SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
            sqLiteDatabase.update(TABLE_CONTACTS,c,KEY_ID+"=1",null);
            return true;
        }catch (Exception e){
            return false;
        }
    }
//    public boolean setMusic(String music){
//        try {
//            ContentValues c=new ContentValues();
//            c.put(KEY_EMTIAZ2,music);
//            SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
//            sqLiteDatabase.update(TABLE_CONTACTS,c,KEY_ID+"=9",null);
//            return true;
//        }catch (Exception e){
//            return false;
//        }
//    }
    public boolean update_mac(String mac){
        try {
            ContentValues c=new ContentValues();
            c.put(KEY_EMTIAZ2,mac);
            SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
            sqLiteDatabase.update(TABLE_CONTACTS,c,KEY_ID+"=2",null);
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public boolean update_rotbeh(String rotbeh){
        try {
            ContentValues c=new ContentValues();
            c.put(KEY_EMTIAZ2,rotbeh);
            SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
            sqLiteDatabase.update(TABLE_CONTACTS,c,KEY_ID+"=7",null);
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public boolean update_total(String totoal){
        try {
            ContentValues c=new ContentValues();
            c.put(KEY_EMTIAZ2,totoal);
            SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
            sqLiteDatabase.update(TABLE_CONTACTS,c,KEY_ID+"=8",null);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    ////////////////////////////

    public boolean update_header(String emtiaz){
        try {
            ContentValues c=new ContentValues();
            c.put(KEY_EMTIAZ2,emtiaz);
            SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
            sqLiteDatabase.update(TABLE_CONTACTS,c,KEY_ID+"=3",null);
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public boolean update_msg(String emtiaz){
        try {
            ContentValues c=new ContentValues();
            c.put(KEY_EMTIAZ2,emtiaz);
            SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
            sqLiteDatabase.update(TABLE_CONTACTS,c,KEY_ID+"=4",null);
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public boolean update_writer(String emtiaz){
        try {
            ContentValues c=new ContentValues();
            c.put(KEY_EMTIAZ2,emtiaz);
            SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
            sqLiteDatabase.update(TABLE_CONTACTS,c,KEY_ID+"=5",null);
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public boolean update_view(String emtiaz){
        try {
            ContentValues c=new ContentValues();
            c.put(KEY_EMTIAZ2,emtiaz);
            SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
            sqLiteDatabase.update(TABLE_CONTACTS,c,KEY_ID+"=6",null);
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public boolean update_send(String send){
        try {
            ContentValues c=new ContentValues();
            c.put(KEY_SEND,send);
            SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
            sqLiteDatabase.update(TABLE_CONTACTS,c,KEY_ID+"=1",null);
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public boolean update_Tolo(String up){
        try {
            ContentValues c=new ContentValues();
            c.put(KEY_TOLO,up);
            SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
            sqLiteDatabase.update(TABLE_CONTACTS,c,KEY_ID+"=1",null);
            return true;
        }catch (Exception e){
            return false;
        }

    }
    public boolean update_Bidar(String up){
        try {
            ContentValues c=new ContentValues();
            c.put(KEY_BIDAR,up);
            SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
            sqLiteDatabase.update(TABLE_CONTACTS,c,KEY_ID+"=1",null);
            return true;
        }catch (Exception e){
            return false;
        }

    }
    public boolean update_Martabeh(String up){
        try {
            ContentValues c=new ContentValues();
            c.put(KEY_MARTABEH,up);
            SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
            sqLiteDatabase.update(TABLE_CONTACTS,c,KEY_ID+"=1",null);
            return true;
        }catch (Exception e){
            return false;
        }

    }
    public boolean get_azan(){
        try {
            try{
                String sql="SELECT * FROM "+TABLE_CONTACTS+" WHERE id=1";
                SQLiteDatabase db=this.getReadableDatabase();
                Cursor cursor = db.rawQuery(sql, null);
                Log.e("DBBBBB sql",sql);
                if (cursor !=null){
                    cursor.moveToFirst();
                    String n=cursor.getString(1);
                    if (n.equals("T")){
                        return true;
                    }
                }


            }catch (Exception e){
                Log.e("DBBBBB","ERRRRRor");

            }
        }catch (Exception e){
            Log.e("DBBBBB","ERRRRRor");

        }
        return false;
    }
    public boolean get_Ahd(){
        try {
            try{
                String sql="SELECT * FROM "+TABLE_CONTACTS+" WHERE id=2";
                SQLiteDatabase db=this.getReadableDatabase();
                Cursor cursor = db.rawQuery(sql, null);
                Log.e("DBBBBB sql",sql);
                if (cursor !=null){
                    cursor.moveToFirst();
                    String n=cursor.getString(1);
                    if (n.equals("T")){
                        return true;
                    }
                }


            }catch (Exception e){
                Log.e("DBBBBB","ERRRRRor");

            }
        }catch (Exception e){
            Log.e("DBBBBB","ERRRRRor");

        }
        return false;
    }
    public boolean get_Final(){
        try {
            try{
                String sql="SELECT * FROM "+TABLE_CONTACTS+" WHERE id=3";
                SQLiteDatabase db=this.getReadableDatabase();
                Cursor cursor = db.rawQuery(sql, null);
                Log.e("DBBBBB sql",sql);
                if (cursor !=null){
                    cursor.moveToFirst();
                    String n=cursor.getString(1);
                    if (n.equals("T")){
                        return true;
                    }
                }


            }catch (Exception e){
                Log.e("DBBBBB","ERRRRRor");

            }
        }catch (Exception e){
            Log.e("DBBBBB","ERRRRRor");

        }
        return false;
    }
    public boolean get_send(){
        try {
            try{
                String sql="SELECT * FROM "+TABLE_CONTACTS+" WHERE id=1";
                SQLiteDatabase db=this.getReadableDatabase();
                Cursor cursor = db.rawQuery(sql, null);
                Log.e("DBBBBB sql",sql);
                if (cursor !=null){
                    cursor.moveToFirst();
                    String n=cursor.getString(6);
                    if (n.equals("T")){
                        return true;
                    }
                }


            }catch (Exception e){
                Log.e("DBBBBB","ERRRRRor");

            }
        }catch (Exception e){
            Log.e("DBBBBB","ERRRRRor");

        }
        return false;
    }
    public boolean get_Toloeh(){
        try {
            try{
                String sql="SELECT * FROM "+TABLE_CONTACTS+" WHERE id=1";
                SQLiteDatabase db=this.getReadableDatabase();
                Cursor cursor = db.rawQuery(sql, null);
                Log.e("DBBBBB sql",sql);
                if (cursor !=null){
                    cursor.moveToFirst();
                    String n=cursor.getString(2);
                    if (n.equals("T")){
                        return true;
                    }
                }


            }catch (Exception e){
                Log.e("DBBBBB","ERRRRRor");

            }
        }catch (Exception e){
            Log.e("DBBBBB","ERRRRRor");

        }
        return false;
    }
    public boolean get_bidar(){
        try {
            try{
                String sql="SELECT * FROM "+TABLE_CONTACTS+" WHERE id=1";
                SQLiteDatabase db=this.getReadableDatabase();
                Cursor cursor = db.rawQuery(sql, null);
                Log.e("DBBBBB sql",sql);
                if (cursor !=null){
                    cursor.moveToFirst();
                    String n=cursor.getString(3);
                    if (n.equals("T")){
                        return true;
                    }
                }


            }catch (Exception e){
                Log.e("DBBBBB","ERRRRRor");

            }
        }catch (Exception e){
            Log.e("DBBBBB","ERRRRRor");

        }
        return false;
    }
    public int get_martabeh(){
        try {
            try{
                String sql="SELECT * FROM "+TABLE_CONTACTS+" WHERE id=1";
                SQLiteDatabase db=this.getReadableDatabase();
                Cursor cursor = db.rawQuery(sql, null);
                Log.e("DBBBBB sql",sql);
                if (cursor !=null){
                    cursor.moveToFirst();
                    String n=cursor.getString(14);
                    return Integer.parseInt(n);
                }


            }catch (Exception e){
                Log.e("DBBBBB","ERRRRRor");

            }
        }catch (Exception e){
            Log.e("DBBBBB","ERRRRRor");

        }

        return 0;
    }
    public String get_d0(){
        try {
            try{
                String sql="SELECT * FROM "+TABLE_CONTACTS+" WHERE id=1";
                SQLiteDatabase db=this.getReadableDatabase();
                Cursor cursor = db.rawQuery(sql, null);
                Log.e("DBBBBB sql",sql);
                if (cursor !=null){
                    cursor.moveToFirst();
                    String n=cursor.getString(7);
                    if (n.equals("")){
                        return "0";
                    }
                    return n;
                }else {
                    return "0";
                }


            }catch (Exception e){
                Log.e("DBBBBB","ERRRRRor");

            }
        }catch (Exception e){
            Log.e("DBBBBB","ERRRRRor");

        }

        return "0";
    }
    public String get_d1(){
        try {
            try{
                String sql="SELECT * FROM "+TABLE_CONTACTS+" WHERE id=1";
                SQLiteDatabase db=this.getReadableDatabase();
                Cursor cursor = db.rawQuery(sql, null);
                Log.e("DBBBBB sql",sql);
                if (cursor !=null){
                    cursor.moveToFirst();
                    String n=cursor.getString(8);
                    if (n.equals("")){
                        return "0";
                    }
                    return n;
                }else {
                    return "0";
                }


            }catch (Exception e){
                Log.e("DBBBBB","ERRRRRor");

            }
        }catch (Exception e){
            Log.e("DBBBBB","ERRRRRor");

        }

        return "0";
    }
    public String get_d2(){
        try {
            try{
                String sql="SELECT * FROM "+TABLE_CONTACTS+" WHERE id=1";
                SQLiteDatabase db=this.getReadableDatabase();
                Cursor cursor = db.rawQuery(sql, null);
                Log.e("DBBBBB sql",sql);
                if (cursor !=null){
                    cursor.moveToFirst();
                    String n=cursor.getString(9);
                    if (n.equals("")){
                        return "0";
                    }
                    return n;
                }else {
                    return "0";
                }


            }catch (Exception e){
                Log.e("DBBBBB","ERRRRRor");

            }
        }catch (Exception e){
            Log.e("DBBBBB","ERRRRRor");

        }

        return "0";
    }
    public String get_d3(){
        try {
            try{
                String sql="SELECT * FROM "+TABLE_CONTACTS+" WHERE id=1";
                SQLiteDatabase db=this.getReadableDatabase();
                Cursor cursor = db.rawQuery(sql, null);
                Log.e("DBBBBB sql",sql);
                if (cursor !=null){
                    cursor.moveToFirst();
                    String n=cursor.getString(10);
                    if (n.equals("")){
                        return "0";
                    }
                    return n;
                }else {
                    return "0";
                }


            }catch (Exception e){
                Log.e("DBBBBB","ERRRRRor");

            }
        }catch (Exception e){
            Log.e("DBBBBB","ERRRRRor");

        }

        return "0";
    }
    public String get_d4(){
        try {
            try{
                String sql="SELECT * FROM "+TABLE_CONTACTS+" WHERE id=1";
                SQLiteDatabase db=this.getReadableDatabase();
                Cursor cursor = db.rawQuery(sql, null);
                Log.e("DBBBBB sql",sql);
                if (cursor !=null){
                    cursor.moveToFirst();
                    String n=cursor.getString(11);
                    if (n.equals("")){
                        return "0";
                    }
                    return n;
                }else {
                    return "0";
                }


            }catch (Exception e){
                Log.e("DBBBBB","ERRRRRor");

            }
        }catch (Exception e){
            Log.e("DBBBBB","ERRRRRor");

        }

        return "0";
    }
    public String get_d5(){
        try {
            try{
                String sql="SELECT * FROM "+TABLE_CONTACTS+" WHERE id=1";
                SQLiteDatabase db=this.getReadableDatabase();
                Cursor cursor = db.rawQuery(sql, null);
                Log.e("DBBBBB sql",sql);
                if (cursor !=null){
                    cursor.moveToFirst();
                    String n=cursor.getString(12);
                    if (n.equals("")){
                        return "0";
                    }
                    return n;
                }else {
                    return "0";
                }


            }catch (Exception e){
                Log.e("DBBBBB","ERRRRRor");

            }
        }catch (Exception e){
            Log.e("DBBBBB","ERRRRRor");

        }

        return "0";
    }
    public String get_d6(){
        try {
            try{
                String sql="SELECT * FROM "+TABLE_CONTACTS+" WHERE id=1";
                SQLiteDatabase db=this.getReadableDatabase();
                Cursor cursor = db.rawQuery(sql, null);
                Log.e("DBBBBB sql",sql);
                if (cursor !=null){
                    cursor.moveToFirst();
                    String n=cursor.getString(13);
                    if (n.equals("")){
                        return "0";
                    }
                    return n;
                }else {
                    return "0";
                }


            }catch (Exception e){
                Log.e("DBBBBB","ERRRRRor");

            }
        }catch (Exception e){
            Log.e("DBBBBB","ERRRRRor");

        }

        return "0";
    }
    public long get_emtiaz1(){
        try {
            try{
                String sql="SELECT * FROM "+TABLE_CONTACTS+" WHERE id=1";
                SQLiteDatabase db=this.getReadableDatabase();
                Cursor cursor = db.rawQuery(sql, null);
                Log.e("DBBBBB sql",sql);
                if (cursor !=null){
                    cursor.moveToFirst();
                    String n=cursor.getString(4);
                    return Long.parseLong(n);
                }


            }catch (Exception e){
                Log.e("DBBBBB","ERRRRRor");

            }
        }catch (Exception e){
            Log.e("DBBBBB","ERRRRRor");

        }

        return 0;
    }
    public String get_emtiaz2(){
        try {
            try{
                String sql="SELECT * FROM "+TABLE_CONTACTS+" WHERE id=1";
                SQLiteDatabase db=this.getReadableDatabase();
                Cursor cursor = db.rawQuery(sql, null);
                Log.e("DBBBBB sql",sql);
                if (cursor !=null){
                    cursor.moveToFirst();
                    String n=cursor.getString(5);
                    return n;
                }


            }catch (Exception e){
                Log.e("DBBBBB","ERRRRRor");

            }
        }catch (Exception e){
            Log.e("DBBBBB","ERRRRRor");

        }

        return "0";
    }
//    public String getMusic(){
//        try {
//            try{
//                String sql="SELECT * FROM "+TABLE_CONTACTS+" WHERE id=9";
//                SQLiteDatabase db=this.getReadableDatabase();
//                Cursor cursor = db.rawQuery(sql, null);
//                Log.e("DBBBBB sql",sql);
//                if (cursor !=null){
//                    Log.e("CUUSOR","1");
//                    cursor.moveToFirst();
//                    Log.e("CUUSOR","2");
////                    String n=cursor.getString(5);
////                    Log.e("CUUSOR",cursor.getString(5));
//                    if (cursor.getString(5)==null){
//                        return "0";
//                    }else {
//                        return cursor.getString(5);
//                    }
//                }
//
//
//            }catch (Exception e){
//                Log.e("DBBBBB","ERRRRRor");
//
//            }
//        }catch (Exception e){
//            Log.e("DBBBBB","ERRRRRor");
//
//        }
//
//        return "0";
//    }
    public String get_mac(){
        try {
            try{
                String sql="SELECT * FROM "+TABLE_CONTACTS+" WHERE id=2";
                SQLiteDatabase db=this.getReadableDatabase();
                Cursor cursor = db.rawQuery(sql, null);
                Log.e("DBBBBB sql",sql);
                if (cursor !=null){
                    cursor.moveToFirst();
                    String n=cursor.getString(5);
                    return n;
                }


            }catch (Exception e){
                Log.e("DBBBBB","ERRRRRor");

            }
        }catch (Exception e){
            Log.e("DBBBBB","ERRRRRor");

        }

        return "0";
    }
    public String get_rotbeh(){
        try {
            try{
                String sql="SELECT * FROM "+TABLE_CONTACTS+" WHERE id=7";
                SQLiteDatabase db=this.getReadableDatabase();
                Cursor cursor = db.rawQuery(sql, null);
                Log.e("DBBBBB sql",sql);
                if (cursor !=null){
                    cursor.moveToFirst();
                    String n=cursor.getString(5);
                    return n;
                }


            }catch (Exception e){
                Log.e("DBBBBB","ERRRRRor");

            }
        }catch (Exception e){
            Log.e("DBBBBB","ERRRRRor");

        }

        return "0";
    }
    public String get_totol(){
        try {
            try{
                String sql="SELECT * FROM "+TABLE_CONTACTS+" WHERE id=8";
                SQLiteDatabase db=this.getReadableDatabase();
                Cursor cursor = db.rawQuery(sql, null);
                Log.e("DBBBBB sql",sql);
                if (cursor !=null){
                    cursor.moveToFirst();
                    String n=cursor.getString(5);
                    return n;
                }


            }catch (Exception e){
                Log.e("DBBBBB","ERRRRRor");

            }
        }catch (Exception e){
            Log.e("DBBBBB","ERRRRRor");

        }

        return "0";
    }

    public String get_header(){
        try {
            try{
                String sql="SELECT * FROM "+TABLE_CONTACTS+" WHERE id=3";
                SQLiteDatabase db=this.getReadableDatabase();
                Cursor cursor = db.rawQuery(sql, null);
                Log.e("DBBBBB sql",sql);
                if (cursor !=null){
                    cursor.moveToFirst();
                    String n=cursor.getString(5);
                    return n;
                }


            }catch (Exception e){
                Log.e("DBBBBB","ERRRRRor");

            }
        }catch (Exception e){
            Log.e("DBBBBB","ERRRRRor");

        }

        return "0";
    }
    public String get_msg(){
        try {
            try{
                String sql="SELECT * FROM "+TABLE_CONTACTS+" WHERE id=4";
                SQLiteDatabase db=this.getReadableDatabase();
                Cursor cursor = db.rawQuery(sql, null);
                Log.e("DBBBBB sql",sql);
                if (cursor !=null){
                    cursor.moveToFirst();
                    String n=cursor.getString(5);
                    return n;
                }


            }catch (Exception e){
                Log.e("DBBBBB","ERRRRRor");

            }
        }catch (Exception e){
            Log.e("DBBBBB","ERRRRRor");

        }

        return "0";
    }
    public String get_wirter(){
        try {
            try{
                String sql="SELECT * FROM "+TABLE_CONTACTS+" WHERE id=5";
                SQLiteDatabase db=this.getReadableDatabase();
                Cursor cursor = db.rawQuery(sql, null);
                Log.e("DBBBBB sql",sql);
                if (cursor !=null){
                    cursor.moveToFirst();
                    String n=cursor.getString(5);
                    return n;
                }


            }catch (Exception e){
                Log.e("DBBBBB","ERRRRRor");

            }
        }catch (Exception e){
            Log.e("DBBBBB","ERRRRRor");

        }

        return "0";
    }
    public String get_view(){
        try {
            try{
                String sql="SELECT * FROM "+TABLE_CONTACTS+" WHERE id=6";
                SQLiteDatabase db=this.getReadableDatabase();
                Cursor cursor = db.rawQuery(sql, null);
                Log.e("DBBBBB sql",sql);
                if (cursor !=null){
                    cursor.moveToFirst();
                    String n=cursor.getString(5);
                    return n;
                }


            }catch (Exception e){
                Log.e("DBBBBB","ERRRRRor");

            }
        }catch (Exception e){
            Log.e("DBBBBB","ERRRRRor");

        }

        return "0";
    }
}
