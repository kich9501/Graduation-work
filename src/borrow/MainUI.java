package borrow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class MainUI extends JFrame implements ActionListener{

	public MainUI() {
		this.setSize(300, 200);
		this.setTitle("���� �뿩 �ݳ� �ý���");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLayout(null);
		this.setResizable(false);

		JButton btnPlayer = new JButton("�մ�");
		btnPlayer.setBounds(10, 10, 160, 40);
		this.add(btnPlayer);
		btnPlayer.addActionListener(this);

		JButton btnGame = new JButton("����");
		btnGame.setBounds(10, 60, 160, 40);
		this.add(btnGame);
		btnGame.addActionListener(this);

		JButton btnBorrow = new JButton("�뿩 / �ݳ�");
		btnBorrow.setBounds(10, 110, 160, 40);
		this.add(btnBorrow);
		btnBorrow.addActionListener(this);

		this.setVisible(true);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MainUI();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		switch(e.getActionCommand()) {
		case "�մ�":
			new PlayerUI();
			break;
		case "����":
			new EquipUI();
			break;
		case "�뿩 / �ݳ�":
			new BorrowUI();
			break;
		}
	}

}
