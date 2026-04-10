# Makefile para Tarea 1 - Etapa 3 (ELO329 - UTFSM)

# Variables de compilación
JCC = javac
JFLAGS = -g
JVM = java

# Archivos de entrada (Parámetros del main)
CONFIG = config.txt
MOVE = move.txt

# Clase que contiene el método main
MAIN = T1Stage1

# Identificar todos los archivos fuente .java
SOURCES = $(wildcard *.java)
CLASSES = $(SOURCES:.java=.class)

# 1. Regla por defecto: Compila todos los archivos .java
all: $(CLASSES)

# Regla para transformar cada .java en un .class
%.class: %.java
	$(JCC) $(JFLAGS) $<

# 2. Regla para ejecutar la simulación con los argumentos requeridos
run: all
	$(JVM) $(MAIN) $(CONFIG) $(MOVE)

# 3. Regla para limpiar archivos compilados (basura)
clean:
	rm -f *.class