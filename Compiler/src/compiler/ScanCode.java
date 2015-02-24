/*
 * Class: COSC 470-001
 * Project: Compiler
 */
package compiler;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class is responsible for reading in the code text file, separating the
 * code text according to the grammar, and placing the separated code elements
 * into into a static ArrayList.
 *
 * @author Jonathan Butler <https://github.com/jackal390iv>
 */
public class ScanCode {

    private Scanner scanner;
    private String codeTextLocation;
    private static ArrayList<String> list,code;

    public ScanCode(String codeTextLocation) {
        list = new ArrayList<String>();
        code = new ArrayList<String>();
        this.codeTextLocation = codeTextLocation;
        readCode();
        prepCode();
        prepToken();
    }

    /**
     * This method reads the code text, who's location is designated in the
     * constructor, and adds its elements to the ArrayList 'list'. It should be
     * noted that until the 'fixSpecialCharacters()' method has been run this
     * list is not yet representative of the grammar.
     *
     */
    private void readCode() {
        try {
            scanner = new Scanner(new File(codeTextLocation));
            String line;
            boolean multiLineComment = false;
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                //This section of this method checks for pre-existing multi-Line comments and removes them; designated in our current grammar as '/*' to '*/'
                if (multiLineComment == true) {
                    if (line.contains(TheCollector.getCommentBlockEndSymbol())) {
                        line = line.substring(line.indexOf(TheCollector.getCommentBlockEndSymbol()) + TheCollector.getCommentBlockEndSymbol().length(), line.length());
                        multiLineComment = false;
                    } else {
                        line = "";
                    }
                }
                //This section of this method checks for line comments and removes them from the end of the line; designated in our current grammar as '--'
                if (line.contains(TheCollector.getCommentSymbol())) {
                    line = line.substring(0, line.indexOf(TheCollector.getCommentSymbol()));
                }
                //This section of this method checks for the start of multi-line comments and removes them; designated in our current grammar as '/*' to '*/'
                if (line.contains(TheCollector.getCommentBlockStartSymbol())) {
                    line = line.substring(0, line.indexOf(TheCollector.getCommentBlockStartSymbol()));
                    multiLineComment = true;
                }
                list.add(line.trim());
            }
            scanner.close();
        } catch (Exception ex) {
            System.out.println("\n" + "ERROR");
            System.out.println("Type: " + ex.getClass().getName());
            System.out.println("Location: " + this.getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName());
            System.out.println("Cause: " + ex.getCause());
            System.out.println("Message: " + ex.getMessage());
            System.out.println("Local Message: " + ex.getLocalizedMessage() + "\n");
            //ex.printStackTrace();
        }
    }

    private void prepCode() {
        try {
            String element;
            int counter, clicker;
            String[] holder;

            for (int i = 0; i < list.size(); i++) {
                while ((list.get(i).contains(" ")) && (!(list.get(i).isEmpty()))) {
                    element = list.get(i);
                    list.remove(i);
                    list.add(i, element.substring(element.indexOf(" ") + 1));
                    list.add(i, "");
                    list.add(i, element.substring(0, element.indexOf(" ")));
                }
            }

            for (int i = 0; i < list.size(); i++) {
                for (int k = 0; k < TheCollector.getReserveWordsMinusCombinables().size(); k++) {
                    if ((list.get(i).contains(TheCollector.getReserveWordsMinusCombinables().get(k))) && (!(list.get(i).equals(TheCollector.getReserveWordsMinusCombinables().get(k))))) {
                        holder = new String[]{list.get(i).substring(0, list.get(i).indexOf(TheCollector.getReserveWordsMinusCombinables().get(k))), TheCollector.getReserveWordsMinusCombinables().get(k), list.get(i).substring(list.get(i).indexOf(TheCollector.getReserveWordsMinusCombinables().get(k)) + TheCollector.getReserveWordsMinusCombinables().get(k).length(), list.get(i).length())};
                        list.remove(i);
                        for (int j = 2; j >= 0; j--) {
                            if (!(holder[j].isEmpty())) {
                                list.add(i, holder[j]);
                            }
                        }
                    }
                }
            }

            for (int i = 0; i < list.size(); i++) {
                if (!(list.get(i).isEmpty())) {
                    for (int k = 0; k < TheCollector.getRegualarCombinables().size(); k++) {
                        counter = 0;
                        element = list.get(i);
                        while ((TheCollector.getRegualarCombinables().get(k).contains(element)) && (!(TheCollector.getRegualarCombinables().get(k).equals(element)))) {
                            holder = new String[]{TheCollector.getRegualarCombinables().get(k).substring(0, TheCollector.getRegualarCombinables().get(k).indexOf(element)), element, TheCollector.getRegualarCombinables().get(k).substring(TheCollector.getRegualarCombinables().get(k).indexOf(element) + element.length(), TheCollector.getRegualarCombinables().get(k).length())};
                            counter++;
                            if ((i + counter == list.size()) || (list.get(i + counter).isEmpty())) {
                                element = "";
                                break;
                            } else if ((!(holder[2].isEmpty())) && (holder[2].contains(list.get(i + counter)))) {
                                element = element + list.get(i + counter);
                            }
                        }
                        if (TheCollector.getRegualarCombinables().get(k).equals(element)) {
                            clicker = 0;
                            while (clicker != counter + 1) {
                                list.remove(i);
                                clicker++;
                            }
                            list.add(i, element);
                        }
                    }
                }
            }

            for (int i = 0; i < list.size(); i++) {
                for (int k = 0; k < TheCollector.getSpacedCombinables().size(); k++) {
                    counter = 0;
                    clicker = 0;
                    element = list.get(i);
                    while ((TheCollector.getSpacedCombinables().get(k).contains(element)) && ((i + counter) < list.size()) && (!(TheCollector.getSpacedCombinables().get(k).equals(element)))) {
                        counter++;
                        if (list.get(i + counter).isEmpty()) {
                            element = element + " ";
                        } else {
                            element = element + list.get(i + counter);
                        }
                    }
                    if (element.equals(TheCollector.getSpacedCombinables().get(k))) {

                        clicker = 0;
                        while (clicker != counter + 1) {
                            list.remove(i);
                            clicker++;
                        }
                        list.add(i, element);
                    }
                }
            }
            
            for(String temp:list){
                if(!(temp.isEmpty())){
                    code.add(temp);
                }
            }
            
            list.clear();

        } catch (Exception ex) {
            System.out.println("\n" + "ERROR");
            System.out.println("Type: " + ex.getClass().getName());
            System.out.println("Location: " + this.getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName());
            System.out.println("Cause: " + ex.getCause());
            System.out.println("Message: " + ex.getMessage());
            System.out.println("Local Message: " + ex.getLocalizedMessage() + "\n");
            //ex.printStackTrace();
        }
    }

    private void prepToken() {
        boolean checker;
        String temp;
        
        Changer.checkBasicSyntax(code.get(0), "START");
        Changer.checkBasicSyntax(code.get(code.size()-2), "END_Minus_1");
        Changer.checkBasicSyntax(code.get(code.size()-1), "END");
        
        for (int i = 0; i < code.size(); i++) {
                checker = false;
                for (int k = 0; k < TheCollector.getReserveWords().size(); k++) {
                    if (code.get(i).equals(TheCollector.getReserveWords().get(k))) {
                        TheCollector.addToken(new Token(code.get(i), code.get(i)));
                        checker = true;
                        break;
                    }
                }
                if (checker == false) {
                    temp=Changer.defineUnknown(code.get(i));
                    if(temp.equals("ERROR")){
                        System.out.printf("\nERROR\nThe following code is not acceptable: %s\n",code.get(i));
                        System.exit(0);
                    }else{
                    TheCollector.addToken(new Token(temp, code.get(i)));}
                }
            
        }
    }
}
