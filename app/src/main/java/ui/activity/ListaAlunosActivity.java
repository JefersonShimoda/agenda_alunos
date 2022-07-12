package ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import DAO.AlunoDAO;
import alura.com.br.R;

public class ListaAlunosActivity extends AppCompatActivity {

    public static final String TITLE_APPBAR = "Lista de Alunos";
    private final AlunoDAO dao = new AlunoDAO();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos); // Conteúdo da página (XML do layout)
        setTitle(TITLE_APPBAR); // Título da página
        configuraFabNovoAluno(); //Chama o método
    }

    private void configuraFabNovoAluno() {
        FloatingActionButton adicionar = findViewById(R.id.activity_lista_alunos_fab_novo_aluno);
        adicionar.setOnClickListener(view -> abreFormularioAlunoActivity());
    }

    private void abreFormularioAlunoActivity() {
        startActivity(new Intent(this,
                FormularioAlunoActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        configuraLista();
    }

    private void configuraLista() {
        // Busca no XML o elemento que será alterado
        ListView listaAlunos = findViewById(R.id.activity_lista_alunos_listview);

        // Coloca os itens no elemento
        listaAlunos.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                dao.todos()));
    }
}

