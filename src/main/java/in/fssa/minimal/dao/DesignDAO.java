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
import in.fssa.minimal.model.Design;
import in.fssa.minimal.model.User;
import in.fssa.minimal.util.ConnectionUtil;

public class DesignDAO {
	
	public void create(Design newDesign) throws PersistenceException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			String query = "INSERT INTO designs ( name, description,location,style_id,created_by ) VALUES (?,?,?,?,?)";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, newDesign.getName());
			ps.setString(2, newDesign.getDescription());
			ps.setString(3, newDesign.getLocation());
			ps.setInt(4, newDesign.getStyleId());
			ps.setInt(5, newDesign.getCreatedBy());
			ps.executeUpdate();
			System.out.println("Design has been created successfully");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistenceException(e);
		} finally {
			ConnectionUtil.close(conn, ps);
		}
	}
	
	public void update(int id, Design updatedDesign) throws PersistenceException {
	    Connection conn = null;
	    PreparedStatement ps = null;
	    try {
	        StringBuilder queryBuilder = new StringBuilder("UPDATE designs SET ");
	        List<Object> values = new ArrayList<>();

	        if (updatedDesign.getName() != null) {
	            queryBuilder.append("name = ?, ");
	            values.add(updatedDesign.getName());
	        }
	        if (updatedDesign.getDescription() != null) {
	            queryBuilder.append("description = ?, ");
	            values.add(updatedDesign.getDescription());
	        }
	        if (updatedDesign.getLocation() != null) {
	            queryBuilder.append("location = ?, ");
	            values.add(updatedDesign.getLocation());
	        }
	        if (updatedDesign.getStyleId() != 0) {
	            queryBuilder.append("style_id = ?, ");
	            values.add(updatedDesign.getStyleId());
	        }
	        if (updatedDesign.getCreatedBy() != 0) {
	            queryBuilder.append("created_by = ?, ");
	            values.add(updatedDesign.getCreatedBy());
	        }
	        

	        queryBuilder.setLength(queryBuilder.length() - 2);

	        queryBuilder.append(" WHERE id = ?");
	        conn = ConnectionUtil.getConnection();
	        ps = conn.prepareStatement(queryBuilder.toString());

	        for (int i = 0; i < values.size(); i++) {
	            ps.setObject(i + 1, values.get(i));
	        }
	        ps.setInt(values.size() + 1, id);

	        ps.executeUpdate();
	        System.out.println("Design has been updated successfully");
	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.out.println(e.getMessage());
	        throw new PersistenceException(e);
	    } finally {
	        ConnectionUtil.close(conn, ps, null);
	    }
	}
	
	public Set<Design> findAllDesign() throws RuntimeException, PersistenceException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Set<Design> designList = new HashSet<>();
		try {
			String query = "SELECT * FROM designs";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				Design design = new Design();
				design.setId(rs.getInt("id"));
				design.setName(rs.getString("name"));
				design.setDescription(rs.getString("description"));
				design.setLocation(rs.getString("location"));
				design.setStyleId(rs.getInt("style_id"));
				design.setCreatedBy(rs.getInt("created_by"));
				designList.add(design);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistenceException(e);
		} finally {
			ConnectionUtil.close(conn, ps, rs);
		}
		return designList;
	}
	
	public Design findAllDesignsByDesignerId(int id) throws PersistenceException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Design design = null;
		try {
			String query = "SELECT * FROM designs WHERE created_by = ?";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				design = new Design();
				design.setId(rs.getInt("id"));
				design.setName(rs.getString("name"));
				design.setDescription(rs.getString("description"));
				design.setLocation(rs.getString("location"));
				design.setStyleId(rs.getInt("style_id"));
				design.setCreatedBy(rs.getInt("created_by"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistenceException(e);
		} finally {
			ConnectionUtil.close(conn, ps, rs);
		}
		return design;
	}
	
	public Design findByDesignId(int id) throws PersistenceException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Design design = null;
		try {
			String query = "SELECT * FROM designs WHERE id = ?";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				design = new Design();
				design.setId(rs.getInt("id"));
				design.setName(rs.getString("name"));
				design.setDescription(rs.getString("description"));
				design.setLocation(rs.getString("location"));
				design.setStyleId(rs.getInt("style_id"));
				design.setCreatedBy(rs.getInt("created_by"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistenceException(e);
		} finally {
			ConnectionUtil.close(conn, ps, rs);
		}
		return design;
	}
	
	
}
