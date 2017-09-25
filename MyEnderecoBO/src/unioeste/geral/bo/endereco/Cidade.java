package unioeste.geral.bo.endereco;

import java.io.Serializable;

/**
 * @author André Victor
 * @author Khadije El Zein
 */
public class Cidade implements Serializable {
    private int idCidade;
    private String nome;
    private UnidadeFederativa unidadeFederativa;

    public Cidade() {
    }

    public Cidade(UnidadeFederativa unidadeFederativa) {
        this.unidadeFederativa = unidadeFederativa;
    }

    public int getIdCidade() {
        return idCidade;
    }

    public void setIdCidade(int idCidade) {
        this.idCidade = idCidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public UnidadeFederativa getUnidadeFederativa() {
        return unidadeFederativa;
    }

    public void setUnidadeFederativa(UnidadeFederativa unidadeFederativa) {
        this.unidadeFederativa = unidadeFederativa;
    }
}
