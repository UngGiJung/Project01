package login;

import java.awt.Button;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
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
	private Button bLogin, bSignup, bsiup, bsiup2, bsech;
	private JComboBox tlo1, tlo2, tlo3, tlo4;// tlo21, //tlo22,
	private MemberDAO dao, dao2;
	private BufferedImage player;

	// 전국 도시
	String[] City = { "서울특별시", "부산광역시", "대구광역시", "인천광역시", "광주광역시", "대전광역시", "울산광역시", "경기도", "강원특별자치도", "충청북도", "충청남도",
			"전라북도", "전라남도", "경상북도", "경상남도", "제주도", "세종특별자치시" };

	// 서울
	String[] Town = { "강남구", "강동구", "강북구", "강서구", "구로구", "금천구", "광진구", "관악구", "노원구", "도봉구", "동대문구", "동작구", "마포구",
			"서대문구", "서초구", "성동구", "성북구", "송파구", "양천구", "영등포구", "용산구", "은평구", "종로구", "중구(서울)", "중랑구" };
	
	// 부산
	String[] Town2 = { "강서구", "금정구", "기장군", "남구(부산)", "동구", "동래구", "부산진구", "북구", "사상구", "사하구", "서구", "수영구", "연제구", "영도구",
			"중구(부산)", "해운대구" };
	
	// 대구
	String[] Town3 = { "남구(대구)", "달서구", "달성군", "동구", "북구", "서구", "수성구", "중구(대구)" };
	

	String[] Village = { "kim", "jhon", "hyosoo", "namyun" };

	// 부산 강서구
	String[] Village2 = { "대저1동", "대저2동", "강동동", "명지1동", "명지2동", "가락동", "녹산동", "가덕도동" };

	// 부산 금정구
	String[] Village3 = { "서제1동", "서제2동", "서제3동", "금사회동동", "부곡제1동", "부곡제2동", "부곡제3동", "부곡제4동", 
			"장전제1동", "장전제2동", "선두구동", "청룡노포동", "남산동", "구서제1동", "구서제2동", "금성동" };
	
	// 서울 강남구
	String[] Village4 = { "개포1동", "개포2동", "개포3동", "개포4동", "논현1동", "논현2동", "대치1동", "대치2동", "대치4동", 
			"도곡1동", "도곡2동", "삼성1동", "삼성2동", "세곡동", "수서동", "신사동", "압구정동", "역삼1동", "역삼2동", "일원1동", "일원본동", "청담동" };
	
	// 서울 강동구
	String[] Village5 = { "강일동", "고덕1동", "고덕2동", "길동", "둔촌1동", "둔촌2동", "명일1동", "명일2동", "상일2동", "성내1동", "성내2동", "성내3동",
			 "암사1동", "암사2동", "암사3동", "천호1동", "천호2동", "천호3동"};
	
	// 대구 남구
	String[] Village6 = { "대명1동", "대명2동", "대명3동", "대명4동", "대명5동", "대명6동", "대명7동", "대명8동", "대명9동", "대명10동", "봉덕1동", "봉덕2동", "봉덕3동", "이천동" };
	
	// 대구 달서구
	String[] Village7 = { "감삼동", "도원동", "두류1,2동", "본동", "본리동", "상인1동", "상인2동", "상인3동", "성당동", "송현1동", "송현2동", "신당동", "용산1동", "용산2동", "월성1동", "월성2동",
			"유천동", "이곡1동", "이곡2동", "장기동", "죽전동", "진천동"};

	String[] ETC = { "kim", "jhon", "hyosoo", "namyun" };
	
	public login() {
		dao = new MemberDAO();
		dao2 = new MemberDAO();

		t = new JFrame("범죄자 정보 열람 서비스 Crime(로그인)");
		t.setSize(600, 600);
		t.setLayout(null); // 프레임 안에서 라벨의 x,y 좌표를 자유롭게 해주는 명령어
		t.addWindowListener(this);

		Label lid = new Label("ID : ");
		lid.setBounds(70, 201, 70, 30);

		tfId = new TextField();
		tfId.setBounds(160, 200, 300, 30);
		// tfId.setText("Login ID");

		Label lpwd = new Label("Password : ");
		lpwd.setBounds(70, 270, 70, 30);

		tfPwd = new TextField();
		tfPwd.setBounds(160, 270, 300, 30);
		tfPwd.setEchoChar('*');
		// tfPwd.setText("PassWord");

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
			player = ImageIO.read(new File("C:\\Logo.png"));
			
		} catch (IOException e) {
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

		// 이미지 넣기
		

	}
		
	public static void main(String[] args) {
		new login();
	}
	

	public void windowClosing(WindowEvent e) {
		System.out.println(e.getComponent().getName());

		if (e.getComponent() == tMain) {
			tMain.dispose();
		} else if (e.getComponent() == t) {
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

		if (e.getSource() == bLogin) {
			// 로그인
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

					// 로그인 성공시 지역찾기 실행
					tMain2 = new JFrame("범죄자 정보 열람 서비스 Crime(지역찾기)");
					tMain2.setLayout(null);
					tMain2.setBounds(80, 80, 700, 800);
					tMain2.addWindowListener(this);
					tMain2.setVisible(true);

					text = new TextArea();
					text.setBounds(190, 100, 300, 150);
					text.setText("지역 세부 정보를 입력하시면\n\n" + "1.해당 지역의 성범죄자의 신상정보\n\n" + "2.해당 지역의 강력범죄 발생 빈도\n\n" + ""
							+ "를 조회하실 수 있습니다.");
					text.setEditable(false);
					text.setFocusable(false);

					Label tlow1 = new Label("City : ");
					tlow1.setBounds(160, 300, 50, 30);

					tlo1 = new JComboBox(City);
					tlo1.setBounds(210, 300, 300, 30);

					Label tlow2 = new Label("Town : ");
					tlow2.setBounds(160, 440, 50, 30);

					tlo1.addItemListener(new ItemListener() {
						public void itemStateChanged(ItemEvent e) {

							if (e.getStateChange() == ItemEvent.SELECTED) {
								String index = tlo1.getSelectedItem().toString();
								if (index.equals("부산광역시")) {

									tlo2.removeAllItems();

									for (int i = 0; i < Town2.length; i++) {
										tlo2.addItem(Town2[i]);
																		
									}
								} else if (index.equals("서울특별시")) {

									tlo2.removeAllItems();

									for (int i = 0; i < Town.length; i++) {
										tlo2.addItem(Town[i]);
									}
								} else if (index.equals("대구광역시")) {

									tlo2.removeAllItems();

									for (int i = 0; i < Town3.length; i++) {
										tlo2.addItem(Town3[i]);
									}
								}
							}
						}
					});

					tlo2 = new JComboBox(Town);
					tlo2.setBounds(210, 440, 300, 30);

					Label tlow3 = new Label("Village : ");
					tlow3.setBounds(160, 570, 50, 30);
					
					tlo2.addItemListener(new ItemListener() {
						public void itemStateChanged(ItemEvent e) {

							if (e.getStateChange() == ItemEvent.SELECTED) {
								String index = tlo2.getSelectedItem().toString();
								if (index.equals("강서구")) {

									tlo3.removeAllItems();

									for (int i = 0; i < Village2.length; i++) {
										tlo3.addItem(Village2[i]);
																		
									} 
								} else if (index.equals("금정구")) {

									tlo3.removeAllItems();

									for (int i = 0; i < Village3.length; i++) {
										tlo3.addItem(Village3[i]);
																		
									}
								} else if (index.equals("강남구")) {

									tlo3.removeAllItems();

									for (int i = 0; i < Village4.length; i++) {
										tlo3.addItem(Village4[i]);
																		
									}
								} else if (index.equals("강동구")) {

									tlo3.removeAllItems();

									for (int i = 0; i < Village5.length; i++) {
										tlo3.addItem(Village5[i]);
																		
									}
								} else if (index.equals("남구(대구)")) {

									tlo3.removeAllItems();

									for (int i = 0; i < Village6.length; i++) {
										tlo3.addItem(Village6[i]);
																		
									}
								} else if (index.equals("달서구")) {

									tlo3.removeAllItems();

									for (int i = 0; i < Village7.length; i++) {
										tlo3.addItem(Village7[i]);
																		
									}
								}
							}
						}
						
					});
					
					tlo3 = new JComboBox(Village4);
					tlo3.setBounds(210, 570, 300, 30);

//					Label tlow4 = new Label("ETC : ");
//					tlow4.setBounds(130, 570, 100, 100);
//
//					tlo4 = new JComboBox(ETC);
//					tlo4.setBounds(250, 600, 300, 30);

					bsech = new Button("Search");
					bsech.setBounds(300, 670, 80, 30);
					bsech.addActionListener(this);

					try {
						tMain2.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("C:\\50.png")))));
					} catch (IOException r) {
						r.printStackTrace();
					}

					tMain2.add(tlo1);
					tMain2.add(tlow1);

					tMain2.add(tlo2);
					tMain2.add(tlow2);

					tMain2.add(tlo3);
					tMain2.add(tlow3);

//					tMain2.add(tlo4);
//					tMain2.add(tlow4);

					tMain2.add(bsech);
					tMain2.add(text);

				} else {
					System.out.println("로그인에 실패했습니다, 다시 입력하세요.");
					tfMsg.setText("로그인에 실패했습니다, 다시 입력하세요.");
				}
			} else {
				tfMsg.setText("ID가 틀렸습니다. 다시 입력하세요.");
			}

		} else if (e.getSource() == bSignup) {

			tsup = new JFrame("범죄자 정보 열람 서비스 Crime(회원가입/탈퇴)");
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

			bsiup2 = new Button("Good Bye With Us!");
			bsiup2.setBounds(339, 470, 160, 30);
			bsiup2.addActionListener(this);

			try {
				tsup.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("C:\\50.png")))));
			} catch (IOException r) {
				r.printStackTrace();
			}

			tsup.add(tspfid);
			tsup.add(tspw);
			tsup.add(lid2);
			tsup.add(lid3);
			tsup.add(bsiup);
			tsup.add(bsiup2);

			bsiup.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String Singid = tspfid.getText();
					String Singpw = tspw.getText();
					MemberDAO md = new MemberDAO();
					md.insert(Singid, Singpw);

				}
			});

			bsiup2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					System.out.println(tspfid.getText() + " : " + tspw.getText());
					String strId4 = tspfid.getText();
					String strId5 = tspw.getText();

					ArrayList<MemberVo> list = dao.list(strId4, strId5);

					System.out.println("list.size() :" + list.size());
					if (list.size() == 1) {
						MemberVo data = (MemberVo) list.get(0);
						String id = data.getId();
						String pwd = data.getPassword();

						System.out.println("DB ==> " + id + " " + pwd);

						if (tspw.getText().equals(pwd)) {
							System.out.println("회원탈퇴");
							System.out.println(tspfid.getText() + " : " + tspw.getText());

							String strId2 = tspfid.getText();
							String strId3 = tspw.getText();
							MemberDAO md = new MemberDAO();
							md.delete(strId2, strId3);

						}
					}
				}

			});

		} else if (e.getSource() == bsech) {
			tMain3 = new JFrame("범죄자 정보 열람 서비스 Crime(검색결과)");
			tMain3.setLayout(null);
			tMain3.setBounds(80, 80, 1000, 1000);
			tMain3.addWindowListener(this);
			tMain3.setVisible(true);
		}
	}
}
