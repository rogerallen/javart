public class lambertian extends material {
    vec3 albedo;
    public lambertian(vec3 a) { albedo = a; }
    public boolean scatter(ray r_in, hit_record rec, vec3 attenuation, ray scattered) {
        vec3 target = rec.p.add(rec.normal).add(vec3.random_in_unit_sphere());
        scattered.set(new ray(rec.p, target.sub(rec.p)));
        attenuation.set(albedo);
        return true;
    }
}
