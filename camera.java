public class camera {
    vec3 origin;
    vec3 lower_left_corner;
    vec3 horizontal;
    vec3 vertical;
    public camera(vec3 lookfrom, vec3 lookat, vec3 vup, float vfov, float aspect) {
        vec3 u, v, w;
        float theta = vfov*(float)Math.PI/180.0f;
        float half_height = (float)Math.tan(theta/2.0f);
        float half_width = aspect * half_height;
        origin = lookfrom;
        w = vec3.unit_vector(lookfrom.sub(lookat));
        u = vec3.unit_vector(vec3.cross(vup, w));
        v = vec3.cross(w, u);
        lower_left_corner = new vec3(-half_width, -half_height, -1.0f);
        lower_left_corner = origin.sub(u.mul(half_width)).sub(v.mul(half_height)).sub(w);
        horizontal = u.mul(2.0f*half_width);
        vertical = v.mul(2.0f*half_height);
    }
    public ray get_ray(float s, float t) {
        vec3 pos = lower_left_corner.add(horizontal.mul(s)).add(vertical.mul(t)).sub(origin);
        return new ray(origin, pos);
    }
}
