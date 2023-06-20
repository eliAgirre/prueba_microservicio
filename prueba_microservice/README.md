# Prueba multi módulo

## Arquitectura hexagonal

La arquitectura hexagonal tiene las siguientes capas:
- **infraestructure**: es la capa que se conecta con el exterior como la base de datos o los controladores.
- **application**: es la capa intermediaria que se conecta con la infraestructura.
- **domain**: es el núcleo, el core o la capa del negocio.

## Estructura y/o módulos

Este proyecto tiene la siguiente estructura:
- **client**: es la capa cliente por ejemplo para usar las tablas, payments o shopping externos (en este caso no es necesario).
- **authentication**: conecta todos los microservicios rest mediante un token, así es más seguro la comunicación (no es el caso, pero sería recomendable, se refuerza la seguridad).
- **database**: se usan los repositorios jpa para realizar consultas a la base de datos en memoria h2.
- **domain**: es la capa que se encarga de solucionar la lógica de negocio.
- **entity**: son todas las entidades de la lógica del dominio.
- **web**: es el starter, el servicio web y tiene la configuración de la base de datos h2 (application.properties).

En este proyecto se ha dispersado las capas que tiene teóricamente la arquitectura hexagonal de forma modularizada, así la lógica del negocio está desacoplada con el exterior. Es decir, si se realiza algún cambio en el exterior no tiene que afectar en la lógica del negocio.

En la **capa de infraestructura** estarían los módulos `database`, `authentication` y `client`.

En la **capa de aplicación** estaría el módulo `web`.

En la **capa del dominio** entran los módulos `domain` y `entity`.

Se ha usado mockito para realizar los tests unitarios desde un fichero json.

Los ficheros json están en el módulo `web` en la carpeta `resources` y después en el paquete `json`.

  ### Relaciones entre los módulos

  - **entity**: no tiene dependencia de ningún módulo, ya que son las entidades de la lógica del dominio.
  - **domain**:
    - Se relaciona con el módulo `entity`, ya que necesita las entidades de la lógica del dominio para el negocio.
    - Depende del módulo `database` para que se puedan insertar y obtener los datos de una base de datos en memoria, que es la ***capa de infraestructura*** de la arquitectura hexagonal.
  - **database**: tiene la relación con el módulo `entity` para manejar los datos.
  - **web**: tiene dependencia con el módulo `domain` para el manejo del negocio y dar respuestas a las peticiones.

## Módulos
  ### Entity
  
  Son las entidades de la lógica del dominio como `Proveedor`.
  
  La entidad `Proveedor` contiene las anotaciones `@Entity` y`@Id`  para la persistencia de datos en JPA. La anotación `@Entity` sirve para que la aplicación sepa que es una entidad y que debe crear la tabla según las propiedades que tenga la clase. La anotación `@Id` sirve para definir el id de la clase.
  
  ```java
@Entity
public class Proveedor implements Serializable {

    private static final long serialVersionUID = -3513241031219979141L;

    @Id
    @Column(name="ID_PROVEEDOR")
    private int idProveedor;

    @Column(name="NOMBRE")
    private String nombre;

    @Column(name="FECHA_ALTA")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime fechaAlta;

    @Column(name="ID_CLIENTE")
    private int idCliente;
}
  ```
  Aparte de las anotaciones de JPA la clase `Proveedor` tiene las anotaciones de Lombok reducir el código repetitivo como `@Getter`, `@ToString`, `@RequiredArgsConstructor` y `@AllArgsConstructor`.
  
  ### Database
  
  Se usa los repositorios JPA para realizar consultas a la base de datos en memoria h2. Se han creado los repositorios necesarios por cada entidad. En el caso del interfaz `ProveedorRepository` se extiende el repositorio JPA para utilizar la entidad `Proveedor`:
  
  ```java
    @Repository
    @Transactional
    public interface ProveedorRepository extends JpaRepository<Proveedor, Integer> {
        List<Proveedor> findByIdCliente(Integer id_cliente);
    }
  ```
  
  La anotación transaccional proporciona a la aplicación la capacidad de controlar declarativamente las transacciones en beans o entidades, así como las clases definidas.
  
  Como se figura en el código se está realizando una consulta a la base de datos con una fecha de inicio, fecha de finalización, marca id y producto id. Esta consulta devuelve una lista de precios según los parámetros que vengan, siempre y cuando coincida que sea mayor o igual que la fecha de inicio, menor o igual que la fecha de finalización, y que coincidan tanto el id de marca como la del producto.
  
  ### Domain
  
  Se encarga de solucionar la lógica de negocio con los servicios necesarios por cada entidad. El módulo `domain` contiene los paquetes `services`, `exception` y `utils`.
  
  En el paquete `services` se han creado los servicios necesarios por cada entidad. La clase `ProveedorService` tiene una anotación `@Service` y `@Slf4j`. La primera es para que Spring Boot sepa que es un servicio y la segunda sirve para ver los registros de los logs.
  
  Esta clase tiene una inyección de dependencias en el constructor del repositorio:
  
  ```java
  
  @Slf4j
  @Service
  public class ProveedorService {

      private ProveedorRepository proveedorRepository;
      
      public ProveedorService(ProveedorRepository proveedorRepository){
          this.proveedorRepository = proveedorRepository;
      }
  }
  ```
  
  La clase `ProveedorService` contiene la anotación `@PostConstuct` para inicializar la tarea de guardar el proveedor mediante el repositorio inyectado en el constructor:
  
  ```java
    @PostConstruct
    public void initProveedor(){

        proveedorRepository.saveAll(Stream.of(new Proveedor(1, DomainConstants.LISTA_REFRESCOS.get(0),
        Utility.getLocalDateTimeNow(), 5),

        new Proveedor(2, DomainConstants.LISTA_REFRESCOS.get(1),
        Utility.getLocalDateTimeNow(), 5),

        new Proveedor(3, DomainConstants.LISTA_REFRESCOS.get(2),
        Utility.getLocalDateTimeNow(), 6),

        new Proveedor(4, DomainConstants.LISTA_REFRESCOS.get(3),
        Utility.getLocalDateTimeNow(), 7),

        new Proveedor(5, DomainConstants.LISTA_REFRESCOS.get(4),
        Utility.getLocalDateTimeNow(), 8),

        new Proveedor(6, DomainConstants.LISTA_REFRESCOS.get(5),
        Utility.getLocalDateTimeNow(), 6)
        )
        .collect(Collectors.toList()));
    }
  ```
  
  Mediante esta clase se podrá consultar la lista de los proveedores según el id del cliente:
  
  ```java
    public List<Proveedor> getProveedoresListByIdCliente(int id_cliente) throws ServiceException {

        log.info(DomainConstants.LOG_SERVICE, id_cliente);

        if (Objects.isNull(id_cliente)) {
        throw new ServiceException.Builder(String.valueOf(ServiceErrorCatalog.ID_CLIENTE_IS_NOT_CORRRECT))
        .withHttpStatus(HttpStatus.BAD_REQUEST).build();
        }

        return proveedorRepository.findByIdCliente(id_cliente);
    }
  ```
  
  ### Web
  
  Este módulo contiene el arranque de la aplicación, los controladores y la configuración de la base de datos desde el fichero `application.properties`. En el `pom.xml` es donde figura cuál es la clase principal:

  ```xml

<properties>
    <start-class>com.ibm.web.MainApplication</start-class>
</properties>
  ```
  
  La configuración de la base de datos de H2 está dentro de la carpeta `resources` en el fichero de `application.properties`:
  
  ```properties
  spring.h2.console.enabled=true
  spring.datasource.url=jdbc:h2:mem:test
  spring.datasource.generate-unique-name=false
  spring.datasource.driverClassName=org.h2.Driver
  spring.datasource.username=sa
  spring.datasource.password=password
  spring.jpa.database-platform=org.hibernate.dialect.H2Dialect

  # Resuelve el nombre de las columnas y de las tablas, no afecta a las relaciones
  spring.jpa.hibernate.naming.physical-strategy=org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl
  DB_CLOSE_ON_EXIT=FALSE

  spring.jpa.defer-datasource-initialization = true
  # muestra los logs de sql
  spring.jpa.show-sql = true


  logging.level.web=DEBUG
  server.port=8082
  ```
  
  Dentro del módulo también está el controlador llamado `PricesController` con la anotación `@RestController`, `@RequestMapping`, `@Slf4j` y `@Validated`. Este controlador simplemente tiene inyectado el servicio de los precios en el constructor, y tiene un único endpoint para que se consulten los datos de los precios según los parámetros obtenidos de la petición:
  
  ```java
    @Slf4j
    @RestController
    @RequestMapping(value="/api/proveedores")
    @Validated
    public class ProveedorController {
        private ProveedorService proveedorService;
        public ProveedorController(ProveedorService proveedorService){
            this.proveedorService = proveedorService;
        }
        @GetMapping(WebConstants.PATH_FILTER)
        public ResponseEntity<List<Proveedor>> getProveedoresListByIdCliente(@NotBlank @RequestParam(value = "id_cliente") int id_cliente) throws ServiceException {
    
            log.info(WebConstants.LOG_CONTROLLER, id_cliente);
    
            if(Objects.isNull(id_cliente)){
                return ResponseEntity.badRequest().build();
            }
    
            List<Proveedor> pricesList;
    
            try {
                pricesList = proveedorService.getProveedoresListByIdCliente(id_cliente);
            }
            catch (ServiceException e) {
                throw new ServiceException(e.getCode(), e.getHttpStatus(), e.getMessage(), e.getCause(), e.getParams());
            }
    
            return ResponseEntity.ok(pricesList);
        }
    }
  ```
  
  Según se figura en el código del controlador el endpoint puede lanzar una excepción de servicio o puede devolver una lista de los proveedores, tanto vacía como rellena.
  
## Tests con Mockito

Se han creado los tests para las clases `ProveedorService` y `ProveedorController`. Se ha usado para realizar los tests `junit.jupiter` y `mockito`.

### Test en el servicio

En el caso de `ProveedorServiceTest` se ha creado un método llamado `setUp` para mockear las clases que se instancian en el servicio como `ProveedorRepository`:

```java
    @BeforeEach
    public void setUp() {

        mockedProveedorRepository = mock(ProveedorRepository.class);
        mockedProveedorService = new ProveedorService(mockedProveedorRepository);
    }
```

### Test en el controlador

En el caso del controlador se han utilizado los ficheros json cargados por cada caso:

```java
    class ProveedorControllerTest extends JsonToObjectsCreator {

        @Mock
        private ProveedorService mockedProveedorService;
        private ProveedorController proveedorController;
    
        @BeforeEach
        public void setUp() {
            openMocks(this);
            proveedorController = new ProveedorController(mockedProveedorService);
        }
    }
```

En uno de los casos unitarios, se obtiene la petición y la respuesta de los ficheros json. Se comprueba si la respuesta no viene nula, si el cuerpo de la respuesta no viene nula, si devuelve el tamaño correspondiente de la lista y si los datos por cada lista las devuelve como es debido:

```java
    @Test
    void GivenIdCliente5_WhenGetProveedoresListByIdCliente_ThenReturnsProveedorList() throws IOException {
            // Given
            int id_cliente = 5;
            List<Proveedor> proveedorList = proveedoresListIdCliente5();

        // When
        when(mockedProveedorService.getProveedoresListByIdCliente(id_cliente)).thenReturn(proveedorList);

        ResponseEntity<List<Proveedor>> responseEntity = proveedorController.getProveedoresListByIdCliente(id_cliente);

        responseEntity.getBody().stream()
        .map( proveedor -> proveedor.toString() )
        .forEach(System.out::println);
        System.out.println();
    }

    @Test
    void GivenIdCliente9_WhenGetProveedoresListByIdCliente_ThenReturnsProveedorListEmpty() throws IOException {
            // Given
            int id_cliente = 9;
            List<Proveedor> proveedorList = proveedoresListIdCliente9();

        // When
        when(mockedProveedorService.getProveedoresListByIdCliente(id_cliente)).thenReturn(proveedorList);

        ResponseEntity<List<Proveedor>> responseEntity = proveedorController.getProveedoresListByIdCliente(id_cliente);

        responseEntity.getBody().stream()
        .map( proveedor -> proveedor.toString() )
        .forEach(System.out::println);
        System.out.println();
        }
```

## H2 Base de datos

Se ha utilizado la base de datos H2 en memoria, ya que, se ha instalado un programa Dbeaver para usar MySQL. Se instalarón los drivers de MySQL, se crea la base de datos, pero después hubo un problema entre el driver de MySQL y el microservicio que se conectaban entre sí.

Por ello, he optado por utilizar la base de datos en memoria H2.

## Lista de dependencias

| Dependencia              |                                    Descripción                                             |
|--------------------------|:------------------------------------------------------------------------------------------:|
| Spring Boot Starter Web  |                             Arranque de la aplicación                                      |
| Spring Boot Starter JPA  | Persiste datos en almacenes SQL con Java Persistence API mediante Spring Data e Hibernate. |
| Spring Boot DevTools     |                   Herramienta de desarrollo como ver los logs.                             |
| Spring Boot Starter Test |                          Se realizan los test unitarios.                                   |
| H2                       |                             Base de datos en memoria.                                      |
| Lombok                   |      Biblioteca de anotaciones Java que ayuda a reducir el código repetitivo.              |
| Jackson Datatype Joda    |  Instancia de ObjectMapper para serializar respuestas y deserializar solicitudes.          |
| Jackson Datatype Jsr310  |          Ofrece una forma sencilla e intuitiva de crear un ObjectMapper.                   |
| Jackson Databind         | Biblioteca de utilidades para ayudar con el desarrollo de la funcionalidad de E/S.         |
| Common io                |  Convierte JSON a objetos y admite una fácil conversión a Map desde datos JSON.            |





