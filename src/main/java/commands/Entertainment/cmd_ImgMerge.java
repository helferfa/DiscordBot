package commands.Entertainment;

import commands.Command;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

public class cmd_ImgMerge implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        File path = new File("C:/Users/Fabian Helfer/OneDrive/Dokumente/DiscordBot2/src/assets/imgs/");
        File url = new File(event.getAuthor().getAvatarUrl());
        System.out.println(url.toString() + " / " + event.getAuthor().getAvatarUrl());

        try {
            BufferedImage image = ImageIO.read(new File(path, "cancer.png"));
            BufferedImage overlay = ImageIO.read(new URL("https://cdn.discordapp.com/avatars/190194096072163328/0c72ade1b85b4faab01a1396ffca1034.png"));

            // create the new image, canvas size is the max. of both image sizes
            int w = Math.max(image.getWidth(), overlay.getWidth());
            int h = Math.max(image.getHeight(), overlay.getHeight());
            BufferedImage combined = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

            // paint both images, preserving the alpha channels
            Graphics g2 = combined.getGraphics();
            g2.drawImage(image, 0, 0, null);
            g2.drawImage(overlay, 336, 191, null);

            // Save as new image
            ImageIO.write(combined, "png", new File(path, "combined.png"));
            event.getTextChannel().sendFile(new File(path, "combined.png"));

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }

    @Override
    public String help() {
        return null;
    }
}
