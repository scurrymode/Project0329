/*�α��� ȭ���� ����� Ŭ���� ����!!*/

package page;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginForm extends JPanel {
	JPanel p_container; //BorderLayout ���뿹��
	JPanel p_center; //GridLayout ���뿹��
	JPanel p_south; //���ʿ� ��ư �� ����
	JLabel la_id, la_pw;
	JTextField t_id;
	JPasswordField t_pw;
	JButton bt;
	
	
	public LoginForm() {
		p_container = new JPanel();
		p_center = new JPanel();
		p_south = new JPanel();
		la_id= new JLabel("ID");
		la_pw = new JLabel("Password");
		t_id = new JTextField(15);
		t_pw = new JPasswordField(15);
		bt = new JButton("���");
		
		p_container.setLayout(new BorderLayout());
		
		
		p_center.setLayout(new GridLayout(2, 2));
		p_center.add(la_id);
		p_center.add(t_id);
		p_center.add(la_pw);
		p_center.add(t_pw);
		p_south.add(bt);

		p_container.add(p_center);
		p_container.add(p_south, BorderLayout.SOUTH);
		
		add(p_container);
		setBackground(Color.YELLOW);
		setPreferredSize(new Dimension(700,500));
		
	}

}
