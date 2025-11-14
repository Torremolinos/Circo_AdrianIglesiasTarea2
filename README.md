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

📦 Circo_AdrianIglesiasTarea2
├── 📂 src
│   └── 📂 main
│       ├── 📂 java
│       │   ├── 📂 controller
│       │   │   └── Control básico del flujo y coordinación interna
│       │   │
│       │   ├── 📂 dao
│       │   │   └── Acceso a datos:
│       │   │       • Ficheros de texto
│       │   │       • Lectura XML
│       │   │       • Conexión SQL (JDBC)
│       │   │
│       │   ├── 📂 entidades
│       │   │   └── Clases del dominio:
│       │   │       • Persona
│       │   │       • Artista
│       │   │       • Coordinacion
│       │   │       • Espectaculo
│       │   │       • Numero
│       │   │
│       │   ├── 📂 fachada
│       │   │   └── Casos de uso del sistema
│       │   │       (Vista → Fachada → Servicios)
│       │   │
│       │   ├── 📂 service
│       │   │   └── Lógica de negocio:
│       │   │       • Gestión de espectáculos
│       │   │       • Gestión de artistas
│       │   │       • Gestión de coordinación
│       │   │
│       │   ├── 📂 utils
│       │   │   └── Utilidades generales:
│       │   │       • Fechas
│       │   │       • Lectura segura
│       │   │       • Validaciones
│       │   │
│       │   └── 📂 views
│       │       └── Vistas y menús de consola:
│       │           • Menú Invitado
│       │           • Menú Artista
│       │           • Menú Coordinación
│       │           • Menú Administrador
│       │
│       └── 📂 resources
│           ├── 📄 circo_adrianiglesias.sql   # Script SQL completo del proyecto
│           └── (otros recursos opcionales)
│
├── 📄 pom.xml           # Configuración Maven
├── 📄 README.md         # Documentación del proyecto
└── 📄 .gitignore        # Exclusiones de Git
