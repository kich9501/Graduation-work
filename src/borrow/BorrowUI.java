package borrow;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class BorrowUI extends JFrame implements ActionListener, MouseListener{
	
	DefaultTableModel model_play;
	JTable table_play;
	
	DefaultTableModel model_equip;
	JTable table_equip;
	
	DefaultTableModel model_borrow;
	JTable table_borrow;
	
	BorrowDAO dao_borrow;
	String no;
	
	EquipDAO dao_equip;
	LendEquipDAO dao_lendequip;
	
	public BorrowUI() {
		this.setSize(1400, 600);
		this.setTitle("´ë¿©/¹Ý³³");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLayout(null);
		this.setResizable(false);
		
		JPanel panLeft = new JPanel();
		panLeft.setBounds(10, 40, 750, 510);
		panLeft.setBackground(Color.DARK_GRAY);
		panLeft.setLayout(null);
		this.add(panLeft);
		
		PlayerDAO dao_play = new PlayerDAO();
		Vector<Object> vecs_play = dao_play.getListVector();
		Vector<Object> cols_play = dao_play.getTableHeader();
		
		model_play = new DefaultTableModel(vecs_play, cols_play);
		table_play = new JTable(model_play);
		JScrollPane scroll_play = new JScrollPane(table_play);
		scroll_play.setBounds(10, 10, 730, 200);
		panLeft.add(scroll_play);
		table_play.addMouseListener(this);
		
		EquipDAO dao_equip = new EquipDAO();
		Vector<Object> vecs_equip = dao_equip.getListVector();
		Vector<Object> cols_equip = dao_equip.getTableHeader();
		
		model_equip = new DefaultTableModel(vecs_equip, cols_equip);
		table_equip = new JTable(model_equip);
		JScrollPane scroll_equip = new JScrollPane(table_equip);
		scroll_equip.setBounds(10, 220, 730, 280);
		panLeft.add(scroll_equip);
		
		JPanel panRight = new JPanel();
		panRight.setBounds(780, 40, 605, 510);
		panRight.setBackground(Color.GRAY);
		panRight.setLayout(null);
		this.add(panRight);
		
		LendEquipDAO dao_borrow = new LendEquipDAO();
		Vector<Object> vecs_borrow = dao_borrow.getListVector();
		Vector<Object> cols_borrow = dao_borrow.getTableHeader();
		
		model_borrow = new DefaultTableModel(vecs_borrow, cols_borrow);
		table_borrow = new JTable(model_borrow);
		JScrollPane scroll_borrow = new JScrollPane(table_borrow);
		scroll_borrow.setBounds(10, 10, 585, 450);
		panRight.add(scroll_borrow);
		
		JButton btnBorrow = new JButton("´ë¿©");
		btnBorrow.setBounds(10, 470, 100, 25);
		panRight.add(btnBorrow);
		btnBorrow.addActionListener(this);
		
		JButton btnReturn = new JButton("¹Ý³³");
		btnReturn.setBounds(120, 470, 100, 25);
		panRight.add(btnReturn);
		btnReturn.addActionListener(this);
		
		this.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		switch (e.getActionCommand()) {
		case "´ë¿©":
			int row_play = table_play.getSelectedRow();
			int row_equip = table_equip.getSelectedRow();
			if (row_play >= 0 && row_equip >= 0) {
				String bno = table_equip.getValueAt(row_equip, 0).toString();
				String mno = table_play.getValueAt(row_play, 0).toString();
				BorrowDTO dto = new BorrowDTO();
				dto.setBno(bno);
				dto.setMno(mno);
				dto.setDays(50);

				dao_borrow = new BorrowDAO();
				dao_borrow.insert(dto);

				dao_lendequip = new LendEquipDAO();
				model_borrow.setDataVector(dao_lendequip.getListVector(mno), dao_lendequip.getTableHeader());

				dao_equip = new EquipDAO();
				model_equip.setDataVector(dao_equip.getListVector(), dao_equip.getTableHeader());
			}
			break;

		case "¹Ý³³":
			int[] rows_borrow = table_borrow.getSelectedRows();
			String[] nos_borrow = new String[rows_borrow.length];

			for (int i = 0; i < rows_borrow.length; i++)
				nos_borrow[i] = table_borrow.getValueAt(rows_borrow[i], 0).toString();

			dao_borrow = new BorrowDAO();
			dao_borrow.delete(nos_borrow);

			dao_lendequip = new LendEquipDAO();
			model_borrow.setDataVector(dao_lendequip.getListVector(), dao_lendequip.getTableHeader());

			dao_equip = new EquipDAO();
			model_equip.setDataVector(dao_equip.getListVector(), dao_equip.getTableHeader());
			break;
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		LendEquipDAO dao_lendequip;
		String mno = "";
		
		if(e.getSource().equals(table_play)) {
			int row_play = table_play.getSelectedRow();
			
			if(row_play >= 0) {
			mno = table_play.getValueAt(row_play, 0).toString();
			
			dao_lendequip = new LendEquipDAO();
			model_borrow.setDataVector(
					dao_lendequip.getListVector(mno),
					dao_lendequip.getTableHeader());
			}
		}
		
		if (e.getSource().equals(table_equip)) {

		}

		if (e.getSource().equals(table_equip)) {

		}
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
