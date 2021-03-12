package borrow;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TeamDAO {
	String tableName = "tteam";

	public Connection getConn() {
		Connection conn = null;

		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:" + Myconst.DB);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return conn;
	}

	public ArrayList<String> getListTeam() {
		ArrayList<String> arList = new ArrayList<String>();

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		conn = getConn();
		String sql = "SELECT * FROM " + tableName;

		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				arList.add(rs.getString("team_no") + ":" + rs.getString("team_name"));

			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arList;
	}
}
