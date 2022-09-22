package br.unigran.listatelefonica.listaTelefonica;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.unigran.listatelefonica.R;
import br.unigran.listatelefonica.bancoDados.ContatoDB;
import br.unigran.listatelefonica.bancoDados.DBHelper;

public class MainActivity extends AppCompatActivity {
    EditText campoNome;
    EditText campoTelefone;
    EditText campoDataNascimento;
    Button btnSalvar;
    ListView listaDados;
    List<Contato> dados;
    DBHelper db;
    ContatoDB contatoDB;
    Integer atualiza;
    Integer confirma = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        campoNome = findViewById(R.id.campoNome);
        campoTelefone = findViewById(R.id.campoTelefone);
        campoDataNascimento = findViewById(R.id.campoDataNascimento);
        btnSalvar = findViewById(R.id.btnSalvar);
        listaDados = findViewById(R.id.listaDados);
        dados = new ArrayList<>(); //Aloca a lista
        //Vincula adapter
        ArrayAdapter adapter = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, dados);
        listaDados.setAdapter(adapter);
        contatoDB = new ContatoDB(db);
        contatoDB.lista(dados);//Lista Inicial
        acoes();

    }
    private void acoes(){
        confirma = null;
        listaDados.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapterView,
                                                   View view, int i, long l) {
                        AlertDialog.Builder mensagem = new AlertDialog.Builder(view.getContext());
                        mensagem.setTitle("Opções");
                        mensagem.setMessage("Escolha uma opção para realizar");
                        mensagem.setPositiveButton("Remover", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogInterface, int id) {

                                new AlertDialog.Builder(view.getContext())
                                        .setMessage("Deseja Remover")
                                        .setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int k) {
                                                contatoDB.lista(dados);
                                                ((ArrayAdapter) listaDados.getAdapter()).notifyDataSetChanged();
                                                String msg1 = "Contato removido";
                                                Toast.makeText(getApplicationContext(), msg1, Toast.LENGTH_SHORT).show();
                                            }
                                        })
                                        .setNegativeButton("Cancelar", null)
                                        .create().show();
                            }
                        });
                        mensagem.setNegativeButton("Editar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                atualiza = dados.get(i).getId();
                                campoNome.setText(dados.get(i).getCampoNom());
                                campoTelefone.setText(dados.get(i).getCampoTel().toString());
                                campoDataNascimento.setText(dados.get(i).getCampoDataNasc().toString());

                                contatoDB.atualizar(dados.get(i));
                                contatoDB.lista(dados);

                                confirma = 1;
                            }
                        });
                        mensagem.setNeutralButton("Cancelar", null);
                        mensagem.show();
                        return false;

                    }
                });
    }

}