# Makefile Tarea 1 - Etapa 2 (ELO329 - UTFSM)

# Variables de Compilación
JCC = javac
JVM = java

# Archivos de entrada
CONFIG = config.txt
MOVE = move.txt

# Archivo principal (En esta etapa mantuviste el nombre T1Stage1)
MAIN = T1Stage1

# 1. Regla por defecto: Compila todos los archivos .java
# Al escribir solo "make" en la terminal, se ejecutará esto.
all: compile

compile:
	$(JCC) *.java

# 2. Regla para ejecutar la simulación
# Al poner "run: compile", te aseguras de que siempre recompile si hubo cambios antes de correr.
run: compile
	$(JVM) $(MAIN) $(CONFIG) $(MOVE)

# 3. Regla para limpiar archivos compilados
clean:
	rm -f *.class