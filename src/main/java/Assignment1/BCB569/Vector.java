package Assignment1.BCB569;

import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;
/**
 * 
 * @author Ashish Jain
 *
 */
public class Vector {
	
	private final double x;
    private final double y;
    private final double z;

    public Vector(double x, double y, double z) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
    }
    
	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getZ() {
		return z;
	}
	
	public double distance(Vector v) {
        final double dx = v.x - x;
        final double dy = v.y - y;
        final double dz = v.z - z;
        return FastMath.sqrt(dx * dx + dy * dy + dz * dz);
    }
	
	public double length() {
        return FastMath.sqrt(x * x + y * y + z * z);
    }
	
	public Vector subtract(Vector v) {
        return new Vector(x - v.x, y - v.y, z - v.z);
    }
	
	public Double[] toArray()
	{
		Double [] arr = {x,y,z};
		return arr;
	}
	
	public double dotProduct(Vector v) {
        return MathArrays.linearCombination(x, v.x, y, v.y, z, v.z);
    }
	
    public Vector crossProduct(Vector v) {
        return new Vector(MathArrays.linearCombination(y, v.z, -z, v.y),
                            MathArrays.linearCombination(z, v.x, -x, v.z),
                            MathArrays.linearCombination(x, v.y, -y, v.x));
    }
	
    public static double angle(Vector v1, Vector v2) {
    	double dot = v1.dotProduct(v2);
    	return Math.toDegrees(FastMath.acos(dot/(v1.length()*v2.length())));
    }
    
    public static double angle1(Vector v1, Vector v2) {

        double normProduct = v1.length() * v2.length();
        double dot = v1.dotProduct(v2);
        double threshold = normProduct * 0.9999;
        if ((dot < -threshold) || (dot > threshold)) {
            // the vectors are almost aligned, compute using the sine
            Vector v3 = v1.crossProduct(v2);
            if (dot >= 0) {
                return FastMath.asin(v3.length() / normProduct);
            }
            return FastMath.PI - FastMath.asin(v3.length() / normProduct);
        }

        // the vectors are sufficiently separated to use the cosine
        return FastMath.acos(dot / normProduct);

    }
    public static Vector multiplyVectorMatrix(Double [][] rotationMatrix,Double[] v)
    {
    	Vector finalVector;
    	int rows = rotationMatrix.length;
        int columns = rotationMatrix[0].length;

        double[] result = new double[rows];

        for (int row = 0; row < rows; row++) {
            double sum = 0;
            for (int column = 0; column < columns; column++) {
                sum += rotationMatrix[row][column]
                        * v[column];
            }
            result[row] = sum;
        }
        finalVector = new Vector(result[0], result[1], result[2]);
        return finalVector;
    }

    public static Double getTorsionalAngle(Vector p0,Vector p1,Vector p2,Vector p3)
    {
    	Vector v01 = p0.subtract(p1);
    	Vector v32 = p3.subtract(p2);
    	Vector v12 = p1.subtract(p2);
    	Vector v0 = v12.crossProduct(v01);
		Vector v3 = v12.crossProduct(v32);
		Double a = Vector.angle(v0,v3);
		if(v0.crossProduct(v3).dotProduct(v12) > 0)
		{
			a = -a;
		}
		return a;
    }
    
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getX() +" "+ getY()+" "+getZ();
	}
	
	
}
