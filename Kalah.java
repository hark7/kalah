

import java.util.Scanner;

/**
 *
 * This class contains the logic for Kalah for 2 players
 * with 6 pits having 6 holes each
 *
 **/
public class Kalah {
  
	private static final int NO_OF_PLAYERS = 2;
  	private static final int NO_OF_SEEDS = 6;
  	private static final int NO_OF_PITS = 6;
  	private static Scanner scanner = new Scanner(System.in);
  
  	private static int[][] board;
  	private static int[] stores;
  	private static int player;
  
  	// set the board with the seeds
  	public static int[][] initializeBoard() {
   		board = new int[NO_OF_PLAYERS][NO_OF_PITS + 1];
    	stores = new int[NO_OF_PLAYERS];
    	for (int i=0; i<NO_OF_PLAYERS; i++) {
      		for (int j=0; j<NO_OF_PITS; j++) {
        		board[i][j] = NO_OF_SEEDS; 
      		}
    	}
    	player = 1;
    	return board;
  	}

  	// print the board
  	private static void printBoard() {
   		// Display the store values
    	for (int i=0; i<NO_OF_PLAYERS; i++) {
    		System.out.print("	Store_"+i+": "+stores[i]+"\n");
    	}
    	for (int i=0; i<NO_OF_PLAYERS; i++) {
      		// This is to indicate the next active player
      		if (i==player) {
        		System.out.print(">>");
      		}
      		// Display the pits and the corresponding pit number
      		for (int j=0; j<NO_OF_PITS; j++) {
        		System.out.print("	"+board[i][j]+"("+(j+1)+")"); 
      		}
      		System.out.println("\n\n");
    	}
  	}
  
  	// accept from player the pit number to be selected for playing
  	private static int getMove(int player) {
   		System.out.println("Enter index of pit for player-"+player+":");
    	int pit = scanner.nextInt();
    	if (pit < 1 || pit > 6) {
      		System.out.println("Error: Invalid pit number!");
      		pit = getMove(player);
    	}
    	if (board[player][pit-1] == 0) {
      		System.out.println("Error: No seeds!");
      		pit = getMove(player);
    	}
    	return pit;
  	}
  
           
  	// perform the move by placing the seeds
  	private static int move(int pit, int player) {
   		int nextPlayer = 0;
    	int hand = board[player][pit-1];
    	board[player][pit-1] = 0; 
    
    	if (player == 1) {
      		int temp = pit;
      		while (hand>0) {
        		for (int i=temp; i<NO_OF_PITS; i++) {
          
          			if (hand>0) {
          	 			hand--;
            			board[player][i]++;
          			} 
			          
          			if (hand == 0 && board[player][i] == 1) {
            			stores[player]+=board[0][i];
            			board[0][i] = 0;
          			}
        		}
        		if (hand>0) {
          			hand--;
          			stores[player]++;
          			if (hand == 0) {
          				nextPlayer = 1;
        			} else {
          				nextPlayer = 0;
        			}
        		} 
        
        		if (hand>0) {
   		  			for (int i=NO_OF_PITS; i>0; i--) {
            
            			if (hand>0) {
              				hand--;
              				board[0][i-1]++;
            			} else {
              				break;
            			}
          			}     
        		}
        		temp = 0;
      		}

    	} else {
      		int temp = pit;
      		while (hand>0) {
        		for (int i=temp-1; i>0; i--) {
          
          			if (hand>0) {
          	  			hand--;
            			board[player][i-1]++;
          			} 
          			if (hand == 0 && board[player][i-1] == 1) {
            			stores[player]+=board[1][i-1];
            			board[1][i-1] = 0;
          			}
        		}
        		if (hand>0) {
          			hand--;
          			stores[player]++;
          			if (hand == 0) {
          				nextPlayer = 0;
        			} else {
          				nextPlayer = 1;
        			}
        		} 
        
        		if (hand>0) {
   		  			for (int i=0; i<NO_OF_PITS; i++) {
            
            			if (hand>0) {
              				hand--;
              				board[1][i]++;
            			} else {
              				break;
            			}
          			}     
        		}
        		temp = NO_OF_PITS+1;
      		}
    	}
    
    	return nextPlayer;
  	}    

	// logic to check if the current player won the game
	private static boolean checkGameOver(int player) {
   		for (int j=0; j<NO_OF_PITS; j++) {
      		if (board[player][j]>0) {
        		return false;
      		}
    	}
    	return true;	
  	}
  
	
	public static void main(String[] args) {
		initializeBoard();
      	printBoard();
      	int currentPlayer = player;
      	while(!checkGameOver(currentPlayer)) {
       		int pit = getMove(currentPlayer);
        	currentPlayer = move(pit, currentPlayer);
        	printBoard();
      	}
      	if (stores[0]>stores[1]) {
      		System.out.println("Game Over! Winner is: 0");  
      	} else {
      		System.out.println("Game Over! Winner is: 1");
	  	}
   	}
}
