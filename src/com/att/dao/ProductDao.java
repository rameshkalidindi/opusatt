package com.att.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.att.model.Product;
import com.att.util.DbConn;

public class ProductDao {

	private Connection connection;

	public ProductDao() {
		connection = DbConn.getConnection();
	}

/*	public void addUser1(User user) {
	Map<String, Object[]> data = new TreeMap<String, Object[]>();
	data.put("1", new Object[] {"ID", "NAME", "LASTNAME"});
	data.put(user.getUserid(), new Object[] {1, product.getProductname(), product.getProductdesc(),product.getRelatedproducts()});
	}*/
	public void addProduct(Product product) {
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("insert into products(productname,productdesc,relatedproducts) values (?, ?, ? )");
			//preparedStatement.setInt(1, product.getProductid());
			preparedStatement.setString(1, product.getProductname());
			preparedStatement.setString(2, product.getProductdesc());
			preparedStatement.setString(3, product.getRelatedproducts());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteProduct(int productId) {
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("delete from products where productid=?");
			// Parameters start with 1
			preparedStatement.setInt(1, productId);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateProduct(Product product) {
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("update products set productname=?, productdesc=?, relatedproducts=?" +
							"where productid=?");
			// Parameters start with 1
			preparedStatement.setString(1, product.getProductname());
			preparedStatement.setString(2, product.getProductdesc());
			preparedStatement.setString(3, product.getRelatedproducts());
			preparedStatement.setInt(4, product.getProductid());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Product> getAllProducts() {
		List<Product> products = new ArrayList<Product>();
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select * from products");
			while (rs.next()) {
				Product product = new Product();
				product.setProductid(rs.getInt("productid"));
				product.setProductname(rs.getString("productname"));
				product.setProductdesc(rs.getString("productdesc"));
				product.setRelatedproducts(rs.getString("relatedproducts"));
				products.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return products;
	}
	
	public Product getProductById(int productId) {
		Product product = new Product();
		try {
			PreparedStatement preparedStatement = connection.
					prepareStatement("select * from products where productid=?");
			preparedStatement.setInt(1, productId);
			ResultSet rs = preparedStatement.executeQuery();
			
			if (rs.next()) {
				product.setProductid(rs.getInt("productid"));
				product.setProductname(rs.getString("productname"));
				product.setProductdesc(rs.getString("productdesc"));
				product.setRelatedproducts(rs.getString("relatedproducts"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return product;
	}
}
