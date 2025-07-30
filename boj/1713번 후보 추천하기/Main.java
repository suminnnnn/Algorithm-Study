import java.util.*;
import java.io.*;

class Student implements Comparable<Student>{

    public int num;
    public int recommendCnt;
    public int turn;

    public Student(int num, int recommendCnt, int turn){
        this.num = num;
        this.recommendCnt = recommendCnt;
        this.turn = turn;
    }

    @Override
    public int compareTo(Student o) {
        if(this.recommendCnt!=o.recommendCnt){
            return this.recommendCnt-o.recommendCnt;
        } else if(this.turn!=o.turn){
            return this.turn-o.turn;
        } else {
            return this.num-o.num;
        }
    }
}

public class Main {

    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        LinkedList<Student> frame = new LinkedList<>();

        Set<Integer> set = new HashSet<>();

        int N = Integer.parseInt(br.readLine());

        int R = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());

        for(int r=1; r<=R; r++){
            int stdNum = Integer.parseInt(st.nextToken());

            if(set.contains(stdNum)){
                for(int i=0; i<frame.size(); i++){
                    if(frame.get(i).num==stdNum){
                        Student student = frame.get(i);
                        student.recommendCnt++;
                    }
                }
            }else{
                if(frame.size()>=N){
                    Student student = frame.remove(0);
                    student.recommendCnt = 0;
                    set.remove(student.num);

                    frame.add(new Student(stdNum, 1, r));
                    set.add(stdNum);
                }else{
                    Student student = new Student(stdNum, 1, r);
                    frame.add(student);
                    set.add(stdNum);
                }
            }

            Collections.sort(frame);

        }

        StringBuffer sb = new StringBuffer();
        Object[] numArr = set.toArray();

        Arrays.sort(numArr);

        for (Object o : numArr) {
            sb.append(o);
            sb.append(" ");
        }

        System.out.println(sb.toString());
    }
}