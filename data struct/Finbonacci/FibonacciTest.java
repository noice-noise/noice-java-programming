package Lab10;

import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertEquals;

/**
 * Created by Serato, Jay Vince on December 10, 2019.
 */
public class FibonacciTest {
    @Before
    public void setUp() {
        try {
            System.setOut(new PrintStream(new FileOutputStream("output")));
            System.setErr(new PrintStream(new FileOutputStream("error")));
            System.setIn(new FileInputStream("input"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testNIs1() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("input"));
            bw.append("1");
            bw.close();
            Fibonacci.main(null);
            BufferedReader br = new BufferedReader(new FileReader("output"));
            String testcase = br.readLine();
            assertEquals("1", testcase);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testNIs2() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("input"));
            bw.append("2");
            bw.close();
            Fibonacci.main(null);
            BufferedReader br = new BufferedReader(new FileReader("output"));
            String testcase = br.readLine();
            assertEquals("1", testcase);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testNIs3() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("input"));
            bw.append("3");
            bw.close();
            Fibonacci.main(null);
            BufferedReader br = new BufferedReader(new FileReader("output"));
            String testcase = br.readLine();
            assertEquals("2", testcase);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testNIs5() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("input"));
            bw.append("5");
            bw.close();
            Fibonacci.main(null);
            BufferedReader br = new BufferedReader(new FileReader("output"));
            String testcase = br.readLine();
            assertEquals("5", testcase);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testNIs8() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("input"));
            bw.append("8");
            bw.close();
            Fibonacci.main(null);
            BufferedReader br = new BufferedReader(new FileReader("output"));
            String testcase = br.readLine();
            assertEquals("21", testcase);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testNIs25() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("input"));
            bw.append("25");
            bw.close();
            Fibonacci.main(null);
            BufferedReader br = new BufferedReader(new FileReader("output"));
            String testcase = br.readLine();
            assertEquals("75025", testcase);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testNIs35() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("input"));
            bw.append("35");
            bw.close();
            Fibonacci.main(null);
            BufferedReader br = new BufferedReader(new FileReader("output"));
            String testcase = br.readLine();
            assertEquals("9227465", testcase);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testNIs40() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("input"));
            bw.append("40");
            bw.close();
            Fibonacci.main(null);
            BufferedReader br = new BufferedReader(new FileReader("output"));
            String testcase = br.readLine();
            assertEquals("102334155", testcase);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
