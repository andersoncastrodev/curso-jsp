package br.com.model;

import java.io.Serializable;

public class ModelLogin implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long  id;
	
	private String login ;
	
	private String senha;
	
	private String nome;
	
	private String email;

	private Boolean useradmin; 
	
	private String perfil;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	

	public Boolean getUseradmin() {
		return useradmin;
	}

	public void setUseradmin(Boolean useradmin) {
		this.useradmin = useradmin;
	}
	
	public String getPerfil() {
		return perfil;
	}
	
	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	//Verifica se os usuario ja foi criando.
	public boolean isNovo() {		
		if(this.id == null) {
			return true; //Para Inserir um Novo.
		}else if(this.id != null && this.id > 0) {
			return false; //Atualizar que ja existe.
		}	
		return id == null;
	}
	
	@Override
	public String toString() {
		return "ModelLogin [id=" + id + ", login=" + login + ", senha=" + senha + ", nome=" + nome + ", email=" + email
				+ "]";
	}
	
}
