package cn.weicelove.constants;

import java.util.HashMap;
import java.util.Map;

/**
 * <p> @author     :  清风
 * <p> description :
 * <p> create date :  2021/12/23 11:30
 */
public enum CharSetEnum {

    big5(1, "big5", ""),
    dec8(3, "dec8", ""),
    cp850(4, "cp850", ""),
    hp8(6, "", ""),
    koi8r(7, "", ""),
    latin1(8, "latin1", ""),
    latin2(9, "", ""),
    swe7(10, "", ""),
    ascii(11, "", ""),
    ujis(12, "", ""),
    sjis(13, "", ""),
    hebrew(16, "", ""),
    tis620(18, "", ""),
    euckr(19, "", ""),
    koi8u(22, "", ""),
    gb2312(24, "gb2312", ""),
    greek(25, "", ""),
    cp1250(26, "", ""),
    gbk(28, "", ""),
    latin5(30, "", ""),
    armscii8(32, "", ""),
    utf8(33, "utf8", ""),
    ucs2(35, "", ""),
    cp866(36, "", ""),
    keybcs2(37, "", ""),
    macce(38, "", ""),
    macroman(39, "", ""),
    cp852(40, "", ""),
    latin7(41, "", ""),
    cp1251(51, "", ""),
    utf16(54, "", ""),
    utf16le(56, "", ""),
    cp1256(57, "", ""),
    cp1257(59, "", ""),
    utf32(60, "", ""),
    binary(63, "", ""),
    geostd8(92, "", ""),
    cp932(95, "", ""),
    eucjpms(97, "", ""),
    gb18030(248, "", ""),
    utf8mb4(255, "", ""),
    ;

    private final int code;
    private final String characterSetName;
    private final String collationName;

    private static Map<Integer, CharSetEnum> valuesMap;

    CharSetEnum(int code, String characterSetName, String collationName) {
        this.code = code;
        this.characterSetName = characterSetName;
        this.collationName = collationName;
    }

    public static CharSetEnum valueOf(Integer code) {
        if (valuesMap == null) {
            valuesMap = new HashMap<>(values().length);
            for (CharSetEnum value : CharSetEnum.values()) {
                valuesMap.put(value.code, value);
            }
        }
        return valuesMap.get(code);
    }

    public int getCode() {
        return code;
    }

    public String getCharacterSetName() {
        return characterSetName;
    }

    public String getCollationName() {
        return collationName;
    }
}
