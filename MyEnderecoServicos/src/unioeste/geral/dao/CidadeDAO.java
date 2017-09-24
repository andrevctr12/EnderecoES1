package unioeste.geral.dao;

import unioeste.geral.bo.endereco.Cidade;

import java.util.List;

/**
 * @author André Victor
 * @author Cristopher Vidal
 * @author Khadije El Zein
 */
public interface CidadeDAO {
    public void inserir(Cidade objeto);
    public void atualizar(Cidade objeto);
    public void excluir(int id);
    public Cidade consultar(Cidade objeto);
    public Cidade consultar(int id);
    public List<Cidade> consultarTodos();
}
