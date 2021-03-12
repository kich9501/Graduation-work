package borrow;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class LendEquipDAO {
	String tableName = "vlendequip";

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
	
	public Vector<Object> getTableHeader() {
		Vector<Object> col = new Vector<Object>();
		
		col.add("대여날짜");
		col.add("게임번호");
		col.add("장르명");
		col.add("게임명");
		col.add("회원번호");
		col.add("회원명");
		col.add("대여기간");
		
		return col;
	}
	
	public Vector<Object> getListVector(){
		return getListVector("");
		
	}
	
	public Vector<Object> getListVector(String mno) {
		Vector<Object> data = new Vector<Object>();
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		conn = getConn();
		String sql = "SELECT * FROM " + tableName
				+ " Where mno='" + mno + "';";
		if(mno.compareTo("") ==0) {
			sql = "SELECT * FROM " + tableName;
		}
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Vector<String> row = new Vector<String>();
				row.add(rs.getString("no"));
				row.add(rs.getString("bno"));
				row.add(rs.getString("name"));
				row.add(rs.getString("textu"));
				row.add(rs.getString("mno"));
				row.add(rs.getString("name:1"));
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
