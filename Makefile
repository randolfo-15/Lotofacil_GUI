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

git:
	git add *\
	git add .*\
	git commit -m "Update"\
	git push \


