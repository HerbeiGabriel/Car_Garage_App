package Repository;

import java.sql.*;

import Domain.*;

public class DataRepoCars extends DataRepository<Integer, Car> {

    private String URL;
    private String table;

    public DataRepoCars(String repopath, String table) {
        this.URL="jdbc:sqlite:" + repopath;
        this.table=table;
        readFromDB();
    }

    @Override
    public void writetoDB(Car car) {
        try{
            Connection conn=DriverManager.getConnection(URL);
            PreparedStatement ps=conn.prepareStatement("INSERT INTO "+table+" VALUES (?,?,?,?)");
            ps.setInt(1, car.getId());
            ps.setString(2, car.getModel());
            ps.setInt(3, car.getHorsepower());
            ps.setInt(4, car.getFueltank());
            ps.executeUpdate();
            ps.close();
            conn.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void updateDB(Integer id, Car car) {
    try{
        Connection conn=DriverManager.getConnection(URL);
        PreparedStatement ps=conn.prepareStatement("UPDATE " + table +" SET model=?, horsepower=?, fueltank=? WHERE id=?");
        ps.setString(1, car.getModel());
        ps.setInt(2, car.getHorsepower());
        ps.setInt(3, car.getFueltank());
        ps.setInt(4, id);
        ps.executeUpdate();
        ps.close();
        conn.close();
    }catch (SQLException e){
        e.printStackTrace();
    }
    }

    @Override
    public void readFromDB() {
        try{
            Connection conn= DriverManager.getConnection(URL);
            PreparedStatement ps=conn.prepareStatement("SELECT * FROM " + table);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                int id=rs.getInt(1);
                String model=rs.getString(2);
                int horsepower=rs.getInt(3);
                int fueltank=rs.getInt(4);
                Car car=new Car(id, model, horsepower, fueltank);
                map.put(id, car);
            }
            try{
                rs.close();
                ps.close();
                conn.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void deleteDB(Integer id) {
    try{
        Connection conn=DriverManager.getConnection(URL);
        PreparedStatement ps=conn.prepareStatement("DELETE FROM " + table + " WHERE id=?");
        ps.setInt(1, id);
        ps.executeUpdate();
        ps.close();
        conn.close();
    }catch (SQLException e){
        e.printStackTrace();
    }
    }
}
