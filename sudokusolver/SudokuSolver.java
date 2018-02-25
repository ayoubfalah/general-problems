package sudokusolver;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import utility.classes.Pair;

/**
 *
 * @author ayoubfalah
 */
public class SudokuSolver 
{    
    private static final List<List<Integer>> CLAUSES = new ArrayList();
    
    private static final int[] DIGITS = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    
    /**
     * 
     * @param literals A set of literals
     * @return a boolean expression E in conjunctive normal form such that 
     *         E = true iff there is exactly one literal that is true.
     */
    private static List<List<Integer>> exactlyOneOf(ArrayList<Integer> literals)
    {
        // The arrayList represents a boolean formula F such that F = true iff
        // there is exaclty one literal l such that l = true
        List<List<Integer>> expression = new ArrayList();
        expression.add(literals);
        ArrayList<Pair> pairs = Combinatorics.combinations(literals);
        for (Pair<Integer> pair : pairs) 
        {
            List<Integer> clause = new ArrayList();
            clause.add(-1 * pair.getA());
            clause.add(-1 * pair.getB());
            expression.add(clause);
        } 
        return expression;
    }
    
    /**
     * For any entry f_{ij} generate a boolean expression E such that
     * E = true iff there is exactly one literal x_{ijk}, k in {1, 2, ..., 9}
     * such that x_{ijk} = true
     */
    static void exactlyOneDigitForEachEntry()
    {
        ArrayList<Pair> pairs = Combinatorics.product(DIGITS);
        List<List<Integer>> clauses = new ArrayList();
        for (Pair<Integer> pair : pairs)
        {
            ArrayList<Integer> literals = new ArrayList();
            for (int k : DIGITS) 
            {
                int literal = (100 * pair.getA()) + (10 * pair.getB()) + k;
                literals.add(literal);                
            }
            List<List<Integer>> expression = exactlyOneOf(literals);
            extendClauses(expression);
        }        
    }
    
    // k appears exactly one in each row
    static void exactlyOneDigitForEachRow()
    {
        ArrayList<Pair> pairs = Combinatorics.product(DIGITS);
        for (Pair<Integer> pair : pairs)
        {
            ArrayList<Integer> literals = new ArrayList();
            for (int k : DIGITS) 
            {
                int literal = 100*pair.getA() + 10*k + pair.getB();
                literals.add(literal);
                
            }
            List<List<Integer>> expression = exactlyOneOf(literals);
            extendClauses(expression);
        }        
    }
    
    // k appears exactly one in each column
    static void exactlyOneDigitForEachColumn()
    {
        ArrayList<Pair> pairs = Combinatorics.product(DIGITS);
        for (Pair<Integer> pair : pairs)
        {
            ArrayList<Integer> literals = new ArrayList();
            for (int k : DIGITS) 
            {
                int literal = 100*k + 10*pair.getA() + pair.getB();
                literals.add(literal);
                
            }
            List<List<Integer>> expression = exactlyOneOf(literals);
            extendClauses(expression);
        }        
    }
    
    // k appears exactly once in a 3x3 block
    static void exactlyOneDigitForEach3kBlock()
    {
        ArrayList<Pair> pairs = Combinatorics.product(new int[]{1, 4, 7});
        ArrayList<Pair> deltas = Combinatorics.product(new int[]{0, 1, 2});
        
        for (Pair<Integer> pair : pairs)
        {
            for (int k : DIGITS)
            {
                ArrayList<Integer> literals = new ArrayList();
                for (Pair<Integer> delta : deltas) 
                {
                    int literal = 100*(pair.getA() + delta.getA()) + 
                                  10*(pair.getB() + delta.getB()) + k;
                    literals.add(literal);
                }  
                List<List<Integer>> expression = exactlyOneOf(literals);
                extendClauses(expression);
            }            
        }
    }
    
    static void oneClauseForEachNoEmptyEntry(int[][] puzzle)
    {
        int n = DIGITS.length;
        for (int i = 0; i < n; i++) 
        {
            for (int j = 0; j < n; j++)
            {
                int k = puzzle[i][j];
                if (k != 0)
                {                    
                    ArrayList<Integer> literals = new ArrayList();
                    int literal = 100*(i + 1) + 10*(j + 1) + k;
                    literals.add(literal);
                    CLAUSES.add(literals);
                }                
            }            
        }
    }
    
    static void exportBooleanFormulaToFile()
    {
        PrintWriter result = null;
        try 
        {
            result = new PrintWriter(new FileWriter("/Users/ayoubfalah/Desktop/booleanFormulaInCNF.cnf"));
        } catch (IOException ex)
        {
            System.out.println("Can´t open file booleanFormulaInCNF.cnf");
            System.out.println("Error: " + ex);
            return;
        }
        
        result.println("p cnf 999 " + CLAUSES.size());
        for (List<Integer> clause : CLAUSES) 
        {
            clause.add(0);
            for (Integer literal : clause) 
            {
                result.print(literal + " ");                
            }
            result.print("\n");            
        }
        result.close();
    }
    
    static void constructSudokuSolution(int[][] puzzle)
    {
        FileReader data;
        Scanner reader;
        try 
        {
            data = new FileReader("/Users/ayoubfalah/Desktop/booleanFormulaInCNF.sat");
            reader = new Scanner(data);
        } catch (FileNotFoundException e) 
        {
            System.out.println("Can´t find file booleanFormulaInCNF.sat");
            return; // End the program by returning to main
        }
        String solutionIndicator = reader.nextLine();
        if (solutionIndicator.equals("UNSAT")) {
            System.out.println("There is no solution");
        }else if (solutionIndicator.equals("SAT")) {
            List<Integer> assignment = parseAssignment(reader);
            Collections.sort(assignment);
            for (int i = 1; i < 10; i++)
            {
                for (int j = 1; j < 10; j++)
                {
                    for (int k = 1; k < 10; k++)
                    {
                        int literal = (100*i) + (10*j) + k;
                        int position = Collections.binarySearch(assignment, literal);
                        if (position >= 0)
                        {
                            puzzle[i-1][j-1] = (char)k;
                            break;
                        }
                    }                    
                }
            }
        }        
    }

    private static List<Integer> parseAssignment(Scanner reader) {
        List<Integer> assignment = new ArrayList();
        while (reader.hasNext())
        {
            assignment.add(reader.nextInt());
        }
        return assignment;
    }

    /**
     * 
     * @param expression a boolean expression in CNF
     * extends CLAUSES by expression
     */
    private static void extendClauses(List<List<Integer>> expression) 
    {
        for (List<Integer> clause : expression) 
        {
            CLAUSES.add(clause);           
        }        
    }
}