public class Main {

    public static void main(String[] args) {
        //Run the game, can edit board lenfth and bomb count to alter difficulty

        //Max board length at 10
        int boardLength = 8;
        int bombCount = 10;
        MinesweeperGame game = new MinesweeperGame(boardLength, bombCount);
        game.playGame();
    }
}