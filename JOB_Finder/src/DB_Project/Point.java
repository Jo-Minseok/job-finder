package DB_Project;

import javax.swing.JFrame;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.sql.*;
import java.util.Date;

public class Point {

	public JFrame frame;
	private JTextField txt_charge;

	public Point() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setTitle("포인트");
		frame.setBounds(100, 100, 290, 260);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		
		JLabel lbl_present = new JLabel("현재 회원님의 포인트 : ");
		lbl_present.setBounds(12, 20, 250, 20);
		frame.getContentPane().add(lbl_present);
		
		JLabel lbl_earned = new JLabel("총 획득 포인트 : ");
		lbl_earned.setBounds(12, 45, 250, 20);
		frame.getContentPane().add(lbl_earned);
		
		JLabel lbl_used = new JLabel("총 사용 포인트 : ");
		lbl_used.setBounds(12, 70, 250, 20);
		frame.getContentPane().add(lbl_used);
		
		JLabel lbl_charge = new JLabel("[ 충전하기 ]");
		lbl_charge.setBounds(12, 110, 150, 20);
		frame.getContentPane().add(lbl_charge);
		
		txt_charge = new JTextField();
		txt_charge.setBounds(12, 130, 180, 25);
		frame.getContentPane().add(txt_charge);
		txt_charge.setColumns(10);
		
		// 포인트 로드
		
		try {
			Main.DBConnection();
			
			// 1. 이름, 현재 포인트 조회 sql_inquiry

			String tablename1;

			if(Main.mode == "개인")
				tablename1 = "개인회원";
			else
				tablename1 = "기업회원";
			
			String sql_inquiry = "SELECT 이름, 포인트 FROM " + tablename1 + "WHERE 회원ID = ?";
			
			Main.pstmt = Main.con.prepareStatement(sql_inquiry);
			Main.pstmt.setString(1, Main.ID);
			Main.rs = Main.pstmt.executeQuery();
			
			int point_present = 0;
			String name = "";
			if(Main.rs.next()) {
				name = Main.rs.getString(1);
				point_present = Integer.parseInt(Main.rs.getString(2));
			}
			lbl_present.setText("현재 " + name + " 회원님의 포인트 : " + point_present); 
			
			// 2. 포인트 획득/사용 조회 sql_point
			
			String tablename2;
			
			if(Main.mode == "개인")
				tablename2 = "개인_포인트_수정_내역";
			else
				tablename2 = "기업_포인트_수정_내역";
			
			String sql_point = "SELECT SUM(포인트) FROM " + tablename2 + " WHERE 회원ID = ? AND 내역 = ?";

			// 2-1. 개인(기업)_포인트_수정_내역에서 "획득" 포인트 조회
			
			Main.pstmt = Main.con.prepareStatement(sql_point);
			Main.pstmt.setString(1,  Main.ID);
			Main.pstmt.setString(2, "획득");
			Main.rs = Main.pstmt.executeQuery();

			int total_point_earned = 0; // 총 획득한 포인트 = DB에 기록된 획득 포인트
			if(Main.rs.next()) {
				total_point_earned = Main.rs.getInt(1);
			}
			lbl_earned.setText(lbl_earned.getText() + total_point_earned);
			
			// 2-2. 개인(기업)_포인트_수정_내역에서 "사용" 포인트 조회
			
			Main.pstmt = Main.con.prepareStatement(sql_point);
			Main.pstmt.setString(1, Main.ID);
			Main.pstmt.setString(2,  "사용");
			Main.rs = Main.pstmt.executeQuery();

			int total_point_used = 0;
			if(Main.rs.next()) {
				total_point_used = Main.rs.getInt(1);
			}
			lbl_used.setText(lbl_used.getText() + total_point_used);
			
			
		}
		catch(Exception ex) {
			JOptionPane.showMessageDialog(null, "포인트 로드를 실패했습니다.", "DB 접속 실패", JOptionPane.ERROR_MESSAGE);
		}
		finally {
			Main.DBClose();
		}

		// 충전 버튼
		
		JButton btn_charge = new JButton("충전");
		btn_charge.setBounds(162, 186, 100, 25);
		btn_charge.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				
				try {
					Main.DBConnection();
					
					// 0. 충전할 포인트
					
					int point_charge = Integer.parseInt(txt_charge.getText());
					
					// 1. 개인(기업)회원에서 현재 포인트 조회 sql_inquiry

					String tablename1;

					if(Main.mode == "개인")
						tablename1 = "개인회원";
					else
						tablename1 = "기업회원";
					
					String sql_inquiry = "SELECT 포인트 FROM " + tablename1 + "WHERE 회원ID = ?";
					
					Main.pstmt = Main.con.prepareStatement(sql_inquiry);
					Main.pstmt.setString(1, Main.ID);
					
					int point_present = 0;
					if(Main.rs.next()) {
						point_present = Main.rs.getInt(1);
					}
					
					
					// 2. 개인(기업)회원에서 포인트 수정 sql_refix
					
					
					String sql_refix = "UPDATE " + tablename1 + " SET 포인트 = ? WHERE 회원ID = ?";
					
					Main.pstmt = Main.con.prepareStatement(sql_refix);
					
					point_present += point_charge; // 현재 포인트 = 현재 + 충전 포인트
					Main.pstmt.setInt(1, point_present);
					Main.pstmt.setString(2, Main.ID);
					Main.pstmt.executeUpdate();
					
					// 3. 개인(기업)_회원_정보_변경에 추가 sql_charge
					
					String tablename2;
					
					if(Main.mode == "개인")
						tablename2 = "개인_포인트_수정_내역";
					else
						tablename2 = "기업_포인트_수정_내역";
					
					String sql_charge = "INSERT INTO " + tablename2 + " VALUES (?, ?, ?)";
					
					Main.pstmt = Main.con.prepareStatement(sql_charge);
					Main.pstmt.setString(1,  Main.ID);
					Main.pstmt.setString(2, "획득");
					Main.pstmt.setInt(3, point_charge);
					Main.pstmt.executeUpdate();
					
					if(Main.rs.next()) {
						JOptionPane.showMessageDialog(null, "충전이 완료되었습니다.", "충전 성공", JOptionPane.INFORMATION_MESSAGE);
						
						// 충전 후 포인트 UI 종료
						frame.dispose();
					}
				}
				catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "포인트 충전 실패", JOptionPane.ERROR_MESSAGE);
				}
				finally {
					Main.DBClose();
				}
				
			}
		});
		frame.getContentPane().add(btn_charge);
		
	}
}
