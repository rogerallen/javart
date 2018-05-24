javart.class: javart.java
	javac javart.java

out.ppm: javart.class
	java javart > out.ppm
