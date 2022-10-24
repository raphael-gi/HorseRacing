public class Spieler {
    String name;
    int kapital;
    int startKapital;

    Spieler(String name, int startKapital) {
        this.name = name;
        this.kapital = startKapital;
        this.startKapital = startKapital;
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

    public int getStartKapital() {
        return startKapital;
    }
}