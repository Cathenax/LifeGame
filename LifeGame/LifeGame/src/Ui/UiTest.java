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
		 //nGrid[i][j].setBackground(Color.WHITE); //死则显示白色
		for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
            	int matrix[][]=graph.getMatrix();
                ui.nGrid[i][j].getBackground(); //初始时所有细胞均为死
                if(matrix[i][j]==0)
                Assert.assertEquals("找死细胞数错误", Color.WHITE, ui.nGrid[i][j].getBackground());
                if(matrix[i][j]==1)
                Assert.assertEquals("找活细胞数错误", Color.BLACK, ui.nGrid[i][j].getBackground());
            }
        }
	}

}
