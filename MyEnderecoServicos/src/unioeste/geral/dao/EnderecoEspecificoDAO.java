package unioeste.geral.dao;

import unioeste.geral.bo.endereco.EnderecoEspecifico;

import java.util.List;

/**
 * @author André Victor
 * @author Cristopher Vidal
 * @author Khadije El Zein
 */
public interface EnderecoEspecificoDAO {
    public void inserir(EnderecoEspecifico objeto);
    public void atualizar(EnderecoEspecifico objeto);
    public void excluir(int id);
    public EnderecoEspecifico consultar(EnderecoEspecifico objeto);
    public EnderecoEspecifico consultar(int id);
    public List<EnderecoEspecifico> consultarTodos();

}
