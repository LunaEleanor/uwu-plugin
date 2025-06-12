package com.uwuScape;
//literally just copypasted this file from idyl with only some changes
import java.util.*;

public class Owoify {
    private static Map<String, String> wordMap = new HashMap();
    private static List<String> prefixes = Arrays.asList(
            "OwO whats this? ",
            "*nuzzles* ",
            "*waises paw* ",
            "*blushes* ",
            "*giggles* ",
            "hehe ",
            "rawr~ ",
            "teehee ",
            "*boops your nose* ",
            "*runs in circles* ",
            "H-hewwo?? ",
            "*licks paw* ",
            "nya~ ",
            "*shakes tail* "
            );

    private static final List<String> emojiSuffixes = Arrays.asList("~", " :3", " x3", " ^_^", " UwU", " owo");

    static {
        wordMap.put("love", "wuv");
        wordMap.put("beauty", "bewti");
        wordMap.put("mr", "mistuh");
        wordMap.put("dog", "doggo");
        wordMap.put("cat", "kitteh");
        wordMap.put("hello", "henwo");
        wordMap.put("hi", "haiii~");
        wordMap.put("hey", "heya~");
        wordMap.put("hell", "heck");
        wordMap.put("fuck", "fwick");
        wordMap.put("fuk", "fwick");
        wordMap.put("shit", "shoot");
        wordMap.put("friend", "fwend");
        wordMap.put("stop", "stawp");
        wordMap.put("god", "gosh");
        wordMap.put("dick", "peepee");
        wordMap.put("penis", "peepee");
        wordMap.put("damn", "darn");
        wordMap.put("kill", "hug");
        wordMap.put("dead", "sweepy");
        wordMap.put("sleepy", "eepy");
        wordMap.put("death", "etewnaw west");
        wordMap.put("no", "nuuu");
        wordMap.put("yes", "yus~");
        wordMap.put("you", "u");
        wordMap.put("you're", "ur");
        wordMap.put("me", "mew");
        wordMap.put("my", "mai");
        wordMap.put("cute", "kawaii~");
        wordMap.put("happy", "hewpy");
        wordMap.put("sorry", "sowwy");
        wordMap.put("lol", "teehee");
        wordMap.put("what", "wut");
        wordMap.put("really", "weawwy");
        wordMap.put("little", "wittwe");
    }

    public static String convert(String text, uwuScapeConfig config) {
        if (config.owoifyMode() == OwoifyMode.OFF)
        {
            return text;
        }

        text = text.replaceAll("(?i)<br\\s*/?>", " ");
        String[] words = text.split("\\s+");
        String result = "";

        double roll = Math.floor(Math.random()*7);

        if(roll == 0 && config.randomPrefix()) {
            result += prefixes.get((int) Math.floor(Math.random() * prefixes.size()));
        }


        boolean first = true;

        for(String w : words) {
            String cleanWord = w.replaceAll("[^a-zA-Z]", "").toLowerCase();
            String replaced = wordMap.get(cleanWord);
            if(replaced != null) {
                String punctuation = w.replaceAll("[a-zA-Z]", "");
                result += " " + replaced + punctuation;
            }
            else if (config.owoifyMode() == OwoifyMode.FULL) {
                result += " ";
                for(char c : w.toCharArray()) {
                    char newChar = c;
                    if(c == 'l' || c == 'r') {
                        newChar = 'w';
                    }
                    else if(c == 'L' || c == 'R') {
                        newChar = 'W';
                    }

                    result += newChar;
                }
            } else {
                result += (first ? "" : " ") + w;
            }
            first = false;
        }
        if (Math.random() < 0.2 && config.randomEmotes())
        {
            result += emojiSuffixes.get((int)(Math.random() * emojiSuffixes.size()));
        }
        return result;
    }
}
