package ImgCore;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class ImgMerge extends JFrame {

    private BufferedImage mergeImage;
    String merge;
    //File profile;

    public ImgMerge(String merge) {
        this.merge = merge;
        //this.profile = profile;
        //processImages();
    }
    public static void main(String[] args) {
        new ImgMerge("combine").processImages();
    }

    public void getImage()
    {
        try {
                ImageIcon myImage = new ImageIcon(new URL("https://cdn.discordapp.com/avatars/190194096072163328/0c72ade1b85b4faab01a1396ffca1034.png"));
                Image img = myImage.getImage();
                BufferedImage img2 = new BufferedImage(128, 128,
                        BufferedImage.TYPE_INT_RGB);
            Graphics g2 = img2.getGraphics();
                g2.drawImage(img,0,0,this);
                ImageIO.write(img2,"png",new File("C:/Users/Fabian Helfer/OneDrive/Dokumente/DiscordBot2/src/assets/imgs/avatar.png"));
            }
         catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     */
    private void processImages() {

        try {
            File path = new File("C:/Users/Fabian Helfer/OneDrive/Dokumente/DiscordBot2/src/assets/imgs/"); // base path of the images
            switch (merge) {
                case "merge":
                    ImageIcon imgUpper =
                            new ImageIcon(ImageIO.read(new File(path, "image.jpg")));
                    ImageIcon imgLower =
                            new ImageIcon(ImageIO.read(new File(path, "overlay.jpg")));

                    mergeImage =
                            new BufferedImage(
                                    imgUpper.getIconWidth(),
                                    imgUpper.getIconHeight() + imgLower.getIconHeight(),
                                    BufferedImage.TYPE_INT_RGB);

                    Graphics g = mergeImage.getGraphics();
                    g.drawImage(imgUpper.getImage(), 0, 0, this);
                    g.drawImage(imgLower.getImage(), 0, imgUpper.getIconHeight(), this);

                    ImageIO.write(mergeImage, "jpg", new File(path, "merged.jpg"));
                    break;

                case "combine":

                    //System.out.println(path.toString());
                    // load source images
                    BufferedImage image = ImageIO.read(new File(path, "cancer.png"));
                    ImageIcon myImage = new ImageIcon(new URL("https://cdn.discordapp.com/avatars/190194096072163328/0c72ade1b85b4faab01a1396ffca1034.png"));
                    Image img = myImage.getImage();

                    // create the new image, canvas size is the max. of both image sizes
                    int w = Math.max(image.getWidth(), img.getWidth(null));
                    int h = Math.max(image.getHeight(), img.getHeight(null));
                    BufferedImage combined = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

                    // paint both images, preserving the alpha channels
                    Graphics g2 = combined.getGraphics();
                    g2.drawImage(image, 0, 0, null);
                    g2.drawImage(img, 336, 191, null);

                    // Save as new image
                    ImageIO.write(combined, "png", new File(path, "combined.png"));
                    break;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}