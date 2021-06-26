package Graph;

import static org.junit.Assert.*;
import static org.junit.Assume.assumeNotNull;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class GraphTest {

	private Graph graph=new Graph();
	@Before
	public void setUp() throws Exception {
	}
	@Test
	public void testWidth() {
		//fail("��δʵ��");
		Assert.assertEquals("Width����", 35, graph.getWidth());
	}
	
	@Test
	public void testHeight() {
		//fail("��δʵ��");
		Assert.assertEquals("Height����", 20, graph.getHeight());
	}

	@Test
	public void testCreatGraph() {
		//fail("��δʵ��");
		Assert.assertNotNull("����ͼʧ��",graph.getMatrix());
	}

	@Ignore@Test
	public void testUpdateGraph() {
		fail("��δʵ��");
	}

	@Test
	public void testDeleteGraph() {
		graph.DeleteGraph();
		int[][] test=graph.getMatrix();
		Assert.assertEquals("ͼδ���", 0, test[1][0]);
		//fail("��δʵ��");
	}

	@Test
	public void testgetseHeight() {
		//fail("��δʵ��");
		graph.setHeight(15);
		Assert.assertEquals("Hight����", 15, graph.getHeight());
	}
	@Test
	public void testgetsetWidth() {
		//fail("��δʵ��");
		graph.setWidth(15);
		Assert.assertEquals("Width����", 15, graph.getWidth());
	}

}
