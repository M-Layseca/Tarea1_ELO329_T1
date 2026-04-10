# Makefile Tarea 1 - Etapa 1 (ELO329 - UTFSM)

#Variables de Compilación
JCC = javac
JFLAGS = -g
JVM = java

#Archivos de entrada
CONFIG = config.txt
MOVE = move.txt
#Archivo con el main
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
	$(JVM) $(MAIN) $(CONFIG) $(MOVE) > salida.txt
	@cat salida.txt

# 3. Regla para limpiar archivos compilados (basura)
clean:
	rm -f *.class

