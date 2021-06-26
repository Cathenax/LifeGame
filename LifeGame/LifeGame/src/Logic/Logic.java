package Logic;

import java.util.Random;

import Graph.Graph;


public class Logic {
	//设定1为活，0为死
	public static final int LIVE=1;
	public static final int DEAD=0;
	//public Random random = new Random();
	/**
	 * 上一个状态到下一个状态的转移
	 * 根据规则可以总结得出两条规则:
	 * 1. 对于周围活着的细胞为3的情况,下一个状态该细胞总是为活
	 * 2. 对于周围活着的细胞为2的情况,下一个状态与上一状态相同
	 */
	//Graph graph=new Graph();
	public void transform(Graph graph){
		int height=graph.getHeight();
		int width=graph.getWidth();
		int[][] matrix=graph.getMatrix();
		int[][] nextMatrix=new int[height][width];
	    for (int y = 0; y < matrix.length; y++) {
	        for (int x = 0; x < matrix[0].length; x++) {
	        	//小于等于1或大于等于4，则为死
	            nextMatrix[y][x]=DEAD;
	            int nearNum= findLifedNum(y,x,graph.getMatrix(),graph);
	            //等于3，则下一状态总是活
	            if(nearNum==3){
	                nextMatrix[y][x]=LIVE;
	            }
	            //等于2，则与上一状态一样
	            else if(nearNum==2){
	                nextMatrix[y][x]=matrix[y][x];
	            }
	        }
	    }
	    graph.setMatrix(nextMatrix);
	    
	}



	/**
	 * 统计每个细胞周围活着的个数
	 * @param x 横坐标
	 * @param y 纵坐标
	 * @return
	 */
	public int findLifedNum(int y, int x,int[][] matrix,Graph graph){
	    int num=0;
	    int height=graph.getHeight();
		int width=graph.getWidth();
	    /*//左边
	    if(x!=0){
	        num+=matrix[y][x-1];
	    }
	    //左上角
	    if(x!=0&&y!=0){
	        num+=matrix[y-1][x-1];
	    }
	    //上边
	    if(y!=0){
	        num+=matrix[y-1][x];
	    }
	    //右上
	    if(x!=graph.getWidth()-1&&y!=0){
	        num+=matrix[y-1][x+1];
	    }
	    //右边
	    if(x!=graph.getWidth()-1){
	        num+=matrix[y][x+1];
	    }
	    //右下
	    if(x!=graph.getWidth()-1&&y!=graph.getHeight()-1){
	        num+=matrix[y+1][x+1];
	    }
	    //下边
	    if(y!=graph.getHeight()-1){
	        num+=matrix[y+1][x];
	    }
	    //左下
	    if(x!=0&&y!=graph.getHeight()-1){
	        num+=matrix[y+1][x-1];
	    }*/
	  //左上
	    if(x==0&&y==0){
	        num+=matrix[y+1][x]+matrix[y][x+1]+matrix[y+1][x+1];
	    }
	    //右上
	    else if(x==width-1&&y==0) {
	    	num+=matrix[y-1][x]+matrix[y][x-1]+matrix[y-1][x-1];
	    }
	    //左下
	    else if(x==0&&y==height-1) {
	    	num+=matrix[y-1][x]+matrix[y][x+1]+matrix[y-1][x+1];
	    }
	    //右下
	    else if(x==width-1&&y==height-1) {
	    	num+=matrix[y-1][x]+matrix[y][x-1]+matrix[y-1][x-1];
	    }
	    //左边
	    else if(x==0) {
	    	num+=matrix[y-1][x]+matrix[y+1][x]+matrix[y-1][x+1]+matrix[y][x+1]+matrix[y+1][x+1];
	    }
	    //上边
	    else if(y==0) {
	    	num+=matrix[y][x-1]+matrix[y][x+1]+matrix[y+1][x-1]+matrix[y+1][x]+matrix[y+1][x+1];
	    }
	    //下边
	    else if(y==height-1) {
	    	num+=matrix[y][x-1]+matrix[y][x+1]+matrix[y-1][x-1]+matrix[y-1][x]+matrix[y-1][x+1];
	    }
	    //右边
	    else if(x==width-1) {
	    	num+=matrix[y-1][x]+matrix[y+1][x]+matrix[y-1][x-1]+matrix[y][x-1]+matrix[y+1][x-1];
	    }
	    //中间
	    else {
	    	num+=matrix[y-1][x]+matrix[y+1][x]+matrix[y-1][x-1]+matrix[y][x-1]+matrix[y+1][x-1]
	    			+matrix[y-1][x+1]+matrix[y][x+1]+matrix[y+1][x+1];
	    }
	    return num;
	}
	
	/**
     * 创建测试案例
     */
    public void createCaseFile(Graph graph) {

		int height=graph.getHeight();
		int width=graph.getWidth();
		int[][] matrix=graph.getMatrix();
        Random random = new Random();
            //开始逐行初始化
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {

                    if (random.nextInt(3) % 3 == 0) {
                    	matrix[y][x]=LIVE;
                    } else {
                    	
                    	matrix[y][x]=DEAD;
                    }
                }
            }
    }
    

}

