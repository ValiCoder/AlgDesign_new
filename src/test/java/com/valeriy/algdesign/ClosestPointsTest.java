package com.valeriy.algdesign;

import org.junit.jupiter.api.Test;

import java.awt.Point;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ClosestPointsTest {

    @Test
    void testSmallArray() {
        Point[] points = {
                new Point(0,0),
                new Point(3,4),
                new Point(1,1),
                new Point(5,2)
        };
        double dist = ClosestPoints.closestPair(points);
        assertEquals(Math.sqrt(2), dist, 1e-6, "Closest pair failed on small array");
    }

    @Test
    void testSinglePoint() {
        Point[] points = { new Point(5,5) };
        double dist = ClosestPoints.closestPair(points);
        assertEquals(Double.MAX_VALUE, dist, 1e-6, "Single point should return MAX_VALUE");
    }

    @Test
    void testTwoPoints() {
        Point[] points = { new Point(0,0), new Point(3,4) };
        double dist = ClosestPoints.closestPair(points);
        assertEquals(5.0, dist, 1e-6, "Distance between two points incorrect");
    }

    @Test
    void testRandomPointsSubset() {
        Random rand = new Random();
        int n = 200;
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++)
            points[i] = new Point(rand.nextInt(1000), rand.nextInt(1000));

        double expected = bruteForce(points);
        double actual = ClosestPoints.closestPair(points);
        assertEquals(expected, actual, 1e-6, "Closest pair failed on random subset");
    }

    private double bruteForce(Point[] points) {
        double min = Double.MAX_VALUE;
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                int dx = points[i].x - points[j].x;
                int dy = points[i].y - points[j].y;
                double d = Math.sqrt(dx*dx + dy*dy);
                if (d < min) min = d;
            }
        }
        return min;
    }
}
