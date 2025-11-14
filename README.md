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

Circo_AdrianIglesiasTarea2/

pom.xml

README.md

.gitignore

src/

main/

java/

controller/
Control del flujo interno

dao/
Acceso a datos (ficheros, XML, SQL)

entidades/
Clases del dominio (Espectaculo, Artista, Coordinacion…)

fachada/
Casos de uso entre vista y servicios

service/
Lógica de negocio

utils/
Funciones y utilidades comunes

views/
Menús y vistas de consola

resources/

circo_adrianiglesias.sql
Script SQL del proyecto

target/
(generado por Maven, ignorado por Git)
