/*
 * ���ӿ� ������ ��� �ܾ ���� y���� ���� ����, �뷮���� ��������� �ϱ� ������
 * �ᱹ ���뼺�� ���� �ڵ����� Ŭ������ ����!
 * */

package game.word;

import java.awt.Graphics;

public class Word {
	String name; //�� ��ü�� ��Ե� �ܾ�!!
	int x;
	int y;
	int velX;
	int velY;// �ܾ ������ �ӵ�
	
	//�� �ܾ� �ʱ�ȭ
	public Word(String name, int x, int y) {
		this.name=name;
		this.x=x;
		this.y=y;
	}
	
	//��ü�� �ݿ��� ������ ��ȭ�ڵ�
	public void tick(){
		y+=5;
	}
	
	public void render(Graphics g){
		g.drawString(name, x, y);
	}
	
	
}