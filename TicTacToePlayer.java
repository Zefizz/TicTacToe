/**
 *
 * This class encapsulates a player in a tic-tac-toe game.
 * See https://en.wikipedia.org/wiki/Tic-tac-toe for more
 * information about tic-tac-toe.
 *
 * @author <Zefizz>
 * @since <add 29/02/16>
 * @custom.citations <Hinek, J. "day20-11" [PDF documents] retrieved from https://culearn.carleton.ca (Winter 2016)
                      Overview - Java Platform SE 7. (23/02/16). Retrieved from https://docs.oracle.com/javase/7/docs/api/>
 */

public class TicTacToePlayer{
  //Player's name. The name cannot be changed once it is set by the construtor.
  private final String name;  // DO NOT CHANGE THIS
  private char piece;
  private byte gamesWon;
  private byte gamesLost;
  //Getter for the players name
  public String getName(){   // DO NOT CHANGE THIS
    return name;
  }
  //getters
  public char getPiece(){return piece;}
  public byte getWins(){return gamesWon;}
  public byte getLoss(){return gamesLost;}
  //setters
  public void incrementWins(){gamesWon++;}
  public void incrementLoses(){gamesLost++;}
  /**
   * Constructor for a tic tac toe player.
   *
   * @param name is the name of the player.
   * @param p must be either 'x' or 'o'.
   */
  public TicTacToePlayer(String name, char p){
    piece = p;
    this.name = name;
    gamesWon = 0;
    gamesLost = 0;
   }
  public TicTacToePlayer(){
    piece = 'q';
    name = " ";
    gamesWon = 0;
    gamesLost = 0;
   }
  /**
   * Checks if a given game instance is over or not.
   *
   * @param game is an instance of a tic tac toe game (the board)
   * @return true if the game instance is over
   * (if one player has one or if there is a tie), otherwise false.
   */
  private static boolean checkCols (TicTacToeGame game, int d, TicTacToePlayer player) {
    char[] pieceArray;
    int count;
    char firstPiece;
    boolean win;
    for (int j=0; j<d; j++) {//go across columns
      pieceArray = new char[d];
      win = true;
      count = 0;
      for (int k=j; k<d*d; k+=d)//go down columns
        pieceArray[count++] = game.getAtPosition(k);
      firstPiece = pieceArray[0];
      for (int i=1; i<d; i++) {
        if ((pieceArray[i]!=firstPiece)||pieceArray[i]!=player.piece) win=false;}
      if (win) return true;
    }
    return false;
  }
  private static boolean checkRows (TicTacToeGame game, int d, TicTacToePlayer player) {
    char[] pieceArray;
    int count;
    char firstPiece;
    boolean win;
    for (int j=0; j<d*d; j+=d) {//go down rows
      pieceArray = new char[d];
      win = true;
      count = 0;
      for (int k=j; k<j+d; k++)//go across rows
        pieceArray[count++] = game.getAtPosition(k);
      firstPiece = pieceArray[0];
      for (int i=1; i<d; i++) {
        if ((pieceArray[i]!=firstPiece)||pieceArray[i]!=player.piece) win=false;}
      if (win) return true;
    }
    return false;
  }
  private static boolean checkDiag (TicTacToeGame game, int d, TicTacToePlayer player) {
    if (d%2==0) return false; //can only have winning diagonal on an odd-dimension board
    char[] pieceArray = new char[d];
    int count = 0;
    boolean win = true;
    for (int i=0; i<d*d; i+=(d+1))  //check top-to-bottom
      pieceArray[count++] = game.getAtPosition(i);
    for (int i=0; i<d; i++) {
      if (pieceArray[i]!=player.piece) win=false;}
    if (win) return true;

    count = 0;
    win = true;
    pieceArray = new char[d];
    for (int i=d*d-d; i>0; i-=(d-1))  //check bottom-to-top
      pieceArray[count++] = game.getAtPosition(i);
    for (int i=0; i<d; i++) {
      if (pieceArray[i]!=player.piece) win=false;}
    if (win) return true;
    return false;
  }
  //Why is this stacic why why why do you love pain?
  public static boolean gameOver(TicTacToeGame game){
    int d = game.getDimension();  //create a copy of the current player, and a dummy player
    TicTacToePlayer playerX = new TicTacToePlayer(" ",'x');
    TicTacToePlayer playerO = new TicTacToePlayer(" ",'o');

    if (checkRows(game,d,playerX)||checkCols(game,d,playerX)||checkDiag(game,d,playerX))
      return true;  //did this player win?
    if (checkRows(game,d,playerO)||checkCols(game,d,playerO)||checkDiag(game,d,playerO))
      return true;  //did this player win?
    for (int i=0; i<d*d; i++) { //is the board full?
      if (game.getAtPosition(i)=='\0') return false;
    }

    return true;
  }

  /**
   * Checks if a given game instance is over or not.
   *
   * @param game is an instance of a tic tac toe game (the board)
   * @param p1 is one player in the game
   * @param p2 is the other player in the game
   * @return p1 if player p1 has won the game, p2 if player p2 has
   * won the game and returns null otherwise (if there is no winner in the given game).
   */
  public static TicTacToePlayer winner(TicTacToeGame game,
                                       TicTacToePlayer p1,
                                       TicTacToePlayer p2)
  {
    int d = game.getDimension();
    if (checkRows(game,d,p1)||checkCols(game,d,p1)||checkDiag(game,d,p1))
      return p1;
    if (checkRows(game,d,p2)||checkCols(game,d,p2)||checkDiag(game,d,p2))
      return p2;
    return null;
  }


  /**
   * Checks if current player is playing x's or o's.
   *
   * @param none
   * @return true if this object is playing x's and false if the object is playing o's.
   */
  public boolean isX(){
    if (piece=='o' || piece=='O')
      return false;
    return true;
  }
  /**
   * Getter method that tells if player is playing x's or o's.
   *
   * @return 'x' if this player is playing x's and returns 'o' if this player is playing o's.
   */
  public char getXO(){
    if (piece=='o' || piece=='O')
      return 'o';
    if (piece=='x' || piece=='X')
      return 'x';
    return 'q';
    }

  /**
   * Finds a valid move in a tic-tac-toe game for this player
   *
   * @param game is an instance of a tic-tac-toe game
   * @return A position in the board [0,D-1] that is a valid move for this player to make,
   * where D = dimention*dimension.
   * If there are multiple valid moves any is acceptable.
   * If there are no valid moves then the function returns -1.
   */
  public int findMove(TicTacToeGame game){
    if (findWinningMove(game)!=-1)
      return findWinningMove(game);
    if (findBlockingMove(game)!=-1)
      return findBlockingMove(game);
    for (int i=0; i<Math.pow(game.getDimension(),2); i++) {
      if (game.getAtPosition(i)=='\0')
        return i;
    }
    return -1;
  }

  /**
   * Finds a valid move in a tic-tac-toe game for this player
   *
   * @param game is an instance of a tic-tac-toe game
   * @return An array of positions in the board [0,D-1] that are each a valid move
   * for this player to make, where D = dimension*dimension.
   * All valid moves must be included in the output.
   * The order of the positions in the output array do not matter. If there are no
   * valid moves then the function return null.
   */
  public int[] findAllMoves(TicTacToeGame game){
    int d = game.getDimension();
    int[] movesArray = new int[d*d];
    for (int i=0; i<d*d; i++)
      movesArray[i] = -7;//if array is init. to zero, pos 0 can not be discerend from emty space
    int counter = 0;
    for (int i=0; i<d*d; i++) {
      if (game.getAtPosition(i)=='\0')
        movesArray[counter++] = i;
    }
    if (counter==0) return null;
    return movesArray;
  }

  /**
   * Finds a winning move if possible for this player.
   *
   * @param game is an instance of a tic-tac-toe game
   * @return A position in the board [0,D-1] that is a valid winning move for this player to make,
   * where D = dimension*dimension.
   * If there are multiple winning moves then any is acceptable.
   * Returns -1 if there is no winning move for the player.
   */

  private boolean fullCol(TicTacToeGame game, int pos) {
    int preservedPos = pos;
    int d = game.getDimension();
    while (pos>=d)
      pos -= d;   //start at the top of the column
    while(pos<d*d) {
      if ((game.getAtPosition(pos)!=piece)&&pos!=preservedPos) {
        return false;
      }pos += d;
    }
    return true;  //if you made it here you have a winning move on the column
  }
  private boolean fullRow(TicTacToeGame game, int pos) {
    int preservedPos = pos;
    int d = game.getDimension();
    while (pos%d!=0)
      pos--;   //start at the front of the row
    for (int i=pos; i<(pos+d); i++) {
      if (i==preservedPos) continue;
      if (game.getAtPosition(i)!=piece) return false;
    }
    return true;  //if you made it here you have a winning move on the row
  }

  private boolean fullDiag(TicTacToeGame game, int pos) {
    int d = game.getDimension();
    boolean hasWinningMoveCol = true; //check for contradiction
    if (d%2==0) return false; //can only happen if dimension is odd
    for (int i=0; i<d*d; i+=(d+1)) {  //start checking top to bottom horizontal
      if (i==pos) continue;
      if (game.getAtPosition(i)!=piece) {
        hasWinningMoveCol = false;
      }
    }
    if (hasWinningMoveCol) return true; //else assume true, look for contra with bot to top
    hasWinningMoveCol = true;
   for (int i=(d*d-d); i>0; i-=(d-1)) {//start checking bottom to top horizontal
      if (i==pos) continue;
      if (game.getAtPosition(i)!=piece) {
        return false;
      }
    }
    return hasWinningMoveCol; //if you made it here you have a winning move on the diagonal
  }

  public int findWinningMove(TicTacToeGame game){
    int[] movesArray = findAllMoves(game);
    for (int i=0; i<movesArray.length; i++) {
      if (movesArray[i]==-7) break;  //next we check if there are any winning moves on a col, row or diag
      if (fullCol(game,movesArray[i])||fullRow(game,movesArray[i])||fullDiag(game,movesArray[i]))
        return movesArray[i];
    }
    return -1;
  }

  /**
   * Finds a blocking move if possible for this player
   *
   *
   * @param game is an instance of a tic-tac-toe game
   * @return A position in the board [0,D-1] that is a valid bocking
   * move for this player to make, where D = dimension*dimension.
   * If there are multiple blocking moves then any is acceptable.
   * Returns -1 if there is no blocking move for this player.
   */
  public int findBlockingMove(TicTacToeGame game){
    //simulate a player with the opposite piece type
    //their winning moves become our blocking moves
    char oppositePiece;
    if (isX()) oppositePiece = 'o';
    else oppositePiece = 'x';
    TicTacToePlayer dummyPlayer = new TicTacToePlayer("Temp",oppositePiece);
    int blockingMove = dummyPlayer.findWinningMove(game);
    if (blockingMove!=-1) return blockingMove;
    return -1;
  }


  /**
   * Plays a move for this player in a game
   *
   * @param game is a tic-tac-toe game that the player is playing.
   * @param pos is the position [-1,D-1] in the game that the player is playing a move,
   * where D=dimension*dimension.
   * @return Nothing. The function has the side effect of playing a move on the board,
   * using this player's symbol (x or o) at the specified position. If the position
   * is -1 then the function does nothing.
   */
  public void play(TicTacToeGame game, int pos){
    if (pos!=-1) {
      TicTacToePlayer dummyPlayer = new TicTacToePlayer(name,piece);
      game.play(pos,dummyPlayer);
    }
  }

}
