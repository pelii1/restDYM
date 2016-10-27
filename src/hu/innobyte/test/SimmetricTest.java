package hu.innobyte.test;

import static org.simmetrics.builders.StringMetricBuilder.with;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.simmetrics.StringMetric;
import org.simmetrics.metrics.JaroWinkler;
import org.simmetrics.metrics.StringMetrics;
import org.simmetrics.simplifiers.Simplifiers;

public class SimmetricTest {
    public static void main(String[] args)
	    throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
	for (Method method : StringMetrics.class.getMethods()) {
	    if (method.getReturnType().equals(StringMetric.class) && method.getParameterCount() == 0) {

		System.out.print(method.getName());
		StringMetric metric = (StringMetric) method.invoke(null);
		try {
		    System.out.print("    " + metric.compare("Légyel", "legyél"));
		    System.out.println("    " + metric.compare("Vanda", "vanda"));
		} catch (Exception e) {
		    System.out.println(" hiba.");
		}
	    }
	}

	StringMetric metric = with(new JaroWinkler())
						     // .simplify(Simplifiers.removeDiacritics())
						     .simplify(Simplifiers.toLowerCase())
						     .build();

	System.out.println(metric.compare("ladba", "Labda"));
	System.out.println(metric.compare("Labda", "labda"));
	System.out.println(metric.compare("LÉda", "léda"));
	System.out.println(metric.compare("Légyel", "legyél"));
	System.out.println(metric.compare("Hello", "helo"));
    }
}
