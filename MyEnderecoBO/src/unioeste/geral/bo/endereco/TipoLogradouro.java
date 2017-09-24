package unioeste.geral.bo.endereco;

import java.io.Serializable;

/**
 * @author André Victor
 * @author Khadije El Zein
 */
public class TipoLogradouro implements Serializable {
    private int idTipoLogradouro;
    private String tipo;

    public int getIdTipoLogradouro() {
        return idTipoLogradouro;
    }

    public void setIdTipoLogradouro(int idTipoLogradouro) {
        this.idTipoLogradouro = idTipoLogradouro;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
