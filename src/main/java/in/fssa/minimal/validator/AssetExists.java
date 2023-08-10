package in.fssa.minimal.validator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import in.fssa.minimal.exception.ValidationException;
import in.fssa.minimal.util.ConnectionUtil;

public class AssetExists {
	public static void chechAssetUrlExists(String assetUrl) throws ValidationException {
		Connection conn = null;
		PreparedStatement pre = null;
		ResultSet rs = null;

		try {
			String query = "Select * From assets Where asset_url = ?";
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
			throw new RuntimeException();
		} finally {
			ConnectionUtil.close(conn, pre, rs);
		}
	}
}
