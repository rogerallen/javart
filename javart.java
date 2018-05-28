import java.util.Random;

public class javart {

    static hitable_list random_scene() {
        // Let's be deterministically random for world creation
        Random rand = new Random(1984);
        int n = 500;
        hitable[] list = new hitable[500+1];
        list[0] = new sphere(new vec3(0.0f,-1000.0f, 0.0f), 1000.0f,
                             new lambertian(new vec3(0.5f, 0.5f, 0.5f)));
        int i = 1;
        for(int a = -11; a < 11; a++) {
            for(int b = -11; b < 11; b++) {
                float choose_mat = rand.nextFloat();
                vec3 center = new vec3((float)(a+0.9*rand.nextFloat()),
                                       0.2f,
                                       (float)(b+0.9*rand.nextFloat()));
                if(center.sub(new vec3(4.0f,0.2f,0.0f)).length() > 0.9f) {
                    if(choose_mat < 0.8f) {
                        list[i++] = new sphere(center, 0.2f,
                                               new lambertian(new vec3((float)(rand.nextFloat()*rand.nextFloat()),
                                                                       (float)(rand.nextFloat()*rand.nextFloat()),
                                                                       (float)(rand.nextFloat()*rand.nextFloat()))));
                    }
                    else if(choose_mat < 0.95f) {
                        list[i++] = new sphere(center, 0.2f,
                                               new metal(new vec3((float)(0.5*(1+rand.nextFloat())),
                                                                  (float)(0.5*(1+rand.nextFloat())),
                                                                  (float)(0.5*(1+rand.nextFloat()))),
                                                         (float)(0.5*rand.nextFloat())));
                    }
                    else {
                        list[i++] = new sphere(center, 0.2f, new dielectric(1.5f));
                    }
                }
            }
        }

        list[i++] = new sphere(new vec3( 0.0f, 1.0f, 0.0f), 1.0f, new dielectric(1.5f));
        list[i++] = new sphere(new vec3(-4.0f, 1.0f, 0.0f), 1.0f, new lambertian(new vec3(0.5f, 0.2f, 0.1f)));
        list[i++] = new sphere(new vec3( 4.0f, 1.0f, 0.0f), 1.0f, new metal(new vec3(0.7f, 0.6f, 0.5f), 0.0f));

        return new hitable_list(list, i);

    }

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
        int nx = 1200;
        int ny = 800;
        int ns = 20;
        System.out.println(String.format("P3\n%d %d\n255",nx,ny));
        float R = (float)Math.cos(Math.PI/4.0f);
        /*
        hitable[] list = {
            new sphere(new vec3(0.0f,0.0f,-1.0f), 0.5f,
                       new lambertian(new vec3(0.1f, 0.2f, 0.5f))),
            new sphere(new vec3(0.0f,-100.5f,-1.0f), 100.0f,
                       new lambertian(new vec3(0.8f, 0.8f, 0.0f))),
            new sphere(new vec3(1.0f,0.0f,-1.0f), 0.5f,
                       new metal(new vec3(0.8f, 0.6f, 0.2f), 1.0f)),
            new sphere(new vec3(-1.0f,0.0f,-1.0f), 0.5f,
                       new dielectric(1.5f)),
            new sphere(new vec3(-1.0f,0.0f,-1.0f), -0.45f,
                       new dielectric(1.5f))
        };
        hitable_list world = new hitable_list(list,list.length);
        */
        hitable_list world = random_scene();
        vec3 lookfrom = new vec3(13.0f, 2.0f, 3.0f);
        vec3 lookat = new vec3( 0.0f, 0.0f, 0.0f);
        float dist_to_focus = 10.0f; //(lookfrom.sub(lookat)).length();
        float aperture = 0.1f;
        camera cam = new camera(lookfrom, lookat, new vec3(0.0f, 1.0f, 0.0f),
                                20.0f, (float)nx/(float)ny,
                                aperture, dist_to_focus);
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
