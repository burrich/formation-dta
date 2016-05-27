package fr.pizzeria.admin.web;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class AuthentificationFilter
 */
@WebFilter(urlPatterns={"/pizzas/*"})
public class AuthentificationFilter implements Filter {
	private static final Logger LOG = Logger.getLogger(AuthentificationFilter.class.toString());

    /**
     * Default constructor. 
     */
    public AuthentificationFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		Boolean isConnected = (Boolean) httpRequest.getSession().getAttribute("isConnected");
		String currentPath = httpRequest.getRequestURI();
		String loginPath = httpRequest.getContextPath() + "/login";
		
		//TODO: remove login page test
		if (isConnected != null || currentPath.equals(loginPath)) {
			chain.doFilter(request, response);
		} else {
			((HttpServletResponse) response).sendRedirect(loginPath);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
