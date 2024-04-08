FILES := GridState.java DisplayGrid.java 

# Default target to compile specified Java files
all: compile run

# Rule to compile each specified Java file
%.class: %.java
	javac $<

compile: $(FILES:.java=.class)

run:
	java DisplayGrid.java

# Target to clean compiled class files
clean:
	rm -f *.class
