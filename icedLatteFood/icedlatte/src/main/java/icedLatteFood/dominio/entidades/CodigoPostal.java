package dominio.entidades;

import java.util.*;

public enum CodigoPostal {
	CP_45600(45600),
	CP_28040(28040),
	CP_28041(28041),
	CP_28042(28042),
	CP_25874(25874),
	CP_28045(28045);

	private final int codigo;
	private final Collection<Repartidor> repartidores;

	CodigoPostal(int codigo) {
		this.codigo = codigo;
		this.repartidores = new ArrayList<>();
	}

	public int getCodigo() {
		return codigo;
	}

	public Collection<Repartidor> getRepartidores() {
		return repartidores;
	}

	public void addRepartidor(Repartidor repartidor) {
		repartidores.add(repartidor);
	};
}
