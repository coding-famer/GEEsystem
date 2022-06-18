package com.vcampus.entity;

public class StuScore {

    private String cardNUmber;
    public String examNUmber;
    public int mathScore;
    public int englishScore;
    public int politicsScore;
    public int majorScore;

    public StuScore(String cardNUmber,String examNUmber,int mathScore,int englishScore,int politicsScore,int majorScore)
    {
        this.cardNUmber=cardNUmber;
        this.examNUmber=examNUmber;
        this.mathScore=mathScore;
        this.englishScore=englishScore;
        this.politicsScore=politicsScore;
        this.majorScore=majorScore;
    }




}
