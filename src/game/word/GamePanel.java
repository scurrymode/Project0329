package game.word;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GamePanel extends JPanel implements ItemListener{
	GameWindow gameWindow;
	JPanel p_west; //���� ��Ʈ�� ����
	JPanel p_center; //�ܾ� �׷��� ó�� ����
	
	JLabel la_user; //���� �α��� ������
	JLabel la_score; //���� ����
	Choice choice; //�ܾ� ���� ����ڽ�
	JTextField t_input; //�ܾ� �Է�â
	JButton bt_start; //���� ���� ��ư
	JButton bt_pause; //���� ���� ��ư
	String res = "C:/java_workspace2/Project0329/res/";
	
	FileInputStream fis;
	InputStreamReader reader; //������ ������� �ϴ� ���ڽ�Ʈ��
	BufferedReader buffr; //���ڱ�� ���� ��Ʈ��
	
	//������ �ܾ ��Ƴ���! ���ӿ� ��Ա� ����!
	ArrayList<String> wordList=new ArrayList<String>();
	
		
	
	public GamePanel(GameWindow gameWindow) {
		this.gameWindow=gameWindow;
		setLayout(new BorderLayout());
		
		p_west = new JPanel();
		p_center = new JPanel(){
			//�� ������ ���ݺ��� �׸��� �׸� �����̴�!
			
			public void paint(Graphics g) {
				g.drawString("����", 200, 500);
			}
		};
	
		la_user = new JLabel("������ ��");
		la_score = new JLabel("0��");
		choice = new Choice();
		t_input = new JTextField(10);
		bt_start = new JButton("Start");
		bt_pause = new JButton("Pause");
		
		choice.setPreferredSize(new Dimension(135, 40));
		choice.add("��ī�װ� ����");
		choice.addItemListener(this);
		
		
		p_west.setPreferredSize(new Dimension(150, 700));
		p_west.setBackground(Color.YELLOW);
		p_west.add(la_user);
		p_west.add(choice);
		p_west.add(t_input);
		p_west.add(bt_start);
		p_west.add(bt_pause);
		p_west.add(la_score);
		
		add(p_west, BorderLayout.WEST);
		add(p_center);
		
		setVisible(false);//���ʿ� �������
		setPreferredSize(new Dimension(900,700));
		setBackground(Color.PINK);
		getCategory();
				
	}
	
	//���̽� ������Ʈ�� ä���� ���ϸ� �����ϱ�
	public void getCategory(){
		File file = new File(res);
			
		File[] files=file.listFiles();
		for(int i =0; i<files.length;i++){
			if(files[i].isFile()){
				String name= files[i].getName(); //������ �̸��� �о
				String[] arr = name.split("\\."); //. �������� �̸��� ������
				if(arr[1].equals("txt")){ //�ڰ� �ؽ�Ʈ�� �տ��� �߰�
					choice.add(name);
				}
			}
		}
	}
	
	//�ܾ� �о� ����
	public void getWord(){
		int index=choice.getSelectedIndex();
		
		if(index!=0){//ù��° ��Ҵ� ����,
			String name=choice.getSelectedItem();
			try {
				fis= new FileInputStream(res+name);
				reader = new InputStreamReader(fis,"utf-8");
				
				//��Ʈ���� ���� ó�� ���ر��� �ø�!!
				buffr = new BufferedReader(reader); //���ʿ� txt���Ͽ� �츮�� ���͸� �س��� �ϳ��� �ν��Ѵ�~!
				String data;
				while(true){
					data = buffr.readLine();//���� �б�
					if(data==null)break;
					//���帮��Ʈ�� ����
					wordList.add(data);
					
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally{
				if(buffr!=null){
					try {
						buffr.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if(reader!=null){
					try {
						reader.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(fis!=null){
					try {
						fis.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
		
			}
		}
		
	}

	public void itemStateChanged(ItemEvent e) {
		getWord();
	}

}
