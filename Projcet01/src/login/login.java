package login;

import java.awt.Button;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;

public class login extends WindowAdapter implements ActionListener { 	
	
	private Frame t, tMain;
	private TextField tfId, tfPwd, tfMsg;
	private Button bLogin, bSignup;
	private Image background=new ImageIcon(login.class.getResource("../image/70_1.png")).getImage();
	
	
	public login() {
		

		
		t = new Frame("범죄자 정보 열람 서비스 Crime(로그인)");
		t.setSize(600, 600);
		t.setLayout(null);
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

		t.add(tfMsg);
		t.add(bLogin);
		t.add(bSignup);
		t.add(lpwd);
		t.add(tfPwd);
		t.add(lid);
		t.add(tfId);

		t.setVisible(true);

	}
	
	public void paint(Graphics g) {
		g.drawImage(background, 0, 0, null);
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
		}
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==bLogin) {
		// TODO Auto-generated method stub
		tMain = new Frame("지역찾기");
		tMain.setBounds(100, 100, 1000, 1000);
		tMain.addWindowListener(this);
		tMain.setVisible(true);
		
		} else if(e.getSource()==bSignup) {
			
			tMain = new Frame("회원가입");
			tMain.setBounds(300, 300, 800, 800);
			tMain.addWindowListener(this);
			tMain.setVisible(true);
			
		}

	}
}
