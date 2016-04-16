package api.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class CasosAedes implements Serializable{

	private static final long serialVersionUID = 4658454227766881007L;
	
	private Integer nuNotificacao;
	private int tpNotificacao;
	private String coCid;
	private Timestamp dtNotificacao;
	private int dsSemanaNotificao;
	private int anoNotificacao;
	private Timestamp dtDiagnosticoSintoma;
	private int dsSemanaSintoma;
	private Timestamp dtNascimento;
	private String tpSexo;
	private int tpGestante;
	private int tpRacaCor;
	private int tpEscolaridade;
	private int coUfResidencia;
	private int coMunicipioResidencia;
	private int coDistritoResidencia;
	private int coBairroResidencia;
	private int tpZonaResidencia;
	private int tpClassificacaoFinal;
	private int tpCriterioConfirmacao;
	private int tpEvolucaoCaso;
	
	public CasosAedes() {
		
	}
	
	public Integer getNuNotificacao() {
		return nuNotificacao;
	}
	public void setNuNotificacao(Integer nuNotificacao) {
		this.nuNotificacao = nuNotificacao;
	}
	public int getTpNotificacao() {
		return tpNotificacao;
	}
	public void setTpNotificacao(int tpNotificacao) {
		this.tpNotificacao = tpNotificacao;
	}
	public String getCoCid() {
		return coCid;
	}
	public void setCoCid(String coCid) {
		this.coCid = coCid;
	}
	public Timestamp getDtNotificacao() {
		return dtNotificacao;
	}
	public void setDtNotificacao(Timestamp dtNotificacao) {
		this.dtNotificacao = dtNotificacao;
	}
	public int getDsSemanaNotificao() {
		return dsSemanaNotificao;
	}
	public void setDsSemanaNotificao(int dsSemanaNotificao) {
		this.dsSemanaNotificao = dsSemanaNotificao;
	}
	public int getAnoNotificacao() {
		return anoNotificacao;
	}
	public void setAnoNotificacao(int anoNotificacao) {
		this.anoNotificacao = anoNotificacao;
	}
	public Timestamp getDtDiagnosticoSintoma() {
		return dtDiagnosticoSintoma;
	}
	public void setDtDiagnosticoSintoma(Timestamp dtDiagnosticoSintoma) {
		this.dtDiagnosticoSintoma = dtDiagnosticoSintoma;
	}
	public int getDsSemanaSintoma() {
		return dsSemanaSintoma;
	}
	public void setDsSemanaSintoma(int dsSemanaSintoma) {
		this.dsSemanaSintoma = dsSemanaSintoma;
	}
	public Timestamp getDtNascimento() {
		return dtNascimento;
	}
	public void setDtNascimento(Timestamp dtNascimento) {
		this.dtNascimento = dtNascimento;
	}
	public String getTpSexo() {
		return tpSexo;
	}
	public void setTpSexo(String tpSexo) {
		this.tpSexo = tpSexo;
	}
	public int getTpGestante() {
		return tpGestante;
	}
	public void setTpGestante(int tpGestante) {
		this.tpGestante = tpGestante;
	}
	public int getTpRacaCor() {
		return tpRacaCor;
	}
	public void setTpRacaCor(int tpRacaCor) {
		this.tpRacaCor = tpRacaCor;
	}
	public int getTpEscolaridade() {
		return tpEscolaridade;
	}
	public void setTpEscolaridade(int tpEscolaridade) {
		this.tpEscolaridade = tpEscolaridade;
	}
	public int getCoUfResidencia() {
		return coUfResidencia;
	}
	public void setCoUfResidencia(int coUfResidencia) {
		this.coUfResidencia = coUfResidencia;
	}
	public int getCoMunicipioResidencia() {
		return coMunicipioResidencia;
	}
	public void setCoMunicipioResidencia(int coMunicipioResidencia) {
		this.coMunicipioResidencia = coMunicipioResidencia;
	}
	public int getCoDistritoResidencia() {
		return coDistritoResidencia;
	}
	public void setCoDistritoResidencia(int coDistritoResidencia) {
		this.coDistritoResidencia = coDistritoResidencia;
	}
	public int getCoBairroResidencia() {
		return coBairroResidencia;
	}
	public void setCoBairroResidencia(int coBairroResidencia) {
		this.coBairroResidencia = coBairroResidencia;
	}
	public int getTpZonaResidencia() {
		return tpZonaResidencia;
	}
	public void setTpZonaResidencia(int tpZonaResidencia) {
		this.tpZonaResidencia = tpZonaResidencia;
	}
	public int getTpClassificacaoFinal() {
		return tpClassificacaoFinal;
	}
	public void setTpClassificacaoFinal(int tpClassificacaoFinal) {
		this.tpClassificacaoFinal = tpClassificacaoFinal;
	}
	public int getTpCriterioConfirmacao() {
		return tpCriterioConfirmacao;
	}
	public void setTpCriterioConfirmacao(int tpCriterioConfirmacao) {
		this.tpCriterioConfirmacao = tpCriterioConfirmacao;
	}
	public int getTpEvolucaoCaso() {
		return tpEvolucaoCaso;
	}
	public void setTpEvolucaoCaso(int tpEvolucaoCaso) {
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
		CasosAedes other = (CasosAedes) obj;
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
