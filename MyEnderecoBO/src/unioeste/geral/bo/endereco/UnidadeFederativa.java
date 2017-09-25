package unioeste.geral.bo.endereco;

import java.io.Serializable;

/**
 * @author André Victor
 * @author Khadije El Zein
 */
public class UnidadeFederativa implements Serializable {
    private int idUnidadeFederativa;
    private String nome;
    private String sigla;
    private Pais pais;

    public UnidadeFederativa() {
    }

    public UnidadeFederativa(Pais pais) {
        this.pais = pais;
    }

    public int getIdUnidadeFederativa() {
        return idUnidadeFederativa;
    }

    public void setIdUnidadeFederativa(int idUnidadeFederativa) {
        this.idUnidadeFederativa = idUnidadeFederativa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }
}
