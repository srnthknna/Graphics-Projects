//
//  shapes.java
//
//  Students should not be modifying this file.
//
//  @author 
//

public class shapes extends simpleShape {

	/**
	 * Object selection variables
	 */
	public static final int sphereNumber = 0;
	public static final int cylinderNumber = 1;
	public static final int cubeNumber = 2;
	public static final int coneNumber = 3;

	/**
	 * Shading selection variables
	 */
	public static final int SHADE_FLAT = 0;
	public static final int SHADE_NOT_FLAT = 1;

	/**
	 * Constructor
	 */
	public shapes() {

	}

	// cone variables
	float[] coneVertices = { 0.717901f, 0.080384f, -1.150212f, 0.912992f,
			0.080384f, -1.130997f, 1.100585f, 0.080384f, -1.074091f, 1.273472f,
			0.080384f, -0.981681f, 1.425008f, 0.080384f, -0.857319f, 1.549371f,
			0.080384f, -0.705782f, 1.641781f, 0.080384f, -0.532895f, 1.698687f,
			0.080384f, -0.345302f, 1.717901f, 0.080384f, -0.150212f, 0.717901f,
			2.080384f, -0.150212f, 1.698687f, 0.080384f, 0.044878f, 1.641781f,
			0.080384f, 0.232471f, 1.549371f, 0.080384f, 0.405358f, 1.425008f,
			0.080384f, 0.556895f, 1.273472f, 0.080384f, 0.681258f, 1.100585f,
			0.080384f, 0.773668f, 0.912992f, 0.080384f, 0.830573f, 0.717901f,
			0.080384f, 0.849788f, 0.522811f, 0.080384f, 0.830573f, 0.335218f,
			0.080384f, 0.773668f, 0.162331f, 0.080384f, 0.681257f, 0.010794f,
			0.080384f, 0.556895f, -0.113569f, 0.080384f, 0.405358f, -0.205978f,
			0.080384f, 0.232471f, -0.262884f, 0.080384f, 0.044878f, -0.282099f,
			0.080384f, -0.150213f, -0.262884f, 0.080384f, -0.345303f,
			-0.205978f, 0.080384f, -0.532896f, -0.113568f, 0.080384f,
			-0.705783f, 0.010796f, 0.080384f, -0.857319f, 0.162332f, 0.080384f,
			-0.981682f, 0.335219f, 0.080384f, -1.074092f, 0.522813f, 0.080384f,
			-1.130997f };
	float[] coneNormals = { -0.2599f, 0.4455f, -0.8567f, 0.0878f, 0.4455f,
			-0.891f, -0.422f, 0.4455f, -0.7896f, -0.568f, 0.4455f, -0.6921f,
			-0.6921f, 0.4455f, -0.568f, -0.7896f, 0.4455f, -0.422f, -0.8567f,
			0.4455f, -0.2599f, -0.891f, 0.4455f, -0.0878f, -0.891f, 0.4455f,
			0.0878f, -0.8567f, 0.4455f, 0.2599f, -0.7896f, 0.4455f, 0.422f,
			-0.6921f, 0.4455f, 0.568f, -0.568f, 0.4455f, 0.6921f, -0.422f,
			0.4455f, 0.7896f, -0.2599f, 0.4455f, 0.8567f, -0.0878f, 0.4455f,
			0.891f, 0.0878f, 0.4455f, 0.891f, 0.2599f, 0.4455f, 0.8567f,
			0.422f, 0.4455f, 0.7896f, 0.568f, 0.4455f, 0.6921f, 0.6921f,
			0.4455f, 0.568f, 0.7896f, 0.4455f, 0.422f, 0.8567f, 0.4455f,
			0.2599f, 0.891f, 0.4455f, 0.0878f, 0.891f, 0.4455f, -0.0878f,
			0.8567f, 0.4455f, -0.2599f, 0.7896f, 0.4455f, -0.422f, 0.6921f,
			0.4455f, -0.568f, 0.568f, 0.4455f, -0.6921f, 0.422f, 0.4455f,
			-0.7896f, -0.0878f, 0.4455f, -0.891f, 0.2599f, 0.4455f, -0.8567f,
			0.0f, -1.0f, 0.0f };
	int[] coneElements = { 31, 9, 32, 0, 9, 1, 30, 9, 31, 29, 9, 30, 28, 9, 29,
			27, 9, 28, 26, 9, 27, 25, 9, 26, 24, 9, 25, 23, 9, 24, 22, 9, 23,
			21, 9, 22, 20, 9, 21, 19, 9, 20, 18, 9, 19, 17, 9, 18, 16, 9, 17,
			15, 9, 16, 14, 9, 15, 13, 9, 14, 12, 9, 13, 11, 9, 12, 10, 9, 11,
			8, 9, 10, 7, 9, 8, 6, 9, 7, 5, 9, 6, 4, 9, 5, 3, 9, 4, 2, 9, 3, 32,
			9, 0, 1, 9, 2, 0, 8, 13, 29, 32, 0, 29, 30, 31, 27, 28, 29, 25, 26,
			27, 21, 24, 25, 21, 22, 23, 19, 20, 21, 17, 18, 19, 13, 16, 17, 13,
			14, 15, 11, 12, 13, 8, 10, 11, 4, 7, 8, 4, 5, 6, 2, 3, 4, 0, 1, 2,
			29, 31, 32, 0, 27, 29, 21, 23, 24, 25, 19, 21, 13, 15, 16, 8, 11,
			13, 4, 6, 7, 8, 2, 4, 25, 27, 0, 17, 19, 25, 0, 13, 17, 0, 2, 8,
			17, 25 };
	int[] coneNormalIndices = { 0, 0, 0, 1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4, 5,
			5, 5, 6, 6, 6, 7, 7, 7, 8, 8, 8, 9, 9, 9, 10, 10, 10, 11, 11, 11,
			12, 12, 12, 13, 13, 13, 14, 14, 14, 15, 15, 15, 16, 16, 16, 17, 17,
			17, 18, 18, 18, 19, 19, 19, 20, 20, 20, 21, 21, 21, 22, 22, 22, 23,
			23, 23, 24, 24, 24, 25, 25, 25, 26, 26, 26, 27, 27, 27, 28, 28, 28,
			29, 29, 29, 30, 30, 30, 31, 31, 31, 32, 32, 32, 32, 32, 32, 32, 32,
			32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32,
			32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32,
			32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32,
			32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32,
			32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32 };
	int[] coneTextureIndices = { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1 };
	// cube variables
	float[] cubeVertices = { 1.0f, -1.0f, -1.0f, 1.0f, -1.0f, 1.0f, -1.0f,
			-1.0f, 1.0f, -1.0f, -1.0f, -1.0f, 1.0f, 1.0f, -0.999999f,
			0.999999f, 1.0f, 1.000001f, -1.0f, 1.0f, 1.0f, -1.0f, 1.0f, -1.0f };
	float[] cubeNormals = { 0.0f, -1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 0.0f,
			0.0f, -0.0f, 0.0f, 1.0f, -1.0f, -0.0f, -0.0f, 0.0f, 0.0f, -1.0f };
	int[] cubeElements = { 1, 2, 3, 7, 6, 5, 4, 5, 1, 5, 6, 2, 2, 6, 7, 0, 3,
			7, 0, 1, 3, 4, 7, 5, 0, 4, 1, 1, 5, 2, 3, 2, 7, 4, 0 };
	int[] cubeNormalIndices = { 0, 0, 0, 1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4, 5,
			5, 5, 0, 0, 0, 1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4, 5, 5 };
	int[] cubeTextureIndices = { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1 };
	// cylinder variables
	float[] cylinderVertices = { 0.717901f, 0.080384f, -1.150212f, 0.717901f,
			2.080384f, -1.150212f, 0.912992f, 0.080384f, -1.130997f, 0.912992f,
			2.080384f, -1.130997f, 1.100585f, 0.080384f, -1.074091f, 1.100585f,
			2.080384f, -1.074091f, 1.273472f, 0.080384f, -0.981681f, 1.273472f,
			2.080384f, -0.981681f, 1.425008f, 0.080384f, -0.857319f, 1.425008f,
			2.080384f, -0.857319f, 1.549371f, 0.080384f, -0.705782f, 1.549371f,
			2.080384f, -0.705782f, 1.641781f, 0.080384f, -0.532895f, 1.641781f,
			2.080384f, -0.532895f, 1.698687f, 0.080384f, -0.345302f, 1.698687f,
			2.080384f, -0.345302f, 1.717901f, 0.080384f, -0.150212f, 1.717901f,
			2.080384f, -0.150212f, 1.698687f, 0.080384f, 0.044878f, 1.698687f,
			2.080384f, 0.044878f, 1.641781f, 0.080384f, 0.232471f, 1.641781f,
			2.080384f, 0.232471f, 1.549371f, 0.080384f, 0.405358f, 1.549371f,
			2.080384f, 0.405358f, 1.425008f, 0.080384f, 0.556895f, 1.425008f,
			2.080384f, 0.556895f, 1.273472f, 0.080384f, 0.681258f, 1.273472f,
			2.080384f, 0.681258f, 1.100585f, 0.080384f, 0.773668f, 1.100585f,
			2.080384f, 0.773668f, 0.912992f, 0.080384f, 0.830573f, 0.912992f,
			2.080384f, 0.830573f, 0.717901f, 0.080384f, 0.849788f, 0.717901f,
			2.080384f, 0.849788f, 0.522811f, 0.080384f, 0.830573f, 0.522811f,
			2.080384f, 0.830573f, 0.335218f, 0.080384f, 0.773668f, 0.335218f,
			2.080384f, 0.773668f, 0.162331f, 0.080384f, 0.681257f, 0.162331f,
			2.080384f, 0.681257f, 0.010794f, 0.080384f, 0.556895f, 0.010794f,
			2.080384f, 0.556895f, -0.113569f, 0.080384f, 0.405358f, -0.113569f,
			2.080384f, 0.405358f, -0.205978f, 0.080384f, 0.232471f, -0.205978f,
			2.080384f, 0.232471f, -0.262884f, 0.080384f, 0.044878f, -0.262884f,
			2.080384f, 0.044878f, -0.282099f, 0.080384f, -0.150213f,
			-0.282099f, 2.080384f, -0.150213f, -0.262884f, 0.080384f,
			-0.345303f, -0.262884f, 2.080384f, -0.345303f, -0.205978f,
			0.080384f, -0.532896f, -0.205978f, 2.080384f, -0.532896f,
			-0.113568f, 0.080384f, -0.705783f, -0.113568f, 2.080384f,
			-0.705783f, 0.010796f, 0.080384f, -0.857319f, 0.010796f, 2.080384f,
			-0.857319f, 0.162332f, 0.080384f, -0.981682f, 0.162332f, 2.080384f,
			-0.981682f, 0.335219f, 0.080384f, -1.074092f, 0.335219f, 2.080384f,
			-1.074092f, 0.522813f, 0.080384f, -1.130997f, 0.522813f, 2.080384f,
			-1.130997f };
	float[] cylinderNormals = { 0.098f, 0.0f, -0.9952f, 0.2903f, 0.0f,
			-0.9569f, 0.4714f, 0.0f, -0.8819f, 0.6344f, 0.0f, -0.773f, 0.773f,
			0.0f, -0.6344f, 0.8819f, 0.0f, -0.4714f, 0.9569f, 0.0f, -0.2903f,
			0.9952f, 0.0f, -0.098f, 0.9952f, 0.0f, 0.098f, 0.9569f, 0.0f,
			0.2903f, 0.8819f, 0.0f, 0.4714f, 0.773f, 0.0f, 0.6344f, 0.6344f,
			0.0f, 0.773f, 0.4714f, 0.0f, 0.8819f, 0.2903f, 0.0f, 0.9569f,
			0.098f, 0.0f, 0.9952f, -0.098f, 0.0f, 0.9952f, -0.2903f, 0.0f,
			0.9569f, -0.4714f, 0.0f, 0.8819f, -0.6344f, 0.0f, 0.773f, -0.773f,
			0.0f, 0.6344f, -0.8819f, 0.0f, 0.4714f, -0.9569f, 0.0f, 0.2903f,
			-0.9952f, 0.0f, 0.098f, -0.9952f, 0.0f, -0.098f, -0.9569f, 0.0f,
			-0.2903f, -0.8819f, 0.0f, -0.4714f, -0.773f, 0.0f, -0.6344f,
			-0.6344f, 0.0f, -0.773f, -0.4714f, 0.0f, -0.8819f, 0.0f, 1.0f,
			0.0f, -0.098f, 0.0f, -0.9952f, -0.2903f, 0.0f, -0.9569f, 0.0f,
			-1.0f, 0.0f };
	int[] cylinderElements = { 1, 3, 2, 3, 5, 4, 5, 7, 6, 7, 9, 8, 9, 11, 10,
			11, 13, 12, 13, 15, 14, 15, 17, 16, 17, 19, 18, 19, 21, 20, 21, 23,
			22, 23, 25, 24, 25, 27, 26, 27, 29, 28, 29, 31, 30, 31, 33, 32, 33,
			35, 34, 35, 37, 36, 37, 39, 38, 39, 41, 40, 41, 43, 42, 43, 45, 44,
			45, 47, 46, 47, 49, 48, 49, 51, 50, 51, 53, 52, 53, 55, 54, 55, 57,
			56, 57, 59, 58, 59, 61, 60, 3, 51, 35, 63, 1, 0, 61, 63, 62, 0, 16,
			24, 0, 1, 2, 2, 3, 4, 4, 5, 6, 6, 7, 8, 8, 9, 10, 10, 11, 12, 12,
			13, 14, 14, 15, 16, 16, 17, 18, 18, 19, 20, 20, 21, 22, 22, 23, 24,
			24, 25, 26, 26, 27, 28, 28, 29, 30, 30, 31, 32, 32, 33, 34, 34, 35,
			36, 36, 37, 38, 38, 39, 40, 40, 41, 42, 42, 43, 44, 44, 45, 46, 46,
			47, 48, 48, 49, 50, 50, 51, 52, 52, 53, 54, 54, 55, 56, 56, 57, 58,
			58, 59, 60, 11, 5, 3, 11, 9, 7, 15, 13, 11, 19, 17, 15, 23, 21, 19,
			27, 25, 23, 31, 29, 27, 35, 33, 31, 43, 37, 35, 43, 41, 39, 47, 45,
			43, 51, 49, 47, 59, 53, 51, 59, 57, 55, 63, 61, 59, 3, 1, 63, 11,
			7, 5, 3, 15, 11, 35, 23, 19, 35, 31, 27, 43, 39, 37, 51, 47, 43,
			59, 55, 53, 3, 63, 59, 19, 15, 3, 35, 27, 23, 51, 43, 35, 3, 59,
			51, 35, 19, 3, 62, 63, 0, 60, 61, 62, 56, 62, 0, 56, 58, 60, 52,
			54, 56, 48, 50, 52, 40, 46, 48, 40, 42, 44, 36, 38, 40, 32, 34, 36,
			24, 30, 32, 24, 26, 28, 20, 22, 24, 16, 18, 20, 8, 14, 16, 8, 10,
			12, 4, 6, 8, 0, 2, 4, 56, 60, 62, 0, 52, 56, 40, 44, 46, 48, 36,
			40, 24, 28, 30, 16, 20, 24, 8, 12, 14, 16, 4, 8, 48, 52, 0, 32, 36,
			48, 0, 24, 32, 0, 4, 16, 32, 48 };
	int[] cylinderNormalIndices = { 0, 0, 0, 1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4,
			4, 5, 5, 5, 6, 6, 6, 7, 7, 7, 8, 8, 8, 9, 9, 9, 10, 10, 10, 11, 11,
			11, 12, 12, 12, 13, 13, 13, 14, 14, 14, 15, 15, 15, 16, 16, 16, 17,
			17, 17, 18, 18, 18, 19, 19, 19, 20, 20, 20, 21, 21, 21, 22, 22, 22,
			23, 23, 23, 24, 24, 24, 25, 25, 25, 26, 26, 26, 27, 27, 27, 28, 28,
			28, 29, 29, 29, 30, 30, 30, 31, 31, 31, 32, 32, 32, 33, 33, 33, 0,
			0, 0, 1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4, 5, 5, 5, 6, 6, 6, 7, 7,
			7, 8, 8, 8, 9, 9, 9, 10, 10, 10, 11, 11, 11, 12, 12, 12, 13, 13,
			13, 14, 14, 14, 15, 15, 15, 16, 16, 16, 17, 17, 17, 18, 18, 18, 19,
			19, 19, 20, 20, 20, 21, 21, 21, 22, 22, 22, 23, 23, 23, 24, 24, 24,
			25, 25, 25, 26, 26, 26, 27, 27, 27, 28, 28, 28, 29, 29, 29, 30, 30,
			30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30,
			30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30,
			30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30,
			30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30,
			30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30,
			31, 31, 31, 32, 32, 32, 33, 33, 33, 33, 33, 33, 33, 33, 33, 33, 33,
			33, 33, 33, 33, 33, 33, 33, 33, 33, 33, 33, 33, 33, 33, 33, 33, 33,
			33, 33, 33, 33, 33, 33, 33, 33, 33, 33, 33, 33, 33, 33, 33, 33, 33,
			33, 33, 33, 33, 33, 33, 33, 33, 33, 33, 33, 33, 33, 33, 33, 33, 33,
			33, 33, 33, 33, 33, 33, 33, 33, 33, 33, 33, 33, 33, 33, 33, 33, 33,
			33, 33, 33, 33, 33, 33, 33 };
	int[] cylinderTextureIndices = { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1 };

	/**
	 * method for creating sphere
	 * 
	 * @param shadingType
	 *            -for shading type
	 */
	public void makesphere(int shadingType)

	{
		float radius = 1f;
		int slices = 20;
		int stacks = 20;
		if (slices < 3)
			slices = 3;

		if (stacks < 3)
			stacks = 3;
		// loops to draw sphere
		for (int i = 0; i < stacks; i++) {
			// angle from center to the longitudes
			float angle = (float) Math.toRadians(((float) i / stacks) * 180);
			float angle1 = (float) Math
					.toRadians(((float) (i + 1) / stacks) * 180);
			for (int j = 0; j < slices; j++) {
				// angle from center to the latitudes
				float angle2 = (float) Math
						.toRadians(((float) j / slices) * (360));
				float angle3 = (float) Math
						.toRadians(((float) (j + 1) / slices) * (360));
				// two points on the first loop along the sphere
				// ux vx variables for texture mapping
				float pointx = (float) (radius * (Math.sin(angle)) * (Math
						.cos(angle2)));
				float pointy = (float) (radius * (Math.cos(angle)));
				float pointz = (float) (radius * (Math.sin(angle)) * (Math
						.sin(angle2)));
				float ux = (float) ((float) (Math.asin(pointx) / Math.PI) + 0.5);
				float vx = (float) (-(float) (Math.asin(pointy) / Math.PI) + 0.5);

				float pointx1 = (float) (radius * (Math.sin(angle)) * (Math
						.cos(angle3)));
				float pointy1 = (float) (radius * (Math.cos(angle)));
				float pointz1 = (float) (radius * (Math.sin(angle)) * (Math
						.sin(angle3)));
				float ux1 = (float) ((float) (Math.asin(pointx1) / Math.PI) + 0.5);
				float vx1 = (float) (-(float) (Math.asin(pointy1) / Math.PI) + 0.5);
				// two points on the second loop along the sphere
				float pointx2 = (float) (radius * (Math.sin(angle1)) * (Math
						.cos(angle2)));
				float pointy2 = (float) (radius * (Math.cos(angle1)));
				float pointz2 = (float) (radius * (Math.sin(angle1)) * (Math
						.sin(angle2)));
				float ux2 = (float) ((float) (Math.asin(pointx2) / Math.PI) + 0.5);
				float vx2 = (float) (-(float) (Math.asin(pointy2) / Math.PI) + 0.5);

				float pointx3 = (float) (radius * (Math.sin(angle1)) * (Math
						.cos(angle3)));
				float pointy3 = (float) (radius * (Math.cos(angle1)));
				float pointz3 = (float) (radius * (Math.sin(angle1)) * (Math
						.sin(angle3)));
				float ux3 = (float) ((float) (Math.asin(pointx3) / Math.PI) + 0.5);
				float vx3 = (float) (-(float) (Math.asin(pointy3) / Math.PI) + 0.5);
				// add triangles to draw the sphere
				addTriangleTexture(pointx, pointy, pointz, ux, vx, pointx2,
						pointy2, pointz2, ux2, vx2, pointx1, pointy1, pointz1,
						ux1, vx1);
				addTriangleTexture(pointx1, pointy1, pointz1, ux1, vx1,
						pointx2, pointy2, pointz2, ux2, vx2, pointx3, pointy3,
						pointz3, ux3, vx3);
			}
		}

	}

	/**
	 * method for creating cube
	 * 
	 * @param shadingType
	 *            -for shading type
	 */
	private void makecube(int shadingType) {

		if (shadingType == shapes.SHADE_FLAT) {

			for (int i = 0; i < cubeElements.length - 2; i += 3) {

				// calculate the base indices of the three vertices
				int point1 = 3 * cubeElements[i]; // slots 0, 1, 2
				int point2 = 3 * cubeElements[i + 1]; // slots 3, 4, 5
				int point3 = 3 * cubeElements[i + 2]; // slots 6, 7, 8

				addTriangle(cubeVertices[point1 + 0], cubeVertices[point1 + 1],
						cubeVertices[point1 + 2], cubeVertices[point2 + 0],
						cubeVertices[point2 + 1], cubeVertices[point2 + 2],
						cubeVertices[point3 + 0], cubeVertices[point3 + 1],
						cubeVertices[point3 + 2]);
			}

		} else {

			for (int i = 0; i < cubeElements.length - 2; i += 3) {

				// calculate the base indices of the three vertices
				int point1 = 3 * cubeElements[i]; // slots 0, 1, 2
				int point2 = 3 * cubeElements[i + 1]; // slots 3, 4, 5
				int point3 = 3 * cubeElements[i + 2]; // slots 6, 7, 8

				int normal1 = 3 * cubeNormalIndices[i];
				int normal2 = 3 * cubeNormalIndices[i + 1];
				int normal3 = 3 * cubeNormalIndices[i + 2];

				addTriangleWithNorms(cubeVertices[point1 + 0],
						cubeVertices[point1 + 1], cubeVertices[point1 + 2],
						cubeVertices[point2 + 0], cubeVertices[point2 + 1],
						cubeVertices[point2 + 2], cubeVertices[point3 + 0],
						cubeVertices[point3 + 1], cubeVertices[point3 + 2],

						cubeNormals[normal1 + 0], cubeNormals[normal1 + 1],
						cubeNormals[normal1 + 2], cubeNormals[normal2 + 0],
						cubeNormals[normal2 + 1], cubeNormals[normal2 + 2],
						cubeNormals[normal3 + 0], cubeNormals[normal3 + 1],
						cubeNormals[normal3 + 2]);

			}
		}
	}

	/**
	 * method for creating cylinder
	 * 
	 * @param shadingType
	 *            -for shading type
	 */
	private void makecylinder(int shadingType) {

		if (shadingType == shapes.SHADE_FLAT) {

			for (int i = 0; i < cylinderElements.length - 2; i += 3) {

				// calculate the base indices of the three vertices
				int point1 = 3 * cylinderElements[i]; // slots 0, 1, 2
				int point2 = 3 * cylinderElements[i + 1]; // slots 3, 4, 5
				int point3 = 3 * cylinderElements[i + 2]; // slots 6, 7, 8

				addTriangle(cylinderVertices[point1 + 0],
						cylinderVertices[point1 + 1],
						cylinderVertices[point1 + 2],
						cylinderVertices[point2 + 0],
						cylinderVertices[point2 + 1],
						cylinderVertices[point2 + 2],
						cylinderVertices[point3 + 0],
						cylinderVertices[point3 + 1],
						cylinderVertices[point3 + 2]);
			}

		} else {
			for (int i = 0; i < cylinderElements.length - 2; i += 3) {

				// calculate the base indices of the three vertices
				int point1 = 3 * cylinderElements[i]; // slots 0, 1, 2
				int point2 = 3 * cylinderElements[i + 1]; // slots 3, 4, 5
				int point3 = 3 * cylinderElements[i + 2]; // slots 6, 7, 8

				int normal1 = 3 * cylinderNormalIndices[i];
				int normal2 = 3 * cylinderNormalIndices[i + 1];
				int normal3 = 3 * cylinderNormalIndices[i + 2];

				addTriangleWithNorms(cylinderVertices[point1 + 0],
						cylinderVertices[point1 + 1],
						cylinderVertices[point1 + 2],
						cylinderVertices[point2 + 0],
						cylinderVertices[point2 + 1],
						cylinderVertices[point2 + 2],
						cylinderVertices[point3 + 0],
						cylinderVertices[point3 + 1],
						cylinderVertices[point3 + 2],

						cylinderNormals[normal1 + 0],
						cylinderNormals[normal1 + 1],
						cylinderNormals[normal1 + 2],
						cylinderNormals[normal2 + 0],
						cylinderNormals[normal2 + 1],
						cylinderNormals[normal2 + 2],
						cylinderNormals[normal3 + 0],
						cylinderNormals[normal3 + 1],
						cylinderNormals[normal3 + 2]);
			}
		}
	}

	/**
	 * method for creating cone
	 * 
	 * @param shadingType
	 *            -for shading type
	 */
	private void makecone(int shadingType) {

		if (shadingType == shapes.SHADE_FLAT) {

			for (int i = 0; i < coneElements.length - 2; i += 3) {

				// calculate the base indices of the three vertices
				int point1 = 3 * coneElements[i]; // slots 0, 1, 2
				int point2 = 3 * coneElements[i + 1]; // slots 3, 4, 5
				int point3 = 3 * coneElements[i + 2]; // slots 6, 7, 8

				addTriangle(coneVertices[point1 + 0], coneVertices[point1 + 1],
						coneVertices[point1 + 2], coneVertices[point2 + 0],
						coneVertices[point2 + 1], coneVertices[point2 + 2],
						coneVertices[point3 + 0], coneVertices[point3 + 1],
						coneVertices[point3 + 2]);
			}

		} else {
			for (int i = 0; i < coneElements.length - 2; i += 3) {

				// calculate the base indices of the three vertices
				int point1 = 3 * coneElements[i]; // slots 0, 1, 2
				int point2 = 3 * coneElements[i + 1]; // slots 3, 4, 5
				int point3 = 3 * coneElements[i + 2]; // slots 6, 7, 8

				int normal1 = 3 * coneNormalIndices[i];
				int normal2 = 3 * coneNormalIndices[i + 1];
				int normal3 = 3 * coneNormalIndices[i + 2];

				addTriangleWithNorms(coneVertices[point1 + 0],
						coneVertices[point1 + 1], coneVertices[point1 + 2],
						coneVertices[point2 + 0], coneVertices[point2 + 1],
						coneVertices[point2 + 2], coneVertices[point3 + 0],
						coneVertices[point3 + 1], coneVertices[point3 + 2],

						coneNormals[normal1 + 0], coneNormals[normal1 + 1],
						coneNormals[normal1 + 2], coneNormals[normal2 + 0],
						coneNormals[normal2 + 1], coneNormals[normal2 + 2],
						coneNormals[normal3 + 0], coneNormals[normal3 + 1],
						coneNormals[normal3 + 2]);
			}
		}
	}

	/**
	 * method for creating shapes
	 * 
	 * @param choice
	 *            -object code
	 * @param shadingType
	 *            -shading type
	 */
	public void makeShape(int choice, int shadingType) {
		if (choice == shapes.sphereNumber)
			makesphere(shadingType);
		else if (choice == shapes.cylinderNumber)
			makecylinder(shadingType);
		else if (choice == shapes.cubeNumber)
			makecube(shadingType);
		else
			makecone(shadingType);
	}
}
