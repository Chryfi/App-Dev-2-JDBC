package datalayer.entities;

public class Newsletter {
    private String title;
    private String beschreibung;

    public Newsletter (String title, String beschreibung) {
        this.title = title;
        this.beschreibung = beschreibung;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    @Override
    public String toString() {
        return "title " + this.title + ", beschreibung " + this.beschreibung;
    }
}
