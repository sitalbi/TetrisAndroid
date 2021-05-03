package com.example.projettetris;

public interface Movement {
    void rotate();
    void left();
    void right();
    void down();
    boolean canRotate();
    boolean canLeft();
    boolean canRight();
    boolean canDown();
}
