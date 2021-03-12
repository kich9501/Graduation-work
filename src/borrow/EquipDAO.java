package borrow;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class EquipDAO {
	String tableName = "tequip";
	String viewName = "vlendableequip";
	
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
	
	public void insert(EquipDTO dto) {
		Connection conn = getConn();
		PreparedStatement ps = null;
		String sql = "INSERT INTO tequip(no, name, manuf, textu, year) " + 
				"VALUES(?, ?, ?, ?, ?);";

		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, dto.getNo());
			ps.setString(2, dto.getName());
			ps.setString(3, dto.getManuf());
			ps.setString(4, dto.getTextu());
			ps.setString(5, dto.getYear());
			
			ps.executeUpdate();                        //위의 sql문이 잘못될 경우 여기서 오류가 발생한다
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void update(EquipDTO dto) {
		Connection conn = getConn();
		PreparedStatement ps = null;
		String sql = "UPDATE " + tableName
				+ " SET name=?, manuf=?, textu=?, year=?"
				+ " WHERE no=?;";

		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, dto.getName());
			ps.setString(2, dto.getManuf());
			ps.setString(3, dto.getTextu());
			ps.setString(4, dto.getYear());
			ps.setString(5, dto.getNo());
			
			
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
	
	public EquipDTO getEquipDTO(String no) {
		EquipDTO dto = new EquipDTO();
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		conn = getConn();
		String sql = "SELECT * FROM " + viewName
				+ " WHERE no=?;";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, no);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				dto.setNo(rs.getString("no"));
				dto.setName(rs.getString("name"));
				dto.setManuf(rs.getString("manuf"));
				dto.setTextu(rs.getString("textu"));
				dto.setYear(rs.getString("year"));
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dto;
	}
	
	public Vector<Object> getTableHeader() {
		Vector<Object> col = new Vector<Object>();
		
		col.add("게임번호");
		col.add("장르명");
		col.add("제작사");
		col.add("게임명");
		col.add("구입연도");
		
		return col;
	}
	
	public Vector<Object> getListVector() {
		Vector<Object> data = new Vector<Object>();
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		conn = getConn();
		String sql = "SELECT * FROM " + viewName;
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Vector<String> row = new Vector<String>();
				row.add(rs.getString("no"));
				row.add(rs.getString("name"));
				row.add(rs.getString("manuf"));
				row.add(rs.getString("textu"));
				row.add(rs.getString("year"));
				
				data.add(row);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return data;
	}
}
