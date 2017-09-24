//package unioeste.geral.controller;
//
//import unioeste.geral.bo.endereco.*;
//import unioeste.geral.endereco.dao.*;
//import unioeste.geral.endereco.exception.EnderecoException;
//import unioeste.geral.exception.EnderecoException;
//import unioeste.geral.manager.UCEnderecoGeralServicos;
//
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//
//public class EnderecoControle {
//    private final Connection connection;
//    private static final String USUARIO_BD = "root";
//    private static final String SENHA_BD   = "";
//    private static final String URL_BD     = "jdbc:mysql://localhost/endereco";
//
//    public EnderecoControle() {
//
//    }
//
//    public EnderecoEspecifico salvarEndereco(EnderecoEspecifico endereco) throws EnderecoException {
//        return new UCEnderecoGeralServicos().cadastrarEndereco(endereco);
//    }
//
//    public void removerEndereco(EnderecoEspecifico endereco) throws SQLException {
//        new UCEnderecoGeralServicos().excluirEndereco(endereco);
//    }
//
//    public ArrayList<String> obterListaAtributos(Endereco endereco) {
//        ArrayList<String> atributos = new ArrayList();
//        atributos.add(String.valueOf(endereco.getIdEndereco()));
//        atributos.add(endereco.getCep());
//        atributos.add(endereco.getLogradouro().getTipoLogradouro().getNomeTipoLogradouro());
//        atributos.add(endereco.getLogradouro().getNomeLogradouro());
//        atributos.add(endereco.getBairro().getNomeBairro());
//        atributos.add(endereco.getCidade().getNomeCidade());
//        atributos.add(endereco.getCidade().getUnidadeFederativa().getNomeUnidadeFederativa());
//        atributos.add(endereco.getCidade().getUnidadeFederativa().getPais().getNomePais());
//        return atributos;
//    }
//
//    public Endereco popularEndereco(String nomeTipoLogradouro, String nomeLogradouro, String nomeBairro,
//                                     String nomeCidade, String nomeUF, String siglaUF, String nomePais,
//                                     String siglaPais, String cep) {
//        Pais pais = new Pais(nomePais, siglaPais);
//        new PaisControle().salvarPais(pais);
//
//        UnidadeFederativa unidadeFederativa = new UnidadeFederativa(nomeUF, siglaUF, pais);
//        new UnidadeFederativaControle().salvarUnidadeFederativa(unidadeFederativa);
//
//        Cidade cidade = new Cidade(nomeCidade, unidadeFederativa);
//        new CidadeControle().salvarCidade(cidade);
//
//        Bairro bairro = new Bairro(nomeBairro);
//        new BairroControle().salvarBairro(bairro);
//
//        TipoLogradouro tipoLogradouro = new TipoLogradouro(nomeTipoLogradouro);
//        new TipoLogradouroControle().salvarTipoLogradouro(tipoLogradouro);
//
//        Logradouro logradouro = new Logradouro(nomeLogradouro, tipoLogradouro);
//        new LogradouroControle().salvarLogradouro(logradouro);
//
//        return new Endereco(cep, logradouro, bairro, cidade);
//    }
//
//    public Endereco populaEnderecoPorObjeto(UnidadeFederativa uf, Cidade cidade, Bairro bairro,
//                                                Logradouro logradouro,int idEndereco, String cep){
//        Endereco endereco = new Endereco();
//        endereco.setBairro(bairro);
//        endereco.setCep(cep);
//        endereco.setCidade(cidade);
//        endereco.setLogradouro(logradouro);
//        endereco.setIdEndereco(idEndereco);
//
//        return endereco;
//    }
//
//    public Endereco populaApartirDoBD(int idEndereco, String cep, int idLogradouro,
//                                        int idBairro, int idCidade, String idUF){
//        LogradouroControle controleLogradouro = new LogradouroControle();
//        Logradouro logradouro =controleLogradouro.obterLogradouro(idLogradouro);
//
//        BairroControle controleBairro = new BairroControle();
//        Bairro bairro = controleBairro.obterBairro(idBairro);
//
//        CidadeControle controleCidade = new CidadeControle();
//        Cidade cidade = controleCidade.obterCidade(idCidade);
//
//        UnidadeFederativaControle controleUF = new UnidadeFederativaControle();
//        UnidadeFederativa uF= controleUF.obterUnidadeFederativa(idUF);
//
//        EnderecoControle controleEndereco = new EnderecoControle();
//        Endereco endereco = controleEndereco.populaEnderecoPorObjeto(uF, cidade, bairro, logradouro, idEndereco, cep);
//
//        return endereco;
//
//    }
//
//    public ArrayList<Endereco> obterListaEndereco() throws Exception {
//    	ArrayList<Endereco> lista= new ArrayList<Endereco>();
//    	EnderecoDAO enderecoDao = new EnderecoDAO();
//        ResultSet rs = null;
//
//        try {
//            rs = enderecoDao.buscarTodosEnderecos(connection);
//        } catch (EnderecoException e) {
//            e.printStackTrace();
//        }
//        if (rs != null) {
//            try {
//                while(rs.next()){
//                    Endereco endereco = new Endereco();
//                    endereco = populaApartirDoBD(rs.getInt("idEndereco"),rs.getString("cep"),
//                                                    rs.getInt("Logradouro_idLogradouro"),
//                                                    rs.getInt("Bairro_idBairro"),
//                                                    rs.getInt("Cidade_idCidade"),
//                                                    rs.getString("Cidade_UnidadeFederativa_idUnidadeFederativa"));
//                    lista.add(endereco);
//                }
//                connection.close();
//                return lista;
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//		return null;
//    }
//
//    public ArrayList<String> obterListaEnderecoString() throws Exception{
//        ArrayList<String> nomes = new ArrayList<String>();
//        ArrayList<Endereco> endereco = obterListaEndereco();
//        for(int i=0; i<endereco.size();i++){
//            nomes.add(Integer.toString(endereco.get(i).getIdEndereco()));
//            nomes.add(endereco.get(i).getCep());
//            nomes.add(Integer.toString(endereco.get(i).getLogradouro().getTipoLogradouro().getIdTipoLogradouro()));
//            nomes.add(Integer.toString(endereco.get(i).getBairro().getIdBairro()));
//            nomes.add(Integer.toString(endereco.get(i).getCidade().getIdCidade()));
//            nomes.add(Integer.toString(endereco.get(i).getCidade().getUnidadeFederativa().getIdUnidadeFederativa()));
//            nomes.add(Integer.toString(endereco.get(i).getCidade().getUnidadeFederativa().getPais().getIdPais()));
//        }
//        return nomes;
//    }
//
//    public ArrayList<String> obterEnderecoString(String idlogradouro, String idbairro,
//                                                    String idcidade, String iduf) throws Exception{
//        ArrayList<String> nomes = new ArrayList<String>();
//        ArrayList<Endereco> endereco = obterListaEndereco();
//        for(int i=0; i<endereco.size();i++){
//            if(endereco.get(i).getLogradouro().getIdLogradouro()==Integer.parseInt(idlogradouro) &&
//               endereco.get(i).getBairro().getIdBairro()==Integer.parseInt(idbairro) &&
//               endereco.get(i).getCidade().getIdCidade()==Integer.parseInt(idcidade) &&
//               endereco.get(i).getCidade().getUnidadeFederativa().getIdUnidadeFederativa()==Integer.parseInt(iduf)
//                ){
//                    nomes.add(Integer.toString(endereco.get(i).getIdEndereco()));
//                    nomes.add(endereco.get(i).getCep());
//            }
//
//        }
//        return nomes;
//    }
//
//    public void alteraEndereco(String cep, String nomeTipoLogradouro , String nomeLogradouro,
//                                String nomeBairro, String nomeCidade, String nomeUF, String nomePais,
//                                String IdTipoLogradouro, String IdLogradouro, String IdBairro,
//                                String IdCidade, String IdUF, String IdPais){
//        Pais pais = new Pais();
//        pais.setIdPais(Integer.parseInt(IdPais));
//        pais.setNomePais(nomePais);
//
//        UnidadeFederativa unidadeFederativa = new UnidadeFederativa();
//        unidadeFederativa.setIdUnidadeFederativa(Integer.parseInt(IdUF));
//        unidadeFederativa.setNomeUnidadeFederativa(nomeUF);
//        unidadeFederativa.setPais(pais);
//
//        Cidade cidade = new Cidade();
//        cidade.setIdCidade(Integer.parseInt(IdCidade));
//        cidade.setNomeCidade(nomeCidade);
//        cidade.setUnidadeFederativa(unidadeFederativa);
//
//        Bairro bairro = new Bairro();
//        bairro.setIdBairro(Integer.parseInt(IdBairro));
//        bairro.setNomeBairro(nomeBairro);
//
//        TipoLogradouro tipo = new TipoLogradouro();
//        tipo.setIdTipoLogradouro(Integer.parseInt(IdTipoLogradouro));
//        tipo.setNomeTipoLogradouro(nomeTipoLogradouro);
//
//        Logradouro logra = new Logradouro();
//        logra.setIdLogradouro(Integer.parseInt(IdLogradouro));
//        logra.setTipoLogradouro(tipo);
//        logra.setNomeLogradouro(nomeLogradouro);
//
//
//        if(!cep.isEmpty()){
//            EnderecoDAO daoEnd = new EnderecoDAO();
//            daoEnd.alterarEnderecoCEP(cep, connection);
//        }
//
//        if(!nomePais.isEmpty()){
//            PaisDAO daoPais = new PaisDAO();
//            daoPais.alterarPais(pais, connection);
//        }
//
//        if(!nomeUF.isEmpty()){
//            UnidadeFederativaDAO daoUF = new UnidadeFederativaDAO();
//            daoUF.alterarUnidadeFederativa(unidadeFederativa, connection);
//        }
//
//        if(!nomeBairro.isEmpty()){
//            BairroDAO daoBairro = new BairroDAO();
//            daoBairro.alterarBairro(bairro, connection);
//        }
//
//        if(!nomeCidade.isEmpty()){
//            CidadeDAO daoCidade = new CidadeDAO();
//            daoCidade.alterarCidade(cidade, connection);
//        }
//
//        if(!nomeTipoLogradouro.isEmpty()){
//            TipoLogradouroDAO daoTLo =new  TipoLogradouroDAO();
//            daoTLo.alterarTipoLogradouro(tipo, connection);
//        }
//        if(!nomeLogradouro.isEmpty()){
//            LogradouroDAO daoLogra = new LogradouroDAO();
//            daoLogra.alterarLogradouro(logra, connection);
//        }
//
//    }
//}
//
