package br.sceweb.modelo;

import org.apache.log4j.Logger;

public class EmpresaService {
	private DAOFactory fabricaDAO;
	private IEmpresaDAO empresaDAO;
	Logger logger = Logger.getLogger(EmpresaService.class);
	public EmpresaService(){
		logger.info("classe empresaservice instanciada");
		fabricaDAO = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		empresaDAO = fabricaDAO.getEmpresaDAO();
	}
	
	public int cadastrarEmpresa(Empresa empresa){
		logger.info("empresa service metodo cadastra executado");
		return empresaDAO.adiciona(empresa);
	}
	
	public Empresa consultaEmpresa (String cnpj){
		return empresaDAO.consultaEmpresa(cnpj);
	}
	public int excluirEmpresa(String cnpj){
		return empresaDAO.exclui(cnpj);
	}

}

