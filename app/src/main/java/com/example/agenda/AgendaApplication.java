package com.example.agenda;

import android.app.Application;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.agenda.dao.AlunoDAO;
import com.example.agenda.model.Aluno;

public class AgendaApplication extends Application {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreate() {
        super.onCreate();
        CriaAlunosDeTeste();
    }

    //ESSES DADOS SÃO PARA INICIAR JUNTO COM O APLICATIVO
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void CriaAlunosDeTeste() {
        AlunoDAO dao = new AlunoDAO();
        dao.salva(new Aluno("Alex", "(11) 95962-9985", "alex@alura.com.br", "Sistema da informação"));
        dao.salva(new Aluno("Fran", "(11) 95582-1245", "fran@gmail.com", "Matematica"));
        dao.salva(new Aluno("Isaac", "(11) 96922-1245", "isaac@gmail.com.br", "Geografia"));
        dao.salva(new Aluno("Nicolas", "(11) 95232-1955", "nicolas@gmail.com", "Flutter"));
        dao.salva(new Aluno("Osvaldo", "(11) 95962-1292", "osvaldo@gmail.com.br", "Android"));
        dao.salva(new Aluno("Bianca", "(11) 95052-9365", "bianca@gmail.com", "IOS"));
        dao.salva(new Aluno("Gabriel", "(11) 95482-99345", "gabriel@gmail.com.br", "Mobile"));
        dao.salva(new Aluno("Tiago", "(11) 94578-3254", "tiago@gmail.com", "Farmacia"));
        dao.salva(new Aluno("Bruno", "(11) 96523-1457", "bruno@gmail.com.br", "Dentista"));
        dao.salva(new Aluno("Silvana", "(11) 95482-1245", "silvana@gmail.com", "Educação Fisica"));
    }
}
