package unioeste.geral.util;

import unioeste.geral.bo.endereco.Endereco;
import unioeste.geral.bo.endereco.EnderecoEspecifico;
import unioeste.geral.exception.EnderecoException;

public class EnderecoValidador {
    private static final String USUARIO_BD = "root";
    private static final String SENHA_BD   = "";
    private static final String URL_BD     = "jdbc:mysql://localhost/endereco";

    // TODO: função pra que recebe objeto Endereco pra comparar primeiro dígito do CEP com nomeEstado ou siglaEstado

	private boolean validarCEP(String cep) throws EnderecoException {
        if (cep.length() != 9) {
            throw new EnderecoException("Tamanho do CEP inválido");
        } else if (!cep.matches("[0-9]{5}\\-[0-9]{3}")) {
            throw new EnderecoException("Formato do CEP inválido");
        } else if (cep.equals("00000-000") ||
                cep.equals("11111-111") ||
                cep.equals("22222-222") ||
                cep.equals("33333-333") ||
                cep.equals("44444-444") ||
                cep.equals("55555-555") ||
                cep.equals("66666-666") ||
                cep.equals("77777-777") ||
                cep.equals("88888-888") ||
                cep.equals("99999-999")) {
            throw new EnderecoException("CEP inválido (dígitos repetidos)");
        }

//      else {
//            Connection connection = new unioeste.apoio.bd.MySQLConnection().getConnection(URL_BD, USUARIO_BD, SENHA_BD);
//            ResultSet rs = new EnderecoDAOImpl().buscarTodosEnderecos(connection);
//            try {
//                while (rs.next()) {
//                    if (rs.getString("cep").equals(cep)) {
//                        throw new EnderecoException("CEP já existente");
//                    }
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }

		return true;
	}

    private boolean validarNome(String nome) throws EnderecoException {
        if (!nome.matches("[\\p{L}\\s]+")) { // \p{L}: expressão regular para qualquer letra Unicode (Latin-1 Supplemen)
            throw new EnderecoException("Nome não válido");
        }
        return true;
    }

    private boolean validarSigla(String sigla) throws EnderecoException {
        if (!sigla.matches("[a-zA-Z]{2,3}")) {
            throw new EnderecoException("Sigla não válida");
        }
        return true;
    }

    public boolean validarEndereco(EnderecoEspecifico enderecoEspecifico) throws EnderecoException {
        EnderecoValidador enderecoValidador = new EnderecoValidador();

        Endereco endereco = enderecoEspecifico.getEndereco();

        enderecoValidador.validarNome(endereco.getLogradouro().getTipoLogradouro().getTipo());
        enderecoValidador.validarNome(endereco.getLogradouro().getNome());
        enderecoValidador.validarNome(endereco.getBairro().getNome());
        enderecoValidador.validarNome(endereco.getCidade().getNome());
        enderecoValidador.validarNome(endereco.getCidade().getUnidadeFederativa().getNome());
        enderecoValidador.validarNome(endereco.getCidade().getUnidadeFederativa().getPais().getNome());
        enderecoValidador.validarSigla(endereco.getCidade().getUnidadeFederativa().getSigla());
        enderecoValidador.validarSigla(endereco.getCidade().getUnidadeFederativa().getPais().getSigla());
        enderecoValidador.validarCEP(endereco.getCEP());
        enderecoValidador.validarEstadoCEP(endereco);
        if (enderecoEspecifico.getNumero() <= 0 ) enderecoEspecifico.setNumero(0);
        if ((endereco.getBairro().getNome().length() > 0) &&
                (endereco.getCEP().length() > 0) &&
                (endereco.getBairro().getNome().length() > 0) &&
                (endereco.getCidade().getNome().length() > 0) &&
                (endereco.getCidade().getUnidadeFederativa().getNome().length() > 0) &&
                (endereco.getCidade().getUnidadeFederativa().getPais().getNome().length() > 0) &&
                (endereco.getLogradouro().getNome().length() > 0) &&
                (endereco.getLogradouro().getTipoLogradouro().getTipo().length() > 0)) {
            return true;
        }
        return false;
    }

//    Região 0 - Grande São Paulo;
//    Região 1 - Interior de São Paulo;
//    Região 2 - Rio de Janeiro e Espírito Santo;
//    Região 3 - Minas Gerais;
//    Região 4 - Bahia e Sergipe;
//    Região 5 - Pernambuco, Alagoas, Paraíba e Rio Grande do Norte;
//    Região 6 - Ceará, Piauí, Maranhão, Pará, Amazonas, Acre, Amapá e Roraima;
//    Região 7 - Goiás, Tocantins, Mato Grosso, Mato Grosso do Sul, Rondônia e DF;
//    Região 8 - Paraná e Santa Catarina;
//    Região 9 - Rio Grande do Sul.
    private boolean validarEstadoCEP(Endereco endereco) throws EnderecoException {
        char primeiroDigitoCEP = endereco.getCEP().charAt(0);
        String siglaUF = endereco.getCidade().getUnidadeFederativa().getSigla().toUpperCase();
        boolean existeExcecao = false;

        if ((primeiroDigitoCEP == '0' || primeiroDigitoCEP == '1')
            && !siglaUF.equals("SP")) {
            existeExcecao = true;
        } else if (primeiroDigitoCEP == '2'
            && !(siglaUF.equals("RJ") || siglaUF.equals("ES"))) {
            existeExcecao = true;
        } else if (primeiroDigitoCEP == '3'
            && !siglaUF.equals("MG")) {
            existeExcecao = true;
        } else if (primeiroDigitoCEP == '4'
            && !(siglaUF.equals("BA") || siglaUF.equals("SE"))) {
            existeExcecao = true;
        } else if (primeiroDigitoCEP == '5'
			&& !(siglaUF.equals("PE")
			|| siglaUF.equals("AL")
			|| siglaUF.equals("PB")
			|| siglaUF.equals("RN"))) {
            existeExcecao = true;
        } else if (primeiroDigitoCEP == '6'
			&& !(siglaUF.equals("CE")
			|| siglaUF.equals("PI")
			|| siglaUF.equals("MA")
			|| siglaUF.equals("PA")
			|| siglaUF.equals("AM")
			|| siglaUF.equals("AC")
			|| siglaUF.equals("AP")
			|| siglaUF.equals("RR"))) {
            existeExcecao = true;
        } else if (primeiroDigitoCEP == '7'
			&& !(siglaUF.equals("GO")
			|| siglaUF.equals("TO")
			|| siglaUF.equals("MT")
			|| siglaUF.equals("MS")
			|| siglaUF.equals("RO")
			|| siglaUF.equals("DF"))) {
            existeExcecao = true;
        } else if (primeiroDigitoCEP == '8'
			&& !(siglaUF.equals("PR") || siglaUF.equals("SC"))) {
            existeExcecao = true;
        } else if (primeiroDigitoCEP == '9'
			&& (!siglaUF.equals("RS"))) {
            existeExcecao = true;
        }

        if (existeExcecao) {
            throw new EnderecoException("Primeiro dígito do CEP e estado não batem");
        }

        return true;
    }

    // TODO: pode servir para o projeto de teste
//    x
//        Pais pais = new Pais("nomepais", "np");
//        new PaisControle().salvarPais(pais);
//
//        UnidadeFederativa unidadeFederativa = new UnidadeFederativa("nomeuf", "rs", pais);
//        new UnidadeFederativaControle().salvarUnidadeFederativa(unidadeFederativa);
//
//        Cidade cidade = new Cidade("nomecidade", unidadeFederativa);
//        new CidadeControle().salvarCidade(cidade);
//
//        Bairro bairro = new Bairro("nomebairro");
//        new BairroControle().salvarBairro(bairro);
//
//        TipoLogradouro tipoLogradouro = new TipoLogradouro("nometipologradouro");
//        new TipoLogradouroControle().salvarTipoLogradouro(tipoLogradouro);
//
//        Logradouro logradouro = new Logradouro("nomelogradouro", tipoLogradouro);
//        new LogradouroControle().salvarLogradouro(logradouro);
//
//        Endereco endereco = new Endereco("98484-111", logradouro, bairro, cidade);
//
//        try {
//            boolean a = new EnderecoValidador().validarEstadoCEP(endereco);
//            System.out.println(a);
//        } catch (EnderecoException e) {
//            e.printStackTrace();
//        }
//    }

}
