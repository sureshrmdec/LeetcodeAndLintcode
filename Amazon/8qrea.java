/*
When you go to amazon.com and type in a query, sometimes we pull up no results. In those cases, we have clever ways of guessing what the customer wanted. One of the things we do is see if the customer missed a space when they were typing. In other words, we check if the search string could be split up into separate words. For your coding problem, I want you to:
write a method to determine if a string of letters without spaces can be split into a set of valid words. For example:
   // n < 200, n = size of input string
   Input: "thisisawesome" // could be split into the set {"this", "is", "awesome"}
   Output: true
*/

//state: boolean dp[i] is iTh char can split
//"thatwasbad" is worst case;

// "island"
// canSplit[0] = true
// canSplit[1] = true
// canSplit[2] = true
// canSplit[3] = false
// canSplit[4] = false
// canSplit[5] = 
// canSplit[6] = 

// dict = {"i", "is", "land", "a", "island"}

public boolean splitSearchString(String input, Set<String> dict) {
    //corner case
    if (input == null || input.length() == 0) {
        return true;
    }
    int longest = findLongestWord(dict);
    //longest = 7;
    //define DP array
    boolean[] canSplit = new boolean[input.length() + 1];
    // initalize state?
    canSplit[0] = true;    
    for (int i = 1; i < input.length() + 1; i++) {
        canSplit[i] = false;
        for (int j = 1; j <= i && j <= longest; j++) {
            if (!canSplit[i - j]) {
                continue;
            }
            String word = input.substring(i - j, i);
            if (dict.contains(word)) {
                canSplit[i] = true;
                break;
            }
        }
    }    
    return canSplit[input.length()];
}

// O (n * l ^ 2)

private int findLongestWord(Set<String> dict) {
    int longest = 0;
    for (String s : dict) {
        longest = Math.max(longest, 
        
    

