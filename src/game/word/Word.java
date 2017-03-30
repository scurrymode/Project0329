/*
 * 게임에 등장할 대상 단어가 각각 y축을 따로 갖고, 대량으로 만들어져야 하기 때문에
 * 결국 재사용성을 위한 코드집합 클래스로 가자!
 * */

package game.word;

import java.awt.Graphics;

public class Word {
	String name; //이 객체가 담게될 단어!!
	int x;
	int y;
	int velX;
	int velY;// 단어가 움직일 속도
	
	//이 단어 초기화
	public Word(String name, int x, int y) {
		this.name=name;
		this.x=x;
		this.y=y;
	}
	
	//객체에 반영될 데이터 변화코드
	public void tick(){
		y+=5;
	}
	
	public void render(Graphics g){
		g.drawString(name, x, y);
	}
	
	
}
