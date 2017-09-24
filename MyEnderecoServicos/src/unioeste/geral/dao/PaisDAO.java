package unioeste.geral.dao;

import unioeste.geral.bo.endereco.Pais;

import java.util.List;

/**
 * @author André Victor
 * @author Cristopher Vidal
 * @author Khadije El Zein
 */
public interface PaisDAO {
    public void inserir(Pais objeto);
    public void atualizar(Pais objeto);
    public void excluir(int id);
    public Pais consultar(Pais objeto);
    public Pais consultar(int id);
    public List<Pais> consultarTodos();
}
