package com.example.projettetris.pieces;

public class Piece_Square extends Piece {

    public Piece_Square(int pos_x, int pos_y, int color, int[][] grid) {
        super(2, 2, new int[2][2], pos_x, pos_y, color, grid);
        createMatrix();
    }

    public void createMatrix() {
        for(int i = 0; i<hauteur; i++) {
            for(int j = 0; j<largeur; j++) {
                matrice[i][j] = color;
            }
        }
    }
}
