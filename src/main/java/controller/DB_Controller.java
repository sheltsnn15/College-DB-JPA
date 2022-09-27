package controller;

import jakarta.persistence.*;
import model.*;
import model.Module;
import view.Alert_Boxes;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;

public class DB_Controller {

    private static final String URL = "jdbc:derby:src/main/java/miscellaneous/db/StudentsDB;create=true";
    private static final String USERNAME = "";
    private static final String PASSWORD = "";
    private static EntityManagerFactory ENTITY_MANAGER_FACTORY;

    private Connection connection = null; // manages connection

    /**
     * Database constructor, will create a new database with required entity tables
     */
    public DB_Controller() {
        try {
            ENTITY_MANAGER_FACTORY = Persistence
                    .createEntityManagerFactory("default");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            // check if the database contains a CarTable
            DatabaseMetaData databaseMetadata = connection.getMetaData();
            ResultSet resultSet = databaseMetadata.getTables(null, null, "Student", null);
            ResultSet resultSet1 = databaseMetadata.getTables(null, null, "Module", null);

            if (Field_Validation.isTableExist(connection, "Student") && Field_Validation.isTableExist(connection, "Module")) {

                if (!resultSet.next()) {
                    Statement statement = connection.createStatement();

                    statement.execute("CREATE TABLE Student(" +
                            "sID INT NOT NULL, " +
                            "sName varchar(200), " +
                            "sDOB DATE, " +
                            "PRIMARY KEY (sID))"
                    );
                }

                // student modules table
                if (!resultSet1.next()) {
                    Statement statement = connection.createStatement();
                    statement.execute("CREATE TABLE Module(" +
                            "studentID INT NOT NULL, " +
                            "moduleName varchar(255) NOT NULL UNIQUE, " +
                            "moduleGrade FLOAT NOT NULL, " +
                            "CONSTRAINT STUDENT_ID_FK FOREIGN KEY (studentID) REFERENCES Student (sID) ON DELETE CASCADE ON UPDATE RESTRICT, " +
                            "CONSTRAINT student_modules_primary_key PRIMARY KEY (studentID, moduleName)" +
                            ")"
                    );
                }
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            System.exit(1);
        }
    }

    /*
    #################### Students Methods ####################
     */

    /**
     * add students to database
     * @param sID
     * @param sName
     * @param sDOB
     */
    public void add_students_to_db(int sID, Name sName, LocalDate sDOB) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction entityTransaction = null;

        // set parameters, then execute insertNewPerson
        try {
            entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();

            Student student = new Student();
            student.setsID(sID);
            student.setsName(sName);
            student.setsDOB(sDOB);

            entityManager.persist(student);
            entityTransaction.commit();
        } // end try
        catch (Exception exception) {
            // if there is a sql error, rollback query changes made
            if (entityTransaction != null) {
                entityTransaction.rollback();
            }
            Alert_Boxes.getErrorBox("Error Box", "DB Exception", exception.getLocalizedMessage());
        } finally {
            entityManager.close();
        }

    }

    /**
     * get student data from database
     * @param sID
     * @return
     */
    public Student get_student(int sID) {
        EntityManager entityManager =
                ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction entityTransaction = null;

        String query = "SELECT s FROM Student s WHERE s.sID = :studentIdentity";

        TypedQuery<Student> typedQuery = entityManager.createQuery(query, Student.class);
        typedQuery.setParameter("studentIdentity", sID);
        Student student = null;
        try {
            student = typedQuery.getSingleResult();
            System.out.println(student.toString());
        } catch (NoResultException exception) {
            Alert_Boxes.getErrorBox("Error Box", "DB Exception", exception.getLocalizedMessage());
        } finally {
            entityManager.close();
        }
        return student;
    }

    /**
     * @return StudentList
     *  Method to get all students from Database
     */
    public List<Student> get_all_students() {
        EntityManager entityManager =
                ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction entityTransaction = null;

        String query = "SELECT s FROM Student s WHERE s.sID IS NOT NULL";

        TypedQuery<Student> typedQuery = entityManager.createQuery(query, Student.class);
        List<Student> studList = null;
        try {
            studList = typedQuery.getResultList();
            studList.forEach(studs -> System.out.println(studs.toString()));
        } catch (NoResultException exception) {
            Alert_Boxes.getErrorBox("Error Box", "DB Exception", exception.getLocalizedMessage());
        } finally {
            entityManager.close();
        }// end finally
        return studList;
    }

    /**
     * @return
     * Method to remove student modules from db
     */
    public void remove_Student_DB(int sID) {
        EntityManager entityManager =
                ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction entityTransaction = null;
        Student student = null;
        try {
            entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();
            student = entityManager.find(Student.class, sID);
            entityManager.remove(student);
            remove_studentModules_DB(sID);
            entityManager.persist(student);
            entityTransaction.commit();
        } catch (Exception exception) {
            if (entityTransaction != null) {
                entityTransaction.rollback();
            }
            Alert_Boxes.getErrorBox("Error Box", "DB Exception", exception.getLocalizedMessage());
        } finally {
            entityManager.close();
        }
    }

    public void update_student_details(int sID, Name sName, LocalDate sDOB) {
        EntityManager entityManager =
                ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction entityTransaction = null;

        String query = "UPDATE Student s SET s.sName = :name" +
                ", s.sDOB = :dob" +
                " WHERE s.sID = :id";


        // set parameters, then execute insertNewPerson
        try {
            entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();

            TypedQuery<Student> typedQuery = entityManager.createQuery(query, Student.class);
            typedQuery.setParameter("name", sName)
                    .setParameter("dob", sDOB)
                    .setParameter("id", sID);
            entityTransaction.commit();
        } // end try
        catch (Exception exception) {
            // if there is a sql error, rollback query changes made
            if (entityTransaction != null) {
                entityTransaction.rollback();
            }
            System.out.println("Class " +exception.getClass());
            exception.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    /*
    #################### Students Methods End ####################
     */
//######################################################################################################################
    /*
    #################### Students Modules Methods ####################
     */

    /**
     * method to add modules to db
     * @param module_composite_pk
     * @param mName
     * @param mGrade
     */
    public void add_modules_to_db(Module_Composite_PK module_composite_pk, String mName, float mGrade) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction entityTransaction = null;

        Module_Composite_PK mcp = new Module_Composite_PK();
        mcp.setModuleName(module_composite_pk.getModuleName());
        mcp.setStudID(module_composite_pk.getStudID());

        Module module = new Module();
        module.setModule_composite_pk(mcp);
        module.setModuleName(mName);
        module.setModuleGrade(mGrade);

        try {
            entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();

            entityManager.persist(module);

            entityTransaction.commit();
        } // end try
        catch (Exception exception) {
            if (entityTransaction != null) {
                entityTransaction.rollback();
            }
            Alert_Boxes.getErrorBox("Error Box", "DB Exception", exception.getLocalizedMessage());
        } finally {
            entityManager.close();
        }
    }

    /**
     * @param sID
     * @return
     * Method to remove student modules from db
     */
    public void remove_studentModules_DB(int sID) {
        EntityManager entityManager =
                ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction entityTransaction = null;

        String query = "DELETE FROM Module s" +
                " WHERE s.module_composite_pk.studID = :id AND s.module_composite_pk.studID IS NOT NULL";

        TypedQuery<Module> typedQuery = entityManager.createQuery(query, Module.class);
        typedQuery.setParameter("id", sID);

        List<Module> moduleList = null;
        try {
            moduleList = typedQuery.getResultList();
            moduleList.forEach(mods -> System.out.println(mods.toString()));
        } catch (NoResultException exception) {
            Alert_Boxes.getErrorBox("Error Box", "DB Exception", exception.getLocalizedMessage());
        } finally {
            entityManager.close();
        }// end finally
    }


    /**
     * method to get all student modules from db
     * @param query
     * @param sID
     * @return
     */
    public List<Module> get_all_modules(String query, int sID) {
        EntityManager entityManager =
                ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction entityTransaction = null;

        TypedQuery<Module> typedQuery = entityManager.createNamedQuery(query, Module.class);
        typedQuery.setParameter("studentID", sID);

        List<Module> moduleList = null;
        try {
            moduleList = typedQuery.getResultList();
            moduleList.forEach(mods -> System.out.println(mods.toString()));
        } catch (NoResultException exception) {
            Alert_Boxes.getErrorBox("Error Box", "DB Exception", exception.getLocalizedMessage());
        } finally {
            entityManager.close();
        }// end finally
        return moduleList;
    }


    /**
     * Method to close the db
     */
    public void close() {
        try {
            connection.close();
        } // end try
        catch (SQLException sqlException) {
            Alert_Boxes.getErrorBox("Error Box", "DB Exception", sqlException.getLocalizedMessage());
        } // end catch
    } // end method close


}
