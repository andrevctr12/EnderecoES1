package unioeste.geral.dao;

import unioeste.geral.bo.endereco.Logradouro;

import java.util.List;

/**
 * @author André Victor
 * @author Cristopher Vidal
 * @author Khadije El Zein
 */
public interface LogradouroDAO {
    public void inserir(Logradouro objeto);
    public void atualizar(Logradouro objeto);
    public void excluir(int id);
    public Logradouro consultar(Logradouro objeto) throws Exception;
    public Logradouro consultar(int id) throws Exception;
    public List<Logradouro> consultarTodos();
}
