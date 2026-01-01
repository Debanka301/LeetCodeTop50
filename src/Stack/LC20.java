/*
Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

An input string is valid if:

Open brackets must be closed by the same type of brackets.
Open brackets must be closed in the correct order.
Every close bracket has a corresponding open bracket of the same type.

Example 1: Input: s = "()" Output: true

Example 2: Input: s = "()[]{}" Output: true

Example 3: Input: s = "(]" Output: false

Example 4: Input: s = "([])" Output: true

Example 5: Input: s = "([)]" Output: false

Constraints: 1 <= s.length <= 104 s consists of parentheses only '()[]{}'.

 */

import java.util.Stack;

class Solution {

    /* My Solution - Intuitive

    Instead of the usual left → right scan with a stack of opening brackets, you:
    Traverse the string from right to left
    Treat a StringBuilder as a pseudo-stack
    Push closing brackets
    Try to match them with corresponding opening brackets while moving left
    So conceptually:
    “I’ll collect closing brackets first, and when I see an opening bracket, I’ll try to cancel it with the last unmatched closing bracket.”

    public boolean isValid(String s) {
        char[] c = s.toCharArray();
        if(c[c.length-1] == '(' || c[c.length-1] == '{' || c[c.length-1] == '[') return false;
        StringBuilder sb = new StringBuilder("");
        sb.append(c[c.length-1]);
        for(int i=c.length-2;i>=0;i--){
            char temp = c[i];
            if(sb.length() == 0){
                sb.append(temp);
                continue;
            }
            else if(sb.charAt(sb.length()-1) == ']'){
                if(temp == '(' || temp == '{') return false;
                else if(temp == ')' || temp == '}' || temp == ']') sb.append(temp);
                else sb.deleteCharAt(sb.length()-1);
            }
            else if(sb.charAt(sb.length()-1) == '}'){
                if(temp == '(' || temp == '[') return false;
                else if(temp == ')' || temp == '}' || temp == ']') sb.append(temp);
                else sb.deleteCharAt(sb.length()-1);
            }
            else if(sb.charAt(sb.length()-1) == ')'){
                if(temp == '{' || temp == '[') return false;
                else if(temp == ')' || temp == '}' || temp == ']') sb.append(temp);
                else sb.deleteCharAt(sb.length()-1);
            }
        }
        if(sb.length() == 0) return true;
        return false;
    }
     */

    //Optimised Approach
    /*
    I use a stack to keep track of opening brackets. While scanning the string, I push opening brackets onto the stack.
    When a closing bracket appears, I pop the stack and check if it matches.If at any point it doesn’t match or the stack is empty, the string is invalid.
    At the end, the stack must be empty for the string to be valid.
     */
    public boolean isValid(String s) {

        Stack<Character> stack = new Stack<>();

        for (char ch : s.toCharArray()) {

            // Push opening brackets
            if (ch == '(' || ch == '{' || ch == '[') {
                stack.push(ch);
            }
            // Handle closing brackets
            else {
                if (stack.isEmpty()) return false;

                char top = stack.pop();

                if ((ch == ')' && top != '(') ||
                        (ch == '}' && top != '{') ||
                        (ch == ']' && top != '[')) {
                    return false;
                }
            }
        }

        // Stack must be empty for valid string
        return stack.isEmpty();
    }
}
public class LC20 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println("Valid : "+ (solution.isValid("([])") ? "Yes":"No"));
    }
}
