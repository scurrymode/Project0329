package game.word;
//���ӿ� ������ ��� �ܾ ����y���� ���� ����, �뷮���� ��������� �ϱ� ������
//�ᱹ ���뼺�� ���� �ڵ������� Ŭ������ ����

import java.awt.Graphics;

public class Word {
	int y;
	int x;
	int velX;
	int velY; //�ܾ ������ �ӵ�
	String name;
	public Word(String name, int x, int y) {
		//�ܾ �¾�� ���߾���� �ڵ尪
		this.name=name;
		this.x=x;
		this.y=y;
	}
	
	//�� ��ü�� �ݿ��� ������ ��ȭ �ڵ�
	public void tick(){
		y+=5;
		
	}
	//�� �ݿ��� ����Ʋ �̿��Ͽ� ȭ�鿡 �׸���
	public void render(Graphics g){
		g.drawString(name, x, y);
	}
	
}
