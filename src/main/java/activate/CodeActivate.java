package activate;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import constant.Constants;
import constant.Globle;

public class CodeActivate extends HttpServlet {
    private static final long serialVersionUID = 1736370958785036518L;

    protected static String codeNumbers = "";

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        BufferedImage image = new BufferedImage(Constants.IMAGE_WIDTH, Constants.IMAGE_HEIGHT, BufferedImage.TYPE_INT_RGB);

        Graphics g = image.getGraphics();

        g.setColor(createRandomColor(200, 250));

        g.fillRect(0, 0, Constants.IMAGE_WIDTH, Constants.IMAGE_HEIGHT);

        for (int i = 0; i < 4; i++)
            drawString(g, i);

        drawNoise(g, 12);

        Globle.setCode(codeNumbers);

        codeNumbers = "";

        ServletOutputStream sos = response.getOutputStream();
        ImageIO.write(image, "GIF", sos);
        sos.close();
    }


    private void drawString(Graphics graphics, int i) {
        Random random = new Random();
        Integer j = random.nextInt((Constants.IMAGE_CHAR.length()));

        String number = Constants.IMAGE_CHAR.substring(j, j + 1);
        graphics.setFont(Constants.codeFont[i]);
        graphics.setColor(Constants.color[i]);

        graphics.drawString(number, 20 + i * 20, 30);

        codeNumbers += number;
    }


    private Color createRandomColor(int fc, int bc) {

        Random random = new Random();

        if (fc > 255)
            fc = 255;

        if (bc > 255)
            bc = 255;

        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }

    private void drawNoise(Graphics graphics, int lineNumber) {
        graphics.setColor(createRandomColor(160, 200));
        for (int i = 0; i < lineNumber; i++) {
            int pointX1 = 1 + (int) (Math.random() * 120);
            int pointY1 = 1 + (int) (Math.random() * 40);
            int pointX2 = 1 + (int) (Math.random() * 120);
            int pointY2 = 1 + (int) (Math.random() * 40);
            graphics.drawLine(pointX1, pointY1, pointX2, pointY2);
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
