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
	JLabel[][] label= new JLabel[10][10];
	JTextField field;
	JPanel panel;
	JMenu menu;
	JMenuBar menubar;
	JMenuItem NewGame,exitmenu;
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
		exitmenu = new JMenuItem("Sair");
		exitmenu.addActionListener(this);
		menu.add(NewGame);
		menu.add(exitmenu);
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
				c.add(field);
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


					c.add(panel);
					frame.setVisible(true);
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
			c.add(field);
		}

		if(e.getSource().equals(exitmenu) || e.getSource().equals(exit))
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
				c.add(field);
			}

				for(int u=0;u<10;u++)
				{
					for(int v=0;v<10;v++)
					{
						if(e.getSource().equals(button[u][v]))
						{
							button[u][v].setVisible(false);
						}
					}
				}

				for(int ex=0;ex<10;ex++)
				{
					c.add(field);
					chk++;
				}
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

	}
	public static void main(String[] args) {

		new CampoMinado();

	}

}
