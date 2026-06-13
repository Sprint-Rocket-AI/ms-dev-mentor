db = db.getSiblingDB('mydatabase');

// 1. Crear usuario de aplicación
db.createUser({
  user: 'admin',
  pwd: 'admin',
  roles: [
    { role: 'readWrite', db: 'mydatabase' }
  ]
});

// 2. Crear colecciones explícitamente (opcional en Mongo, pero recomendado)
db.createCollection('documentos_negocio');
db.createCollection('documentos_lineamientos');
db.createCollection('documentos_sistemas');
db.createCollection('documentos_ddl');


print("Inicialización de la base de datos completada.");
