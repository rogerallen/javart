public class metal extends material {
    vec3 albedo;
    float fuzz;
    public metal(vec3 a, float f) {
        albedo = a;
        if (f < 1) {
            fuzz = f;
        } else {
            fuzz = 1.0f;
        }
    }
    public boolean scatter(ray r_in, hit_record rec, vec3 attenuation, ray scattered) {
        vec3 reflected = vec3.reflect(vec3.unit_vector(r_in.direction()), rec.normal);
        scattered.set(new ray(rec.p, reflected.add(vec3.random_in_unit_sphere().mul(fuzz))));
        attenuation.set(albedo);
        return (vec3.dot(scattered.direction(), rec.normal) > 0.0f);
    }
}
