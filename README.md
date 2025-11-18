# 🎪 Proyecto Circo — Tarea 2 (Acceso a Datos · DAM2)

**Autor:** Adrián Iglesias Riño  
**Asignatura:** Acceso a Datos  
**Curso:** 2º DAM  
**Lenguaje:** Java 17 · Maven  
**Tema:** Gestión de un Circo mediante ficheros, XML y SQL

---

## 📝 Descripción del proyecto

Este proyecto implementa la gestión informática de un **Circo**, organizando y controlando diferentes elementos del dominio:

- Personas  
- Artistas  
- Coordinación  
- Espectáculos  
- Números artísticos  

La aplicación utiliza **XML**, **acceso a bases de datos SQL**, y una arquitectura por capas estructurada mediante **DAO, controllers, Services, Fachadas y Views**.  
El objetivo es aplicar los contenidos de Acceso a Datos y diseñar un sistema modular, mantenible y ampliable.

---

## 🧱 Estructura del proyecto

📁 Estructura del proyecto

📦 Circo_AdrianIglesiasTarea2/

📄 pom.xml

📄 README.md

📄 .gitignore

📂 src/

📂 main/

📂 java/

📂 controller/
Control interno del flujo

📂 dao/
Acceso a datos: ficheros, XML, SQL (JDBC)

📂 entidades/
Modelo del dominio (Espectaculo, Artista, Coordinacion…)

📂 service/
Lógica de negocio del sistema

📂 utils/
Funciones auxiliares y utilidades

📂 views/
Menús y vistas de consola

📂 resources/

🗃️ circo_adrianiglesias.sql
Script SQL de creación e inserción de datos

📂 target/
(generado automáticamente por Maven; ignorado por Git)
