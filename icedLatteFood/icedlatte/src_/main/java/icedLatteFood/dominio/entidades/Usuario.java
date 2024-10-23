package dominio.entidades;

public class Usuario {
	private String idUsuario;
	private String pass;
	
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
}
