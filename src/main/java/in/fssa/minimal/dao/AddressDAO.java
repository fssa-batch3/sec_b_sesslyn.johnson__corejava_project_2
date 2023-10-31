package in.fssa.minimal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import in.fssa.minimal.exception.PersistenceException;
import in.fssa.minimal.exception.ValidationException;
import in.fssa.minimal.model.Address;
import in.fssa.minimal.util.ConnectionUtil;
import in.fssa.minimal.util.Logger;

public class AddressDAO {
	public void create(Address newAddress) throws PersistenceException {
	    Connection conn = null;
	    PreparedStatement ps = null;
	    try {
	        String query = "INSERT INTO address (name, email, phone_number, address, city, state, country, pincode, title, user_id, is_default) " +
	                "VALUES (?,?,?,?,?,?,?,?,?,?,?)";
	        conn = ConnectionUtil.getConnection();
	        ps = conn.prepareStatement(query); 
	        ps.setString(1, newAddress.getName());
	        ps.setString(2, newAddress.getEmail()); 
	        ps.setLong(3, newAddress.getPhoneNumber());
	        ps.setString(4, newAddress.getAddress());
	        ps.setString(5, newAddress.getCity());
	        ps.setString(6, newAddress.getState());
	        ps.setString(7, newAddress.getCountry());
	        ps.setInt(8, newAddress.getPincode());
	        ps.setString(9, newAddress.getTitle()); 
	        ps.setInt(10, newAddress.getUserId());

	        boolean isDefault = determineIsDefault(newAddress.getUserId());
	        ps.setBoolean(11, isDefault);

	        ps.executeUpdate();
	        Logger.info("Address has been created successfully");
	    } catch (SQLException e) {
	        Logger.error(e);
	        throw new PersistenceException(e);
	    } finally {
	        ConnectionUtil.close(conn, ps);
	    }
	}

	private boolean determineIsDefault(int userId) {
	    Connection conn = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    boolean isDefault = true;

	    try {
	        String query = "SELECT COUNT(*) FROM address WHERE user_id = ? AND is_active = 1";
	        conn = ConnectionUtil.getConnection();
	        ps = conn.prepareStatement(query);
	        ps.setInt(1, userId);
	        rs = ps.executeQuery();

	        if (rs.next() && rs.getInt("COUNT(*)") > 0) {
	            isDefault = false;
	        }
	    } catch (SQLException e) {
	        Logger.error(e);
	    } finally {
	        ConnectionUtil.close(conn, ps, rs);
	    }

	    return isDefault;
	}

	public void update(int addressId, Address updatedAddress) throws PersistenceException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			StringBuilder queryBuilder = new StringBuilder("UPDATE address SET ");
			List<Object> values = new ArrayList<>();
			if (updatedAddress.getName() != null) {
				queryBuilder.append("name = ?, ");
				values.add(updatedAddress.getName());
			}
			if (updatedAddress.getEmail() != null) {
				queryBuilder.append("email = ?, ");
				values.add(updatedAddress.getEmail());
			}
			if (updatedAddress.getPhoneNumber() != 0) {
				queryBuilder.append("phone_number = ?, ");
				values.add(updatedAddress.getPhoneNumber());
			} 
			if (updatedAddress.getAddress() != null) {
				queryBuilder.append("address = ?, ");
				values.add(updatedAddress.getAddress());
			}
			if (updatedAddress.getCity() != null) {
				queryBuilder.append("city = ?, ");
				values.add(updatedAddress.getCity());
			}
			if (updatedAddress.getState() != null) {
				queryBuilder.append("state = ?, ");
				values.add(updatedAddress.getState());
			}
			if (updatedAddress.getCountry() != null) {
				queryBuilder.append("country = ?, ");
				values.add(updatedAddress.getCountry());
			}
			if (updatedAddress.getPincode() > 0) {
				queryBuilder.append("pincode = ?, ");
				values.add(updatedAddress.getPincode());
			}
			if (updatedAddress.getTitle() != null) {
				queryBuilder.append("title = ?, ");
				values.add(updatedAddress.getTitle());
			}
			if (updatedAddress.getUserId() > 0) {
				queryBuilder.append("user_id = ?, ");
				values.add(updatedAddress.getUserId());
			}

			queryBuilder.setLength(queryBuilder.length() - 2);
			queryBuilder.append(" WHERE id = ?");
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(queryBuilder.toString());
			for (int i = 0; i < values.size(); i++) {
				ps.setObject(i + 1, values.get(i));
			}
			ps.setInt(values.size() + 1, addressId);
			ps.executeUpdate();
			Logger.info("Address has been updated successfully");
		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistenceException(e);
		} finally {
			ConnectionUtil.close(conn, ps);
		}
	}

	public void delete(int addressId) throws PersistenceException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			String query = "UPDATE address SET is_active = 0 WHERE id = ? AND is_active = 1";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, addressId);
			ps.executeUpdate();
			Logger.info("Address has been deleted successfully");
		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistenceException(e);
		} finally {
			ConnectionUtil.close(conn, ps);
		}
	}

	public Set<Address> findAllAddress() throws PersistenceException, ValidationException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Set<Address> addressList = new HashSet<>();
		try {
			String query = "SELECT id, name, email, phone_number, address, city, state, country, pincode, title, user_id,is_default, is_active FROM address WHERE is_active = 1";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				Address address = new Address();
				address.setId(rs.getInt("id"));
				address.setName(rs.getString("name"));
				address.setEmail(rs.getString("email"));
				address.setPhoneNumber(rs.getLong("phone_number"));
				address.setAddress(rs.getString("address"));
				address.setCity(rs.getString("city"));
				address.setState(rs.getString("state"));
				address.setCountry(rs.getString("country"));
				address.setPincode(rs.getInt("pincode"));
				address.setTitle(rs.getString("title"));
				address.setUserId(rs.getInt("user_id"));
				address.setDefault(rs.getBoolean("is_default"));
			    address.setActive(rs.getBoolean("is_active"));
				addressList.add(address);
			}
		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistenceException(e);
		} finally {
			ConnectionUtil.close(conn, ps, rs);
		}
		return addressList;
	}
	
	public Set<Address> findAllAddressByUserId(int userId) throws PersistenceException, ValidationException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Set<Address> addressList = new HashSet<>();
		try {
			String query = "SELECT id, name, email, phone_number, address, city, state, country, pincode, title, user_id,is_default, is_active FROM address WHERE user_id = ? AND is_active = 1";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, userId);
			rs = ps.executeQuery();
			while (rs.next()) {
				Address address = new Address();
				address.setId(rs.getInt("id"));
				address.setName(rs.getString("name"));
				address.setEmail(rs.getString("email"));
				address.setPhoneNumber(rs.getLong("phone_number"));
				address.setAddress(rs.getString("address"));
				address.setCity(rs.getString("city"));
				address.setState(rs.getString("state"));
				address.setCountry(rs.getString("country"));
				address.setPincode(rs.getInt("pincode"));
				address.setTitle(rs.getString("title"));
				address.setUserId(rs.getInt("user_id"));
				address.setDefault(rs.getBoolean("is_default"));
			    address.setActive(rs.getBoolean("is_active"));
				addressList.add(address);
			}
		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistenceException(e);
		} finally {
			ConnectionUtil.close(conn, ps, rs);
		}
		return addressList;
	}
	
	public Address findAddressById(int id) throws PersistenceException, ValidationException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Address address = null;
		try {
			String query = "SELECT id, name, email, phone_number, address, city, state, country, pincode, title, user_id,is_default, is_active FROM address WHERE id = ? AND is_active = 1";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				address = new Address();
				address.setId(rs.getInt("id"));
				address.setName(rs.getString("name"));
				address.setEmail(rs.getString("email"));
				address.setPhoneNumber(rs.getLong("phone_number"));
				address.setAddress(rs.getString("address"));
				address.setCity(rs.getString("city"));
				address.setState(rs.getString("state"));
				address.setCountry(rs.getString("country"));
				address.setPincode(rs.getInt("pincode"));
				address.setTitle(rs.getString("title"));
				address.setUserId(rs.getInt("user_id"));
				address.setDefault(rs.getBoolean("is_default"));
			    address.setActive(rs.getBoolean("is_active"));
			}
		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistenceException(e);
		} finally {
			ConnectionUtil.close(conn, ps, rs);
		}
		return address;
	}

	public static void checkAddressIdExists(int addressId) throws ValidationException, PersistenceException {
		Connection conn = null;
		PreparedStatement pre = null;
		ResultSet rs = null; 

		try {
			String query = "SELECT id FROM address WHERE is_active = 1 AND id = ?";
			conn = ConnectionUtil.getConnection();
			pre = conn.prepareStatement(query);
			pre.setInt(1, addressId);
			rs = pre.executeQuery();
			if (!rs.next()) {
				throw new ValidationException("Address Id doesn't exist");
			}
		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistenceException(e);
		} finally {
			ConnectionUtil.close(conn, pre, rs);
		}
	}
	
	public static void reactivateAddress(int addressId) throws PersistenceException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			String query = "UPDATE address SET is_active = 1 WHERE is_active = 0 AND id = ?";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, addressId);
			ps.executeUpdate();
			Logger.info("Address has been updated successfully");
		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistenceException(e);
		} finally {
			ConnectionUtil.close(conn, ps);
		}
	}

	public void updateDefaultStatus(int userId, int addressId) throws PersistenceException {
	    Connection conn = null;
	    PreparedStatement ps1 = null;
	    PreparedStatement ps2 = null;
	    try {
	        conn = ConnectionUtil.getConnection(); 
	        conn.setAutoCommit(false); 

	        String updateOldDefaultQuery = "UPDATE address SET is_default = 0 WHERE user_id = ? AND is_active = 1 AND is_default = 1";
	        ps1 = conn.prepareStatement(updateOldDefaultQuery);
	        ps1.setInt(1, userId);
	        ps1.executeUpdate();

	        String updateNewDefaultQuery = "UPDATE address SET is_default = 1 WHERE is_active = 1 AND id = ? AND user_id = ?";
	        ps2 = conn.prepareStatement(updateNewDefaultQuery);
	        ps2.setInt(1, addressId);
	        ps2.setInt(2, userId);
	        ps2.executeUpdate();

	        conn.commit(); 

	        Logger.info("Address has been updated successfully");
	    } catch (SQLException e) {
	        try {
	            if (conn != null) {
	                conn.rollback(); 
	            }
	        } catch (SQLException rollbackEx) {
	            Logger.error(rollbackEx);
	        }
	        Logger.error(e);
	        throw new PersistenceException(e);
	    } finally {
	        try {
	            if (conn != null) {
	                conn.setAutoCommit(true); 
	                ConnectionUtil.close(conn, ps1, ps2); 
	            }
	        } catch (SQLException e) {
	            Logger.error(e);
	        }
	    }
	}
	
	public static void checkUserAddressIdExists(int addressId, int userId) throws ValidationException, PersistenceException {
		Connection conn = null;
		PreparedStatement pre = null;
		ResultSet rs = null; 

		try {
			String query = "SELECT id FROM address WHERE is_active = 1 AND user_id = ? AND id = ?";
			conn = ConnectionUtil.getConnection();
			pre = conn.prepareStatement(query);
			pre.setInt(1, userId);
			pre.setInt(2, addressId);
			rs = pre.executeQuery();
			if (!rs.next()) {
				throw new ValidationException("User Address Id doesn't exist");
			}
		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistenceException(e);
		} finally {
			ConnectionUtil.close(conn, pre, rs);
		}
	}

}
