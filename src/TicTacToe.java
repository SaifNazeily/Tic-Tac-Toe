import javax.swing.*;


public class TicTacToe {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Tic-Tac-Toe");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 400); // Adjust size as needed

            GameBoard gameBoard = new GameBoard();
            frame.add(gameBoard);

            frame.setLocationRelativeTo(null); // Center the frame on the screen
            frame.setVisible(true);
        });
    }
}
