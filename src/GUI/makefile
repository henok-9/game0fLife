FILES := GridState.java GameOfLifeGUI.java CustomCellPanel.java

# Default target to compile specified Java files
all: compile run

# Rule to compile each specified Java file
%.class: %.java
	javac $<

compile: $(FILES:.java=.class)

run:
	# java DisplayGrid 
	java GameOfLifeGUI
# Target to clean compiled class files
clean:
	rm -f *.class
