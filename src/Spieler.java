public class Spieler {
    String name;
    int kapital;

    Spieler(String name, int kapital) {
        this.name = name;
        this.kapital = kapital;
    }

    public String getName() {
        return name;
    }

    public int getKapital() {
        return kapital;
    }

    public void setKapital(int kapital) {
        this.kapital = kapital;
    }
}