import org.sql2o.Connection;
import org.sql2o.Sql2oException;

import java.util.List;

public class Animal {
    private int animal_id;
    private int animal_age;
    private String animal_name;

    public Animal(int animal_id, int animal_age, String animal_name){
        this.animal_id= animal_id;
        this.animal_age= animal_age;
        this.animal_name= animal_name;
    }

    public int getAnimal_id() {
        return animal_id;
    }

    public int getAnimal_age() {
        return animal_age;
    }

    public String getAnimal_name() {
        return animal_name;
    }

    public void save(){
        try(Connection conn = DB.sql2o.open()){
            String sql = "INSERT INTO animals(animal_id, animal_age, animal_name ) VALUES(:animal_id, :animal_age, :animal_name )";
            this.animal_id = (int ) conn.createQuery(sql , true)
                    .addParameter("animal_id", this.animal_id)
                    .addParameter("animal_age", this.animal_age)
                    .addParameter("animal_name", this.animal_name)
                    .executeUpdate()
                    .getKey();
        }
    }
    public static List <Animal> all(){
        String sql = "SELECT * FROM animals";
        try (Connection conn = DB.sql2o.open()){
            return conn.createQuery(sql).executeAndFetch(Animal.class);
        } catch (Sql2oException ex){
            System.out.println(ex);
            return null;
        }
    }
}
