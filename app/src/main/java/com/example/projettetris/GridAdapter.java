package com.example.projettetris;

import android.content.Context;

import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;

import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class GridAdapter extends BaseAdapter {
    private Context mContext;
    private int[][] universe;
    private int width;
    private int height;
    final private ColorDrawable BLUE = new ColorDrawable(Color.rgb(0, 100, 200));
    final private ColorDrawable RED = new ColorDrawable(Color.rgb(200, 0, 0));
    final private ColorDrawable GREEN = new ColorDrawable(Color.rgb(0, 200, 0));
    final private ColorDrawable WHITE = new ColorDrawable(Color.rgb(255, 255, 255));
    final private ColorDrawable BLACK = new ColorDrawable(Color.rgb(0, 0, 0));

    public GridAdapter(Context c, int[][] listImage) {
        mContext = c;
        universe = listImage;

        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        width = size.x;
        height = size.y;
    }

    @Override
    public int getCount() {
        return 170;
    }


    public Integer getItem(int position) {
        return universe[(int) Math.floor(position / 10.0)][position % 10];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    public View getView(int position, View convertView, ViewGroup parent) {

        ImageView imageView;

        imageView = new ImageView(mContext);
        switch (universe[(int) Math.floor(position / 10.0)][position % 10]) {
            case 0:
                imageView.setImageDrawable(BLACK);
                break;
            case 1:
                imageView.setImageDrawable(WHITE);
                break;
            case 2:
                imageView.setImageDrawable(RED);
                break;
            case 3:
                imageView.setImageDrawable(GREEN);
                break;
            case 4:
                imageView.setImageDrawable(BLUE);
                break;
        }
        imageView.setScaleType(ImageView.ScaleType.CENTER);
        imageView.setMinimumHeight(height / 20);
        imageView.setMaxHeight(height / 20);
        imageView.setMinimumWidth(width / 10);
        imageView.setMaxWidth(width / 10);

        return imageView;
    }

}