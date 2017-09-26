package unioeste.geral.controller;

import unioeste.geral.bo.endereco.TipoLogradouro;
import unioeste.geral.manager.UCEnderecoGeralServicos;

import java.util.List;

public class ControllerTipoLogradouro {

    public List<TipoLogradouro> consultarTodos() {
        return new UCEnderecoGeralServicos().consultarTodosTipoLogradouro();
    }
}
