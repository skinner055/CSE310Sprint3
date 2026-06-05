import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

class Cell {
    boolean isMine;
    boolean isRevealed;
    boolean isFlagged;
    int neighboringMines;

    Cell() {
        isMine = false;
        isRevealed = false;
        isFlagged = false;
        neighboringMines = 0;
    }
}

public class Minesweeper {
    private Cell[][] board;
    private int rows, cols, totalMines;

    public Minesweeper(int rows, int cols, int mines) {
        this.rows = rows;
        this.cols = cols;
        this.totalMines = mines;
        board = new Cell[rows][cols];
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                board[i][j] = new Cell();

        placeMines();
        calculateNeighbors();
    }

    private void placeMines() {
        ArrayList<Integer> minePositions = new ArrayList<>();
        while (minePositions.size() < totalMines) {
            int pos = (int) (Math.random() * rows * cols);
            if (!minePositions.contains(pos)) {
                minePositions.add(pos);
                int r = pos / cols;
                int c = pos % cols;
                board[r][c].isMine = true;
            }
        }
    }

    private void calculateNeighbors() {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (!board[r][c].isMine) {
                    int count = 0;
                    for (int i = -1; i <= 1; i++) {
                        for (int j = -1; j <= 1; j++) {
                            int nr = r + i;
                            int nc = c + j;
                            if (nr >= 0 && nc >= 0 && nr < rows && nc < cols && board[nr][nc].isMine)
                                count++;
                        }
                    }
                    board[r][c].neighboringMines = count;
                }
            }
        }
    }

    private void printBoard(boolean revealAll) {
        System.out.print("  ");
        for (int c = 0; c < cols; c++) System.out.print(c + " ");
        System.out.println();
        for (int r = 0; r < rows; r++) {
            System.out.print(r + " ");
            for (int c = 0; c < cols; c++) {
                Cell cell = board[r][c];
                if (revealAll) {
                    System.out.print(cell.isMine ? "*" : cell.neighboringMines);
                } else {
                    if (cell.isRevealed) {
                        System.out.print(cell.neighboringMines);
                    } else if (cell.isFlagged) {
                        System.out.print("F");
                    } else {
                        System.out.print(".");
                    }
                }
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    private void revealCell(int r, int c) {
        if (r < 0 || c < 0 || r >= rows || c >= cols || board[r][c].isRevealed || board[r][c].isFlagged)
            return;
        board[r][c].isRevealed = true;
        if (board[r][c].neighboringMines == 0 && !board[r][c].isMine) {
            // recursively reveal neighbors
            for (int i = -1; i <= 1; i++)
                for (int j = -1; j <= 1; j++)
                    revealCell(r + i, c + j);
        }
    }

    private boolean checkWin() {
        for (int r = 0; r < rows; r++)
            for (int c = 0; c < cols; c++)
                if (!board[r][c].isMine && !board[r][c].isRevealed)
                    return false;
        return true;
    }

    // Stretch: save/load game to file
    public void saveToFile(String filename) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filename))) {
            pw.println(rows + " " + cols + " " + totalMines);
            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < cols; c++) {
                    Cell cell = board[r][c];
                    pw.print((cell.isMine ? 1 : 0) + " ");
                    pw.print((cell.isRevealed ? 1 : 0) + " ");
                    pw.print((cell.isFlagged ? 1 : 0) + " ");
                    pw.print(cell.neighboringMines + " ");
                }
                pw.println();
            }
            System.out.println("Game saved to " + filename);
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Minesweeper game = new Minesweeper(10, 10, 20);

        while (true) {
            game.printBoard(false);
            System.out.println("Enter command (r row col = reveal, f row col = flag, s filename = save):");
            String input = sc.nextLine();
            String[] parts = input.split(" ");
            if (parts[0].equalsIgnoreCase("r")) {
                int r = Integer.parseInt(parts[1]);
                int c = Integer.parseInt(parts[2]);
                if (game.board[r][c].isMine) {
                    System.out.println("Boom! You hit a mine!");
                    game.printBoard(true);
                    break;
                }
                game.revealCell(r, c);
                if (game.checkWin()) {
                    System.out.println("Congratulations! You cleared the board!");
                    game.printBoard(true);
                    break;
                }
            } else if (parts[0].equalsIgnoreCase("f")) {
                int r = Integer.parseInt(parts[1]);
                int c = Integer.parseInt(parts[2]);
                game.board[r][c].isFlagged = !game.board[r][c].isFlagged;
            } else if (parts[0].equalsIgnoreCase("s")) {
                game.saveToFile(parts[1]);
            } else {
                System.out.println("Invalid command.");
            }
        }
        sc.close();
    }
}