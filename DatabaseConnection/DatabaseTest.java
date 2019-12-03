package DatabaseConnection;

import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DatabaseTest {
	private Database database; 
	private User testuser; 
	
	private String username = "Testing123";
	private String password = "Testing123";
	
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
		
		testuser = new User(username, password);
		
	}
	
	@Test()
	public void testVerifyUserExistsForCreateUser() {
		
		assertTrue(this.database.verifyUserExistsForCreateUser(testuser));
	}
	
	@Test()
	public void testSaveAccount() {
		this.database.saveAccount(testuser);
		
		ArrayList<String> result = this.database.query(String.format("SELECT * from User WHERE username='%s'", username));
		
		assertTrue(result.size() == 1);
	}
	
	@Test() // Tests that the 
	public void testVerifyUserExistsForLogin() {

		this.database.saveAccount(testuser);
		
		assertTrue(this.database.verifyUserExistsForLogin(testuser));
	}
	

	
	@Test()
	public void testIncrement() {
		this.database.saveAccount(testuser);
		
		String[] firstResult = this.database.query(String.format("Select * from STATISTICS where username='%s'", username)).get(0).split(",");
		for (int i = 0; i < firstResult.length; i++) {
			firstResult[i] = firstResult[i].trim();
		}
		
		int expected = Integer.parseInt(firstResult[1]) + 1;
		
		this.database.increment(username, true);
		
		String[] secondResult = this.database.query(String.format("Select * from STATISTICS where username='%s'", username)).get(0).split(",");
		for (int i = 0; i < secondResult.length; i++) {
			secondResult[i] = secondResult[i].trim();
		}
		

		int actual = Integer.parseInt(secondResult[1]);
		
		assertEquals(expected, actual);
		
	}
	
	
	@After()
	public void removeTestUser() {
		try {
			this.database.executeDML(String.format("DELETE FROM Statistics WHERE username='%s'", username));
			this.database.executeDML(String.format("DELETE FROM User WHERE username='%s'", username));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}