package com.example.gamefreakingmath;

public class LevelModel {
    public int difficultLevel = 1;

    //operator
    public static final int ADD = 0;
    public static final int SUB = 1;
    public static final int MUL = 2;
    public static final int DEV = 3;

    //Ký hiệu dấu
    public static final String ADD_TEXT = " + ";
    public static final String SUB_TEXT = " - ";
    public static final String MUL_TEXT = " x ";
    public static final String DEV_TEXT = " : ";
    public static final String EQU_TEXT = " = ";
    public static final String[] arrOperatorText = {ADD_TEXT, SUB_TEXT, MUL_TEXT, DEV_TEXT};

    //Các toán hạng
    public int x;
    public int y;
    public int result;
    public int operator;
    public boolean correctWrong;
    public String strOperator = "";
    public String strResult = "";

    //Giá trị lớn nhất để phát số ngẫu nhiên tùy thuộc vào mức độ khó

    public static final int MAX_OPERATOR_EASY = 5;
    public static final int MAX_OPERATOR_MEDIUM = 10;
    public static final int MAX_OPERATOR_HARD = 20;
    public static final int MAX_OPERATOR_EXCELLENT = 50;
    public static final int[] arrMaxOperatorValue = {MAX_OPERATOR_EASY, MAX_OPERATOR_MEDIUM, MAX_OPERATOR_HARD, MAX_OPERATOR_EXCELLENT};
}
