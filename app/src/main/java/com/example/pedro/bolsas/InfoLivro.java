package com.example.pedro.bolsas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InfoLivro extends AppCompatActivity {
    private EditText titulo;
    private EditText autor;
    private EditText editora;
    private Button editar;
    private Button excluir;
    private Button voltar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_livro);
        Intent i = getIntent();
        String id=i.getStringExtra("id");
        final int j=Integer.parseInt(id);

        titulo=findViewById(R.id.titulo);
        autor=findViewById(R.id.autor);
        editora=findViewById(R.id.editora);

        editar=findViewById(R.id.Editar);
        excluir=findViewById(R.id.Excluir);
        voltar=findViewById(R.id.voltar);


        LivroAdapter adapter = new LivroAdapter(getApplicationContext(),null);
        LivroModel livro =  adapter.getLivro(j);
        titulo.setText(livro.getTitulo());
        autor.setText(livro.getAutor());
        editora.setText(livro.getEditora());
        excluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Banco db = new Banco(getApplicationContext());
                db.deletar(j);
                LivroAdapter adapter = new LivroAdapter(getBaseContext(), null);
                adapter.atualiza();
                Toast toast = Toast.makeText(getApplicationContext(), "Excluido com Sucesso", Toast.LENGTH_SHORT);
                toast.show();
                finish();
            }
        });
        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titulos = titulo.getText().toString();
                String autores = autor.getText().toString();
                String editoras = editora.getText().toString();
                LivroModel livros = new LivroModel(titulos,autores,editoras);
                Banco db = new Banco(getApplicationContext());
                db.editar(livros,j);
                LivroAdapter adapter = new LivroAdapter(getBaseContext(), null);
                adapter.atualiza();
                Toast toast = Toast.makeText(getApplicationContext(), "Editado com Sucesso", Toast.LENGTH_SHORT);
                toast.show();

                finish();
            }
        });

        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
