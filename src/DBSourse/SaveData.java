package DBSourse;

import java.time.LocalDate;
import java.util.Date;

public class SaveData {
    private int id;
    private String title;
    private LocalDate date;
    private boolean isPinning;
    public boolean isDeleted;
    private String savePoint;

    public SaveData(int id, String title, LocalDate  date, boolean isPinning, boolean isDeleted, String savePoint) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.isPinning = isPinning;
        this.isDeleted = isDeleted;
        this.savePoint = savePoint;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public LocalDate  getDate() { return date; }
    public boolean isPinning() { return isPinning; }
    public boolean isDeleted() { return isDeleted; }
    public String getSavePoint() { return savePoint; }

    public void setId(int id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setDate(LocalDate  date) { this.date = date; }
    public void setPinning(boolean isPinning) { this.isPinning = isPinning; }
    public void setDeleted(boolean isDeleted) { this.isDeleted = isDeleted; }
    public void setSavePoint(String savePoint) { this.savePoint = savePoint; }
}