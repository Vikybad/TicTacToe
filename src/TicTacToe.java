import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class TicTacToe {

	int boardWidth = 600;
	int boardHeight = 650;

	JFrame frame = new JFrame("FrameDemo");
	JLabel textLabel = new JLabel();
	JPanel textPanel = new JPanel();
	JPanel boardPanel = new JPanel();
	
	JButton[][] board = new JButton[3][3];
	String playerX = "X";
	String playerO = "O";
	String currentPlayer = playerX;
	boolean gameOver = false;
	int turns = 0;
	
	public TicTacToe() {
		frame.setVisible(true);
		frame.setSize(boardWidth, boardHeight);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		
		textLabel.setBackground(Color.darkGray);
		textLabel.setForeground(Color.white);
		textLabel.setFont(new Font("Arial", Font.BOLD, 50));
		textLabel.setHorizontalAlignment(JLabel.CENTER);
		textLabel.setText("Tic-Tac-Toe");
		textLabel.setOpaque(true);
		
		textPanel.setLayout(new BorderLayout());
		textPanel.add(textLabel);
		frame.add(textLabel, BorderLayout.NORTH);
		
		boardPanel.setLayout(new GridLayout(3, 3));
		boardPanel.setBackground(Color.LIGHT_GRAY);
		frame.add(boardPanel);
		
		for (int r=0; r<3; r++) {
			for (int c=0; c<3; c++) {
				JButton tile = new JButton();
				board[r][c] = tile;
				boardPanel.add(tile);
				
				tile.setBackground(Color.DARK_GRAY);
				tile.setForeground(Color.WHITE);
				tile.setFont(new Font("Arial", Font.BOLD, 120));
				tile.setFocusable(false);

				tile.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						if (gameOver) return;

						JButton tile = (JButton) e.getSource();
						String currentValue = tile.getText();
						if (currentValue == "") {
							tile.setText(currentPlayer);
							++turns;
							checkWinner();

							if (!gameOver) {
								currentPlayer = currentPlayer == playerX ? playerO : playerX;
								textLabel.setText(currentPlayer + "'s turn");								
							}
						}

					}

				});
			}
		}
	}
	
	void checkWinner() {
		
		// Horizontal
		for (int r=0; r<3; r++) {
			if (board[r][0].getText() == "") continue;
			
			if (board[r][0].getText() == board[r][1].getText() && board[r][1].getText() == board[r][2].getText()) {
				for (int c=0; c<3; c++) {
					setWinner(board[r][c]);
				}
				gameOver = true;
				String winner = board[r][0].getText();
				textLabel.setText(winner + " is winner");
				return;
			}
		}

		// Vertical
		for (int c=0; c<3; c++) {
			if (board[0][c].getText() == "") continue;
			if (board[0][c].getText() == board[1][c].getText() && board[1][c].getText() == board[2][c].getText()) {
				for (int r=0; r<3; r++) {
					setWinner(board[r][c]);
				}
				gameOver = true;
				String winner = board[0][c].getText();
				textLabel.setText(winner + " is winner");
				return;
			}
		}
		
		
		// Diagonally
		if (
			board[0][0].getText() != "" &&
			board[0][0].getText() == board[1][1].getText() &&
			board[1][1].getText() == board[2][2].getText()
		) {
			
			for (int i=0; i<3; i++) {
				setWinner(board[i][i]);
			}
			gameOver = true;
			String winner = board[0][0].getText();
			textLabel.setText(winner + " is winner");
			return;
		}

		
		// Anti-Diagonally
		if (
			board[0][2].getText() != "" &&
			board[0][2].getText() == board[1][1].getText() &&
			board[1][1].getText() == board[2][0].getText()
		) {
			setWinner(board[0][2]);
			setWinner(board[1][1]);
			setWinner(board[2][0]);
			gameOver = true;
			String winner = board[0][2].getText();
			textLabel.setText(winner + " is winner");
			return;
		}

		if (turns == 9) {
			textLabel.setText("Game Over");
			gameOver = true;
			for (int r=0; r<3; r++) {
				for (int c=0; c<3; c++) {
					setTie(board[r][c]);
				}
			}
			return;
		}
	}
	
	void setWinner(JButton tile) {
		tile.setBackground(Color.GREEN);
		tile.setForeground(Color.GRAY);
	}
	
	void setTie(JButton tile) {
		tile.setBackground(Color.ORANGE);
		tile.setForeground(Color.GRAY);
	}
}
