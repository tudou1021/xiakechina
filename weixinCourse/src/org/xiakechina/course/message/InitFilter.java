package org.xiakechina.course.message;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.xiakechina.course.util.JdbcUtil;

/**
 * Servlet Filter implementation class InitFilter
 */
public class InitFilter implements Filter {

    /**
     * Default constructor. 
     */
    public InitFilter() {
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
	public void doFilter(ServletRequest req, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		JdbcUtil.host = request.getHeader("BAE_ENV_ADDR_SQL_IP");
		JdbcUtil.port =request.getHeader("BAE_ENV_ADDR_SQL_PORT");
		JdbcUtil.username = request.getHeader("BAE_ENV_AK");
		JdbcUtil.password = request.getHeader("BAE_ENV_SK");
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
