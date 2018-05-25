SRCS := $(wildcard *.java)
CLSS := $(SRCS:%.java=%.class)

#DBG = -g
DBG =

out.ppm: $(CLSS)
	java javart > out.ppm

%.class: %.java
	javac $(DBG) $<

clean:
	rm -f $(CLSS) out.ppm
