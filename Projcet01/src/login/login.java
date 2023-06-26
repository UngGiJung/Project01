package login;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.ScrollPane;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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
	private TextField tfId, tfPwd, tfMsg, tspfid, tspw, resultn, resultn3, resultn4, resultn5;
	private TextArea text, resultn2;
	private Button bLogin, bSignup, bsiup, bsiup2, bsech, bresult, bresult2;
	private JComboBox tlo1, tlo2, tlo3, tlo4;// tlo21, //tlo22,
	private ScrollPane scroll1;
	private MemberDAO dao;
	private DB db;
	private Panel panel;

	// 전국 도시
	String[] City = { "서울특별시", "부산광역시", "대구광역시", "인천광역시", "광주광역시", "대전광역시", "울산광역시", "경기도", "강원특별자치도", "충청북도", "충청남도",
			"전라북도", "전라남도", "경상북도", "경상남도", "제주도", "세종특별자치시" };

	// 서울
	String[] Town = { "강남구", "강동구", "강북구", "강서구", "구로구", "금천구", "광진구", "관악구", "노원구", "도봉구", "동대문구", "동작구", "마포구",
			"서대문구", "서초구", "성동구", "성북구", "송파구", "양천구", "영등포구", "용산구", "은평구", "종로구", "중구(서울)", "중랑구" };

	// 부산
	String[] Town2 = { "강서구", "금정구", "기장군", "남구(부산)", "동구", "동래구", "부산진구", "북구", "사상구", "사하구", "서구", "수영구", "연제구",
			"영도구", "중구(부산)", "해운대구" };

	// 대구
	String[] Town3 = { "남구(대구)", "달서구", "달성군", "동구", "북구", "서구", "수성구", "중구(대구)" };

	// 부산 강서구
	String[] Village2 = { " ", "대저동", "강동동", "명지동", "가락동", "녹산동", "가덕도동" };

	// 부산 금정구
	String[] Village3 = { " ", "서제동", "금사회동동", "부곡동", "장전동", "선두구동",
			"청룡노포동", "남산동", "구서동", "금성동" };

	// 서울 강남구
	String[] Village4 = { " ", "개포동", "논현동", "대치동", "도곡동",
			"삼성동", "세곡동", "수서동", "신사동", "압구정동", "역삼동", "일원동", "일원본동", "청담동" };

	// 서울 강동구
	String[] Village5 = { " ", "강일동", "고덕동", "길동", "둔촌동", "명일동", "상일동", "성내동",
			"암사동", "천호동" };

	// 대구 남구
	String[] Village6 = { " ", "대명동", "봉덕동", "이천동" };

	// 대구 달서구
	String[] Village7 = { " ", "감삼동", "도원동", "두류동", "본동", "본리동", "상인동", "성당동", "송현동", "신당동",
			"용산동", "월성동", "유천동", "이곡동", "장기동", "죽전동", "진천동" };

	public login() {
		dao = new MemberDAO();

		t = new JFrame("범죄자 정보 열람 서비스 Crime(로그인)");
		t.setBounds(100, 100, 600, 600);
		t.setLayout(null); // 프레임 안에서 라벨의 x,y 좌표를 자유롭게 해주는 명령어
		t.addWindowListener(this);

		Label lid = new Label("ID                : ");
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
				String id = data.getWord();
				String pwd = data.getWord2();

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

					Label tlow1 = new Label("City     : ");
					tlow1.setBounds(160, 300, 50, 30);

					tlo1 = new JComboBox(City);
					tlo1.setBounds(210, 300, 300, 30);

					Label tlow2 = new Label("Town  : ");
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

					tMain2.add(bsech);
					tMain2.add(text);

				} else {
					System.out.println("비밀번호가 틀렸습니다, 다시 입력하세요.");
					tfMsg.setText("비밀번호가 틀렸습니다, 다시 입력하세요.");

					Dialog info = new Dialog(tMain, "Error Message!", true);
					info.setSize(200, 100);
					info.setLocation(300, 350);
					info.setLayout(new FlowLayout());

					Label msg = new Label("Password is not sure", Label.CENTER);
					Button ok = new Button("Ok");

					ok.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							info.dispose();
						}
					});

					info.add(msg);
					info.add(ok);

					info.setVisible(true);

				}
			} else {
				tfMsg.setText("ID가 틀렸습니다. 다시 입력하세요.");

				Dialog info = new Dialog(tMain, "Error Message!", true);
				info.setSize(200, 100);
				info.setLocation(300, 350);
				info.setLayout(new FlowLayout());

				Label msg = new Label("DB does not have an ID.", Label.CENTER);
				Button ok = new Button("Ok");

				ok.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						info.dispose();
					}
				});

				info.add(msg);
				info.add(ok);

				info.setVisible(true);
			}

		} else if (e.getSource() == bSignup) {

			tsup = new JFrame("범죄자 정보 열람 서비스 Crime(회원가입/탈퇴)");
			tsup.setLayout(null);
			tsup.setBounds(300, 300, 800, 800);
			tsup.addWindowListener(this);
			tsup.setVisible(true);

			Label lid2 = new Label("SignUp ID    : ");
			lid2.setBounds(200, 250, 80, 30);

			tspfid = new TextField();
			tspfid.setBounds(300, 250, 300, 30);

			Label lid3 = new Label("SignUp PW : ");
			lid3.setBounds(200, 350, 80, 30);

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
						String id = data.getWord();
						String pwd = data.getWord2();

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

			db = new DB();

			String local = tlo1.getSelectedItem().toString();
			String local2 = tlo2.getSelectedItem().toString();
			String local3 = tlo3.getSelectedItem().toString();
			String localname = local + " " + local2 + " " + local3;
			System.out.println(localname);

			tMain3 = new JFrame("범죄자 정보 열람 서비스 Crime(검색결과)");
			tMain3.setLayout(null);
			tMain3.setBounds(80, 80, 1000, 1000);
			tMain3.addWindowListener(this);
			tMain3.setVisible(true);


			Label resultN = new Label("Search Result : ");
			resultN.setBounds(15, 27, 100, 30);

			resultn = new TextField(localname);
			resultn.setBounds(115, 23, 300, 30);
			resultn.setEditable(false);

			bresult = new Button("Re:Search");
			bresult.setBounds(420, 21, 80, 30);
			bresult.addActionListener(this);

			bresult.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					tMain3.dispose();
				}
			});

			try {
				tMain3.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("C:\\50.png")))));
			} catch (IOException r) {
				r.printStackTrace();
			}
			
			
			tMain3.add(resultN);
			tMain3.add(resultn);
			tMain3.add(bresult);

			ArrayList<MemberVo> list = db.list(local, local2);

			System.out.println("list.size() :" + list.size());
			if (list.size() == 1) {
				MemberVo data = (MemberVo) list.get(0);
				String murder = data.getWord();
				String robbery = data.getWord2();
				String theft = data.getWord3();
				String violence = data.getWord4();

				System.out.println("DB ==> " + murder + " " + robbery + " " + theft + " " + violence);

				resultn2 = new TextArea();
				resultn2.setBounds(535, 5, 430, 70);
				resultn2.setEditable(false);
				resultn2.setText("[ '구' 단위 지역 내  강력범죄 건수]\n\n" + "[ 살인 :     " + murder + " ]" + "   [ 강도 :     "
						+ robbery + " ]" + "    [ 절도 :     " + theft + " ]" + "    [ 폭력 :     " + violence + " ]");

				tMain3.add(resultn2);

			}

			ArrayList<MemberVo> list2 = db.list(local, local2, local3);

			System.out.println("list.size() :" + list2.size());

			if (list2.size() != 0) {
				
				panel = new Panel();
				panel.setLayout(null);
				panel.setBounds(0, 125, 960, (list2.size()*100)+50);
				panel.setBackground(Color.gray);

				scroll1 = new ScrollPane();
				scroll1.setBounds(0, 125, 985, 700);
				
				
				scroll1.add(panel);
				tMain3.add(scroll1);

				for (int i = 0; i < list2.size(); i++) {
					MemberVo data2 = (MemberVo) list2.get(i);

					String name = data2.getWord();
					String address1 = data2.getWord2();
					String address2 = data2.getWord3();

					System.out.println("DB ==> " + name + " " + address1 + " " + address2);

					resultn3 = new TextField(address1 + "   -----[주민등록상 주소지]");
					resultn3.setBounds(200, 10 + (i * 100), 650, 30);
					resultn3.setEditable(false);

					resultn5 = new TextField(address2 + "   -----[실제거주중 주소지]");
					resultn5.setBounds(200, 45 + (i * 100), 650, 30);
					resultn5.setEditable(false);

					resultn4 = new TextField(name);
					resultn4.setBounds(20, 10 + (i * 100), 150, 30);
					resultn4.setEditable(false);
					
					bresult2 = new Button("Detail Info");
					bresult2.setBounds(875, 10 + (i * 100), 65, 65);
					bresult2.addActionListener(this);

					panel.add(resultn3);
					panel.add(resultn4);
					panel.add(resultn5);
					panel.add(bresult2);

				}
			} else {
				Dialog info2 = new Dialog(tMain3, "Error Message!", true);
				info2.setSize(200, 100);
				info2.setLocation(500, 500);
				info2.setLayout(new FlowLayout());

				Label msg2 = new Label("DB does not have", Label.CENTER);
				Button ok2 = new Button("Ok");

				ok2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						info2.dispose();
					}
					});

					info2.add(msg2);
					info2.add(ok2);

					info2.setVisible(true);
				System.out.println("범죄자 검색이 잘못되었습니다.");
			}
		}
	}
}
