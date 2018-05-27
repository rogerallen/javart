public class javart {

    static vec3 color(ray r, hitable_list world, int depth) {
        hit_record rec = new hit_record();
        if(world.hit(r, 0.001f, Float.MAX_VALUE, rec)) {
            ray scattered = new ray(new vec3(), new vec3());
            vec3 attenuation = new vec3();
            if (depth < 50 && rec.mat.scatter(r, rec, attenuation, scattered)) {
                return color(scattered, world, depth+1).mul(attenuation);
            }
            else {
                return new vec3(0.0f,0.0f,0.0f);
            }
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
            new sphere(new vec3(0.0f,0.0f,-1.0f), 0.5f,
                       new lambertian(new vec3(0.8f, 0.3f, 0.3f))),
            new sphere(new vec3(0.0f,-100.5f,-1.0f), 100.0f,
                       new lambertian(new vec3(0.8f, 0.8f, 0.0f))),
            new sphere(new vec3(1.0f,0.0f,-1.0f), 0.5f,
                       new metal(new vec3(0.8f, 0.6f, 0.2f), 1.0f)),
            new sphere(new vec3(-1.0f,0.0f,-1.0f), 0.5f,
                       new dielectric(1.5f))
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
                    vec3 new_col = color(r,world,0);
                    col = col.add(new_col);
                }
                col = col.div(ns);
                int ir = (int)(255.99*Math.sqrt(col.r()));
                int ig = (int)(255.99*Math.sqrt(col.g()));
                int ib = (int)(255.99*Math.sqrt(col.b()));
                System.out.println(String.format("%d %d %d",ir,ig,ib));
            }
        }
    }

}
