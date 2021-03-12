package borrow;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class BorrowDAO {
	String tableName = "tborrow";
	
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
	
	public void insert(BorrowDTO dto) {
		Connection conn = getConn();
		PreparedStatement ps = null;
		String sql = "INSERT INTO " + tableName
				+ "(bno, mno, days) " +
				"VALUES(?, ?, ?);";

		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, dto.getBno());
			ps.setString(2, dto.getMno());
			ps.setInt(3, dto.getDays());
			
			ps.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void delete(String no) {
		Connection conn = getConn();
		PreparedStatement ps = null;
		String sql = "DELETE FROM " +tableName+ " WHERE no=? ";

		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, no);
			
			ps.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void delete(String[] arNo) {
		String nos = "(";
		for(int i=0; i<arNo.length; i++) {
			nos += "'" + arNo[i] + "',";
		}
		if(nos.endsWith(","))
			nos = nos.substring(0, nos.length()-1);
		nos += ")";
		
		Connection conn = getConn();
		PreparedStatement ps = null;
		String sql = "DELETE FROM " +tableName+ " WHERE no IN " + nos;

		try {
			ps = conn.prepareStatement(sql);
			
			ps.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Vector<Object> getListVector() {
		Vector<Object> data = new Vector<Object>();
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		conn = getConn();
		String sql = "SELECT * FROM " + tableName;
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Vector<String> row = new Vector<String>();
				row.add(rs.getString("no"));
				row.add(rs.getString("bno"));
				row.add(rs.getString("mno"));
				row.add(rs.getString("cdate"));
				row.add(rs.getString("days"));
				
				data.add(row);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return data;
	}
}
