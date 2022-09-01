package br.unigran.agenda;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import br.unigran.bancoDados.DBHelper;

public class MainActivity extends AppCompatActivity {

    EditText nome;
    EditText telefone;
    List<Contato> dados;
    ListView listagem;
    DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new DBHelper(this);//cria conexao
        setContentView(R.layout.activity_main);
        nome=findViewById(R.id.nomeId);
        telefone=findViewById(R.id.telefoneId);
        dados= new ArrayList();
        listagem=findViewById(R.id.listaId);
        ArrayAdapter adapter =
        new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,dados);
        listagem.setAdapter(adapter);

    }

    public void salvar(View view){
        Contato contato = new Contato();
        contato.setNome(nome.getText().toString());
        contato.setTelefone(telefone.getText().toString());
        dados.add(contato);
        db.inserir(contato,db);

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