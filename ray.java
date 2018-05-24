public class ray {
    vec3 A = new vec3();
    vec3 B = new vec3();
    public ray() {}
    public ray(vec3 a, vec3 b) { A = a; B = b; }
    public vec3 origin() { return A; }
    public vec3 direction() { return B; }
    public vec3 point_at_paramter(float t) { return A.add(B.mul(t)); }
}
