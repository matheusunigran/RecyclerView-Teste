package br.unigran.agenda;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import br.unigran.bancoDados.ContatoDB;
import br.unigran.bancoDados.DBHelper;

public class MainActivity extends AppCompatActivity {

    EditText nome;
    EditText telefone;
    List<Contato> dados;
    ListView listagem;
    DBHelper db;
    ContatoDB contatoDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DBHelper(this);//cria conexao
      //mapeia campos tela
        nome=findViewById(R.id.nomeId);
        telefone=findViewById(R.id.telefoneId);
        dados= new ArrayList();//aloca lista
        listagem=findViewById(R.id.listaId);
        //vincula adapter
        ArrayAdapter adapter =
        new ArrayAdapter(this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,dados);
        listagem.setAdapter(adapter);
        contatoDB=new ContatoDB(db);
        contatoDB.lista(dados);//lista inicial
       acoes();
    }

    private void acoes() {
        listagem.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView,
                                           View view, int i, long l) {
               new AlertDialog.Builder(view.getContext())
               .setMessage("Deseja realmente remover")
               .setPositiveButton("Confirmar",
                      new DialogInterface.OnClickListener() {
                      @Override
                      public void onClick(DialogInterface dialogInterface,
                                          int i) {
                               contatoDB.remover(dados.get(i).getId());
                               contatoDB.lista(dados);
                           }
                       })
               .setNegativeButton("cancelar",null)
               .create().show();
                return false;
            }
        });

    }

    public void salvar(View view){
        Contato contato = new Contato();
        contato.setNome(nome.getText().toString());
        contato.setTelefone(telefone.getText().toString());

        contatoDB.inserir(contato);
        contatoDB.lista(dados);

        Snackbar.make(this,view,"ertrt", BaseTransientBottomBar.LENGTH_SHORT).
//                setAction(R.string.app_name, new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//
//                    }
//                }).
                show();
        Toast.makeText(this,"Salvo com sucesso",Toast.LENGTH_SHORT).show();
    }


}