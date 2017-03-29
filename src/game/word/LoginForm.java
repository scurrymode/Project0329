/*로그인 화면을 담당할 클래스 정의!!*/

package game.word;

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
	GameWindow gameWindow;//언제든 컨테이너에 접근할 수 있도록
	JPanel p_container; //BorderLayout 적용예정
	JPanel p_center; //GridLayout 적용예정
	JPanel p_south; //남쪽에 버튼 들어갈 예정
	JLabel la_id, la_pw;
	JTextField t_id;
	JPasswordField t_pw;
	JButton bt;
	

	public LoginForm(GameWindow gameWindow) {
		this.gameWindow=gameWindow;
		p_container = new JPanel();
		p_center = new JPanel();
		p_south = new JPanel();
		la_id= new JLabel("ID");
		la_pw = new JLabel("Password");
		t_id = new JTextField("batman", 15);
		t_pw = new JPasswordField("1234", 15);
		bt = new JButton("로그인");
		
		p_container.setLayout(new BorderLayout());
		
		
		p_center.setLayout(new GridLayout(2, 2));
		p_center.add(la_id);
		p_center.add(t_id);
		p_center.add(la_pw);
		p_center.add(t_pw);
		p_south.add(bt);

		p_container.add(p_center);
		p_container.add(p_south, BorderLayout.SOUTH);
		
		bt.addActionListener(this);
		
		add(p_container);
		setBackground(Color.YELLOW);
		setPreferredSize(new Dimension(400,100));
		
	}
	
	public void loginCheck(){
		String id=t_id.getText();
		String pw=new String(t_pw.getPassword());//char[]을 주기때문에 이건 String 생성자중에 String(char[]);를 만든다.
		if(id.equals("batman")&& pw.equals("1234")){
			JOptionPane.showMessageDialog(this, "로그인 성공");
			gameWindow.setPage(1);
		}else{
			JOptionPane.showMessageDialog(this, "로그인 정보가 올바르지 않습니다.");
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		loginCheck();			
	}

}
