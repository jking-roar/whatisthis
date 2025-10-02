import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.UUID;

public class UuidToImage {

    public static final int IMAGE_SIZE = 600;

    public static void main(String[] args) {
        String uuid = UUID.randomUUID().toString();
        System.out.println(uuid);


        Image image = makeCreature(uuid);

        showImage(image);

    }

    private static void showImage(final Image image) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Canvas canvas = new Canvas() {
            @Override
            public void paint(Graphics g) {
                g.drawImage(image, 0, 0, this);
            }


        };
        canvas.setPreferredSize(new Dimension(IMAGE_SIZE, IMAGE_SIZE));
        frame.add(canvas);
        frame.pack();
        frame.setVisible(true);
    }

    private static Image makeCreature(String uuid) {
        //remove hyphens from uuid
        uuid = uuid.replace("-", "");



        BufferedImage bufferedImage = new BufferedImage(IMAGE_SIZE, IMAGE_SIZE, BufferedImage.TYPE_INT_ARGB);


        Graphics2D g2d = bufferedImage.createGraphics();

        // 1 2
        //draw body of creature based on first 6 characters of uuid as parameters
        g2d.setColor(new Color(Integer.parseInt(uuid.substring(0, 6), 16)));
        //oval body
        g2d.fillOval(0, 0, IMAGE_SIZE, IMAGE_SIZE);

//        g2d.setColor(Color.RED);
//        g2d.fillOval(0, 0, IMAGE_SIZE, IMAGE_SIZE);

        //3 4 5
        //draw arms of creature
        g2d.setColor(Color.BLACK);
        g2d.drawLine(0, (int) (0.5*IMAGE_SIZE), IMAGE_SIZE, (int) (0.5*IMAGE_SIZE));

        // 6 7
        //draw legs of creature
        g2d.drawLine((int) (0.5*IMAGE_SIZE), (int) (0.5*IMAGE_SIZE), (int) (0.5*IMAGE_SIZE), IMAGE_SIZE);

        // 8 9
        //draw head of creature
        g2d.setColor(Color.YELLOW);
        g2d.fillOval((int) (0.25*IMAGE_SIZE), 0, (int) (0.5*IMAGE_SIZE), (int) (0.5*IMAGE_SIZE));

        // 10 11 12
        // draw eyes of creature
        g2d.setColor(Color.BLACK);
        g2d.fillOval((int) (0.35*IMAGE_SIZE), (int) (0.1*IMAGE_SIZE), (int) (0.1*IMAGE_SIZE), (int) (0.1*IMAGE_SIZE));
        g2d.fillOval((int) (0.55*IMAGE_SIZE), (int) (0.1*IMAGE_SIZE), (int) (0.1*IMAGE_SIZE), (int) (0.1*IMAGE_SIZE));
        // draw a pupil looking in a random direction
        g2d.setColor(Color.WHITE);
        g2d.fillOval((int) (0.35*IMAGE_SIZE), (int) (0.1*IMAGE_SIZE), (int) (0.05*IMAGE_SIZE), (int) (0.05*IMAGE_SIZE));
        g2d.fillOval((int) (0.55*IMAGE_SIZE), (int) (0.1*IMAGE_SIZE), (int) (0.05*IMAGE_SIZE), (int) (0.05*IMAGE_SIZE));
        g2d.setColor(Color.BLACK);
        g2d.fillOval((int) (0.35*IMAGE_SIZE), (int) (0.1*IMAGE_SIZE), (int) (0.025*IMAGE_SIZE), (int) (0.025*IMAGE_SIZE));
        g2d.fillOval((int) (0.55*IMAGE_SIZE), (int) (0.1*IMAGE_SIZE), (int) (0.025*IMAGE_SIZE), (int) (0.025*IMAGE_SIZE));


        // 13 14
        //draw mouth of creature
        g2d.setColor(Color.BLACK);
        g2d.drawLine((int)(0.40*IMAGE_SIZE), (int)(0.30*IMAGE_SIZE), (int) (0.6*IMAGE_SIZE), (int)(0.30*IMAGE_SIZE));
        // draw teeth
        g2d.drawLine((int)(0.40*IMAGE_SIZE), (int)(0.30*IMAGE_SIZE), (int) (0.40*IMAGE_SIZE), (int)(0.40*IMAGE_SIZE));
        g2d.drawLine((int)(0.45*IMAGE_SIZE), (int)(0.30*IMAGE_SIZE), (int) (0.45*IMAGE_SIZE), (int)(0.40*IMAGE_SIZE));
        g2d.drawLine((int)(0.50*IMAGE_SIZE), (int)(0.30*IMAGE_SIZE), (int) (0.50*IMAGE_SIZE), (int)(0.40*IMAGE_SIZE));
        g2d.drawLine((int)(0.55*IMAGE_SIZE), (int)(0.30*IMAGE_SIZE), (int) (0.55*IMAGE_SIZE), (int)(0.40*IMAGE_SIZE));
        g2d.drawLine((int)(0.60*IMAGE_SIZE), (int)(0.30*IMAGE_SIZE), (int) (0.60*IMAGE_SIZE), (int)(0.40*IMAGE_SIZE));


        // draw a perfectly normal human nose
        g2d.drawLine((int)(0.50*IMAGE_SIZE), (int)(0.25*IMAGE_SIZE), (int) (0.50*IMAGE_SIZE), (int)(0.35*IMAGE_SIZE));
        g2d.drawLine((int)(0.50*IMAGE_SIZE), (int)(0.25*IMAGE_SIZE), (int) (0.45*IMAGE_SIZE), (int)(0.35*IMAGE_SIZE));
        g2d.drawLine((int)(0.50*IMAGE_SIZE), (int)(0.25*IMAGE_SIZE), (int) (0.55*IMAGE_SIZE), (int)(0.35*IMAGE_SIZE));
        // and nostrils
//        g2d.drawLine((int)(0.45*IMAGE_SIZE), (int)(0.35*IMAGE_SIZE), (int) (0.45*IMAGE_SIZE), (int)(0.40*IMAGE_SIZE));
//        g2d.drawLine((int)(0.55*IMAGE_SIZE), (int)(0.35*IMAGE_SIZE), (int) (0.55*IMAGE_SIZE), (int)(0.40*IMAGE_SIZE));



        //draw uuid of creature
        g2d.setColor(Color.BLACK);
        g2d.drawString(uuid, 0, (int)(0.90*IMAGE_SIZE));

        return bufferedImage;
    }
}
