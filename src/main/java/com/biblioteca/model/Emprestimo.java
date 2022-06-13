package com.biblioteca.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "emprestimos")

public class Emprestimo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "leitor", length = 50, nullable = false, unique = true)
	private String leitor;

	@Column(name = "livro", length = 100, nullable = false)
	private String livro;

	@Column(name = "dataEmprestimo", length = 50, nullable = false)
	private Date dataEmprestimo;

	@Column(name = "dataPevistaDevolucao", length = 250, nullable = false)
	private Date dataPrevistaDevolucao;

	@OneToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "leitor_emp", joinColumns = { @JoinColumn(name = "leitor_id") }, inverseJoinColumns = {
			@JoinColumn(name = "leitor_id") })
	private Set<Leitor> leitores = new HashSet<Leitor>();

	@OneToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "emprestimo_", joinColumns = { @JoinColumn(name = "livro_id") }, inverseJoinColumns = {
			@JoinColumn(name = "emprestimo_id") })
	private Set<Livro> livros = new HashSet<Livro>();

	public Emprestimo( Date dataEmprestimo, Date dataPrevistaDevolucao) {
		this.dataEmprestimo = dataEmprestimo;
		this.dataPrevistaDevolucao = dataPrevistaDevolucao;
		
	}

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLeitor() {
		return leitor;
	}

	public void setLeitor(String leitor) {
		this.leitor = leitor;
	}

	public String getLivro() {
		return livro;
	}

	public void setTitulo(String livro) {
		this.livro = livro;
	}

	public Date getDataEmprestimo() {
		return dataEmprestimo;
	}

	public void setDataEmprestimo(Date dataEmprestimo) {
		this.dataEmprestimo = dataEmprestimo;
	}

	public Date getDataPrevistaDevolucao() {
		return dataPrevistaDevolucao;
	}

	public void setDataPrevistaDevolucao(Date dataPrevistaDevolucao) {
		this.dataPrevistaDevolucao = dataPrevistaDevolucao;
	}

	
	
public Emprestimo() {
		
	}
	
}
