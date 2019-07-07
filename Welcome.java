import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.*;
import java.awt.image.*;
import java.io.*;
class Welcome implements ActionListener{
	JButton welcome_button;
	JFrame welcome_frame;
	JLabel welcome_image;
	public Welcome(){
		welcome_frame=new JFrame("SENTIMENT ANALYSIS");
		welcome_button=new JButton("Welcome To Sentiment Analysis");
		welcome_frame.setBounds(400,30,1200,950);
		welcome_frame.setLayout(null);
		
		welcome_frame.setTitle("Welcome To Sentiment Analysis");
		welcome_button.setFont(new Font("Times New Roman", Font.PLAIN | Font.BOLD,30));
		
		welcome_image=new JLabel(new ImageIcon("emoticon.jpg"));
		welcome_image.setBounds(0,0,1200,950);
		welcome_frame.add(welcome_image);
		welcome_button.setBounds(380,460,500,50);
		welcome_frame.add(welcome_button);
		
		welcome_button.addActionListener(this);
		welcome_frame.setResizable(false);
		welcome_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		welcome_frame.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent ae){
		if(ae.getSource() == welcome_button){
			try{
				SentimentAnalysis e=new SentimentAnalysis();
				welcome_frame.dispose();
			}catch(Exception e){
				System.out.println(e);
			}
		}
	}
	public static void main(String args[])throws IOException{
		Welcome w=new Welcome();
	}
}