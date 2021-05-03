package com.example.projettetris.pieces;

public class Piece_L extends Piece {

    public Piece_L(int pos_x, int pos_y, int color, int[][] grid) {
        super(3, 2, new int[3][2], pos_x, pos_y, color, grid);
        createMatrix();
    }

    public void createMatrix() {
        for(int i = 0; i<hauteur; i++) {
            for(int j = 0; j<largeur; j++) {
                matrice[i][j] = i == 0 || j==0 ? color : 0;
            }
        }
    }


}
