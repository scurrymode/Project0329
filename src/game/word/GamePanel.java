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
	JPanel p_west; //왼쪽 컨트롤 영역
	JPanel p_center; //단어 그래픽 처리 영역
	JLabel la_user;//게임 로그인 유저명
	JLabel la_score;//게임점수
	Choice choice;
	JTextField t_input;//게임 입력창
	JButton bt_start;//게임시작
	JButton bt_pause;//게임정지
	JButton bt_stop;//게임종료
	GameWindow gamewindow;
	String res ="C:/java_workspace2/Project0329/res";
	FileInputStream fis;
	InputStreamReader reader; 
	BufferedReader buffr; //문자기반 버퍼 스트림
	ArrayList<String> wordList = new ArrayList<String>();  //조사한 단어를 담아놓자 게임에 써먹기 위해
	ArrayList<Word> words = new ArrayList<Word>();
	Thread thread;//단어게임을 진행할 스레드
	Word word;
	boolean flag=true;
	boolean isDown=true;
	
	public GamePanel(GameWindow gamewindow) {
		p_west = new JPanel();
		//이 영역은 지금부터 그림을 그릴 영역이다
		p_center= new JPanel(){
			public void paintComponent(Graphics g) {
				//기존그림 지우기
				g.setColor(Color.white);
				g.fillRect(0, 0, 750, 700);
				g.setColor(Color.blue);
				//모든 워드들에 대한 render();호출
				for(int i=0;i<words.size();i++){
					words.get(i).render(g);
				}
			}
		};
		this.gamewindow=gamewindow;
		setLayout(new BorderLayout());
		setBackground(Color.MAGENTA);
		setPreferredSize(new Dimension(900, 700));
		la_user  =new JLabel("최지민 님");
		la_score = new JLabel("0점");
		choice = new Choice();
		t_input = new JTextField(12);
		bt_start = new JButton("start");
		bt_pause =new JButton("pause");
		bt_stop =new JButton("종료");
		
		choice.add("▼단어 선택");
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
		
		//버튼과 리스너 연결
		bt_start.addActionListener(this);
		bt_pause.addActionListener(this);
		bt_stop.addActionListener(this);
		
		//텍스트 필드와 리스너 연결
		t_input.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
					//화면에 존재하는 words와 입력값 비교하여  같으면 words에서 객체 삭제
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
		setVisible(false);//최초 안보임
		getCategory();
	}
	//초이스 컴포넌트에 채워질 파일명 조사하기
	public void getCategory(){
		
		File file = new File(res);
		//파일 디렉토리 섞여있는 배열 반환
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
		//디렉토리만 골라내자
		for(int i=0;i<fileList.length;i++){
			if(fileList[i].isFile()){
				dirList.add(fileList[i].getName());
				if(dirList.get(i).substring(dirList.get(i).length()-3, dirList.get(i).length()).equals("txt")){
					choice.add(dirList.get(i).substring(0, dirList.get(i).length()-4));
				}
			}
		}
	}
	//단어읽어오기
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
	//준비된 단어를 화면에 보여주자 Word 객체로 만든 이유는 총알처럼 y값이 각각 내려오고 사라지고 할 걸 생각하려고~ㅋ	
	public void createWord(){
		for(int i=0;i<wordList.size();i++){
			Word word = new Word(wordList.get(i),i*(75)+10,100);
			//워드 객체명단 만들기
			words.add(word);
		}
	}
	
	//게임시작
	public void startGame(){
		if(thread==null){
			flag=true; //스타트누를때 while문 작동하도록
			
			thread = new Thread(this);
			thread.start();
		}
	}
	//게임 중지or계속
	public void pauseGame(){
		isDown=!isDown;
	}
	//게임종료--처음으로 돌아가자!
	/*1. workList(단어들이 들어있는) 비우기
	 *2. words(Word 인스턴스들이 들어있는) 비우기
	 *3. choice 초기화 (index=0)
	 *4. flag=false
	 *5. thread를 null로 다시 초기화
	*/
	public void stopGame(){
		wordList.removeAll(wordList);
		words.removeAll(words);
		choice.select(0); //첫번째 요소 강제 선택
		flag=false; //while문 중지 목적
		thread=null; //thread 초기화		
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
				//모든 단어들에 대해서 tick();
				for(int i=0;i<words.size();i++){
					words.get(i).tick();
				}
				p_center.repaint();
			}
		}
	}
}
