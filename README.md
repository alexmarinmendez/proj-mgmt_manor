# Sistema de Gestión de Proyectos Tecnológicos - MANOR SAC

Aplicación para la gestión básica de proyectos tecnológicos de **MANOR SAC**, empresa de consultoría informática. El sistema permite registrar, consultar, actualizar y eliminar proyectos, además de obtener estadísticas del presupuesto acumulado por sector industrial.

---

## Descripción

El proyecto tiene como objetivo centralizar la información de los proyectos tecnológicos administrados por MANOR SAC y facilitar su seguimiento mediante operaciones CRUD y consultas estadísticas.

La aplicación gestiona información como:

- Código del proyecto.
- Nombre del proyecto.
- Cliente.
- Sector industrial.
- Presupuesto.
- Estado del proyecto.

---

## Funcionalidades principales

### Gestión de proyectos

- Registrar nuevos proyectos.
- Consultar todos los proyectos registrados.
- Consultar el detalle de un proyecto.
- Actualizar proyectos existentes.
- Eliminar proyectos mediante confirmación previa.
- Mantener el código del proyecto como dato inmutable después del registro.

### Validaciones y reglas de negocio

- El código del proyecto es obligatorio y único.
- El nombre del proyecto es obligatorio.
- El presupuesto debe ser mayor que cero.
- Si no se especifica un estado durante el registro, se asigna **Propuesto**.

### Sectores industriales

Los proyectos pueden clasificarse en los siguientes sectores:

- Financiero.
- Salud.
- Manufactura.
- Comercio minorista.
- Telecomunicaciones.

### Estados del proyecto

Los estados disponibles son:

- Propuesto.
- En progreso.
- Completado.
- Cancelado.

### Estadísticas

La aplicación permite seleccionar un sector industrial y obtener el presupuesto total acumulado de sus proyectos. Los resultados monetarios se presentan en euros con dos decimales.

---

## Persistencia de datos

La información de los proyectos se almacena de forma persistente en una base de datos relacional **MySQL**. Los registros recuperados se muestran desde el más reciente hasta el más antiguo.

---

## Requerimientos funcionales

| Código | Requerimiento |
|---|---|
| RF-01 | Registrar un nuevo proyecto |
| RF-02 | Consultar proyectos registrados |
| RF-03 | Consultar el detalle de un proyecto |
| RF-04 | Actualizar un proyecto |
| RF-05 | Eliminar un proyecto |
| RF-06 | Validar el código del proyecto |
| RF-07 | Validar el nombre del proyecto |
| RF-08 | Validar el presupuesto |
| RF-09 | Validar códigos duplicados |
| RF-10 | Asignar un estado predeterminado |
| RF-11 | Clasificar proyectos por sector industrial |
| RF-12 | Gestionar estados de los proyectos |
| RF-13 | Consultar el presupuesto total por sector |
| RF-14 | Mostrar resultados estadísticos |
| RF-15 | Almacenar información de los proyectos |
| RF-16 | Recuperar información almacenada |

[Para el detalle de reglas, criterios de aceptación y trazabilidad, consultar la especificación de requerimientos funcionales del proyecto.](ERF_Sistema_Gestion_Proyectos_MANOR_SAC.md)

---

## Tecnologías

| Tecnología | Propósito / Uso |
|---|---|
| Java 25 | Lenguaje de programación |
| Gradle 9.8.2 | Build automation Tool y Gestor de dependencias |
| Java Swing | Para la interfaz de usuario |
| MySQL | Base de datos relacional |
| JDBC | Para la conexión con la base de datos |
| IntelliJ IDEA | IDE de desarrollo |

---

## Uso esperado

Un flujo básico de utilización de la aplicación es el siguiente:

1. Registrar un proyecto indicando sus datos generales y presupuesto.
2. Consultar la lista de proyectos existentes.
3. Seleccionar un proyecto para revisar su información.
4. Actualizar sus datos cuando sea necesario.
5. Eliminar un proyecto, previa confirmación del usuario.
6. Seleccionar un sector industrial para consultar su presupuesto acumulado.

---

## Reglas importantes

- El código de proyecto no puede modificarse después de su creación.
- No pueden existir dos proyectos con el mismo código.
- No se admiten presupuestos iguales o menores que cero.
- Los importes se gestionan en euros.
- La eliminación requiere confirmación previa.
- La información debe mantenerse disponible después de reiniciar la aplicación.

---

## Estructura del repositorio

```text
src/
└── main/
   ├── java/
   │   └── com/softer/
   │       ├── config/       # Database configuration
   │       ├── model/        # Domain entities and enums
   │       ├── repository/   # Data access layer
   │       ├── service/      # Business logic
   │       ├── ui/           # Swing user interface
   │       └── MainApplication.java
   │
   └── resources/
       └── application.properties
```
---

## Documentación

La documentación funcional del proyecto incluye la especificación formal de requerimientos, donde se detallan:

- Requerimientos funcionales RF-01 a RF-16.
- Reglas de negocio.
- Criterios de aceptación.
- Matriz de trazabilidad.
- Supuestos y dependencias.
- Decisiones pendientes.

[Ver documento Especificación de Requerimientos Funcionales (ERF)](ERF_Sistema_Gestion_Proyectos_MANOR_SAC.md)

---

## Contribución

Antes de realizar cambios en el proyecto:

1. Crear una rama de trabajo a partir de la rama principal definida por el equipo.
2. Implementar cambios acotados y coherentes con los requerimientos.
3. Incorporar o actualizar las pruebas correspondientes.
4. Utilizar mensajes de commit claros y descriptivos.
5. Crear una solicitud de integración para revisión antes de incorporar cambios a la rama principal.

---

## ⚙️ Para empezar

### Pre-requisitos

Asegúrate tener instalado:

- JDK 25 o superior.
- MySQL Server 8.0 o superior.
- IntelliJ IDEA (recomendado).

### 1. Clonar el repositorio

```bash
git clone https://github.com/alexmarinmendez/proj-mgmt_manor.git
```

### 2. Sobre la base de datos

Ejecuta el script ubicado en la raiz del repositorio:

```text
schema.sql
```

El script crea la base de datos `projects_db`, la tabla `projects`, y también inserta algunos registros de ejemplo.

### 3. Configurar la conexión a la base de datos

Abrir el archivo:

```text
src/main/resources/application.properties
```

Actualiza la configuración de acuerdo a tu servidor de MySQL. Por ejemplo:

```properties
db.url=jdbc:mysql://localhost:3306/projects_db?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
db.username=root
db.password=your_password
```
---

## 📄 Licencia

Este proyecto está bajo la [licencia MIT](LICENSE.txt).