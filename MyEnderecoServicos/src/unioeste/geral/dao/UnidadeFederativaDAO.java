package unioeste.geral.dao;

import unioeste.geral.bo.endereco.Logradouro;
import unioeste.geral.bo.endereco.UnidadeFederativa;

import java.util.List;

/**
 * @author André Victor
 * @author Cristopher Vidal
 * @author Khadije El Zein
 */
public interface UnidadeFederativaDAO {
    public void inserir(UnidadeFederativa objeto);
    public void atualizar(UnidadeFederativa objeto);
    public void excluir(int id);
    public UnidadeFederativa consultar(UnidadeFederativa objeto);
    public UnidadeFederativa consultar(int id);
    public List<UnidadeFederativa> consultarTodos();
}
