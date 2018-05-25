public class javart {

    static vec3 color(ray r, hitable_list world) {
        hit_record rec = new hit_record();
        if(world.hit(r, 0.0f, Float.MAX_VALUE, rec)) {
            return (new vec3(rec.normal.x()+1.0f,
                             rec.normal.y()+1.0f,
                             rec.normal.z()+1.0f)).mul(0.5f);
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
        int ns = 100;
        System.out.println(String.format("P3\n%d %d\n255",nx,ny));
        hitable[] list = {
            new sphere(new vec3(0.0f,0.0f,-1.0f), 0.5f),
            new sphere(new vec3(0.0f,-100.5f,-1.0f), 100.0f)
        };
        hitable_list world = new hitable_list(list);
        camera cam = new camera();
        for(int j = ny-1; j >= 0; j--) {
            for(int i = 0; i < nx; i++) {
                vec3 col = new vec3(0.0f, 0.0f, 0.0f);
                for (int s = 0; s < ns; s++) {
                    float u = (float)(i + Math.random())/(float)nx;
                    float v = (float)(j + Math.random())/(float)ny;
                    ray r = cam.get_ray(u, v);
                    col = col.add(color(r,world));
                }
                col = col.div(ns);
                int ir = (int)(255.99*col.r());
                int ig = (int)(255.99*col.g());
                int ib = (int)(255.99*col.b());
                System.out.println(String.format("%d %d %d",ir,ig,ib));
            }
        }
    }

}
