# 2024-bits-of-flavor

## Fase 0. Definición de las funcionalidades de la web

- Alumno: Jesús Pérez Sánchez
- Tutor: Micael Gallego Carrillo

GitHub Project: [https://github.com/orgs/codeurjc-students/projects/10]

### Entidades

- Usuario | User
- Producto | Product
- Pedido | Order

⋅⋅⋅ Un **Pedido** lo realiza un **Cliente** (N:1)
⋅⋅⋅ Un **Pedido** es una lista de varios **Productos** (1:M)

### Imágenes
  - Cada **Usuario** tiene una foto de perfil.
  - Cada **Producto** tiene una imagen asociada.

### Tecnología complementaria
  - Generación de tickets (PDFs) con los detalles de compra.

### Algoritmo o consulta avanzada
  - Sistema de ofertas personalizadas en base a los productos comprados previamente por el usuario.

### Listado de funcionalidades de la Aplicación Web

#### Usuario anónimo
  1. Visualizar el listado de productos. (BÁSICA)
  2. Filtrar el listado de productos. (BÁSICA)
  3. Visualizar página con los detalles de un producto específico. (BÁSICA)
  4. Registrarse. (BÁSICA)
  5. Iniciar sesión. (BÁSICA)
  6. Visualizar gráfico con estadísticas de productos. (AVANZADA)

#### Usuario registrado
  7. Añádir productos al carrito de compra. (BÁSICA)
  8. Seleccionar fecha de recogida de productos. (BÁSICA)
  9. Modificar perfil de usuario. (BÁSICA)
  10. Descargar ticket PDF con los detalles de compra. (AVANZADA)
  11. Simulación de pasarela de pago real con Stipe. (AVANZADA)

#### Usuario administrador
  12. Crear y añadir nuevos productos a la app. (BÁSICA)
  13. Eliminar productos ya existentes. (BÁSICA)
  14. Sistema de gestión de ofertas. (AVANZADA)

### Wireframe de pantallas y navegación

1. Página de inicio:

![image](https://github.com/user-attachments/assets/f292a62f-5038-42a2-b738-f4e07a945a51)

2. Página de Iniciar Sesión | Registrarse

![image](https://github.com/user-attachments/assets/8132e969-3bb6-4a69-9bea-9a8b84a7f4a5)
![image](https://github.com/user-attachments/assets/0e5428f2-f6ec-473a-98ef-4e71d0ebd0a3)


3. Página de búsqueda con filtro:

![image](https://github.com/user-attachments/assets/9f0961ce-e2f0-4da8-815b-047bb962ce68)

4. Página de detalles de producto:

![image](https://github.com/user-attachments/assets/4422a673-0c67-40e4-beb1-f08d69f09445)

5. Página de perfil de usuario:

![image](https://github.com/user-attachments/assets/52c92b34-b5d3-42c5-9244-d919b968d0cc)

6. Página de resumen de compra y proceso de pago:

![image](https://github.com/user-attachments/assets/bb67b56a-5e6b-45fe-bbbd-4d8abd53cc2a)

7. Página de administrador para añadir un nuevo producto:

![image](https://github.com/user-attachments/assets/2e075264-516a-4c0a-b732-dd62b2c37b5c)

## Fase 1. Versión con funcionalidad básica

### Captura de pantallas y diagrama de navegación

1. Página de inicio:

![image](https://github.com/user-attachments/assets/0ad0e785-7e65-426d-828b-d2259ced1662)

2. Página de Iniciar Sesion / Registrarse:

![image](https://github.com/user-attachments/assets/6551256e-4045-43ff-a829-73f5222d3073)
![image](https://github.com/user-attachments/assets/8671b6c1-a444-49f5-b125-d88729927c55)

3. Página de búsqueda con filtro:

![image](https://github.com/user-attachments/assets/12fd3fc1-c58b-4973-9db5-1e7bca191e33)

4. Página de detalles de producto:

![image](https://github.com/user-attachments/assets/bd3e52bd-ffb9-4a3f-805f-cc06940a7b9c)

5. Página de perfil de usuario:

![image](https://github.com/user-attachments/assets/08f0b643-fa70-4234-9f13-7835bf6cbe4e)

6. Página de resumen de compra y proceso de pago:

![image](https://github.com/user-attachments/assets/53ecd11f-f9b9-4f78-abe4-37a060147329)

7. Página de administrador para añadir un nuevo producto:

![image](https://github.com/user-attachments/assets/33e39a45-a846-43a7-9e31-d04db983de60)

### Diagrama de navegación

![Diagrama de navegacion](https://github.com/user-attachments/assets/335a749b-4fd0-4fcd-8902-c7fbf8a9cde3)

### Diagrama con las entidades de la base de datos

![BBDD Class UML](https://github.com/user-attachments/assets/a8ff9e4d-0cd3-458e-8e5b-98bf30dc69ab)

### Diagrama de clases del BACKEND

![Backend UMLClass](https://github.com/user-attachments/assets/f8d9bcf2-2001-4b5f-9ef5-44bdadb5b57a)

### Diagrama de clases del FRONTEND

![Frontend UMLClass](https://github.com/user-attachments/assets/d8253492-2b8d-4b86-a718-478a576c3594)

### Documentación para construcción de la imagen Docker

IMPORTANTE: Es necesario tener Docker instalado en el ordenador o máquina virtual donde se construirá la imágen Docker.

1. Clonar el repositorio de GitHub: [https://github.com/codeurjc-students/2024-bits-of-flavor.git]
2. Abrir nueva terminal y situarnos en la raiz del proyecto. 
3. Ejecutar el comando cd docker para cambiar de directorio.
4. Ejecutar el comando ./script.sh para construir una nueva imágen Docker.
5. La imágen se publicará en el repositorio [https://hub.docker.com/r/jperezsa2020/java_app].

### Instrucciones de ejecución de la aplicación Dockerizada

IMPORTANTE: Es necesario tener Docker instalado en el ordenador o máquina virtual donde se construirá la imágen Docker.

1. Clonar el repositorio de GitHub: [https://github.com/codeurjc-students/2024-bits-of-flavor.git]
2. Abrir nueva terminal y situarnos en la raiz del proyecto. 
3. Ejecutar el comando cd docker para cambiar de directorio.
4. Ejecutar el comando docker-compose up para iniciar el contenedor.
5. La aplicación web estará disponible en la URL: https://localhost:8443/new/
