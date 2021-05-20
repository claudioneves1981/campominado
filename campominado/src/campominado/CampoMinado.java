package campominado;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class CampoMinado implements ActionListener,MouseListener

{
	
	JFrame frame;
	Container c;
	int high=20,width=20;
	JButton[][] button=new JButton[10][10];
	JButton reset,exit;
	JLabel[][] label=new JLabel[10][10];
	JTextField field;
	JPanel panel;
	JMenu menu;
	JMenuBar menubar;
	JMenuItem NewGame,Exit;
	int mines,dwn,upp,lft,chk=0;
	
	public CampoMinado() 
	{
		
		frame = new JFrame("Campo Minado");
		frame.setSize(469,580);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		c=frame.getContentPane();
		c.setLayout(null);
		c.setBackground(Color.BLACK);
		
		menubar = new JMenuBar();
		menu = new JMenu("Arquivo");
		NewGame = new JMenuItem("Novo Jogo");
		NewGame.addActionListener(this);
		Exit = new JMenuItem("Sair");
		Exit.addActionListener(this);
		menu.add(NewGame);
		menu.add(Exit);
		menubar.add(menu);
		frame.setJMenuBar(menubar);
		
		panel=new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setSize(459,64);
		panel.setLayout(null);
		
		int yaxis=65;
			for(int a=0;a<10;a++)
				{
					int xaxis=1;
					
					for(int b=0;b<10;b++)
					{
						button[a][b]=new JButton("");
						button[a][b].setSize(45,45);
						button[a][b].setLocation(xaxis,yaxis);
						button[a][b].setBackground(Color.LIGHT_GRAY);
						button[a][b].addActionListener(this);
						button[a][b].addMouseListener(this);
						c.add(button[a][b]);
						xaxis+=button[a][b].getWidth()+1;
					}
					yaxis+=button[0][0].getHeight()+1;
				}
				
				reset=new JButton();
				reset.setBackground(Color.WHITE);
				reset.setLocation((frame.getWidth()/2)-(reset.getWidth()/2),0);
				reset.addActionListener(this);
				panel.add(reset);
				
				exit=new JButton("SAIR");
				exit.setSize(exit.getPreferredSize());
				exit.setLocation((reset.getX()/2)-(exit.getWidth()/2),20);
				exit.setBackground(Color.LIGHT_GRAY);
				exit.addActionListener(this);
				c.add(exit);
				
				field=new JTextField("Jogo Iniciado");
				field.setFont(new Font("Times New Roman",Font.BOLD,14));
				field.setSize(field.getPreferredSize());
				field.setBackground(Color.BLACK);
				field.setForeground(Color.WHITE);
				field.setLocation(reset.getX()+100,(panel.getHeight()/2)-(field.getHeight()/2));
				
				yaxis=65;
				for(int e=0;e<10;e++)
				{
					
					int xaxis=1;
					for(int f=0;f<10;f++)
					{
						
						label[e][f]=new JLabel("",JLabel.CENTER);
						label[e][f].setSize(45,45);
						label[e][f].setLocation(xaxis,yaxis);
						label[e][f].setBackground(Color.WHITE);
						label[e][f].setForeground(Color.BLACK);
						label[e][f].setOpaque(true);
						c.add(label[e][f]);
						xaxis+=label[e][f].getWidth()+1;
					}
					yaxis+=label[0][0].getHeight()+1;
				}
				
					setup();
				
					c.add(panel);
					frame.setVisible(true);
	}
	
	private void setup() {
		// TODO Auto-generated method stub
		
	}

	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource().equals(NewGame))
		{
			chk=0;
			
			for(int i=0;i<10;i++)
			{
				for(int j=0;j<10;j++)
				{
					button[i][j].setVisible(true);
					button[i][j].setEnabled(true);
					button[i][j].setBackground(Color.LIGHT_GRAY);
				}
			}
			field.setText("Jogo Iniciado");
		}
		if(e.getSource().equals(Exit))
		{
			frame.setVisible(false);
		}
		
		if(e.getSource().equals(exit))
		{
			frame.setVisible(false);
		}
		
			if(e.getSource().equals(reset))
			{
				chk=0;
				for(int i =0;i<10;i++)
				{
					for(int j=0;j<10;j++)
					{
						button[i][j].setVisible(true);
						button[i][j].setEnabled(true);
						button[i][j].setBackground(Color.LIGHT_GRAY);
						button[i][j].setText("");
					}
				}
				field.setText("Jogo Iniciado");
			}
				
				for(int u=0;u<10;u++)
				{
					for(int v=0;v<10;v++)
					{
						if(e.getSource().equals(button[u][v]))
						{
							button[u][v].setVisible(false);
							if(label[u][v].getText().equals("0"))
							{
								field.setText("lose");
								
								for(int m=0;m<10;m++)
								{
									for(int n=0;n<10;n++)
									{
										button[m][n].setEnabled(false);
										
										if (label[m][n].getText().equals("0"))
										{
											if(button[m][n].getText()!="x")
											{
												button[m][n].setVisible(false);
											}
										}
									}
								
								}
								chk++;
								lose();
							}else if(label[u][v].getText()=="")
							{
								check(u,v);
							}
						}
					}
				}
				
				for(int ex=0;ex<10;ex++)
				{
					field.setText("Você Venceu");
					Win();
					chk++;
				}
	}
	
	private void Win() {
		// TODO Auto-generated method stub
		
	}

	private void check(int u, int v) {
		// TODO Auto-generated method stub
		
	}

	private void lose() {
		// TODO Auto-generated method stub
		
	}

	public void mousePressed(MouseEvent e)
	{
	}
	
	public void mouseReleased(MouseEvent e)
	{
	}
	
	public void mouseEntered(MouseEvent e)
	{
	}
	
	public void mouseExited(MouseEvent e)
	{
	}
	
	public void mouseClicked(MouseEvent e)
	{
		if(e.getButton()==3&&chk==0)
		{
			for(int alpha=0;alpha<10;alpha++)
			{
			}
			
		}

	}
	
						


	public static void main(String[] args) {
		
	CampoMinado campominado = new CampoMinado();
	
	}

}
