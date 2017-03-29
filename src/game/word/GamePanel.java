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
	JPanel p_west; //왼쪽 컨트롤 영역
	JPanel p_center; //단어 그래픽 처리 영역
	
	JLabel la_user; //게임 로그인 유저명
	JLabel la_score; //게임 점수
	Choice choice; //단어 선택 드랍박스
	JTextField t_input; //단어 입력창
	JButton bt_start; //게임 시작 버튼
	JButton bt_pause; //게임 정지 버튼
	String res = "C:/java_workspace2/Project0329/res/";
	
	FileInputStream fis;
	InputStreamReader reader; //파일을 대상으로 하는 문자스트림
	BufferedReader buffr; //문자기반 버퍼 스트림
	
	//조사한 단어를 담아놓자! 게임에 써먹기 위해!
	ArrayList<String> wordList=new ArrayList<String>();
	
		
	
	public GamePanel(GameWindow gameWindow) {
		this.gameWindow=gameWindow;
		setLayout(new BorderLayout());
		
		p_west = new JPanel();
		p_center = new JPanel(){
			//이 영역은 지금부터 그림을 그릴 영역이다!
			
			public void paint(Graphics g) {
				g.drawString("고등어", 200, 500);
			}
		};
	
		la_user = new JLabel("최지민 님");
		la_score = new JLabel("0점");
		choice = new Choice();
		t_input = new JTextField(10);
		bt_start = new JButton("Start");
		bt_pause = new JButton("Pause");
		
		choice.setPreferredSize(new Dimension(135, 40));
		choice.add("▼카테고리 선택");
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
		
		setVisible(false);//최초에 등장안함
		setPreferredSize(new Dimension(900,700));
		setBackground(Color.PINK);
		getCategory();
				
	}
	
	//초이스 컴포넌트에 채워질 파일명 조사하기
	public void getCategory(){
		File file = new File(res);
			
		File[] files=file.listFiles();
		for(int i =0; i<files.length;i++){
			if(files[i].isFile()){
				String name= files[i].getName(); //파일의 이름을 읽어서
				String[] arr = name.split("\\."); //. 기준으로 이름을 나눠서
				if(arr[1].equals("txt")){ //뒤가 텍스트면 앞에만 추가
					choice.add(name);
				}
			}
		}
	}
	
	//단어 읽어 오기
	public void getWord(){
		int index=choice.getSelectedIndex();
		
		if(index!=0){//첫번째 요소는 빼고,
			String name=choice.getSelectedItem();
			try {
				fis= new FileInputStream(res+name);
				reader = new InputStreamReader(fis,"utf-8");
				
				//스트림을 버퍼 처리 수준까지 올림!!
				buffr = new BufferedReader(reader); //애초에 txt파일에 우리가 엔터를 해놔서 하나씩 인식한당~!
				String data;
				while(true){
					data = buffr.readLine();//한줄 읽기
					if(data==null)break;
					//워드리스트에 보관
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
