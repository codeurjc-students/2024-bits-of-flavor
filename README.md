La App permitirá a los miembros de la comunidad universitaria acceder a una oferta de productos alimenticios. Tiene el objetivo de brindar una alternativa para la limitada oferta de las cafeterías disponibles en la propia universidad.

GitHub Project: [https://github.com/orgs/codeurjc-students/projects/10]

Video YouTube Fase 1: [https://youtu.be/QWPBhac1iVk?si=nwOW65DqL7pB7ikM]

- Alumno: Jesús Pérez Sánchez
- Tutor: Micael Gallego Carrillo

### Instrucciones de ejecución de la App

IMPORTANTE: Es necesario tener Docker instalado en el ordenador o máquina virtual donde se ejecutará la aplicación. Se recomienda tener instalado además Docker Desktop [https://www.docker.com/products/docker-desktop/]

1. Clonar el repositorio de GitHub: [https://github.com/codeurjc-students/2024-bits-of-flavor.git]
2. Abrir nueva terminal y situarnos en la raiz del proyecto. 
3. Ejecutar el comando cd docker para cambiar de directorio.
4. Ejecutar el comando docker-compose up para iniciar el contenedor.
5. La aplicación web estará disponible en la URL: https://localhost:8443/new/
6. Ejecutar el comando docker-compose down para parar el contenedor.

## Fase 3. Versión con funcionalidad avanzada

## Funcionalidades

La version 3.0.0 incluye las siguientes nuevas funcionalidades displonibles:

USUARIO ANÓNIMO

1. Visualizar gráfico con estadísticas de compras para las próximas dos semanas.
2. Sección de ofertas desde página inicio.

USUARIO REGISTRADO

3. Sección de productos recomendados desde página inicio.

USUARIO ADMINISTRADOR

4. Añadir nuevas ofertas de productos a la aplicación
5. Eliminar ofertas existentes.
6. Visualizar el historial de compras general de la aplicación.
7. Marcas productos como "Recogido".

### Diagrama de clases del BACKEND

![Backend UMLClass](https://github.com/user-attachments/assets/0a75129b-439b-4d8e-adab-9bd967ff892d)


### Diagrama de clases del FRONTEND

![Frontend UMLClass](https://github.com/user-attachments/assets/e3d59aaf-db6e-4157-8087-2a90016d2631)

## Interfaz de usuario

1. Página de inicio:
<img width="460" alt="Inicio" src="https://github.com/user-attachments/assets/2d01cb1c-999b-4676-89e3-f560a102cffb" />

2. Página de Iniciar Sesion / Registrarse:

<img width="908" alt="Login" src="https://github.com/user-attachments/assets/1d88c641-17c2-4bf1-9f6d-d40f6b73a3dd" />
<img width="913" alt="SginUp" src="https://github.com/user-attachments/assets/aa3d2a88-8cea-4053-a630-75daf5435263" />

3. Página de búsqueda con filtro:

<img width="446" alt="Search" src="https://github.com/user-attachments/assets/3fe3a75f-9529-4833-8a71-ed5397a93368" />

4. Página de detalles de producto:

<img width="468" alt="product" src="https://github.com/user-attachments/assets/9764ba69-e6e5-4d4f-94c9-f77f2250ff89" />

5. Página de perfil de usuario:

<img width="458" alt="Profile" src="https://github.com/user-attachments/assets/40cb695f-e9d0-44b6-bd60-5a8f1006d1d5" />

6. Página de resumen de compra y proceso de pago:

<img width="469" alt="Payment" src="https://github.com/user-attachments/assets/8fe8a5ca-7a28-4c0c-b922-c3e8f6e6720d" />

7. Página de administrador para añadir un nuevo producto:

<img width="467" alt="NewProduct" src="https://github.com/user-attachments/assets/97cdf944-b29f-4b36-937c-32e8f73eaf82" />

8. Página de administrador para gestioón de ofertas:

<img width="458" alt="Stats" src="https://github.com/user-attachments/assets/79b6f84e-3f1f-4fc0-8b54-9556f08d5a41" />

9. Página de estadísticas

<img width="459" alt="Oferta" src="https://github.com/user-attachments/assets/436e0438-fbe6-47fc-a878-6bc49b2ede12" />

## Fase 2. Integración y entrega continua.

### INTEGRACIÓN CONTINUA

Al realizar una pull-request se ejecuta el workflow CI. Por el momento este workflow
ejecuta los test de Selenium referentes a las funcionalidades de usuario anónimo y administrador.
El resultado se puede ver en el apartado ACTIONS de GitHub.
Pueden surgir errores aleatorios. Si el resultado es negativo (fallo en los test) se debera ejecutar
manualmente (Workflow-dispatch) el workflow. Si el error persiste es tarea del programador solucionarlo antes de cerrar la pull-request.

### PUBLICACIÓN DE RELEASE

Al realizar una realease desde GitHub, se ejecuta el workflow CD. Se encarga de publicar la imágen
Docker correspondiente. Antes de publicar una release, se debe realizar un commit directamente en la
rama main modificando la versión del pom.xml. Se publican en total dos imágenes. La primera con
el tag main y la segunda con el tag con la fecha y la hora de construcción.
Este workflow también se puede ejecutar manualmente (Workflow-dispatch).

## Fase 1. Versión con funcionalidad básica

CREDENCIALES DE ACCESO: Al iniciar la app existen dos usuarios precreados con permisos diferentes:

- Nombre de usuario: user // Contraseña: pass // Rol: USER
- Nombre de usuario: admin // Contraseña: pass // Rol: ADMIN

Admeás, en los datos de ejemplo se carga una lista de 10 platos/productos visibles desde la pestaña "Buscar" en la barra de navegación. Para futuras versiones se añadiran más datos de ejemplo.

### Documentación para construcción de la imagen Docker

1. Clonar el repositorio de GitHub: [https://github.com/codeurjc-students/2024-bits-of-flavor.git]
2. Abrir nueva terminal y situarnos en la raiz del proyecto. 
3. Ejecutar el comando cd docker para cambiar de directorio.
4. Ejecutar el comando ./script.sh para construir una nueva imágen Docker.
5. La imágen se publicará en el repositorio [https://hub.docker.com/r/jperezsa2020/java_app].

## Funcionalidades

La version 1.0.0 incluye las siguientes funcionalidades displonibles:

1. Visualizar el listado de productos.
2. Filtrar el listado de productos.
3. Visualizar página con los detalles de un producto específico.
4. Registrarse.
5. Iniciar sesión.
6. Comprar y seleccionar fecha de recogida de productos.
7. Modificar perfil de usuario.
8. Crear y añadir nuevos productos a la app.
9. Eliminar productos ya existentes.

### Diagrama con las entidades de la base de datos

![BBDD Class UML](https://github.com/user-attachments/assets/a8ff9e4d-0cd3-458e-8e5b-98bf30dc69ab)

### ROLES

Existen dos tipos de roles en la aplicación: USER y ADMIN. Los permisos y las funcionalidades disponibles para cada tipo de usuario varian en funcion del rol adquirido.

NOTA: Los usuarios con rol ADMIN también tienen permiso para acceder a las funcionalidades de USER.

Usuario anónimo: tipo usuario sin cuenta en la aplicación o sin la sesión iniciada. Este usuario no
dispone de ningún rol. Sus funcionalidades disponibles son: 1, 2, 3, 4 y 5.

Usuario registrado: tipo de usuario estandar con la sesión iniciada. Este usuario dispone del rol USER. Sus funcionalidades disponibles son: 6 y 7 (además de las funcionalidades de usuario anónimo).

Usuario administrador: tipo de usuario con la sesión iniciada. Este usuario despone del rol ADMIN.
Tiene acceso a todas las funcionalides disponibles en la aplicación.

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

### Diagrama de clases del BACKEND

![Backend UMLClass](https://github.com/user-attachments/assets/f8d9bcf2-2001-4b5f-9ef5-44bdadb5b57a)

### Diagrama de clases del FRONTEND

![Frontend UMLClass](https://github.com/user-attachments/assets/d8253492-2b8d-4b86-a718-478a576c3594)

## Fase 0. Definición de las funcionalidades de la web

### Entidades

- Usuario | User
- Producto | Product
- Pedido | Ticket

⋅⋅⋅ Un **Pedido** lo realiza un **Cliente** (N:1)
⋅⋅⋅ Un **Pedido** es una lista de varios **Productos** (1:M)

### Imágenes
  - Cada **Usuario** tiene una foto de perfil.
  - Cada **Producto** tiene una imagen asociada.

### Tecnología complementaria
  - Generación de tickets (PDFs) con los detalles de compra.

### Algoritmo o consulta avanzada
  - Sistema de ofertas personalizadas en base a los productos comprados previamente por el usuario.

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
