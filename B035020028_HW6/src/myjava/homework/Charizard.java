package myjava.homework;

import java.util.Random;

public class Charizard extends pokemon {

    public Charizard(){
        super.setUnique(30);
        super.setHp(200);
        super.setAtk(60);
        super.getActionList().add("(1) Flamethrower ");
        super.getActionList().add("(2) Parry ");
        super.getActionList().add("(3) Work Up ");
        super.getActionList().add("(4) Catch");
    }

    @Override
    public int action() {
        return 0;
    }

    @Override
    public void show_unique() {
        System.out.println("  CRI:" + super.getUnique());
    }

    @Override
    public void show_result(pokemon wild_pokemon) {
        Random random = new Random(System.currentTimeMillis());
        int damage = wild_pokemon.getAtk() + random.nextInt(10)+1;
        System.out.println("[Wild Pokemon] : " + damage + " damage.");
        System.out.println("[Charizard] : HP - " + damage + " points");
        super.setHp(super.getHp() - damage);
    }

    @Override
    public int attack_skill() {
        Random random = new Random(System.currentTimeMillis());
        int prob = random.nextInt(100)+1;
        int damage = super.getAtk() + random.nextInt(10);
        if(prob < super.getUnique()){
            System.out.println("[Flamethrower] : " + (damage*2) + " damage.");
            return damage*2;
        }
        System.out.println("[Flamethrower] : " +  damage + " damage.");
        return damage;
    }

    @Override
    public int defense_skill() {
        System.out.println("[Parry] : return next damage.");
        return super.defense_skill();
    }

    @Override
    public int buff_skill() {
        if(super.getUnique() + 25 < 100)
            super.setUnique(super.getUnique() + 25);
        System.out.println("[Work Up] : CRI " + super.getUnique() + "%");
        return super.buff_skill();
    }
}
