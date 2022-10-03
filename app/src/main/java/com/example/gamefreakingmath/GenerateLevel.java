package com.example.gamefreakingmath;

import java.util.Random;

public class GenerateLevel {
    //Số điểm tăng độ khó
    public static final int EASY = 10;
    public static final int MEDIUM = 20;
    public static final int HARD = 70;

    public static LevelModel generateLevel(int count) {
        LevelModel level = new LevelModel();

        Random random = new Random();

        //Nâng độ khó khi số điểm đạt
        if (count <= EASY) {
            level.difficultLevel = 1;
            level.operator = 0;
        } else {
            if (count <= MEDIUM) {
                level.difficultLevel = 2;
                //Tạo ngẫu nhiên dấu
                level.operator = random.nextInt(2);
            } else {
                if (count <=HARD) {
                    level.difficultLevel = 3;
                    level.operator = random.nextInt(3);
                } else {
                    level.difficultLevel = 4;
                    level.operator = random.nextInt(4);
                }
            }
        }

        //Tạo các toán hạng
        level.x = random.nextInt(level.arrMaxOperatorValue[level.difficultLevel - 1]) + 1;
        level.y = random.nextInt(level.arrMaxOperatorValue[level.difficultLevel - 1]) + 1;

        //Tạo ngẫu nhiên đúng sai
        level.correctWrong = random.nextBoolean();

        //Tạo ngẫu nhiên gia số
        boolean randomAddSub = random.nextBoolean();
        int giaSo;
        do {
            giaSo =  random.nextInt(level.arrMaxOperatorValue[level.difficultLevel - 1]);
        } while (giaSo == 0);


        //Tạo phép tính toán
        if(!level.correctWrong) {
            switch (level.operator) {
                case LevelModel.ADD:
                    //do {
                        if (randomAddSub) {
                            level.result = level.x + level.y + giaSo;
                        } else {
                            level.result = level.x + level.y - giaSo;
                        }
                    //} while (level.result == level.x + level.y);
                    break;
                case LevelModel.SUB:
                    if (randomAddSub) {
                        level.result = level.x - level.y + giaSo;
                    } else {
                        level.result = level.x - level.y - giaSo;
                    }
                    break;
                case LevelModel.MUL:
                    if (randomAddSub) {
                        level.result = (level.x * level.y) + giaSo;
                    } else {
                        level.result = (level.x * level.y) - giaSo;
                    }
                    break;
                case LevelModel.DEV:
                    if (randomAddSub) {
                        level.result = (level.x / level.y) + giaSo;
                    } else {
                        level.result = (level.x / level.y) - giaSo;
                    }
                    break;

            }
        } else {
            switch (level.operator) {
                case LevelModel.ADD:
                    level.result = level.x + level.y;
                    break;
                case LevelModel.SUB:
                    level.result = level.x - level.y;
                    break;
                case LevelModel.MUL:
                    level.result = level.x * level.y;
                    break;
                case LevelModel.DEV:
                    level.result = level.x / level.y;
                    break;
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
