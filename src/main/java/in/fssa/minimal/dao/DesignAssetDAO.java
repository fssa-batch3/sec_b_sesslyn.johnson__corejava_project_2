package in.fssa.minimal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import in.fssa.minimal.dto.DesignAssetRespondDTO;
import in.fssa.minimal.exception.PersistenceException;
import in.fssa.minimal.exception.ServiceException;
import in.fssa.minimal.exception.ValidationException;
import in.fssa.minimal.model.Asset;
import in.fssa.minimal.model.Design;
import in.fssa.minimal.model.DesignAsset;
import in.fssa.minimal.service.AssetService;
import in.fssa.minimal.service.DesignService;
import in.fssa.minimal.util.ConnectionUtil;

public class DesignAssetDAO {
	/**
	 * Creates a new design asset.
	 *
	 * @param newDesignAsset The DesignAsset object to be created.
	 * @throws PersistenceException If there's an issue with database interaction.
	 */
	public void create(DesignAsset newDesignAsset) throws PersistenceException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			String query = "INSERT INTO design_assets (design_id, assets_id) VALUES (?, ?)";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, newDesignAsset.getDesignId());
			ps.setInt(2, newDesignAsset.getAssetsId());
			ps.executeUpdate();
			System.out.println("Design Asset has been created successfully");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistenceException(e);
		} finally {
			ConnectionUtil.close(conn, ps);
		}
	}

	/**
	 * Updates an existing design asset.
	 *
	 * @param id                 The ID of the design asset to be updated.
	 * @param updatedDesignAsset The updated DesignAsset object.
	 * @throws PersistenceException If there's an issue with database interaction.
	 * @throws ServiceException
	 * @throws ValidationException
	 */
	public void update(int id, DesignAsset updatedDesignAsset) throws PersistenceException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			StringBuilder queryBuilder = new StringBuilder("UPDATE design_assets SET ");
			List<Object> values = new ArrayList<>();

			if (updatedDesignAsset.getDesignId() != 0) {
				queryBuilder.append("design_id = ?, ");
				values.add(updatedDesignAsset.getDesignId());
			}
			if (updatedDesignAsset.getAssetsId() != 0) {
				queryBuilder.append("assets_id = ?, ");
				values.add(updatedDesignAsset.getAssetsId());
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
			System.out.println("Design Asset has been updated successfully");
		} catch (SQLException e) {

			System.out.println(e.getMessage());
			throw new PersistenceException(e);
		} finally {
			ConnectionUtil.close(conn, ps, null);
		}
	}
	/**
	 * Deletes a design asset.
	 *
	 * @param id The ID of the design asset to be deleted.
	 * @throws PersistenceException If there's an issue with database interaction.
	 */
	public void delete(int id) throws PersistenceException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			String query = "UPDATE design_assets SET is_active = false WHERE is_active = 1 AND id = ?";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			ps.executeUpdate();
			System.out.println("Design Asset has been deleted successfully");
		} catch (SQLException e) {

			System.out.println(e.getMessage());
			throw new PersistenceException(e);
		} finally {
			ConnectionUtil.close(conn, ps, null);
		}
	}
	/**
	 * Retrieves all active design assets.
	 *
	 * @return A set of DesignAssetRespondDto objects representing all active design
	 *         assets.
	 * @throws ValidationException  If there's an issue with data validation.
	 * @throws PersistenceException If there's an issue with database interaction.
	 * @throws ServiceException
	 */
	public Set<DesignAssetRespondDTO> findAllDesignAsset()
			throws ValidationException, PersistenceException, ServiceException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Set<DesignAssetRespondDTO> designAssetList = new HashSet<>();
		try {
			String query = "SELECT * FROM design_assets WHERE is_active = 1";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			Design designObj = null;
			Asset assetObj = null;
			while (rs.next()) {
				int designId = rs.getInt("design_id");
				int assetsId = rs.getInt("assets_id");

				designObj = DesignService.findByDesignId(designId);
				assetObj = AssetService.findByAssetId(assetsId);

				DesignAssetRespondDTO designAss = new DesignAssetRespondDTO();
				designAss.setId(rs.getInt("id"));
				designAss.setDesignId(designObj);
				designAss.setAssetsId(assetObj);
				designAss.setActive(rs.getBoolean("is_active"));
				designAssetList.add(designAss);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistenceException(e);
		} finally {
			ConnectionUtil.close(conn, ps, rs);
		}
		return designAssetList;
	}

	/**
	 * Retrieves a specific design asset by ID.
	 *
	 * @param id The ID of the design asset to be retrieved.
	 * @return The DesignAssetRespondDto object representing the design asset.
	 * @throws ValidationException  If there's an issue with data validation.
	 * @throws PersistenceException If there's an issue with database interaction.
	 * @throws ServiceException
	 */
	public DesignAssetRespondDTO findAllDesignAssetById(int id)
			throws ValidationException, PersistenceException, ServiceException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		DesignAssetRespondDTO designAss = null;
		try {
			String query = "SELECT * FROM design_assets WHERE is_active = 1 AND id = ?";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			Design designObj = null;
			Asset assetObj = null;
			if (rs.next()) {
				int designId = rs.getInt("design_id");
				int assetsId = rs.getInt("assets_id");

				designObj = DesignService.findByDesignId(designId);
				assetObj = AssetService.findByAssetId(assetsId);

				designAss = new DesignAssetRespondDTO();
				designAss.setId(rs.getInt("id"));
				designAss.setDesignId(designObj);
				designAss.setAssetsId(assetObj);
				designAss.setActive(rs.getBoolean("is_active"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistenceException(e);
		} finally {
			ConnectionUtil.close(conn, ps, rs);
		}
		return designAss;
	}

	/**
	 * Checks if a design asset with the given ID exists.
	 *
	 * @param id The ID to be checked.
	 * @throws ValidationException  If the ID does not exist.
	 * @throws PersistenceException
	 */
	public static void checkIdExists(int id) throws ValidationException, PersistenceException {
		Connection conn = null;
		PreparedStatement pre = null;
		ResultSet rs = null;

		try {
			String query = "Select id From design_assets Where id = ?";
			conn = ConnectionUtil.getConnection();
			pre = conn.prepareStatement(query);
			pre.setInt(1, id);
			rs = pre.executeQuery();
			if (!rs.next()) {
				throw new ValidationException("Design Asset Id doesn't exist");
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
	 * Retrieves the ID of the last updated non-designer user who is active.
	 *
	 * @return The ID of the last updated non-designer user.
	 * @throws PersistenceException If a database error occurs while retrieving the
	 *                              ID.
	 */
	public static int getLastUpdatedDesignAssetId() throws PersistenceException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int id = 0;
		try {
			String query = "SELECT id FROM design_assets WHERE is_active = 1 ORDER BY created_at DESC LIMIT 1";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			if (rs.next()) {
				id = rs.getInt("id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistenceException(e);
		} finally {
			ConnectionUtil.close(conn, ps, rs);
		}
		return id;
	}

	public DesignAsset findDesignAssetById(int id) throws ValidationException, PersistenceException, ServiceException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		DesignAsset designAss = null;
		try {
			String query = "SELECT * FROM design_assets WHERE is_active = 1 AND id = ?";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				designAss = new DesignAsset();
				designAss.setId(rs.getInt("id"));
				designAss.setDesignId(rs.getInt("design_id"));
				designAss.setAssetsId(rs.getInt("assets_id"));
				designAss.setActive(rs.getBoolean("is_active"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistenceException(e);
		} finally {
			ConnectionUtil.close(conn, ps, rs);
		}
		return designAss;
	}

	public static int getLastUpdatedDesignId() throws PersistenceException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int designerId = 0;
		try {
			String query = "SELECT id FROM designs ORDER BY created_at DESC LIMIT 1";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			if (rs.next()) {
				designerId = rs.getInt("id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistenceException(e);
		} finally {
			ConnectionUtil.close(conn, ps, rs);
		}
		return designerId;
	}

	public static int getLastUpdatedAssetId() throws PersistenceException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int assetId = 0;
		try {
			String query = "SELECT id FROM assets ORDER BY id DESC LIMIT 1";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			if (rs.next()) {
				assetId = rs.getInt("id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistenceException(e);
		} finally {
			ConnectionUtil.close(conn, ps, rs);
		}
		return assetId;
	}

}