package login;

import java.awt.Button;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class login extends WindowAdapter implements ActionListener { 	

//public class login extends JFrame implements ActionListener {	
	
	private JFrame t, tMain, tsup, tMain2, tMain3;
	private TextField tfId, tfPwd, tfMsg, tspfid, tspw;
	private TextArea text;
	private Button bLogin, bSignup, bsiup, bsech;
	private JComboBox tlo1, tlo2,  tlo3, tlo4;//tlo21, //tlo22,
	private MemberDAO dao;

	
    String[] City={"서울특별시", "부산광역시", "대구광역시", "인천광역시", "광주광역시", "대전광역시", "울산광역시", "경기도", "강원특별자치도", "충청북도", "충청남도", "전라북도", "전라남도", "경상북도", "경상남도", "제주도", "세종특별자치시"};
    String[] Town={" ", " ", " ", " "};
    String[] Town1={"강남구", "jhon", "hyosoo", "namyun"};
    String[] Town2={"사상구", "jhon", "hyosoo", "namyun"};
    String[] Village={"kim", "jhon", "hyosoo", "namyun"};
    String[] ETC={"kim", "jhon", "hyosoo", "namyun"};
    
    

	
	
	public login() {
		dao = new MemberDAO();
		
		
			
		t = new JFrame("범죄자 정보 열람 서비스 Crime(로그인)");
		t.setSize(600, 600);
		t.setLayout(null); // 프레임 안에서 라벨의 x,y 좌표를 자유롭게 해주는 명령어
		t.addWindowListener(this);


		Label lid = new Label("ID : ");
		lid.setBounds(50, 190, 50, 50);

		tfId = new TextField();
		tfId.setBounds(160, 200, 300, 30);
		//tfId.setText("Login ID");

		Label lpwd = new Label("Password : ");
		lpwd.setBounds(50, 260, 75, 50);

		tfPwd = new TextField();
		tfPwd.setBounds(160, 270, 300, 30);
		tfPwd.setEchoChar('*');
		//tfPwd.setText("PassWord");

		bLogin = new Button("Login");
		bLogin.setBounds(200, 350, 80, 30);
		bLogin.addActionListener(this);
		
		bSignup = new Button("Sign up");
		bSignup.setBounds(330, 350, 80, 30);
		bSignup.addActionListener(this);

		tfMsg = new TextField();
		tfMsg.setBounds(160, 450, 300, 30);
		tfMsg.setEditable(false);
		tfMsg.setFocusable(false);
		
	      try {
	          t.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("C:\\70.png")))));
	          }catch(IOException e) {
	             e.printStackTrace();
	          }
	

		t.add(tfMsg);
		t.add(bLogin);
		t.add(bSignup);
		t.add(lpwd);
		t.add(tfPwd);
		t.add(lid);
		t.add(tfId);
		

		t.setVisible(true);
		
		//이미지 넣기

			       
	}
	
	public static void main(String[] args) {
		new login();
	}
	
	public void windowClosing(WindowEvent e) {
		System.out.println(e.getComponent().getName());
		
		if(e.getComponent() == tMain) {
			tMain.dispose();
		} else if (e.getComponent() == t){
			t.dispose();
		} else if (e.getComponent() == tsup) {
			tsup.dispose();
		} else if (e.getComponent() == tMain2) {
			tMain2.dispose();
		} else if (e.getComponent() == tMain3) {
			tMain3.dispose();
		} 
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
				
		if(e.getSource()==bLogin) {
			//로그인 
			System.out.println("Login!");
			System.out.println(tfId.getText() + " : " + tfPwd.getText());

			String strId = tfId.getText();

			ArrayList<MemberVo> list = dao.list(strId);
			
			System.out.println("list.size() :" + list.size());
			if (list.size() == 1) {
				MemberVo data = (MemberVo) list.get(0);
				String id = data.getId();
				String pwd = data.getPassword();

				System.out.println("DB ==> " + id + " " + pwd);

				if (tfPwd.getText().equals(pwd)) {
					System.out.println("로그인이 되었습니다.");
					tfMsg.setText("로그인이 되었습니다.");
					
					//로그인 성공시 지역찾기 실행
					tMain2 = new JFrame("범죄자 정보 열람 서비스 Crime(지역찾기)");
					tMain2.setLayout(null);
					tMain2.setBounds(80, 80, 700, 800);
					tMain2.addWindowListener(this);
					tMain2.setVisible(true);

					text = new TextArea();
					text.setBounds(250, 100, 300, 150);
					text.setText
					("지역 세부 정보를 입력하시면\n\n"
							+ "1.해당 지역의 성범죄자의 신상정보\n\n"
							+ "2.해당 지역의 강력범죄 발생 빈도\n\n"
							+ ""
							+ "를 조회하실 수 있습니다.");
					text.setEditable(false);
					text.setFocusable(false);
					
					
					Label tlow1 = new Label("City : ");
					tlow1.setBounds(130, 270, 100, 100);

					tlo1 = new JComboBox(City);
					tlo1.setBounds(250, 300, 300, 30);
					
					Label tlow2 = new Label("Town : ");
					tlow2.setBounds(130, 370, 100, 100);
					
					tlo2 =new JComboBox(Town);
					tlo2.setBounds(250, 400, 300, 30);
					
					
//					if (City.equals("서울특별시")) {
//						
//						tlo21 = new JComboBox(Town1);
//						tlo21.setBounds(250, 400, 300, 30);
//						
//						} else if (City.equals("부산광역시")) {
//							
//							tlo22 = new JComboBox(Town2);
//							tlo22.setBounds(250, 400, 300, 30);
//							
//						}
					
					Label tlow3 = new Label("Village : ");
					tlow3.setBounds(130, 470, 100, 100);

					tlo3 = new JComboBox(Village);
					tlo3.setBounds(250, 500, 300, 30);
					
					Label tlow4 = new Label("ETC : ");
					tlow4.setBounds(130, 570, 100, 100);

					tlo4 = new JComboBox(ETC);
					tlo4.setBounds(250, 600, 300, 30);
					
					bsech = new Button("Search");
					bsech.setBounds(350, 670, 80, 30);
					bsech.addActionListener(this);
					
				      try {
				          tMain2.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("C:\\50.png")))));
				          }catch(IOException r) {
				             r.printStackTrace();
				          }
					
					tMain2.add(tlo1);
					tMain2.add(tlow1);
					
					tMain2.add(tlo2);
					tMain2.add(tlow2);
					
					tMain2.add(tlo3);
					tMain2.add(tlow3);
					
					tMain2.add(tlo4);
					tMain2.add(tlow4);
					
					tMain2.add(bsech);
					tMain2.add(text); 	
					
				} else {
					System.out.println("로그인에 실패했습니다, 다시 입력하세요.");
					tfMsg.setText("로그인에 실패했습니다, 다시 입력하세요.");
				} 
			} else {
				tfMsg.setText("ID가 틀렸습니다. 다시 입력하세요.");
			}			
			
		
		
	} else if(e.getSource()==bSignup) {
			
			tsup = new JFrame("범죄자 정보 열람 서비스 Crime(회원가입)");
			tsup.setLayout(null);
			tsup.setBounds(300, 300, 800, 800);
			tsup.addWindowListener(this);
			tsup.setVisible(true);

			
			Label lid2 = new Label("SignUp ID : ");
			lid2.setBounds(180, 220, 100, 100);

			tspfid = new TextField();
			tspfid.setBounds(300, 250, 300, 30);
			
			Label lid3 = new Label("SignUp PW : ");
			lid3.setBounds(180, 320, 100, 100);

			tspw = new TextField();
			tspw.setBounds(300, 350, 300, 30);
			
			bsiup = new Button("Sign up");
			bsiup.setBounds(380, 420, 80, 30);
			bsiup.addActionListener(this);
			
		      try {
		          tsup.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("C:\\50.png")))));
		          }catch(IOException r) {
		             r.printStackTrace();
		          }
			
			tsup.add(tspfid);
			tsup.add(tspw);
			tsup.add(lid2);
			tsup.add(lid3);
			tsup.add(bsiup);
			
						
			bsiup.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String Singid = tspfid.getText();
					String Singpw = tspw.getText();
					MemberDAO md = new MemberDAO();
					md.insert(Singid,Singpw);
					
//					MemberDAO md = new MemberDAO();
//					
//			        String sql = "INSERT INTO MEMBER";
//		        	   sql += "VALUES ("+ Singid +","+ Singpw +")"; //회원가입ID//회원가입PW
//		        			
//		        	   
//		        stmt = con.createStatement();
//		        
//		        stmt.execute(sql);
//		        System.out.println("데이터 insert 성공!!");
					
					
					
				}
			});
			
		} else if (e.getSource()==bsech) {
			tMain3 = new JFrame("범죄자 정보 열람 서비스 Crime(검색결과)");
			tMain3.setLayout(null);
			tMain3.setBounds(80, 80, 1000, 1000);
			tMain3.addWindowListener(this);
			tMain3.setVisible(true);
		}
	}
}
