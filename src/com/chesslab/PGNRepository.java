package com.chesslab;

public class PGNRepository {
    private final FileRepository fileRepo = new FileRepository();

    public String save(String filename, String pgn) {
        boolean ok = fileRepo.save(filename, pgn);
        return ok ? filename : null;
    }

    public String load(String filename) {
        return fileRepo.load(filename);
    }
}
