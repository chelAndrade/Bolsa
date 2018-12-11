package com.example.pedro.bolsas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Banco extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Biblioteca.db";

    public Banco (Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(BancoContract.SQL_CREATE_LIVRO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(BancoContract.SQL_DROP_LIVRO);
        db.execSQL(BancoContract.SQL_CREATE_LIVRO);
    }
    public Boolean salvar (LivroModel livro){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(BancoContract.Livro.COLUMN_NAME_TITULO, livro.getTitulo());
        values.put(BancoContract.Livro.COLUMN_NAME_AUTOR, livro.getAutor());
        values.put(BancoContract.Livro.COLUMN_NAME_EDITORA, livro.getEditora());
        db.insert(BancoContract.Livro.TABLE_NAME, null, values);
        return true;
    }
    public Cursor atualizaLivro(){
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            String[] visao = {
                    BancoContract.Livro._ID,
                    BancoContract.Livro.COLUMN_NAME_TITULO,
                    BancoContract.Livro.COLUMN_NAME_AUTOR,
                    BancoContract.Livro.COLUMN_NAME_EDITORA


            };

            Cursor c = db.query(BancoContract.Livro.TABLE_NAME, visao, null, null, null, null, null);
            return c;

        } catch (Exception e) {
            Log.e("BIBLIO", e.getLocalizedMessage());
            Log.e("BIBLIO", e.getStackTrace().toString());
            return null;
        }
    }

    public LivroModel getLivro(int i) {
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            String[] visao = {
                    BancoContract.Livro._ID,
                    BancoContract.Livro.COLUMN_NAME_TITULO,
                    BancoContract.Livro.COLUMN_NAME_AUTOR,
                    BancoContract.Livro.COLUMN_NAME_EDITORA
            };
            String sel = BancoContract.Livro._ID + "=?";
            String arg[] = {Integer.toString(i)};
            Cursor c = db.query(BancoContract.Livro.TABLE_NAME, visao, sel, arg, null, null, null);
            c.moveToFirst();
            LivroModel livro = new LivroModel(c.getString(c.getColumnIndex(BancoContract.Livro.COLUMN_NAME_TITULO)),
                    c.getString(c.getColumnIndex(BancoContract.Livro.COLUMN_NAME_AUTOR)), c.getString(
                    c.getColumnIndex(BancoContract.Livro.COLUMN_NAME_EDITORA)));
            return livro;

        } catch (Exception e) {
            Log.e("BIBLIO", e.getLocalizedMessage());
            Log.e("BIBLIO", e.getStackTrace().toString());
            return null;
        }
    }
    public void deletar (int i){
        String onde = BancoContract.Livro._ID + "="+i;
        SQLiteDatabase db = this.getReadableDatabase();
        db.delete(BancoContract.Livro.TABLE_NAME,onde,null);
        db.close();
    }
    public void editar(LivroModel livro,int i ){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(BancoContract.Livro.COLUMN_NAME_TITULO, livro.getTitulo());
        values.put(BancoContract.Livro.COLUMN_NAME_AUTOR, livro.getAutor());
        values.put(BancoContract.Livro.COLUMN_NAME_EDITORA, livro.getEditora());
        Log.i("titulo",livro.getTitulo());
        String sel = BancoContract.Livro._ID + "=?";
        String arg[] = {Integer.toString(i)};
        try {
            db.update(BancoContract.Livro.TABLE_NAME, values, sel, arg);
        }catch (Exception e){
            Log.e("ERRO",e.getMessage());
        }
    }
}
