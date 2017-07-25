package part3;

import static org.junit.Assert.*;

import java.awt.Color;
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
			.vehicleRepository(repository)
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
		Manufacter volkswagenManufacter = new Manufacter("volkswagen");
		Manufacter fordManufacter = new Manufacter("ford");
		
		VehicleBrand volkswagen = new VehicleBrand("volkswagen");
		VehicleBrand ford = new VehicleBrand("ford");
		
		VehicleModel ecosport = new VehicleModel("ecosport");
		
		Vehicle volkswagenCar = new Vehicle.Builder()
			.manufacter(volkswagenManufacter)
			.brand(volkswagen)
			.model(ecosport)
			.type(TypeVehicle.CAR)
			.year(2010)
			.build();
		
		Vehicle fordCar = new Vehicle.Builder()
			.manufacter(fordManufacter)
			.brand(ford)
			.model(ecosport)
			.type(TypeVehicle.CAR)
			.year(2010)
			.build();
		
		Client volkswagenClient = new Client.Builder()
			.name("Takashi")
			.enrollment(LocalDate.of(2017, 10, 1))
			.birthDate(LocalDate.of(1995, 1, 1))
			.vehicle(volkswagen)
			.build();
		
		Client fordClient = new Client.Builder()
			.name("Katakura")
			.enrollment(LocalDate.of(2017, 10, 1))
			.birthDate(LocalDate.of(1995, 1, 1))
			.vehicle(fordCar)
			.build();
		
		ClientRepository clientRepository = new ClientRepository();
		VehicleRepository vehicleRepository = new VehicleRepository();
		
		vehicleRepository.save(volkswagenCar);
		vehicleRepository.save(fordCar);
		
		clientRepository.save(volkswagenClient);
		clientRepository.save(fordClient);

		MarketingReport report = new MarketingReport.Builder()
			.vehicleRepository(vehicleRepository)
			.clientRepository(clientRepository)
			.monthOfEnrollment(10)
			.yearOfEnrollment(2017)
			.birthDate(LocalDate.of(1995, 1, 1))
			.manufacter(null)
			.model(ecosport)
			.build();
		
		List<Client> clients = new ArrayList<>();
		
		clients.add(volkswagenClient);
		clients.add(fordClient);
		
		assertEquals(report.generate(), clients);
		
		report.setManufacter(fordManufacter);
		clients.remove(volkswagenClient);
		
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
			.salary(new BigDecimal("5000"))
			.department(new Department("TI"))
			.office(new Office("desenvolvedor"))
			.pis(100.00)
			.workpermit("812732039")
			.cpf("119829374926")
			.sex(PeopleSex.MALE)
			.email("joao@test.org")
			.maritalStatus(MaritalStatus.MARRIED)
			.cep("82560300")
			.workload(8)
			.schooling(new Schooling("Superior Completo"))
			.openingDate(LocalDate.of(2017, 5, 05))
			.closeDate(LocalDate.of(2020, 5, 05))
			.build();
			
		EmployeeRepository employeeRepository = new EmployeeRepository();
		
		assertFalse(employeeRepository.save(employee));
		assertEquals(employee.getId(), 0);
		assertEquals(employeeRepository.find(employee), null);
		
		employee.setName("Jo�o da Silva");
		
		assertTrue(employeeRepository.save(employee));
		assertEquals(employeeRepository.find(employee.getId()), employee);
	}
	
	// Author: gabrielsbernardi
	@Test
	public void RF005ConsultarDadosCliente(String opcao) {
		if (opcao.equalsIgnoreCase("PESSOAL")) {
			People people = new Employee.Builder()
				.name(null)
				.birthDate(LocalDate.of(1991, 05, 10))
				.phone("999999999")
				.type(TypePeople.PHYSICAL)
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
				.color("red")
				.plate("ABC-1234")
				.renavam("123456789")
				.chassis("A1S54F1V5D4F5D4")
				.build();
			
			VehicleRepository repository = new VehicleRepository();
			repository.save(volkswagenCar);
			
			List<Vehicle> vehicles = new ArrayList<>();
			vehicles.add(volkswagenCar);

			assertEquals(report.generate(), vehicles);
			report.setBrand(volkswagen);
			assertEquals(report.generate(), vehicles);
		} else {
			Service service = new Service.Builder()
				.contractedService("asd")
				.name("Teste")
				.monthlyValue(52.5)
				.cover(100)
				.discounts(2.5)
				.realValue(75)
				.build();

			ServiceRepository serviceRepository = new ServiceRepository();
		
			assertFalse(serviceRepository.save(service));
			assertEquals(service.getId(), 0);
			assertEquals(serviceRepository.find(service), null);

			assertTrue(serviceRepository.save(service));
			assertEquals(serviceRepository.find(service.getId()), service);
		}
	}
	
	// Author: Orlando Krause Junior
	@Test
	public void RF006() {
		
	}
	
	// Author: evertonslv
	@Test
	public void RF007_ManterCadastroVeiculo() {
		Manufacter tesla = new Manufacter("tesla");
		VehicleBrand ford = new VehicleBrand("ford");
		
		VehicleModel ecosport = new VehicleModel("ecosport");
		
		Vehicle fordCar = new Vehicle.Builder()
			.type(TypeVehicle.CAR)
			.brand(ford)
			.manufacter(tesla)
			.color(Color.BLACK)
			.plate("ABC-1234")
			.cnh(null)
			.model(ecosport)
			.year(2010)
			.chassi("123456")
			.renavam("654321")
			.build();
		
		Client client = new Client.Builder()
			.name("Gabriel Takashi Katakura")
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
			.vehicle(fordCar)
			.build();
		
		VehicleRepository vehicleRepository = new VehicleRepository();
		vehicleRepository.add(fordCar);
		
		ClientRepository clientRepository = new ClientRepository();
		clientRepository.save(client);
		
		Client clientFindedByCnpj = clientRepository.findByCpf("82560300");
		
		assertEquals(clientFindedByCnpj, client);
		assertEquals(clientFindedByCnpj.getVehicle(), fordCar);
	}
	
	// Author: evertonslv
	@Test
	public void RF008() {
		
		VehicleModel model = new VehicleModel("model01");
		Status status = new Status("aguardando");
		
		Product product = new Product.Builder()
				.fabricatName("Teste")
				.model(model)
				.value(100.00)
				.serialNumber(123456)
				.status(status)
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
