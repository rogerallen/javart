public class hit_record {
    public float t;
    public vec3 p;
    public vec3 normal;
    public material mat;

    public void set(hit_record r) {
        t = r.t;
        p = r.p;
        normal = r.normal;
        mat = r.mat;
    }
}
