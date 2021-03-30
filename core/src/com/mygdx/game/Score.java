package com.mygdx.game;

import com.sun.org.apache.xpath.internal.objects.XBoolean;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

/**
 * This class is used for showing the highscore.
 * @author Maciej
 */

public class Score implements Serializable {

    private static final long serialVersionUID = 1L;
    private int score_latest;
    private int score_highest;
    public boolean new_score_achieved;
    public List<Integer> listA;

    public Score(){
        score_latest = 0;
        score_highest = 0;
        new_score_achieved = false;
        listA = new ArrayList<>();
    }

    public void setScore_latest(int neu) {
        this.score_latest = neu;
    }

    public void setScore_highest(int neu) {
        this.score_highest = neu;
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

    public void sortthatlist(){
        Collections.sort(listA, Collections.reverseOrder());
    }

    }
