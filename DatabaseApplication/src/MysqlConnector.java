import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.City;
import model.Country;

public class MysqlConnector {

	public MysqlConnector() {
		try {
			// The newInstance() call is a work around for some
			// broken Java implementations

			Class.forName("com.mysql.jdbc.Driver").newInstance();
			System.out.println("JDBC driver registered");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private Connection getConnection() {
		try {
			Connection conn = DriverManager
					.getConnection("jdbc:mysql://localhost/world?" + "user=root&password=greatsqldb");

			System.out.println("Got Mysql database connection");
			return conn;
		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
		return null;
	}

	public List<Country> getCountriesByRegion(String region) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			List<Country> countries = new ArrayList<Country>();

			// Get the connection to the database
			con = getConnection();

			// Execute the query
			stmt = con.prepareStatement("select * from country where region = ?");
			stmt.setString(1, region);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Country country = new Country();
				country.setCode(rs.getString("code"));
				country.setContinent(rs.getString("continent"));
				country.setName(rs.getString("name"));
				country.setRegion(rs.getString("region"));
				countries.add(country);
			}
			return countries;
		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		} finally {
			// it is a good idea to release
			// resources in a finally{} block
			// in reverse-order of their creation
			// if they are no-longer needed

			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException sqlEx) {
				} // ignore

				rs = null;
			}

			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException sqlEx) {
				} // ignore

				stmt = null;
			}
			if(con != null){
				try {
					con.close();
				} catch (SQLException sqlEx) {
				} // ignore

				con = null;
			}

		}
		return null;
	}

	public boolean insertCity(City city) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		
		try {
			// Get the connection to the database
			con = getConnection();

			// Execute the query
			stmt = con.prepareStatement("insert into city values(LAST_INSERT_ID(),?,?,?,?)");
			stmt.setString(1, city.getName());
			stmt.setString(2, city.getCountryCode());
			stmt.setString(3, city.getDistrict());
			stmt.setInt(4, city.getPopulation());
			return stmt.executeUpdate() > 0;

		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		} finally {
			// it is a good idea to release
			// resources in a finally{} block
			// in reverse-order of their creation
			// if they are no-longer needed

			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException sqlEx) {
				} // ignore

				rs = null;
			}

			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException sqlEx) {
				} // ignore

				stmt = null;
			}
			if(con != null){
				try {
					con.close();
				} catch (SQLException sqlEx) {
				} // ignore

				con = null;
			}

		}
		return false;
	}
}
