package service;

import java.util.ArrayList;
import java.util.List;

import entities.Curso;

public class CursoService {

	private List<Curso> cursos;
	
	public CursoService() {
	
		this.cursos = new ArrayList<>();
		
		this.cursos.add(new Curso(1, "Bacharelado em Ci�ncia da Computa��o", "Integral", 8));
		this.cursos.add(new Curso(2, "Tecnologia em An�lise e Desenvolvimento de Sistemas", "Noturno", 6));
		this.cursos.add(new Curso(3, "Engenharia Mec�nica", "Integral", 10));
		this.cursos.add(new Curso(4, "Licenciatura em Ci�ncias Biol�gicas", "Integral", 8));
	}
	
	public List<Curso> buscarTodos() {
		
		return this.cursos;
	}
}
