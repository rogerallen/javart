public class vec3 {
    float[] e = new float[3];
    public vec3() { }
    public vec3(float e0, float e1, float e2) {
        e[0] = e0; e[1] = e1; e[2] = e2;
    }
    public float x() { return e[0]; }
    public float y() { return e[1]; }
    public float z() { return e[2]; }
    public float r() { return e[0]; }
    public float g() { return e[1]; }
    public float b() { return e[2]; }

    // no operator overloading for java.
    public vec3 neg() {
        return new vec3(-e[0], -e[1], -e[2]);
    }
    public vec3 add(vec3 v2) {
        return new vec3(e[0] + v2.e[0], e[1] + v2.e[1], e[2] + v2.e[2]);
    }
    public vec3 sub(vec3 v2) {
        return new vec3(e[0] - v2.e[0], e[1] - v2.e[1], e[2] - v2.e[2]);
    }
    public vec3 mul(vec3 v2) {
        return new vec3(e[0] * v2.e[0], e[1] * v2.e[1], e[2] * v2.e[2]);
    }
    public vec3 mul(float t) {
        return new vec3(e[0] * t, e[1] * t, e[2] * t);
    }
    public vec3 div(vec3 v2) {
        return new vec3(e[0] / v2.e[0], e[1] / v2.e[1], e[2] / v2.e[2]);
    }
    public vec3 div(float t) {
        return new vec3(e[0] / t, e[1] / t, e[2] / t);
    }
    public float length() {
        return (float)Math.sqrt(e[0]*e[0]+e[1]*e[1]+e[2]*e[2]);
    }
    public double squared_length() {
        return e[0]*e[0]+e[1]*e[1]+e[2]*e[2];
    }
    public void make_unit_vector() {
        float k = 1.0f/length();
        e[0] *= k; e[1] *= k; e[2] *= k;
    }
    public void set(vec3 v) {
        e[0] = v.e[0];
        e[1] = v.e[1];
        e[2] = v.e[2];
    }

    public static float dot(vec3 v1, vec3 v2) {
        return v1.e[0] * v2.e[0] + v1.e[1] * v2.e[1] + v1.e[2] * v2.e[2];
    }
    public static vec3 cross(vec3 v1, vec3 v2) {
        return new vec3( (v1.e[1]*v2.e[2] - v1.e[2]*v2.e[1]),
			-(v1.e[0]*v2.e[2] - v1.e[2]*v2.e[0]),
			 (v1.e[0]*v2.e[1] - v1.e[1]*v2.e[0]));
    }
    public static vec3 unit_vector(vec3 v){
        return v.div(v.length());
    }

    public static vec3 random_in_unit_sphere() {
        vec3 p;
        do {
            p = (new vec3((float)Math.random(),(float)Math.random(),(float)Math.random()))
                .mul(2.0f)
                .sub(new vec3(1.0f,1.0f,1.0f));
        } while (p.squared_length() >= 1.0f);
        return p;
    }

    public static vec3 reflect(vec3 v, vec3 n) {
        return v.sub(n.mul(2.0f*dot(v,n)));
    }
}
