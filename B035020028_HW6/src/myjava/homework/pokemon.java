package myjava.homework;

import java.util.ArrayList;

public abstract class pokemon implements skill {
    private int hp, atk, unique;
    private ArrayList<String> actionList = new ArrayList<>();

    public ArrayList<String> getActionList() {
        return actionList;
    }

    public int getHp() {
        return hp;
    }

    public int getAtk() {
        return atk;
    }

    public int getUnique(){
        return this.unique;
    }

    public void setHp(int hp){
        if(hp >= 0)
            this.hp = hp;
        else
            this.hp = 0;
    }

    public void setAtk(int atk){
        this.atk = atk;
    }

    public void setUnique(int unique){
        this.unique = unique;
    }

    public abstract int action();

    public abstract void show_unique();

    public abstract void show_result(pokemon wild_pokemon);

    @Override
    public int attack_skill() {
        return this.atk;
    }

    @Override
    public int buff_skill() {
        return 0;
    }

    @Override
    public int defense_skill() {
        return 0;
    }
}
