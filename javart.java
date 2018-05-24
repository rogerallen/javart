public class javart {

    static boolean hit_sphere(vec3 center, float radius, ray r) {
        vec3 oc = r.origin().sub(center);
        float a = vec3.dot(r.direction(), r.direction());
        float b = 2.0f * vec3.dot(oc, r.direction());
        float c = vec3.dot(oc, oc) - radius*radius;
        float discriminant = b*b - 4*a*c;
        return (discriminant > 0.0f);
    }

    static vec3 color(ray r) {
        if(hit_sphere(new vec3(0,0,-1), 0.5f, r)) {
            return new vec3(1,0,0);
        }
        vec3 unit_direction = vec3.unit_vector(r.direction());
        float t = 0.5f*(unit_direction.y() + 1.0f);
        vec3 tmp0 = new vec3(1.0f, 1.0f, 1.0f).mul(1.0f-t);
        vec3 tmp1 = new vec3(0.5f, 0.7f, 1.0f).mul(t);
        return tmp0.add(tmp1);
    }

    public static void main(String[] args) {
        int nx = 200;
        int ny = 100;
        System.out.println(String.format("P3\n%d %d\n255",nx,ny));
        vec3 lower_left_corner = new vec3(-2.0f, -1.0f, -1.0f);
        vec3 horizontal = new vec3(4.0f, 0.0f, 0.0f);
        vec3 vertical = new vec3(0.0f, 2.0f, 0.0f);
        vec3 origin = new vec3(0.0f, 0.0f, 0.0f);
        for(int j = ny-1; j >= 0; j--) {
            for(int i = 0; i < nx; i++) {
                float u = (float)i/(float)nx;
                float v = (float)j/(float)ny;
                vec3 pos = lower_left_corner.add(horizontal.mul(u)).add(vertical.mul(v));
                ray r = new ray(origin, pos);
                vec3 col = color(r);
                int ir = (int)(255.99*col.r());
                int ig = (int)(255.99*col.g());
                int ib = (int)(255.99*col.b());
                System.out.println(String.format("%d %d %d",ir,ig,ib));
            }
        }
    }

}
