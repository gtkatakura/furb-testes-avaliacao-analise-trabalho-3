package part3;

import static org.junit.Assert.*;

import java.awt.Color;
import java.time.LocalDate;
import java.util.List;

import org.junit.Test;

public class Exercise1Test {

	
	/*
	RF001: Manter Clientes:

	Dado O  administrador  deverá  inserir  os  seguintes dados  obrigatórios:  
	Nome,  Endereço,  CEP,  Data  de  nascimento,  Data  de abertura, RG, CPF ou CNPJ, 
	Telefone, E-mail para contato, CNH, Tipo de pessoa (física ou jurídica); 
	e os campos não obrigatórios: Nome do pai e Nome da mãe)
	Quando Inserir um cliente
	Então O sistema deverá cadastrar um novo cliente.
	*/	
	
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
			.motherName("PadmÃ© Naberrie")
			.build();
		
		ClientRepository repository = new ClientRepository();
		
		assertFalse(repository.save(client));
		assertEquals(client.getId(), 0);
		assertEquals(repository.find(client.getId()), null);
		
		client.setName("Gabriel Takashi Katakura");
		
		assertTrue(repository.save(client));
		assertEquals(repository.find(client.getId()), client);
	}

	
	/*
	RF002: Emissão de relatórios de veículos:

	Dado Marca, Modelo, Tipo de veículo e Ano
	Quando Emitir um relatório
	Então O sistema deverá apresentar o relatório gerado, com os campos nome, e-mail, telefone e plano.
	*/
	
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
	
	
	/*
	RF003: Emissão de relatórios de Markenting:

	Dado os seguintes campos opcionais como a data de nascimento, 
	endereço, modelo do veículo, ano, fabricante e tipo de modelo
	Quando emitido o relatório
	Então é exibido os dados informados como o nome, data de nascimento, 
	endereço, modelo do veículo, ano, fabricante, e tipo de veículo
	*/	
	
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
	
	
	/*
	RF004 Manter Cadastro de Funcionário:

	Dado um funcionário com os dados completos (nome, data de nascimento, telefone, 
	endereço, salário, departamento, função, pis, carteira de trabalho, CPF, sexo, e-mail, 
	estado civil, carga horário, escolaridade, data de admissão e data de desligamento)
	Quando o sistema tentar cadastrar esse funcionário Então deverá efetuar o cadastro com sucesso
	Quando o sistema tentar alterar esse funcionário Então deverá efetuar a alteração com sucesso
	Quando o sistema tentar inativar este funcionário Então deverá efetuar a inativação com sucesso
	Quando o sistema tentar pesquisar por este funcionário Então o funcionário deverá ser encontrado
	(deve-se apresentar o nome, data de nascimento, telefone, endereço, salário, departamento, função, 
	pis, carteira de trabalho, CPF, sexo, e-mail, estado civil, carga horário, escolaridade, data de admissão 
	e data de desligamento)
	*/
	
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
		
		employee.setName("João da Silva");
		
		assertTrue(employeeRepository.save(employee));
		assertEquals(employeeRepository.find(employee.getId()), employee);
	}
	
	
	/*
	RF005: Página de acesso para o cliente consultar seus dados:
	
	Dado as opções “Pessoal”, “Veículo” e “Serviços”
	Quando selecionado a opção "Pessoal" Então  os  seguintes dados serão exibidos na tela: 
	Nome, Data de Nascimento, Telefone, TIpo de Pessoa(Fisica  ou  Juridica),  CNH,  Endereço,
	Data  de  Abertura,  E-mail, CPF/CNPJ
	Quando selecionado a opção "Veiculo" Então os seguintes campos deverão aparecer:
	Fabricante, Modelo do Veículo, Tipo de Veículo, Cor do Veiculo, Placa  do  Veículo,
	RENAVAM,  Chassi  e  Ano  do  Veículo
	Quando selecionado a opção "Serviços” Então o sistema exibirá na tela os campos Ata da Contratação,
	Nome do Plano, Valor Mensal, Cobertura, Desconto, Valor Real.	
	*/
	
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

			people.setName("JoÃ£o da Silva");

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
	
	
	/*
	RF006: Manter cadastro de serviço:

	Dado o CPF  para  localizar  o  cliente. Deverão ser preenchidos os seguintes campos:
	Nome do Serviço, Tipo do Plano, Data da Contratação, Numero do Contrato, Desconto e Valor Real
	Quando o cliente for  incluir um serviço
	Então o sistema exibirá na tela os seguintes campos: Nome do Cliente, CPF do Cliente, 
	Nome do Serviço, Tipo do Plano, Data da Contratação, Numero do Contrato, Desconto e Valor Real.
	*/
	
	// Author: Orlando Krause Junior
	@Test
	public void RF006() {
		
	}
	
	
	/*
	RF007: Manter cadastro de veículo

	Dado o CPF  para  localizar  o  cliente. Deverão ser preenchidos os seguintes campos: 
	Tipo do Veiculo, Fabricante, Cor, Placa, CNH, Modelo, Ano, Chassi e RENAVAM
	Quando o administrador for  incluir um veículo
	Então o sistema exibirá uma tela com os seguintes campos: nome do cliente, CPF, 
	Tipo do Veiculo, Fabricante, Cor, Placa, CNH, Modelo, Ano, Chassi e RENAVAM.
	*/
	
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
			.motherName("PadmÃ© Naberrie")
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
	
	
	/*
	RF008: Manter Produto

	Dado o nome do fabricante, o modelo, o valor o número de série, status e chassis
	Quando o gerente selecionar um equipamento e alterar alguma informação da mesma
	Então o sistema deverá inserir o equipamento com sucesso
	*/
	
	// Author: evertonslv
	@Test
	public void RF008() {
		ProductModel tracking = new ProductModel("rastreamento");
		Status status = new Status("aguardando");
		
		Product product = new Product.Builder()
			.manufacterName("Nome do Fabricante")
			.model(tracking)
			.value(new BigDecimal("100"))
			.serialNumber("123456")
			.status(status)
			.chassi("654321");
		
		ProductRepository productRepository = new ProductRepository();
		
		assertTrue(productRepository.save(product));
		assertEquals(productRepository.find(product.getId()), product);
	}
	
	
	/*
	RF009: Log do Sistema

	Dado uma alteração no sistema
	Quando a execução for finalizada
	Então será gerado um arquivo txt, com a seguinte formatação: [Data][Hora][Usuário][Ação realizada no sistema]
	*/
	
	// Author: 
	@Test
	public void RF009() {
		
	}
}
