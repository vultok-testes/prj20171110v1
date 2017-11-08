package br.sceweb.controle;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * Servlet implementation class DispatchServlet
 * Gerencia a logica de aplicacao
 */
public class ServletControle extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final WebsiteTitle myapp = new WebsiteTitle();
	//Declare one map to hold all concrete action objects.
	private Map<String,ICommand> cmds = new HashMap<String,ICommand>();
	Logger logger = Logger.getLogger("ServletControle");
    /**
     * Default constructor. 
     */
    public ServletControle() {
       initCommands();
    }
    /**
     * instancia os comandos
     */
    private void initCommands() {
    	cmds.put("/home", new Home());
    	cmds.put("/FormEmpresa", new Cmd_ManterEmpresa());
    	cmds.put("IncluirEmpresa", new Cmd_IncluirEmpresa());
    	cmds.put("ExcluirEmpresa", new Cmd_ExcluirEmpresa());
   	}


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getRequestURI().substring(request.getContextPath().length());
		String parametro = request.getParameter("acao");
		ICommand acao = null;
		logger.info(path);
		logger.info(parametro);
		       
        request.setAttribute("myapp", myapp);
        try {
        	if (parametro != null) {
        		acao = cmds.get(parametro);
        		logger.info("achou o comando " + parametro);
        	}else{
        		logger.info("achou o " + path);
        		acao = cmds.get(path);
        	}
			acao.execute(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
