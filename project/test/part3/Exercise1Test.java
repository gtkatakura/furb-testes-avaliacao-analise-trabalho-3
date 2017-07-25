package part3;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.Test;

public class Exercise1Test {

	@Test
	public void RF001manterClientes() {
		Client client = new Client.Builder()
			.name(null)
			.address("Rua dos Alfeneiros, 71")
			.cep("82560300")
			.birthDate(LocalDate.of(1995, 1, 1))
			.openingDate(LocalDate.of(2017, 7, 24))
			.rg("2977269")
			.cpf("83784234410")
			.phone("123456789")
			.email("junit@test.org")
			.cnh("24960914640")
			.typePeople(TypePeople.PHYSICAL)
			.patherName("Darth Vader")
			.motherName("Padmé Naberrie")
			.build();
		
		ClientRepository repository = new ClientRepository();
		
		assertFalse(repository.save(client));
		assertEquals(client.getId(), 0);
		assertEquals(repository.find(client.getId()), null);
		
		client.setName("Gabriel Takashi Katakura");
		
		assertTrue(repository.save(client));
		assertEquals(repository.find(client.getId()), client);
	}

	@Test
	public void RF002emissaoDeRelatoriosDeVeiculos() {
		VehicleBrand volkswagen = new VehicleBrand("volkswagen");
		VehicleBrand ford = new VehicleBrand("ford");

		VehicleModel ecosport = new VehicleModel("ecosport");
		
		Vehicle volkswagenCar = new Vehicle.Builder()
			.brand(volkswagen)
			.model(ecosport)
			.type(TypeVehicle.CAR)
			.year(2010)
			.build();
		
		Vehicle fordCar = new Vehicle.Builder()
			.brand(ford)
			.model(ecosport)
			.type(TypeVehicle.CAR)
			.year(2010)
			.build();
		
		VehicleRepository repository = new VehicleRepository();
		
		repository.save(volkswagenCar);
		repository.save(fordCard);
		
		VehiclesReport report = new VehiclesReport.Builder()
			.brand(null)
			.model(ecosport)
			.type(TypeVehicle.CAR)
			.year(2010)
			.build();
		
		List<Vehicle> vehicles = new ArrayList<>();
		
		vehicles.add(volkswagenCar);
		vehicles.add(fordCar);
		
		assertEquals(report.generate(), vehicles);
		
		report.setBrand(volkswagen);
		vehicles.remove(fordCar);
		
		assertEquals(report.generate(), vehicles);
	}
	
	@Test
	public void RF003_emissaoDeRelatoriosDeMarketing() {
		Manufacter tesla = new Manufacter();
		VehicleBrand ford = new VehicleBrand("ford");
		
		VehicleModel ecosport = new VehicleModel("ecosport");
		
		Vehicle fordCar = new Vehicle.Builder()
			.manufacter(tesla)
			.brand(ford)
			.model(ecosport)
			.type(TypeVehicle.CAR)
			.year(2010)
			.build();
		
		Client client1 = new Client.Builder()
			.name("Takashi")
			.enrollment(LocalDate.of(2017, 10, 1))
			.birthDate(LocalDate.of(1995, 1, 1))
			.vehicle(fordCar)
			.build();
		
		Client client2 = new Client.Builder()
			.name("Katakura")
			.enrollment(LocalDate.of(2017, 9, 1))
			.birthDate(LocalDate.of(1995, 1, 1))
			.vehicle(fordCar)
			.build();
		
		ClientRepository clientRepository = new ClientRepository();
		VehicleRepository vehicleRepository = new VehicleRepository();
		
		vehicleRepository.save(fordCar);
		clientRepository.save(client1);
		clientRepository.save(client2);

		MarketingReport report = new MarketingReport.Builder()
			.monthOfEnrollment(10)
			.yearOfEnrollment(2017)
			.birthDate(LocalDate.of(1995, 1, 1))
			.manufacter(tesla)
			.model(ecosport)
			.build();
		
		List<Client> clients = new ArrayList<>();
		
		clients.add(client1);
		clients.add(client2);
		
		assertEquals(report.generate(), clients);
		
		report.setMonthOfEnrollment(10);
		clients.remove(client2);
		
		assertEquals(report.generate(), clients);
	}
}
