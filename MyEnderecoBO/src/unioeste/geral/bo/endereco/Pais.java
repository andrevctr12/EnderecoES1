package unioeste.geral.bo.endereco;

import java.io.Serializable;

/**
 * @author André Victor
 * @author Khadije El Zein
 */
public class Pais implements Serializable {
    private int idPais;
    private String nome;
    private String sigla;

    public int getIdPais() {
        return idPais;
    }

    public void setIdPais(int idPais) {
        this.idPais = idPais;
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
}
