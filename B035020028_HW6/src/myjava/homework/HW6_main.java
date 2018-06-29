package myjava.homework;

import java.io.IOException;
import java.util.Random;

public class HW6_main {

    private static Random random = null;
    private static Keypad keypad = new Keypad();
    private static pokemon[] Pokemon = new pokemon[4];
    private static pokemon wild_pokemon = new Bulbasaur();
    private static int CATCH_SUCCEED = 0;
    private static int initail_hp, opt = 1;

    public static void main(String args[]) throws IOException{
        initialize();
        System.out.println("[Wild pokemon appeared!]");
        //對戰過程
        while(true) {
            show_parameters();
            //
            if (Pokemon[opt].getHp() == 0){
                System.out.println("You Dead.");
                break;
            }
            else if(wild_pokemon.getHp() == 0){
                System.out.println("You Win.");
                break;
            }
            //
            for (String action : Pokemon[opt].getActionList()) {
                System.out.print(action);
            }
            System.out.print("\nAction:(By default (1)) : ");
            switch (keypad.getInput()) {
                case 1:
                    wild_pokemon.setHp(wild_pokemon.getHp() - Pokemon[opt].attack_skill());
                    break;
                case 2:
                    if(opt == 3)
                        wild_pokemon.setHp(wild_pokemon.getHp() - wild_pokemon.getAtk());
                    Pokemon[opt].defense_skill();
                    break;
                case 3:
                    Pokemon[opt].buff_skill();
                    break;
                case 4:
                    System.out.println("[catch] : Throw the Poke Ball");
                    random = new Random(System.currentTimeMillis());
                    int prob = random.nextInt((initail_hp - wild_pokemon.getHp()+1));
                    if (prob  <  (initail_hp - wild_pokemon.getHp())) {
                        CATCH_SUCCEED = 2;
                        System.out.println("---------------        --------------------");
                        System.out.println("you caught the wild pokemon.");
                    } else {
                        CATCH_SUCCEED = 1;
                        System.out.println("you did not catch the wild pokemon.");
                    }
                    break;
                default:
                    wild_pokemon.setHp(wild_pokemon.getHp() - Pokemon[opt].attack_skill());
                    break;
            }
            if (CATCH_SUCCEED != 2) {
                CATCH_SUCCEED = 0;
                Pokemon[opt].show_result(wild_pokemon);
            }
            else
                break;
        }
    }

    public static void initialize() {
        Pokemon[1] = new Pikachu();
        Pokemon[2] = new Bulbasaur();
        Pokemon[3] = new Charizard();
        random = new Random(System.currentTimeMillis());
        wild_pokemon.setHp(random.nextInt(150)+150);
        wild_pokemon.setAtk(random.nextInt(5)+30);
        initail_hp = wild_pokemon.getHp();
        System.out.println("(1) Pikachu (2) Bulbsasur (3) Charizard");
        System.out.print("Choose your pokemon (By default (1)) :");
        opt = keypad.getInput();
    }

    public static void show_parameters(){
        System.out.print("----Pokemon----");
        System.out.print("        ");
        System.out.println("----Wild Pokemon----");
        //
        System.out.print("  HP:" + Pokemon[opt].getHp());
        for(int i=0; i<=20-String.valueOf(Pokemon[opt].getHp()).length(); i++)
            System.out.print(" ");
        System.out.println("HP:" + wild_pokemon.getHp());
        //
        System.out.print("  ATK:" + Pokemon[opt].getAtk());
        for(int i=0; i<20-String.valueOf(Pokemon[opt].getAtk()).length(); i++)
            System.out.print(" ");
        System.out.println("ATK:" + wild_pokemon.getAtk());
        //
        Pokemon[opt].show_unique();
        System.out.println("---------------        --------------------");
    }



}
