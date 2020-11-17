package org.example.foreignKeySubstitution.utils;

public class NameTransferUitl {

    /**
     * 驼峰转 下划线
     * userName  ---->  user_name
     * user_name  ---->  user_name
     *
     * @param humpName 驼峰命名
     * @return 带下滑线的String
     */
    public static String humpToUnderline(String humpName) {
        //截取下划线分成数组，
        char[] charArray = humpName.toCharArray();
        StringBuilder buffer = new StringBuilder();
        //处理字符串
        for (int i = 0, l = charArray.length; i < l; i++) {
            if (charArray[i] >= 65 && charArray[i] <= 90) {
                buffer.append("_")
                        .append(charArray[i] += 32);
            } else {
                buffer.append(charArray[i]);
            }
        }
        return buffer.toString();
    }
}
