package com.att.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.att.dao.ProductDao;
import com.att.model.Product;
import com.att.util.DbConn;

public class Controller extends HttpServlet {
	
	final static Logger logger = Logger.getLogger(Controller.class);
	
    private static final long serialVersionUID = 1L;
    private static String INSERT_OR_EDIT = "/product.jsp";
    private static String LIST_PRODUCT = "/listProduct.jsp";
    private static String ERROR_PAGE = "/error.html";
    private ProductDao dao;
    
    public Controller() {
        super();
        dao = new ProductDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward="";
        String action = request.getParameter("action");
        logger.info("action in doGet= " +action);
        if (action.equalsIgnoreCase("delete")){
            int productId = Integer.parseInt(request.getParameter("productId"));
            dao.deleteProduct(productId);
            forward = LIST_PRODUCT;
            request.setAttribute("products", dao.getAllProducts());    
        } else if (action.equalsIgnoreCase("edit")){
            forward = INSERT_OR_EDIT;
            int productId = Integer.parseInt(request.getParameter("productId"));
            Product product = dao.getProductById(productId);
            request.setAttribute("product", product);
        } else if (action.equalsIgnoreCase("listProduct")){
            forward = LIST_PRODUCT;
            request.setAttribute("products", dao.getAllProducts());
        } else {
            forward = INSERT_OR_EDIT;
        }

        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String forward="";
    	Product product = new Product();
        String uname=request.getParameter("username");
        String pwd=request.getParameter("password");
        String action = request.getParameter("action");
        logger.info("action="+request.getParameter("action"));
        if (action.equalsIgnoreCase("login")){
       /* if (action == "login")
        {*/
        String authenticate = authenticate(uname, pwd);	  
        logger.info("authenticate="+authenticate);
        if (authenticate == "success")
        {
        product.setProductname(request.getParameter("productname"));
        product.setProductdesc(request.getParameter("productdesc"));
        product.setRelatedproducts(request.getParameter("relatedproducts"));
        String productid = request.getParameter("productid");
        logger.info("productname="+request.getParameter("productname"));
        if(productid == null || productid.isEmpty())
        {
            dao.addProduct(product);
        }
        else
        {
            product.setProductid(Integer.parseInt(productid));
            dao.updateProduct(product);
        }
        RequestDispatcher view = request.getRequestDispatcher(LIST_PRODUCT);
        request.setAttribute("products", dao.getAllProducts());
        view.forward(request, response);
        }
        else
        	forward = ERROR_PAGE;
        }else if (action.equalsIgnoreCase("product")){
        	
            product.setProductname(request.getParameter("productname"));
            product.setProductdesc(request.getParameter("productdesc"));
            product.setRelatedproducts(request.getParameter("relatedproducts"));
            String productid = request.getParameter("productid");
            logger.info("productname="+request.getParameter("productname"));
            if(productid == null || productid.isEmpty())
            {
                dao.addProduct(product);
            }
            else
            {
                product.setProductid(Integer.parseInt(productid));
                dao.updateProduct(product);
            }
            RequestDispatcher view = request.getRequestDispatcher(LIST_PRODUCT);
            request.setAttribute("products", dao.getAllProducts());
            view.forward(request, response);
        }
    }
    
    public String authenticate(String uname, String pwd){
    	String auth_str = null;
        try {
        	Properties prop = new Properties();
            InputStream inputStream = Controller.class.getClassLoader().getResourceAsStream("/opus.properties");
            prop.load(inputStream);
            String username = prop.getProperty("login_uname");
            String password = prop.getProperty("login_pwd");
            logger.info("uname "+uname);
            logger.info("pwd "+pwd);
            logger.info("username "+username);
            logger.info("password "+password);
            if (username.equalsIgnoreCase(uname)  && password.equalsIgnoreCase(pwd) ){
            	auth_str = "success";            	
            }
            else
            	auth_str = "failure";
        } catch (IOException e) {
            e.printStackTrace();
        }
    	
    	return auth_str;
    }
}