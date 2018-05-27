public class camera {
    vec3 origin;
    vec3 lower_left_corner;
    vec3 horizontal;
    vec3 vertical;
    public camera(float vfov, float aspect) {
        float theta = vfov*(float)Math.PI/180.0f;
        float half_height = (float)Math.tan(theta/2.0f);
        float half_width = aspect * half_height;
        lower_left_corner = new vec3(-half_width, -half_height, -1.0f);
        horizontal = new vec3(2.0f*half_width, 0.0f, 0.0f);
        vertical = new vec3(0.0f, 2.0f*half_height, 0.0f);
        origin = new vec3(0.0f, 0.0f, 0.0f);
    }
    public ray get_ray(float u, float v) {
        vec3 pos = lower_left_corner.add(horizontal.mul(u)).add(vertical.mul(v)).sub(origin);
        return new ray(origin, pos);
    }
}
