package com.example.projettetris.pieces;

import com.example.projettetris.Movement;

public class Piece implements Movement {
    protected int hauteur;
    protected int largeur;
    protected int[][] matrice;
    protected int pos_x;
    protected int pos_y;
    protected int color;
    private int[][] grid;

    public Piece(int hauteur, int largeur, int[][] matrice, int pos_x, int pos_y, int color, int[][] grid) {
        this.hauteur = hauteur;
        this.largeur = largeur;
        this.matrice = matrice;
        this.pos_x = pos_x;
        this.pos_y = pos_y;
        this.color = color;
        this.grid = grid;
    }

    public int getHauteur() {
        return hauteur;
    }

    public void setHauteur(int hauteur) {
        this.hauteur = hauteur;
    }

    public int getLargeur() {
        return largeur;
    }

    public void setLargeur(int largeur) {
        this.largeur = largeur;
    }

    public int[][] getMatrice() {
        return matrice;
    }

    public void setMatrice(int[][] matrice) {
        this.matrice = matrice;
    }

    public int getPos_x() {
        return pos_x;
    }

    public void setPos_x(int pos_x) {
        this.pos_x = pos_x;
    }

    public int getPos_y() {
        return pos_y;
    }

    public void setPos_y(int pos_y) {
        this.pos_y = pos_y;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    @Override
    public void rotate() {
        this.matrice = getRotation();
    }

    protected int[][] getRotation() {
        final int rows = this.matrice.length;
        final int columns = this.matrice[0].length;

        int[][] resultMatrix = new int[columns][rows];

        for (int pieceYCoordinates = 0; pieceYCoordinates < rows; pieceYCoordinates++) {
            for (int pieceXCoordinates = 0; pieceXCoordinates < columns; pieceXCoordinates++) {
                resultMatrix[pieceXCoordinates][rows - 1 - pieceYCoordinates] = this.matrice[pieceYCoordinates][pieceXCoordinates];
            }
        }
        int tmp = this.largeur;
        this.largeur = hauteur;
        this.hauteur = tmp;
        return resultMatrix;
    }

    @Override
    public void left() {
        this.pos_x--;
    }

    @Override
    public void right() {
        this.pos_x++;
    }

    @Override
    public void down() {
        this.pos_y++;
    }

    @Override
    public boolean canRotate() {
        return this.pos_y + this.largeur < 17 && this.pos_x + this.hauteur < 10 && grid[this.pos_y + this.hauteur][this.pos_x] == 0;
    }

    @Override
    public boolean canLeft() {
        if (this.pos_x > 0) {
            for (int li = 0; li < this.getHauteur(); li++) {
                for (int col = 0; col < this.getLargeur(); col++) {
                    if (grid[this.pos_y + li][this.pos_x - 1] != 0) {
                        return false;
                    }
                }
            }
        } else {
            return false;
        }
        return true;
    }

    @Override
    public boolean canRight() {
        if (this.pos_x + this.largeur < 10) {
            for (int li = 0; li < this.getHauteur(); li++) {
                for (int col = 0; col < this.getLargeur(); col++) {
                    if (grid[this.pos_y + li][this.pos_x + this.largeur] != 0) {
                        return false;
                    }
                }
            }
        } else {
            return false;
        }
        return true;
    }

    @Override
    public boolean canDown() {
        if(this.pos_y + this.hauteur < 17) {
            for (int li = 0; li < this.getHauteur(); li++) {
                for (int col = 0; col < this.getLargeur(); col++) {
                    if (grid[this.pos_y + li + 1][this.pos_x + col] != 0) {
                        if(matrice[li ][col] != 0) {
                            return false;
                        }
                    }
                }
            }
        } else {
            return false;
        }
        return true;
    }
}
