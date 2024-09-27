package com.tap.project;

import java.util.Scanner;
import java.util.Random;

abstract class Player
{
	String name;
	char mark;
	abstract void makeMove();
	boolean isValidMove(int row,int column)
	{
		if(row>=0 && row <=2 &&
				column>=0 && column<=2)
		{
			if(TicTacToe.board[row][column]==' ')
			{
				return true;
			}
		}
		return false;
	}
	
}
class HumanPlayer extends Player
{

	HumanPlayer(String name,char mark)
	{
		this.name=name;
		this.mark=mark;
	}
	void makeMove()
	{
		Scanner scan=new Scanner(System.in);
		int row;
		int column;
		do
		{
			System.out.println("Enter the row and column");
			row=scan.nextInt();
			column=scan.nextInt();
		}while (!isValidMove(row,column));
		
		TicTacToe.placeMark(row, column, mark);
		scan.close();
		
	}
	
}
class AiPlayer extends Player
{

	AiPlayer(String name,char mark)
	{
		this.name=name;
		this.mark=mark;
	}
	void makeMove()
	{
		Scanner scan=new Scanner(System.in);
		int row;
		int column;
		do
		{
			Random r=new Random();
			row=r.nextInt(3);
			column=r.nextInt(3);
		}while (!isValidMove(row,column));
		
		TicTacToe.placeMark(row, column, mark);
		scan.close();
		
	}
	
}




public class LaunchGame {
	public static void main(String[] args)
	{
		@SuppressWarnings("unused")
		TicTacToe t=new TicTacToe();
		/*t.dispBoard();
		t.placeMark(0, 0, 'X');
		t.placeMark(1, 0, 'O');*/
		//t.placeMark(0, 1, 'X');
		//t.placeMark(1, 1, 'O');
		//t.placeMark(0, 2, 'x');
		//t.placeMark(2, 1, 'O');
		HumanPlayer h1=new HumanPlayer("Roo",'X');
		AiPlayer h2=new AiPlayer("bot",'O');
		//HumanPlayer h2=new HumanPlayer("Priya",'O');
		Player cp;
		cp=h1;
		while(true)
		{
			System.out.println(cp.name+" 's turn");
			cp.makeMove();
			TicTacToe.dispBoard();
			if(TicTacToe.checkColwin()||TicTacToe.checkRowWin()
					||TicTacToe.checkDiagWin())
			{
				System.out.println(cp.name + " has won");
				break;
			}
			else if(TicTacToe.checkDraw())
			{
				System.out.println("Game is Draw");
				break;
			}
			else
			{
				if(cp==h1)
				{
					cp=h2;
				}
				else
				{
					cp=h1;
				}
			}
		}

		
		
		
		/*t.dispBoard();
		System.out.println(t.checkDiagWin());
		System.out.println(t.checkRowWin());
		System.out.println(t.checkColwin());*/
	}

}
class TicTacToe
{
	static char[][] board;
	public TicTacToe()
	{
		board =new char[3][3];
		initBoard();
	}
	void initBoard()
	{
		for(int i=0;i<board.length;i++)
		{
			for(int j=0;j<board[i].length;j++)
			{
				board[i][j] = ' ';
			}
		}
	}
		static void dispBoard()
		{
			System.out.println("-------------");
			for(int i=0;i<board.length;i++)
			{
				for(int j=0;j<board[i].length;j++)
				{
					System.out.print(board[i][j]+" |  ");
				}
				System.out.println(); 
				System.out.println("-------------");  
			}
		}
		static void placeMark(int row,int column,char mark)
		{
			if(row>=0&&row<=2&&column>=0&&column<=2)
			{
				board[row][column]=mark;
			}
			else
			{
				System.out.println("Invalid Position");
			}
		}
		static boolean checkColwin()
		{
			for(int j=0;j<=2;j++)
			{
			if( board[0][j]!=' ' &&board[0][j]==board[1][j] && board[1][j]==board[2][j])
			{
				return true;
			}
			
			}
			return false;
		}
		static boolean checkRowWin()
		{
			for(int i=0;i<=2;i++)
			{
				if(board[i][0]!=' '&&board[i][0]==board[i][1] &&
						board[i][1]==board[i][2])
				{
					return true;
				}
			}
			return false;
		}
		static boolean checkDiagWin()
		{
			if(board[0][0]!=' '&&board[0][0]==board[1][1]
		    && board[1][1]==board[2][2]
			||board[0][2]!=' '&&board[0][2]==board[1][1]
			&&board[1][1]==board[2][0])
		{
			return true;
		}
		else
		{
			return false;
		}

		}
		static boolean checkDraw()
		{
			for(int i=0;i<=2;i++)
			{
				for(int j=0;j<=2;j++)
				{
					if(board[i][j]==' ')
					{
						return false;
					}
				}
			}
			return true;
		}
	
}

