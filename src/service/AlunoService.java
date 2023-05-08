package service;

import java.util.ArrayList;
import java.util.List;

import entities.Aluno;
import entities.Curso;

public class AlunoService {

	private List<Aluno> alunos;
	
	public AlunoService() {
	
		alunos = new ArrayList<Aluno>();
		alunos.add(new Aluno(111111, "João da Silva", "Masculino", new Curso(2, "Tecnologia em Análise e Desenvolvimento de Sistemas", "Noturno", 6), "01/02/2020", 4, 0.58));
		alunos.add(new Aluno(222222, "Maria de Oliveira", "Feminino", new Curso(4, "Licenciatura em Ciências Biológicas", "Integral", 8), "01/08/2022", 1, 0.0));
		alunos.add(new Aluno(333333, "Ricardo Carvalho", "Masculino", new Curso(1, "Bacharelado em Ciência da Computação", "Integral", 8), "01/08/2020", 6, 0.83));
	}
	
	public List<Aluno> buscarTodos() {
		
		return this.alunos;
	}

	public void cadastrar(Aluno aluno) {
		
		this.alunos.add(aluno);
	}
}
