package icedLatteFood.dominio.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String idUsuario;
	@Column
	private String pass;

	public Usuario(String pass) {
		this.pass=pass;
	}
	
	public String getIdUsuario() {
		 return idUsuario;
	}
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	//otro metodo
	public boolean autenticar(String password) {
        return this.pass.equals(password);
    }

	@Override
	public String toString() {
		return String.format("Greeting [idUsuario=%s, password=%s]", idUsuario, pass);
	}
}
