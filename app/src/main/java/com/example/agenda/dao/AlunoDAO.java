package com.example.agenda.dao;

import android.os.Build;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.agenda.model.Aluno;

import java.util.ArrayList;
import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.O)
public class AlunoDAO {


    private final static List<Aluno> alunos = new ArrayList<>();
    private static int contadorDeIds = 1;


    public void salva(Aluno aluno) {

        aluno.setId(contadorDeIds);
        alunos.add(aluno);
        //SALVA UM NOVO ID
        atualizaIds();

    }

    private void atualizaIds() {
        //INCREMENTA UM NOVO ID
        contadorDeIds++;
    }

    public void edita(Aluno aluno) {
        //PEGA O ALUNO E BUSCA PELO ID PARA EDITAR
        Aluno alunoEncontrado = buscaAlunoPeloId(aluno);

        if (alunoEncontrado != null) {
            //PEGA A POSICAO DO ALUNO
            int posicaoDoAluno = alunos.indexOf(alunoEncontrado);
            //PEGA O ALUNO E SETA A POSICAO AO ALUNO DO PARAMETRO
            alunos.set(posicaoDoAluno, aluno);
        }
    }

    @Nullable
    private Aluno buscaAlunoPeloId(Aluno aluno) {
        //PERCORRE TODOS OS ALUNOS
        for (Aluno a :
                alunos) {
            //VERIFICA SE O ALUNO DO MODEL É IGUAL AO ALUNO DO ARRAY
            if (a.getId() == aluno.getId()) {
                //RETORNA O ALUNO SELECIONADO
                return a;
            }
        }
        //SE NÃO ACHAR NADA RETORNA NULL
        return null;
    }

    public List<Aluno> todos() {

        return new ArrayList<>(alunos);
    }

    public void remove(Aluno aluno) {
        //VERIFICA O ALUNO SELECIONADO
        Aluno alunoDevolvido = buscaAlunoPeloId(aluno);
        //SE O ALUNO FOR DIFERENTE DE VAZIO
        if (alunoDevolvido != null) {
            //PELA O ALUNO E REMOVE PELO ID
            alunos.remove(alunoDevolvido);
        }
    }
}
