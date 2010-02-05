package org.wiztools.util.datearithmetic;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author subwiz
 */
public class Main {

    final private static Pattern DATE_ARITHMETIC_PATTERN = Pattern.compile(
            "\\s*(\\d{4})-(\\d{2})-(\\d{2})\\s+(\\+|-)\\s+(\\d+)(d|m|y)\\s*");
    final private static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public static void main(String[] arg){
        System.out.println("In the standard-input give the arithmetic in this format:");
        System.out.println("\tyyyy-mm-dd <operator> <number><units>");
        System.out.println("Operators are: +, -");
        System.out.println("Units:");
        System.out.println("\td: Days");
        System.out.println("\tm: Months");
        System.out.println("\ty: Years");
        System.out.println("Example:");
        System.out.println("\t1979-02-15 + 31y");
        System.out.println("Give EOF character or press ^+C to quit.");
        while(true){
            final String input = System.console().readLine();
            if(input == null){
                System.exit(0);
            }
            final Matcher m = DATE_ARITHMETIC_PATTERN.matcher(input);
            if(m.matches()){
                final int year = Integer.parseInt(m.group(1));
                final int month = Integer.parseInt(m.group(2)) - 1; // Calendar's month starts from 0
                final int date = Integer.parseInt(m.group(3));

                final Calendar c = Calendar.getInstance();
                c.clear();
                c.set(Calendar.YEAR, year);
                c.set(Calendar.MONTH, month);
                c.set(Calendar.DATE, date);

                int number = Integer.parseInt(m.group(5));
                if(m.group(4).equals("-")){
                    number = number * (-1);
                }

                final String unit = m.group(6);
                int calOperation = 0;
                if(unit.equals("d")){
                    calOperation = Calendar.DATE;
                }
                else if(unit.equals("m")){
                    calOperation = Calendar.MONTH;
                }
                else if(unit.equals("y")){
                    calOperation = Calendar.YEAR;
                }

                c.add(calOperation, number);

                System.out.println( ">> " + DATE_FORMAT.format(c.getTime()));
            }
            else{
                System.err.println("Wrong pattern.");
            }
        }
    }
}
