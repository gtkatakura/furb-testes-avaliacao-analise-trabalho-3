package part3;

import static org.junit.Assert.*;

import java.awt.Color;
import java.security.Provider.Service;
import java.time.LocalDate;
import java.util.List;

import org.junit.Test;

public class Exercise1Test {


	/*
	RF001: Manter Clientes:

	Dado O  administrador  dever�  inserir  os  seguintes dados  obrigat�rios:  
	Nome,  Endere�o,  CEP,  Data  de  nascimento,  Data  de abertura, RG, CPF ou CNPJ, 
	Telefone, E-mail para contato, CNH, Tipo de pessoa (f�sica ou jur�dica); 
	e os campos n�o obrigat�rios: Nome do pai e Nome da m�e)
	Quando Inserir um cliente
	Ent�o O sistema dever� cadastrar um novo cliente.
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


	/*
	RF002: Emiss�o de relat�rios de ve�culos:

	Dado Marca, Modelo, Tipo de ve�culo e Ano
	Quando Emitir um relat�rio
	Ent�o O sistema dever� apresentar o relat�rio gerado, com os campos nome, e-mail, telefone e plano.
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
	RF003: Emiss�o de relat�rios de Markenting:

	Dado os seguintes campos opcionais como a data de nascimento, 
	endere�o, modelo do ve�culo, ano, fabricante e tipo de modelo
	Quando emitido o relat�rio
	Ent�o � exibido os dados informados como o nome, data de nascimento, 
	endere�o, modelo do ve�culo, ano, fabricante, e tipo de ve�culo
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
	RF004 Manter Cadastro de Funcion�rio:

	Dado um funcion�rio com os dados completos (nome, data de nascimento, telefone, 
	endere�o, sal�rio, departamento, fun��o, pis, carteira de trabalho, CPF, sexo, e-mail, 
	estado civil, carga hor�rio, escolaridade, data de admiss�o e data de desligamento)
	Quando o sistema tentar cadastrar esse funcion�rio Ent�o dever� efetuar o cadastro com sucesso
	Quando o sistema tentar alterar esse funcion�rio Ent�o dever� efetuar a altera��o com sucesso
	Quando o sistema tentar inativar este funcion�rio Ent�o dever� efetuar a inativa��o com sucesso
	Quando o sistema tentar pesquisar por este funcion�rio Ent�o o funcion�rio dever� ser encontrado
	(deve-se apresentar o nome, data de nascimento, telefone, endere�o, sal�rio, departamento, fun��o, 
	pis, carteira de trabalho, CPF, sexo, e-mail, estado civil, carga hor�rio, escolaridade, data de admiss�o 
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

		employee.setName("Jo�o da Silva");

		assertTrue(employeeRepository.save(employee));
		assertEquals(employeeRepository.find(employee.getId()), employee);
	}


	/*
	RF005: P�gina de acesso para o cliente consultar seus dados:

	Dado as op��es �Pessoal�, �Ve�culo� e �Servi�os�
	Quando selecionado a op��o "Pessoal" Ent�o  os  seguintes dados ser�o exibidos na tela: 
	Nome, Data de Nascimento, Telefone, TIpo de Pessoa(Fisica  ou  Juridica),  CNH,  Endere�o,
	Data  de  Abertura,  E-mail, CPF/CNPJ
	Quando selecionado a op��o "Veiculo" Ent�o os seguintes campos dever�o aparecer:
	Fabricante, Modelo do Ve�culo, Tipo de Ve�culo, Cor do Veiculo, Placa  do  Ve�culo,
	RENAVAM,  Chassi  e  Ano  do  Ve�culo
	Quando selecionado a op��o "Servi�os� Ent�o o sistema exibir� na tela os campos Ata da Contrata��o,
	Nome do Plano, Valor Mensal, Cobertura, Desconto, Valor Real.	
	 */

	// Author: gabrielsbernardi
	@Test
	public void RF005ConsultarDadosCliente() {
		VehicleBrand volkswagen = new VehicleBrand("volkswagen");
		VehicleModel ecosport = new VehicleModel("ecosport");

		Vehicle car = new Vehicle.Builder()
				.brand(volkswagen)
				.model(ecosport)
				.type(TypeVehicle.CAR)
				.year(2010)
				.build();

		VehicleRepository vehicleRepository = new VehicleRepository();
		vehicleRepository.save(car);

		Service service = new Service.Builder()
				.name("Servi�o de Teste")
				.planType("teste")
				.dateHiring(LocalDate.of(2017, 07, 25))
				.contactNumber("19273919")
				.discount(new BigDecimal("2.8"))
				.realValue(new BigDecimal("100"))
				.build();

		ServiceRepository serviceRepository = new ServiceRepository();
		serviceRepository.save(service);

		List<Service> services = new ArrayList<>();
		services.add(service);

		Employee employee = new Employee.Builder()
				.name("Gabriel Takashi Katakura")
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
				.vehicle(car)
				.services(services)
				.build();

		EmployeeRepository employeeRepository = new EmployeeRepository();
		employeeRepository.save(employee);

		PersonalPage personalPage = new PersonalPage(employee);

		assertEquals(personalPage.optionPersonal(), employee);
		assertEquals(personalPage.optionVehicle(), car);
		assertEquals(personalPage.optionServices(), services);
	}


	/*
	RF006: Manter cadastro de servi�o:

	Dado o CPF  para  localizar  o  cliente. Dever�o ser preenchidos os seguintes campos:
	Nome do Servi�o, Tipo do Plano, Data da Contrata��o, Numero do Contrato, Desconto e Valor Real
	Quando o cliente for  incluir um servi�o
	Ent�o o sistema exibir� na tela os seguintes campos: Nome do Cliente, CPF do Cliente, 
	Nome do Servi�o, Tipo do Plano, Data da Contrata��o, Numero do Contrato, Desconto e Valor Real.
	 */

	// Author: Matheus Waltrich da Silva
	@Test
	public void RF006manterServico() {
		Service service = new Service.Builder()
				.name(null)
				.planType("teste")
				.dateHiring(LocalDate.of(2017, 07, 25))
				.contactNumber("19273919")
				.discount(new BigDecimal("2.8"))
				.realValue(new BigDecimal("100"))
				.build();

		ServiceRepository serviceRepository = new ServiceRepository();

		assertFalse(serviceRepository.save(service));
		assertEquals(service.getId(), 0);
		assertEquals(serviceRepository.find(service.getId()), null);

		service.setName("Servi�o de teste");

		assertTrue(serviceRepository.save(service));
		assertEquals(serviceRepository.find(service.getId()), service);
	}


	/*
	RF007: Manter cadastro de ve�culo

	Dado o CPF  para  localizar  o  cliente. Dever�o ser preenchidos os seguintes campos: 
	Tipo do Veiculo, Fabricante, Cor, Placa, CNH, Modelo, Ano, Chassi e RENAVAM
	Quando o administrador for  incluir um ve�culo
	Ent�o o sistema exibir� uma tela com os seguintes campos: nome do cliente, CPF, 
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


	/*
	RF008: Manter Produto

	Dado o nome do fabricante, o modelo, o valor o n�mero de s�rie, status e chassis
	Quando o gerente selecionar um equipamento e alterar alguma informa��o da mesma
	Ent�o o sistema dever� inserir o equipamento com sucesso
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

	Dado uma altera��o no sistema
	Quando a execu��o for finalizada
	Ent�o ser� gerado um arquivo txt, com a seguinte formata��o: [Data][Hora][Usu�rio][A��o realizada no sistema]
	 */

	// Author: 
	@Test
	public void RF009() {
		// Adicionar Veículo
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

		VehicleRepository vehicleRepository = new VehicleRepository();
		vehicleRepository.save(fordCar);

		String actualDate = LocalDateTime.now().toString();
		String actualUser = System.getProperty("user.name");
		assertTrue(Logger.getLogger().getLoggedLines().contains(actualDate + " " + actualUser + " Adicionado veiculo " + fordCar.toString()));


		// Adicionar Cliente
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
				.motherName("PadmÃƒÂ© Naberrie")
				.build();

		ClientRepository repository = new ClientRepository();
		repository.save(client);

		String actualDate = LocalDateTime.now().toString();
		String actualUser = System.getProperty("user.name");
		assertTrue(Logger.getLogger().getLoggedLines().contains(actualDate + " " + actualUser + " Adicionado Cliente " + client.toStrin()));


		// Adiciona Product
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
		productRepository.save(product);

		String actualDate = LocalDateTime.now().toString();
		String actualUser = System.getProperty("user.name");
		assertTrue(Logger.getLogger().getLoggedLines().contains(actualDate + " " + actualUser + " Adicionado Cliente " + product.toStrin()));

	}
}
