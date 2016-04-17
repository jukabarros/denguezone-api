package api.model;

import java.io.Serializable;

public class CasosAedesStrings implements Serializable{

	private static final long serialVersionUID = 4658454227766881007L;
	
	private String nuNotificacao;
	private String tpNotificacao;
	private String coCid;
	private String dtNotificacao;
	private String dsSemanaNotificao;
	private String anoNotificacao;
	private String dtDiagnosticoSintoma;
	private String dsSemanaSintoma;
	private String dtNascimento;
	private String tpSexo;
	private String tpGestante;
	private String tpRacaCor;
	private String tpEscolaridade;
	private String coUfResidencia;
	private String coMunicipioResidencia;
	private String coDistritoResidencia;
	private String coBairroResidencia;
	private String tpZonaResidencia;
	private String tpClassificacaoFinal;
	private String tpCriterioConfirmacao;
	private String tpEvolucaoCaso;
	
	public CasosAedesStrings() {
		
	}
	
	public String getNuNotificacao() {
		return nuNotificacao;
	}

	public void setNuNotificacao(String nuNotificacao) {
		this.nuNotificacao = nuNotificacao;
	}

	public String getTpNotificacao() {
		return tpNotificacao;
	}

	public void setTpNotificacao(String tpNotificacao) {
		this.tpNotificacao = tpNotificacao;
	}

	public String getCoCid() {
		return coCid;
	}

	public void setCoCid(String coCid) {
		this.coCid = coCid;
	}

	public String getDtNotificacao() {
		return dtNotificacao;
	}

	public void setDtNotificacao(String dtNotificacao) {
		this.dtNotificacao = dtNotificacao;
	}

	public String getDsSemanaNotificao() {
		return dsSemanaNotificao;
	}

	public void setDsSemanaNotificao(String dsSemanaNotificao) {
		this.dsSemanaNotificao = dsSemanaNotificao;
	}

	public String getAnoNotificacao() {
		return anoNotificacao;
	}

	public void setAnoNotificacao(String anoNotificacao) {
		this.anoNotificacao = anoNotificacao;
	}

	public String getDtDiagnosticoSintoma() {
		return dtDiagnosticoSintoma;
	}

	public void setDtDiagnosticoSintoma(String dtDiagnosticoSintoma) {
		this.dtDiagnosticoSintoma = dtDiagnosticoSintoma;
	}

	public String getDsSemanaSintoma() {
		return dsSemanaSintoma;
	}

	public void setDsSemanaSintoma(String dsSemanaSintoma) {
		this.dsSemanaSintoma = dsSemanaSintoma;
	}

	public String getDtNascimento() {
		return dtNascimento;
	}

	public void setDtNascimento(String dtNascimento) {
		this.dtNascimento = dtNascimento;
	}

	public String getTpSexo() {
		return tpSexo;
	}

	public void setTpSexo(String tpSexo) {
		this.tpSexo = tpSexo;
	}

	public String getTpGestante() {
		return tpGestante;
	}

	public void setTpGestante(String tpGestante) {
		this.tpGestante = tpGestante;
	}

	public String getTpRacaCor() {
		return tpRacaCor;
	}

	public void setTpRacaCor(String tpRacaCor) {
		this.tpRacaCor = tpRacaCor;
	}

	public String getTpEscolaridade() {
		return tpEscolaridade;
	}

	public void setTpEscolaridade(String tpEscolaridade) {
		this.tpEscolaridade = tpEscolaridade;
	}

	public String getCoUfResidencia() {
		return coUfResidencia;
	}

	public void setCoUfResidencia(String coUfResidencia) {
		this.coUfResidencia = coUfResidencia;
	}

	public String getCoMunicipioResidencia() {
		return coMunicipioResidencia;
	}

	public void setCoMunicipioResidencia(String coMunicipioResidencia) {
		this.coMunicipioResidencia = coMunicipioResidencia;
	}

	public String getCoDistritoResidencia() {
		return coDistritoResidencia;
	}

	public void setCoDistritoResidencia(String coDistritoResidencia) {
		this.coDistritoResidencia = coDistritoResidencia;
	}

	public String getCoBairroResidencia() {
		return coBairroResidencia;
	}

	public void setCoBairroResidencia(String coBairroResidencia) {
		this.coBairroResidencia = coBairroResidencia;
	}

	public String getTpZonaResidencia() {
		return tpZonaResidencia;
	}

	public void setTpZonaResidencia(String tpZonaResidencia) {
		this.tpZonaResidencia = tpZonaResidencia;
	}

	public String getTpClassificacaoFinal() {
		return tpClassificacaoFinal;
	}

	public void setTpClassificacaoFinal(String tpClassificacaoFinal) {
		this.tpClassificacaoFinal = tpClassificacaoFinal;
	}

	public String getTpCriterioConfirmacao() {
		return tpCriterioConfirmacao;
	}

	public void setTpCriterioConfirmacao(String tpCriterioConfirmacao) {
		this.tpCriterioConfirmacao = tpCriterioConfirmacao;
	}

	public String getTpEvolucaoCaso() {
		return tpEvolucaoCaso;
	}

	public void setTpEvolucaoCaso(String tpEvolucaoCaso) {
		this.tpEvolucaoCaso = tpEvolucaoCaso;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((coCid == null) ? 0 : coCid.hashCode());
		result = prime * result + ((nuNotificacao == null) ? 0 : nuNotificacao.hashCode());
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
		CasosAedesStrings other = (CasosAedesStrings) obj;
		if (coCid == null) {
			if (other.coCid != null)
				return false;
		} else if (!coCid.equals(other.coCid))
			return false;
		if (nuNotificacao == null) {
			if (other.nuNotificacao != null)
				return false;
		} else if (!nuNotificacao.equals(other.nuNotificacao))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CasosAedes [nuNotificacao=" + nuNotificacao + ", coCid=" + coCid + ", coBairroResidencia="
				+ coBairroResidencia + "]";
	}
	
}
