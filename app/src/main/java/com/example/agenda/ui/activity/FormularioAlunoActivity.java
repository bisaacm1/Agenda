package com.example.agenda.ui.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.agenda.R;
import com.example.agenda.dao.AlunoDAO;
import com.example.agenda.model.Aluno;

@RequiresApi(api = Build.VERSION_CODES.O)
public class FormularioAlunoActivity extends AppCompatActivity {

    private static final String TITULO_APPBAR_NOVO_ALUNO = "Novo aluno";
    private static final String TITULO_APPBAR_EDITA_ALUNO = "Edita aluno";
    private EditText campoNome;
    private EditText campoTelefone;
    private EditText campoEmail;
    private EditText campoMateria;
    private final AlunoDAO dao = new AlunoDAO();
    private Aluno aluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_aluno);
        inicializacaoDosCampos();
        carregaAluno();
    }

    @Override
    //METODO RESPONSAVEL POR PEGAR O LAYOUT DO MENU
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater()
                .inflate(R.menu.activity_formulario_aluno_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        //AO CLICAR NO ITEM SALVAR
        if (itemId == R.id.activity_formulario_aluno_menu_salvar) {

            finalizaFormulario();
        }

        return super.onOptionsItemSelected(item);
    }

    private void carregaAluno() {
        Intent dados = getIntent();

        //COM BASE NA CHAVE_ALUNO SABERA EM QUAL TELA ESTA SALVAR OU ATUALIZAR
        if (dados.hasExtra(ConstantesActivities.CHAVE_ALUNO)) {

            setTitle(TITULO_APPBAR_EDITA_ALUNO);

            aluno = (Aluno) dados.getSerializableExtra(ConstantesActivities.CHAVE_ALUNO);
            preencheCampos();
        } else {
            setTitle(TITULO_APPBAR_NOVO_ALUNO);

            aluno = new Aluno();
        }
    }

    //METODO RESPONSAVEL PARA JA TRAZER PREENCHIDO OS EDITTEXT
    private void preencheCampos() {
        campoNome.setText(aluno.getNome());
        campoTelefone.setText(aluno.getTelefone());
        campoEmail.setText(aluno.getEmail());
        campoMateria.setText(aluno.getMateria());
    }

    private void finalizaFormulario() {

        preencheAluno();
        if (aluno.temIdValido()) {
            dao.edita(aluno);
            //PARA VOLTAR A TELA PRINCIPAL
            finish();
        } else {
            VerificaVazioESalva();

        }

    }

    //VERIFICA SE OS CAMPOS ESTAO VAZIOS
    private void VerificaVazioESalva() {
        if ("".equals(aluno.getNome())) {
            Toast.makeText(this, "Preencha o campo nome", Toast.LENGTH_SHORT).show();
        } else {
            dao.salva(aluno);
            //PARA VOLTAR A TELA PRINCIPAL
            finish();
        }


    }

    //PEGAR CADA CAMPO DO LAYOUT E SETAR A UMA VARIAVEL
    private void inicializacaoDosCampos() {
        campoNome = findViewById(R.id.activity_formulario_aluno_nome);
        campoTelefone = findViewById(R.id.activity_formulario_aluno_telefone);
        campoEmail = findViewById(R.id.activity_formulario_aluno_email);
        campoMateria = findViewById(R.id.activity_formulario_aluno_materia);
    }

    //PEFA OS DADOS CADASTRADOS E SETA A CADA CAMPO
    private void preencheAluno() {
        String nome = campoNome.getText().toString();
        String telefone = campoTelefone.getText().toString();
        String email = campoEmail.getText().toString();
        String materia = campoMateria.getText().toString();

        aluno.setNome(nome);
        aluno.setTelefone(telefone);
        aluno.setEmail(email);
        aluno.setMateria(materia);
    }
}
