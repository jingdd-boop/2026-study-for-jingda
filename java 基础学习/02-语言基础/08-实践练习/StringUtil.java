/**
 * 练习 3：字符串处理工具
 * 运行：javac StringUtil.java && java StringUtil
 */
public class StringUtil {

    public static void main(String[] args) {
        String text = "hello java world java";

        System.out.println("===== 字符串处理工具 =====");
        System.out.println("原文: " + text);

        System.out.println("\n1. 反转: " + reverse(text));

        System.out.println("\n2. 词频统计:");
        countWordFrequency(text);

        System.out.println("\n3. 回文判断:");
        String[] testCases = {"level", "hello", "上海自来水来自海上"};
        for (String s : testCases) {
            System.out.println("  \"" + s + "\" → " + (isPalindrome(s) ? "是回文" : "不是回文"));
        }
    }

    /** 反转字符串 */
    static String reverse(String s) {
        return new StringBuilder(s).reverse().toString();
    }

    /** 统计词频（简单版，按空格分割） */
    static void countWordFrequency(String text) {
        String[] words = text.toLowerCase().split("\\s+");
        String[] uniqueWords = getUniqueWords(words);

        for (String word : uniqueWords) {
            int count = 0;
            for (String w : words) {
                if (w.equals(word)) count++;
            }
            System.out.println("  " + word + ": " + count + " 次");
        }
    }

    /** 获取不重复的单词列表 */
    static String[] getUniqueWords(String[] words) {
        String[] temp = new String[words.length];
        int size = 0;

        for (String word : words) {
            boolean exists = false;
            for (int i = 0; i < size; i++) {
                if (temp[i].equals(word)) {
                    exists = true;
                    break;
                }
            }
            if (!exists) {
                temp[size++] = word;
            }
        }

        String[] result = new String[size];
        System.arraycopy(temp, 0, result, 0, size);
        return result;
    }

    /** 判断是否为回文（忽略空格，不区分大小写） */
    static boolean isPalindrome(String s) {
        String cleaned = s.replaceAll("\\s+", "").toLowerCase();
        int left = 0, right = cleaned.length() - 1;

        while (left < right) {
            if (cleaned.charAt(left) != cleaned.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
