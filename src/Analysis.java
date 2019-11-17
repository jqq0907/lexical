import java.util.LinkedList;

public class Analysis {
    LinkedList<Character> list1=new LinkedList<>();//把字符串转成字符数组存入
    LinkedList<Character> list2=new LinkedList<>();//存放类型name
    public Analysis(){
    }
public char readin(){
        char c=list1.pollFirst();
        list2.offerLast(c);//取出list1中的第一个字符放入list2结尾
        return c;
}
public void readout(){
        char c1=list2.pollLast();
        list1.offerFirst(c1);//取出list2的最后一个字符放入list1开头
}
    public void ananlyze() {
        int state = 0;
        Token token = new Token();
        while (!list1.isEmpty()) {
            char c2 = readin();
            ;//如果list1不为空，取出list1中第一个字符
            switch (state) {
                case 0:
                    if (Character.isLetter(c2))//判断是否为字母
                        state = 1;
                    else if (Character.isDigit(c2))//
                        state = 3;
                    else if (c2 == '+')
                        state = 10;
                    else if (c2 == '-')
                        state = 14;
                    else if (c2 == '*')
                        state = 18;
                    else if (c2 == '/')
                        state = 21;
                    else if (c2 == '%')
                        state = 24;
                    else if (c2 == '&')
                        state = 27;
                    else if (c2 == '|')
                        state = 31;
                    else if (c2 == '^')
                        state = 38;
                    else if (c2 == '<')
                        state = 41;
                    else if (c2 == '>')
                        state = 47;
                    else if (c2 == '=')
                        state = 57;
                    else if (c2 == '?') {
                        state = 60;
                        readout();
                    } else if (c2 == ':') {
                        state = 61;
                        readout();
                    } else if (c2 == '[') {
                        state = 62;
                        readout();
                    } else if (c2 == ']') {
                        state = 63;
                        readout();
                    } else if (c2 == '(') {
                        state = 64;
                        readout();
                    } else if (c2 == ')') {
                        state = 65;
                        readout();
                    } else if (c2 == '.') {
                        state = 66;
                        readout();
                    } else if (c2 == ',') {
                        state = 67;
                        readout();
                    } else if (c2 == '{') {
                        state = 68;
                        readout();
                    } else if (c2 == '}') {
                        state = 69;
                        readout();
                    } else if (c2 == ';') {
                        state = 70;
                        readout();
                    } else {
                        if (c2 != ' ') {
                            System.out.println("不能识别");
                            LGUI.t2.append("不能识别");
                            LGUI.t2.append("\r\n");
                            list2.clear();
                            continue;
                        }
                    }
                    break;
                case 1:
                    if (Character.isLetter(c2) || Character.isDigit(c2))
                        state = 1;
                    else {
                        state = 2;
                        readout();
                    }
                    break;
                case 2:
                    readout();
                    String s = getList2();
                    if (Id.isKeyword(s.trim())) {//判断关键字
                        token.setName(s.trim());
                        token.setType("关键字");
                    } else {
                        token.setName(s.trim());//trim()删除字符串两端空格
                        token.setType("标识符");
                    }
                        System.out.println(token.toString());
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                        list2.clear();
                        state = 0;
                        break;
                case 3:
                    if (Character.isDigit(c2))
                        state = 3;
                    else if (c2 == '.')
                        state = 4;
                    else if (c2 == 'e' || c2 == 'E')
                        state = 6;
                    else {
                        state = 9;
                        readout();
                    }
                    break;
                case 4:
                    if (Character.isDigit(c2))
                        state = 5;
                    else {
                        if (c2 != ' ') {
                            System.out.println("不能识别");
                            LGUI.t2.append("不能识别");
                            LGUI.t2.append("\r\n");
                            list2.clear();
                            continue;
                        }
                    }
                    break;
                case 5:
                    if (c2 == 'e' || c2 == 'E')
                        state = 6;
                    else {
                        state = 9;
                        readout();
                    }
                    break;
                case 6:
                    if (c2 == '+' || c2 == '-')
                        state = 7;
                    else if (Character.isDigit(c2))
                        state = 8;
                    else {
                        if (c2 != ' ') {
                            System.out.println("不能识别");
                            LGUI.t2.append("不能识别");
                            LGUI.t2.append("\r\n");
                            list2.clear();
                            continue;
                        }
                    }
                    break;
                case 7:
                    if (Character.isDigit(c2))
                        state = 8;
                    else {
                        System.out.println("不能识别");
                        LGUI.t2.append("不能识别");
                        LGUI.t2.append("\r\n");
                        list2.clear();
                        continue;
                    }
                    break;
                case 8:
                    if (Character.isDigit(c2))
                        state = 8;
                    else {
                        state = 9;
                        readout();
                    }
                    break;
                case 9://数值
                    readout();
                    String s1 = getList2();
                    token.setName(s1.trim());
                    token.setType("数值");
                    System.out.println(token.toString());
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    list2.clear();
                    state = 0;
                    break;
                case 10:
                    if (Character.isDigit(c2))
                        state = 3;
                    else if (c2 == '+') {
                        state = 11;
                        readout();
                    } else if (c2 == '=') {
                        state = 12;
                        readout();
                    } else {
                        state = 13;
                        readout();
                    }
                    break;
                case 11://++
                    token.setName(getList2().trim());
                    token.setType("运算符");
                    System.out.println(token.toString());
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    list2.clear();
                    state = 0;
                    break;
                case 12://+=
                    token.setName(getList2().trim());
                    token.setType("运算符");
                    System.out.println(token.toString());
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    list2.clear();
                    state = 0;
                    break;
                case 13://+
                    readout();
                    token.setName(getList2().trim());
                    token.setType("运算符");
                    System.out.println(token.toString());
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    list2.clear();
                    state = 0;
                    break;
                case 14:
                    if (Character.isDigit(c2))
                        state = 3;
                    else if (c2 == '-') {
                        state = 15;
                        readout();
                    } else if (c2 == '=') {
                        state = 16;
                        readout();
                    } else {
                        state = 17;
                        readout();
                    }
                    break;
                case 15://--
                    token.setName(getList2().trim());
                    token.setType("运算符");
                    System.out.println(token.toString());
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    list2.clear();
                    state = 0;
                    break;
                case 16://-=
                    token.setName(getList2().trim());
                    token.setType("运算符");
                    System.out.println(token.toString());
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    list2.clear();
                    state = 0;
                    break;
                case 17://-
                    readout();
                    token.setName(getList2().trim());
                    token.setType("运算符");
                    System.out.println(token.toString());
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    list2.clear();
                    state = 0;
                    break;
                case 18:
                    if (c2 == '=') {
                        state = 19;
                        readout();
                    } else {
                        state = 20;
                        readout();
                    }
                    break;
                case 19://*=
                    token.setName(getList2());
                    token.setType("运算符");
                    System.out.println(token.toString());
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    list2.clear();
                    state = 0;
                    break;
                case 20://*
                    readout();
                    token.setName(getList2());
                    token.setType("运算符");
                    System.out.println(token.toString());
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    state = 0;
                    list2.clear();
                    break;
                case 21:
                    if (c2 == '=') {
                        state = 22;
                        readout();
                    } else {
                        state = 23;
                        readout();
                    }
                    break;
                case 22:// /=
                    token.setName(getList2());
                    token.setType("运算符");
                    System.out.println(token.toString());
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    list2.clear();
                    state = 0;
                    break;
                case 23:// /
                    readout();
                    token.setName(getList2());
                    token.setType("运算符");
                    System.out.println(token.toString());
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    list2.clear();
                    state = 0;
                    break;
                case 24:
                    if (c2 == '=') {
                        state = 25;
                        readout();
                    } else {
                        state = 26;
                        readout();
                    }
                    break;
                case 25:// %=
                    token.setName(getList2());
                    token.setType("运算符");
                    System.out.println(token.toString());
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    list2.clear();
                    state = 0;
                    break;
                case 26:// %
                    readout();
                    token.setName(getList2());
                    token.setType("运算符");
                    System.out.println(token.toString());
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    list2.clear();
                    state = 0;
                    break;
                case 27:
                    if (c2 == '&') {
                        state = 28;
                        readout();
                    } else if (c2 == '=') {
                        state = 29;
                        readout();
                    } else {
                        state = 30;
                        readout();
                    }
                    break;
                case 28:// &&
                    token.setName(getList2());
                    token.setType("运算符");
                    System.out.println(token.toString());
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    list2.clear();
                    state = 0;
                    break;
                case 29:// &=
                    token.setName(getList2());
                    token.setType("运算符");
                    System.out.println(token.toString());
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    list2.clear();
                    state = 0;
                    break;
                case 30:// &
                    readout();
                    token.setName(getList2());
                    token.setType("运算符");
                    System.out.println(token.toString());
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    list2.clear();
                    state = 0;
                    break;
                case 31:
                    if (c2 == '|') {
                        state = 32;
                        readout();
                    } else if (c2 == '=') {
                        state = 33;
                        readout();
                    } else {
                        state = 34;
                        readout();
                    }
                    break;
                case 32:// ||
                    token.setName(getList2());
                    token.setType("运算符");
                    System.out.println(token.toString());
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    list2.clear();
                    state = 0;
                    break;
                case 33:// |=
                    token.setName(getList2());
                    token.setType("运算符");
                    System.out.println(token.toString());
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    list2.clear();
                    state = 0;
                    break;
                case 34:// |
                    readout();
                    token.setName(getList2());
                    token.setType("运算符");
                    System.out.println(token.toString());
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    list2.clear();
                    state = 0;
                    break;
                case 35:
                    if (c2 == '=') {
                        state = 36;
                        readout();
                    } else {
                        state = 37;
                        readout();
                    }
                    break;
                case 36: // !=
                    token.setName(getList2());
                    token.setType("运算符");
                    System.out.println(token.toString());
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    list2.clear();
                    state = 0;
                    break;
                case 37: // !
                    readout();
                    token.setName(getList2());
                    token.setType("运算符");
                    System.out.println(token.toString());
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    list2.clear();
                    state = 0;
                    break;
                case 38:
                    if (c2 == '=') {
                        state = 39;
                        readout();
                    } else {
                        state = 40;
                        readout();
                    }
                    break;
                case 39: // ^=
                    token.setName(getList2());
                    token.setType("运算符");
                    System.out.println(token.toString());
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    list2.clear();
                    state = 0;
                    break;
                case 40: // ^异或
                    readout();
                    token.setName(getList2());
                    token.setType("运算符");
                    System.out.println(token.toString());
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    list2.clear();
                    state = 0;
                    break;
                case 41:
                    if (c2 == '=') {
                        state = 42;
                        readout();
                    } else if (c2 == '<') {
                        state = 43;
                    } else {
                        state = 46;
                        readout();
                    }
                    break;
                case 42: // <=
                    token.setName(getList2());
                    token.setType("运算符");
                    System.out.println(token.toString());
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    list2.clear();
                    state = 0;
                    break;
                case 43:
                    if (c2 == '=') {
                        state = 44;
                        readout();
                    } else {
                        state = 45;
                        readout();
                    }
                    break;
                case 44: // <<=复合赋值运算符
                    token.setName(getList2());
                    token.setType("运算符");
                    System.out.println(token.toString());
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    list2.clear();
                    state = 0;
                    break;
                case 45: // <<
                    readout();
                    token.setName(getList2());
                    token.setType("运算符");
                    System.out.println(token.toString());
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    list2.clear();
                    state = 0;
                    break;
                case 46: // <
                    readout();
                    token.setName(getList2());
                    token.setType("运算符");
                    System.out.println(token.toString());
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    list2.clear();
                    state = 0;
                    break;
                case 47:
                    if (c2 == '=') {
                        state = 48;
                        readout();
                    } else if (c2 == '>')
                        state = 49;
                    else {
                        state = 55;
                        readout();
                    }
                    break;
                case 48: // >=
                    token.setName(getList2());
                    token.setType("运算符");
                    System.out.println(token.toString());
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    list2.clear();
                    state = 0;
                    break;
                case 49:
                    if (c2 == '>')
                        state = 50;
                    else if (c2 == '=') {
                        state = 53;
                        readout();
                    } else {
                        state = 54;
                        readout();
                    }
                    break;
                case 50:
                    if (c2 == '=') {
                        state = 51;
                        readout();
                    } else {
                        state = 52;
                        readout();
                    }
                    break;
                case 51: // >>>=
                    token.setName(getList2());
                    token.setType("运算符");
                    System.out.println(token.toString());
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    list2.clear();
                    state = 0;
                    break;
                case 52: // >>>无符号又移
                    readout();
                    token.setName(getList2());
                    token.setType("运算符");
                    System.out.println(token.toString());
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    list2.clear();
                    state = 0;
                    break;
                case 53: // >>=
                    token.setName(getList2());
                    token.setType("运算符");
                    System.out.println(token.toString());
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    list2.clear();
                    state = 0;
                    break;
                case 54: // >>
                    readout();
                    token.setName(getList2());
                    token.setType("运算符");
                    System.out.println(token.toString());
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    list2.clear();
                    state = 0;
                    break;
                case 55: // >
                    readout();
                    token.setName(getList2());
                    token.setType("运算符");
                    System.out.println(token.toString());
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    list2.clear();
                    state = 0;
                    break;
                case 56:
                    break;
                case 57:
                    if (c2 == '=') {
                        state = 58;
                        readout();
                    } else {
                        state = 59;
                        readout();
                    }
                    break;
                case 58: // ==
                    token.setName(getList2());
                    token.setType("运算符");
                    System.out.println(token.toString());
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    list2.clear();
                    state = 0;
                    break;
                case 59: // =
                    readout();
                    token.setName(getList2());
                    token.setType("运算符");
                    System.out.println(token.toString());
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    list2.clear();
                    state = 0;
                    break;
                case 60: // ?
                    token.setName(getList2());
                    token.setType("界符");
                    System.out.println(token.toString());
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    list2.clear();
                    state = 0;
                    break;
                case 61: // :
                    token.setName(getList2());
                    token.setType("界符");
                    System.out.println(token.toString());
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    list2.clear();
                    state = 0;
                    break;
                case 62: // [
                    token.setName(getList2());
                    token.setType("界符");
                    System.out.println(token.toString());
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    list2.clear();
                    state = 0;
                    break;
                case 63: // ]
                    token.setName(getList2());
                    token.setType("界符");
                    System.out.println(token.toString());
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    list2.clear();
                    state = 0;
                    break;
                case 64: // (
                    token.setName(getList2());
                    token.setType("界符");
                    System.out.println(token.toString());
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    list2.clear();
                    state = 0;
                    break;
                case 65: // )
                    token.setName(getList2());
                    token.setType("界符");
                    System.out.println(token.toString());
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    list2.clear();
                    state = 0;
                    break;
                case 66: // .
                    token.setName(getList2());
                    token.setType("界符");
                    System.out.println(token.toString());
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    list2.clear();
                    state = 0;
                    break;
                case 67: // ,
                    token.setName(getList2());
                    token.setType("界符");
                    System.out.println(token.toString());
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    list2.clear();
                    state = 0;
                    break;
                case 68: // {
                    token.setName(getList2());
                    token.setType("界符");
                    System.out.println(token.toString());
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    list2.clear();
                    state = 0;
                    break;
                case 69: // }
                    token.setName(getList2());
                    token.setType("界符");
                    System.out.println(token.toString());
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    list2.clear();
                    state = 0;
                    break;
                case 70: // ;
                    token.setName(getList2());
                    token.setType("界符");
                    System.out.println(token.toString());
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    list2.clear();
                    state = 0;
                    break;

            }
        }
    }

    private String getList2() { //取出list2内容
        String s=new String();
        for (int i = 0; i <list2.size() ; i++) {
            char c=list2.get(i);
            s+=c;
        }
        return s;
    }


}
