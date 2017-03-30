package game.word2;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameWindow extends JFrame{
	JPanel[] page = new JPanel[2];
	
	
	public GameWindow() {
		page[0]= new LoginForm(this);
		page[1] = new GamePanel();
				
		add(page[0]);
		add(page[1]);
		
		setLayout(new FlowLayout());
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setPage(0);
	}
	
	//게임페이지로 이동
	public void setPage(int num){
		for(int i=0; i<page.length; i++){
			if(i==num){
				page[i].setVisible(true);
			}else{
			page[i].setVisible(false);
			}
		}
		pack();
		setLocationRelativeTo(null);		
	}

	

	public static void main(String[] args) {
		new GameWindow();

	}

}
