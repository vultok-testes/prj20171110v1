package br.sceweb.controle;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import br.sceweb.modelo.Empresa;
import br.sceweb.modelo.EmpresaService;

public class Cmd_ExcluirEmpresa implements ICommand {
	Logger logger = Logger.getLogger("Cmd_ExcluirEmpresa");

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String msg = null;
		int rc = 0; // obtem o codigo de retorno da execucao sql
		logger.info("chamou o comando excluir empresa");

		EmpresaService empresaService = new EmpresaService();
		try {

			rc = empresaService.excluirEmpresa(request.getParameter("txtCNPJ"));
			if (rc == 1) {
				msg = "exclusao realizada com sucesso";
			}else{
				msg = "dados invalidos";
			}
			request.setAttribute("msg", msg);
			RequestDispatcher view = request.getRequestDispatcher("FormEmpresa.jsp");
			view.forward(request, response);
		} catch (Exception e) {
			msg = e.getMessage();
			request.setAttribute("msg", msg);
			RequestDispatcher view = request.getRequestDispatcher("FormEmpresa.jsp");
			;
			view.forward(request, response);
			logger.info("erro  = " + e.getMessage());
		}

	}
}
