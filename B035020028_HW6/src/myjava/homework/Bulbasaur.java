package myjava.homework;

import java.util.Random;

public class Bulbasaur extends pokemon {

    public Bulbasaur(){
        super.setUnique(0);
        super.setHp(40);
        super.setAtk(20);
        super.getActionList().add("(1) Razor Leaf ");
        super.getActionList().add("(2) Light Screen ");
        super.getActionList().add("(3) Synthesis ");
        super.getActionList().add("(4) Catch");
    }

    @Override
    public int action() {
        Random random = new Random(System.currentTimeMillis());
        super.setUnique(super.getAtk()*4 + random.nextInt(10));
        System.out.println("[Light Screen] : Shield + " + super.getUnique() + " points");
        return 0;
    }

    @Override
    public void show_unique() {
        System.out.println("  LS:" + super.getUnique());
    }

    @Override
    public void show_result(pokemon wild_pokemon) {
        Random random = new Random(System.currentTimeMillis());
        int damage = wild_pokemon.getAtk() + random.nextInt(10)+1;
        //先用光墙擋,不夠才損HP
        System.out.println("[Wild Pokemon] : " + damage + " damage.");
        if(super.getUnique() > 0){
            if(damage <= super.getUnique()){
                super.setUnique(super.getUnique() - damage);
                System.out.println("[Light Shield] : Shield - " + damage + " points");
                damage = 0;
            }
            else{
                System.out.println("[Light Shield] : Shield - " + super.getUnique() + " points");
                super.setUnique(0);
                damage -= super.getUnique();
            }
        }
        else
            System.out.println("[Light Shield] : Shield - 0 points");
        //
        if(damage > 0){
            System.out.println("[Bulbasaur] : HP - " + damage + " points");
            super.setHp(super.getHp() - damage);
        }

    }

    @Override
    public int attack_skill() {
        Random random = new Random(System.currentTimeMillis());
        int damage = random.nextInt(10);
        System.out.println("[Razor Leaf] : " + (super.getAtk()*4 + damage) + " damage.");
        return super.attack_skill()*4 + damage;
    }

    @Override
    public int defense_skill() {
        action();
        return super.defense_skill();
    }

    @Override
    public int buff_skill() {
        int previous_hp = super.getHp();
        Random random = new Random(System.currentTimeMillis());
        int recovery = super.getAtk()*2 + random.nextInt(10);
        if(super.getHp() + recovery < 40)
            super.setHp(super.getHp() + recovery);
        else
            super.setHp(40);
        System.out.println("[Synthesis] : HP + " + (super.getHp()-previous_hp) + " points.");
        return super.buff_skill();
    }
}
