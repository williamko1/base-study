package me.qinzc.orientprocess;

/**
 * desc :
 *
 * @author : Zane Qin
 * creatTime : 17:55 2020/4/8
 * modifier:
 * modifyTime:
 */
public class GenericTypeMain {

    public interface Converter<S, T>{
        T convert(S source);
    }

    public static void main(String[] args) {
        Converter<String, Integer> stringIntegerConverter = new Converter<String, Integer>() {
            @Override
            public Integer convert(String source) {
                return Integer.valueOf(source);
            }
        };
        Converter<Integer, String> integerStringConverter = new Converter<Integer, String>() {
            @Override
            public String convert(Integer source) {
                return String.valueOf(source);
            }
        };
    }

}
