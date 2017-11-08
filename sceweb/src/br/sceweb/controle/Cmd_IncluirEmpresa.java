package br.sceweb.controle;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import br.sceweb.modelo.Empresa;
import br.sceweb.modelo.EmpresaService;

public class Cmd_IncluirEmpresa implements ICommand{
	Logger logger = Logger.getLogger("Cmd_IncluirEmpresa");
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String msg = null;
		logger.info("chamou o comando incluir empresa");
		
		Empresa empresa = new Empresa();
		EmpresaService empresaService = new EmpresaService();
		try {
			empresa.setCnpj(request.getParameter("txtCNPJ"));
			empresa.setNomeDaEmpresa(request.getParameter("txtNomeDaEmpresa"));
			empresa.setNomeFantasia(request.getParameter("txtNomeFantasia"));
			empresa.setEndereco(request.getParameter("txtEndereco"));
			empresa.setTelefone(request.getParameter("txtTelefone"));
			empresa.setResponsavel(request.getParameter("txtResponsavel"));
			empresa.setTelefoneResponsavel(request.getParameter("txtTelefoneResponsavel"));
			empresa.setSetor(request.getParameter("txtSetor"));
			empresa.setEmail(request.getParameter("txtEmail"));
			empresaService.cadastrarEmpresa(empresa);
			msg = "cadastro realizado com sucesso";
			request.setAttribute("msg", msg);
			RequestDispatcher view = request.getRequestDispatcher("FormEmpresa.jsp");
	        view.forward(request, response); 
		} catch (Exception e) {
			msg = e.getMessage();
			logger.info("erro  = " + e.getMessage());
	        request.setAttribute("msg", msg);
	        RequestDispatcher view = request.getRequestDispatcher("FormEmpresa.jsp");;
	        view.forward(request, response); 
			
		}
		
	}
}
