package audrey;

public class Enumerator {

	private static int pos = 1;

	/*
	 * Enumerator.print(Course_Type.class);
	 * System.out.println(Enumerator.nextEnum(Course_Type.class, in.nextInt()));
	 * System.out.println(Course_Type.CORE_ELECTIVE.toString());
	 */

	public static <E extends Enum<E>> E nextEnum(Class<E> obj, int value)
	{
		for (E constant : obj.getEnumConstants())
		{
			if ((value - pos) == constant.ordinal())
			{
				return constant;
			}
		}
		return null;
	}

	public static <E extends Enum<E>> void print(Class<E> obj)
	{
		int i = pos;
		for (E constant : obj.getEnumConstants())
		{
			System.out.println("\t" + i + ". " + string(constant));
			i++;
		}
	}
	
	public static <E extends Enum<E>> String string(E constant)
	{
		return constant.toString().replaceAll("_", " ");
	}
}
