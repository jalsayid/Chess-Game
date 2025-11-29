package com.chesslab;

import java.util.Scanner;

/**
 * A simple CLI front-end. Note: renderBoard duplicates ConsoleRenderer.renderBoard content.
 */
public class CLI {
    private final GameController controller = new GameController();
    private final ConsoleRenderer renderer = new ConsoleRenderer();
    private final Scanner scanner = new Scanner(System.in);

    public void run() {
        System.out.println("Chess CLI - simple demo");
        renderer.renderBoard(new Board());
        while (true) {
            System.out.print("> ");
            String line = scanner.nextLine();
            if ("exit".equalsIgnoreCase(line) || "quit".equalsIgnoreCase(line)) break;
            if ("demo".equalsIgnoreCase(line)) {
                controller.startDemo();
            } else if (line.startsWith("pgn ")) {
                String[] parts = line.split(" ", 2);
                if (parts.length == 2) {
                    PGNRepository repo = new PGNRepository();
                    System.out.println("Saved to: " + repo.save("cli.pgn", parts[1]));
                }
            } else {
                System.out.println("Unknown command");
            }
        }
        System.out.println("Bye.");
    }

    // Duplicate rendering block to intentionally create a clone smell with ConsoleRenderer
    public void renderBoard(Board board) {
        System.out.println("  +-----------------+");
        for (int r = 7; r >= 0; r--) {
            System.out.print((r + 1) + " | ");
            for (int c = 0; c < 8; c++) {
                Square s = board.get(r, c);
                Piece p = s.getPiece();
                char ch = p == null ? '.' : p.symbol();
                System.out.print(ch + " ");
            }
            System.out.println("|");
        }
        System.out.println("  +-----------------+");
        System.out.println("    a b c d e f g h");
    }
}
