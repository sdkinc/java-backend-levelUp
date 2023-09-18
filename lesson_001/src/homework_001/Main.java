package homework_001;

/**
 * 9/4/2023
 * Java Reflection API
 *
 * @author Marsel Sidikov (AIT TR)
 */
public class Main {
    public static void main(String[] args) {

        homework_001.SqlGenerator_try1 sqlGenerator = new homework_001.SqlGenerator_try1();
        String sqlString = sqlGenerator.generateTable(Staff.class);

        System.out.println(sqlString);

        sqlString = sqlGenerator.generateTable(Apartment.class);

        System.out.println(sqlString);


    }
}
