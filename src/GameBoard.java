import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameBoard extends JPanel {
    private final JButton[][] buttons;
    private boolean xTurn = true;

    public GameBoard() {
        setLayout(new GridLayout(3, 3));
        buttons = new JButton[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setFont(new Font("Arial", Font.BOLD, 80));
                buttons[i][j].setFocusPainted(false);
                buttons[i][j].setBackground(Color.WHITE);
                buttons[i][j].setForeground(Color.BLACK);
                buttons[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
                buttons[i][j].addActionListener(new ButtonClickListener(i, j));
                add(buttons[i][j]);
            }
        }
    }

    private class ButtonClickListener implements ActionListener {
        private final int row;
        private final int col;

        public ButtonClickListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (buttons[row][col].getText().isEmpty() && !isGameOver()) {
                buttons[row][col].setText(xTurn ? "X" : "O");
                buttons[row][col].setForeground(xTurn ? Color.RED : Color.BLUE);
                xTurn = !xTurn;
                checkForWinner();
            }
        }
    }

    private boolean isGameOver() {
        return checkWin("X") || checkWin("O") || isBoardFull();
    }

    private boolean checkWin(String player) {
        for (int i = 0; i < 3; i++) {
            if (buttons[i][0].getText().equals(player) &&
                    buttons[i][1].getText().equals(player) &&
                    buttons[i][2].getText().equals(player)) {
                return true;
            }
        }

        for (int i = 0; i < 3; i++) {
            if (buttons[0][i].getText().equals(player) &&
                    buttons[1][i].getText().equals(player) &&
                    buttons[2][i].getText().equals(player)) {
                return true;
            }
        }

        if (buttons[0][0].getText().equals(player) &&
                buttons[1][1].getText().equals(player) &&
                buttons[2][2].getText().equals(player)) {
            return true;
        }

        return buttons[0][2].getText().equals(player) &&
                buttons[1][1].getText().equals(player) &&
                buttons[2][0].getText().equals(player);
    }

    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getText().isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    private void checkForWinner() {
        if (checkWin("X")) {
            JOptionPane.showMessageDialog(this, "Player X wins!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
            resetBoard();
        } else if (checkWin("O")) {
            JOptionPane.showMessageDialog(this, "Player O wins!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
            resetBoard();
        } else if (isBoardFull()) {
            JOptionPane.showMessageDialog(this, "It's a draw!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
            resetBoard();
        }
    }

    private void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }
        xTurn = true; // Reset turn to X
    }
}
