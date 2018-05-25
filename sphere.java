public class sphere extends hitable {
    vec3 center = new vec3();
    float radius;
    public sphere() {}
    public sphere(vec3 cen, float r) { center = cen; radius = r; }
    public boolean hit(ray r, float t_min, float t_max, hit_record rec) {
        vec3 oc = r.origin().sub(center);
        float a = vec3.dot(r.direction(), r.direction());
        float b = vec3.dot(oc, r.direction());
        float c = vec3.dot(oc, oc) - radius*radius;
        float discriminant = b*b - a*c;
        if (discriminant > 0.0f) {
            float temp = (-b - (float)Math.sqrt(b*b-a*c))/a;
            if (temp < t_max && temp > t_min) {
                rec.t = temp;
                rec.p = r.point_at_paramter(rec.t);
                rec.normal = (rec.p.sub(center)).div(radius);
                return true;
            }
            temp = (-b + (float)Math.sqrt(b*b-a*c))/a;
            if (temp < t_max && temp > t_min) {
                rec.t = temp;
                rec.p = r.point_at_paramter(rec.t);
                rec.normal = (rec.p.sub(center)).div(radius);
                return true;
            }
        }
        return false;
    }
}
