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
		//fail("尚未实现");
		Assert.assertEquals("Width错误", 35, graph.getWidth());
	}
	
	@Test
	public void testHeight() {
		//fail("尚未实现");
		Assert.assertEquals("Height错误", 20, graph.getHeight());
	}

	@Test
	public void testCreatGraph() {
		//fail("尚未实现");
		Assert.assertNotNull("创建图失败",graph.getMatrix());
	}

	@Ignore@Test
	public void testUpdateGraph() {
		fail("尚未实现");
	}

	@Test
	public void testDeleteGraph() {
		graph.DeleteGraph();
		int[][] test=graph.getMatrix();
		Assert.assertEquals("图未清空", 0, test[1][0]);
		//fail("尚未实现");
	}

	@Test
	public void testgetseHeight() {
		//fail("尚未实现");
		graph.setHeight(15);
		Assert.assertEquals("Hight错误", 15, graph.getHeight());
	}
	@Test
	public void testgetsetWidth() {
		//fail("尚未实现");
		graph.setWidth(15);
		Assert.assertEquals("Width错误", 15, graph.getWidth());
	}

}
