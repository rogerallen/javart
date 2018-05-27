public class camera {
    vec3 origin;
    vec3 lower_left_corner;
    vec3 horizontal;
    vec3 vertical;
    vec3 u, v, w;
    float lens_radius;
    public camera(vec3 lookfrom, vec3 lookat, vec3 vup, float vfov, float aspect, float aperture, float focus_dist) {
        lens_radius = aperture / 2.0f;
        float theta = vfov*(float)Math.PI/180.0f;
        float half_height = (float)Math.tan(theta/2.0f);
        float half_width = aspect * half_height;
        origin = lookfrom;
        w = vec3.unit_vector(lookfrom.sub(lookat));
        u = vec3.unit_vector(vec3.cross(vup, w));
        v = vec3.cross(w, u);
        lower_left_corner = new vec3(-half_width, -half_height, -1.0f);
        lower_left_corner = origin.sub(u.mul(half_width*focus_dist))
            .sub(v.mul(half_height*focus_dist))
            .sub(w.mul(focus_dist));
        horizontal = u.mul(2.0f*half_width*focus_dist);
        vertical = v.mul(2.0f*half_height*focus_dist);
    }
    public ray get_ray(float s, float t) {
        vec3 rd = vec3.random_in_unit_disk().mul(lens_radius);
        vec3 offset = u.mul(rd.x()).add(v.mul(rd.y()));
        vec3 pos = lower_left_corner.add(horizontal.mul(s)).add(vertical.mul(t)).sub(origin).sub(offset);
        return new ray(origin.add(offset), pos);
    }
}
