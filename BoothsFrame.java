
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.awt.*;
import java.awt.event.*;
public class BoothsFrame extends Frame{
    Button find;
    TextField num1,num2;
    TextArea res;
    Label msg;
    public BoothsFrame(){
        setLayout(new BorderLayout(10,10));
        addWindowListener(new Close());
        num1=new TextField();
        num1.setName("Number 1");
        num2=new TextField();
        num2.setName("Number 2");
        find=new Button("Calculate");
        find.addActionListener(new Listener());
        msg=new Label();
        msg.setBackground(Color.lightGray);
        Panel t=new Panel(new GridLayout(5,1,10,10));
        t.add(new Label("Number 1 (Integer)"));
        t.add(num1);
        t.add(new Label("Number 2 (Integer)"));
        t.add(num2);
        t.add(find);
        add(t,BorderLayout.NORTH);
        res=new TextArea();
        res.setEditable(false);
        ScrollPane j=new ScrollPane();
        j.add(res);
        add(j,BorderLayout.CENTER);
        add(msg,BorderLayout.SOUTH);
    }
    public Insets getInsets(){
        return new Insets(50, 20, 20, 20);
    }
    /**
     *
     * @param multiplicand integer number
     * @param multiplier integer
     * @return integer  value
     */
    public void multiplyInteger(int multiplicand, int multiplier){
        res.setText("");
        
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
        res.append("Multiplicand: "+multiplicand+" \t Binary Form: "+String.valueOf(br)+"\n");
        res.append("Multiplier: "+multiplier+" \t Binary Form: "+String.valueOf(qr)+"\n"+"\n");
        //System.out.println("Multiplicand="+multiplicand+" Binary Form="+String.valueOf(br));
        // System.out.println("Multiplier="+multiplier+" Binary Form="+String.valueOf(qr));
        char qn=qr[size-1],qnPlusOne='0';
        res.append("\nAccumulator \t\t    QR     \t\t Qn Qn+1 \t Sequence Counter"+"\n");
        res.append(String.valueOf(acc) +"\t\t"+ String.valueOf(qr)+ "\t\t"+qn+" "+qnPlusOne+"\t\t\t"+sc+"\n");
        sc--;
        while(sc!=-1)
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
            res.append(String.valueOf(acc) +"\t\t"+ String.valueOf(qr)+ "\t\t"+qn+" "+qnPlusOne+"\t\t\t"+sc+"\n");
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
        res.append("\n\nBinary Form: "+String.valueOf(acc)+String.valueOf(qr));
        res.append("\nResult = "+num);
    }
    class Close extends WindowAdapter{
        public void windowClosing(WindowEvent e){
            System.exit(1);
        }
    }
    class Listener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            try {
                msg.setText("");
                multiplyInteger(Integer.parseInt(num1.getText()), Integer.parseInt(num2.getText()));
            } catch (Exception ae) {
                // TODO: handle exception
                res.setText("");
                msg.setText("Invalid number format");
            }
        }
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
        BoothsFrame bA=new BoothsFrame();
        bA.setSize(500,600);
        bA.setVisible(true);
        bA.setTitle("Booths Algorithm");
    }
}