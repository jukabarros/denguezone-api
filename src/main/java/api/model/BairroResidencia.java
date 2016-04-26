package api.model;

import java.io.Serializable;

public class BairroResidencia implements Serializable {

	private static final long serialVersionUID = -506982896668052070L;
	
	private Integer codigo;
	
	private String nome;
	
	private String municipio;

	public BairroResidencia() {
	}

	public BairroResidencia(Integer codigo, String nome, String municipio) {
		this.codigo = codigo;
		this.nome = nome;
		this.municipio = municipio;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BairroResidencia other = (BairroResidencia) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BairroResidencia [codigo=" + codigo + ", nome=" + nome + "]";
	}

}
