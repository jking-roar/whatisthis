package so.graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ManualCircle {
    public static void main(String[] args) {
        JFrame jFrame = new JFrame("Look");
        JComponent component = new JComponent() {
            @Override
            public void paint(Graphics g) {
                Graphics2D graphics = (Graphics2D) g;
                int width = getWidth();
                int height = getHeight();

                int[] pixels = new int[width * height];

                int radius = height / 6;
                for(int theta = 0; theta < 360; theta++){
                    double rads = Math.toRadians(theta);

                    int x = (int) ((width / 2) + (Math.cos(rads) * radius));
                    int y = (int) ((height / 2) + (Math.sin(rads) * radius));
                    pixels[(x + y * width)] = 0xFFFF00FF;
                }

                BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
                image.setRGB(0, 0, width, height, pixels, 0, width);

                graphics.drawImage(image, 0, 0, width, height, null);

            }
        };

        jFrame.add(component);
        jFrame.setSize(200, 200);
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jFrame.setVisible(true);
    }
}
