# ✈️ API REST de Gestión de Vuelos (Spring Boot)

Prueba-Tecnica__Curso-3

📌 Descripción del Proyecto

Este proyecto es una aplicación desarrollada con **Spring Boot** que implementa un CRUD (Create, Read, Update, Delete) completo para la gestión de vuelos. La aplicación permite administrar una lista de vuelos en memoria, aplicando reglas de negocio específicas y ofreciendo capacidades avanzadas de filtrado y ordenamiento.

---


📂 Estructura del Proyecto

```
El proyecto sigue una arquitectura por capas para separar las responsabilidades:

src/main/java/org/example/crud_vuelos_sb/
├── controllers/          # Endpoints de la API REST
├── services/             # Lógica de negocio y filtros
├── repositories/         # Gestión de datos en memoria (List)
├── models/               # Entidad principal Vuelo
├── dtos/                 # Objetos de transferencia (Request/Response) y Mappers
├── exceptions/           # Manejo global de errores (@RestControllerAdvice)
└── utils/                # Utilidades para validación de fechas
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


🔧 Lógica del Programa

Para asegurar la calidad de los datos, se han implementado las siguientes reglas:
1.  **Validación de Fechas**: No se permite crear vuelos donde la fecha de salida sea posterior a la de llegada.
2.  **No Duplicados**: El sistema impide el registro de dos vuelos con el mismo nombre en la misma fecha de salida.
3.  **Integridad**: El ID del vuelo es inmutable y gestionado por el sistema, evitando conflictos manuales.
4.  **Formato Estándar**: Todas las fechas se comunican en formato `dd-MM-yyyy`.


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


