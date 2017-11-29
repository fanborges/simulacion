/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulacion;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;
import java.text.NumberFormat;
import java.util.Locale;

/**
 *
 * @author Clemente
 */
public class Writer {

    public static void writer(String cadena) {
        // Convert the string to a
        // byte array.
        String s = cadena;
        byte data[] = s.getBytes();
        Path p = Paths.get("./simulacion.txt");

        try (OutputStream out = new BufferedOutputStream(
                Files.newOutputStream(p, CREATE, APPEND))) {
            out.write(data, 0, data.length);
        } catch (IOException x) {
            System.err.println(x);
        }
    }

    public static String dinero(double d) {
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        String moneyString = formatter.format(d);
        return moneyString;
    }

    public static String varo(double d) {
        double amount = d;
        String moneyString = NumberFormat.getCurrencyInstance(new Locale("es", "MX"))
                .format(amount);
        return moneyString;
        
    }
}
