import java.util.Scanner;

public class Spiel {
    Scanner sc = new Scanner(System.in);
    private int anzahl;
    final int anzPferde = 6;
    final int randomness = 100;
    Spieler spieler;

    Spiel(){
        System.out.println("Geben sie ihren Benutzernamen ein:");
        String name = sc.nextLine();
        if (name.isEmpty()) {
            System.out.println("Benutzername kann nicht Leer sein!");
            new Spiel();
        }
        spieler = new Spieler(name, 10000);
        System.out.println("Sie haben ein Kapital von: " + spieler.getKapital());
        setzen();
    }

    public void setzen() {
        Gewinner gewinner;
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
                System.out.println("Geben sie einen Betrag kleiner oder gleich gross wie " + spieler.getKapital() + " an.");
                setzen();
                return;
            }
            if (this.anzahl == spieler.getKapital()) {
                System.out.println("All in!");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Geben sie einen gültigen Betrag an!");
            setzen();
            return;
        }
        gewinner = new Gewinner(anzPferde, randomness);
        pferde(gewinner);
        wahlen(gewinner);
    }
    public void pferde(Gewinner gew){
        float div = (float)randomness / 100;
        for (int i = 0; anzPferde > i; i++){
            System.out.println("Pferd" + (i + 1) + " = " + gew.getChance().get(i) / div + "%");
        }
    }

    public void wahlen(Gewinner gewinner) {
        System.out.println("Wählen sie ein Pferd indem sie eine Zahl zwischen 1 und " + anzPferde + " eintippen:");
        try {
            int num = sc.nextInt();
            if (num > anzPferde || num < 1){
                System.out.println("Geben sie eine Zahl zwischen 1 und " + anzPferde + " an!");
                wahlen(gewinner);
                return;
            }
            if (num == gewinner.getWinner()) {
                System.out.println("Pferd nummer " + gewinner.getWinner() + " hat gewonnen!");
                double chance_prozent = gewinner.getChance().get(gewinner.getWinner() - 1);
                double win_dub = (randomness/chance_prozent) * anzahl;
                int win = (int) Math.round(win_dub);
                System.out.println("Sie haben " + win + "$ gewonnen!");
                spieler.setKapital(spieler.getKapital() + win);
            }
            else {
                System.out.println("Pferd nummer " + gewinner.getWinner() + " hat gewonnen!");
                System.out.println("Sie haben " + anzahl + "$ verloren!");
                spieler.setKapital(spieler.getKapital() - anzahl);
            }
            System.out.println("Sie haben ein Kapital von: " + spieler.getKapital() + "$");
        }
        catch (Exception e){
            System.out.println("Geben sie einen gültigen Betrag an!");
            wahlen(gewinner);
            return;
        }
        setzen();
    }

    public void end(){
        System.out.println("Das Spiel ist vorbei!");
        System.out.println("Ihr End Kapital beträgt: " + spieler.getKapital() + "$");
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
        again();
    }

    public void again(){
        System.out.println("Möchten sie nochmals Spielen?[Ja/Nein]");
        sc = new Scanner(System.in);
        String antwort = sc.nextLine();
        if (antwort.equalsIgnoreCase("Ja") || antwort.equalsIgnoreCase("J")){
            spieler.setKapital(spieler.getStartKapital());
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