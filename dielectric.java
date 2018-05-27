public class dielectric extends material {
    float ref_idx;
    public dielectric(float ri) { ref_idx = ri; }
    public boolean scatter(ray r_in, hit_record rec, vec3 attenuation, ray scattered) {
        vec3 outward_normal;
        float ni_over_nt;
        attenuation.set(new vec3(1.0f, 1.0f, 1.0f));
        float reflect_prob;
        float cosine;
        if(vec3.dot(r_in.direction(), rec.normal) > 0) {
            outward_normal = rec.normal.mul(-1);
            ni_over_nt = ref_idx;
            cosine = ref_idx * vec3.dot(r_in.direction(), rec.normal) / r_in.direction().length();
        }
        else {
            outward_normal = rec.normal;
            ni_over_nt = 1.0f/ref_idx;
            cosine = -vec3.dot(r_in.direction(), rec.normal) / r_in.direction().length();
        }
        vec3 reflected = vec3.reflect(r_in.direction(), rec.normal);
        vec3 refracted = new vec3();
        if(vec3.refract(r_in.direction(), outward_normal, ni_over_nt, refracted)) {
            reflect_prob = schlick(cosine, ref_idx);
        }
        else {
            reflect_prob = 1.0f;
        }
        if(Math.random() < reflect_prob) {
            scattered.set(new ray(rec.p, reflected));
        }
        else {
            scattered.set(new ray(rec.p, refracted));
        }
        return true;
    }
    float schlick(float cosine, float ref_idx) {
        float r0 = (1.0f-ref_idx) / (1.0f + ref_idx);
        r0 = r0*r0;
        return r0 + (1.0f-r0)*(float)Math.pow(1.0f-cosine,5.0f);
    }
}
