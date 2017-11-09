package br.sceweb.modelo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import br.sceweb.servico.FabricaDeConexoes;

public class MySQLEmpresaDAO implements IEmpresaDAO {
	Logger logger = Logger.getLogger(MySQLEmpresaDAO.class);

	public int adiciona(Empresa empresa) {
		PreparedStatement ps;
		int codigoRetorno = 0;
		try (Connection conn = new FabricaDeConexoes().getConnection()) {
			ps = (PreparedStatement) conn.prepareStatement(
					"insert into empresa (cnpj, nomeDaEmpresa, nomeFantasia, endereco, telefone, responsavel, telefone_resp, setor, email) values(?,?,?,?,?,?,?,?,?)");
			ps.setString(1, empresa.getCnpj());
			ps.setString(2, empresa.getNomeDaEmpresa());
			ps.setString(3, empresa.getNomeFantasia());
			ps.setString(4, empresa.getEndereco());
			ps.setString(5, empresa.getTelefone());
			ps.setString(6, empresa.getResponsavel());
			ps.setString(7, empresa.getTelefoneResponsavel());
			ps.setString(8, empresa.getSetor());
			ps.setString(9, empresa.getEmail());
			codigoRetorno = ps.executeUpdate();
			ps.close();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			logger.info("erro metodo adiciona empresa = " + e.getMessage());

		}
		return codigoRetorno;

	}

	public int exclui(String cnpj) {
		java.sql.PreparedStatement ps;
		int codigoretorno = 0;
		try (Connection conn = new FabricaDeConexoes().getConnection()) {
			ps = conn.prepareStatement("delete from empresa where cnpj = ?");
			ps.setString(1, cnpj);
			codigoretorno = ps.executeUpdate();
			logger.info("codigo de retorno do metodo exclui empresa = " + codigoretorno);
			ps.close();
		} catch (SQLException e) {
			logger.info("erro metodo exclui empresa = " + e.getMessage());
		}
		return codigoretorno;

	}

	public Empresa consultaEmpresa(String cnpj) {
		Empresa empresa = null;
		java.sql.PreparedStatement ps;
		try (Connection conn = new FabricaDeConexoes().getConnection()) {
			ps = conn.prepareStatement("select * from empresa where cnpj = ?");
			ps.setString(1, cnpj);
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				empresa = new Empresa();
				empresa.setCnpj(resultSet.getString("cnpj"));
				empresa.setNomeDaEmpresa(resultSet.getString("nomeDaEmpresa"));
				empresa.setNomeFantasia(resultSet.getString("nomeFantasia"));
				empresa.setEndereco(resultSet.getString("endereco"));
				empresa.setTelefone(resultSet.getString("telefone"));
				empresa.setResponsavel(resultSet.getString("responsavel"));
				empresa.setTelefoneResponsavel(resultSet.getString("telefone_resp"));
				empresa.setSetor(resultSet.getString("setor"));
				empresa.setEmail(resultSet.getString("email"));
			}
			resultSet.close();
			ps.close();
		} catch (SQLException e) {
			logger.info("erro metodo consulta empresa = " + e.getMessage());
		} catch (Exception e) {
			logger.info("erro metodo consulta empresa = " + e.getMessage());
		}
		return empresa;
	}
}
