public class javart {

    public static void main(String[] args) {
        int nx = 200;
        int ny = 100;
        System.out.println("P3");
        System.out.println(String.format("%d %d 255",nx,ny));
        for(int j = ny-1; j >= 0; j--) {
            for(int i = 0; i < nx; i++) {
                float r = (float)i / (float)nx;
                float g = (float)j / (float)ny;
                float b = 0.2f;
                int ir = (int)(255.99*r);
                int ig = (int)(255.99*g);
                int ib = (int)(255.99*b);
                System.out.println(String.format("%d %d %d",ir,ig,ib));
            }
        }
    }

}
