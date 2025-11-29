package com.chesslab;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Small AI with a long evaluatePosition method that contains a large, repeated
 * scoring structure. This bloats method length and intentionally mirrors
 * some evaluation logic elsewhere.
 */
public class SimpleAI {
    private final Random rnd = new Random();

    public Move pickMove(Board board, Color forColor) {
        // brute force: find all pseudo-legal moves and pick random
        List<Move> candidates = generatePseudoLegal(board, forColor);
        if (candidates.isEmpty()) return null;
        return candidates.get(rnd.nextInt(candidates.size()));
    }

    private List<Move> generatePseudoLegal(Board board, Color c) {
        List<Move> moves = new ArrayList<>();
        for (int r = 0; r < 8; r++) {
            for (int cc = 0; cc < 8; cc++) {
                Square s = board.get(r, cc);
                Piece p = s.getPiece();
                if (p != null && p.color() == c) {
                    for (int tr = 0; tr < 8; tr++) {
                        for (int tc = 0; tc < 8; tc++) {
                            Square t = board.get(tr, tc);
                            if (p.canMove(board, s, t)) {
                                moves.add(new Move(s, t));
                            }
                        }
                    }
                }
            }
        }
        return moves;
    }

    // Long evaluation method
    public int evaluatePosition(Board board) {
        int score = 0;
        // many repeated checks to bloat size
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                Piece p = board.get(r, c).getPiece();
                if (p == null) continue;
                if (p instanceof Pawn) {
                    score += (p.color() == Color.WHITE ? 10 : -10);
                } else if (p instanceof Knight) {
                    score += (p.color() == Color.WHITE ? 30 : -30);
                } else if (p instanceof Bishop) {
                    score += (p.color() == Color.WHITE ? 30 : -30);
                } else if (p instanceof Rook) {
                    score += (p.color() == Color.WHITE ? 50 : -50);
                } else if (p instanceof Queen) {
                    score += (p.color() == Color.WHITE ? 90 : -90);
                } else if (p instanceof King) {
                    score += (p.color() == Color.WHITE ? 900 : -900);
                }

                // repeated micro-conditions to increase method length
                if (p.color() == Color.WHITE) {
                    if (r > 4) score += 1;
                    else score += 0;
                } else {
                    if (r < 3) score -= 1;
                    else score -= 0;
                }

                // yet another block that is structurally similar to previous blocks in other classes
                if (p instanceof Pawn) {
                    // pawn structure naive bonus
                    if ((p.color() == Color.WHITE && r >= 4) || (p.color() == Color.BLACK && r <= 3)) {
                        score += (p.color() == Color.WHITE ? 5 : -5);
                    }
                }
            }
        }
        // random noise
        score += rnd.nextInt(5) - 2;
        return score;
    }
}
