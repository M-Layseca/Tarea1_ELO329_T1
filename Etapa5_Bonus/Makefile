# Variables
JC = javac
JVM = java
JFLAGS = -g

SOURCES = EloTelTag.java ETnube.java Viewer.java Celular.java Tablet.java SimuladorTest.java
CLASSES = $(SOURCES:.java=.class)

all: $(CLASSES)

%.class: %.java
	$(JC) $(JFLAGS) $<

run: all
	$(JVM) SimuladorTest config.txt move.txt

clean:
	rm -f *.class output.csv