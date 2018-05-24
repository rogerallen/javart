SRCS := $(wildcard *.java)
CLSS := $(SRCS:%.java=%.class)

out.ppm: $(CLSS)
	java javart > out.ppm

%.class: %.java
	javac $<

clean:
	rm -f $(CLSS) out.ppm
