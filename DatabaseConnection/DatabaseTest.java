package DatabaseConnection;

import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

public class DatabaseTest {
	Database database; 
	User testuser; 
	@Before
	public void testBefore() {
		// TODO Auto-generated constructor stub
		try {
			this.database = new Database();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		testuser = new User("Testing123", "Testing123");
		
	}
	
	
	
	
	@Test() public void testSaveAccount() {
		this.database.verifyUserExistsForCreateUser(testuser);
		
		String result = this.database.query(String.format("SELECT * FROM USER WHERE username='%s'", testuser.getUsername())).get(0);
		
		String[] result2 = result.split(",");
		for (int i = 0; i < result2.length; i++) {
			result2[i] = result2[i].trim();
		}
		assertTrue(testuser.getUsername().contentEquals(result2[0]));
		
	}
	

}
