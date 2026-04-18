# Makefile para Tarea 1 - Etapa 5 Bonus (ELO329 - UTFSM)

# Variables de compilación
JCC = javac
JFLAGS = -g
JVM = java

# Archivos de entrada (Parámetros del main)
CONFIG = config.txt
MOVE = move.txt

# Clase que contiene el metodo main
MAIN = SimuladorTest

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

# 3. Regla para limpiar archivos compilados (basura) y el csv
clean:
	rm -f *.class