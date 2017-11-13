package com.vv.zvv.JavaBean;

/**
 * description:
 * author: zvv
 * date: 2017/11/13 13:52
 * update: 2017/11/13
 * version: 0.0
 */
public class CircularAnimationChartViewItem {
    public int color;
    public double value;
    public String title;

    public CircularAnimationChartViewItem(String title, double value, int color) {
        this.color = color;
        this.value = value;
        this.title = title;
    }
}
