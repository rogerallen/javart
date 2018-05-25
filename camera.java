public class camera {
    vec3 origin;
    vec3 lower_left_corner;
    vec3 horizontal;
    vec3 vertical;
    public camera() {
        lower_left_corner = new vec3(-2.0f, -1.0f, -1.0f);
        horizontal = new vec3(4.0f, 0.0f, 0.0f);
        vertical = new vec3(0.0f, 2.0f, 0.0f);
        origin = new vec3(0.0f, 0.0f, 0.0f);
    }
    public ray get_ray(float u, float v) {
        vec3 pos = lower_left_corner.add(horizontal.mul(u)).add(vertical.mul(v));
        return new ray(origin, pos);
    }
}
