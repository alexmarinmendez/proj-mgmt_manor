# Especificación de Requerimientos Funcionales

## Sistema de Gestión de Proyectos Tecnológicos de MANOR SAC

**Código del documento:** ERF-SGPT-001  
**Versión:** 1.0  
**Estado:** Propuesta para revisión  
**Organización:** MANOR SAC  
**Tipo de documento:** Especificación de Requerimientos Funcionales  

---

## 1. Control del documento

| Campo | Detalle |
|---|---|
| Nombre del sistema | Sistema de Gestión de Proyectos Tecnológicos |
| Sigla | SGPT |
| Propietario del producto | MANOR SAC |
| Versión | 1.0 |
| Estado | Propuesta para revisión |
| Audiencia | Patrocinadores, usuarios clave, analistas, desarrolladores, personal de calidad y responsables de soporte |

### 1.1 Historial de versiones

| Versión | Descripción del cambio | Responsable | Fecha |
|---|---|---|---|
| 1.0 | Formalización del borrador inicial de requerimientos funcionales | Por definir | Por definir |

### 1.2 Aprobaciones

| Rol | Nombre | Estado | Fecha |
|---|---|---|---|
| Patrocinador del proyecto | Por definir | Pendiente | Por definir |
| Propietario del producto | Por definir | Pendiente | Por definir |
| Responsable de tecnología | Por definir | Pendiente | Por definir |
| Responsable de calidad | Por definir | Pendiente | Por definir |

---

## 2. Propósito

El presente documento especifica los requerimientos funcionales del Sistema de Gestión de Proyectos Tecnológicos de MANOR SAC. El sistema permitirá registrar, consultar, actualizar y eliminar proyectos, así como obtener estadísticas del presupuesto acumulado por sector industrial.

La especificación establece funcionalidades verificables y criterios de aceptación que servirán como base para el análisis, diseño, desarrollo, pruebas y aceptación del sistema.

## 3. Alcance

El sistema comprenderá las siguientes capacidades:

- Registrar proyectos tecnológicos.
- Consultar la lista y el detalle de los proyectos registrados.
- Actualizar la información de un proyecto sin modificar su código.
- Eliminar proyectos mediante confirmación previa.
- Validar campos obligatorios, presupuestos y códigos duplicados.
- Clasificar proyectos por sector industrial.
- Gestionar el estado de los proyectos.
- Calcular y mostrar el presupuesto total por sector industrial.
- Almacenar y recuperar información mediante una base de datos relacional MySQL.

### 3.1 Fuera del alcance

No se consideran dentro de esta versión, salvo aprobación posterior mediante control de cambios:

- Gestión de usuarios, roles y permisos.
- Autenticación y autorización.
- Gestión de tareas, recursos, cronogramas, hitos o riesgos.
- Gestión documental o almacenamiento de archivos adjuntos.
- Conversión entre monedas.
- Auditoría detallada de cambios.
- Integración con sistemas contables, CRM, ERP u otras plataformas.
- Importación o exportación masiva de información.

## 4. Definiciones y convenciones

| Término | Definición |
|---|---|
| Proyecto | Iniciativa tecnológica registrada y gestionada por MANOR SAC. |
| Código de proyecto | Identificador de negocio único e inmutable asignado al proyecto. |
| Identificador | Clave técnica interna y única generada por el sistema o la base de datos. |
| Sector industrial | Categoría económica a la que pertenece el cliente o el proyecto. |
| Presupuesto | Importe monetario asignado al proyecto, expresado en euros. |
| Estado | Situación operativa del proyecto dentro de su ciclo de vida. |
| RF | Requerimiento funcional. |
| CA | Criterio de aceptación. |

### 4.1 Prioridades

- **Alta:** funcionalidad indispensable para la operación básica del sistema.
- **Media:** funcionalidad necesaria para mejorar consulta, seguimiento o presentación.
- **Baja:** funcionalidad deseable que puede planificarse para una versión posterior.

### 4.2 Convención de redacción

Cada requerimiento utiliza un identificador único y la expresión **“El sistema deberá”** para establecer una obligación funcional verificable.

## 5. Actores

| Actor | Descripción | Responsabilidades principales |
|---|---|---|
| Usuario gestor de proyectos | Persona autorizada para administrar información de proyectos. | Registrar, consultar, actualizar y eliminar proyectos. |
| Usuario de consulta | Persona que requiere revisar proyectos y estadísticas. | Consultar proyectos, visualizar detalles y obtener presupuestos por sector. |
| Sistema | Aplicación de gestión de proyectos de MANOR SAC. | Validar, procesar, almacenar, recuperar y presentar la información. |

> **Nota:** La distinción de permisos entre actores deberá definirse cuando se incorpore la gestión de usuarios y roles.

## 6. Datos del proyecto

| Campo | Tipo lógico | Obligatorio | Editable después del registro | Regla principal |
|---|---|---:|---:|---|
| Identificador | Entero o UUID | Sí, generado por el sistema | No | Debe ser único. |
| Código del proyecto | Texto | Sí | No | No puede estar vacío ni duplicado. |
| Nombre del proyecto | Texto | Sí | Sí | No puede estar vacío. |
| Nombre del cliente | Texto | Según definición pendiente | Sí | Debe almacenarse asociado al proyecto. |
| Sector industrial | Catálogo | Según definición pendiente | Sí | Debe corresponder a una categoría permitida. |
| Presupuesto | Decimal | Sí | Sí | Debe ser mayor que cero y expresarse en euros. |
| Estado | Catálogo | No | Sí | Si se omite, se asigna “Propuesto”. |
| Fecha de registro | Fecha y hora | Sí, generada por el sistema | No | Se utilizará para ordenar los registros. |

## 7. Reglas de negocio

| Código | Regla |
|---|---|
| RN-01 | El código del proyecto es obligatorio. |
| RN-02 | El código del proyecto debe ser único. |
| RN-03 | El código del proyecto no puede modificarse después del registro. |
| RN-04 | El nombre del proyecto es obligatorio. |
| RN-05 | El presupuesto debe ser mayor que cero. |
| RN-06 | Si no se especifica un estado al registrar un proyecto, el sistema debe asignar “Propuesto”. |
| RN-07 | Los sectores permitidos son Financiero, Salud, Manufactura, Comercio minorista y Telecomunicaciones. |
| RN-08 | Los estados permitidos son Propuesto, En progreso, Completado y Cancelado. |
| RN-09 | El presupuesto total por sector corresponde a la suma de los presupuestos de todos los proyectos registrados en el sector seleccionado. |
| RN-10 | Los importes deben mostrarse en euros con dos decimales. |
| RN-11 | La consulta general debe ordenar los proyectos desde el registro más reciente hasta el más antiguo. |

---

## 8. Requerimientos funcionales

### RF-01. Registrar un nuevo proyecto

| Atributo | Especificación |
|---|---|
| Descripción | El sistema deberá permitir registrar un nuevo proyecto. |
| Actor principal | Usuario gestor de proyectos |
| Prioridad | Alta |
| Precondiciones | El usuario debe encontrarse en la opción de registro de proyectos. |
| Entradas | Código, nombre del proyecto, nombre del cliente, sector industrial, presupuesto y estado. |
| Proceso | El sistema validará la información, asignará el estado predeterminado cuando corresponda y almacenará el proyecto. |
| Resultado | Proyecto registrado y disponible para consulta. |
| Excepciones | Datos obligatorios vacíos, presupuesto no válido, código duplicado o valor de catálogo no permitido. |

**Criterios de aceptación**

- **CA-01.1:** Dado que los datos son válidos, cuando el usuario confirme el registro, entonces el sistema deberá guardar el proyecto y mostrar una confirmación.
- **CA-01.2:** Dado que existe un error de validación, cuando el usuario intente registrar el proyecto, entonces el sistema no deberá guardar información parcial y deberá identificar el campo o la regla incumplida.
- **CA-01.3:** Dado que el registro fue exitoso, cuando el usuario consulte los proyectos, entonces el nuevo proyecto deberá aparecer en la lista.

### RF-02. Consultar proyectos registrados

| Atributo | Especificación |
|---|---|
| Descripción | El sistema deberá mostrar los proyectos almacenados. |
| Actor principal | Usuario gestor de proyectos o usuario de consulta |
| Prioridad | Alta |
| Precondiciones | La aplicación debe tener acceso a la base de datos. |
| Salidas | Identificador, código, nombre, cliente, sector industrial, presupuesto y estado. |
| Orden | Desde el registro más reciente hasta el más antiguo. |

**Criterios de aceptación**

- **CA-02.1:** Cuando el usuario acceda a la consulta, el sistema deberá recuperar y mostrar todos los proyectos disponibles.
- **CA-02.2:** Cuando existan proyectos, el sistema deberá presentarlos en orden descendente de fecha de registro.
- **CA-02.3:** Cuando no existan proyectos, el sistema deberá mostrar una indicación clara de que no hay registros disponibles.

### RF-03. Consultar el detalle de un proyecto

| Atributo | Especificación |
|---|---|
| Descripción | El sistema deberá permitir seleccionar un proyecto y visualizar todos sus datos. |
| Actor principal | Usuario gestor de proyectos o usuario de consulta |
| Prioridad | Alta |
| Precondiciones | El proyecto debe existir. |
| Resultado | Información completa del proyecto presentada en el formulario de detalle o edición. |

**Criterios de aceptación**

- **CA-03.1:** Dado un proyecto existente, cuando el usuario lo seleccione, entonces el sistema deberá mostrar toda su información almacenada.
- **CA-03.2:** Si el proyecto ya no existe, el sistema deberá informar la situación y actualizar la lista sin mostrar datos desactualizados.

### RF-04. Actualizar un proyecto

| Atributo | Especificación |
|---|---|
| Descripción | El sistema deberá permitir modificar el nombre del proyecto, cliente, sector industrial, presupuesto y estado. |
| Actor principal | Usuario gestor de proyectos |
| Prioridad | Alta |
| Precondiciones | El proyecto debe existir y haber sido seleccionado. |
| Restricción | El código del proyecto no podrá modificarse. |
| Resultado | Información actualizada y persistida. |

**Criterios de aceptación**

- **CA-04.1:** Dado que los cambios son válidos, cuando el usuario confirme la actualización, entonces el sistema deberá guardar los nuevos valores y mostrar una confirmación.
- **CA-04.2:** El sistema deberá presentar el código del proyecto como un dato no editable.
- **CA-04.3:** Si el presupuesto actualizado no es mayor que cero, el sistema deberá rechazar la operación y conservar los datos anteriores.
- **CA-04.4:** Si el proyecto no existe al momento de guardar, el sistema deberá cancelar la actualización e informar al usuario.

### RF-05. Eliminar un proyecto

| Atributo | Especificación |
|---|---|
| Descripción | El sistema deberá permitir eliminar un proyecto seleccionado. |
| Actor principal | Usuario gestor de proyectos |
| Prioridad | Alta |
| Precondiciones | El proyecto debe existir y haber sido seleccionado. |
| Confirmación | El sistema deberá solicitar confirmación antes de eliminar. |
| Resultado | Proyecto eliminado permanentemente de la base de datos. |

**Criterios de aceptación**

- **CA-05.1:** Cuando el usuario solicite eliminar un proyecto, el sistema deberá mostrar una confirmación que permita aceptar o cancelar.
- **CA-05.2:** Si el usuario confirma, el sistema deberá eliminar el proyecto, actualizar la lista y mostrar una confirmación.
- **CA-05.3:** Si el usuario cancela, el sistema no deberá modificar la información.
- **CA-05.4:** Si el proyecto ya no existe, el sistema deberá informar la situación y actualizar la lista.

### RF-06. Validar el código del proyecto

| Atributo | Especificación |
|---|---|
| Descripción | El sistema deberá verificar que el código no esté vacío durante el registro. |
| Prioridad | Alta |
| Regla asociada | RN-01 |

**Criterios de aceptación**

- **CA-06.1:** Si el código está vacío o contiene únicamente espacios, el sistema deberá impedir el registro e informar que el campo es obligatorio.

### RF-07. Validar el nombre del proyecto

| Atributo | Especificación |
|---|---|
| Descripción | El sistema deberá verificar que el nombre del proyecto no esté vacío durante el registro. |
| Prioridad | Alta |
| Regla asociada | RN-04 |

**Criterios de aceptación**

- **CA-07.1:** Si el nombre está vacío o contiene únicamente espacios, el sistema deberá impedir el registro e informar que el campo es obligatorio.

### RF-08. Validar el presupuesto

| Atributo | Especificación |
|---|---|
| Descripción | El sistema deberá verificar que el presupuesto sea mayor que cero durante el registro y la actualización. |
| Prioridad | Alta |
| Regla asociada | RN-05 |

**Criterios de aceptación**

- **CA-08.1:** El sistema deberá aceptar un presupuesto numérico mayor que cero.
- **CA-08.2:** El sistema deberá rechazar valores iguales a cero, negativos, vacíos o no numéricos.
- **CA-08.3:** Ante un valor no válido, el sistema deberá mostrar un mensaje y no deberá completar la operación.

### RF-09. Validar códigos duplicados

| Atributo | Especificación |
|---|---|
| Descripción | El sistema deberá impedir el registro de dos proyectos con el mismo código. |
| Prioridad | Alta |
| Regla asociada | RN-02 |

**Criterios de aceptación**

- **CA-09.1:** Si el código ya está asociado a otro proyecto, el sistema deberá rechazar el registro e informar que el código ya existe.
- **CA-09.2:** La base de datos deberá reforzar la unicidad del código mediante una restricción única.

### RF-10. Asignar un estado predeterminado

| Atributo | Especificación |
|---|---|
| Descripción | El sistema deberá asignar el estado “Propuesto” cuando el usuario no especifique un estado al registrar un proyecto. |
| Prioridad | Alta |
| Regla asociada | RN-06 |

**Criterios de aceptación**

- **CA-10.1:** Si el estado se omite, el proyecto deberá almacenarse con el estado “Propuesto”.
- **CA-10.2:** Si el usuario selecciona un estado permitido, el sistema deberá conservar dicho valor.

### RF-11. Clasificar proyectos por sector industrial

| Atributo | Especificación |
|---|---|
| Descripción | El sistema deberá permitir asignar un sector industrial a cada proyecto. |
| Prioridad | Alta |
| Valores permitidos | Financiero, Salud, Manufactura, Comercio minorista y Telecomunicaciones. |
| Regla asociada | RN-07 |

**Criterios de aceptación**

- **CA-11.1:** El sistema deberá presentar únicamente los sectores permitidos en el catálogo vigente.
- **CA-11.2:** El sistema deberá impedir el almacenamiento de un sector que no pertenezca al catálogo permitido.

### RF-12. Gestionar estados de los proyectos

| Atributo | Especificación |
|---|---|
| Descripción | El sistema deberá permitir asignar y actualizar el estado de un proyecto. |
| Prioridad | Alta |
| Valores permitidos | Propuesto, En progreso, Completado y Cancelado. |
| Regla asociada | RN-08 |

**Criterios de aceptación**

- **CA-12.1:** El sistema deberá presentar únicamente los estados permitidos.
- **CA-12.2:** El sistema deberá impedir el almacenamiento de un estado no permitido.
- **CA-12.3:** Al actualizar el estado, el nuevo valor deberá visualizarse en la consulta general y en el detalle.

### RF-13. Consultar el presupuesto total por sector industrial

| Atributo | Especificación |
|---|---|
| Descripción | El sistema deberá calcular el presupuesto acumulado de los proyectos pertenecientes al sector seleccionado. |
| Actor principal | Usuario de consulta o usuario gestor de proyectos |
| Prioridad | Alta |
| Entrada | Sector industrial. |
| Cálculo | Suma de los presupuestos de todos los proyectos del sector seleccionado. |
| Regla asociada | RN-09 |

**Criterios de aceptación**

- **CA-13.1:** Dado un sector seleccionado, cuando el usuario solicite la estadística, el sistema deberá sumar los presupuestos de todos los proyectos de ese sector.
- **CA-13.2:** Los proyectos de otros sectores no deberán incluirse en el cálculo.
- **CA-13.3:** Si no existen proyectos para el sector, el sistema deberá mostrar un total de 0,00 EUR.

### RF-14. Mostrar resultados estadísticos

| Atributo | Especificación |
|---|---|
| Descripción | El sistema deberá presentar el presupuesto total del sector seleccionado en euros y con dos decimales. |
| Prioridad | Media |
| Regla asociada | RN-10 |

**Criterios de aceptación**

- **CA-14.1:** El resultado deberá identificar el sector utilizado en el cálculo.
- **CA-14.2:** El importe deberá presentarse en euros con dos posiciones decimales.
- **CA-14.3:** Si ocurre un error durante el cálculo o la recuperación de datos, el sistema deberá informar que no fue posible obtener la estadística y no deberá mostrar un valor incorrecto como válido.

### RF-15. Almacenar información de los proyectos

| Atributo | Especificación |
|---|---|
| Descripción | El sistema deberá almacenar permanentemente la información de los proyectos en una base de datos relacional MySQL. |
| Prioridad | Alta |
| Resultado | Los registros permanecerán disponibles después de cerrar y volver a ejecutar la aplicación. |

**Criterios de aceptación**

- **CA-15.1:** Después de registrar o actualizar un proyecto y reiniciar la aplicación, la información deberá permanecer disponible.
- **CA-15.2:** Una operación fallida no deberá dejar registros incompletos ni datos parcialmente actualizados.
- **CA-15.3:** La eliminación confirmada deberá reflejarse de forma permanente en la base de datos.

### RF-16. Recuperar información almacenada

| Atributo | Especificación |
|---|---|
| Descripción | El sistema deberá recuperar los proyectos almacenados y mostrarlos desde el registro más reciente hasta el más antiguo. |
| Prioridad | Alta |
| Regla asociada | RN-11 |

**Criterios de aceptación**

- **CA-16.1:** Al iniciar la consulta, el sistema deberá recuperar los registros persistidos en MySQL.
- **CA-16.2:** Los resultados deberán ordenarse por fecha de registro descendente.
- **CA-16.3:** Si no es posible acceder a la base de datos, el sistema deberá informar el error y evitar presentar información incompleta como si estuviera actualizada.

---

## 9. Matriz de trazabilidad

| Requerimiento | Regla de negocio | Caso de uso asociado | Prioridad |
|---|---|---|---|
| RF-01 | RN-01, RN-02, RN-04, RN-05, RN-06, RN-07, RN-08 | CU-01 Registrar proyecto | Alta |
| RF-02 | RN-10, RN-11 | CU-02 Consultar proyectos | Alta |
| RF-03 | No aplica | CU-03 Consultar detalle | Alta |
| RF-04 | RN-03, RN-05, RN-07, RN-08 | CU-04 Actualizar proyecto | Alta |
| RF-05 | No aplica | CU-05 Eliminar proyecto | Alta |
| RF-06 | RN-01 | CU-01 Registrar proyecto | Alta |
| RF-07 | RN-04 | CU-01 Registrar proyecto | Alta |
| RF-08 | RN-05 | CU-01 / CU-04 | Alta |
| RF-09 | RN-02 | CU-01 Registrar proyecto | Alta |
| RF-10 | RN-06 | CU-01 Registrar proyecto | Alta |
| RF-11 | RN-07 | CU-01 / CU-04 | Alta |
| RF-12 | RN-08 | CU-01 / CU-04 | Alta |
| RF-13 | RN-09 | CU-06 Consultar presupuesto por sector | Alta |
| RF-14 | RN-10 | CU-06 Consultar presupuesto por sector | Media |
| RF-15 | RN-02 | Todos los casos con persistencia | Alta |
| RF-16 | RN-11 | CU-02 Consultar proyectos | Alta |

## 10. Resumen de requerimientos

| Código | Nombre | Prioridad |
|---|---|---|
| RF-01 | Registrar un nuevo proyecto | Alta |
| RF-02 | Consultar proyectos registrados | Alta |
| RF-03 | Consultar el detalle de un proyecto | Alta |
| RF-04 | Actualizar un proyecto | Alta |
| RF-05 | Eliminar un proyecto | Alta |
| RF-06 | Validar el código del proyecto | Alta |
| RF-07 | Validar el nombre del proyecto | Alta |
| RF-08 | Validar el presupuesto | Alta |
| RF-09 | Validar códigos duplicados | Alta |
| RF-10 | Asignar un estado predeterminado | Alta |
| RF-11 | Clasificar proyectos por sector industrial | Alta |
| RF-12 | Gestionar estados de los proyectos | Alta |
| RF-13 | Consultar el presupuesto total por sector industrial | Alta |
| RF-14 | Mostrar resultados estadísticos | Media |
| RF-15 | Almacenar información de los proyectos | Alta |
| RF-16 | Recuperar información almacenada | Alta |

## 11. Supuestos y dependencias

- MANOR SAC dispondrá de una instancia operativa de MySQL y de las credenciales de conexión correspondientes.
- Los sectores industriales y estados definidos en esta versión constituyen catálogos cerrados.
- Todos los presupuestos se registrarán y mostrarán en euros.
- El sistema contará con una fecha y hora de registro para cumplir el ordenamiento requerido.
- La definición de perfiles de seguridad, permisos y auditoría queda pendiente para una versión posterior.

## 12. Decisiones pendientes

Antes de aprobar la especificación, las partes interesadas deberán definir:

1. Longitud, formato y sensibilidad a mayúsculas del código del proyecto.
2. Longitud máxima de los campos de texto.
3. Obligatoriedad del cliente y del sector industrial.
4. Precisión y valor máximo permitido para el presupuesto.
5. Reglas de transición entre estados, si correspondieran.
6. Política de eliminación física o lógica de proyectos.
7. Zona horaria aplicable a la fecha de registro.
8. Perfiles autorizados para registrar, actualizar y eliminar.
9. Nivel de detalle requerido para mensajes de error y registros técnicos.
10. Necesidad de filtros, búsqueda, paginación y exportación en la consulta general.

## 13. Criterios generales de calidad funcional

- Cada operación deberá proporcionar una confirmación de éxito o un mensaje de error comprensible.
- Las validaciones deberán ejecutarse antes de persistir cambios.
- Los mensajes de validación deberán identificar la causa sin exponer detalles técnicos sensibles.
- Las operaciones de escritura deberán mantener la consistencia de los datos.
- La interfaz deberá evitar la modificación accidental del código del proyecto.
- Los valores monetarios deberán conservar precisión decimal adecuada y no deberán calcularse con tipos de punto flotante binario.

## 14. Condiciones de aceptación del producto

La versión funcional será candidata a aceptación cuando:

1. Todos los requerimientos de prioridad alta hayan sido implementados.
2. Todos los criterios de aceptación asociados hayan sido probados con resultado satisfactorio.
3. No existan defectos críticos ni altos abiertos.
4. La persistencia y recuperación desde MySQL hayan sido verificadas después de reiniciar la aplicación.
5. Los cálculos de presupuesto por sector hayan sido contrastados con datos de prueba conocidos.
6. Las decisiones pendientes que afecten el desarrollo hayan sido aprobadas y documentadas.

---