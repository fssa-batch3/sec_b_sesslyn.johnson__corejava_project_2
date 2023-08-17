package in.fssa.minimal.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import in.fssa.minimal.dto.AppointmentRespondDto;
import in.fssa.minimal.dto.DesignAssetRespondDto;
import in.fssa.minimal.exception.PersistenceException;
import in.fssa.minimal.exception.ValidationException;
import in.fssa.minimal.model.Asset;
import in.fssa.minimal.model.Design;
import in.fssa.minimal.model.DesignAsset;
import in.fssa.minimal.model.User;
import in.fssa.minimal.service.AssetService;
import in.fssa.minimal.service.DesignService;
import in.fssa.minimal.service.UserService;
import in.fssa.minimal.util.ConnectionUtil;

public class DesignAssetDAO {
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
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistenceException(e);
		} finally {
			ConnectionUtil.close(conn, ps, null);
		}
	}

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
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistenceException(e);
		} finally {
			ConnectionUtil.close(conn, ps, null);
		}
	}

	public Set<DesignAssetRespondDto> findAllDesignAsset() throws ValidationException, PersistenceException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Set<DesignAssetRespondDto> designAssetList = new HashSet<>();
		try {
			String query = "SELECT * FROM design_assets WHERE is_active = 1";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			Design design_obj = null;
			Asset asset_obj = null;
			while (rs.next()) {
				int design_id = rs.getInt("design_id");
				int assets_id = rs.getInt("assets_id");

				design_obj = DesignService.findByDesignId(design_id);
				asset_obj = AssetService.findByAssetId(assets_id);

				DesignAssetRespondDto designAss = new DesignAssetRespondDto();
				designAss.setId(rs.getInt("id"));
				designAss.setDesignId(design_obj);
				designAss.setAssetsId(asset_obj);
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
	
	public DesignAssetRespondDto findAllDesignAssetById(int id) throws ValidationException, PersistenceException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		DesignAssetRespondDto designAss = null;
		try {
			String query = "SELECT * FROM design_assets WHERE is_active = 1 AND id = ?";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			Design design_obj = null;
			Asset asset_obj = null;
			if (rs.next()) {
				int design_id = rs.getInt("design_id");
				int assets_id = rs.getInt("assets_id");

				design_obj = DesignService.findByDesignId(design_id);
				asset_obj = AssetService.findByAssetId(assets_id);

				designAss = new DesignAssetRespondDto();
				designAss.setId(rs.getInt("id"));
				designAss.setDesignId(design_obj);
				designAss.setAssetsId(asset_obj);
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
}
