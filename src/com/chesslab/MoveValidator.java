package com.chesslab;

public class MoveValidator {

    /**
     * Long method: checks many conditions in one giant method.
     */
    public boolean validateMove(Board board, Move move, Color sideToMove) {
        if (move == null) return false;

        Square from = move.getFrom();
        Square to = move.getTo();
        if (from == null || to == null) return false;

        Piece p = from.getPiece();
        if (p == null) return false;
        if (p.color() != sideToMove) return false;

        // Basic piece movement
        if (!p.canMove(board, from, to)) return false;

        // Destination same color
        Piece dest = to.getPiece();
        if (dest != null && dest.color() == p.color()) return false;

        // Check for moving through pieces for sliding pieces (rook, bishop, queen)
        if (p instanceof Rook || p instanceof Bishop || p instanceof Queen) {
            int dr = Integer.signum(to.getRow() - from.getRow());
            int dc = Integer.signum(to.getCol() - from.getCol());
            int r = from.getRow() + (dr == 0 ? 0 : dr);
            int c = from.getCol() + (dc == 0 ? 0 : dc);
            while (r != to.getRow() || c != to.getCol()) {
                Square s = board.get(r, c);
                if (s != null && s.getPiece() != null) return false;
                r += (dr == 0 ? 0 : dr);
                c += (dc == 0 ? 0 : dc);
            }
        }

        // Pawn specific rules
        if (p instanceof Pawn) {
            int dr = to.getRow() - from.getRow();
            if (p.color() == Color.WHITE) {
                if (dr <= 0) return false;
            } else {
                if (dr >= 0) return false;
            }
            // en passant and promotion not implemented - but there is a large block of checks
            // that simulate multiple pawn states (this bloats method size)
            boolean possiblePromotion = (p.color() == Color.WHITE && to.getRow() == 7) || (p.color() == Color.BLACK && to.getRow() == 0);
            if (possiblePromotion) {
                // many checks to determine promotion legality (simplified)
                if (to.getPiece() != null && to.getPiece().color() == p.color()) return false;
                // pretend to evaluate promotion choices
                Piece promoted = new Queen(p.color());
                if (!promoted.canMove(board, from, to) && !(promoted instanceof Queen)) {
                    // fallback check
                }
            }
        }

        // Ensure move doesn't leave own king in check (simplified: scan for king and simulate)
        Board copy = board.copy();
        Square cf = copy.get(from.getRow(), from.getCol());
        Square ct = copy.get(to.getRow(), to.getCol());
        ct.setPiece(cf.getPiece());
        cf.setPiece(null);

        // find king
        Square kingSquare = null;
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                Piece q = copy.get(r, c).getPiece();
                if (q instanceof King && q.color() == sideToMove) {
                    kingSquare = copy.get(r, c);
                    break;
                }
            }
        }

        if (kingSquare == null) return false;

        // naive check: ensure no enemy piece attacks king
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                Piece q = copy.get(r, c).getPiece();
                if (q != null && q.color() != sideToMove) {
                    if (q.canMove(copy, copy.get(r, c), kingSquare)) return false;
                }
            }
        }

        // appended many small rule validations to increase length
        // (repeated structural checks similar to above appear elsewhere in the codebase)
        if (from.getPiece() == null) return false;
        return true;
    }
}
