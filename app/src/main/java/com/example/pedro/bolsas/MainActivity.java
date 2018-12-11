package com.example.pedro.bolsas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText titulo;
    private EditText autor;
    private EditText editora;
    private Button cadastro;
    private Button listar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        titulo = findViewById(R.id.Titulo);
        autor = findViewById(R.id.Autor);
        editora = findViewById(R.id.editora);
        cadastro = findViewById(R.id.btncadastro);
        listar = findViewById(R.id.btnExcluir);
        cadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Titulo=titulo.getText().toString();
                String Editora=editora.getText().toString();
                String Autor=autor.getText().toString();
                if(Titulo.isEmpty()){
                    titulo.requestFocus();
                }
                else if(Editora.isEmpty()){
                    editora.requestFocus();
                }
                else if(Autor.isEmpty()){
                    autor.requestFocus();
                } else{
                    LivroModel livro = new LivroModel(Titulo,Autor,Editora);
                    Banco db = new Banco(getApplicationContext());
                    if(db.salvar(livro)){
                        Log.e("test",livro.getTitulo());
                        Toast toast = Toast.makeText(getApplicationContext(), "Adicionado com sucesso", Toast.LENGTH_SHORT);
                        toast.show();
                        LivroAdapter adapter = new LivroAdapter(getBaseContext(), null);
                        adapter.atualiza();

                    }
                    else {
                        titulo.requestFocus();
                        Toast toast = Toast.makeText(getApplicationContext(), "Ja existe um livro com esse nome", Toast.LENGTH_SHORT);
                        toast.show();
                    }

                }
            }
        });
        listar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent listar = new Intent(MainActivity.this,Listar.class);
                startActivity(listar);
            }
        });
    }
}
