package com.mygdx.game;

import com.sun.org.apache.xpath.internal.objects.XBoolean;

public class Score {
    private int score_latest;
    private int score_highest;
    public boolean new_score_achieved;
    private String username;

    public Score() {
        new_score_achieved = false;
    }

    public void setScore_latest(int neu) {
        this.score_latest = neu;
    }

    public void setScore_highest(int i) {
        this.score_highest = i;
    }

    public int getScore_latest() {
        return score_latest;
    }

    public int getScore_highest() {
        return score_highest;
    }

    public void manage() {
        if (getScore_latest() > getScore_highest()) {
            setScore_highest(getScore_latest());
            new_score_achieved = true;
        } else {
            new_score_achieved = false;
        }
        }
    }
