package com.Implement;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

public class ChessBoard {
    private static int sideLen = 8;
    private static int[][] chessBoard = new int[sideLen][sideLen];
    private static boolean[] access = new boolean[sideLen * sideLen];
    private static boolean finish = false;

    public static void main(String[] args) {
        int row = 5;
        int col = 5;

        long start = System.currentTimeMillis();
        traversalChessBoard(chessBoard, row - 1, col - 1, 1);
        long end = System.currentTimeMillis();

        System.out.println();
        System.out.println("Time Spent: " + (end - start) + " ms");
        System.out.println();

        for(int[] rows : chessBoard) {
            for (int step : rows) {
                System.out.print(step + "\t");
            }
            System.out.println();
        }
    }

    public static void sort(ArrayList<Point> points) {
        // Greedy Algorithm
        points.sort(new Comparator<Point>() {
            @Override
            public int compare(Point p1, Point p2) {
                // Ascending Order
                return next(p1).size() - next(p2).size();
            }
        });
    }

    public static void traversalChessBoard(int[][] chessBoard, int row, int col, int step) {
        // Depth-First Search(DFS)

        chessBoard[row][col] = step;

        access[row * sideLen + col] = true;

        // x, y
        ArrayList<Point> points = next(new Point(col, row));
        sort(points);

        while (!points.isEmpty()) {
            Point point = points.remove(0);

            if (!access[point.y * sideLen + point.x]) {
                traversalChessBoard(chessBoard, point.y, point.x, step + 1);
            }
        }

        if (step < sideLen * sideLen && !finish) {
            chessBoard[row][col] = 0;
            access[row * sideLen + col] = false;
        } else {
            finish = true;
        }
    }

    public static ArrayList<Point> next(Point curPoint) {
        ArrayList<Point> points = new ArrayList<>();

        Point point = new Point();

        // Determine whether the knight can visit the squares
        if ((point.x = curPoint.x - 2) >= 0 && (point.y = curPoint.y - 1) >= 0) {
            points.add(new Point(point));
        }
        if ((point.x = curPoint.x - 1) >= 0 && (point.y = curPoint.y - 2) >= 0) {
            points.add(new Point(point));
        }
        if ((point.x = curPoint.x + 1) < sideLen && (point.y = curPoint.y - 2) >= 0) {
            points.add(new Point(point));
        }
        if ((point.x = curPoint.x + 2) < sideLen && (point.y = curPoint.y - 1) >= 0) {
            points.add(new Point(point));
        }
        if ((point.x = curPoint.x + 2) < sideLen && (point.y = curPoint.y + 1) < sideLen) {
            points.add(new Point(point));
        }
        if ((point.x = curPoint.x + 1) < sideLen && (point.y = curPoint.y + 2) < sideLen) {
            points.add(new Point(point));
        }
        if ((point.x = curPoint.x - 1) >= 0 && (point.y = curPoint.y + 2) < sideLen) {
            points.add(new Point(point));
        }
        if ((point.x = curPoint.x - 2) >= 0 && (point.y = curPoint.y + 1) < sideLen) {
            points.add(new Point(point));
        }

        return points;
    }
}
