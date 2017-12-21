package com.person;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
import com.google.gson.Gson;
@Path("/person")
@Produces(MediaType.TEXT_PLAIN)
public class Gestion {
  public String userBD = "root";
  public  String passwordBD = "4991";
  public String BDD = "person";
  public String TABLE = "p";
  @GET
  @Path("")
  @Produces(MediaType.TEXT_HTML)
  public  String getPersonByIDq(@QueryParam("id") String id) {
    Connection connection = null;
    String nom ="";
    String prenom ="";
    try{
      String connectionURL = "jdbc:mysql://localhost:3306/"+BDD;
      Class.forName("com.mysql.jdbc.Driver").newInstance();
      connection = DriverManager.getConnection(connectionURL, userBD, passwordBD);
      String query = "SELECT * FROM "+TABLE+" WHERE id ="+id;
      Statement st = connection.createStatement( );
      ResultSet rs = st.executeQuery(query);
      rs.next();
      nom = rs.getString("nom");
      prenom = rs.getString("prenom");
    }catch (InstantiationException | IllegalAccessException |ClassNotFoundException | SQLException e){
        prenom = e.getLocalizedMessage();
      }
      return " <p><b>Personne : </br> id : </b>"+id+"</br><b>  nom :  </b>"+ nom+"</br><b> prenom : </b> "+prenom+"</p>";
  }
  //@DELETE
  @POST
  @Path("/del/id")
  //@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  @Produces(MediaType.TEXT_HTML)
  public String delPerson(
		@FormParam("id") String id) {
      Connection connection = null;
      String etat= "<b>Personne "+id+" :</b> supprimer avec succee" ;
      try{
        String connectionURL = "jdbc:mysql://localhost:3306/"+BDD;
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        connection = DriverManager.getConnection(connectionURL, userBD, passwordBD);
                String query = "DELETE FROM "+TABLE+" where id="+id;
                Statement st = connection.createStatement();
               	st.executeUpdate(query);
      } catch (InstantiationException | IllegalAccessException |ClassNotFoundException | SQLException e){
          etat = e.getLocalizedMessage();
        }
         return "<p>"+etat+"</p>";
  }
  //@PUT
  @POST
  @Path("/edit/nom")
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  @Produces(MediaType.TEXT_PLAIN)
  public String editPersonNom(
    @FormParam("id") int id,@FormParam("nom") String nom) {
      Connection connection = null;
      String etat= "nom Person "+id+" editer avec succee" ;
      try{
        String connectionURL = "jdbc:mysql://localhost:3306/"+BDD;
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        connection = DriverManager.getConnection(connectionURL, userBD, passwordBD);
                String query = "UPDATE "+TABLE+" SET nom = ? WHERE id = ? ";
                PreparedStatement ps = connection.prepareStatement(query);
                ps.setString(1, nom );
                ps.setInt(2, id );
                 ps.executeUpdate();
      } catch (InstantiationException | IllegalAccessException |ClassNotFoundException | SQLException e){
          etat = e.getLocalizedMessage();
        }
        return "<p>"+etat+"</p>";
  }
  //@PUT
  @POST
  @Path("/edit/prenom")
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  @Produces(MediaType.TEXT_HTML)
  public String editPersonPrenom(
    @FormParam("id") int id,@FormParam("prenom") String prenom) {
      Connection connection = null;
      String etat= "<b>nom Person "+id+":</b> editer avec succee" ;
      try{
        String connectionURL = "jdbc:mysql://localhost:3306/"+BDD;
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        connection = DriverManager.getConnection(connectionURL, userBD, passwordBD);
                String query = "UPDATE "+TABLE+" SET prenom = ? WHERE id = ? ";
                PreparedStatement ps = connection.prepareStatement(query);
                ps.setString(1, prenom );
                ps.setInt(2, id );
                 ps.executeUpdate();

      } catch (InstantiationException | IllegalAccessException |ClassNotFoundException | SQLException e){
          etat = e.getLocalizedMessage();
        }
        return "<p>"+etat+"</p>";
  }
  @POST
  @Path("/edit/age")
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  @Produces(MediaType.TEXT_HTML)
  public String editPersonAge(
    @FormParam("id") int id,@FormParam("age") int age) {
      Connection connection = null;
      String etat= "<b>age Personne "+id+": </b> editer avec succee" ;
      try{
        String connectionURL = "jdbc:mysql://localhost:3306/"+BDD;
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        connection = DriverManager.getConnection(connectionURL, userBD, passwordBD);
                String query = "UPDATE "+TABLE+" SET age = ? WHERE id = ? ";
                PreparedStatement ps = connection.prepareStatement(query);
                ps.setInt(1, age );
                ps.setInt(2, id );
                 ps.executeUpdate();
      } catch (InstantiationException | IllegalAccessException |ClassNotFoundException | SQLException e){
          etat = e.getLocalizedMessage();
        }
        return "<p>"+etat+"</p>";
  }
  @POST
  @Path("/add")
  @Produces(MediaType.TEXT_HTML)
  public String addPerson(
		@FormParam("nom") String nom,
		@FormParam("prenom") String prenom,
    @FormParam("age") int age) {
      Connection connection = null;
      String etat= "<b>M."+nom+" "+prenom+"</b> a ete  Ajouter avec succee" ;
      try{
        String connectionURL = "jdbc:mysql://localhost:3306/"+BDD;
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        connection = DriverManager.getConnection(connectionURL, userBD, passwordBD);
        String query = "INSERT INTO "+TABLE+"(nom,prenom,age) VALUES (?,?,?) ";
        PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1,nom );
             ps.setString(2, prenom );
             ps.setInt(3, age );
              ps.executeUpdate();
      } catch (InstantiationException | IllegalAccessException |ClassNotFoundException | SQLException e){
          etat = e.getLocalizedMessage();
        }
        return "<p>"+etat+"</p>";
  }
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
      public Response getAll(){
        Connection connection = null;
        int id = 0;
        int age = 0;
        String nom ="nom";
        String prenom ="prenom";
        ArrayList<Personne> myList = new ArrayList<Personne>();
        try{
          String connectionURL = "jdbc:mysql://localhost:3306/"+BDD;
          Class.forName("com.mysql.jdbc.Driver").newInstance();
          connection = DriverManager.getConnection(connectionURL, userBD, passwordBD);
          String query = "SELECT * FROM "+TABLE;
          Statement st = connection.createStatement( );
          ResultSet rs = st.executeQuery(query);
          while(rs.next()){
            Personne p = new Personne(id, nom, prenom, age);
            p.setId(rs.getInt("id"));
            p.setNom(rs.getString("nom"));
            p.setPrenom(rs.getString("prenom"));
            p.setAge(rs.getInt("age"));
            myList.add(p);
          }
        }catch (InstantiationException | IllegalAccessException |ClassNotFoundException | SQLException e){
             e.getLocalizedMessage();
          }
          Gson gson = new Gson();
		       String jsonList = gson.toJson(myList);
    		return Response.status(Response.Status.OK).entity(jsonList).build();
      }
  }
