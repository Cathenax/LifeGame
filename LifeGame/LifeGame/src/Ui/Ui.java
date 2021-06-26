package Ui;

import javax.swing.*;

import Graph.Graph;

import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.TimeUnit;

public class Ui extends JFrame implements ActionListener {
    private static Ui frame;
    //private Cell cell;
    private int maxLength=0, maxWidth=0; //���Ϳ�
    JButton[][] nGrid; //һ����ť��ʾһ��ϸ��
    private boolean[][] isSelected;  //��ť����ϸ�����Ƿ�ѡ��
    private JButton jbNowGeneration; //ȷ������ǰ��������������
    private JButton start; //��һ������ʼ���ܣ���ͣ���˳�
    private JComboBox lengthList, widthList;//����ѡ��
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
        frame = new Ui("������Ϸ");
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
                nGrid[i][j] = new JButton(""); //��ť�����ÿ��Ա�ʾϸ��
                nGrid[i][j].setBackground(Color.WHITE); //��ʼʱ����ϸ����Ϊ��
                centerPanel.add(nGrid[i][j]);
            }
        }

        jLength = new JLabel("���ȣ�");
        lengthList = new JComboBox();
        for (int i = 3; i <= 100; i++)
            lengthList.addItem(String.valueOf(i));
        lengthList.setSelectedIndex(maxLength - 3); //���õĳ�ʼ��СֵΪ3���ʼ�ȥ3����ͬ

        jWidth = new JLabel("��ȣ�");
        widthList = new JComboBox();
        for (int i = 3; i <= 100; i++)
            widthList.addItem(String.valueOf(i));
        widthList.setSelectedIndex(maxWidth - 3);
        start = new JButton("��ʼ����");

        bottomPanel.add(jLength);
        bottomPanel.add(lengthList);
        bottomPanel.add(jWidth);
        bottomPanel.add(widthList);
        bottomPanel.add(start);
        
        btnNewButton = new JButton("\u6682\u505C");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		//time.stop=true;
        		// 3.�������̵߳� ����/��ͣ
        		time.pauseThread();
        	}
        });
        bottomPanel.add(btnNewButton);
        
        btnNewButton_1 = new JButton("\u7EE7\u7EED");
        btnNewButton_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		//����
        		//time.stop=false;
        		// 4.�Լ� ����/��ͣ �̺߳�� ����/����
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


        // ���ô���
        this.setSize(1000, 650);
        this.setResizable(true);
        this.setLocationRelativeTo(null); // �ô�������Ļ����
        this.setVisible(true);// ����������Ϊ�ɼ���

        // ע�������
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
      
    	//��ʼ����ͼ
    	graph.CreatGraph();
    	showCell();
    	//�Զ����»���
    	//Time time=new Time();
    	//Thread thread1 = new Thread(time);  
    	time.start(); 
    	
    	//time.stop=false;
    	//time.run();
    }
    public class Time extends Thread{
    	//private class GameControlTask  {
    	//boolean stop=false;//trueΪͣ��false��������
    	//Graph 
    	//������
        private final Object lock = new Object();
    	 //��־�߳��������
        private boolean pause = false;
        /**
         * �������ֻ����run ������ʵ�֣���Ȼ���������̣߳�����ҳ������Ӧ
         */
        void onPause() {
            synchronized (lock) {
                try {
                    //�߳� �ȴ�/����
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        /**
         * �����߳��Ƿ�����
         */
        public void pauseThread() {
            this.pause = true;
        }
        public void resumeThread() {
            this.pause = false;
            synchronized (lock) {
                //�����߳�
                lock.notify();
            }
        }

            @Override
            public void run() {
            	while(true) {
            		if (pause) {
                        //�߳� ����/�ȴ�
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
                    nGrid[i][j].setBackground(Color.BLACK); //����ʾ��ɫ
                } else {
                    nGrid[i][j].setBackground(Color.WHITE); //������ʾ��ɫ
                }
            }
        }
    }

}
