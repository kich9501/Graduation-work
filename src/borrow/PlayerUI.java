package borrow;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class PlayerUI extends JFrame implements ActionListener, MouseListener{
	
	DefaultTableModel model;
	JTable table;
	
	JTextField tfNo, tfName, tfHei, tfWei, tfBack;
	JComboBox<String> comDebut, comTota, comPosi, comTeam;
	
	HashMap<String, String> hashTeam;
	HashMap<String, String> hashTeamRev;
	
	JButton btnInit;
	
	public PlayerUI() {
		this.setSize(1200, 600);
		this.setTitle("손님 관리");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLayout(null);
		this.setResizable(false);
		
		JPanel panList = new JPanel();
		panList.setBounds(10, 10, 780, 550);
		panList.setBackground(Color.BLACK);
		panList.setLayout(null);
		this.add(panList);
		
		PlayerDAO  dao = new PlayerDAO();
		Vector<Object> vecs = dao.getListVector();
		Vector<Object> cols = dao.getTableHeader();
		
		model = new DefaultTableModel(vecs, cols);
		table = new JTable(model);
		JScrollPane scroll = new JScrollPane(table);
		scroll.setBounds(10, 10, 760, 530);
		panList.add(scroll);
		table.addMouseListener(this);
		
		JPanel panRight = new JPanel();
		panRight.setBounds(800, 10, 380, 550);
		panRight.setBackground(Color.WHITE);
		panRight.setLayout(null);
		this.add(panRight);
		
		//선수번호
		JLabel lblNo = new JLabel("회원번호 : ");
		lblNo.setBounds(10, 10, 100, 25);
		panRight.add(lblNo);
		
		tfNo = new JTextField();
		tfNo.setBounds(100, 10, 100, 25);
		panRight.add(tfNo);
		
		//선수이름
		JLabel lblName = new JLabel("회원이름 : ");
		lblName.setBounds(10, 50, 100, 25);
		panRight.add(lblName);
				
		tfName = new JTextField();
		tfName.setBounds(100, 50, 100, 25);
		panRight.add(tfName);
		
		JLabel lblBack = new JLabel("회원등록번호 : ");
		lblBack.setBounds(10, 90, 100, 25);
		panRight.add(lblBack);
				
		tfBack = new JTextField();
		tfBack.setBounds(100, 90, 100, 25);
		panRight.add(tfBack);
		
		//데뷔연도
		JLabel lblDebut = new JLabel("회원가입연도 : ");
		lblDebut.setBounds(10, 130, 100, 25);
		panRight.add(lblDebut);
		
		String[] strDebut = {"2020", "2019", "2018", "2017", "2016", "2015", "2014", "2013", "2012", "2011", "2010", "2009", "2008", "2007", "2006", "2005", "2004", "2003", "2002", "2001", "2000"};
		comDebut = new JComboBox<String>(strDebut);
		comDebut.setBounds(100, 130, 60, 25);
		panRight.add(comDebut);
		
		//키
		JLabel lblHei = new JLabel("생년월일 : ");
		lblHei.setBounds(10, 170, 100, 25);
		panRight.add(lblHei);
				
		tfHei = new JTextField();
		tfHei.setBounds(100, 170, 60, 25);
		panRight.add(tfHei);
		
		
		//몸무게
		JLabel lblWei = new JLabel("핸드폰 뒷자리 : ");
		lblWei.setBounds(10, 210, 100, 25);
		panRight.add(lblWei);

		tfWei = new JTextField();
		tfWei.setBounds(100, 210, 50, 25);
		panRight.add(tfWei);

		
		//투타
		JLabel lblTota = new JLabel("즐겨찾는 장르 : ");
		lblTota.setBounds(10, 250, 100, 25);
		panRight.add(lblTota);

		String[] strTota = { "RPG", "FPS", "턴제게임", "로그라이크"};
		comTota = new JComboBox<String>(strTota);
		comTota.setBounds(100, 250, 85, 25);
		panRight.add(comTota);
		
		//포지션
		JLabel lblPosi = new JLabel("직업 : ");
		lblPosi.setBounds(10, 290, 100, 25);
		panRight.add(lblPosi);

		String[] strPosi = { "학생", "직장인", "의사", "경찰", "교수"};
		comPosi = new JComboBox<String>(strPosi);
		comPosi.setBounds(100, 290, 70, 25);
		panRight.add(comPosi);
		
		//소속팀
		JLabel lblTeam = new JLabel("사용 플랫폼 : ");
		lblTeam.setBounds(10, 330, 100, 25);
		panRight.add(lblTeam);
				
		comTeam = new JComboBox<String>();
		comTeam.setBounds(100, 330, 120, 25);
		panRight.add(comTeam);
		
		TeamDAO ddao = new TeamDAO();
		ArrayList<String> arList = ddao.getListTeam();
		hashTeam = new HashMap<String, String>();
		hashTeamRev = new HashMap<String, String>();
		for(int i=0; i<arList.size(); i++) {
			comTeam.addItem(arList.get(i).substring(2));
			
			String num = arList.get(i).substring(0, 1);
			String name = arList.get(i).substring(2);
			hashTeam.put(num, name);
			hashTeamRev.put(name, num);
		}
		
		JButton btnSave = new JButton("저장");
		btnSave.setBounds(10, 500, 80, 40);
		panRight.add(btnSave);
		btnSave.addActionListener(this);
		
		JButton btnDel = new JButton("삭제");
		btnDel.setBounds(100, 500, 80, 40);
		panRight.add(btnDel);
		btnDel.addActionListener(this);
		
		JButton btnUpdate = new JButton("수정");
		btnUpdate.setBounds(190, 500, 80, 40);
		panRight.add(btnUpdate);
		btnUpdate.addActionListener(this);
		
		btnInit = new JButton("초기화");
		btnInit.setBounds(280, 500, 80, 40);
		panRight.add(btnInit);
		btnInit.addActionListener(this);

		this.setVisible(true);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		PlayerDTO dto = null;
		PlayerDAO dao = null;
		String no;
		String name;
		String backnum;
		String debut;
		String height;
		String weight;
		String tota;
		String position;
		String team;

		switch (e.getActionCommand()) {

		case "초기화":
			tfNo.setText("");
			tfName.setText("");
			tfBack.setText("");
			comDebut.setSelectedIndex(0);
			tfHei.setText("");
			tfWei.setText("");
			comTota.setSelectedIndex(0);
			comPosi.setSelectedIndex(0);
			comTeam.setSelectedIndex(0);
			break;

		case "저장":
			no = tfNo.getText();
			name = tfName.getText();
			backnum = tfBack.getText();
			debut = (String) comDebut.getSelectedItem();
			height = tfHei.getText();
			weight = tfWei.getText();
			tota = (String) comTota.getSelectedItem();
			position = (String) comPosi.getSelectedItem();
			team = hashTeamRev.get(comTeam.getSelectedItem().toString());

			dto = new PlayerDTO(no, name, backnum, debut, height, weight, tota, position, team);
			dao = new PlayerDAO();
			dao.insert(dto);
			// System.out.println(dto.toString());

			model.setDataVector(dao.getListVector(), dao.getTableHeader());
			btnInit.doClick();
			break;

		case "수정":
			no = tfNo.getText();
			name = tfName.getText();
			backnum = tfBack.getText();
			debut = comDebut.getSelectedItem().toString();
			height = tfHei.getText();
			weight = tfWei.getText();
			tota = comTota.getSelectedItem().toString();
			position = comPosi.getSelectedItem().toString();
			team = hashTeamRev.get(comTeam.getSelectedItem().toString());

			dto = new PlayerDTO(no, name, backnum, debut, height, weight, tota, position, team);
			dao = new PlayerDAO();
			dao.update(dto);
			// System.out.println(dto.toString());

			model.setDataVector(dao.getListVector(), dao.getTableHeader());
			btnInit.doClick();
			break;

		case "삭제":
			no = tfNo.getText();
			dao = new PlayerDAO();
			dao.delete(no);

			model.setDataVector(dao.getListVector(), dao.getTableHeader());
			btnInit.doClick();
			break;
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		String no = table.getValueAt(table.getSelectedRow(), 0).toString();

		PlayerDAO dao = new PlayerDAO();
		PlayerDTO dto = dao.getPlayerDTO(no);
		
		tfNo.setText(dto.getNo());
		tfName.setText(dto.getName());
		comDebut.setSelectedItem(dto.getDebut());
		tfBack.setText(dto.getBacknum());
		tfHei.setText(dto.getHeight());
		tfWei.setText(dto.getWeight());
		comTota.setSelectedItem(dto.getTota());
		comPosi.setSelectedItem(dto.getPosition());
		comTeam.setSelectedItem(dto.getTeam());

		System.out.println(no);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
