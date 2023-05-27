package com.example.tic_tac_toe;

public class TicTac
{
    static class Move
    {
        int row, col,value;
    };

    static char player, opponent;


    static Boolean isMovesLeft(char board[][])
    {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (board[i][j] == '_')
                    return true;
        return false;
    }


    static int evaluate(char b[][])
    {
        // Checking for Rows for X or O victory.
        for (int row = 0; row < 3; row++)
        {
            if (b[row][0] == b[row][1] &&
                    b[row][1] == b[row][2])
            {
                if (b[row][0] == player)
                    return +10;
                else if (b[row][0] == opponent)
                    return -10;
            }
        }

        // Checking for Columns for X or O victory.
        for (int col = 0; col < 3; col++)
        {
            if (b[0][col] == b[1][col] &&
                    b[1][col] == b[2][col])
            {
                if (b[0][col] == player)
                    return +10;

                else if (b[0][col] == opponent)
                    return -10;
            }
        }

        // Checking for Diagonals for X or O victory.
        if (b[0][0] == b[1][1] && b[1][1] == b[2][2])
        {
            if (b[0][0] == player)
                return +10;
            else if (b[0][0] == opponent)
                return -10;
        }

        if (b[0][2] == b[1][1] && b[1][1] == b[2][0])
        {
            if (b[0][2] == player)
                return +10;
            else if (b[0][2] == opponent)
                return -10;
        }

        // Else if none of them have won then return 0
        return 0;
    }

    // This is the minimax function. It considers all
// the possible ways the game can go and returns
// the value of the board
    static int minimax(char board[][],
                       int depth, Boolean isMax,int alpha,int beta)
    {
        int score = evaluate(board);

        // If Maximizer has won the game
        // return evaluated score
        if (score == 10)
            return score;

        // If Minimizer has won the game
        // return evaluated score
        if (score == -10)
            return score;

        // If there are no more moves and
        // no winner then it is a draw
        if (isMovesLeft(board) == false)
            return 0;

        // If this maximizer's move
        if (isMax)
        {
            int best = -1000;

            // Traverse all cells
            boolean fr=false;
            for (int i = 0; i < 3; i++)
            {
                for (int j = 0; j < 3; j++)
                {
                    // Check if cell is empty
                    if (board[i][j]=='_')
                    {
                        // Make the move
                        board[i][j] = player;

                        // Call minimax recursively and choose
                        // the maximum value
                        best = Math.max(best, minimax(board,
                                depth + 1, !isMax,alpha,beta));

                        alpha=Math.max(alpha,best);

                        if(beta <= alpha){
                            fr=true;
                            break;}
                        // Undo the move
                        board[i][j] = '_';
                    }
                }
                if(fr)break;
            }
            return best;
        }

        // If this minimizer's move
        else
        {
            int best = 1000;

            // Traverse all cells
            boolean fr=false;
            for (int i = 0; i < 3; i++)
            {
                for (int j = 0; j < 3; j++)
                {
                    // Check if cell is empty
                    if (board[i][j] == '_')
                    {
                        // Make the move
                        board[i][j] = opponent;

                        // Call minimax recursively and choose
                        // the minimum value
                        best = Math.min(best, minimax(board,
                                depth + 1, !isMax,alpha,beta));
                        if(beta <= alpha){
                            fr=true;
                            break;}
                        // Undo the move
                        board[i][j] = '_';
                    }
                }
                if(fr)break;
            }
            return best;
        }
    }

    static Move findBestMove(char board[][])
    {
        int bestVal = -1000;
        Move bestMove = new Move();
        bestMove.row = -1;
        bestMove.col = -1;


        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {

                if (board[i][j] == '_')
                {
                    // Make the move
                    board[i][j] = player;

                    // compute evaluation function for this
                    // move.
                    int moveVal = minimax(board, 0, false,-1000,1000);

                    // Undo the move
                    board[i][j] = '_';

                    // If the value of the current move is
                    // more than the best value, then update
                    // best
                    if (moveVal > bestVal)
                    {
                        bestMove.row = i;
                        bestMove.col = j;
                        bestVal = moveVal;
                    }
                }
            }
        }

        bestMove.value=bestVal;
        return bestMove;
    }


    public static int[] Finder(int[]gameState,int activePlayer)
    {
        char[][]board=new char[3][3];
        int k=0;

        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                if(gameState[k]==2)
                    board[i][j]='_';
                else if(gameState[k]==1)
                    board[i][j]='o';
                else
                    board[i][j]='x';
                k++;
            }
        }
        player=activePlayer==0?'x':'o';
        opponent=activePlayer==0?'o':'x';

        Move bestMove = findBestMove(board);

        return new int[]{bestMove.row, bestMove.col,bestMove.value};
    }

}
