package com.valeriy.algdesign;

import java.awt.Point;
import java.util.Arrays;
import java.util.Comparator;

public class ClosestPoints {

    public static double closestPair(Point[] points) {
        if (points == null || points.length < 2) return Double.MAX_VALUE;

        // сортировка по x
        Point[] px = Arrays.copyOf(points, points.length);
        Arrays.sort(px, Comparator.comparingInt(p -> p.x));

        return closestPairRec(px, 0, px.length - 1);
    }

    private static double closestPairRec(Point[] points, int left, int right) {
        if (right - left <= 3) {
            return bruteForce(points, left, right);
        }

        int mid = left + (right - left) / 2;
        double dl = closestPairRec(points, left, mid);
        double dr = closestPairRec(points, mid + 1, right);
        double d = Math.min(dl, dr);

        // полоска
        int size = 0;
        Point[] strip = new Point[right - left + 1];
        for (int i = left; i <= right; i++) {
            if (Math.abs(points[i].x - points[mid].x) < d) {
                strip[size++] = points[i];
            }
        }

        // сортировка полоски по y
        Arrays.sort(strip, 0, size, Comparator.comparingInt(p -> p.y));

        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size && (strip[j].y - strip[i].y) < d; j++) {
                double dist = distance(strip[i], strip[j]);
                if (dist < d) d = dist;
            }
        }

        return d;
    }

    private static double bruteForce(Point[] points, int left, int right) {
        double min = Double.MAX_VALUE;
        for (int i = left; i <= right; i++) {
            for (int j = i + 1; j <= right; j++) {
                double dist = distance(points[i], points[j]);
                if (dist < min) min = dist;
            }
        }
        return min;
    }

    private static double distance(Point p1, Point p2) {
        int dx = p1.x - p2.x;
        int dy = p1.y - p2.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    // тестовый main
    public static void main(String[] args) {
        Point[] points = {
                new Point(0,0),
                new Point(3,4),
                new Point(1,1),
                new Point(5,2)
        };

        System.out.println("Closest distance: " + closestPair(points));
    }
}
