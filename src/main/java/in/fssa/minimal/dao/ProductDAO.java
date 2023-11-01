package in.fssa.minimal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.fssa.minimal.dto.ProductRespondDTO;
import in.fssa.minimal.exception.PersistenceException;
import in.fssa.minimal.exception.ServiceException;
import in.fssa.minimal.exception.ValidationException;
import in.fssa.minimal.model.Category;
import in.fssa.minimal.model.Product;
import in.fssa.minimal.model.User;
import in.fssa.minimal.service.CategoryService;
import in.fssa.minimal.service.UserService;
import in.fssa.minimal.util.ConnectionUtil;
import in.fssa.minimal.util.Logger;

public class ProductDAO {
	/**
	 * Retrieves a set of all active product details from the database.
	 *
	 * This method queries the 'products' table to retrieve all product details that
	 * are marked as active ('is_active' = 1) in the database and returns them as a
	 * set of ProductDetailDTO objects.
	 *
	 * @return A set of ProductDetailDTO objects representing all active product
	 *         details in the database.
	 * @throws PersistenceException If a database error occurs or a SQL exception is
	 *                              thrown during the operation.
	 * @throws ServiceException 
	 * @throws ValidationException 
	 */

	public List<ProductRespondDTO> findAll() throws PersistenceException, ValidationException, ServiceException {
		List<ProductRespondDTO> productList = new ArrayList<>();

	    Connection con = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;

	    try {
	        String query = "SELECT id, name, image_url, description, seller_id, price, discount, quantity, category_id, warranty, is_active"
	                + " FROM products";
	        con = ConnectionUtil.getConnection();
	        ps = con.prepareStatement(query);
	        rs = ps.executeQuery();

	        while (rs.next()) {
	            ProductRespondDTO product = new ProductRespondDTO();
	            product.setId(rs.getInt("id"));
	            product.setName(rs.getString("name"));
	            product.setImageUrl(rs.getString("image_url"));
	            product.setDescription(rs.getString("description"));

	        	UserService userService = new UserService();
                User seller = userService.findBySellerId(rs.getInt("seller_id"));
                
                CategoryService categoryService = new CategoryService();
                Category category = categoryService.getAllCategoryById(rs.getInt("category_id"));

	            product.setSellerId(seller);
	            product.setCategoryId(category);

	            product.setPrice(rs.getInt("price"));
	            product.setDiscount(rs.getInt("discount"));
	            product.setQuantity(rs.getInt("quantity"));
	            product.setWarranty(rs.getString("warranty"));
	            product.setActive(rs.getBoolean("is_active"));
	            productList.add(product);
	        }
	    } catch (SQLException e) {
	        Logger.error(e);
	        throw new PersistenceException(e);
	    } finally {
	        ConnectionUtil.close(con, ps, rs);
	    }

	    return productList;
	}
	
	/**
	 * Creates a new product with the provided details and returns its generated
	 * product ID.
	 *
	 * This method inserts a new product into the 'products' table in the database
	 * with the provided details, including name, image URL, description, seller ID,
	 * and type ID. It also retrieves and returns the generated product ID after the
	 * insert operation is successful.
	 *
	 * @param newProduct The ProductDetailDTO object containing the details of the
	 *                   new product.
	 * @return The generated product ID of the newly created product.
	 * @throws PersistenceException If a database error occurs or a SQL exception is
	 *                              thrown during the operation.
	 */

	public void create(Product newProduct) throws PersistenceException {
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			String query = "INSERT INTO products (name, image_url, description, price, discount, quantity,warranty, seller_id, category_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?,?)";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, newProduct.getName());
			ps.setString(2, newProduct.getImageUrl());
			ps.setString(3, newProduct.getDescription());
			ps.setInt(4, newProduct.getPrice());
			ps.setInt(5, newProduct.getDiscount());
			ps.setInt(6, newProduct.getQuantity());
			ps.setString(7, newProduct.getWarranty());
			ps.setInt(8, newProduct.getSellerId());
			ps.setInt(9, newProduct.getCategoryId());
			ps.executeUpdate();

			Logger.info("Product  has been created successfully");

		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistenceException(e);
		} finally {
			ConnectionUtil.close(conn, ps);
		}
	}

	/**
	 * Updates a product's information in the database with the provided details.
	 *
	 * This method updates the information of a product in the 'products' table in
	 * the database with the provided details, including name, image URL,
	 * description, and type ID. The update is based on the product's ID (pdt_id).
	 * Only the non-null and non-negative values in the updatedProduct object are
	 * applied to the database. The 'is_active' column is used to filter active
	 * products.
	 *
	 * @param id             The ID of the product to be updated.
	 * @param updatedProduct The ProductEntity object containing the updated product
	 *                       details.
	 * @throws PersistenceException If a database error occurs or a SQL exception is
	 *                              thrown during the operation.
	 */

	public void update(int id, Product updatedProduct) throws PersistenceException {

		Connection conn = null;
		PreparedStatement ps = null;

		try {

			StringBuilder queryBuilder = new StringBuilder("UPDATE products SET ");
			List<Object> values = new ArrayList<>();

			if (updatedProduct.getName() != null) {
				queryBuilder.append("name = ?, ");
				values.add(updatedProduct.getName());
			}
			if (updatedProduct.getImageUrl() != null) {
				queryBuilder.append("image_url = ?, ");
				values.add(updatedProduct.getImageUrl());
			}
			if (updatedProduct.getDescription() != null) {
				queryBuilder.append("description = ?, ");
				values.add(updatedProduct.getDescription());
			}
			if (updatedProduct.getCategoryId() > 0) {
				queryBuilder.append("category_id = ?, ");
				values.add(updatedProduct.getCategoryId());
			}
			if (updatedProduct.getSellerId() > 0) {
				queryBuilder.append("seller_id = ?, ");
				values.add(updatedProduct.getSellerId());
			}
			if (updatedProduct.getPrice() > 0) {
				queryBuilder.append("price = ?, ");
				values.add(updatedProduct.getPrice());
			}
			if (updatedProduct.getDiscount() > 0) {
				queryBuilder.append("discount = ?, ");
				values.add(updatedProduct.getDiscount());
			}
			if (updatedProduct.getWarranty() != null) {
				queryBuilder.append("warranty = ?, ");
				values.add(updatedProduct.getWarranty());
			}
			if (updatedProduct.getQuantity() > 0) {
				queryBuilder.append("quantity = ?, ");
				values.add(updatedProduct.getQuantity());
			}

			queryBuilder.setLength(queryBuilder.length() - 2);
			queryBuilder.append(" WHERE is_active = 1 AND id = ?");
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(queryBuilder.toString());

			for (int i = 0; i < values.size(); i++) {
				ps.setObject(i + 1, values.get(i));
			}
			ps.setInt(values.size() + 1, id);
			ps.executeUpdate();
			Logger.info("Product has been updated successfully");
		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistenceException(e);
		} finally {
			ConnectionUtil.close(conn, ps);
		}

	}

	/**
	 * Marks a product as inactive in the database.
	 *
	 * This method updates the 'is_active' column of a product in the 'products'
	 * table in the database to mark it as inactive. The product is identified by
	 * its ID (pdt_id).
	 *
	 * @param id The ID of the product to be marked as inactive.
	 * @throws PersistenceException If a database error occurs or a SQL exception is
	 *                              thrown during the operation.
	 */

	public void delete(int productId) throws PersistenceException {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			String query = "UPDATE products SET is_active = 0 WHERE is_active = 1 AND id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, productId);
			ps.executeUpdate();
			Logger.info("Product has been deleted successfully");
		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistenceException(e);
		} finally {
			ConnectionUtil.close(con, ps);
		}

	}
	
	public void updateQuantity(int quantity, int productId) throws PersistenceException {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			String query = "UPDATE products SET quantity = ? WHERE is_active = 1 AND id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, quantity);
			ps.setInt(2, productId);
			ps.executeUpdate();
			Logger.info("Product Quantity has been updated successfully");
		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistenceException(e);
		} finally {
			ConnectionUtil.close(con, ps);
		}

	}

	/**
	 * Finds the category ID by its name in the database.
	 *
	 * This method retrieves the ID of a category from the 'categories' table in the
	 * database based on its name.
	 *
	 * @param name The name of the category to search for.
	 * @return The ID of the category with the specified name, or 0 if not found.
	 * @throws PersistenceException If a database error occurs or a SQL exception is
	 *                              thrown during the operation.
	 * @throws ServiceException 
	 * @throws ValidationException 
	 */

	public ProductRespondDTO findProductById(int productId) throws PersistenceException, ValidationException, ServiceException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ProductRespondDTO product = null;
		try {

			String query = "SELECT id, name, image_url, description, seller_id, price, discount, quantity, warranty,category_id, is_active "
					+ "FROM products WHERE is_active=1 AND id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, productId);
			rs = ps.executeQuery();
			while (rs.next()) {
				    product = new ProductRespondDTO();
		            product.setId(rs.getInt("id"));
		            product.setName(rs.getString("name"));
		            product.setImageUrl(rs.getString("image_url"));
		            product.setDescription(rs.getString("description"));

		        	UserService userService = new UserService();
	                User seller = userService.findBySellerId(rs.getInt("seller_id"));
	                
	                CategoryService categoryService = new CategoryService();
	                Category category = categoryService.getAllCategoryById(rs.getInt("category_id"));

		            product.setSellerId(seller);
		            product.setCategoryId(category);

		            product.setPrice(rs.getInt("price"));
		            product.setDiscount(rs.getInt("discount"));
		            product.setQuantity(rs.getInt("quantity"));
		            product.setWarranty(rs.getString("warranty"));
		            product.setActive(rs.getBoolean("is_active"));
			}

		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistenceException(e);

		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
		return product;
	}

	
	public ProductRespondDTO findByTypeName(String name) throws PersistenceException, ValidationException, ServiceException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ProductRespondDTO product = null;
		
		try {

			String query = "SELECT SELECT id, name, image_url, description, seller_id, price, discount, quantity, warranty,category_id, is_active"
					+ " FROM products WHERE is_active=1 AND name = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, name);
			rs = ps.executeQuery();

			if (rs.next()) {
				    product = new ProductRespondDTO();
		            product.setId(rs.getInt("id"));
		            product.setName(rs.getString("name"));
		            product.setImageUrl(rs.getString("image_url"));
		            product.setDescription(rs.getString("description"));

		        	UserService userService = new UserService();
	                User seller = userService.findBySellerId(rs.getInt("seller_id"));
	                
	                CategoryService categoryService = new CategoryService();
	                Category category = categoryService.getAllCategoryById(rs.getInt("category_id"));

		            product.setSellerId(seller);
		            product.setCategoryId(category);

		            product.setPrice(rs.getInt("price"));
		            product.setDiscount(rs.getInt("discount"));
		            product.setQuantity(rs.getInt("quantity"));
		            product.setWarranty(rs.getString("warranty"));
		            product.setActive(rs.getBoolean("is_active"));
			}

		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistenceException(e);
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}

		return product;
	}

	

	/**
	 * Retrieves a set of detailed product information for all products associated
	 * with a specific seller.
	 *
	 * @param id The unique identifier of the seller whose products are to be
	 *           retrieved.
	 * @return A Set of ProductDetailDTO objects containing detailed product
	 *         information for the seller's products.
	 * @throws PersistenceException If there is an issue with the database
	 *                              connection or query.
	 * @throws ServiceException 
	 * @throws ValidationException 
	 */

	public List<ProductRespondDTO> findAllProductsBySellerId(int sellerId) throws PersistenceException, ValidationException, ServiceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<ProductRespondDTO> pdt = new ArrayList<>();

		try {
			 String query = "SELECT id, name, image_url, description, seller_id, price, discount, quantity,warranty, category_id, is_active"
		                + " FROM products WHERE seller_id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, sellerId);
			rs = ps.executeQuery();

			while (rs.next()) {
				ProductRespondDTO product = new ProductRespondDTO();
				    product.setId(rs.getInt("id"));
		            product.setName(rs.getString("name"));
		            product.setImageUrl(rs.getString("image_url"));
		            product.setDescription(rs.getString("description"));

		        	UserService userService = new UserService();
	                User seller = userService.findBySellerId(rs.getInt("seller_id"));
	                
	                CategoryService categoryService = new CategoryService();
	                Category category = categoryService.getAllCategoryById(rs.getInt("category_id"));

		            product.setSellerId(seller);
		            product.setCategoryId(category);

		            product.setPrice(rs.getInt("price"));
		            product.setDiscount(rs.getInt("discount"));
		            product.setQuantity(rs.getInt("quantity"));
		            product.setWarranty(rs.getString("warranty"));
		            product.setActive(rs.getBoolean("is_active"));

				    pdt.add(product);
			}

		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistenceException(e);
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
		return pdt;
	}
	
	public List<ProductRespondDTO> findAllProductsByCategoryId(int categoryId) throws PersistenceException, ValidationException, ServiceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<ProductRespondDTO> pdt = new ArrayList<>();

		try {
			 String query = "SELECT id, name, image_url, description, seller_id, price, discount, quantity,warranty, category_id, is_active"
		                + " FROM products WHERE is_active=1 and seller_id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, categoryId);
			rs = ps.executeQuery();

			while (rs.next()) {
				    ProductRespondDTO product = new ProductRespondDTO();
				    product.setId(rs.getInt("id"));
		            product.setName(rs.getString("name"));
		            product.setImageUrl(rs.getString("image_url"));
		            product.setDescription(rs.getString("description"));

		        	UserService userService = new UserService();
	                User seller = userService.findBySellerId(rs.getInt("seller_id"));
	                
	                CategoryService categoryService = new CategoryService();
	                Category category = categoryService.getAllCategoryById(rs.getInt("category_id"));

		            product.setSellerId(seller);
		            product.setCategoryId(category);

		            product.setPrice(rs.getInt("price"));
		            product.setDiscount(rs.getInt("discount"));
		            product.setQuantity(rs.getInt("quantity"));
		            product.setWarranty(rs.getString("warranty"));
		            product.setActive(rs.getBoolean("is_active"));

				    pdt.add(product);
			}

		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistenceException(e);
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
		return pdt;
	}

	/**
	 * Checks the existence of a product with the specified product ID in the
	 * database.
	 *
	 * @param id The product ID to be checked for existence.
	 * @throws PersistenceException If there is an issue with the database
	 *                              connection or query.
	 * @throws ValidationException  If the product with the given ID does not exist
	 *                               in the database.
	 */

	public static void checkProductExist(int productId) throws PersistenceException, ValidationException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			String query = "SELECT id FROM products WHERE id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, productId);
			rs = ps.executeQuery();
			if (!rs.next()) {
				throw new ValidationException("Product doesn't exist");
			}
		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistenceException(e);

		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
	}

	public static void checkProductNameExist(String productName) throws PersistenceException, ValidationException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			String query = "SELECT id FROM products WHERE is_active = 1 AND name = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, productName);
			rs = ps.executeQuery();
			if (rs.next()) {
				throw new ValidationException("Product already exist");
			}
		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistenceException(e);

		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
	}

	public static void reactivateProduct(int productId) throws PersistenceException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			String query = "UPDATE products SET is_active = 1 WHERE is_active = 0 AND id = ?";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, productId);
			ps.executeUpdate();
			Logger.info("Product has been updated successfully");
		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistenceException(e);
		} finally {
			ConnectionUtil.close(conn, ps);
		}
	}

}
