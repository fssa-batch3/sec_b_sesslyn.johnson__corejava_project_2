package in.fssa.minimal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import in.fssa.minimal.exception.PersistenceException;
import in.fssa.minimal.model.Asset;
import in.fssa.minimal.util.ConnectionUtil;

public class AssetDAO {
	public void create(Asset newAsset) throws PersistenceException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			String query = "INSERT INTO styles ( name ) VALUES (?)";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, newAsset.getAssetsUrl());
			ps.executeUpdate();
			System.out.println("Asset has been created successfully");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistenceException(e);
		} finally {
			ConnectionUtil.close(conn, ps);
		}
	}
	
	public void update(int id, Asset updatedAsset) throws PersistenceException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			String query = "UPDATE assets SET asset_url = ? WHERE id = ?";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, updatedAsset.getAssetsUrl());
			ps.setInt(2, id);
			ps.executeUpdate();
			System.out.println("Asset has been updated successfully");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistenceException(e);
		} finally {
			ConnectionUtil.close(conn, ps, null);
		}
	}
}
