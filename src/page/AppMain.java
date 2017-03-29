package page;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class AppMain extends JFrame implements ActionListener{
	JButton[] menu=new JButton[3];
	JPanel p_north, p_center;
	URL[] url= new URL[3];
	String[] path={"/login.png", "/content.png", "/etc.png" };
	JPanel[] page=new JPanel[3];
	
	//���������� ���� �Ѵ�.
	LoginForm loginForm;
	Content content;
	Etc etc;
	
	
	
	public AppMain() {
		p_north = new JPanel();
		p_center = new JPanel();
		page[0] = new LoginForm();//�α��� �� ����
		page[1] = new Content();//����Ʈ ����
		page[2] = new Etc();//��Ÿ����
		
		
		for(int i =0; i<path.length;i++){
			url[i] = this.getClass().getResource(path[i]);
			menu[i]=new JButton(new ImageIcon(url[i]));
			p_north.add(menu[i]);
			menu[i].addActionListener(this);
		}
		
		add(p_north, BorderLayout.NORTH);
		
		p_center.add(page[0]);
		p_center.add(page[1]);
		p_center.add(page[2]);
		add(p_center);
		
		
		setSize(700,600);
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	
	}
	
	
	public void actionPerformed(ActionEvent e) {
		Object obj=e.getSource();
		for(int i=0; i<page.length;i++){
			if(obj==menu[i]){
				page[i].setVisible(true);
			}else{
				page[i].setVisible(false);
			}
		}
	}

	public static void main(String[] args) {
		new AppMain();
	}
}
