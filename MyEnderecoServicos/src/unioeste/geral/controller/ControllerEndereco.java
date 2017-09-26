package unioeste.geral.controller;

import unioeste.geral.bo.endereco.*;
import unioeste.geral.exception.EnderecoException;
import unioeste.geral.manager.UCEnderecoGeralServicos;

public class ControllerEndereco {

    private UCEnderecoGeralServicos servicos;

    public ControllerEndereco() {
        servicos = new UCEnderecoGeralServicos();
    }

    public void cadastrarEndereco(EnderecoEspecifico enderecoEspecifico) throws EnderecoException {
        EnderecoEspecifico enderecoCadastrado;
        try {
            enderecoCadastrado = servicos.cadastrarEndereco(enderecoEspecifico);
            if (enderecoCadastrado == null) {
                throw new EnderecoException("Endereço não cadastrado corretamente");
            }
        } catch (EnderecoException e) {
            e.printStackTrace();
        }
    }

    public EnderecoEspecifico popularEndereco(String nomeTipoLogradouro, String nomeLogradouro,
                                              String nomeBairro, String nomeCidade, String nomeUF, String siglaUF,
                                              String nomePais, String siglaPais, String cep, String complemento, int numero) {

        TipoLogradouro tipoLogradouro = new TipoLogradouro();
        tipoLogradouro.setTipo(nomeTipoLogradouro);

        Logradouro logradouro = new Logradouro(tipoLogradouro);
        logradouro.setNome(nomeLogradouro);

        Bairro bairro = new Bairro();
        bairro.setNome(nomeBairro);

        Pais pais = new Pais();
        pais.setNome(nomePais);
        pais.setSigla(siglaPais);

        UnidadeFederativa unidadeFederativa = new UnidadeFederativa(pais);
        unidadeFederativa.setNome(nomeUF);
        unidadeFederativa.setSigla(siglaUF);

        Cidade cidade = new Cidade(unidadeFederativa);
        cidade.setNome(nomeCidade);

        Endereco endereco = new Endereco(cidade, bairro, logradouro);
        endereco.setCEP(cep);

        EnderecoEspecifico enderecoEspecifico = new EnderecoEspecifico(endereco);
        enderecoEspecifico.setComplemento(complemento);
        enderecoEspecifico.setNumero(numero);

        return enderecoEspecifico;
    }

}
