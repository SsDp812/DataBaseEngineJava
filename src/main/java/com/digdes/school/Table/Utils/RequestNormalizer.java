package com.digdes.school.Table.Utils;


public class RequestNormalizer {

    //Нормализатор для приведения запроса в "стандартный" вид и последующей удобной его обработки
    public static String normolize(String request){
        request = deleteSpaces(request);
        request =  addSpaces(request);
        request = toLower(request);
        return request;
    }

    private static String deleteSpaces(String request){
        StringBuilder builder = new StringBuilder(request);
        boolean flag = true;
        for(int i = 0; i < builder.length();i++){
            if(builder.charAt(i) == '\''){
                flag = !flag;
            }
            if(flag){
                if(builder.charAt(i) == ' ' && i > 1 && builder.charAt(i-1) == ' '){
                    builder.deleteCharAt(i);
                    i--;
                }
            }
        }
        request = builder.toString();
        return request;
    }
    private static String addSpaces(String request){
        StringBuilder builder = new StringBuilder(request);
        boolean flag = true;
        for(int i = 0; i < builder.length();i++){
            if(builder.charAt(i) == '\''){
                flag = !flag;
            }
            if(flag){
                if(builder.charAt(i) == '=' && i > 1 && i < builder.length() - 1){
                    if(builder.charAt(i - 1) != ' '){
                        builder.insert(i," ");
                        i++;
                    }
                }

                if(builder.charAt(i) == '=' && i > 1 && i < builder.length() - 1){
                    if(builder.charAt(i + 1) != ' '){
                        builder.insert(i+1," ");
                        i++;
                    }
                }
            }
        }
        request = builder.toString();
        return request;
    }
    private static String toLower(String request){
        StringBuilder builder = new StringBuilder(request);
        StringBuilder temp = new StringBuilder("");
        boolean flag = true;
        for(int i = 0; i < builder.length();i++){
            if(builder.charAt(i) == '\''){
                flag = !flag;
            }
            if(flag){
                Character str= builder.charAt(i);
                temp.append(Character.toLowerCase(builder.charAt(i)));
            }else{
                temp.append(String.valueOf(builder.charAt(i)));
            }
        }
        return temp.toString();
    }
}
