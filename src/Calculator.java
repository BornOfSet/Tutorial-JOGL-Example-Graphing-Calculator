import java.nio.FloatBuffer;

public class Calculator {

	//程序初成时的测试数据
	public FloatBuffer Buffer() {
		float[] array = {0.2f,-0.5f, 0.2f, 0.2f, 0.9f, 0.2f};
		FloatBuffer p = FloatBuffer.wrap(array);
		return p;
	}
	
	double e = 2.718281828459045;
	
	private float Taylor(int level, float x0, float x) {
		double a = 1;
		double s = Math.exp(x0);
		for(int n=1;n<=level;n++) {
			a *= n;
			s += Math.exp(x0) / a * Math.pow(x-x0, n);
		}
		return (float)s;
	}
	
	int samples = 88;//采样点的数量
	float seg = 0.1f;//采样点的间隔
	float offset = -5;//采样点的偏移
	public FloatBuffer dBuffer() {
		//样本曲线e^x
		float[] array = new float[2 * samples];
		for(int i = 0;i< samples;i++) {
			array[2 * i] = offset + seg * i;
			array[2 * i+1] = (float)Math.exp(offset + seg * i);
		}
		FloatBuffer p = FloatBuffer.wrap(array);
		
		return p;
	}
	
	
	public FloatBuffer xBuffer() {
		//坐标系
		float[] array = {-100f,0f,100f,0f};
		FloatBuffer p = FloatBuffer.wrap(array);
		return p;
	}
	
	public FloatBuffer yBuffer() {
		//坐标系
		float[] array = {0f,-100f,0f,100f};
		FloatBuffer p = FloatBuffer.wrap(array);
		return p;
	}
	
	
	public FloatBuffer tBuffer() {
		//多项式
		float[] array = new float[2 * samples];
		for(int i = 0;i< samples;i++) {
			array[2 * i] = offset + seg * i;
			array[2 * i+1] = Taylor(32, 2, offset + seg * i);
		}
		FloatBuffer p = FloatBuffer.wrap(array);
		
		return p;
	}
	
}
