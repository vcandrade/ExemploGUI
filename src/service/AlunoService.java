package service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import entities.Aluno;
import entities.Curso;

public class AlunoService {

	private List<Aluno> alunos;
	
	public AlunoService() {
		
		this.alunos = new ArrayList<>();
		
		this.alunos.add(new Aluno(111111, "João da Silva", "Masculino", new Curso(2, "Tecnologia em Análise e Desenvolvimento de Sistemas", "Noturno", 6), new Date(2020,  02,  01), 4, 0.58));
		this.alunos.add(new Aluno(222222, "Maria de Oliveira", "Feminino", new Curso(4, "Licenciatura em Ciências Biológicas", "Integral", 8), new Date(2022,  8,  1), 1, 0.00));
		this.alunos.add(new Aluno(333333, "Ricardo Carvalho", "Masculino", new Curso(1, "Bacharelado em Ciência da Computação", "Integral", 8), new Date(2020,  10,  15), 6, 0.83));
	}
	
	public List<Aluno> buscarTodos() {
		
		return this.alunos;
	}
	
	public void cadastrar(Aluno aluno) {
		
		this.alunos.add(aluno);
	}
}
