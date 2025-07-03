
import javax.swing.JFrame;
import com.jogamp.opengl.awt.GLCanvas;

public class Main {

	public static void main(String[] args) {
		JFrame frame = new JFrame("简单画线器");
		frame.setSize(500, 500);
		GLCanvas canvas = new GLCanvas();

		canvas.addGLEventListener(new MainListener());
		frame.getContentPane().add(canvas);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
	}

}
