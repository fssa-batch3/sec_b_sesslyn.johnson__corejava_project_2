package in.fssa.minimal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import in.fssa.minimal.exception.PersistenceException;
import in.fssa.minimal.exception.ValidationException;
import in.fssa.minimal.model.Asset;
import in.fssa.minimal.util.ConnectionUtil;

public class AssetDAO {
	/**
	 * Creates a new asset with the provided URL.
	 *
	 * @param newAsset The Asset object containing the URL of the new asset.
	 * @throws PersistenceException If a database error occurs while creating the
	 *                              asset.
	 */
	public void create(Asset newAsset) throws PersistenceException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			String query = "INSERT INTO assets ( asset_url ) VALUES (?)";
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

	/**
	 * Updates the URL of an existing asset.
	 *
	 * @param id           The ID of the asset to update.
	 * @param updatedAsset The Asset object containing the updated URL.
	 * @throws PersistenceException If a database error occurs while updating the
	 *                              asset.
	 */
	public void update(int assetId, Asset updateAsset) throws PersistenceException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			String query = "UPDATE assets SET asset_url = ? WHERE id = ?";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, updateAsset.getAssetsUrl());
			ps.setInt(2, assetId);
			ps.executeUpdate();
			System.out.println("Asset has been updated successfully");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistenceException(e);
		} finally {
			ConnectionUtil.close(conn, ps);
		}
	}

	/**
	 * Retrieves detailed information about an asset by its ID.
	 *
	 * @param id The ID of the asset to retrieve.
	 * @return An Asset object representing the requested asset.
	 * @throws PersistenceException If a database error occurs while retrieving the
	 *                              asset.
	 */
	public Asset findById(int assetId) throws PersistenceException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Asset asset = null;
		try {
			String query = "SELECT asset_url,id FROM assets WHERE id = ?";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, assetId);
			rs = ps.executeQuery();
			if (rs.next()) {
				asset = new Asset();
				asset.setId(rs.getInt("id"));
				asset.setAssetsUrl(rs.getString("asset_url"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistenceException(e);
		} finally {
			ConnectionUtil.close(conn, ps, rs);
		}
		return asset;
	}

	/**
	 * Checks if an asset with the given ID exists in the database.
	 *
	 * @param id The ID of the asset to check.
	 * @throws ValidationException If the asset with the specified ID doesn't exist.
	 *  @throws PersistenceException If a database error occurs while retrieving the
	 *                              asset id.
	 */
	public static void checkIdExists(int assetId) throws ValidationException, PersistenceException {
		Connection conn = null;
		PreparedStatement pre = null;
		ResultSet rs = null;

		try {
			String query = "SELECT id FROM assets WHERE id = ?";
			conn = ConnectionUtil.getConnection();
			pre = conn.prepareStatement(query);
			pre.setInt(1, assetId);
			rs = pre.executeQuery();
			if (!rs.next()) {
				throw new ValidationException("Asset Id doesn't exist");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistenceException(e);
		} finally {
			ConnectionUtil.close(conn, pre, rs);
		}
	}

	/**
	 * Checks if an asset with the given URL exists in the database.
	 *
	 * @param assetUrl The URL of the asset to check.
	 * @throws ValidationException If an asset with the specified URL already
	 *                             exists.
	 *  @throws PersistenceException If a database error occurs while retrieving the
	 *                              asset.
	 */
	public static void checkAssetUrlExists(String assetUrl) throws ValidationException, PersistenceException {
		Connection conn = null;
		PreparedStatement pre = null;
		ResultSet rs = null;

		try {
			String query = "SELECT asset_url FROM assets WHERE asset_url = ?";
			conn = ConnectionUtil.getConnection();
			pre = conn.prepareStatement(query);
			pre.setString(1, assetUrl);
			rs = pre.executeQuery();
			if (rs.next()) {
				throw new ValidationException("Asset Url already exists");
			}
		} catch (SQLException e) {
		e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistenceException(e);
		} finally {
			ConnectionUtil.close(conn, pre, rs);
		}
	}
}