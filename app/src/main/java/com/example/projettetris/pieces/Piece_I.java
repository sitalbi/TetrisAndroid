package com.example.projettetris.pieces;

public class Piece_I  extends Piece {

    public Piece_I(int pos_x, int pos_y, int color, int[][] grid) {
        super(4, 1, new int[4][1], pos_x, pos_y, color, grid);
        createMatrix();
    }

    public void createMatrix() {
        for(int i = 0; i<hauteur; i++) {
                matrice[i][0] = color;
        }
    }
}
