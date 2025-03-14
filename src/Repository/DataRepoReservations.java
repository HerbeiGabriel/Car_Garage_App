package Repository;

import Domain.Reservation;

import java.sql.*;
import java.time.LocalDate;

public class DataRepoReservations extends DataRepository<Integer, Reservation> {

    private String URL;
    private String table;

    public DataRepoReservations(String repopath, String table) {
        this.URL="jdbc:sqlite:" + repopath;
        this.table=table;
        readFromDB();
    }


    @Override
    public void writetoDB(Reservation elem) {
        String query="INSERT INTO "+ table + " VALUES(?, ?, ?, ?, ?)";
        try{
            Connection conn= DriverManager.getConnection(URL);
            PreparedStatement ps=conn.prepareStatement(query);
            ps.setInt(1, elem.getId());
            ps.setInt(2, elem.getCarid());
            ps.setString(3, elem.getDate().toString());
            ps.setString(4, elem.getCustomername());
            ps.setInt(5, elem.getPrice());
            ps.executeUpdate();
            conn.close();
            ps.close();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    @Override
    public void updateDB(Integer integer, Reservation elem) {
        String query="UPDATE "+ table +" SET carid=?, date=?, customername=?, price=? WHERE id=?";
        try{
            Connection conn=DriverManager.getConnection(URL);
            PreparedStatement ps=conn.prepareStatement(query);
            ps.setInt(1, elem.getCarid());
            ps.setString(2, elem.getDate().toString());
            ps.setString(3, elem.getCustomername());
            ps.setInt(4, elem.getPrice());
            ps.setInt(5, integer);
            ps.executeUpdate();
            conn.close();
            ps.close();
        }catch (SQLException e){
            System.out.println(e);
        }
    }

    @Override
    public void readFromDB() {
    try{
        Connection conn=DriverManager.getConnection(URL);
        PreparedStatement ps=conn.prepareStatement("SELECT * FROM "+ table);
        ResultSet rs=ps.executeQuery();
        while(rs.next()){
            int id=rs.getInt("id");
            int carid=rs.getInt("carid");
            String date=rs.getString("date");
            LocalDate date1=LocalDate.parse(date);
            String customername=rs.getString("customername");
            int price=rs.getInt("price");
            Reservation res=new Reservation(id, carid, date1, customername, price);
            map.put(id,res);
        }
        rs.close();
        ps.close();
        conn.close();
    }catch (Exception e){
        System.out.println(e);
    }
    }

    @Override
    public void deleteDB(Integer integer) {
    String query="DELETE FROM "+ table +" WHERE id=?";
    try{
        Connection conn=DriverManager.getConnection(URL);
        PreparedStatement ps=conn.prepareStatement(query);
        ps.setInt(1, integer);
        ps.executeUpdate();
        ps.close();
        conn.close();
    }catch (SQLException e){
        System.out.println(e);
    }
    }
}
