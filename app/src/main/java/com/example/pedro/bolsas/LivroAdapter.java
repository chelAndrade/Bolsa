package com.example.pedro.bolsas;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class LivroAdapter  extends CursorAdapter {
    private Banco db;
    public LivroAdapter(Context context, Cursor c) {
        super(context, c, 0);
        db= new Banco(context);
    }
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.listar_livro,parent,false);
    }
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView txtTitulo=(TextView)view.findViewById(R.id.Livrotitulo);



        String titulo =cursor.getString(cursor.getColumnIndexOrThrow(BancoContract.Livro.COLUMN_NAME_TITULO));

        txtTitulo.setText(titulo);

    }
    public void atualiza(){
        this.changeCursor(db.atualizaLivro());
    }
    public LivroModel getLivro (int i ){
        return  db.getLivro(i);
    }
}

