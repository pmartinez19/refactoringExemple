package refactoring;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class GestorLloguerLite {
	
	public static void main(String[] args) throws ParseException{
		// demostració de construcció d'un vehicle de category BASIC
		Vehicle vehicleBasic = new Vehicle("Tata", "Vista", Vehicle.BASIC);
		
		// demostració de construccuó d'un lloguer amb una data
		SimpleDateFormat dateFormat = new SimpleDateFormat("d/M/yyyy");
		Date date = (Date) dateFormat.parse("2/8/2013");
		Lloguer lloguerBasic = new Lloguer(date, 2, vehicleBasic);
		
		// demostració de formatat d'una data
		System.out.println(dateFormat.format(lloguerBasic.getDate()));
		
	}
	public static String historialClient(Client c) {
		return null;
		
	}
}
