package in.fssa.minimal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
import in.fssa.minimal.util.Logger;
 
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
			String query = "INSERT INTO design_assets (design_id, assets_id, designer_id) VALUES (?, ?, ?)";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, newDesignAsset.getDesignId());
			ps.setInt(2, newDesignAsset.getAssetsId());
			ps.setInt(3, newDesignAsset.getDesignerId());
			ps.executeUpdate();
			Logger.info("Design Asset has been created successfully");

		} catch (SQLException e) { 
			Logger.error(e); 
			throw new PersistenceException(e);
		} finally {
			ConnectionUtil.close(conn, ps);
		}
	}

	
	/**
	 * Deletes a design asset.
	 *
	 * @param designAssetId The ID of the design asset to be deleted.
	 * @throws PersistenceException If there's an issue with database interaction.
	 */
	public void delete(int designAssetId) throws PersistenceException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			String query = "UPDATE design_assets SET is_active = false WHERE is_active = 1 AND id = ?";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, designAssetId);
			ps.executeUpdate();
			Logger.info("Design Asset has been deleted successfully");
		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistenceException(e);
		} finally {
			ConnectionUtil.close(conn, ps);
		}
	}
	
	/**
	 * Activates a design asset.
	 *
	 * @param designAssetId The ID of the design asset to be activated.
	 * @throws PersistenceException If there's an issue with database interaction.
	 */
	public void activate(int designAssetId) throws PersistenceException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			String query = "UPDATE design_assets SET is_active = true WHERE is_active = false AND id = ?";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, designAssetId);
			ps.executeUpdate();
			Logger.info("Design Asset has been activated successfully");
		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistenceException(e);
		} finally {
			ConnectionUtil.close(conn, ps);
		}
	}
	
	/**
	 * Retrieves all active design assets.
	 *
	 * @return A set of DesignAssetRespondDto objects representing all active design assets.
	 * @throws ValidationException  If there's an issue with data validation.
	 * @throws PersistenceException If there's an issue with database interaction.
	 * @throws ServiceException If there's an issue with a service.
	 */
	public List<DesignAssetRespondDTO> findAllDesignAsset()
			throws ValidationException, PersistenceException, ServiceException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<DesignAssetRespondDTO> designAssetList = new ArrayList<>();
		try {
			String query = "SELECT * FROM design_assets ";
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
				designAss.setDesignerId(rs.getInt("designer_id"));
				designAss.setActive(rs.getBoolean("is_active"));
				designAssetList.add(designAss);
			}

		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistenceException(e);
		} finally {
			ConnectionUtil.close(conn, ps, rs);
		}
		return designAssetList;
	}

	/**
	 * Retrieves all design assets associated with a specific designer.
	 *
	 * @param designerId The ID of the designer.
	 * @return A set of DesignAssetRespondDto objects representing design assets of the designer.
	 * @throws ValidationException  If there's an issue with data validation.
	 * @throws PersistenceException If there's an issue with database interaction.
	 * @throws ServiceException If there's an issue with a service.
	 */
	public List<DesignAssetRespondDTO> findAllDesignAssetByDesignerId(int designerId)
			throws ValidationException, PersistenceException, ServiceException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<DesignAssetRespondDTO> designAssetList = new ArrayList<>();
		try {
			String query = "SELECT * FROM design_assets WHERE designer_id = ?";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, designerId);
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
				designAss.setDesignerId(rs.getInt("designer_id"));
				designAss.setActive(rs.getBoolean("is_active"));
				designAssetList.add(designAss);
			}

		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistenceException(e);
		} finally {
			ConnectionUtil.close(conn, ps, rs);
		}
		return designAssetList;
	}
	
	/**
	 * Retrieves all active design assets associated with a specific designer.
	 *
	 * @param designerId The ID of the designer.
	 * @return A set of DesignAssetRespondDto objects representing active design assets of the designer.
	 * @throws ValidationException  If there's an issue with data validation.
	 * @throws PersistenceException If there's an issue with database interaction.
	 * @throws ServiceException If there's an issue with a service.
	 */
	public List<DesignAssetRespondDTO> findAllActiveDesignAssetByDesignerId(int designerId)
			throws ValidationException, PersistenceException, ServiceException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<DesignAssetRespondDTO> designAssetList = new ArrayList<>();
		try {
			String query = "SELECT * FROM design_assets WHERE is_active = 1 AND designer_id = ?";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, designerId);
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
				designAss.setDesignerId(rs.getInt("designer_id"));
				designAss.setActive(rs.getBoolean("is_active"));
				designAssetList.add(designAss);
			}

		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistenceException(e);
		} finally {
			ConnectionUtil.close(conn, ps, rs);
		}
		return designAssetList;
	}

	/**
	 * Retrieves a specific design asset by ID.
	 *
	 * @param designAssetId The ID of the design asset to be retrieved.
	 * @return The DesignAssetRespondDto object representing the design asset.
	 * @throws ValidationException  If there's an issue with data validation.
	 * @throws PersistenceException If there's an issue with database interaction.
	 * @throws ServiceException If there's an issue with a service.
	 */
	public DesignAssetRespondDTO findAllDesignAssetById(int designAssetId)
			throws ValidationException, PersistenceException, ServiceException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		DesignAssetRespondDTO designAss = null;
		try {
			String query = "SELECT * FROM design_assets WHERE id = ?";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, designAssetId);
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
				designAss.setDesignerId(rs.getInt("designer_id"));
				designAss.setActive(rs.getBoolean("is_active"));
			}

		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistenceException(e);
		} finally {
			ConnectionUtil.close(conn, ps, rs);
		}
		return designAss;
	}

	/**
	 * Checks if a design asset with the given ID exists.
	 *
	 * @param designAssetId The ID to be checked.
	 * @throws ValidationException  If the ID does not exist.
	 * @throws PersistenceException If there's an issue with database interaction.
	 */
	public static void checkIdExists(int designAssetId) throws ValidationException, PersistenceException {
		Connection conn = null;
		PreparedStatement pre = null;
		ResultSet rs = null;

		try {
			String query = "Select id From design_assets Where id = ?";
			conn = ConnectionUtil.getConnection();
			pre = conn.prepareStatement(query);
			pre.setInt(1, designAssetId);
			rs = pre.executeQuery();
			if (!rs.next()) {
				throw new ValidationException("Design Asset Id doesn't exist");
			}
		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistenceException(e);
		} finally {
			ConnectionUtil.close(conn, pre, rs);
		}
	}

	/**
	 * Retrieves the ID of the last updated non-designer user who is active.
	 *
	 * @return The ID of the last updated non-designer user.
	 * @throws PersistenceException If a database error occurs while retrieving the ID.
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
			Logger.error(e);
			throw new PersistenceException(e);
		} finally {
			ConnectionUtil.close(conn, ps, rs);
		}
		return id;
	}

	/**
	 * Retrieves a specific design asset by ID.
	 *
	 * @param designAssetId The ID of the design asset to retrieve.
	 * @return The DesignAsset object representing the design asset.
	 * @throws PersistenceException If there's an issue with database interaction.
	 */
	public DesignAsset findDesignAssetById(int designAssetId) throws PersistenceException{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		DesignAsset designAss = null;
		try {
			String query = "SELECT * FROM design_assets WHERE id = ?";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, designAssetId);
			rs = ps.executeQuery();
			if (rs.next()) {
				designAss = new DesignAsset();
				designAss.setId(rs.getInt("id"));
				designAss.setDesignId(rs.getInt("design_id"));
				designAss.setAssetsId(rs.getInt("assets_id"));
				designAss.setDesignerId(rs.getInt("designer_id"));
				designAss.setActive(rs.getBoolean("is_active"));
			}

		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistenceException(e);
		} finally {
			ConnectionUtil.close(conn, ps, rs);
		}
		return designAss;
	}

	/**
	 * Retrieves the ID of the last updated design.
	 *
	 * @return The ID of the last updated design.
	 * @throws PersistenceException If a database error occurs while retrieving the ID.
	 */
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
			Logger.error(e);
			throw new PersistenceException(e);
		} finally {
			ConnectionUtil.close(conn, ps, rs);
		}
		return designerId;
	}

	/**
	 * Retrieves the ID of the last updated asset.
	 *
	 * @return The ID of the last updated asset.
	 * @throws PersistenceException If a database error occurs while retrieving the ID.
	 */
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
			Logger.error(e);
			throw new PersistenceException(e);
		} finally {
			ConnectionUtil.close(conn, ps, rs);
		}
		return assetId;
	}

}