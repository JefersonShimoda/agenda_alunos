package DAO;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import model.Aluno;

public class AlunoDAO {

    private final static List<Aluno> alunos = new ArrayList<>();
    private static int contadorIDs = 1;

    public void salva(Aluno aluno) {
        aluno.setId(contadorIDs);
        alunos.add(aluno);
        atualizaIds();
    }

    private void atualizaIds() {
        contadorIDs++;
    }

    public void edita(Aluno aluno) {
        Aluno alunoEncontrado = buscaAlunoPeloId(aluno);
        if (alunoEncontrado != null){
            int posicaoAluno = alunos.indexOf(alunoEncontrado);
            alunos.set(posicaoAluno, aluno);
        }
    }

    @Nullable
    private Aluno buscaAlunoPeloId(Aluno aluno) {
        for (Aluno a: alunos) {
            if (a.getId() == aluno.getId()){
                return a;
            }
        }
        return null;
    }

    public List<Aluno> todos(){
        return new ArrayList<>(alunos);
    }


    public void exclui(Aluno aluno) {
        Aluno alunoDevolvido = buscaAlunoPeloId(aluno);
        if (alunoDevolvido != null){
            alunos.remove(alunoDevolvido);
        }

    }
}


