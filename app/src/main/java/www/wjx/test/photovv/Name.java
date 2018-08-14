package www.wjx.test.photovv;

import android.graphics.Matrix;

/**
 * Created by ckckck on 2018/8/14.
 *
 * life is short , bugs are too many !
 */

public class Name {
	private String name;
	private Matrix mMatrix;
	private int color;
//"matrix(1 0 0 1 2219.9082 776.9521)"
	public Name(String name, String matrix, int color) {
		this.name = name;
		mMatrix = string2Maxtri(matrix);
		this.color = color;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public Matrix string2Maxtri(String matrix){
		String matrixStr = matrix.substring(7, matrix.length() - 1);
		String[] split = matrixStr.split(" ");
		float[] floats=new float[9];
		floats[0]=Float.parseFloat(split[0]);
		floats[1]=Float.parseFloat(split[2]);
		floats[2]=Float.parseFloat(split[4]);
		floats[3]=Float.parseFloat(split[1]);
		floats[4]=Float.parseFloat(split[3]);
		floats[5]=Float.parseFloat(split[5]);
		floats[6]=0f;
		floats[7]=0f;
		floats[7]=1f;
		Matrix matrix1 = new Matrix();
		matrix1.setValues(floats);
		System.out.println(matrix1);
		return matrix1;
	}
	public Matrix getMatrix() {
		return mMatrix;
	}

	public void setMatrix(Matrix matrix) {
		mMatrix = matrix;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}
}
