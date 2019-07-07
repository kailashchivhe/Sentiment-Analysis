import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.*;
import java.awt.image.*;
import java.io.*;
import java.sql.*;
public class SentimentAnalysis implements ActionListener{
	JButton calculate_button;
	JFrame emotion_frame;
	JPanel jp1,jp2;
	Panel jp3;
	JTextArea jtf1;
	JLabel jl1,jl2,jl3;
	public SentimentAnalysis()throws IOException{
		emotion_frame=new JFrame("SENTIMENT ANALYSIS");
		emotion_frame.setLayout(new GridLayout(3,1));
		emotion_frame.setBounds(400,30,1200,950);
		emotion_frame.setTitle("Sentiment Analysis");
		
		jp1=new JPanel();
		jp2=new JPanel();
		jp3=new Panel();
		//jp4=new JPanel();
		
		jp1.setBackground(new Color(32,32,32));
		jp2.setBackground(new Color(32,32,32));
		jp3.setBackground(new Color(32,32,32));
		//jp4.setBackground(new Color(32,32,32));
		//jp2.setLayout(new FlowLayout());
		jp3.setLayout(new FlowLayout());
		jl1=new JLabel("If you want to 'SHINE' like a 'SUN' , First 'BURN' like a 'SUN'");
		jl2=new JLabel("-: A.P.J. Abdul Kalam. ");
		jl3=new JLabel();
		jl1.setFont(new Font("Times New Roman", Font.PLAIN | Font.BOLD,42));
		jl2.setFont(new Font("Times New Roman", Font.PLAIN | Font.BOLD,42));
		jl1.setForeground(new Color(255,128,0));
		jl2.setForeground(new Color(255,128,0));
		jl2.setLocation(5,5);
		jl3.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("backgroundImage_2.png")).getImage().getScaledInstance(1180, 250, Image.SCALE_SMOOTH)));
		jp1.add(jl1);
		jp1.add(jl2);
		jp1.add(jl3);
		
		jtf1=new JTextArea(6,70);
		jtf1.setFont(new Font("Times New Roman", Font.PLAIN | Font.BOLD,21));
		jtf1.setText("Enter a Sentence here!");
		calculate_button=new JButton("Calculate Sentiment Analysis");
		//Image img = ImageIO.read(getClass().getResource("cbutton.png"));
		//calculate_button.setIcon(new ImageIcon(img));
		//jtf1.setLocation(100,100);
		//calculate_button.setBounds(0,0,10,10);
		calculate_button.setFont(new Font("Times New Roman", Font.PLAIN | Font.BOLD,30));
		jp2.add(jtf1);
		jp2.add(calculate_button);
		
		emotion_frame.add(jp1);
		emotion_frame.add(jp2);
		emotion_frame.add(jp3);
		//emotion_frame.add(jp4);
		calculate_button.addActionListener(this);
		emotion_frame.setResizable(false);
		emotion_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		emotion_frame.setVisible(true);
	}
	public void actionPerformed(ActionEvent ae){
		if(ae.getSource() == calculate_button){
			String sentence=jtf1.getText();
			sentence=sentence.toLowerCase();
			if(!sentence.equals("") && !sentence.equals(" ")){
				int hcnt=0,scnt=0;
				try{
					Connection con=DBConnect.kConnect();
					Statement stmt1=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
					Statement stmt2=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
					ResultSet rs1=stmt1.executeQuery("select * from happy");
					ResultSet rs2=stmt2.executeQuery("select * from sad");
					if(rs1.isBeforeFirst()){
						while(rs1.next()){
							String dword=rs1.getString(1);
							if(sentence.contains(dword)){
								hcnt++;
							}
						}
					}
					if(rs2.isBeforeFirst()){
						while(rs2.next()){
							//System.out.println("Inside sad loop");
							String dword=rs2.getString(1);
							if(sentence.contains(dword)){
								//System.out.println("Inside if sad ");
								scnt++;
							}
						}
					}
					jp3.removeAll();
					emotion_frame.setVisible(true);
					JLabel emoticon =new JLabel();
					if(hcnt > 0 && scnt == 0){
						//System.out.println("inside happy");
						//System.out.println("The sentence is happy sentence");
						JLabel message = new JLabel(" HAPPY SENTENCE !");
						message.setFont(new Font("Times New Roman", Font.PLAIN | Font.BOLD,30));
						message.setForeground(new Color(255,128,0));
						message.setLocation(5,5);
						emoticon.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("happy.png")).getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH)));
						jp3.add(emoticon);
						jp3.add(message);
						emotion_frame.add(jp3);
						emotion_frame.setVisible(true);
					}
					else if(scnt > 0 && hcnt == 0){
						//System.out.println("The sentence is sad sentence");
						//System.out.println("inside sad");
						JLabel message = new JLabel(" SAD SENTENCE !");
						message.setFont(new Font("Times New Roman", Font.PLAIN | Font.BOLD,30));
						message.setForeground(new Color(255,128,0));
						message.setLocation(5,5);
						emoticon.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("sad.png")).getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH)));
						jp3.add(emoticon);
						jp3.add(message);
						emotion_frame.add(jp3);
						emotion_frame.setVisible(true);
					}
					else if(scnt > 0 && hcnt > 0){
						//System.out.println("The sentence is nostalgic sentence");
						JLabel message = new JLabel(" NOSTALGIC SENTENCE !");
						message.setFont(new Font("Times New Roman", Font.PLAIN | Font.BOLD,30));
						message.setForeground(new Color(255,128,0));
						message.setLocation(5,5);
						emoticon.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("nost.gif")).getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH)));
						jp3.add(emoticon);
						jp3.add(message);
						emotion_frame.add(jp3);
						emotion_frame.setVisible(true);
					}
					else{
						//System.out.println("The sentence does not belong to the project domain");
						//System.out.println("inside else");
						JLabel message = new JLabel(" SENTENCE OUT OF PROJECT DOMAIN! ");
						message.setFont(new Font("Times New Roman", Font.PLAIN | Font.BOLD,30));
						message.setForeground(new Color(255,128,0));
						message.setLocation(5,5);
						emoticon.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("dom.gif")).getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH)));
						jp3.add(emoticon);
						jp3.add(message);
						emotion_frame.add(jp3);
						emotion_frame.setVisible(true);
					}
					//rs1.beforeFirst();
					//rs2.beforeFirst();
				}catch(Exception e){
					System.out.println(e);
				}
			}
			else{
				JOptionPane.showMessageDialog(null,"Please Enter a sentence");
			}
		}
	}
	public static void main(String args[])throws IOException{
		SentimentAnalysis e=new SentimentAnalysis();
	}
}