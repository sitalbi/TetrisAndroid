package com.example.projettetris.pieces;

public class Piece_Z extends Piece {

    public Piece_Z(int pos_x, int pos_y, int color, int[][] grid) {
        super(2, 3, new int[2][3], pos_x, pos_y, color, grid);
        createMatrix();
    }

    public void createMatrix() {
        for(int i = 0; i<hauteur; i++) {
            for(int j = 0; j<largeur; j++) {
                matrice[i][j] = i == 0 ? color : (i != 0 && j==0 ? color : 0);
            }
        }
    }
}
