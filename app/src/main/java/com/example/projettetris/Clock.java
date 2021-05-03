package com.example.projettetris;

import android.os.Handler;
import android.widget.TextView;

import androidx.core.util.Supplier;

import com.example.projettetris.pieces.PieceManager;

public class Clock {

    private final PieceManager pieceManager;
    private Handler handler;

    private Supplier<Boolean> runnable;

    private boolean running;

    private long delay;

    private float factor;

    private TextView chrono;

    private double time;

    public Clock(Supplier<Boolean> runnable, long delay, PieceManager pieceManager, TextView chrono) {
        this.handler = new Handler();
        this.runnable = runnable;
        this.running = false;
        this.delay = delay;
        this.factor = 1.0f;
        this.pieceManager = pieceManager;
        this.chrono = chrono;
        this.time = 0;
    }

    public synchronized void start() {
        running = true;
        handler.removeCallbacksAndMessages(null);
        run();
    }

    public synchronized void run() {
        handler.postDelayed(() -> {
            if(running) {
                running = runnable.get();
                pieceManager.down();
                time += (double)delay/1000;
                if(delay > 300) { // Vitesse de chute des pieces max
                    if((int)time%10 == 0) {
                        setDelay(delay - 50);
                    }
                }
                String timing = Integer.toString((int)time);
                chrono.setText(timing + " s");
                run();
            }
        }, getAcceleratedDelay());
    }

    public synchronized void pause() {
        running = false;
        handler.removeCallbacksAndMessages(null);
    }


    public synchronized long getAcceleratedDelay() {
        return (long) (delay * factor);
    }

    public synchronized long getDelay() {
        return delay;
    }

    public synchronized void setDelay(long delay) {
        this.delay = delay;
    }

    public synchronized void accelerate(float factor) {
        this.factor /= factor;
    }

    public synchronized void decelerate() {
        this.factor = 1.0f;
    }

    public synchronized boolean isAccelerated() {
        return this.factor != 1.0f;
    }

    public boolean isRunning() {
        return running;
    }

    public double getTime() {
        return time;
    }

}
