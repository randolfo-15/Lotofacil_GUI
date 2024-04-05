all: program

#  Fields
# ========
src=src/*.java

#  Methods
# =========
program: $(src)
	javac -d .bin $(src)

run:
	java -cp ".:.bin" Main

