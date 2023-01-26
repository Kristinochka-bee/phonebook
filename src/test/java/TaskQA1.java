public class TaskQA1 {
    public static void main(String[] args) {
        //Регулярные выражения
        /*
        \\d = одна цифра
        +  один или более
        *  0 или более
        ?  символ кот идет до "?"может быть, а может и не быть
        (x|y|z) одно из множеств
        [a - z] все буквы английского алфавита в нижнем регистре
        [A - A] все буквы английского алфавита в нижнем регистре
       [a -zA-  z] все буквы английского алфавита в разном регистре
       [0-9 ] все возможные цифры
       [^abc]  букв abc  не должно быть (указывать в нужном регистре)
       {2} указывает кол-во символов . в данном случае 2
       {2,} от 2 до бесконечности
       {2,4} от 2 до 4
       \\w одна буква (\\w = [a-zA-Z])
       ^abc что символ abc должны быть в начале строки *(в этой же последовательности m)
        abc$   что символ abc должны быть в конце строки
        .  -означает любой символ для одного

        (com|de) или или, [a-h] множество букв

         */
        String a = "-4578";
        String b = "696z";
        String c = "a6968585";
        String d = "aG123";
        String e = "aG123TjduA064790";
        String i = "hellko";
        String g = "hello22";
        String p = "Hello";
        String o = "hello";
        String k = "hel3lo";
        String m = "werhello";
        String v = "hellower";
        String x = "hellower4762467229065";
        String ä = "+9999";


        System.out.println(a.matches("-\\d+"));
        System.out.println(b.matches("\\d+(x|y|z)")); //в конце должна быть одна из этих букв тогда true
        System.out.println(c.matches("[a-z]\\d+"));
        System.out.println(d.matches("[a-zA-Z123]+\\d+"));
        System.out.println(e.matches("[a-zA-Z0-9]+\\d+"));  //любые цифры 0-9 и буквы разных регистров
        System.out.println(i.matches("[^zxc]+"));  //+ могут быть также другие буквы
        System.out.println(g.matches("hello\\d{2}"));  //после теста толжно иджти 2 символа
        System.out.println(p.matches("hello[A-Z]{4,6}"));  // от 4 до 6 сиьволов в вверхнем регистре
        System.out.println(o.matches("\\w{5}"));  //слово состоящие из 5 букв любого регистра
        System.out.println(k.matches("[a-z3]{6}"));  //6 cимволов , одна из них будет цифра
        System.out.println(v.matches("\\w+wer$"));  //^abc что символ abc должны быть в начале строки *(в этой же последовательности m)
        System.out.println(x.matches(".+")); //.  -означает любой символ "+" любое колличечество
        System.out.println(ä.matches("\\+\\d+"));


        String url = "https://www.google.com"; //домен должен быть либо com or de
        System.out.println(url.matches("https://www\\..+\\.(com|de)"));//домен должен быть либо com or de ,,,(com|de) или или, [a-h] множество букв
        System.out.println(url.matches("https://www\\..+\\.(com)")); //в конце доллжно быть только com
        System.out.println(url.matches("https://www\\..+\\.com$")); //символ com должны быть в конце строки ,,[a-z]+ может быть любой домен
        System.out.println(url.matches("https://www\\..+\\.[a-z]+")); //символ com должны быть в конце строки ,,[a-z]+ может быть любой домен

        String url1 = "https://www.google.com/login"; //домен должен быть либо com or de
        System.out.println(url1.matches("https://www\\..+\\.[a-z]+/.+")); //в конце может быть любое слово от а до з и + любое слово в конце . в наш случае login

        String task = "helrlo";//Task
        System.out.println(c.matches("[a-zA-Z\\d+]{6,}"));
    }
}
