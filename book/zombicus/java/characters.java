import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import sodium.*;

public class characters {
    public static void main(String[] args)
    {
        Animate.animate(
            "Zombicus characters",

(double t0, Cell<Double> clock, Stream<Unit> sTick,
                                Dimension windowSize) -> {
    World world = new World(windowSize);
    List<Cell<Character>> chars = new ArrayList<>();
    CellLoop<List<Character>> scene = new CellLoop<>();
    int id = 0;
    for (int x = 100; x < windowSize.width; x += 100)
        for (int y = 150; y < windowSize.height; y += 150) {
            Point pos0 = new Point(x, y);
            if (id != 3 && id != 6 && id != 7) {
                HomoSapiens h = new HomoSapiens(world, id, t0, pos0,
                    clock, sTick);
                chars.add(h.character);
            }
            else {
                HomoZombicus z = new HomoZombicus(id, t0, pos0,
                    clock, sTick, scene);
                chars.add(z.character);
            }
            id++;
        }
    Cell<List<Character>> scene_ = new Cell<>(
        new ArrayList<Character>());
    for (Cell<Character> c : chars) {
        scene_ = Cell.lift(
            (cc, l0) -> {
                List<Character> l = new ArrayList<Character>(l0);
                l.add(cc);
                return l;
            },
            c, scene_);
    }
    ArrayList<Character> emptyScene = new ArrayList<>();
    Cell<List<Character>> fixedScene = scene_.updates().hold(emptyScene);
    scene.loop(fixedScene);
    return scene;
}

        );
    }
}

