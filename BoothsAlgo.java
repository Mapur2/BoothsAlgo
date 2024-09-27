
import java.util.Arrays;
import java.util.Scanner;

public class BoothsAlgo {
    public BoothsAlgo(){}

    /**
     *
     * @param multiplicand integer number
     * @param multiplier integer
     * @return integer  value
     */
    public void multiplyInteger(int multiplicand, int multiplier){
        String sa=decToBin(Math.abs(multiplicand));
        String sb=decToBin(Math.abs(multiplier));
        boolean brNegative=false,qrNegative=false;
        if(sa.length()>sb.length())
        {
            int diff=sa.length()-sb.length();
            for (int i = 0; i < diff; i++) {
                sb='0'+sb;
            }
        } else if(sb.length()>sa.length())
        {
            int diff=sb.length()-sa.length();
            for (int i = 0; i < diff; i++) {
                sa='0'+sa;
            }
        }
        char acc[]=new char[sa.length()];
        Arrays.fill(acc,'0');
        char br[],qr[];
        if(multiplicand<0) {
            br = findTwoComplement(sa);
            brNegative=true;
        }
        else {
            br = sa.toCharArray();
        }

        if(multiplier<0) {
            qr = findTwoComplement(sb);
            qrNegative=true;
        }
        else
            qr = sb.toCharArray();

        int sc = qr.length;
        int size=sc;
        char brTwosComplement[]=findTwoComplement(String.valueOf(br));
//        System.out.println(br);
//        System.out.println(qr);
//        System.out.println(brTwosComplement);
        System.out.println("Multiplicand="+multiplicand+" Binary Form="+String.valueOf(br));
        System.out.println("Multiplier="+multiplier+" Binary Form="+String.valueOf(qr));
        char qn=qr[size-1],qnPlusOne='0';
        System.out.println("\nAccumulator \t\t    QR     \t\t Qn Qn+1 \t Sequence Counter");
        System.out.println(Arrays.toString(acc) +"\t\t"+ Arrays.toString(qr)+ "\t\t"+qn+" "+qnPlusOne+"\t\t\t\t"+sc);
        while(sc!=0)
        {
            qn=qr[size-1];
            if(qn==qnPlusOne) {
                qnPlusOne = arithmeticShiftRight(acc, qr);
            }
            else if(qn=='1' && qnPlusOne=='0')
            {
                acc=addTwoBin(acc,brTwosComplement);
                qnPlusOne = arithmeticShiftRight(acc, qr);
            }
            else
            {
                acc=addTwoBin(acc,br);
                qnPlusOne = arithmeticShiftRight(acc, qr);
            }
            System.out.println(Arrays.toString(acc) +"\t\t"+ Arrays.toString(qr)+ "\t\t"+qn+" "+qnPlusOne+"\t\t\t"+sc);
            sc--;
        }
        int num;
        if((brNegative && qrNegative) || (!brNegative && !qrNegative))
        {
            num=binToDec(acc,qr);
        }
        else{
            acc=findTwoComplement(String.valueOf(acc)+String.valueOf(qr));
            num=binToDec(acc)*-1;
        }
        System.out.println("\nResult = "+num);
    }

    /**
     * only accepts absolute value Converts decimal number to binary
     * @param n
     * @return Binary of the number
     */
    private String decToBin(int n){
        String s="";
        while (n!=0){
            s=(n%2)+s;
            n/=2;
        }
        return '0'+s;
    }
    private int binToDec(char a[],char b[]){
        int size=a.length;
        int n[]=new int[size*2];

        int k=0;
        for (int i = 0; i < size; i++) {
            n[k++]=a[i]-48;
        }
        for (int i = 0; i < size; i++) {
            n[k++]=b[i]-48;
        };
        k=0;
        int s=0;
        for (int i = size*2-1; i > -1 ; i--) {
            s=s+((int)Math.pow(2,k++)*n[i]);
        }
        return  s;
    }
    private int binToDec(char a[]){
        int size=a.length;
        int n[]=new int[size];
        int k=0;
        for (int i = 0; i < size; i++) {
            n[k++]=a[i]-48;
        }
        k=0;
        int s=0;
        for (int i = size-1; i > -1 ; i--) {
            s=s+((int)Math.pow(2,k)*n[i]);
            k++;
        }
        return  s;
    }
    private char arithmeticShiftRight(char a[],char b[]){
        int n=a.length;
        char qn=b[n-1];
        char q=a[n-1];
        for (int i = n-1; i >0; i--) {
            a[i]=a[i-1];
        }
        for (int i =n-1; i > 0; i--) {
            b[i]=b[i-1];
        }
        b[0]=q;
       // System.out.println(Arrays.toString(a) +" "+ Arrays.toString(b));
        return qn;
    }
    /**
     * Find 2's complement
     * @param n
     * @return
     */
    private char[] findTwoComplement(String n){
        String s="";
        int size=n.length();
        for (int i = 0; i < size; i++) {
            s=s+(n.charAt(i)=='0'?'1':'0');
        }
        if(s.charAt(size-1)=='0') {
            s=(s.substring(0, size - 1) + '1');
            return s.toCharArray();
        }
        char s1[]=new char[size];
        for (int i = 0; i <size-1 ; i++) {
            s1[i]='0';
        }
        s1[size-1]='1';
        return addTwoBin(s.toCharArray(),s1);
    }
    private char[] addTwoBin(char []a,char []b){
        int size=a.length;
        char n[]=new char[size];
        int c=0;
        for (int i = size-1; i > -1; i--) {
            int d=(a[i]-48)+(b[i]-48)+c;
            n[i]=(char)((d%2)+48);
            c=d/2;
        }
        return n;
    }
    public static void main(String[] args) {
        BoothsAlgo bA=new BoothsAlgo();
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the first number");
        int a=sc.nextInt();
        System.out.println("Enter the second number");
        int b=sc.nextInt();
        bA.multiplyInteger(a,b);
    }
}
