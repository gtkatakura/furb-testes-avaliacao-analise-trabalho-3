package part3;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.Test;

public class Exercise1Test {

	// Author: Gabriel Takashi Katakura
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

	// Author: Gabriel Takashi Katakura
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
	
	// Author: Gabriel Takashi Katakura
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
	
	// Author: matheuswaltrich
	@Test
	public void RF004manterFuncionarios() {
		Employee employee = new Employee.Builder()
			.name(null)
			.birthDate(LocalDate.of(1991, 05, 10))
			.phone("999999999")
			.address("Rua XV de novembro, 71")
			.salary(5000.00)
			.department("TI")
			.office("desenvolvedor")
			.pis(100.00)
			.workpermit("812732039")
			.cpf("119829374926")
			.sex("M")
			.email("joao@test.org")
			.maritalstatus("Casado")
			.cep("82560300")
			.workload(8)
			.schooling("Superior Completo")
			.openingDate(LocalDate.of(2017, 5, 05))
			.closeDate(LocalDate.of(2020, 5, 05))
			.build();
			
		EmployeeRepository employeeRepository = new EmployeeRepository();
		
		assertFalse(employeeRepository.save(employee));
		assertEquals(employee.getId(), 0);
		assertEquals(employeeRepository.find(employee), null);
		
		employee.setName("João da Silva");
		
		assertTrue(employeeRepository.save(employee));
		assertEquals(employeeRepository.find(employee.getId()), employee);
	}
	
	// Author: gabrielsbernardi
	@Test
	public void RF005() {
		
	}
	
	// Author: Orlando Krause Junior
	@Test
	public void RF006(String opcao) {
		if (opcao.equalsIgnoreCase("PESSOAL")) {
			People people = new Employee.Builder()
				.name(null)
				.birthDate(LocalDate.of(1991, 05, 10))
				.phone("999999999")
				.type("Física")
				.cnh(null)
				.address("Rua XV de novembro, 71")
				.openingDate(LocalDate.of(2017, 5, 05))
				.email("joao@test.org")
				.cpf("119829374926")
				.build();

			PeopleRepository peopleRepository = new PeopleRepository();
		
			assertFalse(peopleRepository.save(people));
			assertEquals(people.getId(), 0);
			assertEquals(peopleRepository.find(people), null);

			people.setName("João da Silva");

			assertTrue(peopleRepository.save(people));
			assertEquals(peopleRepository.find(people.getId()), people);
		} else if (opcao.equalsIgnoreCase("VEICULO")) {
			VehicleBrand volkswagen = new VehicleBrand("volkswagen");
			VehicleModel gol = new VehicleModel("gol");
			
			Vehicle volkswagenCar = new Vehicle.Builder()
				.brand(volkswagen)
				.model(gol)
				.type(TypeVehicle.CAR)
				.year(2010)
				.build();
			
			VehicleRepository repository = new VehicleRepository();
			repository.save(volkswagenCar);
			
			List<Vehicle> vehicles = new ArrayList<>();
		
			vehicles.add(volkswagenCar);
			vehicles.add(fordCar);

			assertEquals(report.generate(), vehicles);

			report.setBrand(volkswagen);
			vehicles.remove(fordCar);

			assertEquals(report.generate(), vehicles);
		}
	}
	
	// Author: evertonslv
	@Test
	public void RF007_ManterCadastroVeiculo() {
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
		
			VehicleRepository vehicleRepository = new VehicleRepository();
			
			assertTrue(vehicleRepository.save(fordCar));
			assertEquals(vehicleRepository.find(fordCar.getId()), fordCar);
			
	}
	
	// Author: evertonslv
	@Test
	public void RF008() {
		VehicleModel model = new VehicleModel("model01");
		
		Product product = new Product.Builder()
				.fabricatName("Teste")
				.model(model)
				.value(100.00)
				.serialNumber(123456)
				.status(2)
				.chassi(654321);
			
				ProductRepository productRepository = new ProductRepository();
				
				assertTrue(productRepository.save(product));
				assertEquals(productRepository.find(product.getId()), product);
	}
	
	// Author: 
	@Test
	public void RF009() {
		
	}
}
