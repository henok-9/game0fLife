FILES := GridState.java GameOfLifeGUI.java CustomCellPanel.java

all: compile run

%.class: %.java
	javac $<

compile: $(FILES:.java=.class)

run:
	# java DisplayGrid 
	java GameOfLifeGUI
	
# Target to clean compiled class files
clean:
	rm -f *.class
