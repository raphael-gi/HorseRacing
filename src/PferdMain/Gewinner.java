package PferdMain;

import java.util.ArrayList;

public class Gewinner {
    private final ArrayList<Integer> chance = new ArrayList<>();
    private final ArrayList<Integer> arrayList = new ArrayList<>();
    private int winner;
    private final int anz_pferde;
    private final int randomness;

    Gewinner(int anz_pferde, int randomness){
        this.anz_pferde = anz_pferde;
        this.randomness = randomness;
        for (int i = 0; i < randomness; i++){
            double rand_dub = Math.random() * (anz_pferde - 1);
            int rand = (int) Math.round(rand_dub);
            this.arrayList.add(rand);
        }

        for (int i = 0; i < anz_pferde; i++){
            calc(0, i);
        }
        choose();
    }

    public void calc(int chance, int get){
        for (int i = 0; i < randomness; i++){
            if (this.arrayList.get(i) == get){
                chance = chance + 1;
            }
        }
        this.chance.add(chance);
    }

    public void choose(){
        double max = -1;
        for (int i = 0; i < anz_pferde; i++){
            double max_dub = Math.random() * chance.get(i);
            if (max_dub > max){
                max = max_dub;
                winner = i + 1;
            }
        }
    }

    public ArrayList<Integer> getChance() {
        return this.chance;
    }

    public int getWinner() {
        return winner;
    }
}