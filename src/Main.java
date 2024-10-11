import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        ArrayList<Bejegyzes> bejegyzesek = new ArrayList<>();
        bejegyzesek.add(new Bejegyzes("Tamas", "Elso bejegyzes"));
        bejegyzesek.add(new Bejegyzes("Jozsef", "Masodik bejegyzes"));

        Scanner s = new Scanner(System.in);
        System.out.println("Kérem adjon egy darabszámot: ");
        int darabszam = 0;
        if (s.hasNextInt()) {
            darabszam = s.nextInt();
            s.nextLine();
        } else {
            System.err.println("Hibás adat!");
        }
        for (int i = 0; i < darabszam; i++) {
            System.out.println("Adjon meg egy szerzot: ");
            String szerzo = s.nextLine();
            System.out.println("Adjon meg egy tartalmat: ");
            String tartalom = s.nextLine();
            bejegyzesek.add(new Bejegyzes(szerzo, tartalom));
        }


        //fileread

        try {
            File file = new File("bejegyzesek.csv");
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String data = sc.nextLine();
                String[] parts = data.split(";");
                bejegyzesek.add(new Bejegyzes(parts[0], parts[1]));
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.err.println("File nem talalhato!");
        }

        //d) husszor annyi
        Random r = new Random();
        for (int i = 0; i < bejegyzesek.size() * 20; i++) {
            Bejegyzes bejegyzes = bejegyzesek.get(r.nextInt(bejegyzesek.size()));
            bejegyzes.like();
        }

        //e) Kérj be a felhasználótól egy szöveget. A második bejegyzés tartalmát módosítsd a
        //felhasználó által megadott szövegre.

        System.out.println("Kérem adjon egy szöveget: ");
        String szoveg = s.nextLine();
        bejegyzesek.get(1).setTartalom(szoveg);


        //f) kiiras
        for (Bejegyzes b : bejegyzesek) {
            System.out.println("------");
            System.out.println(b);
            System.out.println("*******");
        }

        // 3
        // a.)

        class StockComparator implements Comparator<Bejegyzes> {

            // Function to compare
            public int compare(Bejegyzes b1, Bejegyzes b2) {
                if (b1.getLikeok() == b2.getLikeok())
                    return 0;
                else if (b1.getLikeok() > b2.getLikeok())
                    return 1;
                else
                    return -1;
            }
        }

        Collections.sort(bejegyzesek, new StockComparator());
        Collections.reverseOrder();

        // a)  Keresd meg a legnépszerűbb bejegyzést, majd a likeok számát írd ki a konzolra.
        System.out.printf("A likeok szam a legnepszerubb bejegyzesen: %s\n", bejegyzesek.get(0).getLikeok());

        // b)
        //Döntsd el, hogy van-e olyan bejegyzés, amely 35-nél több likeot kapott. Az eredményt írd ki a konzolra.

        boolean VanE = false;
        for (Bejegyzes b : bejegyzesek) {
            if (b.getLikeok() > 35) {
                VanE = true;
                break;
            }
        }
        System.out.printf("Van olyan bejegyzés, ami több likeot kapott mint 35: %s\n", VanE);

        //c.) Számold meg, hogy hány olyan bejegyzés van, amely 15-nél kevesebb likeot kapott. Az eredményt írd ki a konzolra.

        int count = 0;
        for (Bejegyzes b : bejegyzesek) {
            if (b.getLikeok() < 15) {
                count++;
            }
        }
        System.out.printf("%s Olyan bejegyzés van, ami több likeot kapott mint 15\n", count);

        //d.) Rendezd át a listát a likeok szerint csökkenő sorrendben, majd írd ki újra a bejegyzéseket. A rendezett lista tartalmát írd ki egy bejegyzesek_rendezett.txt fájlba.

        for (Bejegyzes b : bejegyzesek) {
            System.out.println("------");
            System.out.println(b);
            System.out.println("*******");
        }
        try {
            File output = new File("bejegyzesek_rendezett.txt");
            if (!output.exists()) {
                output.createNewFile();
            }
            FileWriter fw = new FileWriter("bejegyzesek_rendezett.txt");
            for (Bejegyzes b : bejegyzesek) {
                fw.write(b.toString() + "\n");
            }
            fw.close();

        }catch (IOException e){
            System.err.println("Hiba tortent");
        }


    }
}