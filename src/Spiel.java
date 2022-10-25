import java.util.Scanner;

public class Spiel {
    Scanner sc = new Scanner(System.in);
    private int anzahl;
    final int anzPferde = 6;
    Spieler spieler;
    Gewinner gewinner;
    public static final String RESET = "\u001B[0m";
    public static final String GREEN = "\u001B[32m";

    Spiel(){
        System.out.println("Geben sie ihren Benutzernamen ein:");
        String name = sc.nextLine();
        if (name.isEmpty()) {
            System.out.println("Benutzername kann nicht Leer sein!");
            new Spiel();
        }
        spieler = new Spieler(name, 10000);
        System.out.println("Sie haben ein Kapital von: " + GREEN + spieler.getKapital() + "$" + RESET);
        setzen();
    }

    public void setzen() {
        System.out.println("Wie viel möchten sie setzen?");
        try {
            sc = new Scanner(System.in);
            String anz = sc.nextLine();
            if (anz.equalsIgnoreCase("end")){
                end();
                return;
            }
            this.anzahl = Integer.parseInt(anz);
            if (this.anzahl > spieler.getKapital() || this.anzahl < 0) {
                System.out.println("Geben sie einen angemessenen Betrag an!");
                System.out.println("Geben sie einen Betrag kleiner oder gleich gross wie " + GREEN + spieler.getKapital() + "$" + RESET + " an.");
                setzen();
                return;
            }
            if (this.anzahl == spieler.getKapital()) {
                System.out.println("All in!");
            }
        }
        catch (Exception e) {
            System.out.println("Geben sie einen gültigen Betrag an!");
            setzen();
            return;
        }
        gewinner = new Gewinner(anzPferde);
        pferde();
        wahlen();
    }
    public void pferde(){
        float div = (float)gewinner.getRandomness() / 100;
        for (int i = 0; anzPferde > i; i++){
            System.out.println("Pferd" + (i + 1) + " = " + gewinner.getChance().get(i) / div + "%");
        }
    }

    public void wahlen() {
        System.out.println("Wählen sie ein Pferd indem sie eine Zahl zwischen 1 und " + anzPferde + " eintippen:");
        try {
            sc = new Scanner(System.in);
            int num = sc.nextInt();
            if (num > anzPferde || num < 1){
                System.out.println("Geben sie eine Zahl zwischen 1 und " + anzPferde + " an!");
                wahlen();
                return;
            }
            System.out.println("Pferd nummer " + gewinner.getWinner() + " hat gewonnen!");
            if (num == gewinner.getWinner()) {
                double chanceProzent = gewinner.getChance().get(gewinner.getWinner() - 1);
                double win_dub = (gewinner.getRandomness()/chanceProzent) * anzahl;
                int win = (int) Math.round(win_dub);
                System.out.println("Sie haben " + GREEN + win + "$" + RESET + " gewonnen!");
                spieler.setKapital(spieler.getKapital() + win);
            }
            else {
                System.out.println("Sie haben " + GREEN + anzahl + "$" + RESET + " verloren!");
                spieler.setKapital(spieler.getKapital() - anzahl);
            }
            System.out.println("Sie haben ein Kapital von: " + GREEN + spieler.getKapital() + "$" + RESET);
        }
        catch (Exception e){
            System.out.println("Geben sie einen gültigen Betrag an!");
            wahlen();
            return;
        }
        setzen();
    }

    public void end(){
        System.out.println("Das Spiel ist vorbei!");
        System.out.println("Ihr end Kapital beträgt: " + GREEN + spieler.getKapital() + "$" + RESET);
        if (spieler.getKapital() > spieler.getStartKapital()){
            System.out.println("Sie haben gewonne!");
        }
        else if (spieler.getKapital() < spieler.getStartKapital()){
            System.out.println("Sie haben verloren!");
        }
        else {
            System.out.println("Sie haben weder gewonnen oder verloren!");
        }
        System.out.println("Vielen Dank " + spieler.getName() + " fürs spielen.\n");
        System.out.println("Möchten Sie nochmals spielen?[Ja/Nein]");
        again();
    }

    public void again(){
        sc = new Scanner(System.in);
        String antwort = sc.nextLine();
        if (antwort.equalsIgnoreCase("Ja") || antwort.equalsIgnoreCase("J")){
            spieler.setKapital(spieler.getStartKapital());
            System.out.println("\n");
            new Spiel();
            return;
        }
        if (antwort.equalsIgnoreCase("Nein") || antwort.equalsIgnoreCase("N")){
            System.exit(0);
            return;
        }
        System.out.println("Geben sie Ja oder Nein ein!");
        again();
    }
}