package GameLogic;

public class Board {
    int table[][];
    int tableSize;
    Player lastPlayer;
    public Board(){
        tableSize = 16;
        table = new int[tableSize][tableSize];
    }
    public String makeMove(int posX, int posY,Player player){
        if (table[posX][posY] == 0){
            table[posX][posY] = player.getColor();
            lastPlayer = player;
            return "Done";
        }
        return "This cell is occupaid";
    }
    public Boolean checkForWinner(){
        int count=0;
        for (int row=1;row<tableSize;row++) {
            count = 1;
            for(int col=2;col<tableSize;col++){
                if (table[row][col]==table[row][col-1] && table[row][col]!=0 )count++; else count = 1;
                if(count == 5) return true;
            }
        }
        for (int col=2;col<tableSize;col++) {
            count = 1;
            for(int row=1;row<tableSize;row++){
                if (table[row][col]==table[row][col-1] && table[row][col]!=0 )count++; else count = 1;
                if(count == 5) return true;
            }
        }
        return false;
    }

    public Player getLastPlayer() {
        return lastPlayer;
    }

    public int[][] getTable() {
        return table;
    }

    public int getTableSize() {
        return tableSize;
    }
}
