package game.word2;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GamePanel extends JPanel implements ItemListener{
	JPanel p_west, p_center;
	JLabel user;
	Choice choice;
	JTextField t_input;
	JButton bt_start, bt_pause;
	JLabel score;
	String res = "C:/java_workspace2/Project0329/res";
	
	
	public GamePanel() {
		setLayout(new BorderLayout());
		
		p_west = new JPanel();
		p_center = new JPanel();
		user = new JLabel("최지민님");
		choice = new Choice();
		t_input = new JTextField(10);
		bt_start = new JButton("시작");
		bt_pause = new JButton("정지");
		score = new JLabel("0점");
		choice.setPreferredSize(new Dimension(135,40));
		
		p_west.setPreferredSize(new Dimension(150, 700));
		p_center.setPreferredSize(new Dimension(750, 700));
		p_center.setBackground(Color.PINK);
		
		p_west.add(user);
		p_west.add(choice);
		p_west.add(t_input);
		p_west.add(bt_start);
		p_west.add(bt_pause);
		p_west.add(score);
		
		choice.addItemListener(this);
		
		add(p_west, BorderLayout.WEST);
		add(p_center);
		
		setPreferredSize(new Dimension(900,700));
		setBackground(Color.PINK);
		setVisible(false);
		
		createCategory();
	}
	
	public void createCategory(){
		File file = new File(res);
		File[] files=file.listFiles();
		choice.add("카테고리를 고르세요.");
		for(int i = 0; i<files.length;i++){
			if (files[i].isFile()){
				String name = files[i].getName();
				String[] arr = name.split("\\.");
				if(arr[1].equals("txt")){
					choice.add(name);
				}
			}
		}
	}

	public void itemStateChanged(ItemEvent e) {
			
	}

}
