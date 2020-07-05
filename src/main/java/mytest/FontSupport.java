package mytest;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

/*
		<dependency>
			<groupId>com.amazonaws</groupId>
			<artifactId>aws-lambda-java-core</artifactId>
			<version>1.2.1</version>
		</dependency>
		<dependency>
			<groupId>com.amazonaws</groupId>
			<artifactId>aws-lambda-java-events</artifactId>
			<version>3.1.0</version>
		</dependency>
 */
public class FontSupport implements RequestHandler<String, String> {
	@Override
	public String handleRequest(final String input, final Context context) {
		try {
			return "OK:" + test();
		} catch (Throwable e) {
			e.printStackTrace();
			return String.valueOf(e) + "\n" + ("SystemProperties: " + System.getProperties());
		}
	}

	private int test() {
		final BufferedImage canvas = new BufferedImage(5, 5, BufferedImage.TYPE_INT_RGB);
		final Graphics graphics = canvas.getGraphics();
		final Font font = new Font("Verdana", Font.PLAIN, 110);
		final FontMetrics metrics = graphics.getFontMetrics(font); // Crash!!
		final int maxAdvance = metrics.getMaxAdvance();
		System.out.println("MaxAdvance: " + metrics.getMaxAdvance());
		return maxAdvance;
	}
}