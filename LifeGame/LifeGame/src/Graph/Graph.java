package Graph;
import Logic.Logic;

public class Graph {
	Logic logic=new Logic();
	private int height=20;
	private int width=35;
	private int[][] matrix=new int[height][width];
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int[][] getMatrix() {
		return matrix;
	}
	public void setMatrix(int[][] matrix) {
		this.matrix = matrix;
	}
	//初始化地图
    public void CreatGraph()
    {
	    logic.createCaseFile(this);
    }
    //更新地图
    public void UpdateGraph()
    {
    	logic.transform(this);
    }
    //清空地图
    public void DeleteGraph()
    {
	    for (int y = 0; y < height; y++) {
	        for (int x = 0; x < width; x++) {
	        	matrix[y][x]=0;//细胞清空
	        }
	    }
    }
}


