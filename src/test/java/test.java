public class test {
    public static void main(String[] args) {
        String line="192.168.1.57  8.348 [21/Mar/2018:15:28:58 +0800] \"POST /CHECK/getCheckIllegalCase.do HTTP/1.1\" 200 ";
        String time=line.substring(line.indexOf("["),line.indexOf("]")+1);
        System.out.println(time);
       line= line.replace(time,"");
        System.out.println(line);
        System.out.println(System.currentTimeMillis());
    }
}
