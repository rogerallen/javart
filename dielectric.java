public class dielectric extends material {
    float ref_idx;
    public dielectric(float ri) { ref_idx = ri; }
    public boolean scatter(ray r_in, hit_record rec, vec3 attenuation, ray scattered) {
        vec3 outward_normal;
        float ni_over_nt;
        attenuation.set(new vec3(1.0f, 1.0f, 1.0f));
        if(vec3.dot(r_in.direction(), rec.normal) > 0) {
            outward_normal = rec.normal.mul(-1);
            ni_over_nt = ref_idx;
        }
        else {
            outward_normal = rec.normal;
            ni_over_nt = 1.0f/ref_idx;
        }
        vec3 reflected = vec3.reflect(r_in.direction(), rec.normal);
        vec3 refracted = new vec3();
        if(vec3.refract(r_in.direction(), outward_normal, ni_over_nt, refracted)) {
            scattered.set(new ray(rec.p, refracted));
        }
        else {
            scattered.set(new ray(rec.p, reflected));
            return false;
        }
        return true;
    }
}
