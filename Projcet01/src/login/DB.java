package login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DB {
	
	String url = "jdbc:oracle:thin:@localhost:1521/XE";
	String user = "c##firstproj";
	String password = "firstproj";
	
	private Connection con;
	private Statement stmt;
	private ResultSet rs;
	
	public ArrayList<MemberVo> list(String local, String local2) {
		ArrayList<MemberVo> list = new ArrayList<MemberVo>();
		

		try {
			connDB();

			String query = "SELECT murder, robbery, theft, violence FROM DB ";
			if (local != null) {
				query += "where city ='" + local  + "'";
				query += " and village ='" + local2  + "'";

			}
			System.out.println("SQL : " + query);

			rs = stmt.executeQuery(query);
			rs.last();
			System.out.println("rs.getRow() : " + rs.getRow());

			if (rs.getRow() == 0) {
				System.out.println("0 row selected.....");
				
			} else {
				System.out.println(rs.getRow() + "rows selected.......");
				rs.previous();
				while (rs.next()) {
					String murder = rs.getString("murder");
					String robbery = rs.getString("robbery");
					String theft = rs.getString("theft");
					String violence = rs.getString("violence");

					MemberVo data = new MemberVo(murder, robbery, theft, violence);
					list.add(data);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
		
	}
		
		public ArrayList<MemberVo> list(String local, String local2, String local3) {
			ArrayList<MemberVo> list2 = new ArrayList<MemberVo>();
			

			try {
				connDB();

				String query = "SELECT name, address1, address2 from DB2 ";
				if (local != null) {
					query += "where address1 like'%" + local  + "%' and address1 like '%"+ local2 +"%'" + "and address1 like '%"+ local3 +"%'";
					query += " or address2 like'%" + local  + "%' and address2 like '%"+ local2 +"%'" + "and address2 like '%"+ local3 +"%'";

				} else if (local == " ") {
					query += "where address1 like'%" + local  + "%' and address1 like '%"+ local2 +"%'";
					query += " or address2 like'%" + local  + "%' and address2 like '%"+ local2 +"%'";
				}
				System.out.println("SQL : " + query);

				rs = stmt.executeQuery(query);
				rs.first();
				System.out.println("rs.getRow() : " + rs.getRow());

				if (rs.getRow() == 0) {
					System.out.println("0 row selected.....");
					
				} else {
					System.out.println(rs.getRow() + "rows selected.......");
					rs.previous();
					while (rs.next()) {
						String name = rs.getString("NAME");
						String address1 = rs.getString("ADDRESS1");
						String address2 = rs.getString("ADDRESS2");

						MemberVo data = new MemberVo(name, address1, address2);
						list2.add(data);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			return list2;
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
