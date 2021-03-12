package borrow;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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

public class EquipUI extends JFrame implements ActionListener, MouseListener{
	
	DefaultTableModel model;
	JTable table;
	
	JTextField tfManuf, tfTextu, tfNo;
	JComboBox<String> comJname, comYear;
	
	JButton btnInit;
	
	public EquipUI() {
		this.setSize(800, 600);
		this.setTitle("게임타이틀 관리");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLayout(null);
		this.setResizable(false);
		
		JPanel panList = new JPanel();
		panList.setBounds(10, 10, 390, 550);
		panList.setBackground(Color.BLACK);
		panList.setLayout(null);
		this.add(panList);
		
		EquipDAO  dao = new EquipDAO();
		Vector<Object> vecs = dao.getListVector();
		Vector<Object> cols = dao.getTableHeader();
		
		model = new DefaultTableModel(vecs, cols);
		table = new JTable(model);
		JScrollPane scroll = new JScrollPane(table);
		scroll.setBounds(10, 10, 370, 530);
		panList.add(scroll);
		table.addMouseListener(this);
		
		JPanel panRight = new JPanel();
		panRight.setBounds(410, 10, 380, 550);
		panRight.setBackground(Color.WHITE);
		panRight.setLayout(null);
		this.add(panRight);
		
		//장비번호
		JLabel lblNo = new JLabel("게임번호 : ");
		lblNo.setBounds(10, 10, 100, 25);
		panRight.add(lblNo);

		tfNo = new JTextField();
		tfNo.setBounds(75, 10, 100, 25);
		panRight.add(tfNo);
		
		//장비명
		JLabel lblJname = new JLabel("장르 : ");
		lblJname.setBounds(10, 50, 100, 25);
		panRight.add(lblJname);

		String[] strJname = {"RPG", "FPS", "로그라이크", "AOS", "추리게임", "턴제 게임"};
		comJname = new JComboBox<String>(strJname);
		comJname.setBounds(75, 50, 80, 25);
		panRight.add(comJname);
		
		//제조사
		JLabel lblManuf = new JLabel("제작사 : ");
		lblManuf.setBounds(10, 90, 100, 25);
		panRight.add(lblManuf);
		
		tfManuf = new JTextField();
		tfManuf.setBounds(75, 90, 100, 25);
		panRight.add(tfManuf);
		
		//재질
		JLabel lblTextu = new JLabel("게임명 : ");
		lblTextu.setBounds(10, 130, 100, 25);
		panRight.add(lblTextu);

		tfTextu = new JTextField();
		tfTextu.setBounds(75, 130, 100, 25);
		panRight.add(tfTextu);
		
		//구입연도
		JLabel lblYear = new JLabel("구입연도 : ");
		lblYear.setBounds(10, 170, 100, 25);
		panRight.add(lblYear);

		String[] strYear = { "2020", "2019", "2018", "2017", "2016", "2015" };
		comYear = new JComboBox<String>(strYear);
		comYear.setBounds(75, 170, 80, 25);
		panRight.add(comYear);
		
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
		EquipDTO dto = null;
		EquipDAO dao = null;
		String no;
		String name;
		String manuf;
		String textu;
		String year;
		
		switch (e.getActionCommand()) {

		case "초기화":
			tfNo.setText("");
			comJname.setSelectedIndex(0);
			tfManuf.setText("");
			tfTextu.setText("");
			comYear.setSelectedIndex(0);
			break;
			
		case "저장":
			no = tfNo.getText();
			name = (String) comJname.getSelectedItem();
			manuf = tfManuf.getText();
			textu = tfTextu.getText();
			year = (String) comYear.getSelectedItem();

			dto = new EquipDTO(no, name, manuf, textu, year);
			dao = new EquipDAO();
			dao.insert(dto);
			// System.out.println(dto.toString());

			model.setDataVector(dao.getListVector(), dao.getTableHeader());
			btnInit.doClick();
			break;
			
		case "수정":
			no = tfNo.getText();
			name = comJname.getSelectedItem().toString();
			manuf = tfManuf.getText();
			textu = tfTextu.getText();
			year = comYear.getSelectedItem().toString();

			dto = new EquipDTO(no, name, manuf, textu, year);
			dao = new EquipDAO();
			dao.update(dto);
			// System.out.println(dto.toString());

			model.setDataVector(dao.getListVector(), dao.getTableHeader());
			btnInit.doClick();
			break;
			
		case "삭제":
			no = tfNo.getText();
			dao = new EquipDAO();
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
		
		EquipDAO dao = new EquipDAO();
		EquipDTO dto = dao.getEquipDTO(no);
		
		tfNo.setText(dto.getNo());
		comJname.setSelectedItem(dto.getName());
		tfManuf.setText(dto.getManuf());
		tfTextu.setText(dto.getTextu());
		comYear.setSelectedItem(dto.getYear());

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
