package com.example.gamefreakingmath;

import java.util.Random;

public class GenerateLevel {
    //Số điểm tăng độ khó
    public static final int EASY = 10;
    public static final int MEDIUM = 20;
    public static final int HARD = 100;

    public static LevelModel generateLevel(int count) {
        LevelModel level = new LevelModel();

        Random random = new Random();

        //Nâng độ khó khi số điểm đạt
        if (count <= EASY) {
            level.difficultLevel = 1;
        } else {
            if (count <= MEDIUM) {
                level.difficultLevel = 2;
            } else {
                if (count <=HARD) {
                    level.difficultLevel = 3;
                } else {
                    level.difficultLevel = 4;
                }
            }
        }
        //Tạo ngẫu nhiên dấu
        level.operator = random.nextInt(level.difficultLevel);

        //Tạo các toán hạng
        level.x = random.nextInt(level.arrMaxOperatorValue[level.difficultLevel] + 1);
        level.y = random.nextInt(level.arrMaxOperatorValue[level.difficultLevel] + 1);

        level.correctWrong = random.nextBoolean();

        //Tạo phép tính toán
        if(level.correctWrong == false) {
            switch (level.operator) {
                case LevelModel.ADD:
                    do {
                        level.result = random.nextInt(level.arrMaxOperatorValue[level.difficultLevel] );
                    } while (level.result == level.x + level.y);
                    break;
                case LevelModel.SUB:
                    do {
                        level.result = random.nextInt(level.arrMaxOperatorValue[level.difficultLevel] );
                    } while (level.result == level.x - level.y);
                    break;
                case LevelModel.MUL:
                    do {
                        level.result = random.nextInt(level.arrMaxOperatorValue[level.difficultLevel] );
                    } while (level.result == level.x * level.y);
                    break;
                case LevelModel.DEV:
                    do {
                        level.result = random.nextInt(level.arrMaxOperatorValue[level.difficultLevel]);
                    } while (level.result == level.x / level.y);
                    break;
            }
        } else {
            switch (level.operator) {
                case LevelModel.ADD:
                    level.result = level.x + level.y;
                    break;

                case LevelModel.SUB:
                    level.result = level.x - level.y;

                case LevelModel.MUL:
                    level.result = level.x * level.y;

                case LevelModel.DEV:
                    level.result = level.x / level.y;

                default:
                    break;

            }
        }

        level.strOperator = String.valueOf(level.x) + level.arrOperatorText[level.operator]
                            + String.valueOf(level.y);

        level.strResult =  LevelModel.EQU_TEXT + String.valueOf(level.result);

        return level;



    }
}
