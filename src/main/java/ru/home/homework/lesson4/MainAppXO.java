package ru.home.homework.lesson4;

import java.util.Random;
import java.util.Scanner;

public class MainAppXO {
    public static Random random = new Random();
    public static Scanner scanner = new Scanner(System.in);
    public static char[][] field;

    public static final int SIZE = 3;
    public static final char FIELD_ELEMENT_EMPTY = '_';
    public static final char FIELD_ELEMENT_X = 'X';
    public static final char FIELD_ELEMENT_0 = '0';

    public static void main(String[] args) {

        /*
        1. Полностью разобраться с кодом, попробовать переписать с нуля, стараясь не подглядывать в
        методичку.
        2. Переделать проверку победы, чтобы она не была реализована просто набором условий,
        например, с использованием циклов.
        */
        createArray();
        printField();

        while (true) {
            playerTurn();
            printField();
            if (winCondition(FIELD_ELEMENT_X)) {
                System.out.println("Human WIN");
                break;
            }
            if (isFieldFull()) {
                System.out.println("WIN-WIN");
                break;
            }
            aiTurn();
            printField();
            if (winCondition(FIELD_ELEMENT_0)) {
                System.out.println("AI WIN");
                break;
            }
            if (isFieldFull()) {
                System.out.println("WIN-WIN");
                break;
            }
        }
    }

    public static void playerTurn() {
        int x, y;
        do {
            System.out.println("Введите координаты X и Y : ");
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;
        } while (!isCellEmpty(x, y));
        field[x][y] = FIELD_ELEMENT_X;
    }

    public static void aiTurn() {
        int x, y;
        do {
            x = random.nextInt(SIZE);
            y = random.nextInt(SIZE);
        } while (!isCellEmpty(x, y));
        System.out.println("Компьютер поставил 0 в точку " + (x + 1) + " " + (y + 1));
        field[x][y] = FIELD_ELEMENT_0;
    }

    public static boolean winCondition(char c) {
        // Горизонтальные прямые
        if (field[0][0] == c && field[0][1] == c && field[0][2] == c) {
            return true;
        }
        if (field[1][0] == c && field[1][1] == c && field[1][2] == c) {
            return true;
        }
        if (field[2][0] == c && field[2][1] == c && field[2][2] == c) {
            return true;
        }
        // Вертикальные прямые
        if (field[0][0] == c && field[1][0] == c && field[2][0] == c) {
            return true;
        }
        if (field[0][1] == c && field[1][1] == c && field[2][1] == c) {
            return true;
        }
        if (field[0][2] == c && field[1][2] == c && field[2][2] == c) {
            return true;
        }
        // Диагонали
        if (field[0][0] == c && field[1][1] == c && field[2][2] == c) {
            return true;
        }
        if (field[0][2] == c && field[1][1] == c && field[2][0] == c) {
            return true;
        }
        return false;
    }

    public static boolean isCellEmpty(int x, int y) {
        if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) {
            return false;
        }
        if (field[x][y] == FIELD_ELEMENT_EMPTY) {
            return true;
        }
        return false;
    }

    public static boolean isFieldFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (field[i][j] == FIELD_ELEMENT_EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void printField() {
        for (int i = 0; i <= SIZE; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(field[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void createArray() {
        field = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                field[i][j] = FIELD_ELEMENT_EMPTY;
            }
        }
    }
}
