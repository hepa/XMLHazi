package dao;

import business.Person;
import java.sql.SQLException;
import java.util.ArrayList;

public class PersonDAO extends DefaultDAO {

    private Person person;

    public PersonDAO() {
    }

    public PersonDAO(Person person) {
        this.person = person;
    }

    public ArrayList<Person> findAllNames() throws SQLException {
        try {
            ps = c.prepareStatement("SELECT `first_name`, `last_name`, `username` FROM `student`");

            logger.trace(ps);
            rs = ps.executeQuery();

            ArrayList<Person> persons = new ArrayList<>();
            while (rs.next()) {
                person = new Person();
                person.setFirstName(rs.getString(1));
                person.setLastName(rs.getString(2));
                person.setUsername(rs.getString(3));

                persons.add(person);
            }

            ps = c.prepareStatement("SELECT `first_name`, `last_name`, `username` FROM `teacher`");

            logger.trace(ps);
            rs = ps.executeQuery();
            while (rs.next()) {
                person = new Person();
                person.setFirstName(rs.getString(1));
                person.setLastName(rs.getString(2));
                person.setUsername(rs.getString(3));

                persons.add(person);
            }
            
            ps = c.prepareStatement("SELECT `mother_name`, `father_name`, `username` FROM `family`");

            logger.trace(ps);
            rs = ps.executeQuery();
            while (rs.next()) {                                
                String motherName = rs.getString(1);
                String[] smn = motherName.split(" ");
                Person mother = new Person();
                mother.setFirstName(smn[0]);
                mother.setLastName(smn[1]);
                mother.setUsername(rs.getString(3));
                
                String fatherName = rs.getString(2);
                String[] fmn = fatherName.split(" ");
                Person father = new Person();
                father.setFirstName(fmn[0]);
                father.setLastName(fmn[1]);
                father.setUsername(rs.getString(3));

                persons.add(mother);
                persons.add(father);
            }
            
            return persons;
        } finally {
            closeStatementAndSet();
        }
    }
}
