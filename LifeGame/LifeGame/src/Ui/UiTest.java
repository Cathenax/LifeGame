package Ui;

import static org.junit.Assert.*;

import java.awt.Color;

import javax.swing.JButton;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import Graph.Graph;

public class UiTest {

	Ui ui=new Ui("test");
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		Graph graph=ui.graph;
		graph.CreatGraph();
		ui.showCell();
		 //nGrid[i][j].setBackground(Color.WHITE); //������ʾ��ɫ
		for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
            	int matrix[][]=graph.getMatrix();
                ui.nGrid[i][j].getBackground(); //��ʼʱ����ϸ����Ϊ��
                if(matrix[i][j]==0)
                Assert.assertEquals("����ϸ��������", Color.WHITE, ui.nGrid[i][j].getBackground());
                if(matrix[i][j]==1)
                Assert.assertEquals("�һ�ϸ��������", Color.BLACK, ui.nGrid[i][j].getBackground());
            }
        }
	}

}
