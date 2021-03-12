package borrow;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class PlayerDAO {
	String tableName = "tplayer";
	String viewName = "vplayer";
	
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
	
	public void insert(PlayerDTO dto) {
		Connection conn = getConn();
		PreparedStatement ps = null;
		String sql = "INSERT INTO tplayer(no, name, backnum, debut, height, weight, tota, position, team) " + 
				"VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?);";

		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, dto.getNo());
			ps.setString(2, dto.getName());
			ps.setString(3, dto.getBacknum());
			ps.setString(4, dto.getDebut());
			ps.setString(5, dto.getHeight());
			ps.setString(6, dto.getWeight());
			ps.setString(7, dto.getTota());
			ps.setString(8, dto.getPosition());
			ps.setString(9, dto.getTeam());
			
			ps.executeUpdate();                        //위의 sql문이 잘못될 경우 여기서 오류가 발생한다
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void update(PlayerDTO dto) {
		Connection conn = getConn();
		PreparedStatement ps = null;
		String sql = "UPDATE " + tableName
				+ " SET name=?, backnum=?, Debut=?, height=?, weight=?, tota=?, position=?, team=?"
				+ " WHERE no=?;";

		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, dto.getName());
			ps.setString(2, dto.getBacknum());
			ps.setString(3, dto.getDebut());
			ps.setString(4, dto.getHeight());
			ps.setString(5, dto.getWeight());
			ps.setString(6, dto.getTota());
			ps.setString(7, dto.getPosition());
			ps.setString(8, dto.getTeam());
			ps.setString(9, dto.getNo());
			
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
	
	public PlayerDTO getPlayerDTO(String no) {
		PlayerDTO dto = new PlayerDTO();
		
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
				dto.setBacknum(rs.getString("backnum"));
				dto.setDebut(rs.getString("debut"));
				dto.setHeight(rs.getString("height"));
				dto.setWeight(rs.getString("weight"));
				dto.setTota(rs.getString("tota"));
				dto.setPosition(rs.getString("position"));
				dto.setTeam(rs.getString("team_name"));
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
		
		col.add("회원번호");
		col.add("회원이름");
		col.add("회원등록번호");
		col.add("가입연도");
		col.add("생년월일");
		col.add("HP");
		col.add("선호장르");
		col.add("직업");
		col.add("선호 플랫폼");
		
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
				row.add(rs.getString("backnum"));
				row.add(rs.getString("debut"));
				row.add(rs.getString("height"));
				row.add(rs.getString("weight"));
				row.add(rs.getString("tota"));
				row.add(rs.getString("position"));
				row.add(rs.getString("team_name"));
				
				data.add(row);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return data;
	}
}
