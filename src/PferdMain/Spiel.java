package PferdMain;

import java.util.Scanner;

public class Spiel {
    Scanner sc = new Scanner(System.in);
    private final String name;
    private final int start_kapital;
    private int kapital;
    private int anz;
    final int anz_pferde = 6;
    final int randomness = 100;

    Spiel(){
        System.out.println("Geben sie ihren Benutzernamen ein:");
        String nam = sc.nextLine();
        if (nam.isEmpty()){
            System.out.println("Benutzername kann nicht Leer sein!");
            new Spiel();
        }
        Spieler eins = new Spieler(nam, 10000);
        name = eins.getName();
        kapital = eins.getKapital();
        start_kapital = eins.getKapital();
        setzen();
    }

    public void setzen(){
        System.out.println("Wie viel möchten sie setzen?");
        try {
            Scanner sc = new Scanner(System.in);
            String anz = sc.nextLine();
            if (anz.equalsIgnoreCase("end")){
                end();
            }
            this.anz = Integer.parseInt(anz);
            if (this.anz > kapital){
                System.out.println("Sie haben zu wenig Geld!");
                System.out.println("Geben sie einen Betrag kleiner oder gleich gross wie " + kapital + " an.");
                setzen();
            }
            if (this.anz == kapital){
                System.out.println("All in!");
            }
            Gewinner gew = new Gewinner(anz_pferde, randomness);
            pferde(gew);
            wahlen(gew);
        }
        catch (Exception e){
            System.out.println("Geben sie einen gültigen Betrag an!");
            setzen();
        }
    }
    public void pferde(Gewinner gew){
        float div = (float)randomness / 100;
        for (int i = 0; anz_pferde > i; i++){
            System.out.println("Pferd" + (i + 1) + " = " + gew.getChance().get(i) / div + "%");
        }
    }

    public void wahlen(Gewinner gew){
        System.out.println("Wählen sie ein Pferd indem sie eine Zahl zwischen 1 und " + anz_pferde + " eintippen:");
        try {
            Scanner sc = new Scanner(System.in);
            int num = sc.nextInt();
            if (num > anz_pferde || num < 1){
                System.out.println("Geben sie eine Zahl zwischen 1 und " + anz_pferde + " an!");
                wahlen(gew);
            }
            else if (num == gew.getWinner()) {
                System.out.println("Pferd nummer " + gew.getWinner() + " hat gewonnen!");
                double chance_prozent = gew.getChance().get(gew.getWinner() - 1);
                double win_dub = (randomness/chance_prozent) * anz;
                int win = (int) Math.round(win_dub);
                System.out.println("Sie haben " + win + "$ gewonnen!");
                kapital = kapital + win;
            }
            else {
                System.out.println("Pferd nummer " + gew.getWinner() + " hat gewonnen!");
                System.out.println("Sie haben " + anz + "$ verloren!");
                kapital = kapital - anz;
            }
            System.out.println("Sie haben ein Kapital von: " + kapital + "$");
            setzen();
        }
        catch (Exception e){
            System.out.println("Geben sie einen gültigen Betrag an!");
            wahlen(gew);
        }
    }

    public void end(){
        System.out.println("Das Spiel ist vorbei!");
        System.out.println("Ihr End Kapital beträgt: " + kapital + "$");
        if (kapital > start_kapital){
            System.out.println("Sie haben gewonne!");
        }
        else if (kapital < start_kapital){
            System.out.println("Sie haben verloren!");
        }
        else {
            System.out.println("Sie haben weder gewonnen oder verloren!");
        }
        System.out.println("Vielen Dank " + name + " fürs spielen.\n");
        again();
    }

    public void again(){
        System.out.println("Möchten sie nochmals Spielen?[Ja/Nein]");
        String antwort = sc.nextLine();
        if (antwort.equalsIgnoreCase("Ja") || antwort.equalsIgnoreCase("J")){
            kapital = start_kapital;
            setzen();
        }
        else if (antwort.equalsIgnoreCase("Nein") || antwort.equalsIgnoreCase("N")){
            System.exit(0);
        }
        else {
            System.out.println("Geben sie Ja oder Nein ein!");
            again();
        }
    }
}