package com.example.pedro.bolsas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class Listar extends AppCompatActivity {
    private ListView lista;
    private Button botaoVoltar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);

        lista=(ListView)findViewById(R.id.list);
        botaoVoltar=(Button)findViewById(R.id.voltar);
        LivroAdapter adapter=new LivroAdapter(getApplicationContext(),null);
        lista.setAdapter(adapter);
        adapter.atualiza();
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent infolivro= new Intent(Listar.this,InfoLivro.class);
                int i =(int )id;
                infolivro.putExtra("id" , Integer.toString(i));

                startActivity(infolivro);
                finish();
            }
        });
        botaoVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
