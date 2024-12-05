# Sistema_caja_ahorros
**Universidad Politecnica Salesiana**

Desarrollo de Sistema para una caja de ahorros

## Descripción del Proyecto

Este proyecto es un sistema para gestionar una caja de ahorros, permitiendo la administración de cuentas, usuarios, transacciones y socios.

## Requisitos
> [!NOTE]
> Versiones:
> 
> - JDK: 23
> - Java: 17
> - Maven: 3.9.9
> - PostgreSQL: 42.7.3

## Configura la base de datos en `src/main/resources/application.properties`:
spring.datasource.url=jdbc:postgresql://localhost:5432/caja_ahorros
spring.datasource.username=TU_USUARIO
spring.datasource.password=TU_CONTRASEÑA

## Compila y ejecuta el proyecto:

./mvnw spring-boot:run

## Uso

### Endpoints

- **Usuarios**
  - `GET /usuarios`: Obtener todos los usuarios.
  - `GET /usuarios/{id}`: Buscar usuario por ID.
  - `POST /usuarios/{idSocio}`: Registrar un nuevo usuario.
  - `PUT /usuarios/{idSocio}`: Actualizar un usuario.
  - `DELETE /usuarios/{id}`: Eliminar un usuario.

- **Cuentas**
  - `GET /cuentas`: Obtener todas las cuentas.
  - `GET /cuentas/{id}`: Buscar cuenta por ID.
  - `POST /cuentas/{idSocio}`: Registrar una nueva cuenta.
  - `PUT /cuentas/{idSocio}`: Actualizar una cuenta.
  - `DELETE /cuentas/{id}`: Eliminar una cuenta.

- **Transacciones**
  - `GET /transacciones`: Obtener todas las transacciones.
  - `GET /transacciones/{id}`: Buscar transacción por ID.
  - `POST /transacciones/{idCuentaOrigen}/{idCuentaDestino}`: Registrar una nueva transacción.

- **Socios**
  - `GET /socios`: Obtener todos los socios.
  - `GET /socios/{id}`: Buscar socio por ID.
  - `POST /socios`: Registrar un nuevo socio.
  - `PUT /socios`: Actualizar un socio.
  - `DELETE /socios/{id}`: Eliminar un socio.


# Funciones

## Henry
  Descripcion:
  
## Franklin
  Descripcion:
## Edwin
  Descripcion:
## Andres
  Descripcion:
## Wilmer
  Descripcion:
