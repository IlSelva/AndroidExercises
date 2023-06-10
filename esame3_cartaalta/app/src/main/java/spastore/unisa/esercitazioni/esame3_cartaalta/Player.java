package spastore.unisa.esercitazioni.esame3_cartaalta;

import java.io.Serializable;

public class Player implements Serializable, Comparable{

    private String name;
    private Integer score;

    public Player(String name, Integer score){
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Override
    public int compareTo(Object o) {
        if(o instanceof Player)
            return score.compareTo(((Player) o).getScore());
        throw new ClassCastException();
    }
}
