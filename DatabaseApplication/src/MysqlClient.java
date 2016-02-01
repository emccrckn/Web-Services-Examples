import java.sql.SQLException;
import java.util.List;

import model.City;
import model.Country;

public class MysqlClient {

	public MysqlConnector connector;
	
	public MysqlClient(){
		connector = new MysqlConnector();
	}
	public void runQueries() throws SQLException{

		//Get countries in Western Africa
		List<Country> countries = connector.getCountriesByRegion("Western Africa");
		for(Country country : countries){
			System.out.println(country);
		}
	}
	public void insertCity(City city) {
		boolean success = connector.insertCity(city);
		if(!success){
			System.err.println("Insert failed");
		}else{
			System.out.println("Insert success!");
		}
	}
	
	public static void main(String[] args) {
		MysqlClient client = new MysqlClient();
		try {
			client.runQueries();
			City city = new City();
			city.setCountryCode("BEL");
			city.setDistrict("A state");
			city.setName("S");
			city.setPopulation(1000);
			client.insertCity(city);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
