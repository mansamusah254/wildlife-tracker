import org.sql2o.Connection;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sighting {
    private String animal;
    private String location;
    private String ranger_name;

    private int id;

    public Sighting(String animal, String location, String rangername){
        this.animal=animal;
        this.location=location;
        this.ranger_name=rangername;
    }

    
    public String getAnimal() {
        return animal;
    }

    public String getLocation() {
        return location;
    }

    public String getRanger_name() {
        return ranger_name;
    }

    public int getId() {
        return id;
    }


    public void save(){
        try(Connection conn =  DB.sql2o.open()){
            String sql = "INSERT INTO sightings(animal, location, ranger_name) VALUES(:animal, :location, :ranger_name)";
            this.id = (int) conn.createQuery(sql,true)
                    .addParameter("animal", this.animal)
                    .addParameter("location", this.location)
                    .addParameter("ranger_name", this.ranger_name)
                    .executeUpdate()
                    .getKey();
        }
    }
    public static List<Sighting> all(){
        String sql = "SELECT * FROM sightings";
        try (Connection conn = DB.sql2o.open()){
            return conn.createQuery(sql).executeAndFetch(Sighting.class);
        } catch (Sql2oException ex){
            System.out.println(ex);
            return null;
        }
    }
}
