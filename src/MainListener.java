import java.nio.IntBuffer;

import com.jogamp.opengl.GL3;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;

public class MainListener implements GLEventListener {
	
	IntBuffer vao = IntBuffer.allocate(4);
	IntBuffer vbo = IntBuffer.allocate(4);
	Calculator cal;
	int shadingProgram0 = 0;
	int shadingProgram1 = 1;
	
	public MainListener() {
		cal = new Calculator();
	}
	
	@Override
	public void display(GLAutoDrawable arg0) {
		GL3 gl = arg0.getGL().getGL3();
		gl.glUseProgram(shadingProgram0);
		gl.glBindVertexArray(vao.get(0));
		gl.glDrawArrays(GL3.GL_LINE_STRIP, 0, cal.dBuffer().capacity()/2);
		gl.glBindVertexArray(vao.get(1));
		gl.glDrawArrays(GL3.GL_LINE_STRIP, 0, cal.xBuffer().capacity()/2);
		gl.glBindVertexArray(vao.get(2));
		gl.glDrawArrays(GL3.GL_LINE_STRIP, 0, cal.yBuffer().capacity()/2);
		gl.glUseProgram(shadingProgram1);
		gl.glBindVertexArray(vao.get(3));
		gl.glDrawArrays(GL3.GL_LINE_STRIP, 0, cal.tBuffer().capacity()/2);
	}

	@Override
	public void dispose(GLAutoDrawable arg0) {
	}

	@Override
	public void init(GLAutoDrawable arg0) {
		GL3 gl = arg0.getGL().getGL3();
		gl.glGenVertexArrays(4, vao);
		gl.glGenBuffers(4, vbo);
		
		//样本曲线e^x
		gl.glBindVertexArray(vao.get(0));
		gl.glBindBuffer(GL3.GL_ARRAY_BUFFER, vbo.get(0));
		gl.glBufferData(GL3.GL_ARRAY_BUFFER, cal.dBuffer().capacity()*Float.BYTES, cal.dBuffer(), GL3.GL_STATIC_DRAW);
		gl.glEnableVertexAttribArray(0);
		gl.glVertexAttribPointer(0, 2, GL3.GL_FLOAT, false, Float.BYTES*2, 0);
		
		//坐标系x
		gl.glBindVertexArray(vao.get(1));
		gl.glBindBuffer(GL3.GL_ARRAY_BUFFER, vbo.get(1));
		gl.glBufferData(GL3.GL_ARRAY_BUFFER, cal.xBuffer().capacity()*Float.BYTES, cal.xBuffer(), GL3.GL_STATIC_DRAW);
		gl.glEnableVertexAttribArray(0);
		gl.glVertexAttribPointer(0, 2, GL3.GL_FLOAT, false, Float.BYTES*2, 0);
		
		//坐标系y
		gl.glBindVertexArray(vao.get(2));
		gl.glBindBuffer(GL3.GL_ARRAY_BUFFER, vbo.get(2));
		gl.glBufferData(GL3.GL_ARRAY_BUFFER, cal.yBuffer().capacity()*Float.BYTES, cal.yBuffer(), GL3.GL_STATIC_DRAW);
		gl.glEnableVertexAttribArray(0);
		gl.glVertexAttribPointer(0, 2, GL3.GL_FLOAT, false, Float.BYTES*2, 0);
		
		//多项式
		gl.glBindVertexArray(vao.get(3));
		gl.glBindBuffer(GL3.GL_ARRAY_BUFFER, vbo.get(3));
		gl.glBufferData(GL3.GL_ARRAY_BUFFER, cal.tBuffer().capacity()*Float.BYTES, cal.tBuffer(), GL3.GL_STATIC_DRAW);
		gl.glEnableVertexAttribArray(0);
		gl.glVertexAttribPointer(0, 2, GL3.GL_FLOAT, false, Float.BYTES*2, 0);
		
		
		gl.glLineWidth(1);
		shadingProgram0 = gl.glCreateProgram();
		ShaderLoader.SetPipeline(gl,shadingProgram0,"SimpleVS.vs", "SimplePS.fs");
		
		shadingProgram1 = gl.glCreateProgram();
		ShaderLoader.SetPipeline(gl,shadingProgram1,"SimpleVS.vs", "SimplePS_r.fs");
	}

	@Override
	public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3, int arg4) {
	}

}
