package game.word;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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

public class GamePanel extends JPanel implements ItemListener , Runnable, ActionListener{
	JPanel p_west; //���� ��Ʈ�� ����
	JPanel p_center; //�ܾ� �׷��� ó�� ����
	JLabel la_user;//���� �α��� ������
	JLabel la_score;//��������
	Choice choice;
	JTextField t_input;//���� �Է�â
	JButton bt_start;//���ӽ���
	JButton bt_pause;//��������
	JButton bt_stop;//��������
	GameWindow gamewindow;
	String res ="C:/java_workspace2/Project0329/res";
	FileInputStream fis;
	InputStreamReader reader; 
	BufferedReader buffr; //���ڱ�� ���� ��Ʈ��
	ArrayList<String> wordList = new ArrayList<String>();  //������ �ܾ ��Ƴ��� ���ӿ� ��Ա� ����
	ArrayList<Word> words = new ArrayList<Word>();
	Thread thread;//�ܾ������ ������ ������
	Word word;
	boolean flag=true;
	boolean isDown=true;
	
	public GamePanel(GameWindow gamewindow) {
		p_west = new JPanel();
		//�� ������ ���ݺ��� �׸��� �׸� �����̴�
		p_center= new JPanel(){
			public void paintComponent(Graphics g) {
				//�����׸� �����
				g.setColor(Color.white);
				g.fillRect(0, 0, 750, 700);
				g.setColor(Color.blue);
				//��� ����鿡 ���� render();ȣ��
				for(int i=0;i<words.size();i++){
					words.get(i).render(g);
				}
			}
		};
		this.gamewindow=gamewindow;
		setLayout(new BorderLayout());
		setBackground(Color.MAGENTA);
		setPreferredSize(new Dimension(900, 700));
		la_user  =new JLabel("������ ��");
		la_score = new JLabel("0��");
		choice = new Choice();
		t_input = new JTextField(12);
		bt_start = new JButton("start");
		bt_pause =new JButton("pause");
		bt_stop =new JButton("����");
		
		choice.add("��ܾ� ����");
		choice.addItemListener(this);
		choice.setPreferredSize(new Dimension(135, 40));
		t_input.setPreferredSize(new Dimension(135, 40));
		p_west.setPreferredSize(new Dimension(150, 700));
		p_west.setBackground(Color.CYAN);
		p_west.add(la_user);
		p_west.add(choice);
		p_west.add(t_input);
		p_west.add(bt_start);
		p_west.add(bt_pause);
		p_west.add(bt_stop);
		p_west.add(la_score);
		
		//��ư�� ������ ����
		bt_start.addActionListener(this);
		bt_pause.addActionListener(this);
		bt_stop.addActionListener(this);
		
		//�ؽ�Ʈ �ʵ�� ������ ����
		t_input.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
					//ȭ�鿡 �����ϴ� words�� �Է°� ���Ͽ�  ������ words���� ��ü ����
					String value = t_input.getText();
					t_input.setText("");
					for (int i =0; i<words.size();i++){
						if(words.get(i).name.equals(value)){
							words.remove(i);
						}
					}
				}
			}
		});
		
		add(p_center);
		add(p_west,BorderLayout.WEST);
		setVisible(false);//���� �Ⱥ���
		getCategory();
	}
	//���̽� ������Ʈ�� ä���� ���ϸ� �����ϱ�
	public void getCategory(){
		
		File file = new File(res);
		//���� ���丮 �����ִ� �迭 ��ȯ
		File[] fileList = file.listFiles();
		/*
		for(int i=0;i<fileList.length;i++){
			if(fileList[i].isFile()){
				String name=fileList[i].getName();
				String[] arr = name.split("\\.");
				if(arr[1].equals("txt")){
					choice.add(name);
				}
			}
		}*/
		ArrayList<String> dirList = new ArrayList<String>();
		//���丮�� �����
		for(int i=0;i<fileList.length;i++){
			if(fileList[i].isFile()){
				dirList.add(fileList[i].getName());
				if(dirList.get(i).substring(dirList.get(i).length()-3, dirList.get(i).length()).equals("txt")){
					choice.add(dirList.get(i).substring(0, dirList.get(i).length()-4));
				}
			}
		}
	}
	//�ܾ��о����
	public void getWord(){
		try {
			int index = choice.getSelectedIndex();
				if(index!=0){
					wordList.removeAll(wordList);
					words.removeAll(words);
					fis = new FileInputStream(res+"/"+choice.getSelectedItem()+".txt");
					reader =new InputStreamReader(fis,"utf-8");
					buffr =new BufferedReader(reader);
					String data;
						
					while(true){
						data = buffr.readLine();
						if(data==null)break;
						wordList.add(data);
					}createWord();
				}
			}catch (FileNotFoundException e) {
				e.printStackTrace();
			}catch (IOException e) {
				e.printStackTrace();
			}finally{
				if(buffr!= null){
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
						e.printStackTrace();
					}
				}
				if(fis!=null){
					try {
						fis.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
	} 
	//�غ�� �ܾ ȭ�鿡 �������� Word ��ü�� ���� ������ �Ѿ�ó�� y���� ���� �������� ������� �� �� �����Ϸ���~��	
	public void createWord(){
		for(int i=0;i<wordList.size();i++){
			Word word = new Word(wordList.get(i),i*(75)+10,100);
			//���� ��ü��� �����
			words.add(word);
		}
	}
	
	//���ӽ���
	public void startGame(){
		if(thread==null){
			flag=true; //��ŸƮ������ while�� �۵��ϵ���
			
			thread = new Thread(this);
			thread.start();
		}
	}
	//���� ����or���
	public void pauseGame(){
		isDown=!isDown;
	}
	//��������--ó������ ���ư���!
	/*1. workList(�ܾ���� ����ִ�) ����
	 *2. words(Word �ν��Ͻ����� ����ִ�) ����
	 *3. choice �ʱ�ȭ (index=0)
	 *4. flag=false
	 *5. thread�� null�� �ٽ� �ʱ�ȭ
	*/
	public void stopGame(){
		wordList.removeAll(wordList);
		words.removeAll(words);
		choice.select(0); //ù��° ��� ���� ����
		flag=false; //while�� ���� ����
		thread=null; //thread �ʱ�ȭ		
	}
	
	public void itemStateChanged(ItemEvent e) {
		getWord();
	}
	public void actionPerformed(ActionEvent e) {
		Object obj= e.getSource();
		
		if(obj==bt_start){
			startGame();
		}else if(obj==bt_pause){
			pauseGame();
		}else if(obj==bt_stop){
			stopGame();
		}
	}
	public void run() {
		while(flag){
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(isDown){
				//��� �ܾ�鿡 ���ؼ� tick();
				for(int i=0;i<words.size();i++){
					words.get(i).tick();
				}
				p_center.repaint();
			}
		}
	}
}
