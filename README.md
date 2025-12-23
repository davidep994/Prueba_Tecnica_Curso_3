# ✈️ API REST de Gestión de Vuelos (Spring Boot)

Prueba-Tecnica__Curso-3

📌 Descripción del Proyecto

Este proyecto es una aplicación desarrollada con **Spring Boot** que implementa un CRUD (Create, Read, Update, Delete) completo para la gestión de vuelos. La aplicación permite administrar una lista de vuelos en memoria, aplicando reglas de negocio específicas y ofreciendo capacidades avanzadas de filtrado y ordenamiento.

---


📂 Estructura del Proyecto

```
El proyecto sigue una arquitectura por capas para separar las responsabilidades:

* **`controllers`**: Exponen los endpoints de la API REST.
* **`services`**: Contienen la lógica de negocio y el procesamiento de datos.
* **`repositories`**: Gestionan la persistencia de datos (en este caso, una lista en memoria).
* **`models`**: Definición de la entidad principal `Vuelo`.
* **`dtos`**: Objetos de transferencia de datos para desacoplar la API del modelo interno.
* **`exceptions`**: Manejo global de errores y excepciones personalizadas.
* **`utils`**: Clases de apoyo para validación de fechas.
```


---


🚀 Características del Programa

* **CRUD Completo**: Gestión total de vuelos.
* **Generación Automática de ID**: Los IDs se generan internamente mediante `AtomicInteger` para garantizar unicidad.
* **Filtrado Combinable**: Búsqueda de vuelos por empresa, lugar de llegada y fecha de salida simultáneamente.
* **Ordenamiento Dinámico**: Opción de ordenar los resultados por cualquier campo (por defecto: fecha de salida) en orden ascendente o descendente.
* **Cálculo Automático**: Cada vuelo calcula automáticamente su `duracionDias` al ser consultado.
* **Manejo de Errores**: Respuestas claras y formateadas ante errores de validación o recursos no encontrados.


---


🔧 Lógica del Filtro (FiltroVuelos)

* **CRUD Completo**: Gestión total de vuelos.
* **Generación Automática de ID**: Los IDs se generan internamente mediante `AtomicInteger` para garantizar unicidad.
* **Filtrado Combinable**: Búsqueda de vuelos por empresa, lugar de llegada y fecha de salida simultáneamente.
* **Ordenamiento Dinámico**: Opción de ordenar los resultados por cualquier campo (por defecto: fecha de salida) en orden ascendente o descendente.
* **Cálculo Automático**: Cada vuelo calcula automáticamente su `duracionDias` al ser consultado.
* **Manejo de Errores**: Respuestas claras y formateadas ante errores de validación o recursos no encontrados.


---


📜 Ejemplo de Ejecución

```
### 1. Obtener todos los vuelos (con filtros y orden)
`GET /api/vuelos?empresa=Iberia&ordenarPor=lugarLlegada&ordenar=ASC`

### 2. Crear un nuevo vuelo (POST)
**URL:** `/api/vuelos`  
**Body (JSON):**
```json
{
  "nombreVuelo": "IB450",
  "empresa": "Iberia",
  "lugarSalida": "Madrid",
  "lugarLlegada": "Roma",
  "fechaSalida": "15-05-2026",
  "fechaLlegada": "15-05-2026"
}
```


---


🧩 Requisitos para Ejecutar

Para poner en marcha el proyecto, necesitas:

Java JDK 17 o superior.

Maven (gestor de dependencias).

Un IDE (IntelliJ IDEA, VS Code o Eclipse).

Postman o cualquier cliente HTTP para probar los endpoints.


