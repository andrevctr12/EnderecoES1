package unioeste.geral.controller;

import unioeste.geral.bo.endereco.Bairro;

public class ControllerBairro {
    public Bairro popularBairro(String nome) {
        Bairro bairro = new Bairro();
        bairro.setNome(nome);
        return bairro;
    }
}
