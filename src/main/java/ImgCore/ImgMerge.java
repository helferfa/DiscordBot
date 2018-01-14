package ImgCore;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class ImgMerge extends JFrame {

    private BufferedImage mergeImage;

    public ImgMerge() {

    }

    public static void main(String[] args) {
        new ImgMerge().processImages();
    }

    /**
     *
     */
    private void processImages() {
        try {
            ImageIcon imgUpper =
                    new ImageIcon(ImageIO.read(new File("c:/imgs/oben.jpg")));
            ImageIcon imgLower =
                    new ImageIcon(ImageIO.read(new File("c:/imgs/unten.jpg")));

            mergeImage =
                    new BufferedImage(
                            imgUpper.getIconWidth(),
                            imgUpper.getIconHeight() + imgLower.getIconHeight(),
                            BufferedImage.TYPE_INT_RGB);

            Graphics g = mergeImage.getGraphics();
            g.drawImage(imgUpper.getImage(), 0, 0, this);
            g.drawImage(imgLower.getImage(), 0, imgUpper.getIconHeight(), this);

            ImageIO.write(mergeImage, "jpg", new File("c:/imgs/Merge.jpg"));

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void paint(Graphics g) {
        if (mergeImage != null) {
            g.drawImage(mergeImage, 0, 0, this);
        }

    }
}