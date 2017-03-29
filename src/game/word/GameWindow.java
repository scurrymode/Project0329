package game.word;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

//�� ������� ũ�Ⱑ �����Ǿ� ���� �ʾƾ� �Ѵ�. ��? ������ �ȿ� �����Ե� �г��� �� ũ�⸦ �����ϰ� �ǹǷ�
public class GameWindow extends JFrame{
	JPanel[] page = new JPanel[2];
	
	public GameWindow() {
		setLayout(new FlowLayout());
		
		page[0] = new LoginForm(this);
		page[1] = new GamePanel(this);
		
		add(page[0]);
		add(page[1]);
		
		setPage(0);		
		
			
		
		setVisible(true);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	//������ �ȿ� � �г��� ������ �������ִ� �޼��� ����
	public void setPage(int index){
		for(int i =0; i<page.length;i++){
			if(i==index){
				page[i].setVisible(true);
				pack();
			}else{
				page[i].setVisible(false);
			}
			pack(); //���빰�� ũ�⸸ŭ ������ũ�⸦ �����Ѵ�!	
			setLocationRelativeTo(null);
		}
	}

	public static void main(String[] args) {
		new GameWindow();

	}

}
