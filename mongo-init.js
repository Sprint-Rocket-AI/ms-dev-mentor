db = db.getSiblingDB('mydatabase');

// 1. Crear usuario de aplicación
db.createUser({
  user: 'admin',
  pwd: 'admin',
  roles: [
    { role: 'readWrite', db: 'mydatabase' }
  ]
});

db.createCollection('documentos_contexto');


print("Inicialización de la base de datos completada.");
