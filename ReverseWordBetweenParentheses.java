package client;


import java.util.*;
class ReverseWordBetweenParentheses {
    static String reverseBetweenParentheses(String word, int length) {
        Stack<Integer> stack = new Stack<Integer>();
        for (int i = 0; i < length; i++) {

            if (word.charAt(i) == '(') {
                stack.push(i);

            } else if (word.charAt(i) == ')') {
                char[] chars = word.toCharArray();
                reverseChars(chars, stack.peek() + 1, i);
                word = String.copyValueOf(chars);
                stack.pop();
            }
        }
        StringBuilder reversedWord = new StringBuilder();
        for (int i = 0; i < length; i++) {
            if (word.charAt(i) != ')' && word.charAt(i) != '('){
                reversedWord.append(word.charAt(i));
            }
        }
        return reversedWord.toString();
    }

    static void reverseChars(char chars[], int peek, int i) {
        if (peek < i) {
            char ch = chars[peek];
            chars[peek] = chars[i];
            chars[i] = ch;
            reverseChars(chars, peek + 1, i - 1);
        }
    }

    public static void main (String[] args)
    {
        String stringToBeReversed = "dd(df)a(ghhh)";
        String reversedString = reverseBetweenParentheses(stringToBeReversed, stringToBeReversed.length());
        System.out.println(reversedString);
    }
}
