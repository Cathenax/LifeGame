package Logic;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import Graph.Graph;

public class LogicTest {

	Logic logic=new Logic();
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testFindLifedNum() {
		//fail("��δʵ��");
		Graph graph=new Graph();
		graph.CreatGraph();
		int matrix[][]= {{1,1,1,1},{1,1,1,1},{1,1,1,1},{1,1,1,1}};
		graph.setMatrix(matrix);
		int test=logic.findLifedNum(2, 2, matrix,graph);
		Assert.assertEquals("�һ�ϸ��������", 8, test);
	}



}
