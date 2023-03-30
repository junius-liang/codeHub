package generator.demo;

/**
 * @author junius
 * @date 2023/03/23 11:05
 * @project codeHub
 **/
public class GetSum1To100 {
    public static int sum(int i){
        if (i==1){
            return 1;
        }
        return i+sum(i-1);
    }

    public static void main(String[] args) {
        int sum = sum(100);
        System.out.println(sum);
    }
}
