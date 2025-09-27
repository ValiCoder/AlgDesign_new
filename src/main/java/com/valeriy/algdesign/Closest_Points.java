package com.valeriy.algdesign;

import java.util.*;

public class Closest_Points {

    public static class Point {
        double x, y;
        Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    static double dist(Point a, Point b) {
        double dx = a.x - b.x;
        double dy = a.y - b.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    static double bruteForce(Point[] pts, int left, int right) {
        double min = Double.POSITIVE_INFINITY;
        for (int i = left; i <= right; i++) {
            for (int j = i + 1; j <= right; j++) {
                min = Math.min(min, dist(pts[i], pts[j]));
            }
        }
        return min;
    }

    static double stripClosest(List<Point> strip, double d) {
        double min = d;
        int n = strip.size();

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n && (strip.get(j).y - strip.get(i).y) < min; j++) {
                min = Math.min(min, dist(strip.get(i), strip.get(j)));
            }
        }
        return min;
    }

    static double closestUtil(Point[] px, Point[] py, int left, int right) {
        if (right - left <= 3) {
            return bruteForce(px, left, right);
        }

        int mid = (left + right) / 2;
        Point midPoint = px[mid];

        List<Point> Qy = new ArrayList<Point>();
        List<Point> Ry = new ArrayList<Point>();
        for (Point p : py) {
            if (p.x <= midPoint.x) Qy.add(p);
            else Ry.add(p);
        }

        double dl = closestUtil(px, Qy.toArray(new Point[0]), left, mid);
        double dr = closestUtil(px, Ry.toArray(new Point[0]), mid + 1, right);
        double d = Math.min(dl, dr);

        List<Point> strip = new ArrayList<Point>();
        for (Point p : py) {
            if (Math.abs(p.x - midPoint.x) < d) {
                strip.add(p);
            }
        }

        return Math.min(d, stripClosest(strip, d));
    }

    public static double closestPair(Point[] points) {
        Point[] px = points.clone();
        Point[] py = points.clone();

        Arrays.sort(px, new Comparator<Point>() {
            public int compare(Point p1, Point p2) {
                return Double.compare(p1.x, p2.x);
            }
        });
        Arrays.sort(py, new Comparator<Point>() {
            public int compare(Point p1, Point p2) {
                return Double.compare(p1.y, p2.y);
            }
        });

        return closestUtil(px, py, 0, points.length - 1);
    }

    public static void main(String[] args) {
        Point[] points = {
                new Point(2.1, 3.1),
                new Point(12.3, 30.5),
                new Point(40.0, 50.2),
                new Point(5.0, 1.0),
                new Point(12.0, 10.0),
                new Point(3.0, 4.0)
        };

        double result = closestPair(points);
        System.out.printf("The closest distance is %.4f%n", result);
    }
}
