import java.util.ArrayList;

public class Gewinner {
    private final ArrayList<Integer> chance = new ArrayList<>();
    private final ArrayList<Long> arrayList = new ArrayList<>();
    private final int anz_pferde;
    private final int winner;
    private final int randomness = 100;

    Gewinner(int anz_pferde){
        this.anz_pferde = anz_pferde;
        for (int i = 0; i < randomness; i++){
            long rand = Math.round(Math.random() * (anz_pferde - 1));
            this.arrayList.add(rand);
        }

        for (int i = 0; i < anz_pferde; i++) {
            chance.add(calc(i));
        }
        winner = choose();
    }

    public int calc(int get){
        int chance = 0;
        for (int i = 0; i < randomness; i++){
            if (arrayList.get(i) == get){
                chance = chance + 1;
            }
        }
        return chance;
    }

    public int choose(){
        int winner = 0;
        double max = -1;
        for (int i = 0; i < anz_pferde; i++){
            double max_dub = Math.random() * chance.get(i);
            if (max_dub > max){
                max = max_dub;
                winner = i + 1;
            }
        }
        return winner;
    }

    public ArrayList<Integer> getChance() {
        return this.chance;
    }
    public int getWinner() {
        return winner;
    }
    public int getRandomness() {
        return randomness;
    }
}