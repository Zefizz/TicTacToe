/**
 * 
 * This is a Java program that allows a use to play a game
 * against the computer (TicTacToePlayer)
 *
 * @author <Zefizz>
 * @since <add 29/02/16>
 * @custom.citations <Hinek, J. "day20-11" [PDF documents] retrieved from https://culearn.carleton.ca (Winter 2016)
                      Overview - Java Platform SE 7. (23/02/16). Retrieved from https://docs.oracle.com/javase/7/docs/api/>
 */

import java.util.Scanner;  // used for input

public class TicTacToeApp{

	private static void printStats(TicTacToePlayer p, byte gamesPlayed) {
		System.out.println ("\nYOUR STATS:\n");
		System.out.println ("\tGames Played:\t" + gamesPlayed );
		System.out.println ("\tGames Won:\t" + p.getWins() );
    System.out.println ("\tGames Lost:\t" + p.getLoss() );
    System.out.println ("\tGames Drawn:\t" + (gamesPlayed-(p.getLoss()+p.getWins())) );

	}
	private static void playProcedure(TicTacToeGame game, TicTacToePlayer p, TicTacToePlayer q, String nextMove) {
		p.play(game, Integer.parseInt(nextMove));
		if (p.gameOver(game)) {
			if (p.winner(game, p, q)!=null) {
				if (p.winner(game, p, q).equals(p)) {
					p.incrementWins();
          q.incrementLoses();
					System.out.println("The game is over! " + p.getName() + " won this game.");
				}
			}
			else if (q.winner(game, p, q)!=null) {
				if (q.winner(game, p, q).equals(p)) {
					q.incrementWins();
          p.incrementLoses();
					System.out.println("The game is over! " + q.getName() + " won this game.");
				}
			}
			else
      	System.out.println("This game was a draw!");
		}
	}

  public static void main(String[] args){
  	String nextMove = "";
    byte gamesPlayed = 0;
  	String nextCompMove = "";
    Scanner keyboard = new Scanner(System.in);
    TicTacToeGame board = new TicTacToeGame();

    System.out.println ("Welcome to TicTacToe!\nWhat is your name?");
    String name = keyboard.next();
    System.out.println ("Please enter the piece you would like to play with ( x or o )");
    String pieceSTR = keyboard.next();
    pieceSTR.toLowerCase();
    char piece = pieceSTR.charAt(0);
    char oppPiece = 'o';

    TicTacToePlayer p1 = new TicTacToePlayer(name, piece);
    if (!p1.isX())
      oppPiece = 'x';
    TicTacToePlayer p2 = new TicTacToePlayer("COMP", oppPiece);


    while(true){
   		if (p1.isX()) {
   			System.out.println("Please enter your next move (or enter \"quit\" to end it all)");
   			nextMove = keyboard.next();
        if (nextMove.equals("quit")) break;
   			playProcedure(board, p1, p2, nextMove);

   			if (!p1.gameOver(board)) {
   				nextCompMove = Integer.toString(p2.findMove(board));
   				System.out.println ("COMP just played " + nextCompMove);
   				playProcedure(board, p2, p1, nextCompMove);
    			System.out.println (board.show());
    		}
   		}
   		else {
        nextCompMove = Integer.toString(p2.findMove(board));
   			System.out.println ("COMP just played " + nextCompMove);
   			playProcedure(board, p2, p1, nextCompMove);
   			System.out.println (board.show());

   			if (!p2.gameOver(board)) {
   				System.out.println("Please enter your next move (or enter \"quit\" to end it all)");
   				nextMove = keyboard.next();
          if (nextMove.equals("quit")) break;
   				playProcedure(board, p1, p2, nextMove);
   			}
   		}
   		if (p1.gameOver(board)) {
   			System.out.println (board.show());
   			board = new TicTacToeGame();
   			gamesPlayed++;
   		}
	 }
  printStats(p1, gamesPlayed);
	System.out.println ("Thank you for playing!");
	}
}
