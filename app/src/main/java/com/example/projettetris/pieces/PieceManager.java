package com.example.projettetris.pieces;

import android.graphics.Color;
import android.widget.Button;

import com.example.projettetris.Clock;
import com.example.projettetris.GameActivity;
import com.example.projettetris.GridAdapter;

import java.sql.Array;
import java.util.ArrayList;

public class PieceManager {

    private int[][] gameGrid;
    private GridAdapter gridAdapter;
    private Piece currentPiece;
    private GameActivity gameActivity;
    private ArrayList<Piece> allPiece = new ArrayList<Piece>();
    private int score = 0;

    public PieceManager(int[][] gameGrid, GridAdapter gridAdapter) {
        this.gameGrid = gameGrid;
        this.gridAdapter = gridAdapter;
    }

    public void createPiece() {
        Piece piece = getRandomPiece();
        int rand = (int) (Math.random() * 9);
        int randX = (rand + piece.getLargeur()) < 10 ? rand : 0;
        piece.setPos_x(randX);
        piece.setPos_y(0);
        allPiece.add(piece);
        setCurrentPiece(piece);
        gameActivity.setScore(score + " pts");
    }

    private boolean canCreate() {
        for(int i = 0; i<10; i++) {
            if (gameGrid[0][i] != 0) {
                return false;
            }
        }
        return true;
    }

    private Piece getRandomPiece() {
        int rand = ((int) (Math.random() * 4) + 1);
        Piece piece;
        switch (rand) {
            case 1:
                piece = new Piece_I(0, 0, (int) ((Math.random() * 4) + 1), gameGrid);
                break;
            case 2:
                piece = new Piece_L(0, 0, (int) ((Math.random() * 4) + 1), gameGrid);
                break;
            case 3:
                piece = new Piece_S(0, 0, (int) ((Math.random() * 4) + 1), gameGrid);
                break;
            case 4:
                piece = new Piece_Square(0, 0, (int) ((Math.random() * 4) + 1), gameGrid);
                break;
            default:
                piece = null;
                break;
        }
        return piece;
    }

    public void setCurrentPiece(Piece currentPiece) {
        this.currentPiece = currentPiece;
        if(currentPiece!=null) {
            this.drawPieceOnGrid(currentPiece);
        }
    }

    public void setGameActivity(GameActivity gameActivity) {
        this.gameActivity = gameActivity;
    }

    private void drawPieceOnGrid(Piece piece) {
        for (int li = 0; li < piece.getHauteur(); li++) {
            for (int col = 0; col < piece.getLargeur(); col++) {
                if(piece.getMatrice()[li][col] != 0) {
                    if(gameGrid[li + piece.getPos_y()][col + piece.getPos_x()] == 0) {
                        gameGrid[li + piece.getPos_y()][col + piece.getPos_x()] = piece.getMatrice()[li][col];
                    }
                }
            }
        }
        this.gridAdapter.notifyDataSetChanged();
    }

    private void removePieceFromGrid(Piece piece) {
        for (int li = 0; li < piece.getHauteur(); li++) {
            for (int col = 0; col < piece.getLargeur(); col++) {
                if(piece.getMatrice()[li][col] != 0) {
                    gameGrid[li + piece.getPos_y()][col + piece.getPos_x()] = 0;
                }
            }
        }
        this.gridAdapter.notifyDataSetChanged();
    }

    public void rotate() {
        if (this.currentPiece.canRotate()) {
            this.removePieceFromGrid(currentPiece);
            this.currentPiece.rotate();
            this.gridAdapter.notifyDataSetChanged();
            this.drawPieceOnGrid(currentPiece);
        }
    }

    public void left() {
        if (this.currentPiece.canLeft()) {
            this.removePieceFromGrid(currentPiece);
            this.currentPiece.left();
            this.gridAdapter.notifyDataSetChanged();
            this.drawPieceOnGrid(currentPiece);
        }
    }

    public void right() {
        if (this.currentPiece.canRight()) {
            this.removePieceFromGrid(currentPiece);
            this.currentPiece.right();
            this.gridAdapter.notifyDataSetChanged();
            this.drawPieceOnGrid(currentPiece);
        }
    }

    public void down() {
        this.removePieceFromGrid(currentPiece);
        if (this.currentPiece.canDown()) {
            this.currentPiece.down();
            this.drawPieceOnGrid(currentPiece);
        } else {
            this.drawPieceOnGrid(currentPiece);
            while(canRemoveLine() != -1) {
                removeLine(canRemoveLine());
            }
            if(canCreate()) {
                createPiece();
            } else {
                gridEnd();
                this.gridAdapter.notifyDataSetChanged();
                this.gameActivity.gameRunningChange();
            }
        }
    }

    private void removeLine(int line) {
        for(int i = 0; i<10; i++) {
            gameGrid[line][i] = 0;
        }

        for (int li = 0; li < line; li++) {
            for (int col = 0; col < 10; col++) {
                gameGrid[line-li][col] = gameGrid[line-li-1][col];
            }
        }
        score+=10;
    }

    private int canRemoveLine() {
        int cpt = 0;
        for (int li = 0; li < 17; li++) {
            for (int col = 0; col < 10; col++) {
                if(col == 0) cpt = 0;
                if(gameGrid[li][col] != 0) {
                    cpt++;
                }
                if(cpt == 10) {
                    return li;
                }
            }
        }
        return -1;
    }

    private void gridEnd() {
        for (int li = 0; li < 17; li++) {
            for (int col = 0; col < 10; col++) {
                if (gameGrid[li][col] != 0) {
                    gameGrid[li][col] = 1;
                } else {
                    gameGrid[li][col] = Color.DKGRAY;
                }

            }
        }
    }

    public int getScore() {
        return score;
    }

}


