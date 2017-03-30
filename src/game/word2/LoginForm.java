package game.word2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginForm extends JPanel implements ActionListener{
	GameWindow gameWindow;
	JPanel p_center;
	JPanel p_south;
	JButton bt;
	JLabel la_id, la_pw;
	JTextField t_id;
	JPasswordField t_pw;
	
	public LoginForm(GameWindow gameWindow) {
		this.gameWindow=gameWindow;
		setLayout(new BorderLayout());
		
		
		p_center = new JPanel();
		p_south = new JPanel();
		bt = new JButton("로그인");
		la_id = new JLabel("ID");
		la_pw = new JLabel("PW");
		t_id = new JTextField("batman", 15);
		t_pw = new JPasswordField("1234", 15);
		
		p_center.setLayout(new GridLayout(2, 2));

		
		p_center.add(la_id);
		p_center.add(t_id);
		p_center.add(la_pw);
		p_center.add(t_pw);
		p_south.add(bt);
		
		add(p_center);
		add(p_south, BorderLayout.SOUTH);
		
		bt.addActionListener(this);
		
		
		setPreferredSize(new Dimension(400, 100));
		setBackground(Color.CYAN);
	}
	
	public void actionPerformed(ActionEvent e) {
		String id = t_id.getText();
		String pw = new String(t_pw.getPassword());
		
		if((id.equals("batman"))&&(pw.equals("1234"))){
			JOptionPane.showMessageDialog(this, "환영합니다");
			gameWindow.setPage(1);
		}else{
			JOptionPane.showMessageDialog(this, "다시 시도하세요");
		}
		
	}

}
