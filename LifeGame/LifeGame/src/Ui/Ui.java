package Ui;

import javax.swing.*;

import Graph.Graph;

import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.TimeUnit;

public class Ui extends JFrame implements ActionListener {
    private static Ui frame;
    //private Cell cell;
    private int maxLength=0, maxWidth=0; //长和宽
    JButton[][] nGrid; //一个按钮表示一个细胞
    private boolean[][] isSelected;  //按钮（即细胞）是否被选中
    private JButton jbNowGeneration; //确定，当前代数，代数清零
    private JButton start; //下一代，开始繁衍，暂停，退出
    private JComboBox lengthList, widthList;//长款选择
    private boolean isRunning;
    //private Thread thread;
    private boolean isDead;
    public Graph graph=new Graph();
    private JButton btnNewButton;
    private Time time=new Time();
    //Thread thread1 = new Thread();  
    private JButton btnNewButton_1;
    private JButton btnNewButton_2;
    private JButton btnNewButton_3;

    public static void main(String arg[]) {
        frame = new Ui("生命游戏");
    }

    public int getMaxWidth() {
        return maxWidth;
    }

    public void setMaxWidth(int maxWidth) {
        this.maxWidth = maxWidth;
    }

    public int getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }

    public void initGUI() {
        if (maxWidth == 0) {
            maxWidth = 20;
        }
        if (maxLength == 0) {
            maxLength = 35;
        }

        //cell = new Cell(maxWidth, maxLength);

        JPanel backPanel, centerPanel, bottomPanel;
        JLabel jWidth, jLength;
        backPanel = new JPanel(new BorderLayout());
        centerPanel = new JPanel(new GridLayout(20, 35));
        bottomPanel = new JPanel();
        this.setContentPane(backPanel);
        backPanel.add(centerPanel, "Center");
        backPanel.add(bottomPanel, "South");

        nGrid = new JButton[maxWidth][maxLength];
        isSelected = new boolean[maxWidth][maxLength];
        for (int i = 0; i < maxWidth; i++) {
            for (int j = 0; j < maxLength; j++) {
                nGrid[i][j] = new JButton(""); //按钮内容置空以表示细胞
                nGrid[i][j].setBackground(Color.WHITE); //初始时所有细胞均为死
                centerPanel.add(nGrid[i][j]);
            }
        }

        jLength = new JLabel("长度：");
        lengthList = new JComboBox();
        for (int i = 3; i <= 100; i++)
            lengthList.addItem(String.valueOf(i));
        lengthList.setSelectedIndex(maxLength - 3); //设置的初始最小值为3，故减去3，下同

        jWidth = new JLabel("宽度：");
        widthList = new JComboBox();
        for (int i = 3; i <= 100; i++)
            widthList.addItem(String.valueOf(i));
        widthList.setSelectedIndex(maxWidth - 3);
        start = new JButton("开始繁衍");

        bottomPanel.add(jLength);
        bottomPanel.add(lengthList);
        bottomPanel.add(jWidth);
        bottomPanel.add(widthList);
        bottomPanel.add(start);
        
        btnNewButton = new JButton("\u6682\u505C");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		//time.stop=true;
        		// 3.启动后线程的 阻塞/暂停
        		time.pauseThread();
        	}
        });
        bottomPanel.add(btnNewButton);
        
        btnNewButton_1 = new JButton("\u7EE7\u7EED");
        btnNewButton_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		//继续
        		//time.stop=false;
        		// 4.以及 阻塞/暂停 线程后的 唤醒/继续
        		time.resumeThread();
        	}
        });
        bottomPanel.add(btnNewButton_1);
        
        btnNewButton_2 = new JButton("\u968F\u673A\u521D\u59CB\u5316");
        btnNewButton_2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		graph.CreatGraph();
            	showCell();
        	}
        });
        bottomPanel.add(btnNewButton_2);
        
        btnNewButton_3 = new JButton("\u7EC6\u80DE\u6E05\u96F6");
        btnNewButton_3.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		graph.DeleteGraph();
        		showCell();
        	}
        });
        bottomPanel.add(btnNewButton_3);


        // 设置窗口
        this.setSize(1000, 650);
        this.setResizable(true);
        this.setLocationRelativeTo(null); // 让窗口在屏幕居中
        this.setVisible(true);// 将窗口设置为可见的

        // 注册监听器
        this.addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent e) {
                System.exit(0);
            }
        });
        start.addActionListener(this);
        for (int i = 0; i < maxWidth; i++) {
            for (int j = 0; j < maxLength; j++) {
                nGrid[i][j].addActionListener(this);
            }
        }
       
    }

    public Ui(String name) {
        super(name);
        initGUI();
    }

    public void actionPerformed(ActionEvent e) {
      
    	//初始话地图
    	graph.CreatGraph();
    	showCell();
    	//自动更新换代
    	//Time time=new Time();
    	//Thread thread1 = new Thread(time);  
    	time.start(); 
    	
    	//time.stop=false;
    	//time.run();
    }
    public class Time extends Thread{
    	//private class GameControlTask  {
    	//boolean stop=false;//true为停，false持续更新
    	//Graph 
    	//无意义
        private final Object lock = new Object();
    	 //标志线程阻塞情况
        private boolean pause = false;
        /**
         * 这个方法只能在run 方法中实现，不然会阻塞主线程，导致页面无响应
         */
        void onPause() {
            synchronized (lock) {
                try {
                    //线程 等待/阻塞
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        /**
         * 设置线程是否阻塞
         */
        public void pauseThread() {
            this.pause = true;
        }
        public void resumeThread() {
            this.pause = false;
            synchronized (lock) {
                //唤醒线程
                lock.notify();
            }
        }

            @Override
            public void run() {
            	while(true) {
            		if (pause) {
                        //线程 阻塞/等待
                        onPause();
                    }
	                    graph.UpdateGraph();
	                    showCell();
	                    try {
	                        TimeUnit.MILLISECONDS.sleep(1000);
	                    } catch (InterruptedException ex) {
	                        ex.printStackTrace();
	                    }
            	}
            }
    }


     public void showCell(){
     //int[][] grid = cell.getGrid();
    	int[][] grid=graph.getMatrix();
        for (int i = 0; i < maxWidth; i++) {
            for (int j = 0; j < maxLength; j++) {
                if (grid[i][j ] == 1) {
                    nGrid[i][j].setBackground(Color.BLACK); //活显示黑色
                } else {
                    nGrid[i][j].setBackground(Color.WHITE); //死则显示白色
                }
            }
        }
    }

}
