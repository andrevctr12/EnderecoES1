package unioeste.geral.bo.endereco;

import java.io.Serializable;

/**
 * @author André Victor
 * @author Khadije El Zein
 */
public class Logradouro implements Serializable {
    private int idLogradouro;
    private String nome;
    private TipoLogradouro tipoLogradouro;

    public Logradouro() {
    }

    public Logradouro(TipoLogradouro tipoLogradouro) {
        this.tipoLogradouro = tipoLogradouro;
    }

    public int getIdLogradouro() {
        return idLogradouro;
    }

    public void setIdLogradouro(int idLogradouro) {
        this.idLogradouro = idLogradouro;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoLogradouro getTipoLogradouro() {
        return tipoLogradouro;
    }

    public void setTipoLogradouro(TipoLogradouro tipoLogradouro) {
        this.tipoLogradouro = tipoLogradouro;
    }
}
