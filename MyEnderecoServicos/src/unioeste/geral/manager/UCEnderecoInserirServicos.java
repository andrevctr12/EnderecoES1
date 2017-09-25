package unioeste.geral.manager;

import unioeste.geral.bo.endereco.*;
import unioeste.geral.dao.UnidadeFederativaDAO;
import unioeste.geral.dao.impl.*;

/**
 * @author André Victor
 * @author Cristopher Vidal
 * @author Khadije El Zein
 */
public class UCEnderecoInserirServicos {
    private EnderecoEspecifico enderecoEspecifico;

    public UCEnderecoInserirServicos(EnderecoEspecifico enderecoEspecifico) {
        this.enderecoEspecifico = enderecoEspecifico;
    }

    public void cadastrarLogradouro() {
        Logradouro logradouro = enderecoEspecifico.getEndereco().getLogradouro();
        Logradouro logradouroDB = new LogradouroDAOImpl().consultar(logradouro);
        if (logradouroDB == null) {
            new LogradouroDAOImpl().inserir(logradouro);
        }
        else if (logradouro.getTipoLogradouro().getIdTipoLogradouro() != logradouro.getTipoLogradouro().getIdTipoLogradouro()) {
            new LogradouroDAOImpl().inserir(logradouro);
        }
    }

    public void cadastrarPais() {

    }

    public void cadastrarBairro() {
        Bairro bairro = enderecoEspecifico.getEndereco().getBairro();
        Bairro bairroDB = new BairroDAOImpl().consultar(bairro);
        if (bairroDB != null) {
            bairro.setIdBairro(bairroDB.getIdBairro());
        }
        else {
            new BairroDAOImpl().inserir(bairro);
        }
    }

    public void cadastrarCidade() {
        Cidade cidade = enderecoEspecifico.getEndereco().getCidade();
        Cidade cidadeDB = new CidadeDAOImpl().consultar(cidade);
        if (cidadeDB != null) {
            cidade.setIdCidade(cidadeDB.getIdCidade());
        }
        else {
            new CidadeDAOImpl().inserir(cidade);
        }
    }

    public void cadastrarUnidadeFederativa() {

    }

    public void cadastrarEndereco() {
        Endereco endereco = enderecoEspecifico.getEndereco();
        Endereco enderecoDB = new EnderecoDAOImpl().consultar(endereco);
        if (enderecoDB != null) {
            endereco.setIdEndereco(enderecoDB.getIdEndereco());
        }
        else {
            new EnderecoDAOImpl().inserir(endereco);
        }
    }
}
