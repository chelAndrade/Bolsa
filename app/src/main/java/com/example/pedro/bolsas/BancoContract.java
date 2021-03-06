package com.example.pedro.bolsas;

import android.provider.BaseColumns;

public class BancoContract {
    public static final String TEXT_TYPE = " TEXT";
    public static final String INT_TYPE = " INTEGER";
    public static final String SEP = ",";
    public static final String SQL_CREATE_LIVRO = "CREATE TABLE " + Livro.TABLE_NAME + " (" +
            Livro._ID + INT_TYPE +" PRIMARY KEY AUTOINCREMENT" + SEP +
            Livro.COLUMN_NAME_TITULO + TEXT_TYPE +" UNIQUE " + SEP +
            Livro.COLUMN_NAME_AUTOR + TEXT_TYPE + SEP +
            Livro.COLUMN_NAME_EDITORA + INT_TYPE + ")";
    public static final String SQL_DROP_LIVRO = "DROP TABLE IF EXISTS " + Livro.TABLE_NAME;
    public BancoContract(){}
        public static final class Livro implements BaseColumns {
            public static final String TABLE_NAME = "livro";
            public static final String COLUMN_NAME_TITULO = "titulo";
            public static final String COLUMN_NAME_AUTOR= "autor";
            public static final String COLUMN_NAME_EDITORA = "editora";

    }
}
