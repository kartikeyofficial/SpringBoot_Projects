package IRCTC;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;

@SpringBootApplication
public class IrctcApplication {

	public static void main(String[] args) {
		List<Integer> l = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
       List<Integer> l1 = l.stream().filter(i-> i%2==0).collect(Collector.toList());
	}

}
