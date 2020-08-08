package com.example.qu;

public class Score {

    private int score,total;

    public Score() {
    }

    public Score(int score, int total) {
        this.score = score;
        this.total = total;
    }

    public int getscore() {
        return score;
    }

    public void setscore(int score) {
        this.score = score;
    }

    public int gettotal() {
        return total;
    }

    public void settotal(int total) {
        this.total = total;
    }
}
