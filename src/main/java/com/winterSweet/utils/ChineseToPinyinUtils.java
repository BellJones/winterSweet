package com.winterSweet.utils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created with IntelliJ IDEA
 * User: Butterfly Killer
 * Date: 2017/3/9 10:39
 * <p>
 * Description: 汉字转拼音工具
 * </p>
 */
public class ChineseToPinyinUtils {

    private static Logger LOGGER = LogManager.getLogger(ChineseToPinyinUtils.class.getName());

    /**
     * 将汉字转为小写的拼音 [全拼]
     * <p>
     * 例如: "测试",转换结果为 'ceshi'
     * </p>
     *
     * @param chines 汉字
     * @return pinyin 拼音
     */
    public static String toPinyin(String chines) {
        String pinyin = "";
        char[] chinesChar = chines.toCharArray();
        String[] template;
        for (char ch : chinesChar) {
            if (Character.toString(ch).matches("[\\u4E00-\\u9FA5]+")) {
                template = getCharToStringArray(ch);
                pinyin += template[0];
            } else {
                pinyin += Character.toString(ch);
            }
        }
        return pinyin;
    }

    /**
     * 提取汉字的第一个拼音的大写形式字母
     * <p>
     * 例如: "测试" 转换结果为 'CS'
     * </p>
     *
     * @param chines 汉字
     * @return pinyin 首位拼音大写组合
     */
    public static String toFirstCapitalPinyin(String chines) {
        String pinyin = "";
        for (int i = 0; i < chines.length(); i++) {
            char word = chines.charAt(i);
            String[] pinyinArray = getCharToStringArray(word);
            if (null != pinyinArray) {
                pinyin += pinyinArray[0].charAt(0);
            } else {
                pinyin += word;
            }
        }
        return pinyin.toUpperCase();
    }

    /**
     * 将汉字转换为拼音全拼,首字母大写
     * <p>
     * 例如: "测试" 转换结果为 'CeShi'
     * </p>
     *
     * @param chines 汉字
     * @return pinyin
     */
    public static String toStandardPinyin(String chines) {
        String pinyin = "";
        char[] chinesChar = chines.toCharArray();
        for (char cher : chinesChar) {
            String param = String.valueOf(cher);
            // 汉字符判断
            if (param.matches("[\\u4e00-\\u9fa5]")) {
                pinyin += getFirstCharacterUpperCast(cher);
            } else {
                pinyin += param;
            }
        }
        return pinyin;
    }

    /**
     * 将字符串转移为ASCII码
     *
     * @param chines 汉字
     * @return ascII
     */
    public static String toAscII(String chines) {
        String ascII = "";
        byte[] chinesBytes = chines.getBytes();
        for (byte ch : chinesBytes) {
            ascII += Integer.toHexString(ch & 0xff);
        }
        return ascII;
    }

    /**
     * char转为String组
     *
     * @param cher char对象
     * @return String组
     */
    private static String[] getCharToStringArray(char cher) {
        HanyuPinyinOutputFormat hanyuPinyinOutputFormat = new HanyuPinyinOutputFormat();
        hanyuPinyinOutputFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE); // 全小写
        hanyuPinyinOutputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE); // 无音调
        hanyuPinyinOutputFormat.setVCharType(HanyuPinyinVCharType.WITH_V); // 'ü' 换成 "v"
        String[] words = new String[0];
        try {
            words = PinyinHelper.toHanyuPinyinStringArray(cher, hanyuPinyinOutputFormat);
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            LOGGER.error("BAD HanYu Pinyin Output Format Combination : {}", e.getMessage());
        }
        return words;
    }

    // 将单个汉字转换为首字母大写形式的拼音
    private static String getFirstCharacterUpperCast(char word) {
        String firstUpper = "";
        String[] pinyinArray = getCharToStringArray(word);
        if (null != pinyinArray) {
            char[] pinyinCharacters = pinyinArray[0].toCharArray();
            for (int i = 0; i < pinyinCharacters.length; i++) {
                if (0 == i) {
                    firstUpper += String.valueOf(pinyinCharacters[i]).toUpperCase();
                } else {
                    firstUpper += pinyinCharacters[i];
                }
            }
        }
        return firstUpper;
    }

    public static void main(String[] args) {
        String chine = "生存或者死亡,这是个问题.";
        System.out.println(toStandardPinyin(chine));
    }
}
