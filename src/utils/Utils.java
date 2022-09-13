package utils;

import com.sun.source.tree.Tree;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

public class Utils {

    static NumberFormat numberFormat =
            new DecimalFormat("R$ #,##0.00",
                    new DecimalFormatSymbols(
                            new Locale("pt", "BR")));


    public static String doubleToString(double valor) {
        return numberFormat.format(valor);
    }

    public static boolean validName(String str) {
        if(str.isEmpty()) {
            System.out.println(" cannot be empty");
            return false;
        }
        return true;
    }

    public static boolean validCPF(String str){

        if(!validName(str)  || str.length() != 11){
            System.out.println(" The cpf cannot contain more or less than 11 characters");
            return false;
        }else if(cpfValidation(str)) {
            return true;
        }else{
            return false;
        }
    }

    public static boolean validPassword(String str){
        validName(str);

        if(str.length() < 6){
            System.out.println(" The password connot less than 6 characters");
            return false;
        }
        return true;
    }

    public static Integer validNumber(String str){
        boolean isNumeric = str.chars().allMatch( Character::isDigit);
        if(isNumeric){
            return Integer.parseInt(str);
        }else{
            return 0;
        }
    }
    public static boolean cpfValidation(String cpf){

        int s = 0;
        int aux = 10;
        for(int i = 0; i < 9; i++){
            s += Integer.parseInt(String.valueOf(cpf.charAt(i))) * aux--;
        }

        s = (s * 10) % 11;

        if(s == 10) s = 0;
        if(s == Integer.parseInt(String.valueOf(cpf.charAt(9)))){
            s = 0;
            aux = 11;
            for(int i = 0; i < 10; i++){
                s += Integer.parseInt(String.valueOf(cpf.charAt(i))) * aux--;
            }

            s = (s * 10) % 11;
            if(s == 10) s = 0;
            if(s == Integer.parseInt(String.valueOf(cpf.charAt(10)))){
                return true;
            }
        }
        System.out.println(" CPF invalid");
        return false;
    }
}
