import java.time.LocalDateTime;
import java.util.Comparator;

public class Bejegyzes {

    private String szerzo;
    private String tartalom;
    private Integer likeok;
    private LocalDateTime letrejott;
    private LocalDateTime szerkesztve;

    public Bejegyzes(String szerzo, String tartalom) {
        this.szerzo = szerzo;
        this.tartalom = tartalom;
        this.likeok = 0;
        this.letrejott = LocalDateTime.now();
        this.szerkesztve = LocalDateTime.now();
    }

    public String getSzerzo() {
        return szerzo;
    }

    public String getTartalom() {
        return tartalom;
    }

    public void setTartalom(String tartalom) {
        this.tartalom = tartalom;
        this.szerkesztve = LocalDateTime.now();
    }

    public Integer getLikeok() {
        return likeok;
    }
    public LocalDateTime getLetrejott() {
        return letrejott;
    }

    public LocalDateTime getSzerkesztve() {
        return szerkesztve;
    }

    public void like() {
        this.likeok++;
    }

    @Override
    public String toString() {
        if (!szerkesztve.equals(letrejott)){
            return String.format("%s - %s - %s\nSzerkesztve: %s\n%s",szerzo,likeok,letrejott,szerkesztve,tartalom);
        }
        else{
            return String.format("%s - %s - %s\n%s",szerzo,likeok,letrejott,tartalom);
        }
    }


}
