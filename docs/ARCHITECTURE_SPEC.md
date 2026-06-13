# ms-dev-mentor — Spec Arquitectónico

Documento normativo. Define las decisiones técnicas que todo código nuevo debe respetar.

---

## 1. Estilo arquitectónico

**Vertical Slice + Hexagonal “directa” dentro de cada slice.**

- **Vertical Slice puro:** el paquete raíz es la **feature** (`doc_negocio`, `doc_sisteme`, `doc_ddl`, …), no la capa. Cada slice contiene su propio `domain/`, `application/` e `infrastructure/`.
- **Slice autocontenido:** un slice solo puede importar de su propio paquete y de `commons/`. Nunca de otro slice.
- **`commons/`:** solo lo que comparten ≥ 2 slices (`Documento` base, `TipoDocumento`, `EntityNotFoundException`, `GlobalExceptionHandler`). Si una clase la usa **un solo** slice, vive dentro de ese slice.
- **Hexagonal directa:** dentro del slice NO hay puertos de entrada. El controller REST inyecta directamente los casos de uso de `application`.
- **Puertos de salida sí:** interfaces en `<slice>/domain/ports/out/` para desacoplar `application` de la infraestructura.

### Estructura de paquetes

```
cl.sprint_rocket_ai.ms_dev_mentor
│
├── commons/                                    ← compartido entre slices
│   ├── domain/
│   │   ├── enums/                              
│   │   ├── exceptions/                         (EntityNotFoundException)
│   │   └── models/                             (Documento – clase base abstracta)
│   └── infrastructure/                         (GlobalExceptionHandler)
│
└── <slice>/                                    ← un paquete por feature
    ├── domain/
    │   ├── enums/                              (enums propios del slice)
    │   ├── models/                             (modelos + value objects embebidos)
    │   └── ports/out/<Entidad>PortOut.java
    ├── application/                            (1 clase = 1 caso de uso)
    └── infrastructure/
        ├── in/
        │   ├── <Entidad>Rest.java              (interfaz Swagger)
        │   ├── <Entidad>Controller.java        (@RestController + verbos)
        │   └── dtos/                           (Request/Response records)
        ├── out/<Entidad>AdapterOut.java
        └── persistences/mongodb/<Entidad>MongoRepository.java
```

---

## 2. Reglas de dependencia

| Desde \ Hacia              | commons | propio slice | otro slice |
|----------------------------|:-------:|:------------:|:----------:|
| Cualquier capa de un slice |   ✅    |      ✅      |     ❌     |
| `commons`                  |   ✅    |      ❌      |     ❌     |

Dentro de un slice, entre capas:

| Desde \ Hacia          | domain | application | infra.dtos | infra.in | infra.out | infra.persistences |
|------------------------|:------:|:-----------:|:----------:|:--------:|:---------:|:------------------:|
| **domain**             |   ✅   |     ❌      |     ❌     |    ❌    |    ❌     |         ❌         |
| **application**        |   ✅   |     ✅      |     ✅     |    ❌    |    ❌     |         ❌         |
| **infra.in**           |   ✅   |     ✅      |     ✅     |    ✅    |    ❌     |         ❌         |
| **infra.out**          |   ✅   |     ❌      |     ❌     |    ❌    |    ✅     |         ✅         |

Claves:
1. `domain` no depende de nada fuera de sí mismo (ni Spring, ni DTOs).
2. `application` conoce los DTOs de infraestructura (recibe `*Request`, devuelve `*Response`), pero **NO** conoce el adapter ni el repositorio.
3. `application` depende sólo de la interfaz `<slice>.domain.ports.out.*`.
4. No hay puertos de entrada: el controller llama directo al caso de uso.
5. `commons` jamás importa código de un slice.

---

## 3. Naming

- **Atributos:** SIEMPRE en **español** (`titulo`, `fechaCreacion`, `tablas`).
- **Métodos:** SIEMPRE en **inglés** (`execute`, `save`, `findById`, `applyTo`, `from`, `toDomain`).
- **Casos de uso:** verbo inglés + entidad español → `SaveDocumentoNegocio`, `GetDocumentoDDLById`, `ListDocumentoSistemaByProyecto`, `UpdateDocumentoDDL`.
- **Clases:** `<Entidad>Rest`, `<Entidad>Controller`, `<Entidad>AdapterOut`, `<Entidad>PortOut`, `<Entidad>MongoRepository`, `<Entidad>Request`, `<Entidad>Response`.
- **Colecciones Mongo:** snake_case en plural (`documentos_negocio`, `documentos_ddl`).
- **Campos persistidos:** `@Field("snake_case")` sobre atributo camelCase español.
- **Endpoints:** kebab-case en plural (`/api/documentos-ddl`, `/api/documentos-negocio`).
- **Enums:** valores en MAYÚSCULAS español (`BORRADOR`, `VIGENTE`, `POSTGRESQL`, `CONFLUENCE`).
- **Paquete del slice:** snake_case (`doc_negocio`, `doc_ddl`).

---

## 4. Capa `domain` (por slice)

- **Modelos:** POJOs anémicos con getters/setters. Se aceptan anotaciones Mongo (`@Document`, `@Id`, `@Field`).
- Jerarquía: `commons.domain.models.Documento` abstracta + subclase por slice, con método abstracto `getTipoDocumento()`.
- **Value Objects embebidos** (p. ej. `Tabla`, `Columna`, `Relacion` en `doc_ddl`): viven junto al modelo raíz en `<slice>/domain/models/`.
- **Enums** propios del slice en `<slice>/domain/enums/`; los compartidos en `commons/domain/enums/`.
- **Excepciones** en `commons/domain/exceptions/`, extienden `RuntimeException`. Usar `EntityNotFoundException` para 404.
- **Puertos de salida:** interfaces en `<slice>/domain/ports/out/` que operan sobre modelos de dominio (`save`, `findById`). No devuelven tipos de Spring Data ni DTOs.

---

## 5. Capa `application` (casos de uso)

- **Una clase = un caso de uso.** `@Service`, declarada `final`.
- Inyección por constructor. **Sin Lombok, sin `@Autowired`.**
- Único método público: `execute(...)`.
- Recibe `*Request`, devuelve `*Response` (o `List<*Response>`).
- Conversión:
  - **Request → modelo:** `request.applyTo(modelo)` (método de instancia del record).
  - **Modelo → Response:** `Response.from(modelo)` (factory estático del record).
- `Save`: instancia el modelo, llama `request.applyTo(modelo)`, setea `fechaCreacion = LocalDateTime.now()`, persiste, retorna `Response.from(saved)`.
- `Update`: `findById(...).map(existing -> { request.applyTo(existing); existing.setFechaActualizacion(now); save; }).map(Response::from).orElseThrow(EntityNotFoundException)`.
- Logging SLF4J (`private static final Logger log = LoggerFactory.getLogger(...)`), inicio y fin con placeholders `{}`, mensajes en español.

---

## 6. Capa `infrastructure` (por slice)

### 6.1 DTOs (`<slice>/infrastructure/in/dtos/`)
- **SIEMPRE `record`**.
- `@Schema` en clase y en cada campo (`description`, `example`).
- **Bean Validation** en los Request (`@NotBlank`, `@NotNull`, `@NotEmpty`, `@Size`, `@Valid` para anidados). Mensajes en español.
- **`*Request.applyTo(<Entidad> target)`** (método de instancia): muta `target` con los valores del request, incluida la conversión recursiva de DTOs anidados (`toDomain()` interno por VO).
- **`*Response.from(<Entidad> modelo)`** (factory estático): convierte modelo → DTO, con mapeo recursivo si aplica.
- Los Response NO llevan constraints de validación.
- Sin lógica de negocio. **No compartir DTOs entre slices.**

### 6.2 Interfaz Swagger (`<slice>/infrastructure/in/<Entidad>Rest.java`)
- Interfaz con `@Tag` + todo el detalle Swagger por método: `@Operation`, `@ApiResponses`, `@ApiResponse`, `@Content`, `@Schema`, `@ArraySchema`, `@Parameter`.
- **NO lleva `@RequestMapping` ni anotaciones de verbo.**
- Sí puede llevar `@RequestBody`, `@PathVariable`, `@RequestParam` en parámetros.

### 6.3 Controller (`<slice>/infrastructure/in/<Entidad>Controller.java`)
- `@RestController` + `@RequestMapping("/api/<entidades>")`, `implements <Entidad>Rest`, `final`.
- Inyección por constructor de los casos de uso.
- Cada método `@Override` añade SOLO el verbo y el path (`@PostMapping`, `@GetMapping("/{id}")`, `@PutMapping("/{id}")`, …).
- Cuerpo = una línea delegando al caso de uso y envolviendo en `ResponseEntity`:
  - `create` → `new ResponseEntity<>(uc.execute(req), HttpStatus.CREATED)`
  - resto → `ResponseEntity.ok(uc.execute(...))`
- **Sin lógica, sin validaciones manuales, sin try/catch.**

### 6.4 Adapter de salida (`<slice>/infrastructure/out/<Entidad>AdapterOut.java`)
- `@Component`, `final`, `implements <Entidad>PortOut`.
- Inyecta el `*MongoRepository` por constructor. Logging SLF4J. Sin lógica de negocio.

### 6.5 Repositorio Mongo (`<slice>/infrastructure/persistences/mongodb/<Entidad>MongoRepository.java`)
- Interfaz `extends MongoRepository<<Entidad>, String>` con métodos derivados por nombre.

---

## 7. Rutas REST estándar

Base path `/api/<entidades>`:

| Operación           | Verbo | Path                       | Status | Caso de uso                |
|---------------------|-------|----------------------------|:------:|----------------------------|
| Crear               | POST  | `/`                        | 201    | `Save<Entidad>`            |
| Obtener por id      | GET   | `/{id}`                    | 200    | `Get<Entidad>ById`         |
| Actualizar          | PUT   | `/{id}`                    | 200    | `Update<Entidad>`          |

Errores documentados en Swagger: `400` (request inválida), `404` (no encontrado).

---

## 8. Checklist para nueva feature (nuevo slice)

1. Crear paquete `<slice>/` en la raíz del proyecto.
2. **Domain:** modelo (extiende `commons.domain.models.Documento` si aplica), enums propios, `<Entidad>PortOut`.
3. **Application:** `Save`, `GetById`, `ListBy<X>`, `Update` (con `request.applyTo()` / `Response.from()`).
4. **DTOs:** `*Request` (con `applyTo`) y `*Response` (con `from`), records con `@Schema` y Bean Validation.
5. **REST:** interfaz `*Rest` (Swagger) + `*Controller` (verbos/paths).
6. **Out:** `*AdapterOut` + `*MongoRepository`.

---

## 9. Anti-patrones prohibidos

- ❌ Lombok.
- ❌ `@Autowired` en campos.
- ❌ Importar entre slices (`doc_negocio` no puede ver `doc_ddl`).
- ❌ Mover al slice algo que ya usan ≥ 2 slices (debe ir a `commons`), o viceversa.
- ❌ Anotaciones de verbo (`@GetMapping`, …) o `@RequestMapping` en la interfaz `*Rest`.
- ❌ Anotaciones Swagger en el controller.
- ❌ Lógica en el controller distinta a delegar al caso de uso.
- ❌ `application` dependiendo de `adapters.out` o `persistences`.
- ❌ `domain` importando DTOs, Spring Web o cualquier framework no-persistencia.
- ❌ DTOs como `class` (deben ser `record`).
- ❌ Atributos en inglés / métodos en español.
- ❌ Compartir DTOs entre slices.
- ❌ Mappers fuera de `Request.applyTo()` / `Response.from()` (incluye `toDomain()` interno de DTOs anidados del MISMO slice).

