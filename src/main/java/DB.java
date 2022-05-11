import org.sql2o.Sql2o;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DB {

        //        public static Sql2o sql2o = new Sql2o("jdbc:postgresql://ec2-52-4-104-184.compute-1.amazonaws.com:5432/d4nqnvk43ts128","wrfjwpkhufxyaf","d422ab6da4156c4bf730ee836b00d41b9a9ce07ed2171bd432b9c227345aa2bb");
        public static Sql2o sql2o = new Sql2o("jdbc:postgresql://localhost:5432/wildlife_tracker", "postgres", "kamotho");

        public static void main(String[] args) {
                try (Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/wildlife_tracker", "postgres", "kamotho")) {
                        System.out.println("This is a trial to check if my database will connect");
                        System.out.println("Congratulations...Database connected Successfully");

                        Statement statement = con.createStatement();
                } catch (SQLException e) {
                        System.out.println("Connection failure.");
                }
        }
}