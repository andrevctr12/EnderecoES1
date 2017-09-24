package unioeste.geral.dao;

import unioeste.geral.bo.endereco.Bairro;

import java.util.List;

/**
 * @author André Victor
 * @author Cristopher Vidal
 * @author Khadije El Zein
 */
public interface BairroDAO {
    public void inserir(Bairro objeto);
    public void atualizar(Bairro objeto);
    public void excluir(int id);
    public Bairro consultar(Bairro objeto);
    public Bairro consultar(int id);
    public List<Bairro> consultarTodos();

}
