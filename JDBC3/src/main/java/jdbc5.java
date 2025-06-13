import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;



public class jdbc5 {
	private static Connection con;
	private static PreparedStatement pstmt;
	private static ResultSet rs;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		

		
			// TODO Auto-generated method stub
			
			String Fetch="select * from student where Cid=?";
			System.out.println("Enter Fetch id");
			
			int  id=new Scanner(System.in).nextInt();
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/emploee_g","root","Harish#J@1432");
				pstmt=con.prepareStatement(Fetch);
				pstmt.setInt(1, id);
				rs=pstmt.executeQuery();
				while(rs.next()==true) {
					
				int Cid=rs.getInt(1);
					String name=rs.getString(2);
					int marks=rs.getInt(3);
					System.out.println(Cid+""+name+""+marks);
					}
				
				
				
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}
			
			

		}

	}


