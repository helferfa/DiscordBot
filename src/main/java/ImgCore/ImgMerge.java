package ImgCore;

import api.LeagueApi;
import com.cathive.fonts.roboto.RobotoFonts;
import org.json.simple.JSONObject;

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
    }


    public static void main(String[] args) {

        try {
            /**
             *
             * Creates the League Picture
             */
            ImgMerge m = new ImgMerge("combine");

            LeagueApi lol = new LeagueApi(null);
            String[] data = lol.getDataFromGame("SchwarzesPhantom");
            String[] champs = new String[10];
            String[] names = new String[10];
            for (int i = 0; i<10; i++) {
                names[i] = data[i];
            }
            for (int i = 10; i<20; i++) {
                champs[i-10] = data[i];
            }
            m.getChampionSkins(champs);
            m.createFooter(names);
            m.createLeague();

            //m.createFooter();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void createFooter() throws IOException, FontFormatException {
        File path = new File("C:/Users/Fabian Helfer/OneDrive/Dokumente/DiscordBot2/src/assets/imgs/");
        BufferedImage footer = new BufferedImage(
                308, 100,
                BufferedImage.TYPE_INT_RGB);
        Graphics g = footer.getGraphics();
        ImageIcon img = new ImageIcon(ImageIO.read(new File(path, "7.png")));

        g.drawRect(0, 0, 307, 99);

        g.drawImage(img.getImage(), 5, 5, 90, 90, this);


        g.setFont(Font.createFont(Font.TRUETYPE_FONT, new File("C:/Users/Fabian Helfer/OneDrive/Dokumente/DiscordBot2/src/assets/fonts/roboto/Roboto-Regular.ttf")).deriveFont(12f));
        g.drawString("Summoner freshddumb", 100, 50);
        ImageIO.write(footer, "jpg", new File(path, "1.jpg"));
    }

    /**
     * Not Interactive Method of loading a specific Picture
     */
    public void getImage() {
        try {
            ImageIcon myImage = new ImageIcon(new URL("https://cdn.discordapp.com/avatars/190194096072163328/0c72ade1b85b4faab01a1396ffca1034.png"));
            Image img = myImage.getImage();
            BufferedImage img2 = new BufferedImage(128, 128,
                    BufferedImage.TYPE_INT_RGB);
            Graphics g2 = img2.getGraphics();
            g2.drawImage(img, 0, 0, this);
            ImageIO.write(img2, "png", new File("C:/Users/Fabian Helfer/OneDrive/Dokumente/DiscordBot2/src/assets/imgs/avatar.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Saves all Champs from the champ Array for creating the whole Image
     *
     * @param champs
     */
    public void getChampionSkins(String[] champs) {
        try {
            for (int i = 0; i < 10; i++) {
                ImageIcon myImage = new ImageIcon(new URL("http://ddragon.leagueoflegends.com/cdn/img/champion/loading/" + champs[i] + "_0.jpg"));
                System.out.println("http://ddragon.leagueoflegends.com/cdn/img/champion/loading/" + champs[i] + "_0.jpg");
                Image img = myImage.getImage();
                BufferedImage img2 = new BufferedImage(308, 560,
                        BufferedImage.TYPE_INT_RGB);
                Graphics g2 = img2.getGraphics();
                g2.drawImage(img, 0, 0, this);
                ImageIO.write(img2, "jpg", new File("C:/Users/Fabian Helfer/OneDrive/Dokumente/DiscordBot2/src/assets/imgs/" + i + ".jpg"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates an Image in given FilePath that contains all five Chmapions that are saved as 1...9.jgp in Path
     *
     * @throws IOException
     */
    public void createLeague() throws IOException {
        File path = new File("C:/Users/Fabian Helfer/OneDrive/Dokumente/DiscordBot2/src/assets/imgs/");
        ImageIcon[] images = new ImageIcon[10];
        for (int i = 0; i < 10; i++) {
            images[i] = new ImageIcon(ImageIO.read(new File(path, "" + i + ".jpg")));
        }
        mergeImage =
                new BufferedImage(
                        1540, 1320,
                        BufferedImage.TYPE_INT_RGB);
        Graphics g = mergeImage.getGraphics();
        for (int i = 0; i < 10; i++) {
            if (i < 5) {
                g.drawImage(images[i].getImage(), i * 308, 0, this);
            } else {
                g.drawImage(images[i].getImage(), (i - 5) * 308, 660, this);
            }
        }
        ImageIcon[] footerimages = new ImageIcon[10];
        for (int i = 0; i < 10; i++) {
            footerimages[i] = new ImageIcon(ImageIO.read(new File(path, "footer" + i + ".jpg")));
        }

        for (int i = 0; i < 10; i++) {
            if (i < 5) {
                g.drawImage(footerimages[i].getImage(), i * 308, 560, this);
            } else {
                g.drawImage(footerimages[i].getImage(), (i - 5) * 308, 1220, this);
            }
        }

        ImageIO.write(mergeImage, "jpg", new File(path, "merged.jpg"));
    }

    public void createFooter(String[] infos) throws Exception {
        File path = new File("C:/Users/Fabian Helfer/OneDrive/Dokumente/DiscordBot2/src/assets/imgs/");
        saveProfilePic(infos);
        for (int i = 0; i < 10; i++) {

            BufferedImage footer = new BufferedImage(
                    308, 100,
                    BufferedImage.TYPE_INT_RGB);
            Graphics g = footer.getGraphics();
            ImageIcon img = new ImageIcon(ImageIO.read(new File(path, "profile" + i + ".png")));

            g.drawRect(0, 0, 307, 99);

            g.drawImage(img.getImage(), 5, 5, 90, 90, this);


            g.setFont(Font.createFont(Font.TRUETYPE_FONT, new File("C:/Users/Fabian Helfer/OneDrive/Dokumente/DiscordBot2/src/assets/fonts/roboto/Roboto-Medium.ttf")).deriveFont(15f));
            g.drawString("Summoner " + infos[i], 100, 50);
            ImageIO.write(footer, "jpg", new File(path, "footer" + i + ".jpg"));
        }
    }

    public void saveProfilePic(String[] participants) throws Exception {
        File path = new File("C:/Users/Fabian Helfer/OneDrive/Dokumente/DiscordBot2/src/assets/imgs/");
        for (int i = 0; i < 10; i++) {
            BufferedImage footer = new BufferedImage(
                    90, 90,
                    BufferedImage.TYPE_INT_RGB);
            Graphics g = footer.getGraphics();
            ImageIcon img = new ImageIcon(ImageIO.read(new URL("http://ddragon.leagueoflegends.com/cdn/6.24.1/img/profileicon/" + new LeagueApi(null).getProfileIconID(participants[i]) + ".png")));
            g.drawImage(img.getImage(), 0, 0, 90, 90, this);
            ImageIO.write(footer, "png", new File(path, "profile" + i + ".png"));
        }

    }

    /**
     * Processing Images whether its combining them (upper/lower) or merging them
     */
    public void processImages() {

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