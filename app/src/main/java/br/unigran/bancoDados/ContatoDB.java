package br.unigran.bancoDados;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import br.unigran.agenda.Contato;

public class ContatoDB {
    private SQLiteDatabase conexao;
    public void inserir(Contato contato, DBHelper db){
        conexao = db.getWritableDatabase();//abri o bd
        ContentValues valores = new ContentValues();
        valores.put("nome",contato.getNome());
        valores.put("telefone",contato.getTelefone());
        conexao.insertOrThrow("Agenda",null,valores);
        conexao.close();
    }
}
