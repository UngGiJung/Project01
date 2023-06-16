package login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class MemberDAO {

	String url = "jdbc:oracle:thin:@localhost:1521/xe";
	String user = "c##firstproj";
	String password = "firstproj";

	private Connection con;
	private Statement stmt = null;
	private ResultSet rs;

	public void insert(String ID, String PW) {

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("jdbc driver loading success.");

			con = DriverManager.getConnection(url, user, password);
			System.out.println("oracle connection success.");
			stmt = con.createStatement();

//        public void insert(String ID,String PW) {

			String sql = "INSERT INTO MEMBER";
			sql += " VALUES (" + "'" + ID + "'" + "," + "'" + PW + "'" + ")"; // 회원가입ID//회원가입PW

			System.out.println("데이터 insert 성공!!");
			System.out.println(sql);

			stmt.execute(sql);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}


public ArrayList<MemberVo> list(String id) {
    ArrayList<MemberVo> list =new ArrayList<MemberVo>();
    
    try {
       connDB();
       
       String query ="SELECT * FROM member ";
       if(id !=null) {
          query += "where id =TRIM('"+id+"')";
          
       }
       System.out.println("SQL : " +query);
       
       rs=stmt.executeQuery(query);
       rs.last();
       System.out.println("rs.getRow() : "+rs.getRow());
       
       if(rs.getRow() ==0) {
          System.out.println("0 row selected.....");
       }else {
          System.out.println(rs.getRow()+"rows selected.......");
          rs.previous();
          while(rs.next()) {
             String sh =rs.getString("id");
             String password =rs.getString("password");
                            
             MemberVo data = new MemberVo(sh,password);
             list.add(data);
          }
       }
    }catch(Exception e) {
       e.printStackTrace();
    }
     
      return list;
  }

  public void connDB() {
      try {
          Class.forName("oracle.jdbc.driver.OracleDriver");
          System.out.println("jdbc driver loading success.");
          con = DriverManager.getConnection(url, user, password);
          System.out.println("oracle connection success.");
          
          stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
          System.out.println("statement create success.");
      } catch (Exception e) {
          e.printStackTrace();
      }
  }
}
