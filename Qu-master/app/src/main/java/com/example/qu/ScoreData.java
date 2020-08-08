package com.example.qu;

public class ScoreData {

    static String username;
    static private String Score,Total;

    public ScoreData()
    {

    }

    public ScoreData(String username,String Score,String Total)
    {
        this.username=username;
        this.Score=Score;
        this.Total=Total;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        ScoreData.username = username;
    }

    public String getScore() {
        return Score;
    }

    public void setScore(String score) {
        Score = score;
    }

    public String getTotal() {
        return Total;
    }

    public void setTotal(String total) {
        Total = total;
    }
}
