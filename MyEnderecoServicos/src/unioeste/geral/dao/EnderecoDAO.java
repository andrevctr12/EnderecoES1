package unioeste.geral.dao;

import unioeste.geral.bo.endereco.Endereco;

import java.util.List;

/**
 * @author André Victor
 * @author Cristopher Vidal
 * @author Khadije El Zein
 */
public interface EnderecoDAO {
    public void inserir(Endereco objeto);
    public void atualizar(Endereco objeto);
    public void excluir(int id);
    public Endereco consultar(Endereco objeto);
    public Endereco consultar(int id);
    public List<Endereco> consultarTodos();


}
