package dev.shorturl;

public class Base62Encoder {
    private static final String BASE62_CHARACTERS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static String encode(long num) {
        StringBuilder encodedStr = new StringBuilder();

        if (num == 0) {
            return String.valueOf(BASE62_CHARACTERS.charAt(0));
        }

        while (num > 0) {
            int remainder = (int) (num % 62);
            encodedStr.insert(0, BASE62_CHARACTERS.charAt(remainder));
            num /= 62;
        }

        return encodedStr.toString();
    }

    public static long decode(String base62Str) {
        long num = 0;

        for (int i = 0; i < base62Str.length(); i++) {
            num = num * 62 + BASE62_CHARACTERS.indexOf(base62Str.charAt(i));
        }

        return num;
    }

}
