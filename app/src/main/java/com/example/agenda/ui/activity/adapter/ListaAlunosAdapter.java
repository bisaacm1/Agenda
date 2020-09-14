package com.example.agenda.ui.activity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.agenda.R;
import com.example.agenda.model.Aluno;

import java.util.ArrayList;
import java.util.List;

public class ListaAlunosAdapter extends BaseAdapter {


    private final List<Aluno> alunos = new ArrayList<>();
    private final Context context;

    public ListaAlunosAdapter(Context context) {
        this.context = context;
    }

    //PEGA TODOS OS ALUNOS CADASTRADOS
    @Override
    public int getCount() {
        return alunos.size();
    }

    //PEGA O ALUNO PELA POSICAO
    @Override
    public Aluno getItem(int posicao) {
        return alunos.get(posicao);
    }

    //PEGA O ALUNO PELO ID
    @Override
    public long getItemId(int posicao) {
        return alunos.get(posicao).getId();
    }

    //PARA CONFIGURAR O ALUNO NO LAYOUT
    @Override
    public View getView(int posicao, View view, ViewGroup viewGroup) {
        View viewCriada = criaView(viewGroup);
        Aluno alunoDevolvido = alunos.get(posicao);
        vincula(viewCriada, alunoDevolvido);
        return viewCriada;
    }

    //PEGA O ID DOS TEXTVIEW DO LAYOUT PARA JOGAR NA TELA
    private void vincula(View viewCriada, Aluno alunoDevolvido) {
        TextView nome = viewCriada.findViewById(R.id.item_aluno_nome);
        nome.setText(alunoDevolvido.getNome());
        TextView telefone = viewCriada.findViewById(R.id.item_aluno_telefone);
        telefone.setText(alunoDevolvido.getTelefone());
        TextView email = viewCriada.findViewById(R.id.item_aluno_email);
        email.setText(alunoDevolvido.getEmail());
        TextView materia = viewCriada.findViewById(R.id.item_aluno_materia);
        materia.setText(alunoDevolvido.getMateria());
    }

    //PEGA O LAYOUT PRONTO PARA CRIAR
    private View criaView(ViewGroup viewGroup) {
        return LayoutInflater
                .from(context)
                .inflate(R.layout.item_aluno, viewGroup, false);
    }

    //ATUALIZA O ALUNO
    public void atualiza(List<Aluno> alunos){
        //AO ATUALIZARMOS ELE JA LISTAR NA TELA
        this.alunos.clear();
        // PEGA AS INFORMACOES E E ADICIONA
        this.alunos.addAll(alunos);

        notifyDataSetChanged();
    }

    public void remove(Aluno aluno) {

        alunos.remove(aluno);
        notifyDataSetChanged();
    }

}
