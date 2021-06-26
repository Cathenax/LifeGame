package Logic;

import java.util.Random;

import Graph.Graph;


public class Logic {
	//�趨1Ϊ�0Ϊ��
	public static final int LIVE=1;
	public static final int DEAD=0;
	//public Random random = new Random();
	/**
	 * ��һ��״̬����һ��״̬��ת��
	 * ���ݹ�������ܽ�ó���������:
	 * 1. ������Χ���ŵ�ϸ��Ϊ3�����,��һ��״̬��ϸ������Ϊ��
	 * 2. ������Χ���ŵ�ϸ��Ϊ2�����,��һ��״̬����һ״̬��ͬ
	 */
	//Graph graph=new Graph();
	public void transform(Graph graph){
		int height=graph.getHeight();
		int width=graph.getWidth();
		int[][] matrix=graph.getMatrix();
		int[][] nextMatrix=new int[height][width];
	    for (int y = 0; y < matrix.length; y++) {
	        for (int x = 0; x < matrix[0].length; x++) {
	        	//С�ڵ���1����ڵ���4����Ϊ��
	            nextMatrix[y][x]=DEAD;
	            int nearNum= findLifedNum(y,x,graph.getMatrix(),graph);
	            //����3������һ״̬���ǻ�
	            if(nearNum==3){
	                nextMatrix[y][x]=LIVE;
	            }
	            //����2��������һ״̬һ��
	            else if(nearNum==2){
	                nextMatrix[y][x]=matrix[y][x];
	            }
	        }
	    }
	    graph.setMatrix(nextMatrix);
	    
	}



	/**
	 * ͳ��ÿ��ϸ����Χ���ŵĸ���
	 * @param x ������
	 * @param y ������
	 * @return
	 */
	public int findLifedNum(int y, int x,int[][] matrix,Graph graph){
	    int num=0;
	    int height=graph.getHeight();
		int width=graph.getWidth();
	    /*//���
	    if(x!=0){
	        num+=matrix[y][x-1];
	    }
	    //���Ͻ�
	    if(x!=0&&y!=0){
	        num+=matrix[y-1][x-1];
	    }
	    //�ϱ�
	    if(y!=0){
	        num+=matrix[y-1][x];
	    }
	    //����
	    if(x!=graph.getWidth()-1&&y!=0){
	        num+=matrix[y-1][x+1];
	    }
	    //�ұ�
	    if(x!=graph.getWidth()-1){
	        num+=matrix[y][x+1];
	    }
	    //����
	    if(x!=graph.getWidth()-1&&y!=graph.getHeight()-1){
	        num+=matrix[y+1][x+1];
	    }
	    //�±�
	    if(y!=graph.getHeight()-1){
	        num+=matrix[y+1][x];
	    }
	    //����
	    if(x!=0&&y!=graph.getHeight()-1){
	        num+=matrix[y+1][x-1];
	    }*/
	  //����
	    if(x==0&&y==0){
	        num+=matrix[y+1][x]+matrix[y][x+1]+matrix[y+1][x+1];
	    }
	    //����
	    else if(x==width-1&&y==0) {
	    	num+=matrix[y-1][x]+matrix[y][x-1]+matrix[y-1][x-1];
	    }
	    //����
	    else if(x==0&&y==height-1) {
	    	num+=matrix[y-1][x]+matrix[y][x+1]+matrix[y-1][x+1];
	    }
	    //����
	    else if(x==width-1&&y==height-1) {
	    	num+=matrix[y-1][x]+matrix[y][x-1]+matrix[y-1][x-1];
	    }
	    //���
	    else if(x==0) {
	    	num+=matrix[y-1][x]+matrix[y+1][x]+matrix[y-1][x+1]+matrix[y][x+1]+matrix[y+1][x+1];
	    }
	    //�ϱ�
	    else if(y==0) {
	    	num+=matrix[y][x-1]+matrix[y][x+1]+matrix[y+1][x-1]+matrix[y+1][x]+matrix[y+1][x+1];
	    }
	    //�±�
	    else if(y==height-1) {
	    	num+=matrix[y][x-1]+matrix[y][x+1]+matrix[y-1][x-1]+matrix[y-1][x]+matrix[y-1][x+1];
	    }
	    //�ұ�
	    else if(x==width-1) {
	    	num+=matrix[y-1][x]+matrix[y+1][x]+matrix[y-1][x-1]+matrix[y][x-1]+matrix[y+1][x-1];
	    }
	    //�м�
	    else {
	    	num+=matrix[y-1][x]+matrix[y+1][x]+matrix[y-1][x-1]+matrix[y][x-1]+matrix[y+1][x-1]
	    			+matrix[y-1][x+1]+matrix[y][x+1]+matrix[y+1][x+1];
	    }
	    return num;
	}
	
	/**
     * �������԰���
     */
    public void createCaseFile(Graph graph) {

		int height=graph.getHeight();
		int width=graph.getWidth();
		int[][] matrix=graph.getMatrix();
        Random random = new Random();
            //��ʼ���г�ʼ��
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

