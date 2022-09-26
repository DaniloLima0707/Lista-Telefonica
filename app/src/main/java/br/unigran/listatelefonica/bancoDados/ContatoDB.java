package br.unigran.listatelefonica.bancoDados;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import br.unigran.listatelefonica.listaTelefonica.Contato;

public class ContatoDB {
    private DBHelper db;
    private SQLiteDatabase conexao;

    public ContatoDB(DBHelper db) { this.db = db; }

    public void inserir(Contato contato) {
        conexao = db.getWritableDatabase();//abre o bd
        ContentValues valores = new ContentValues();
        valores.put("campoNom", contato.getCampoNom());
        valores.put("campoTel", contato.getCampoTel());
        valores.put("campoDataNasc", contato.getCampoDataNasc());
        conexao.update("Lista", valores, "id=?", new String[]{contato.getId().toString()});
        conexao.close();
    }

    public void atualizar(Contato contato) {
        conexao = db.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("campoNom", contato.getCampoNom());
        valores.put("campoTel", contato.getCampoTel());
        valores.put("campoDataNasc", contato.getCampoDataNasc());
        conexao.update("Lista", valores, "id=?", new String[]{contato.getId().toString()});
        conexao.close();
    }

    public void remover(int id){
        conexao = db.getWritableDatabase();
        conexao.delete("Lista","id=?", new String[]{id + ""});
    }

    public void lista(List dados){
        dados.clear();
        conexao = db.getReadableDatabase();
        String names[] = {"id", "campoNome", "campoTelefone", "campoDataNascimento"};
        Cursor query = conexao.query("Lista", names, null, null, null, null, "campoNome");
        while (query.moveToNext()){
            Contato contato = new Contato();
            contato.setId(Integer.parseInt(
                    query.getString(0)));
            contato.setCampoNom(
                    query.getString(1));
            contato.setCampoTel(
                    query.getString(2));
            contato.setCampoDataNasc(
                    query.getString(3));
            dados.add(contato);
        }
        conexao.close();
    }
}
