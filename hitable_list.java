public class hitable_list extends hitable {
    int list_size; // have to allow for list_size < length
    hitable[] list;
    public hitable_list() {}
    public hitable_list(hitable[] l, int n)  { list = l; list_size = n; }
    public boolean hit(ray r, float t_min, float t_max, hit_record rec) {
        hit_record temp_rec = new hit_record();
        boolean hit_anything = false;
        float closest_so_far = t_max;
        for (int i = 0; i < list_size; i++) {
            if (list[i].hit(r, t_min, closest_so_far, temp_rec)) {
                hit_anything = true;
                closest_so_far = temp_rec.t;
                rec.set(temp_rec);
            }
        }
        return hit_anything;
    }
}
