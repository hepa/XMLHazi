package business;

import dao.SettingsDAO;
import java.sql.SQLException;

public class Settings {
    private boolean enabledMessageAboutGrade;
    private boolean enabledEmailAboutGrade;
    private boolean enabledMessageAboutHomework;
    private boolean enabledEmailAboutHomework;
    private boolean enabledMessageAboutHomeworkGrade;
    private boolean enabledEmailAboutHomeworkGrade;
    private boolean enabledMessageAboutItem;
    private boolean enabledEmailAboutItem;
    private boolean enabledMessageAboutAttendance;
    private boolean enabledEmailAboutAttendance;

    public Settings() {
    }

    public Settings(boolean enabledMessageAboutGrade, boolean enabledEmailAboutGrade, boolean enabledMessageAboutHomework, boolean enabledEmailAboutHomework, boolean enabledMessageAboutHomeworkGrade, boolean enabledEmailAboutHomeworkGrade, boolean enabledMessageAboutItem, boolean enabledEmailAboutItem, boolean enabledMessageAboutAttendance, boolean enabledEmailAboutAttendance) {
        this.enabledMessageAboutGrade = enabledMessageAboutGrade;
        this.enabledEmailAboutGrade = enabledEmailAboutGrade;
        this.enabledMessageAboutHomework = enabledMessageAboutHomework;
        this.enabledEmailAboutHomework = enabledEmailAboutHomework;
        this.enabledMessageAboutHomeworkGrade = enabledMessageAboutHomeworkGrade;
        this.enabledEmailAboutHomeworkGrade = enabledEmailAboutHomeworkGrade;
        this.enabledMessageAboutItem = enabledMessageAboutItem;
        this.enabledEmailAboutItem = enabledEmailAboutItem;
        this.enabledMessageAboutAttendance = enabledMessageAboutAttendance;
        this.enabledEmailAboutAttendance = enabledEmailAboutAttendance;
    }

    // <editor-fold desc="Getter&Setter" defaultstate="collapsed">
    public boolean isEnabledMessageAboutGrade() {
        return enabledMessageAboutGrade;
    }

    public void setEnabledMessageAboutGrade(boolean enabledMessageAboutGrade) {
        this.enabledMessageAboutGrade = enabledMessageAboutGrade;
    }

    public boolean isEnabledEmailAboutGrade() {
        return enabledEmailAboutGrade;
    }

    public void setEnabledEmailAboutGrade(boolean enabledEmailAboutGrade) {
        this.enabledEmailAboutGrade = enabledEmailAboutGrade;
    }

    public boolean isEnabledMessageAboutHomework() {
        return enabledMessageAboutHomework;
    }

    public void setEnabledMessageAboutHomework(boolean enabledMessageAboutHomework) {
        this.enabledMessageAboutHomework = enabledMessageAboutHomework;
    }

    public boolean isEnabledEmailAboutHomework() {
        return enabledEmailAboutHomework;
    }

    public void setEnabledEmailAboutHomework(boolean enabledEmailAboutHomework) {
        this.enabledEmailAboutHomework = enabledEmailAboutHomework;
    }

    public boolean isEnabledMessageAboutHomeworkGrade() {
        return enabledMessageAboutHomeworkGrade;
    }

    public void setEnabledMessageAboutHomeworkGrade(boolean enabledMessageAboutHomeworkGrade) {
        this.enabledMessageAboutHomeworkGrade = enabledMessageAboutHomeworkGrade;
    }

    public boolean isEnabledEmailAboutHomeworkGrade() {
        return enabledEmailAboutHomeworkGrade;
    }

    public void setEnabledEmailAboutHomeworkGrade(boolean enabledEmailAboutHomeworkGrade) {
        this.enabledEmailAboutHomeworkGrade = enabledEmailAboutHomeworkGrade;
    }

    public boolean isEnabledMessageAboutItem() {
        return enabledMessageAboutItem;
    }

    public void setEnabledMessageAboutItem(boolean enabledMessageAboutItem) {
        this.enabledMessageAboutItem = enabledMessageAboutItem;
    }

    public boolean isEnabledEmailAboutItem() {
        return enabledEmailAboutItem;
    }

    public void setEnabledEmailAboutItem(boolean enabledEmailAboutItem) {
        this.enabledEmailAboutItem = enabledEmailAboutItem;
    }

    public boolean isEnabledMessageAboutAttendance() {
        return enabledMessageAboutAttendance;
    }

    public void setEnabledMessageAboutAttendance(boolean enabledMessageAboutAttendance) {
        this.enabledMessageAboutAttendance = enabledMessageAboutAttendance;
    }

    public boolean isEnabledEmailAboutAttendance() {
        return enabledEmailAboutAttendance;
    }

    public void setEnabledEmailAboutAttendance(boolean enabledEmailAboutAttendance) {
        this.enabledEmailAboutAttendance = enabledEmailAboutAttendance;
    }

    // </editor-fold>
    
    public static Settings getInstance(Student s) throws SQLException {
        return new SettingsDAO().find(s);
    }
    
    public static Settings getInstance(Family f) throws SQLException {
        return new SettingsDAO().find(f);
    }
    
    public void update(Student s) throws SQLException {
        new SettingsDAO(this).update(s);
    }
    
    public static void update(Student s, String attribute, boolean value) throws SQLException {        
        new SettingsDAO().update(s, attribute, value);
    }
    
    public static void update(Family f, String attribute, boolean value) throws SQLException {
        new SettingsDAO().update(f, attribute, value);
    }

    @Override
    public String toString() {
        return "Settings{" + "enabledMessageAboutGrade=" + enabledMessageAboutGrade + ", enabledEmailAboutGrade=" + enabledEmailAboutGrade + ", enabledMessageAboutHomework=" + enabledMessageAboutHomework + ", enabledEmailAboutHomework=" + enabledEmailAboutHomework + ", enabledMessageAboutHomeworkGrade=" + enabledMessageAboutHomeworkGrade + ", enabledEmailAboutHomeworkGrade=" + enabledEmailAboutHomeworkGrade + ", enabledMessageAboutItem=" + enabledMessageAboutItem + ", enabledEmailAboutItem=" + enabledEmailAboutItem + ", enabledMessageAboutAttendance=" + enabledMessageAboutAttendance + ", enabledEmailAboutAttendance=" + enabledEmailAboutAttendance + '}';
    }
        
    
}
