package unioeste.geral.dao;

import unioeste.geral.bo.endereco.TipoLogradouro;

import java.util.List;

/**
 * @author André Victor
 * @author Cristopher Vidal
 * @author Khadije El Zein
 */
public interface TipoLogradouroDAO {
    public void inserir(TipoLogradouro objeto);
    public void atualizar(TipoLogradouro objeto);
    public void excluir(int id);
    public TipoLogradouro consultar(TipoLogradouro objeto) throws Exception;
    public TipoLogradouro consultar(int id);
    public List<TipoLogradouro> consultarTodos();

}
