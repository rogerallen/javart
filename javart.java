public class javart {

    public static void main(String[] args) {
        int nx = 200;
        int ny = 100;
        System.out.println(String.format("P3\n%d %d\n255",nx,ny));
        for(int j = ny-1; j >= 0; j--) {
            for(int i = 0; i < nx; i++) {
                vec3 col = new vec3((float)i/(float)nx, (float)j/(float)ny, 0.2f);
                int ir = (int)(255.99*col.r());
                int ig = (int)(255.99*col.g());
                int ib = (int)(255.99*col.b());
                System.out.println(String.format("%d %d %d",ir,ig,ib));
            }
        }
    }

}
