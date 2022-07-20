package ui.activity;

import static ui.activity.ConstantesActivities.CHAVE_ALUNO;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import DAO.AlunoDAO;
import alura.com.br.R;
import model.Aluno;

public class ListaAlunosActivity extends AppCompatActivity {

    public static final String TITLE_APPBAR = "Lista de Alunos";
    private final AlunoDAO dao = new AlunoDAO();
    private ArrayAdapter<Aluno> adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos); // Conteúdo da página (XML do layout)
        setTitle(TITLE_APPBAR); // Título da página
        configuraFabNovoAluno(); //Chama o método
        configuraLista();
        dao.salva(new Aluno("Jorge","85477546773","jorge@email.com"));
        dao.salva(new Aluno("Ana","578637","ana@email.com"));
    }

    private void configuraFabNovoAluno() {
        FloatingActionButton adicionar = findViewById(R.id.activity_lista_alunos_fab_novo_aluno);
        adicionar.setOnClickListener(view -> abreFormularioModoInsere());
    }

    private void abreFormularioModoInsere() {
        startActivity(new Intent(this,
                FormularioAlunoActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        atualizaAlunos();
    }

    private void atualizaAlunos() {
        adapter.clear();
        adapter.addAll(dao.todos());
    }

    private void configuraLista() {
        // Busca no XML o elemento que será alterado
        ListView listaAlunos = findViewById(R.id.activity_lista_alunos_listview);

        // Coloca os itens no elemento
        configuraAdapter(listaAlunos);
        configuraListenerDeCliquePorItem(listaAlunos);
        configuraListenerCliqueLongoPorItem(listaAlunos);
    }

    private void configuraListenerCliqueLongoPorItem(ListView listaAlunos) {
        listaAlunos.setOnItemLongClickListener((adapterView, view, iposicao, lid) -> {
            Aluno alunoEscolhido = (Aluno) adapterView.getItemAtPosition(iposicao);
            dao.exclui(alunoEscolhido);
            adapter.remove(alunoEscolhido);
            return true;
        });
    }

    private void configuraListenerDeCliquePorItem(ListView listaAlunos) {
        listaAlunos.setOnItemClickListener((adapterView, view, iposicao, lid) -> {
            Aluno alunoEscolhido = (Aluno) adapterView.getItemAtPosition(iposicao); //pegar o numero do elemento da lista 2/2
            abreFormularioModoEdicao(alunoEscolhido);
        });
    }

    private void abreFormularioModoEdicao(Aluno aluno) {
        Intent enviarDadosParaFormulario = new Intent(
                ListaAlunosActivity.this,
                FormularioAlunoActivity.class
        );
        enviarDadosParaFormulario.putExtra(CHAVE_ALUNO, aluno); //Extra envia dados para outra activity. Exige que Serializable seja implementado na classe.
        startActivity(enviarDadosParaFormulario);
    }

    private void configuraAdapter(ListView listaAlunos) {
        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1);
        listaAlunos.setAdapter(adapter);
    }
}

