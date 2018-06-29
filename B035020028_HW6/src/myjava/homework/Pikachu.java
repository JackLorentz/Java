package myjava.homework;

import java.util.ArrayList;
import java.util.Random;

public class Pikachu extends pokemon {

    public Pikachu(){
        super.setUnique(20);
        super.setHp(80);
        super.setAtk(40);
        super.getActionList().add("(1) Thunder Shock ");
        super.getActionList().add("(2) Double Teen ");
        super.getActionList().add("(3) Thunder ");
        super.getActionList().add("(4) Catch");
    }

    @Override
    public int action() {
        int previous_eva = super.getUnique();
        if(super.getUnique()*2 < 100)
            super.setUnique(super.getUnique() * 2);
        else if(super.getUnique()*2 > 100)
            super.setUnique(100);
        System.out.println("[Double Teen] : EVA + " + (super.getUnique() - previous_eva) + " points");
        return 0;
    }

    @Override
    public void show_unique() {
        System.out.println("  EVA:" + super.getUnique());
    }

    @Override
    public void show_result(pokemon wild_pokemon) {
        Random random = new Random(System.currentTimeMillis());
        int prob = random.nextInt(100)+1;
        int damage = random.nextInt(10)+1;
        System.out.println("[Wild Pokemon] : " + (wild_pokemon.getAtk()+damage) + " damage.");
        if(prob < super.getUnique()){
            System.out.println("Evasion Succeed");
        }
        else{
            System.out.println("[Pikachu] : HP - " + (wild_pokemon.getAtk()+damage) + " points");
            super.setHp(super.getHp() - (wild_pokemon.getAtk()+damage));
        }
    }

    @Override
    public int attack_skill() {
        Random random = new Random(System.currentTimeMillis());
        int damage = random.nextInt(10);
        System.out.println("[Thunder Shock] : " + (super.getAtk() + damage) + " damage.");
        return super.attack_skill() + damage;
    }

    @Override
    public int defense_skill() {
        action();
        return super.defense_skill();
    }

    @Override
    public int buff_skill() {
        int previous_atk = super.getAtk();
        super.setAtk(super.getAtk() * 2);
        System.out.println("[Thunder] : ATK + " + (super.getAtk()-previous_atk) + " points");
        return super.buff_skill();
    }
}
