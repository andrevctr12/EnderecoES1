package unioeste.geral.bo.endereco;

import java.io.Serializable;

/**
 * @author André Victor
 * @author Khadije El Zein
 */
public class EnderecoEspecifico implements Serializable {
    private int idEnderecoEspecifico;
    private int numero;
    private String complemento;
    private Endereco endereco;

    public EnderecoEspecifico() {
    }

    public EnderecoEspecifico(Endereco endereco) {

        this.endereco = endereco;
    }

    public int getIdEnderecoEspecifico() {
        return idEnderecoEspecifico;
    }

    public void setIdEnderecoEspecifico(int idEnderecoEspecifico) {
        this.idEnderecoEspecifico = idEnderecoEspecifico;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        complemento = complemento;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}
