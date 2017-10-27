package br.sceweb.testeIntegracao;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import br.sceweb.data_builder.ObtemEmpresa;
import br.sceweb.modelo.DAOFactory;
import br.sceweb.modelo.Empresa;
import br.sceweb.modelo.IEmpresaDAO;

public class UC01CadastrarEmpresa {

	static IEmpresaDAO empresaDAO;
	static Empresa empresa;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		empresaDAO = DAOFactory.getDAOFactory(DAOFactory.MYSQL).getEmpresaDAO();
		empresa = ObtemEmpresa.comDadosValidos();
		empresaDAO.adiciona(empresa);

	}

	@After
	public void tearDownAfter() throws Exception {
		empresaDAO.exclui("89424232000180");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * ID - CT01UC01FBCadastra_com_sucesso Objetivo - verificar o comportamento
	 * do sistema na inclusao de empresa com sucesso Pré-condiçao - O cnpj
	 * 89424232000180 nao esta cadastrado.
	 */
	@Test
	public void CT01UC01FBCadastra_com_sucesso() {
		assertEquals(1, empresaDAO.adiciona(empresa));
	}
	@Test(expected = IllegalArgumentException.class)
	public void CT02UC01FBCadastra_cnpj_invalido() {
		empresa.setCnpj("89424");
	}
	@Test
	public void CT03UC01FBCadastra_cnpj_ja_cadastrado() {
		empresaDAO.adiciona(empresa);
		assertEquals(0, empresaDAO.adiciona(empresa));

	}

}