package login;

import java.awt.Button;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JFrame;

public class MemberDAO {

	String url = "jdbc:oracle:thin:@localhost:1521/XE";
	String user = "c##firstproj";
	String password = "firstproj";

	private Connection con;
	private Statement stmt = null;
	private ResultSet rs;
	private boolean b;
	private JFrame sout;
	
	
	//회원가입
	public void insert(String ID, String PW) {
		//PW 영문소문자 4 이상, 숫자 2 이상 걸러내는 필터
		int num = 0 , str = 0;
	    char pwd = 0;		
		for (int i = 0; i < PW.length(); i++) {
			
			pwd = PW.charAt(i);
			
			if ((48 > pwd || 57 < pwd) && (97 > pwd || pwd > 122)) {
				error2();
				break;
			} else if (48 <= pwd && pwd <= 57) {
				num++;
				
			} else if ( 97 <= pwd && pwd <= 122 ) {
				str++;
				
			} else {
				error2();
				break;
			}
		}
			
		if (6 <= ID.length() && ID.length() <= 12 && 2 <= num && 4 <= str) {

		try {
			connDB();

			String sql = "INSERT INTO MEMBER";
			sql += " VALUES (" + "'" + ID + "'" + "," + "'" + PW + "'" + ")"; // 회원가입ID//회원가입PW

			System.out.println("데이터 insert 성공!!");
			System.out.println(sql);

			stmt.execute(sql);
			
			Dialog info3 = new Dialog(sout, "Complete Message!", true);
			info3.setSize(200, 100);
			info3.setLocation(600, 600);
			info3.setLayout(new FlowLayout());

			Label msg3 = new Label("Sign UP Complete..!!", Label.CENTER);
			Button ok3 = new Button("Ok");

			ok3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					info3.dispose();
				}
				});

				info3.add(msg3);
				info3.add(ok3);

				info3.setVisible(true);

		} catch (SQLException e) {
			if (e.getMessage().contains("ORA-00001")){ // SQL 오류 메세지를 받아와 이 조건일때 IF문 저의부를 실행시켜주게 만든것
				System.out.println(e);
				Dialog info2 = new Dialog(sout, "Error Message!", true);
				info2.setSize(200, 100);
				info2.setLocation(600, 600);
				info2.setLayout(new FlowLayout());

				Label msg2 = new Label("Already have the same ID..", Label.CENTER);
				Button ok2 = new Button("Ok");

				ok2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						info2.dispose();
					}
					});

					info2.add(msg2);
					info2.add(ok2);

					info2.setVisible(true);
			}
		} 
		} else if (6 > ID.length() || ID.length() > 12) {
			error();
		} else if ( 2 > num || 4 > str) {
			error2();
		} 
	}
	
	//로그인
	public ArrayList<MemberVo> list(String id) {
		ArrayList<MemberVo> list = new ArrayList<MemberVo>();

		try {
			connDB();

			String query = "SELECT * FROM member ";
			if (id != null) {
				query += "where ID =TRIM('" + id + "')";

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
					String sh = rs.getString("id");
					String password = rs.getString("password");

					MemberVo data = new MemberVo(sh, password);
					list.add(data);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	
	//DB에 ID 존재하지 않을때, PW가 다를 때(회원탈퇴)
	public ArrayList<MemberVo> list(String ID, String PW) {
		ArrayList<MemberVo> list = new ArrayList<MemberVo>();

		try {
			connDB();

			String query = "SELECT * FROM member ";
			if (ID != null) {
				query += "where ID =TRIM('" + ID + "')";

			}
			System.out.println("SQL : " + query);

			rs = stmt.executeQuery(query);
			rs.last();
			System.out.println("rs.getRow() : " + rs.getRow());

			if (rs.getRow() == 0) {
				System.out.println("0 row selected.....");
				
				Dialog info2 = new Dialog(sout, "Error Message!", true);
				info2.setSize(200, 100);
				info2.setLocation(600, 600);
				info2.setLayout(new FlowLayout());

				Label msg2 = new Label("DB does not have an ID.", Label.CENTER);
				Button ok2 = new Button("Ok");

				ok2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						info2.dispose();
					}
					});

					info2.add(msg2);
					info2.add(ok2);

					info2.setVisible(true);
			} else {
				System.out.println(rs.getRow() + "rows selected.......");
				rs.previous();
				while (rs.next()) {
					String sh = rs.getString("id");
					String password = rs.getString("password");
					MemberVo data = new MemberVo(sh, password);
					list.add(data);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	
	//회원탈퇴
	public void delete(String ID, String PW) {

		try {
			connDB();

			String query = "DELETE FROM MEMBER";
			query += " where ID =TRIM('" + ID + "')";

			System.out.println("SQL : " + query);

			b = stmt.execute(query); // excute의 결과값은 쿼리문이 정상작동 되었을때 결과값으로 false가 나온다

			if (!b) { // 쿼리문이 정상적으로 작동 되었으므로 false가 나오고 이것의 부정, 즉 그 값이 true 일때 if문 실행
				System.out.println("회원탈퇴 되었습니다..!");

				Dialog info = new Dialog(sout, "Complete Message!", true);
				info.setSize(200, 100);
				info.setLocation(600, 600);
				info.setLayout(new FlowLayout());

				Label msg = new Label("Delete Complete", Label.CENTER);
				Button ok = new Button("Ok");

				ok.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						info.dispose();

					}
				});

				info.add(msg);
				info.add(ok);

				info.setVisible(true);

			} else {
				System.out.println("회원 ID가 옳바르지 않습니다.");
	
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
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
	
	public void error() {
		
		Dialog info3 = new Dialog(sout, "회원가입 조건 설명", true);
		info3.setSize(400, 100);
		info3.setLocation(500, 500);
		info3.setLayout(new FlowLayout());
		
		//ID : 6~12자 이내    //    PW : 영문소문자4자 이상, 숫자2자 이상 조합"
		
		Label msg3 = new Label("ID : 6~12자 이내", Label.CENTER);
		Button ok3 = new Button("Ok");
		
		ok3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				info3.dispose();
			}
		});
		
		info3.add(msg3);
		info3.add(ok3);
		
		info3.setVisible(true);
	}
	
	public void error2() {
		
		Dialog info3 = new Dialog(sout, "회원가입 조건 설명", true);
		info3.setSize(400, 100);
		info3.setLocation(500, 500);
		info3.setLayout(new FlowLayout());
		
		Label msg3 = new Label("PW : 영문소문자4자 이상, 숫자2자 이상 조합", Label.CENTER);
		Button ok3 = new Button("Ok");
		
		ok3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				info3.dispose();
			}
		});
		
		info3.add(msg3);
		info3.add(ok3);
		
		info3.setVisible(true);
	}
}
