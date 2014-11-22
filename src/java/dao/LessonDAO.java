package dao;

import business.ClassRoom;
import business.Class;
import business.Homework;
import business.Lesson;
import business.Lessondate;
import business.SchoolYear;
import business.Subject;
import business.Teacher;
import errors.ClassNotFound;
import errors.UserNotFound;
import java.sql.SQLException;
import java.util.ArrayList;

public class LessonDAO extends DefaultDAO {

    private Lesson l;

    public LessonDAO() {
        l = null;
    }

    public LessonDAO(Lesson l) {
        this.l = l;
    }

    public ArrayList<Lesson> findAll(Teacher t) throws SQLException, UserNotFound, ClassNotFound {
        try {
            ps = c.prepareStatement("SELECT * FROM `lessons_at_current_year` WHERE `teacher_id` = ?");
            ps.setString(1, t.getIdCardNumber());

            logger.trace(ps);
            rs = ps.executeQuery();

            ArrayList<Lesson> lessons = new ArrayList<>();
            while (rs.next()) {
                l = new Lesson();
                l.setId(rs.getInt("id"));
                l.setSubject(Subject.getInstance(rs.getInt("subject_id")));
                l.setTeacher(Teacher.getInstance(rs.getString("teacher_id")));
                l.setYear(SchoolYear.getInstance(rs.getInt("year_id")));
                l.setForm(Class.getInstance(rs.getInt("class_id")));
                l.setClassRoom(ClassRoom.getInstance(rs.getString("classroom_name")));
                l.setDates(Lessondate.getInstance(l));

                lessons.add(l);
            }

            return lessons;
        } finally {
            closeStatementAndSet();
        }
    }

    public ArrayList<Lesson> findAll(Class cl) throws SQLException, UserNotFound {
        try {
            ps = c.prepareStatement("SELECT * FROM `lessons_at_current_year` WHERE `class_id` = ?");
            ps.setInt(1, cl.getId());

            logger.trace(ps);
            rs = ps.executeQuery();

            ArrayList<Lesson> lessons = new ArrayList<>();
            while (rs.next()) {
                l = new Lesson();
                l.setId(rs.getInt("id"));
                l.setSubject(Subject.getInstance(rs.getInt("subject_id")));
                l.setTeacher(Teacher.getInstance(rs.getString("teacher_id")));
                l.setYear(SchoolYear.getInstance(rs.getInt("year_id")));
                l.setForm(cl);
                l.setClassRoom(ClassRoom.getInstance(rs.getString("classroom_name")));
                l.setDates(Lessondate.getInstance(l));

                lessons.add(l);
            }

            return lessons;
        } finally {
            closeStatementAndSet();
        }
    }

    public ArrayList<Lesson> findAll(ClassRoom cr) throws SQLException, UserNotFound, ClassNotFound {
        try {
            ps = c.prepareStatement("SELECT `id` FROM `lesson` WHERE `classroom_name` = ? ");
            ps.setString(1, cr.getName());

            logger.trace(ps);
            rs = ps.executeQuery();

            ArrayList<Lesson> lessons = new ArrayList<>();
            while (rs.next()) {
                lessons.add(new LessonDAO().find(rs.getInt("id")));
            }

            return lessons;
        } finally {
            closeStatementAndSet();
        }
    }

    public ArrayList<Lesson> findAll(String day) throws SQLException, UserNotFound, ClassNotFound {
        try {
            ps = c.prepareStatement("SELECT `lesson_id` FROM `lesson_date` WHERE `day` = ?");
            ps.setString(1, day);

            logger.trace(ps);
            rs = ps.executeQuery();

            ArrayList<Lesson> lessons = new ArrayList<>();
            while (rs.next()) {
                lessons.add(new LessonDAO().find(rs.getInt("lesson_id")));
            }

            return lessons;
        } finally {
            closeStatementAndSet();
        }
    }

    public Lesson find(int id) throws SQLException, UserNotFound, ClassNotFound {
        try {
            ps = c.prepareStatement("SELECT * FROM `lesson` WHERE `id` = ?");
            ps.setInt(1, id);

            logger.trace(ps);
            rs = ps.executeQuery();

            while (rs.next()) {
                l = new Lesson();
                l.setId(id);
                l.setSubject(Subject.getInstance(rs.getInt("subject_id")));
                l.setTeacher(Teacher.getInstance(rs.getString("teacher_id")));
                l.setYear(SchoolYear.getInstance(rs.getInt("year_id")));
                l.setForm(Class.getInstance(rs.getInt("class_id")));
                l.setClassRoom(ClassRoom.getInstance(rs.getString("classroom_name")));
                l.setDates(Lessondate.getInstance(l));
            }

            return l;
        } finally {
            closeStatementAndSet();
        }
    }

    public ArrayList<Homework> findHomeworks() throws SQLException {
        try {
            ps = c.prepareStatement("SELECT * FROM `homework` WHERE `lesson_id` = ?");
            ps.setInt(1, l.getId());

            logger.debug(ps);
            rs = ps.executeQuery();

            ArrayList<Homework> hws = new ArrayList<>();
            while (rs.next()) {
                Homework hw = new Homework();
                hw.setId(rs.getInt("id"));
                hw.setContent(rs.getString("content"));
                hw.setCreated(rs.getDate("created"));
                hw.setLesson(l);
                hws.add(hw);
            }

            return hws;
        } finally {
            closeStatementAndSet();
        }
    }

    public Class findClass() throws SQLException, UserNotFound {
        try {
            ps = c.prepareStatement("SELECT `class_id` FROM `lesson` WHERE `id` = ?");
            ps.setInt(1, l.getId());

            logger.trace(ps);
            rs = ps.executeQuery();
            
            rs.next();            
            return Class.getInstance(rs.getInt(1));
        } finally {
            closeStatementAndSet();
        }
    }
}
